package view;


import bll.ProductBLL;
import model.Product;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.util.ArrayList;

public class ClientView extends JFrame{
    private JButton addItemButton = new JButton("Adauga produs in cos");
    private JButton viewCart = new JButton("Vizualizeaza cosul");

    private JPanel dataPanel = new JPanel();
    private JPanel dataPanel1 = new JPanel();
    private JPanel dataPanel2 = new JPanel();
    private JPanel fullPanel = new JPanel();

    private TableModel tableModel;
    private JTable tabel;
    private JScrollPane pane;

    private ProductBLL productBLL = new ProductBLL();

    private ArrayList<Product> products;

    private static int id = 1;

    /**
     * Creeaza fereastra pentru clienti.
     */
    public ClientView(int id) {
        this.setTitle("Clienti");

        dataPanel2.add(addItemButton);
        dataPanel2.add(viewCart);
        dataPanel.add(dataPanel1);
        dataPanel.add(dataPanel2);
        dataPanel.setLayout(new BoxLayout(dataPanel, BoxLayout.Y_AXIS));

        products = productBLL.getProducts();
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
