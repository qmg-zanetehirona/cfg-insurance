package org.example.services;

import java.util.Map;

public class PriceService {

    QuestionService questionService;

    public PriceService(QuestionService questionService) {
        this.questionService = questionService;
    }

    public double calculatePolicyPrice(Map<String, String> questionsAndAnswer) {

        if (questionsAndAnswer.get("policy_type").equals("1")) {
            return calculatePolicyPrice(100, questionsAndAnswer);
        }
        if (questionsAndAnswer.get("policy_type").equals("2")) {
            return calculatePolicyPrice(200, questionsAndAnswer);
        }
        if (questionsAndAnswer.get("policy_type").equals("3")) {
            return calculatePolicyPrice(300, questionsAndAnswer);
        }
        throw new UnsupportedOperationException("Incorrect option for POLICY TYPE");
    }

    private double calculatePolicyPrice(double price, Map<String, String> questionsAndAnswer) {

        if (questionsAndAnswer.get("no_bedrooms").equals("1")) {
            return price;
        }
        if (questionsAndAnswer.get("no_bedrooms").equals("2")) {
            return price * 1.2;
        }
        if (questionsAndAnswer.get("no_bedrooms").equals("3")) {
            return price * 1.4;
        }
        throw new UnsupportedOperationException("Incorrect option for QUANTITY OF BEDROOMS");
    }
}
