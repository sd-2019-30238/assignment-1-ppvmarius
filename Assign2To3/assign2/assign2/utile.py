from django.shortcuts import render
from django.http import HttpResponse
from furnitures.models import Furniture
from orders.models import Order

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


class RequestSpecificFurniture:
    def __init__(self, request, slug):
        self.request = request
        self.slug = slug
    def getRequest(self):
        return self.request
    def getSlug(self):
        return self.slug

class RequestSpecificFurnitureHandle:
    def handle(self, requestObj):
        furniture = Furniture.objects.get(slug=requestObj.getSlug())
        return render(requestObj.getRequest(), 'furnitures/furniture_detail.html', {'furniture':furniture})


class ModifyFurnitureQuantityQuery:
    def __init__(self, slug):
        self.slug = slug
    def getSlug(self):
        return self.slug

class ModifyFurnitureQuantityQueryHandle:
    def handle(self, requestObj):
        temp_furniture = Furniture.objects.get(slug=requestObj.getSlug())
        temp_furniture.quantity = temp_furniture.quantity - 1
        temp_furniture.save()


class AddOrderRequest:
    def __init__(self, furnitureSlug, user, status):
        self.furnitureSlug = furnitureSlug
        self.user = user
        self.status = status
    def getFurnitureSlug(self):
        return self.furnitureSlug
    def getUser(self):
        return self.user
    def getStatus(self):
        return self.status

class AddOrderRequestHandle:
    def handle(self, requestObj):
        order = Order()
        order.furniture = Furniture.objects.get(slug=requestObj.getFurnitureSlug())
        order.client = requestObj.getUser()
        order.status = requestObj.getStatus()
        order.save()


class RequestAllOrders:
    def __init__(self, request, filterType="id"):
        self.request = request
        self.filterType = filterType
    def getRequest(self):
        return self.request
    def getFilterType(self):
        return self.filterType

class RequestAllOrdersHandle:
    def handle(self, requestObj):
        orders = Order.objects.all().order_by('id')
        if not requestObj.getRequest().user.is_staff:
            orders = [item for item in orders if requestObj.getRequest().user == item.client]
        return render(requestObj.getRequest(), 'orders/list_orders.html', {'orders':orders})


class RequestSpecificOrder:
    def __init__(self, request, id):
        self.request = request
        self.id = id
    def getRequest(self):
        return self.request
    def getId(self):
        return self.id

class RequestSpecificOrderHandle:
    def handle(self, requestObj):
        order = Order.objects.get(id=requestObj.getId())
        return render(requestObj.getRequest(), 'orders/order_details.html', {'order':order})


class RequestModifySpecificOrder:
    def __init__(self, id, newStatus):
        self.id = id
        self.newStatus = newStatus
    def getId(self):
        return self.id
    def getNewStatus(self):
        return self.newStatus

class RequestModifySpecificOrderHandle:
    def handle(self, requestObj):
        order = Order.objects.get(id=requestObj.getId())
        order.status = requestObj.getNewStatus()
        order.save()

handleDictio = {"RequestAllFurnitures":RequestAllFurnituresHandle, \
                "RequestSpecificFurniture":RequestSpecificFurnitureHandle, \
                "ModifyFurnitureQuantityQuery":ModifyFurnitureQuantityQueryHandle, \
                "AddOrderRequest":AddOrderRequestHandle, \
                "RequestAllOrders":RequestAllOrdersHandle, \
                "RequestSpecificOrder":RequestSpecificOrderHandle, \
                "RequestModifySpecificOrder":RequestModifySpecificOrderHandle}
class Mediator:
    def mediate(self, reqObj):
    	return handleDictio[reqObj.__class__.__name__]().handle(reqObj)
