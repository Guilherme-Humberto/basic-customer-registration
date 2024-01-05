package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import database.DbConnection;
import entities.Client;

public class ClientDAO {
    public void create(Client client) {
        String sql = "INSERT INTO client (firstName, lastName, email, age) values (?, ?, ?, ?)";
        PreparedStatement statement = null;

        try {
            statement = DbConnection.getConnection().prepareStatement(sql);
            statement.setString(1, client.getFirstName());
            statement.setString(2, client.getLastName());
            statement.setString(3, client.getEmail());
            statement.setInt(4, client.getAge());

            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Client> list() {
        String sql = "SELECT * FROM client";
        PreparedStatement statement = null;
        List<Client> clients = new ArrayList<>();

        try {
            statement = DbConnection.getConnection().prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            
            while(result.next()) {
                Client client = new Client();
                client.setFirstName(result.getString("firstName"));
                client.setLastName(result.getString("lastName"));
                client.setEmail(result.getString("email"));
                client.setAge(result.getInt("age"));

                clients.add(client);
            }

            return clients;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void update(int clientId, String firstName, String lastName, String email, int age) {
        PreparedStatement statement = null;
        String sql = "UPDATE client SET ";
        List<String> updates = new ArrayList<>();

        if (firstName != null) updates.add("firstName = ?");
        if (lastName != null) updates.add("lastName = ?");
        if (email != null) updates.add("email = ?");
        if (age != 0) updates.add("age = ?");

        sql += String.join(", ", updates) + " WHERE id = ?";

        try {
            statement = DbConnection.getConnection().prepareStatement(sql);
            int parameterIndex = 1;

            if (firstName != null) statement.setString(parameterIndex++, firstName);
            if (lastName != null) statement.setString(parameterIndex++, lastName);
            if (email != null) statement.setString(parameterIndex++, email);
            if (age != 0) statement.setInt(parameterIndex++, age);

            statement.setInt(parameterIndex, clientId);

            statement.executeUpdate();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remove(int clientId) {
        String sql = "DELETE FROM client WHERE id = ?";
        PreparedStatement statement = null;

        try {
            statement = DbConnection.getConnection().prepareStatement(sql);
            statement.setInt(1, clientId);
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

