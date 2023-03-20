package org.example;

import org.example.repositories.RepositoryLogIn;
import org.example.repositories.RepositoryPolicy;
import org.example.services.*;

import java.io.IOException;
import java.sql.Connection;

public class Initializer {

    private final InputDialog inputDialog = new InputDialog();
    private final Database database = new Database();
    private final Connection connection = database.connectWithDB();
    private final RepositoryLogIn repositoryLogIn = new RepositoryLogIn(connection);
    private final RepositoryPolicy repositoryPolicy = new RepositoryPolicy(connection);
    private final OutputDialog outputDialog = new OutputDialog(repositoryPolicy);
    private final LoginService loginService = new LoginService(repositoryLogIn,inputDialog,outputDialog);
    private final QuestionService questionService = new QuestionService(inputDialog, outputDialog);
    private final PriceService priceService = new PriceService(questionService);
    private final PolicyCreator policyCreator = new PolicyCreator(inputDialog,priceService,questionService);
    private final PolicyService policyService = new PolicyService(inputDialog, outputDialog, repositoryPolicy, policyCreator);

    public Initializer() throws IOException {
    }

    public InputDialog getInputDialog() {
        return inputDialog;
    }

    public LoginService getLoginService() {
        return loginService;
    }

    public PolicyService getPolicyService() {
        return policyService;
    }
}
