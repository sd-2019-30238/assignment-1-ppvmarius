package view;

import bll.OrderBLL;
import model.Order;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OrderView extends JFrame{
    private JButton backButton = new JButton("Back");

    private JPanel dataPanel = new JPanel();
    private JPanel dataPanel1 = new JPanel();
    private JPanel fullPanel = new JPanel();

    private TableModel tableModel;
    private JTable tabel;
    private JScrollPane pane;

    private OrderBLL orderBLL = new OrderBLL();
    ArrayList<Order> orders;

    /**
     * Creeaza fereastra pentru clienti.
     */
    public OrderView(int clientId) {
        orders = orderBLL.getClientOrders(clientId);
        if(orders.get(0) == null){
            JOptionPane.showMessageDialog(null, "Nu aveti comenzi active!");
            return;
        }

        this.setTitle("Orders");


        dataPanel1.add(backButton);
        dataPanel.add(dataPanel1);
        dataPanel.setLayout(new BoxLayout(dataPanel, BoxLayout.Y_AXIS));

        tableModel = View.createTableModel(Order.class, orders);

        tabel = new JTable(tableModel);
        pane = new JScrollPane(tabel);

        fullPanel.add(dataPanel);
        fullPanel.add(pane);
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
    }
}
