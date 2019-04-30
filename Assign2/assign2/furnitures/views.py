# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.shortcuts import render
from .models import Furniture
# Create your views here.

def furniture_list(request):
    furnitures = Furniture.objects.all().order_by('name')
    return render(request, 'furnitures/furniture_list.html', {'furnitures': furnitures})
