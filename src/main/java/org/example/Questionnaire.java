package org.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Questionnaire {

    public static String no_bedrooms = "How many bedrooms does your house have?\n" +
            "1. ⛺️ One Bedroom\n" +
            "2. \uD83C\uDFEC Two Bedrooms\n" +
            "3. \uD83C\uDFE0 More than three Bedrooms";
    public static String policy_type = "What type of policy do you need?\n" +
            "1. 🥉 Bronze\n" +
            "2. 🥈 Silver\n" +
            "3. 🥇 Gold";
    public static String postcodeq = "Please Enter your postcode";
    public static String firstName = "First Name";
    public static String lastName = "Last name";
    public static String dob = "Date of Birth";
    public static String emailAddress = "Email address";
    public static String policyStartDate = "Policy start date";

////////// Validators of question ////////////////////
    public boolean validateRegexDOB(String dob) {
        Pattern pattern = Pattern.compile("(0?[1-9]|[12][0-9]|3[01])-(0?[1-2]|1[012])-(\\d{4})");
        return pattern.matcher(dob).matches();
    }

    public boolean validatelogicalDOB(String dob) {
        LocalDate dateSupplied = LocalDate.parse(dob, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        return (dateSupplied.isBefore(LocalDate.now()) && (dateSupplied.getYear() >= (LocalDate.now().getYear() - 100)));
    }

    public boolean validateRegexEmail(String email) {
        Pattern pattern = Pattern.compile("^.+@[^\\.].*\\.[a-z]{2,}$");
        return pattern.matcher(email).matches();
    }

    public boolean validateRegexStartCover(String startDate) {
        Pattern pattern = Pattern.compile("(0?[1-9]|[12][0-9]|3[01])-(0?[1-2]|1[012])-(2023)");
        return pattern.matcher(startDate).matches();
    }

    public boolean validateStartDateCoverValid(String startDate) {
        LocalDate dateSupplied = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        return dateSupplied.isEqual(LocalDate.now()) || dateSupplied.isAfter(LocalDate.now());
    }
}



//

//            "1. Do you own the property?",
//            "2. Have you made any recent renovations or upgrades to your property?",
//            "3. Do you want coverage for natural disasters, such as floods or earthquakes?",
//            "5. Have you had any previous insurance claims or coverage?");



//
