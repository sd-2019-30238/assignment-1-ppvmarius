from django.shortcuts import render
from django.http import HttpResponse
from .models import Furniture

class RequestAllFurnitures:
    def __init__(self, request, filterType="name"):
        self.filterType = filterType
        self.request = request

    def getFilterType(self):
        return self.filterType

    def getRequest(self):
        return self.request

class RequestAllFurnituresHandle:
    def handle(self, requestObj):
        furnitures = Furniture.objects.all().order_by(requestObj.getFilterType())
        return render(requestObj.getRequest(), 'furnitures/furniture_list.html', {'furnitures': furnitures})

handleDictio = {"RequestAllFurnitures":RequestAllFurnituresHandle}

class Mediator:
    def mediate(self, reqObj):
    	return handleDictio[reqObj.__class__.__name__]().handle(reqObj)
