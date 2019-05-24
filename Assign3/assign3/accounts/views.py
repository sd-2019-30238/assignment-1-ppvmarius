# -*- coding: utf-8 -*-
from __future__ import unicode_literals
from django.shortcuts import render, redirect
from django.contrib.auth.forms import UserCreationForm, AuthenticationForm
from django.contrib.auth import login, logout
from .utile import SignupRequest, Mediator
# Create your views here.

mediator = Mediator()

def signup_view(request):
    if request.method == 'POST':
        signUpReq = SignupRequest(request, UserCreationForm(request.POST), request.POST.get('email'))
        return mediator.mediate(signUpReq)
    else:
        form = UserCreationForm()
        return render(request, 'accounts/signup.html', {'form':form})

def login_view(request):
    if request.method == 'POST':
        form = AuthenticationForm(data=request.POST)
        if form.is_valid():
            user = form.get_user()
            login(request, user)
            return redirect('furnitures:list')
    else:
        form = AuthenticationForm()
    return render(request, 'accounts/login.html', {'form':form})

def logout_view(request):
    if request.method == 'POST':
        logout(request)
    return redirect('homepage')
