package com.yourcompanyname.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.yourcompanyname.model.Person;

public class PersonDAO {

    public static List<Person> getAllPersons() {
        List<Person> persons = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM person");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Person person = new Person();
                person.setId(rs.getInt("id"));
                person.setName(rs.getString("name"));
                person.setEmail(rs.getString("email"));
                person.setDocumentPath(rs.getString("document_path"));
                person.setPhotoPath(rs.getString("photo_path"));
                persons.add(person);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return persons;
    }

    public static void addPerson(Person person) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement("INSERT INTO person (name, email, document_path, photo_path) VALUES (?, ?, ?, ?)")) {
            ps.setString(1, person.getName());
            ps.setString(2, person.getEmail());
            ps.setString(3, person.getDocumentPath());
            ps.setString(4, person.getPhotoPath());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Implement update and delete operations similarly
}
