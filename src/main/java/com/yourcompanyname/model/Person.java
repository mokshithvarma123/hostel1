package com.yourcompanyname.model;

public class Person {
    private int id;
    private String name;
    private String email;
    private String documentPath;
    private String photoPath;

    // Constructors, getters, and setters
    public Person() {
    }

    public Person(String name, String email, String documentPath, String photoPath) {
        this.name = name;
        this.email = email;
        this.documentPath = documentPath;
        this.photoPath = photoPath;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDocumentPath() {
        return documentPath;
    }

    public void setDocumentPath(String documentPath) {
        this.documentPath = documentPath;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }
}
