package view;


import bll.ProductBLL;
import model.DiscountFactory;
import model.Product;
import model.ShoppingCart;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ClientView extends JFrame{
    private JButton addItemButton = new JButton("Adauga produs in cos");
    private JButton viewCart = new JButton("Vizualizeaza cosul");
    private JButton viewOrders = new JButton("Vizualizeaza comenzi");

    private JPanel dataPanel = new JPanel();
    private JPanel dataPanel1 = new JPanel();
    private JPanel dataPanel2 = new JPanel();
    private JPanel fullPanel = new JPanel();

    private TableModel tableModel;
    private JTable tabel;
    private JScrollPane pane;

    private ProductBLL productBLL = new ProductBLL();
    private ShoppingCart shoppingCart = new ShoppingCart();
    private DiscountFactory discountFactory = new DiscountFactory();
    private ArrayList<Product> products;

    private static int id = 1;

    /**
     * Creeaza fereastra pentru clienti.
     */
    public ClientView(int idClient) {
        this.setTitle("Clienti");

        dataPanel2.add(viewOrders);
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

        addItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Product tempProd = productBLL.findById(id);
                    shoppingCart.addProduct(discountFactory.getDiscount(tabel.getValueAt(tabel.getSelectedRow(), 6).toString()).getCartItem(tempProd));
                } catch (NumberFormatException f) {
                    JOptionPane.showMessageDialog(null, "Not good!");
                }
            }
        });

        viewCart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CartView cartView = new CartView(shoppingCart.getShopItems());
            }
        });
    }

    private void drawTable() {
        this.remove(fullPanel);
        products = productBLL.getProducts();
        tableModel = View.createTableModel(Product.class, products);
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
