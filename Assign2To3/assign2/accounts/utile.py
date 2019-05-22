from django.shortcuts import render, redirect
from django.contrib.auth import login

class SignupRequest:
    def __init__(self, request, form, mail):
        self.request = request
        self.form = form
        self.mail = mail
    def getRequest(self):
        return self.request
    def getForm(self):
        return self.form
    def getMail(self):
        return self.mail

class SignupRequestHandle:
    def handle(self, requestObj):
        if requestObj.getForm().is_valid():
            user = requestObj.getForm().save(commit=False)
            user.email = requestObj.getMail()
            user.save()
            login(requestObj.getRequest(), user)
            return redirect('furnitures:list')
        else:
            return render(requestObj.getRequest(), 'accounts/signup.html', {'form':requestObj.getForm()})

handleDictio = {"SignupRequest":SignupRequestHandle}
class Mediator:
    def mediate(self, reqObj):
    	return handleDictio[reqObj.__class__.__name__]().handle(reqObj)
