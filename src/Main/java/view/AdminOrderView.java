package view;

import bll.OrderBLL;
import com.sun.org.apache.xpath.internal.operations.Or;
import model.Order;
import model.Product;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class AdminOrderView extends JFrame{
    private JButton backButton = new JButton("Back");
    private JButton submitButton = new JButton("Submit change");
    private JLabel statusLabel = new JLabel("New Status");
    private JTextField statusField = new JTextField(10);


    private JPanel dataPanel = new JPanel();
    private JPanel dataPanel1 = new JPanel();
    private JPanel dataPanel2 = new JPanel();
    private JPanel fullPanel = new JPanel();

    private TableModel tableModel;
    private JTable tabel;
    private JScrollPane pane;

    private OrderBLL orderBLL = new OrderBLL();
    ArrayList<Order> orders;

    private static int id = 1;

    /**
     * Creeaza fereastra pentru clienti.
     */
    public AdminOrderView() {
        orders = orderBLL.getOrders();
        if(orders.get(0) == null){
            JOptionPane.showMessageDialog(null, "Nu aveti comenzi active!");
            return;
        }

        this.setTitle("Orders");


        dataPanel1.add(backButton);
        dataPanel2.add(statusLabel);
        dataPanel2.add(statusField);
        dataPanel2.add(submitButton);
        dataPanel.add(dataPanel1);
        dataPanel.add(dataPanel2);
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

        tabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tabel.rowAtPoint(e.getPoint());
                int column = tabel.columnAtPoint(e.getPoint());
                if (row >= 0 && column >= 0) {
                    id = Integer.parseInt(tabel.getValueAt(tabel.getSelectedRow(), 0).toString());
                }
            }
        });

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OrderBLL orderBLL = new OrderBLL();
                Order tempOrder = orderBLL.findById(id);
                orderBLL.updateOrder(new Order(tempOrder.getId(), tempOrder.getClientId(), statusField.getText(), tempOrder.getDescription()));
                drawTable();
            }
        });
    }
    private void drawTable() {
        this.remove(fullPanel);
        orders = orderBLL.getOrders();

        tableModel = View.createTableModel(Order.class, orders);
        tabel = new JTable(tableModel);
        pane = new JScrollPane(tabel);
        fullPanel.removeAll();
        fullPanel.add(dataPanel);
        fullPanel.add(pane);
        fullPanel.setLayout(new BoxLayout(fullPanel, BoxLayout.Y_AXIS));
        this.add(fullPanel);
        this.setVisible(true);
        this.pack();

        tabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tabel.rowAtPoint(e.getPoint());
                int column = tabel.columnAtPoint(e.getPoint());
                if (row >= 0 && column >= 0) {
//                    System.out.println(Integer.parseInt(tabel.getValueAt(tabel.getSelectedRow(),0).toString()));
                    id = Integer.parseInt(tabel.getValueAt(tabel.getSelectedRow(), 0).toString());
                }
            }
        });
    }

    public static int getId(){
        return id;
    }
}
