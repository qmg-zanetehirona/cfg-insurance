package org.example;

import org.example.exceptions.IncorrectUsernamePasswordException;
import org.example.policies.PolicyDTO;
import org.example.repositories.RepositoryPolicy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.example.enums.PolicyTypeOption.BRONZE;
import static org.example.enums.PolicyTypeOption.GOLD;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class RepositoryPolicyTest {
    @Mock
    Database mockDatabase;
    @Mock
    Connection mockConnection;
    @Mock
    Statement mockStatement;
    @Mock
    ResultSet mockResultSet;

    @Test
    public void customerIdFromTable() throws SQLException, IncorrectUsernamePasswordException {
        //given
        Database database = new Database();
        RepositoryPolicy repositoryPolicy = new RepositoryPolicy(database.connectWithDB());
        // when
        int result = repositoryPolicy.getCustomerId("Alex");
        // then
        assertEquals(2, result);
    }

    @Test
    public void policiesFromCustomerOne() throws SQLException {
        //given
        Database database = new Database();
        RepositoryPolicy repositoryPolicy = new RepositoryPolicy(database.connectWithDB());
        // when
        List<String> result = repositoryPolicy.getListPolicyTypes(2);
        // then
        assertEquals(List.of("GOLD"), result);
    }

    @Test
    public void policiesFromCustomerMoreThanOne() throws SQLException {
        //given
        Database database = new Database();
        RepositoryPolicy repositoryPolicy = new RepositoryPolicy(database.connectWithDB());
        // when
        List<String> result = repositoryPolicy.getListPolicyTypes(1);
        // then
        assertEquals(List.of("BRONZE", "GOLD", "SILVER", "GOLD"), result);
    }

    @Test
    public void policiesFromCustomerZero() throws SQLException {
        //given
        Database database = new Database();
        RepositoryPolicy repositoryPolicy = new RepositoryPolicy(database.connectWithDB());
        // when
        List<String> result = repositoryPolicy.getListPolicyTypes(100);
        // then
        assertEquals(List.of(), result);
    }

    @Test
    public void getPolicyDTOList() throws Exception {

        //given
        RepositoryPolicy repositoryPolicy = new RepositoryPolicy(mockConnection);

        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(" SELECT " +
                "PolicyId,\n" +
                "PolicyType,\n" +
                "PolicyPrice,\n" +
                "PolicyPostcode,\n" +
                "User_Id FROM Policies WHERE User_Id = '1'"))
                .thenReturn(mockResultSet);

        when(mockResultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(mockResultSet.getInt("PolicyId")).thenReturn(1000).thenReturn(1001);
        when(mockResultSet.getString("PolicyType")).thenReturn(BRONZE.getValue()).thenReturn(GOLD.getValue());
        when(mockResultSet.getDouble("PolicyPrice")).thenReturn(100.0).thenReturn(300.0);
        when(mockResultSet.getString("PolicyPostcode")).thenReturn("E2 8FW").thenReturn("X26 4TT");
        when(mockResultSet.getInt("User_Id")).thenReturn(1);

        List<PolicyDTO> expected = new ArrayList<>();
        expected.add(new PolicyDTO(1000, BRONZE.getValue(), 100.0, "E2 8FW", 1));
        expected.add(new PolicyDTO(1001, GOLD.getValue(), 300.0, "X26 4TT", 1));

        //when
        List<PolicyDTO> result = repositoryPolicy.getPolicyDTO(1);

        //then
        assertThat(expected).isEqualTo(result);
    }

}
