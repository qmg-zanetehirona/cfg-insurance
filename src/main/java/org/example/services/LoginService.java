package org.example.services;

import org.example.Customer;
import org.example.enums.LogInSignUpOption;
import org.example.enums.YesNoOption;
import org.example.InputDialog;
import org.example.OutputDialog;
import org.example.exceptions.IncorrectPasswordRequirementsException;
import org.example.exceptions.IncorrectUsernamePasswordException;
import org.example.exceptions.UsernameTakenException;
import org.example.repositories.RepositoryLogIn;

import java.sql.SQLException;

import static org.example.enums.YesNoOption.NO;
import static org.example.enums.YesNoOption.YES;

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
        YesNoOption tryAgain = YES;
        while (tryAgain.isYes()) {
            try {
                return userLoginOrRegistration();
            } catch (Exception e) {
                outputDialog.outputErrorMessage(e);
                tryAgain = inputDialog.inputWouldYouLikeToTryAgainCD(e);
            }
        }
        closeApp();
        return null;
    }

    public Customer userLoginOrRegistration() throws Exception {
        LogInSignUpOption logInSignUpOption = inputDialog.inputChooseLoginSignUpExitOD();
        String username = inputDialog.inputUsernameIM();
        String originalPassword = inputDialog.inputOriginalPasswordIM();
        return runFunctionSelected(logInSignUpOption, username, originalPassword);
    }

    public Customer runFunctionSelected(LogInSignUpOption logInSignUpOption, String username, String originalPassword)
            throws
            SQLException, IncorrectUsernamePasswordException, UsernameTakenException, IncorrectPasswordRequirementsException {
        Customer customer;
        if (logInSignUpOption.isLogIn()) {
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

