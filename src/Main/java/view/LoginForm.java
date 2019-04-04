package view;

import bll.AdminBLL;
import bll.ClientBLL;
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
    private JButton adminLoginButton;
    private JPasswordField passwordField1;

    public LoginForm(){
        passwordField1.setEchoChar('*');
        this.add(rootPanel);
        this.setTitle("Furniture Store");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.pack();

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClientBLL clientBLL = new ClientBLL();
                Client client = clientBLL.findByUsernameAndPassword(usernameTextField.getText(), passwordField1.getText());
                if(client != null) {
                    ClientView clientView = new ClientView(client.getId());       //trebuie trimis si id-ul
                }else{
                    JOptionPane.showMessageDialog(null, "Wrong username or password!");
                }
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
                Admin admin = adminBLL.findByUsernameAndPassword(usernameTextField.getText(), passwordField1.getText());
                if(admin != null){
                    AdminView adminView = new AdminView();
                }else{
                    JOptionPane.showMessageDialog(null, "Wrong username or password");
                }
            }
        });
    }
}
