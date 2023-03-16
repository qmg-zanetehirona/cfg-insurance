package org.example;

import org.example.policies.PolicyDTO;

import javax.swing.*;
import java.util.List;

public class OutputPolicyTable extends JFrame {

    private JList<Object> objectList;

    public OutputPolicyTable(List<PolicyDTO> policyDTOList) {
        JPanel panel = new JPanel();
        objectList = new JList<>(policyDTOList.toArray());
        JScrollPane scrollPane = new JScrollPane(objectList);
        panel.add(scrollPane);
        setContentPane(panel);
    }
}


