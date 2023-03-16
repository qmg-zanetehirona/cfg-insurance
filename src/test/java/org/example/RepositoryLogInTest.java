package org.example;

import org.example.exceptions.IncorrectPasswordRequirementsException;
import org.example.repositories.RepositoryLogIn;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class RepositoryLogInTest {
      Database database = new Database();
      RepositoryLogIn repositoryLogIn = new RepositoryLogIn(database.connectWithDB());

    @Test
    public void passwordNotValidFromBegginingTest() {

        assertThrows(IncorrectPasswordRequirementsException.class, () -> repositoryLogIn.passwordValid("Migo1sfs2"));
    }

    @Test
    public void passwordValidatorCorrectTest() {
        //when
        boolean result = repositoryLogIn.passwordValidator("Migo1234!");
        // then
       assertTrue(result);
    }

    @Test
    public void passwordValidatorWithoutUppercaseTest() {
        // when
        boolean result = repositoryLogIn.passwordValidator("migo1234!");
        // then
        assertFalse(result);
    }

    @Test
    public void passwordValidatorWithoutSpecialCharacterTest() {
        // when
        boolean result = repositoryLogIn.passwordValidator("Migo1234");
        //then
        assertFalse(result);
    }

    @Test
    public void passwordValidatorWithoutNumbersTest() {
        // when
        boolean result = repositoryLogIn.passwordValidator("Migosdfagag!");
        // then
        assertFalse(result);
    }

    @Test
    public void passwordValidatorLessThan8Test() {
        // when
        boolean result = repositoryLogIn.passwordValidator("M12os!");
        // then
        assertFalse(result);
    }
}
