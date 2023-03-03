package org.example;

public class Customer {

    private final String username;
    private int userid;

    public Customer(String username) {
        this.username = username;
    }

    public Customer(String username, int userid) {
        this.username = username;
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public int getUserid() {
        return userid;
    }
}
