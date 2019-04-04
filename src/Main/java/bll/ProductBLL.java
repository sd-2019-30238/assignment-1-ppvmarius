package bll;

import dao.ProductDAO;
import model.Product;

import java.util.ArrayList;

/**
 * Nivelul bussines - logic. Aceasta clasa foloseste clasele de nivel inferior (DAO - cele pentru legatura cu baza de date).
 */
public class ProductBLL {

    /**
     * Returneaza produsul cu id-ul cautat
     * @param id
     * @return
     */
    public Product findById(int id) {
        ProductDAO productDAO = new ProductDAO();
        Product product = productDAO.findById(id);

        if (product != null)
            return product;
        else
            System.out.println("Cannot find the desired product!");
        return null;
    }

    /**
     * Modifica datele produsului cu cele nou introduse
     * @param product
     */
    public void updateProduct(Product product) {
        if (ProductDAO.updateProduct(product) == 0)
            System.out.println("The product does not exist!" + product.getId());
    }

    /**
     * Sterge produsul avand id-ul introdus.
     * @param id
     */
    public void deleteProduct(int id) {
        ProductDAO productDAO = new ProductDAO();
        productDAO.deleteById(id);
    }

    /**
     * Insereaza un produs nou.
     * @param product
     */
    public void insertProduct(Product product) {
        while(findById(product.getId()) != null){
            product.setId(product.getId() + 1);
        }
        if (ProductDAO.insertProduct(product) == 0)
            System.out.println("Cannot insert the desired client");
    }

    /**
     * Afiseaza toate produsele.
     */
    public void printProducts(){
        ProductDAO productDAO = new ProductDAO();
        ArrayList<Product> products = productDAO.getObjects();
        System.out.println(products);
    }

    /**
     * Returneaza o lista cu toate produsele existente in baza de date.
     * @return
     */
    public ArrayList<Product> getProducts(){
        ProductDAO productDAO = new ProductDAO();
        ArrayList<Product> products = productDAO.getObjects();
        return products;
    }

    public ArrayList<Product> findBySaleType(String saleType){
        ProductDAO productDAO = new ProductDAO();
        ArrayList<Product> products = productDAO.findBySaleType(saleType);
        System.out.println(products);
        return products;
    }
}
