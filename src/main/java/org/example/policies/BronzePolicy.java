package org.example.policies;

import java.util.Objects;

public class BronzePolicy implements Policy {

    private String policyType = "Bronze";

    private double price;

    private String postcode;

    private String startdate;

    public BronzePolicy(double price, String postcode, String startdate) {
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
        return startdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BronzePolicy that = (BronzePolicy) o;
        return Double.compare(that.price, price) == 0 && Objects.equals(policyType, that.policyType) && Objects.equals(postcode, that.postcode) && Objects.equals(startdate, that.startdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(policyType, price, postcode, startdate);
    }

    @Override
    public String toString() {
        return "BronzePolicy{" +
                "policyType='" + policyType + '\'' +
                ", price=" + price +
                ", postcode='" + postcode + '\'' +
                ", startdate='" + startdate + '\'' +
                '}';
    }
}

