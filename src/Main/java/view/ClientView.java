package view;


import bll.OrderBLL;
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
    private JButton placeOrderButton = new JButton("Plaseaza comanda");
    private JButton filterButton = new JButton("Filtreaza");
    private String[] options = {"all", "20%", "50%", "1+1"};
    private JComboBox filterComboBox = new JComboBox(options);

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

    private OrderBLL orderBLL = new OrderBLL();

    private static int id = 1;

    /**
     * Creeaza fereastra pentru clienti.
     */
    public ClientView(int idClient) {
        this.setTitle("Clienti");

        dataPanel1.add(filterComboBox);
        dataPanel1.add(filterButton);
        dataPanel2.add(viewOrders);
        dataPanel2.add(addItemButton);
        dataPanel2.add(viewCart);
        dataPanel2.add(placeOrderButton);
        dataPanel.add(dataPanel1);
        dataPanel.add(dataPanel2);
        dataPanel.setLayout(new BoxLayout(dataPanel, BoxLayout.Y_AXIS));

        if(String.valueOf(filterComboBox.getSelectedItem()).equals("all")){
            products = productBLL.getProducts();
        }else{
            products = productBLL.findBySaleType(String.valueOf(filterComboBox.getSelectedItem()));
        }
//        System.out.println(products + String.valueOf(filterComboBox.getSelectedItem()));
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
                    JOptionPane.showMessageDialog(null, "Added succesfully!");
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

        placeOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int sum = 0;
                for(Product prod: shoppingCart.getShopItems()){
                    productBLL.updateProduct(new Product(prod.getId(), prod.getProductName(), productBLL.findById(prod.getId()).getPrice(), prod.getProdDescription(), prod.getCategory(), productBLL.findById(prod.getId()).getQuantity() - prod.getQuantity(), prod.getSaleType()));
                    sum += prod.getPrice();
                }
                orderBLL.insertOrder(1, idClient, "Pending", "Value" + sum);
                drawTable();
                shoppingCart.removeAllProducts();
                JOptionPane.showMessageDialog(null, "Order placed successfully");
            }
        });

        viewOrders.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OrderView orderView = new OrderView(idClient);
            }
        });

        filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawTable();
            }
        });
    }

    private void drawTable() {
        this.remove(fullPanel);
        if(String.valueOf(filterComboBox.getSelectedItem()).equals("all")){
            products = productBLL.getProducts();
        }else{
            products = productBLL.findBySaleType(String.valueOf(filterComboBox.getSelectedItem()));
        }
//        System.out.println(products + String.valueOf(filterComboBox.getSelectedItem()));

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
