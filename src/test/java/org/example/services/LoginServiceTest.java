package org.example.services;

import org.example.Customer;
import org.example.Database;
import org.example.InputDialog;
import org.example.OutputDialog;
import org.example.exceptions.IncorrectPasswordRequirementsException;
import org.example.exceptions.IncorrectUsernamePasswordException;
import org.example.exceptions.UsernameTakenException;
import org.example.repositories.RepositoryLogIn;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LoginServiceTest {

    @Mock
    RepositoryLogIn repositoryLogIn;

    Database database = new Database();

    @Mock
    InputDialog inputDialog;

    @Mock
    OutputDialog outputDialog;

    @Test
    public void loginServiceCreateOption2Test() throws Exception {
        //given
        LoginService loginService = new LoginService(repositoryLogIn, inputDialog, outputDialog);
        Customer maxi = new Customer(26, "Maxi");
        when(inputDialog.inputChooseLoginSignUpExitOD()).thenReturn("2");
        when(inputDialog.inputUsernameIM()).thenReturn("Maxi");
        when(inputDialog.inputOriginalPasswordIM()).thenReturn("Maxi1234!");
        when(repositoryLogIn.createCustomer("Maxi", "Maxi1234!")).thenReturn(maxi);
        //when
        Customer result = loginService.login();
        //then
        assertInstanceOf(Customer.class, result);
        assertThat(result).isEqualTo(maxi);
    }

    @Test
    public void loginServiceViewOption1Test() throws Exception {
        //given
        LoginService loginService = new LoginService(repositoryLogIn, inputDialog, outputDialog);
        Customer maxi = new Customer(26, "Maxi");
        when(inputDialog.inputChooseLoginSignUpExitOD()).thenReturn("1");
        when(inputDialog.inputUsernameIM()).thenReturn("Maxi");
        when(inputDialog.inputOriginalPasswordIM()).thenReturn("Maxi1234!");
        when(repositoryLogIn.logInExistingCustomer("Maxi", "Maxi1234!")).thenReturn(maxi);
        //when
        Customer result = loginService.login();
        //then
        assertInstanceOf(Customer.class, result);
        assertThat(result).isEqualTo(maxi);
    }

    @Test
    public void FunctionselectedSingUpCorrectTest() throws SQLException, IncorrectUsernamePasswordException, UsernameTakenException, IncorrectPasswordRequirementsException {
        //given
        LoginService loginService = new LoginService(repositoryLogIn, inputDialog, outputDialog);
        Customer aki = new Customer(26, "Aki");
        when(repositoryLogIn.createCustomer("Aki", "Aki1234!")).thenReturn(aki);
        //when
        Customer result = loginService.runFunctionSelected("2", "Aki", "Aki1234!");
        // then
        assertThat(result).isEqualTo(aki);
        verify(repositoryLogIn).createCustomer("Aki", "Aki1234!"); //run once
    }

    @Test
    public void FunctionselectedLogInCorrectTest() throws SQLException, IncorrectUsernamePasswordException, UsernameTakenException, IncorrectPasswordRequirementsException {
        //given
        LoginService loginService = new LoginService(repositoryLogIn, inputDialog, outputDialog);
        Customer aki = new Customer(26, "Aki");
        when(repositoryLogIn.logInExistingCustomer("Aki", "Aki1234!")).thenReturn(aki);
        //when
        Customer result = loginService.runFunctionSelected("1", "Aki", "Aki1234!");
        // then
        assertThat(result).isEqualTo(aki);
        verify(repositoryLogIn).logInExistingCustomer("Aki", "Aki1234!"); //run once
    }

    @Test
    public void FunctionSelectedSingUpUserNameTakenTest() {
        //given
        LoginService loginService = new LoginService(new RepositoryLogIn(database.connectWithDB()), inputDialog, outputDialog);
        //then
        assertThrows(UsernameTakenException.class, () -> loginService.runFunctionSelected("2", "Migo", "Migo1234!"));
    }

    @Test
    public void FunctionSelectedSingUpPasswordRequerimentsIncorrectTest() {

        LoginService loginService = new LoginService(new RepositoryLogIn(database.connectWithDB()), inputDialog,outputDialog);

        assertThrows(IncorrectPasswordRequirementsException.class, () -> loginService.runFunctionSelected("2", "Pedro", "Pedro1234"));
    }

    @Test
    public void FunctionSelectedLogInPasswordIncorrectTest() {
        //given
        LoginService loginService = new LoginService(new RepositoryLogIn(database.connectWithDB()), inputDialog,outputDialog);
        //then
        assertThrows(IncorrectUsernamePasswordException.class, () -> loginService.runFunctionSelected("1", "Ola", "Ola1234"));
    }


}