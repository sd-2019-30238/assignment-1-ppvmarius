package view;

import javax.swing.*;

public class ClientView extends JFrame{
    private JPanel rootPanel;

    public ClientView(){
        this.add(rootPanel);
        this.setTitle("Furniture Store");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.pack();
    }
}
