package view;

import bll.OrderBLL;
import model.Order;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AdminView extends JFrame{
    private JLabel helloLabel = new JLabel("Welcome back!");
    private JButton backButton = new JButton("Back");
    private JButton ordersButton = new JButton("Manage Orders");

    private JPanel dataPanel = new JPanel();
    private JPanel dataPanel1 = new JPanel();
    private JPanel dataPanel2 = new JPanel();
    private JPanel fullPanel = new JPanel();

    /**
     * Creeaza fereastra pentru clienti.
     */
    public AdminView() {

        dataPanel1.add(helloLabel);
        dataPanel2.add(backButton);
        dataPanel2.add(ordersButton);
        dataPanel.add(dataPanel1);
        dataPanel.add(dataPanel2);
        dataPanel.setLayout(new BoxLayout(dataPanel, BoxLayout.Y_AXIS));

        fullPanel.add(dataPanel);
        fullPanel.setLayout(new BoxLayout(fullPanel, BoxLayout.Y_AXIS));
        this.add(fullPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.pack();

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        ordersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminOrderView adminOrderView = new AdminOrderView();
            }
        });
    }
}
