package org.example;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class QuestionnaireTest {

    @Test
    public void validateRegexDOBTest() {
        //given
        Questionnaire questionnaire = new Questionnaire();
        // when
        boolean result = questionnaire.validateRegexDOB("1-12-2050");
        // then
        assertTrue(result);
    }

    @Test
    public void validatelogicalDOBTest() {
        //given
        Questionnaire questionnaire = new Questionnaire();
        // when
        boolean result = questionnaire.validatelogicalDOB("01-12-2022");
        // then
        assertTrue(result);
    }

    @Test
    public void validateRegexEmail() {
        //given
        Questionnaire questionnaire = new Questionnaire();
        // when
        boolean result = questionnaire.validateRegexEmail("jeanette.giron1@gmail.com");
        // then
        assertTrue(result);
    }

    @Test
    public void validateRegexStartDate() {
        //given
        Questionnaire questionnaire = new Questionnaire();
        // when
        boolean result = questionnaire.validateRegexStartCover("31-12-2023");
        // then
        assertTrue(result);
    }

    @Test
    public void validateStartDateValid() {
        //given
        LocalDate localDate = LocalDate.now();
        String date = localDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        Questionnaire questionnaire = new Questionnaire();
        // when
        boolean result = questionnaire.validateStartDateCoverValid(date);
        // then
        assertTrue(result);
    }

}
