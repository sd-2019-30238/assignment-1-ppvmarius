from django.conf.urls import url
from django.contrib import admin
from . import views

urlpatterns = [
    url(r'^$', views.furniture_list, name="list"),
    url(r'^(?P<slug>[\w-]+)/$', views.furniture_detail, name="detail"),
]
