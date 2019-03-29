package view;

import javax.swing.*;

public class AdminView extends JFrame{
    private JPanel rootPanel;

    public AdminView(){
        this.add(rootPanel);
        this.setTitle("Admin Form");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.pack();
    }
}
