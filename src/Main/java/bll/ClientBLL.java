package bll;

import dao.ClientDAO;
import model.Client;
import java.util.ArrayList;

/**
 * Nivelul bussines - logic. Aceasta clasa foloseste clasele de nivel inferior (DAO - cele pentru legatura cu baza de date).
 */
public class ClientBLL{

    /**
     * Returneaza clientul cu id-ul cautat.
     * @param id
     * @return
     */
    public Client findById(int id){
        ClientDAO clientDAO = new ClientDAO();
        Client client = clientDAO.findById(id);

        if(client != null)
            return client;
        else
            return null;
    }

    /**
     * Updateaza clientul cu noile date introduse.
     * @param client
     */
    public void updateClient(Client client){
        if(ClientDAO.updateClient(client) == 0){
            System.out.println("The client does not exist!");
        }
    }

    /**
     * Sterge clientul avand id-ul dat.
     * @param id
     */
    public void deleteClient(int id){
        ClientDAO clientDAO = new ClientDAO();
        clientDAO.deleteById(id);
    }

    /**
     * Insereaza un client nou.
     * @param client
     */
    public void insertClient(Client client){
        while(findById(client.getId()) != null){
            client.setId(client.getId() + 1);
        }
        if(ClientDAO.insertClient(client) == 0){
            System.out.println("Cannot insert the client!");
        }
    }

    /**
     * Afiseaza toti clientii.
     */
    public void printClients(){
        ClientDAO clientDAO = new ClientDAO();
        ArrayList<Client> clients = clientDAO.getObjects();
        System.out.println(clients);
    }

    /**
     * Returneaza o lista cu toti clientii existenti in baza de date.
     * @return
     */
    public ArrayList<Client> getClients(){
        ClientDAO clientDAO = new ClientDAO();
        ArrayList<Client> clients = clientDAO.getObjects();
        return clients;
    }
}
