package org.example;

import org.example.repositories.RepositoryLogIn;
import org.example.repositories.RepositoryPolicy;
import org.example.services.*;

import java.io.IOException;
import java.sql.Connection;

public class Initializer {

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
    PolicyService policyService = new PolicyService(inputDialog, outputDialog, repositoryPolicy, policyCreator);

    public Initializer() throws IOException {
    }

    public InputDialog getInputDialog() {
        return inputDialog;
    }

    public RepositoryPolicy getRepositoryPolicy() {
        return repositoryPolicy;
    }

    public OutputDialog getOutputDialog() {
        return outputDialog;
    }

    public PolicyCreator getPolicyCreator() {
        return policyCreator;
    }

    public PolicyService getPolicyService() {
        return policyService;
    }
}
