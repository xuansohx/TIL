from django.shortcuts import render, redirect
from django.http.response import HttpResponse
from .models import Room, Message
import secrets
import json
import pusher

pusher_client = pusher.Pusher(
  app_id='*****',
  key='*****',
  secret='*****',
  cluster='***,
  ssl=True
)

# Create your views here.
def index(request):
    if request.method == "POST":
        title = request.POST["room-title"]
        max_count = request.POST["room-max-count"]
        code = secrets.token_urlsafe(16)
        room = Room()
        room.title = title
        room.max_connection = max_count # 방 최대 인원
        room.code = code
        room.master_id = request.user.id # 현재 로그인한 아이디
        room.save()
        room.users.add(request.user)
        # 현재 이 방에 몇 명이 있는지 -> 길이로 판단
        current_connection = len(room.users.all) 

        context = {
            'id': room.id,
            'title': title,
            'max_connection': max_count,
            'current_connection' : current_connection,
            'master': room.master.username
        }
        pusher_client.trigger('main', 'create-room', json.dumps(context))
        return HttpResponse('', status=204)
    else:
        
        rooms = Room.objects.all()
        context = {
            'rooms': rooms
        }
        return render(request, 'index.html', context)


def show(request, room_id):
    if request.user.is_authenticated:
        room = Room.objects.get(id=room_id)
        room.users.add(request.user) # 방에 들어갔을 때
        join_message = {
            'user' : request.user.username,
            'contents' : f'{request.user.username}님이 방에 들어왔습니다.'
        }
        pusher_client.trigger(room.code, 'chat', json.dumps(join_message))

        messages = Message.objects.filter(room_id=room.id).order_by("created_at")
        context = {
            'room_id' : room_id,
            'current_connection' : len(room.users.all())
        }
        pusher_client.trigger('main', 'update-room', json.dumps(context))

        context = {
            'room': room,
            'messages': messages
        }
        return render(request, 'show.html', context)
    else:
        return redirect('accounts:login') # move to login page in accounts app

def chat(request, room_id):
    room = Room.objects.get(id=room_id)
    message = Message()
    message.room_id = room.id
    message.contents = request.POST["contents"]
    message.user_id = request.user.id
    message.save()
    context = {
        'user' : request.user.username,
        'contents' : message.contents,
    }
    pusher_client.trigger('room', 'chat', json.dumps(context)) # (page, event, send context)
    return HttpResponse('', status=204)

def exit(request, room_id):  
    room = Room.objects.get(id=room_id) # room_id -> fine current-room
    room.users.remove(request.user) # remove user
    exit_message = {
        'user' : request.user.username,
        'contents' : f'{request.user.username}님이 방에서 나갔습니다.'
    }
    pusher_client.trigger(room.code, 'chat', json.dumps(exit_message))

    # after remove room -> update room
    context = {
        'room_id' : room_id,
        'current_connection' : len(room.users.all())
    }
    pusher_client.trigger('main', 'update-room', json.dumps(context))
    return redirect('boards:index')