package org.example.services;

import org.example.*;
import org.example.policies.Policy;
import org.example.policies.SilverPolicy;
import org.example.repositories.RepositoryPolicy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PolicyServiceTest {

    @Mock
    InputDialog inputDialog;

    @Mock
    RepositoryPolicy repositoryPolicy;

    Database database = new Database();

    @Mock
    OutputDialog outputDialog;

    @Mock
    PolicyCreator policyCreator;

    @Test
    public void searchPolicySearchTest() throws SQLException {

        //given
        PolicyService policyService = new PolicyService(inputDialog, outputDialog, new RepositoryPolicy(database.connectWithDB()), policyCreator);
        Customer ola = new Customer(6, "Ola");
        when(inputDialog.inputChooseViewCreateLogOutOD()).thenReturn("1");
        doNothing().when(outputDialog).outputPoliciesAvailable(anyInt());
        //when
        Customer result = policyService.searchPolicy(ola);
        //then
        assertInstanceOf(Customer.class, result);
        assertThat(result).isEqualTo(ola);

    }

    @Test
    public void searchPolicyCreateTest() throws Exception {

        //given
        PolicyService policyService = new PolicyService(inputDialog, outputDialog, repositoryPolicy, policyCreator);
        Customer oscar = new Customer(6, "Oscar");
        Policy policy = new SilverPolicy(200.0, "E3 8FF", "04-10-2023");
        when(inputDialog.inputChooseViewCreateLogOutOD()).thenReturn("2");
        when(policyCreator.create()).thenReturn(policy);
        //when
        Customer result = policyService.searchPolicy(oscar);
        //then
        assertInstanceOf(Customer.class, result);
        assertThat(result).isEqualTo(oscar);

    }
}
