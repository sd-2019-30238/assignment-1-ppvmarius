package view;

import bll.ClientBLL;
import dao.ClientDAO;
import model.Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterForm extends JFrame{
    private JPanel rootPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JButton submitButton;
    ClientBLL clientBLL = new ClientBLL();

    public RegisterForm(){
        this.add(rootPanel);
        this.setTitle("Register Form");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.pack();

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client client = new Client(1, textField1.getText(), textField2.getText(), textField3.getText(), Integer.parseInt(textField4.getText()), textField5.getText(), textField6.getText());
                clientBLL.insertClient(client);
                setVisible(false);
            }
        });
    }
}
