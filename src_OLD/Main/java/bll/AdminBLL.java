package bll;

import dao.AdminDAO;
import model.Admin;

public class AdminBLL {
    public Admin findByUsernameAndPassword(String username, String password){
        AdminDAO adminDAO = new AdminDAO();
        Admin admin = adminDAO.findByUsernameAndPassword(username, password);

        if(admin != null)
            return admin;
        else
            return null ;
    }
}
