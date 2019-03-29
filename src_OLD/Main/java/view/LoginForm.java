package view;

import bll.AdminBLL;
import model.Admin;
import model.Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm extends JFrame{
    private JPanel rootPanel;
    private JButton loginButton;
    private JButton registerButton;
    private JTextField usernameTextField;
    private JTextField passwordTextField2;
    private JButton adminLoginButton;

    public LoginForm(){
        this.add(rootPanel);
        this.setTitle("Furniture Store");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.pack();

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterForm registerForm = new RegisterForm();
            }
        });

        adminLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminBLL adminBLL = new AdminBLL();
                Admin admin = adminBLL.findByUsernameAndPassword(usernameTextField.getText(), passwordTextField2.getText());
                if(admin != null){
                    AdminView adminView = new AdminView();
                }else{
                    JOptionPane.showMessageDialog(null, "Wrong username or password");
                }
            }
        });
    }
}
