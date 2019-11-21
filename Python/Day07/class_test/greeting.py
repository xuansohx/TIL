from human import Person

a = Person()
a.walk() # 함수 사용
a.say_hello()

print(a.name) # getter의 역할
a.name = 'Banana' # setter의 역할
print(a.name)
