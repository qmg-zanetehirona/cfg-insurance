package org.example.repositories;

import org.example.Customer;
import org.example.exceptions.IncorrectUsernamePasswordException;
import org.example.policies.Policy;
import org.example.policies.PolicyDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepositoryPolicy {
    public Connection connect;
    public RepositoryPolicy(Connection connect) {
        this.connect = connect;
    }
    public int getCustomerId(String username) throws SQLException, IncorrectUsernamePasswordException {
        ResultSet resultSet = connect.createStatement().
                executeQuery(String.format(" SELECT UserId FROM usersregistration WHERE user_username = '%s'", username));
        if (resultSet.next()) {
            return resultSet.getInt("UserId");
        }
        throw new IncorrectUsernamePasswordException("Incorrect username or password");
    }

    // not needed anymore
    public List<String> getListPolicyTypes(int customerId) throws SQLException {
        List<String> ListPolicytypes = new ArrayList<>();
        ResultSet resultSet = connect.createStatement().executeQuery(String.format(" SELECT PolicyType\n" +
                "FROM Policies\n" +
                "WHERE User_Id='%s'", customerId));
        while (resultSet.next()) {
            String gePolicyTypes = resultSet.getString("PolicyType");
            ListPolicytypes.add(gePolicyTypes);
        }
        return ListPolicytypes;
    }

    public void createPolicyDB(Policy policy, Customer customer) throws SQLException {
        connect.createStatement().executeUpdate
                (String.format(" INSERT INTO Policies (PolicyType,PolicyPrice,PolicyPostcode,User_Id) VALUES ('%s','%s','%s','%s')", policy.getType(), policy.getPrice(), policy.getPostCode(), customer.getUserid()));
    }

    public void deletePolicyDB(int polityId) throws SQLException {
        connect.createStatement().executeUpdate
                (String.format(" DELETE FROM Policies WHERE PolicyId = '%s'", polityId));
    }

    public List<PolicyDTO> getPolicyDTO(int customerId) throws SQLException {
        List<PolicyDTO> policyDTOlist = new ArrayList<>();
        ResultSet resultSet = connect.createStatement().
                executeQuery(String.format(" SELECT " +
                        "PolicyId,\n" +
                        "PolicyType,\n" +
                        "PolicyPrice,\n" +
                        "PolicyPostcode,\n" +
                        "User_Id FROM Policies WHERE User_Id = '%s'", customerId));
        while (resultSet.next()) {
            int policyId = resultSet.getInt("PolicyId");
            String policyType = resultSet.getString("PolicyType");
            double policyPrice = resultSet.getDouble("PolicyPrice");
            String policyPostCode = resultSet.getString("PolicyPostcode");
            int userId = resultSet.getInt("User_Id");
           policyDTOlist.add(new PolicyDTO(policyId, policyType, policyPrice, policyPostCode, userId));
        }
        return policyDTOlist;
    }

    public void modifyPolicyDB(int policyid, String newPolicyType, int priceAdjustment) throws SQLException {
        connect.createStatement().executeUpdate
                (String.format(" UPDATE Policies SET PolicyType = '%s', PolicyPrice = PolicyPrice + '%s' WHERE PolicyId = '%s'", newPolicyType,priceAdjustment,policyid));
    }

    public List<PolicyDTO> getPolicyDTOwithPolicyId(int selectedPolicyId) throws SQLException {
        List<PolicyDTO> policyDTOlist = new ArrayList<>();
        ResultSet resultSet = connect.createStatement().
                executeQuery(String.format(" SELECT " +
                        "PolicyId,\n" +
                        "PolicyType,\n" +
                        "PolicyPrice,\n" +
                        "PolicyPostcode,\n" +
                        "User_Id FROM Policies WHERE PolicyId = '%s'", selectedPolicyId));
        while (resultSet.next()) {
            int policyId = resultSet.getInt("PolicyId");
            String policyType = resultSet.getString("PolicyType");
            double policyPrice = resultSet.getDouble("PolicyPrice");
            String policyPostCode = resultSet.getString("PolicyPostcode");
            int userId = resultSet.getInt("User_Id");
            policyDTOlist.add(new PolicyDTO(policyId, policyType, policyPrice, policyPostCode, userId));
        }
        return policyDTOlist;
    }
}
