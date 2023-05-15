package org.example.userwindows;

import org.example.policies.PolicyDTO;
import org.example.repositories.RepositoryPolicy;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ModifyPolicyWindow extends JFrame {

    public ModifyPolicyWindow(RepositoryPolicy repositoryPolicy, InputDialog inputDialog, int selectedPolicyId) throws IOException {

        JPanel panel = new JPanel((new FlowLayout(FlowLayout.CENTER)));
        JLabel idLabel = new JLabel("Policy ID:");

        JTextField idTextField = new JTextField(6);
        idTextField.setEditable(false);

        List<PolicyDTO> policyToModify = getPolicyToModify(selectedPolicyId, repositoryPolicy);

        JList<Object> objectList = new JList<>(policyToModify.toArray());
        JScrollPane scrollPane = new JScrollPane(objectList);
        scrollPane.setPreferredSize(new Dimension(520, 20));

        JLabel manageMessageSelectPolicy = new JLabel("                ⬇    Select from the table below the policy that you want to modify:   ⬇              ");
        JLabel manageMessageChangeType = new JLabel("                            Change the policy type that you want to modify:                            ");

        JButton modifyButtonOnModifyWindow = new JButton("   Modify Policy   ");
        modifyButtonOnModifyWindow.setEnabled(false);

        JLabel modifiedPolicyTypeLabel = new JLabel("Choose new policy type:");

        JComboBox<String> policyOptionsComboBox = new JComboBox<>();
        policyOptionsComboBox.addItem("Bronze");
        policyOptionsComboBox.addItem("Silver");
        policyOptionsComboBox.addItem("Gold");
        policyOptionsComboBox.setEnabled(false);

        panel.add(manageMessageSelectPolicy);

        panel.add(scrollPane);
        panel.add(idLabel);
        panel.add(idTextField);
        panel.add(manageMessageChangeType);
        panel.add(modifiedPolicyTypeLabel);
        panel.add(policyOptionsComboBox);
        panel.add(modifyButtonOnModifyWindow);
        setContentPane(panel);

        enabledModifyButton(objectList, modifyButtonOnModifyWindow);
        getPolicyIDFromTable(idTextField, objectList);
        getPolicyTypeFromTable(objectList, policyOptionsComboBox);
        modifyAndUpdatePolicy(selectedPolicyId, idTextField, policyOptionsComboBox, modifyButtonOnModifyWindow, inputDialog, objectList, repositoryPolicy);

    }

    private void getPolicyIDFromTable(JTextField idTextField, JList<Object> objectList) {
        objectList.addListSelectionListener(e -> {
            PolicyDTO selectedPolicy = (PolicyDTO) objectList.getSelectedValue();
            if (selectedPolicy != null) {
                idTextField.setText(String.valueOf(selectedPolicy.getPolicyId()));
            }
        });
    }

    private List<PolicyDTO> refreshPolicyList(int selectedPolicyId, RepositoryPolicy repositoryPolicy) {
        try {
            return repositoryPolicy.getPolicyDTOwithPolicyId(selectedPolicyId);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void getPolicyTypeFromTable(JList<Object> objectList, JComboBox<String> policyOptionsComboBox) {
        objectList.addListSelectionListener(e -> {
            PolicyDTO selectedPolicy = (PolicyDTO) objectList.getSelectedValue();
            if (selectedPolicy != null) {
                policyOptionsComboBox.setSelectedItem(selectedPolicy.getPolicyType());
                policyOptionsComboBox.setEnabled(true);
            }
        });
    }

    private void enabledModifyButton(JList<Object> objectList, JButton modifyButton) {
        objectList.addListSelectionListener(e -> modifyButton.setEnabled(true));
    }

    private void modifyAndUpdatePolicy(int selectedPolicyId, JTextField idTextField, JComboBox<String> policyOptionsComboBox,
                                       JButton modifyButton, InputDialog inputDialog, JList<Object> objectList, RepositoryPolicy repositoryPolicy) {
        modifyButton.addActionListener(e -> {
            PolicyDTO selectedPolicy = (PolicyDTO) objectList.getSelectedValue();
            int id = Integer.parseInt(idTextField.getText());
            String oldPolicyType = selectedPolicy.getPolicyType();
            String newPolicyType = (String) policyOptionsComboBox.getSelectedItem();
            int priceAdjustment = calculatePriceAdjustment(oldPolicyType, newPolicyType);
            double oldPolicyPrice = selectedPolicy.getPolicyPrice();
            inputDialog.messageForUserMD("Your new policy price would be £" + (oldPolicyPrice + priceAdjustment)+"\n" + refund(priceAdjustment));
            if (inputDialog.areYouSureYesNo().isYes()) {
                inputDialog.messageForUserMD("Policy have been modified.");
                modifyPolicy(id, newPolicyType, repositoryPolicy, priceAdjustment);
                List<PolicyDTO> updatedList = refreshPolicyList(selectedPolicyId, repositoryPolicy);
                objectList.setListData(updatedList.toArray());
                idTextField.setText("");
                modifyButton.setEnabled(false);
                policyOptionsComboBox.setEnabled(false);
            }
        });
    }

    private String refund(int priceAdjustment) {
        return priceAdjustment>=0?"and an amount of £"+ Math.abs(priceAdjustment) + " will be charged.":"and an amount of £"+ Math.abs(priceAdjustment) +" will be refunded.";
    }

    private void modifyPolicy(int id, String newPolicyType, RepositoryPolicy repositoryPolicy, int priceAdjustment) {
        try {
            repositoryPolicy.modifyPolicyDB(id, newPolicyType, priceAdjustment);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private List<PolicyDTO> getPolicyToModify(int selectedPolicyId, RepositoryPolicy repositoryPolicy) {
        try {
            return repositoryPolicy.getPolicyDTOwithPolicyId(selectedPolicyId);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private int calculatePriceAdjustment(String oldPolicyType, String newPolicyType) {

        if (oldPolicyType.equalsIgnoreCase(newPolicyType)) {
            return 0;
        } else if (oldPolicyType.equalsIgnoreCase("BRONZE") && newPolicyType.equalsIgnoreCase("SILVER")
                || oldPolicyType.equalsIgnoreCase("SILVER") && newPolicyType.equalsIgnoreCase("GOLD")) {
            return 110;
        } else if (oldPolicyType.equalsIgnoreCase("GOLD") && newPolicyType.equalsIgnoreCase("SILVER")
                || oldPolicyType.equalsIgnoreCase("SILVER") && newPolicyType.equalsIgnoreCase("BRONZE")) {
            return -90;
        } else if (oldPolicyType.equalsIgnoreCase("GOLD") && newPolicyType.equalsIgnoreCase("BRONZE")) {
            return -190;
        } else {
            return 210;
        }
    }

}


