package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.sql.SQLException;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LoginServiceTest {
    @Mock
    Repository repository;
    @Test
    public void FunctionSelectedLogInDetailsCorrectTest() throws SQLException, IncorrectUsernamePasswordException, UsernameTakenException, IncorrectPasswordRequirementsException {
        //given
        LoginService loginService = new LoginService(new Repository());
        // when
        Customer result = loginService.runFunctionSelected("1", "Migo", "Migo1234!");
        // then
        assertInstanceOf(Customer.class, result);
    }
    @Test
    public void FunctionSelectedLogInPasswordIncorrectTest() throws SQLException, IncorrectUsernamePasswordException, UsernameTakenException {
        //given
        LoginService loginService = new LoginService(new Repository());
        //then
        assertThrows(IncorrectUsernamePasswordException.class, () -> loginService.runFunctionSelected("1", "Migo", "Migo1234"));
    }
    @Test
    public void FunctionselectedSingUpCorrectTest() throws SQLException, IncorrectUsernamePasswordException, UsernameTakenException, IncorrectPasswordRequirementsException {
        //given
        LoginService loginService = new LoginService(repository);
        Customer aki = new Customer("Aki");
        when(repository.createCustomer("Aki", "Aki1234!")).thenReturn(aki);
        // when
        Customer result = loginService.runFunctionSelected("2", "Aki", "Aki1234!");
        // then
        assertThat(result).isEqualTo(aki);
        verify(repository).createCustomer("Aki", "Aki1234!"); //run once
    }
    @Test
    public void FunctionSelectedSingUpUserNameTakenTest() throws SQLException, IncorrectUsernamePasswordException, UsernameTakenException {
        LoginService loginService = new LoginService(new Repository());

        assertThrows(UsernameTakenException.class, () -> loginService.runFunctionSelected("2", "Migo", "Migo1234!"));
    }
    @Test
    public void FunctionSelectedSingUpPasswordIncorrectTest() throws SQLException, IncorrectUsernamePasswordException, UsernameTakenException {
        LoginService loginService = new LoginService(new Repository());

        assertThrows(UsernameTakenException.class, () -> loginService.runFunctionSelected("2", "Pedro", "Pedro1234"));
    }
}
