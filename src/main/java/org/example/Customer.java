package org.example;

public class Customer {

    private final String username;
    private final int userid;

    public Customer(int userid, String username) {
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
