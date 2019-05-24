from django.conf.urls import url
from . import views

app_name = 'furnitures'

urlpatterns = [
    url(r'^$', views.furniture_list, name="list"),
    url(r'^(?P<slug>[\w-]+)/$', views.furniture_detail, name="detail"),
]
