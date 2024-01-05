import dao.ClientDAO;
import entities.Client;
import java.util.List;

public class App {

    public static void listClients(ClientDAO clientDAO) {
        //_______________Lista cliente _______________

        List<Client> clients = clientDAO.list();

        for (Client client : clients) {
            System.out.println(client.getFirstName());
            System.out.println(client.getLastName());
            System.out.println(client.getEmail());
            System.out.println(client.getAge());
        }
    }
    
    public static void main(String[] args) throws Exception {
        ClientDAO clientDAO = new ClientDAO();
        Client client = new Client();

        //_______________Criação de clientes _______________

        client.setFirstName("Java");
        client.setLastName("Script");
        client.setEmail("teste@teste.com");
        client.setAge(0);

        clientDAO.create(client);
        listClients(clientDAO);

        //_______________Atualiza clientes _______________


        clientDAO.update(1, "Python", null, null, 0);
        listClients(clientDAO);

        //_______________Remoção cliente _______________

        clientDAO.remove(1);
        listClients(clientDAO);

    }
}

