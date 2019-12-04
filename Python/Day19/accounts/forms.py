
from django import forms
from django.contrib.auth.forms import UserCreationForm, AuthenticationForm
from .models import User

class CustomUserCreationForm(UserCreationForm):
    address = forms.CharField(min_length=3)
    class Meta(UserCreationForm.Meta):
        model = User
        fields = UserCreationForm.Meta.fields + ('address', )

class CustomAuthenticationForm(AuthenticationForm):
    class Meta(AuthenticationForm):
        model = User