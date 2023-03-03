package org.example;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class PolicyService {

    Repository repository;

    public PolicyService(Repository repository) {
        this.repository = repository;
    }

    Scanner scan = new Scanner(System.in);

    private String userChooseFunctionPolicy() {

        System.out.print("Would you like to:\n" +
                "1. View existing policy \n" +
                "2. Create a new one \n" +
                "Please enter a value: -> : ");
        return scan.next();
    }

    //Create test
    private Customer createCustomerWithId(Customer customer) throws SQLException, IncorrectUsernamePasswordException {
        return new Customer(customer.getUsername(), repository.getCustomerId(customer.getUsername()));
    }

    private List<String> runSearchPolicyFunctionSelected(String option, Customer customer)
            throws SQLException {
        List<String> listPolicies;
        if (option.equals("1")) {
            listPolicies = getCustomerPolicies(customer);
            System.out.println("**********************************");
            System.out.println("******    VIEW POLICY    ******");
            System.out.println("**********************************");
        } else {
            listPolicies = createCustomerPolicies(customer);
            System.out.println("**********************************");
            System.out.println("*****    CREATE POLICY    *****");
            System.out.println("**********************************");
        }
        return listPolicies;
    }

    private List<String> getCustomerPolicies(Customer customer) throws SQLException {
        return repository.getListPolicyTypes(customer.getUserid());
    }

    private List<String> createCustomerPolicies(Customer customer) {
        return null;
    }

    private void printPoliciesAvailable(int customerId) throws SQLException {
        System.out.println("Policies available: ");
        repository.getListPolicyTypes(customerId).forEach(System.out::println);
    }

    public Customer searchPolicy(Customer customer) throws SQLException, IncorrectUsernamePasswordException {
        String option = userChooseFunctionPolicy();
        Customer customerWithId = createCustomerWithId(customer);
        runSearchPolicyFunctionSelected(option, customerWithId);
        printPoliciesAvailable(customerWithId.getUserid());
        return customerWithId;
    }
}
