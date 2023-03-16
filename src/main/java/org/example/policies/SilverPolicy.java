package org.example.policies;

import org.example.policies.Policy;

public class SilverPolicy implements Policy {

    private final String policyType= "Silver";

    private double price;

    private String postcode;

    private String startdate;

    public SilverPolicy(double price, String postcode, String startdate) {
        this.price = price;
        this.postcode = postcode;
        this.startdate = startdate;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public String getType() {
        return policyType;
    }

    @Override
    public String getPostCode() { return postcode; }

    @Override
    public String getStartDate() {
        return startdate;}

}

