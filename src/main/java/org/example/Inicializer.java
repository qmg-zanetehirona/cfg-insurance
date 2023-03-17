package org.example;

import org.example.repositories.RepositoryLogIn;
import org.example.repositories.RepositoryPolicy;
import org.example.services.LoginService;
import org.example.services.PolicyCreator;
import org.example.services.PriceService;
import org.example.services.QuestionService;

import java.io.IOException;
import java.sql.Connection;

public class Inicializer {

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

    public Inicializer() throws IOException {
    }
}
