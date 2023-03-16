package org.example;

import org.example.policies.PolicyDTO;
import org.example.repositories.RepositoryPolicy;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class OutputDialog {

    RepositoryPolicy repositoryPolicy;

    public OutputDialog(RepositoryPolicy repositoryPolicy) {
        this.repositoryPolicy = repositoryPolicy;
    }

    public void outputPoliciesAvailable(int customerId) throws SQLException {
        List<PolicyDTO> policyDTOListAvailable = repositoryPolicy.getPolicyDTO(customerId);
        OutputPolicyTable outputPolicyTable = new OutputPolicyTable(policyDTOListAvailable);
        JDialog dialog = new JDialog();
        dialog.setTitle("Policies Available");
        dialog.setModal(true);
        dialog.setContentPane(outputPolicyTable.getContentPane());
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    public void outputErrorMessage(Exception e) {
        JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
    }
}
