# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.shortcuts import render
from .models import Furniture
from django.http import HttpResponse
from assign2.utile import RequestAllFurnitures, RequestSpecificFurniture, Mediator
# Create your views here.
mediator = Mediator()

def furniture_list(request):
    # supposing we use this as a controller
    if request.method == 'POST':
        reqAll = RequestAllFurnitures(request, request.POST.get('filter'))
        return mediator.mediate(reqAll)
    else:
        reqAll = RequestAllFurnitures(request, "name")
        return mediator.mediate(reqAll)

def furniture_detail(request, slug):
    reqSpec = RequestSpecificFurniture(request, slug)
    return mediator.mediate(reqSpec)
