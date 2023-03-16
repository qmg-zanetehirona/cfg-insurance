package org.example.services;

import org.example.Customer;
import org.example.InputDialog;
import org.example.OutputDialog;
import org.example.policies.Policy;
import org.example.repositories.RepositoryPolicy;

import java.sql.SQLException;

public class PolicyService {

    InputDialog inputDialog;
    private OutputDialog outputDialog;
    RepositoryPolicy repositoryPolicy;
    PolicyCreator policyCreator;

    public PolicyService(InputDialog inputDialog, OutputDialog outputDialog, RepositoryPolicy repositoryPolicy, PolicyCreator policyCreator) {
        this.inputDialog = inputDialog;
        this.outputDialog = outputDialog;
        this.repositoryPolicy = repositoryPolicy;
        this.policyCreator = policyCreator;
    }

    public Customer searchPolicy(Customer customer) {
        String option = inputDialog.inputChooseViewCreateLogOutOD();
        try {
            runSearchPolicyFunctionSelected(option, customer);
        } catch (Exception e) {
            // no op
        }
        return customer;
    }

    private void runSearchPolicyFunctionSelected(String option, Customer customer) throws Exception {
        if (option.equals("1")) {
            printPoliciesAvailable(customer.getUserid());
        } else if (option.equals("2")) {
            createCustomerPolicies(customer);
        } else {
            closeApp();
        }
    }

    private void createCustomerPolicies(Customer customer) throws Exception {
        Policy personalizedPolicy = policyCreator.create();
        repositoryPolicy.createPolicyDB(personalizedPolicy, customer);
        inputDialog.messageForUserMD("Policy Created!\n" +
                "Type: " + personalizedPolicy.getType() + "\nPrice :" + personalizedPolicy.getPrice() + "\nStart Date :" + personalizedPolicy.getStartDate());
    }


    private void printPoliciesAvailable(int customerId) throws SQLException {
        outputDialog.outputPoliciesAvailable(customerId);
    }
    private void closeApp() {
        inputDialog.messageForUserMD("Closing app...");
        System.exit(0);
    }


}
