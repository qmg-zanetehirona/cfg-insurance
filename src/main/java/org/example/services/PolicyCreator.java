package org.example.services;

import org.example.userwindows.InputDialog;
import org.example.policies.BronzePolicy;
import org.example.policies.GoldPolicy;
import org.example.policies.Policy;
import org.example.policies.SilverPolicy;
import org.example.userwindows.Payment;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

import static org.example.enums.PolicyTypeOption.*;

public class PolicyCreator {
    InputDialog inputDialog;
    PriceService priceService;
    QuestionService questionService;

    public PolicyCreator(InputDialog inputDialog, PriceService priceService, QuestionService questionService) {
        this.inputDialog = inputDialog;
        this.priceService = priceService;
        this.questionService = questionService;
    }

    public Policy create() throws Exception {
        Map<String, String> questionAndAnswers = questionService.askQuestions();
        double policyPrice = priceService.calculatePolicyPrice(questionAndAnswers);
        //////////////////
        paymentWindowShow();
        /////////////////
        return createPolicy(questionAndAnswers, policyPrice);
    }

    private Policy createPolicy(Map<String, String> questionAndAnswers, double policyPrice) {
        String postcode = questionAndAnswers.get("postcode");
        String startdate = questionAndAnswers.get("policyStartDate");

        if (questionAndAnswers.get("policy_type").equals(BRONZE.getValue())) {
            return new BronzePolicy(policyPrice, postcode, startdate);
        }
        if (questionAndAnswers.get("policy_type").equals(SILVER.getValue())) {
            return new SilverPolicy(policyPrice, postcode, startdate);
        }
        if (questionAndAnswers.get("policy_type").equals(GOLD.getValue())) {
            return new GoldPolicy(policyPrice, postcode, startdate);
        }
        throw new UnsupportedOperationException("Incorrect option for POLICY TYPE");
    }

    private void paymentWindowShow() {
        Payment payment = new Payment();
        JDialog dialog = new JDialog();
        dialog.setTitle("Confirm Purchase");
        dialog.setModal(true);
        dialog.setContentPane(payment.getContentPane());
        JButton payButton = new JButton(" Click to Pay ");
        payButton.setBounds(140,280,100,20);
        payButton.addActionListener(e1 -> dialog.dispose());
        dialog.add(payButton);
        dialog.setBounds(530, 300, 380, 350);
        dialog.setVisible(true);
    }
}

