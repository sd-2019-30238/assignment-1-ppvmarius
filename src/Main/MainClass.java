package Main;

import dao.PersonDAO;
import model.Person;

import java.util.ArrayList;

public class MainClass {
    public static void main(String[] args){
        PersonDAO personDAO = new PersonDAO();
        ArrayList<Person> persoane = personDAO.getObjects();
        System.out.println(persoane);
    }
}
