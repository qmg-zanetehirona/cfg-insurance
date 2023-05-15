package org.example.services;

import org.example.userwindows.InputDialog;
import org.example.policies.BronzePolicy;
import org.example.policies.Policy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.enums.BedroomsQuantityOption.*;
import static org.example.enums.PolicyTypeOption.*;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PolicyCreatorTest {

    @Mock
    QuestionService questionService;
    @Mock
    PriceService priceService;
    @Mock
    InputDialog inputDialog;

    @Test
    public void createTest() throws Exception {

        PolicyCreator policyCreator = new PolicyCreator(inputDialog, priceService, questionService);
        //given
        Policy policyTest = new BronzePolicy(100.0, "E2 8FW", "04-10-2023");
        Map<String, String> questionAndAnswers = new HashMap<>();
        questionAndAnswers.put("no_bedrooms", ONE_BEDROOM.getQuantity());
        questionAndAnswers.put("policy_type", BRONZE.getValue());
        questionAndAnswers.put("postcode", "E2 8FW");
        questionAndAnswers.put("policyStartDate", "04-10-2023");

        when(questionService.askQuestions()).thenReturn(questionAndAnswers);
        when(priceService.calculatePolicyPrice(questionAndAnswers)).thenReturn(100.0);

        //when
        Policy result = policyCreator.create();

        //then
        assertInstanceOf(Policy.class, result);
        assertThat(result).isEqualTo(policyTest);
    }
}