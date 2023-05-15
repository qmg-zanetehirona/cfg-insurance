package org.example.userwindows;

import org.example.policies.PolicyDTO;
import org.example.repositories.RepositoryPolicy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ManagePolicyTable extends JFrame {

    public ManagePolicyTable(List<PolicyDTO> policyDTOList, int customerId, RepositoryPolicy repositoryPolicy, InputDialog inputDialog) throws IOException {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel idLabel = new JLabel("Policy ID:");

        JTextField idTextField = new JTextField(4);
        idTextField.setEditable(false);

        JButton cancelPolicyButton = new JButton("Cancel Policy");
        cancelPolicyButton.setEnabled(false);

        JButton refreshPolicyButton = new JButton("Refresh");
        refreshPolicyButton.setEnabled(false);

        JList<Object> objectList = new JList<>(policyDTOList.toArray());
        JScrollPane scrollPane = new JScrollPane(objectList);
        scrollPane.setViewportView(objectList);


        JLabel manageMessage = new JLabel("                     ⬇  Select from the table below the policy that you want to manage:  ⬇                     ");

        JButton modifyButton = new JButton("Modify Policy");
        modifyButton.setEnabled(false);

        panel.add(manageMessage);
        panel.add(idLabel);
        panel.add(idTextField);
        panel.add(refreshPolicyButton);
        panel.add(scrollPane);
        panel.add(modifyButton);
        panel.add(cancelPolicyButton);
        setContentPane(panel);

        enabledModifyButton(objectList,modifyButton);
        enabledCancelButton(objectList,cancelPolicyButton);
        getPolicyIDFromTable(idTextField,objectList);
        cancelAndUpdatePolicy(customerId, idTextField, cancelPolicyButton, modifyButton, inputDialog, objectList, repositoryPolicy);
        modifyButtonOpenModifyWindow(objectList, modifyButton, cancelPolicyButton,refreshPolicyButton,repositoryPolicy,inputDialog);

        refreshAfterModification(customerId,repositoryPolicy,objectList,refreshPolicyButton,inputDialog,idTextField, cancelPolicyButton, modifyButton);
    }

    private void refreshAfterModification(int customerId, RepositoryPolicy repositoryPolicy,
                                          JList<Object> objectList, JButton updatePolicyButton,
                                          InputDialog inputDialog, JTextField idTextField,
                                          JButton cancelPolicyButton, JButton modifyButton) {
        updatePolicyButton.addActionListener(e -> {
            inputDialog.messageForUserMD("Updating Policy list");
            List<PolicyDTO> updatedList = refreshPolicyList(customerId, repositoryPolicy);
            objectList.setListData(updatedList.toArray());
            objectList.setEnabled(true);
            updatePolicyButton.setEnabled(false);
            idTextField.setText("");
            cancelPolicyButton.setEnabled(false);
            modifyButton.setEnabled(false);
        });
    }

    private void enabledCancelButton(JList<Object> objectList, JButton cancelPolicyButton) {
        objectList.addListSelectionListener(e -> {
                cancelPolicyButton.setEnabled(true);
        });
    }

    private void getPolicyIDFromTable(JTextField idTextField, JList<Object> objectList) {
        objectList.addListSelectionListener(e -> {
            PolicyDTO selectedPolicy = (PolicyDTO) objectList.getSelectedValue();
            if (selectedPolicy != null) {
                idTextField.setText(String.valueOf(selectedPolicy.getPolicyId()));
            }
        });
    }

    private void cancelAndUpdatePolicy(int customerId, JTextField idTextField, JButton cancelPolicyButton, JButton modifyButton, InputDialog inputDialog, JList<Object> objectList, RepositoryPolicy repositoryPolicy) {
        cancelPolicyButton.addActionListener(e -> {
            int id = Integer.parseInt(idTextField.getText());
            if (inputDialog.areYouSureYesNo().isYes()) {
                inputDialog.messageForUserMD("Policy have been cancelled.");
                deletePolicy(id, repositoryPolicy);
                List<PolicyDTO> updatedList = refreshPolicyList(customerId, repositoryPolicy);
                objectList.setListData(updatedList.toArray());
                idTextField.setText("");
                cancelPolicyButton.setEnabled(false);
                modifyButton.setEnabled(false);
            }
        });
    }

    private void deletePolicy(int id, RepositoryPolicy repositoryPolicy) {
        try {
            repositoryPolicy.deletePolicyDB(id);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<PolicyDTO> refreshPolicyList(int customerId, RepositoryPolicy repositoryPolicy) {
        try {
            return repositoryPolicy.getPolicyDTO(customerId);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void enabledModifyButton(JList<Object> objectList, JButton modifyButton) {
        objectList.addListSelectionListener(e -> {
            modifyButton.setEnabled(true);
        });
    }

    public void modifyButtonOpenModifyWindow(JList<Object> objectList, JButton modifyButton, JButton cancelPolicyButton,JButton updatePolicyButton, RepositoryPolicy repositoryPolicy, InputDialog inputDialog) {
        modifyButton.addActionListener(e -> {
            ModifyPolicyWindow modifyPolicyWindow = null;
            try {
                modifyButton.setEnabled(false);
                cancelPolicyButton.setEnabled(false);
                objectList.setEnabled(false);
                updatePolicyButton.setEnabled(true);
                int selectedPolicyId = ((PolicyDTO) objectList.getSelectedValue()).getPolicyId();
                modifyPolicyWindow = new ModifyPolicyWindow(repositoryPolicy, inputDialog, selectedPolicyId);
                JDialog dialog = new JDialog();
                dialog.setTitle("Policy to Modify");
                dialog.setModal(true);
                dialog.setContentPane(modifyPolicyWindow.getContentPane());
                JButton closeButton = new JButton(" ←   Click here to go back to 'Manage Policies' ");
                closeButton.setBounds(500,500,10,20);
                closeButton.addActionListener(e1 -> dialog.dispose());
                dialog.add(closeButton, BorderLayout.SOUTH);
                dialog.setBounds(430, 320, 570, 320);
                dialog.setVisible(true);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}


