package org.example.services;

import org.example.Customer;
import org.example.userwindows.LoginWindow;
import org.example.enums.ManageCreatePolicyOption;
import org.example.userwindows.InputDialog;
import org.example.userwindows.OutputDialog;
import org.example.policies.Policy;
import org.example.repositories.RepositoryLogIn;
import org.example.repositories.RepositoryPolicy;

import java.io.IOException;
import java.sql.SQLException;

public class PolicyService {

    InputDialog inputDialog;
    private OutputDialog outputDialog;
    RepositoryPolicy repositoryPolicy;
    PolicyCreator policyCreator;
    RepositoryLogIn repositoryLogIn;

    LoginWindow loginWindow;

    public PolicyService(InputDialog inputDialog, OutputDialog outputDialog, RepositoryPolicy repositoryPolicy, PolicyCreator policyCreator,RepositoryLogIn repositoryLogIn) {
        this.inputDialog = inputDialog;
        this.outputDialog = outputDialog;
        this.repositoryPolicy = repositoryPolicy;
        this.policyCreator = policyCreator;
        this.repositoryLogIn = repositoryLogIn;
    }

    public Customer searchPolicy(Customer customer) {
        ManageCreatePolicyOption option = inputDialog.inputChooseViewCreateLogOutOD();
        try {
            runSearchPolicyFunctionSelected(option, customer);
        } catch (Exception e) {
            // no op
        }
        return customer;
    }

    private void runSearchPolicyFunctionSelected(ManageCreatePolicyOption option, Customer customer) throws Exception {
        if (option.isManage()) {
            printPoliciesAvailable(customer.getUserid());
        } else if (option.isCreate()) {
            createCustomerPolicies(customer);
        } else {
            LoginService loginService = new LoginService(repositoryLogIn, inputDialog,outputDialog);
            loginService.login();
        }
    }

    private void createCustomerPolicies(Customer customer) throws Exception {
        Policy personalizedPolicy = policyCreator.create();
        repositoryPolicy.createPolicyDB(personalizedPolicy, customer);
        inputDialog.messageForUserMD("Policy Created!\n" +
                "Type: " + personalizedPolicy.getType() + "\nPrice :" + personalizedPolicy.getPrice() + "\nStart Date :" + personalizedPolicy.getStartDate());
    }

    private void printPoliciesAvailable(int customerId) throws SQLException, IOException {
        outputDialog.outputPoliciesAvailable(customerId);
    }
}
