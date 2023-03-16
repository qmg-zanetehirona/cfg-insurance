package org.example.question;

import org.example.exceptions.IncorrectDateSelected;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PolicyStartDate implements Question{
    @Override
    public String id() {
        return "policyStartDate";
    }

    @Override
    public String text() {
        return "Choose start date";
    }

    @Override
    public int options() {
        return 1;
    }

    @Override
    public boolean validate(String startdate) throws IncorrectDateSelected {
        if (!validateStartDateCoverValid(startdate)) {
            throw new IncorrectDateSelected("Date selected is outdated");
        }
        return true;
    }

    public boolean validateStartDateCoverValid(String startdate) {
        LocalDate dateSupplied = LocalDate.parse(startdate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        return dateSupplied.isEqual(LocalDate.now()) || dateSupplied.isAfter(LocalDate.now());
    }
}
