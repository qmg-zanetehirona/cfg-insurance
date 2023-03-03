package org.example;

import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RepositoryTest {

    @Test
    public void passwordValidFromBegginingTest() throws SQLException, IncorrectUsernamePasswordException, UsernameTakenException, IncorrectPasswordRequirementsException {
        //given
        Repository repository = new Repository();
        // when
        String result = repository.passwordValid("Migo1234!");
        // then
        assertEquals("Migo1234!", result);
    }
    @Test
    public void passwordNotValidFromBegginingTest() throws SQLException, IncorrectUsernamePasswordException, UsernameTakenException, IncorrectPasswordRequirementsException {
        //given
        Repository repository = new Repository();
        // then
        assertThrows(IncorrectPasswordRequirementsException.class,() -> repository.passwordValid("Migo1sfs2"));
    }
    @Test
    public void passwordValidatorCorrectTest() throws SQLException, IncorrectUsernamePasswordException, UsernameTakenException
    {
        //given
        Repository repository = new Repository();
        // when
        boolean result = repository.passwordValidator("Migo1234!");
        // then
        assertTrue(result);
    }
    @Test
    public void passwordValidatorWithoutUppercaseTest() throws SQLException, IncorrectUsernamePasswordException, UsernameTakenException
    {
        //given
        Repository repository = new Repository();
        // when
        boolean result = repository.passwordValidator("migo1234!");
        // then
        assertFalse(result);
    }
    @Test
    public void passwordValidatorWithoutSpecialCharacterTest() throws SQLException, IncorrectUsernamePasswordException, UsernameTakenException
    {
        //given
        Repository repository = new Repository();
        // when
        boolean result = repository.passwordValidator("Migo1234");
        // then
        assertFalse(result);
    }
    @Test
    public void passwordValidatorWithoutNumbersTest() throws SQLException, IncorrectUsernamePasswordException, UsernameTakenException
    {
        //given
        Repository repository = new Repository();
        // when
        boolean result = repository.passwordValidator("Migosdfagag!");
        // then
        assertFalse(result);
    }
    @Test
    public void passwordValidatorLessThan8Test() throws SQLException, IncorrectUsernamePasswordException, UsernameTakenException
    {
        //given
        Repository repository = new Repository();
        // when
        boolean result = repository.passwordValidator("M12os!");
        // then
        assertFalse(result);
    }

    @Test
    public void customerIdFromTable() throws SQLException, IncorrectUsernamePasswordException, UsernameTakenException
    {
        //given
        Repository repository = new Repository();
        // when
        int result = repository.getCustomerId("Alex");
        // then
        assertEquals(2, result);
    }

    @Test
    public void policiesFromCustomerOne() throws SQLException, IncorrectUsernamePasswordException, UsernameTakenException
    {
        //given
        Repository repository = new Repository();
        // when
        List<String> result = repository.getListPolicyTypes(2);
        // then
        assertEquals(List.of("GOLD"), result);
    }

    @Test
    public void policiesFromCustomerMoreThanOne() throws SQLException, IncorrectUsernamePasswordException, UsernameTakenException
    {
        //given
        Repository repository = new Repository();
        // when
        List<String> result = repository.getListPolicyTypes(1);
        // then
        assertEquals(List.of("BRONZE","GOLD","SILVER","GOLD"), result);
    }
    @Test
    public void policiesFromCustomerZero() throws SQLException, IncorrectUsernamePasswordException, UsernameTakenException
    {
        //given
        Repository repository = new Repository();
        // when
        List<String> result = repository.getListPolicyTypes(100);
        // then
        assertEquals(List.of(), result);
    }

}
