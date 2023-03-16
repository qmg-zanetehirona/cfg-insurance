package org.example.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class PriceServiceTest {

    @Mock
    QuestionService questionService;

    @Test
    void calculatePolicyPriceGoldBasicTest() {

        PriceService priceService = new PriceService(questionService);
        Map<String, String> questionAndAnswers = new HashMap<>();
        questionAndAnswers.put("no_bedrooms", "1");
        questionAndAnswers.put("policy_type", "3");
        questionAndAnswers.put("postcode", "E2 8FW");
        //when
        double result = priceService.calculatePolicyPrice(questionAndAnswers);
        //then
        assertEquals(result, 300.0); //110,200,300
    }

    @Test
    void calculatePolicyPriceBronzeBigTest() {

        PriceService priceService = new PriceService(questionService);
        Map<String, String> questionAndAnswers = new HashMap<>();
        questionAndAnswers.put("no_bedrooms", "3");
        questionAndAnswers.put("policy_type", "1");
        questionAndAnswers.put("postcode", "E2 8FW");
        //when
        double result = priceService.calculatePolicyPrice(questionAndAnswers);
        //then
        assertEquals(result, 140.0); //140,280,420
    }

    @Test
    void calculatePolicyPriceSilverMedTest() {

        PriceService priceService = new PriceService(questionService);
        Map<String, String> questionAndAnswers = new HashMap<>();
        questionAndAnswers.put("no_bedrooms", "2");
        questionAndAnswers.put("policy_type", "2");
        questionAndAnswers.put("postcode", "E2 8FW");
        //when
        double result = priceService.calculatePolicyPrice(questionAndAnswers);
        //then
        assertEquals(result, 240.0); //120,240,360
    }

    @Test
    void calculatePolicyPriceThrowsException() {
        //given
        PriceService priceService = new PriceService(questionService);
        Map<String, String> questionAndAnswers = new HashMap<>();
        questionAndAnswers.put("no_bedrooms", "5");
        questionAndAnswers.put("policy_type", "2");
        questionAndAnswers.put("postcode", "E2 8FW");
        //then
        assertThrows(UnsupportedOperationException.class, () -> priceService.calculatePolicyPrice(questionAndAnswers));
    }
}
