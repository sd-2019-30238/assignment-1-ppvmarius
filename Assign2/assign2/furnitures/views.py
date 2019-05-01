# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.shortcuts import render
from .models import Furniture
from django.http import HttpResponse
# Create your views here.

def furniture_list(request):
    if request.method == 'POST':
        if request.POST.get('filter') == "sale_type":
            furnitures = Furniture.objects.all().order_by('saleType')
        elif request.POST.get('filter') == "price":
            furnitures = Furniture.objects.all().order_by('newPrice')
        else:
            furnitures = Furniture.objects.all().order_by('name')
    else:
        furnitures = Furniture.objects.all().order_by('name')
    return render(request, 'furnitures/furniture_list.html', {'furnitures': furnitures})

def furniture_detail(request, slug):
    # return HttpResponse(slug)
    furniture = Furniture.objects.get(slug=slug)
    return render(request, 'furnitures/furniture_detail.html', {'furniture':furniture})
