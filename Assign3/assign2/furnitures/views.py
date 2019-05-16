# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.shortcuts import render
from .models import Furniture
from django.http import HttpResponse
from .utile import FurnitureQueryService, FurnitureCommandService
# Create your views here.

furnitureQuery = FurnitureQueryService()
furnitureCommand = FurnitureCommandService()

def furniture_list(request):
    if request.method == 'POST':
        furnitures = furnitureQuery.getFurnitures(request.POST.get('filter'))
    else:
        furnitures =  furnitureQuery.getFurnitures('name')
    return render(request, 'furnitures/furniture_list.html', {'furnitures': furnitures})

def furniture_detail(request, slug):
    # return HttpResponse(slug)
    furniture = furnitureQuery.getFurniture(slug=slug)
    return render(request, 'furnitures/furniture_detail.html', {'furniture':furniture})
