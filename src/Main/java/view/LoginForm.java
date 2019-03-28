package view;

import model.Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm extends JFrame{
    private JPanel rootPanel;
    private JButton loginButton;
    private JButton registerButton;
    private JTextField textField1;
    private JTextField textField2;
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

            }
        });
    }
}
