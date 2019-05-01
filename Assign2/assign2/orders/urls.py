from django.conf.urls import url
from . import views

app_name = 'orders'

urlpatterns = [
    url(r'^add_order', views.add_order, name="add_order"),
]
