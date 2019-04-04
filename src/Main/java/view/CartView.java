package view;

import bll.ProductBLL;
import model.Product;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.util.ArrayList;

public class CartView extends JFrame{
    private JPanel dataPanel = new JPanel();
    private JPanel dataPanel1 = new JPanel();
    private JPanel fullPanel = new JPanel();

    private TableModel tableModel;
    private JTable tabel;
    private JScrollPane pane;

    private ProductBLL productBLL = new ProductBLL();

//    private static int id = 1;

    /**
     * Creeaza fereastra pentru clienti.
     */
    public CartView(ArrayList<Product> products) {
        this.setTitle("Cart");

        dataPanel.add(dataPanel1);
        dataPanel.setLayout(new BoxLayout(dataPanel, BoxLayout.Y_AXIS));

        tableModel = View.createTableModel(Product.class, products);

        tabel = new JTable(tableModel);
        pane = new JScrollPane(tabel);

        fullPanel.add(dataPanel);
        fullPanel.add(pane);
        fullPanel.setLayout(new BoxLayout(fullPanel, BoxLayout.Y_AXIS));
        this.add(fullPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.pack();
    }


}
