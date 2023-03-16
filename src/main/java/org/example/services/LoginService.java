package org.example.services;

import org.example.Customer;
import org.example.InputDialog;
import org.example.OutputDialog;
import org.example.exceptions.IncorrectPasswordRequirementsException;
import org.example.exceptions.IncorrectUsernamePasswordException;
import org.example.exceptions.UsernameTakenException;
import org.example.repositories.RepositoryLogIn;

import java.sql.SQLException;

public class LoginService {

    RepositoryLogIn repositoryLogIn;

    InputDialog inputDialog;

    OutputDialog outputDialog;

    public LoginService(RepositoryLogIn repositoryLogIn, InputDialog inputDialog, OutputDialog outputDialog) {
        this.repositoryLogIn = repositoryLogIn;
        this.inputDialog = inputDialog;
        this.outputDialog = outputDialog;
    }

    public Customer login() {
        String tryAgain = "1";
        while (tryAgain.equals("1")) {
            try {
                return userLoginOrRegistration();
            } catch (Exception e) {
                //TODO JOPTION ERROR
                outputDialog.outputErrorMessage(e);
                String response = inputDialog.inputWouldYouLikeToTryAgainCD(e);
                tryAgain = response != null && response.equals("1") ? "1" : "0";
            }
        }
        closeApp();
        return null;
    }

    public Customer userLoginOrRegistration() throws Exception {

        String option = inputDialog.inputChooseLoginSignUpExitOD();

        if (option.equals("1") || option.equals("2")) {

            String username = inputDialog.inputUsernameIM();
            String originalPassword = inputDialog.inputOriginalPasswordIM();
            return runFunctionSelected(option, username, originalPassword);
        }
        closeApp();
        return null;
    }

    public Customer runFunctionSelected(String option, String username, String originalPassword)
            throws
            SQLException, IncorrectUsernamePasswordException, UsernameTakenException, IncorrectPasswordRequirementsException {
        Customer customer;
        if (option.equals("1")) {
            customer = repositoryLogIn.logInExistingCustomer(username, originalPassword);
            inputDialog.messageForUserMD("Access granted!");
        } else {
            customer = repositoryLogIn.createCustomer(username, originalPassword);
            inputDialog.messageForUserMD("Customer created!");
        }
        return customer;
    }

    private void closeApp() {
        inputDialog.messageForUserMD("Closing app...");
        System.exit(0);
    }
}

