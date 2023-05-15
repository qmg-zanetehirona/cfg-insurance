package org.example.userwindows;

import org.example.policies.PolicyDTO;
import org.example.repositories.RepositoryPolicy;
import org.example.userwindows.InputDialog;
import org.example.userwindows.ManagePolicyTable;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class OutputDialog {

    RepositoryPolicy repositoryPolicy;

    InputDialog inputDialog;

    public OutputDialog(RepositoryPolicy repositoryPolicy, InputDialog inputDialog) {
        this.repositoryPolicy = repositoryPolicy;
        this.inputDialog = inputDialog;
    }

    public void outputPoliciesAvailable(int customerId) throws SQLException, IOException {
        List<PolicyDTO> policyDTOListAvailable = repositoryPolicy.getPolicyDTO(customerId);
        if (policiesQuantity(policyDTOListAvailable)!=0) {
            ManagePolicyTable managePolicyTable = new ManagePolicyTable(policyDTOListAvailable, customerId, repositoryPolicy, inputDialog);
            JDialog dialog = new JDialog();
            dialog.setTitle("Policies Available");
            dialog.setModal(true);
            dialog.setContentPane(managePolicyTable.getContentPane());
            JButton closeButton = new JButton(" ←    Click here to go back to 'Main Menu'");
            closeButton.setBounds(500,500,10,20);
            closeButton.addActionListener(e1 -> dialog.dispose());
            dialog.add(closeButton, BorderLayout.SOUTH);
            dialog.setBounds(430, 320, 570, 320);
            dialog.setVisible(true);

        } else {
            inputDialog.messageForUserMD("There is no policies available");
        }
    }

    public void outputErrorMessage(Exception e) {
        JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
    }

    private int policiesQuantity(List<PolicyDTO> policyDTOList){
        return policyDTOList.size();
    }
}
