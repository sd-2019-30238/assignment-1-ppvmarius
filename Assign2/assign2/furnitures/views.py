# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.shortcuts import render

# Create your views here.

def furniture_list(request):
    return render(request, 'furnitures/furniture_list.html')
