package org.example.policies;

import java.util.Objects;

public class PolicyDTO {

    private final int policyId;
    private final String policyType;
    private final double policyPrice;
    private final String policyPostcode;
    private final int User_Id;

    public PolicyDTO(int policyId, String policyType, double policyPrice, String policyPostcode, int user_Id) {
        this.policyId = policyId;
        this.policyType = policyType;
        this.policyPrice = policyPrice;
        this.policyPostcode = policyPostcode;
        User_Id = user_Id;
    }

    public int getPolicyId() {
        return policyId;
    }

    public String getPolicyType() {
        return policyType;
    }

    public double getPolicyPrice() {
        return policyPrice;
    }

    public String getPolicyPostcode() {return policyPostcode;}

    public int getUser_Id() {return User_Id;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PolicyDTO policyDTO = (PolicyDTO) o;
        return policyId == policyDTO.policyId && Double.compare(policyDTO.policyPrice, policyPrice) == 0 && User_Id == policyDTO.User_Id && Objects.equals(policyType, policyDTO.policyType) && Objects.equals(policyPostcode, policyDTO.policyPostcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(policyId, policyType, policyPrice, policyPostcode, User_Id);
    }

    @Override
    public String toString() {
        return String.format("Policy Id: %-12s Price: £%-7s     Postcode: %-15s Type: %-10s",
                policyId, policyPrice, policyPostcode, policyType);
    }
}
