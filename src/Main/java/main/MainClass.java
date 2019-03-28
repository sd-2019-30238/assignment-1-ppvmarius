package main;

import dao.ClientDAO;
import model.Client;
import view.LoginForm;

import java.util.ArrayList;

public class MainClass {
    public static void main(String[] args){
        ClientDAO clientDAO = new ClientDAO();
        ArrayList<Client> persoane = clientDAO.getObjects();
        System.out.println(persoane);

        LoginForm loginForm = new LoginForm();
    }
}