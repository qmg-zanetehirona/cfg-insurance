package org.example.services;

import org.example.InputDialog;
import org.example.policies.BronzePolicy;
import org.example.policies.GoldPolicy;
import org.example.policies.Policy;
import org.example.policies.SilverPolicy;

import java.util.Map;

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
        return createPolicy(questionAndAnswers, policyPrice);
    }

    private Policy createPolicy(Map<String, String> questionAndAnswers, double policyPrice) {
        String postcode = questionAndAnswers.get("postcode");
        String startdate = questionAndAnswers.get("policyStartDate");

        if (questionAndAnswers.get("policy_type").equals("1")) {
            return new BronzePolicy(policyPrice, postcode, startdate);
        }
        if (questionAndAnswers.get("policy_type").equals("2")) {
            return new SilverPolicy(policyPrice, postcode, startdate);
        }
        if (questionAndAnswers.get("policy_type").equals("3")) {
            return new GoldPolicy(policyPrice, postcode, startdate);
        }
        throw new UnsupportedOperationException("Incorrect option for POLICY TYPE");
    }

}

