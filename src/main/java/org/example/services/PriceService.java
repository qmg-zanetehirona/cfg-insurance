package org.example.services;

import java.util.Map;

import static org.example.enums.BedroomsQuantityOption.*;
import static org.example.enums.PolicyTypeOption.*;

public class PriceService {

    QuestionService questionService;

    public PriceService(QuestionService questionService) {
        this.questionService = questionService;
    }

    public double calculatePolicyPrice(Map<String, String> questionsAndAnswer) {

        if (questionsAndAnswer.get("policy_type").equals(BRONZE.getValue())) {
            return calculatePolicyPrice(100, questionsAndAnswer);
        }
        if (questionsAndAnswer.get("policy_type").equals(SILVER.getValue())) {
            return calculatePolicyPrice(200, questionsAndAnswer);
        }
        if (questionsAndAnswer.get("policy_type").equals(GOLD.getValue())) {
            return calculatePolicyPrice(300, questionsAndAnswer);
        }
        throw new UnsupportedOperationException("Incorrect option for POLICY TYPE");
    }

    private double calculatePolicyPrice(double price, Map<String, String> questionsAndAnswer) {

        if (questionsAndAnswer.get("no_bedrooms").equals(ONE_BEDROOM.getQuantity())) {
            return price;
        }
        if (questionsAndAnswer.get("no_bedrooms").equals(TWO_BEDROOMS.getQuantity())) {
            return price * 1.2;
        }
        if (questionsAndAnswer.get("no_bedrooms").equals(MORE_THAN_TWO_BEDROOMS.getQuantity())) {
            return price * 1.4;
        }
        throw new UnsupportedOperationException("Incorrect option for QUANTITY OF BEDROOMS");
    }
}
