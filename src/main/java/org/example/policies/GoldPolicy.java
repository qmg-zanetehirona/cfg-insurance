package org.example.policies;

public class GoldPolicy implements Policy {

    private final String policyType = "Gold";

    private double price;

    private String postcode;

    private String startdate;

    public GoldPolicy(double price, String postcode, String startdate) {
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