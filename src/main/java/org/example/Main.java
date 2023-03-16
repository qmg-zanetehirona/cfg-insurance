package org.example;

import org.example.question.Question;
import org.example.repositories.RepositoryLogIn;
import org.example.repositories.RepositoryPolicy;
import org.example.services.*;

import javax.swing.*;
import java.sql.Connection;

public class Main {

    public static void main(String[] args) throws Exception {

        InputDialog inputDialog = new InputDialog();
        Database database = new Database();
        Connection connection = database.connectWithDB();
        RepositoryLogIn repositoryLogIn = new RepositoryLogIn(connection);
        RepositoryPolicy repositoryPolicy = new RepositoryPolicy(connection);
        OutputDialog outputDialog = new OutputDialog(repositoryPolicy);
        LoginService loginService = new LoginService(repositoryLogIn,inputDialog,outputDialog);
        QuestionService questionService = new QuestionService(inputDialog, outputDialog);
        PriceService priceService = new PriceService(questionService);
        PolicyCreator policyCreator = new PolicyCreator(inputDialog,priceService,questionService);
        PolicyService policyService = new PolicyService(inputDialog,outputDialog, repositoryPolicy, policyCreator);

        Thread.sleep(3000);


        inputDialog.messageForUserMD("Hello, Welcome to ProtectFirst!");

        Customer customer = loginService.login();

        while (true) {
            Customer customerConId = policyService.searchPolicy(customer);
        }
    }
}
