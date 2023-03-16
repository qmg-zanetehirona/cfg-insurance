package org.example.exceptions;

import javax.swing.*;

public class IncorrectPostcodeException extends Exception {
    public IncorrectPostcodeException(String msm) {
        super("POSTCODE INVALID.\n" +
                "Please make sure the POSTCODE is valid :\n" +
                "*  Out-code and In-code separated by a single space\n"+
                "*  For example: PO1 3AX (e2 8fw) (Ox26 4tT)\n" +
                "*  PO refers to the postcode area of Portsmouth\n" +
                "*  PO1 refers to a postcode district within the postcode area of Portsmouth\n" +
                "*  PO1 3 refers to the postcode sector.\n" +
                "*  PO1 3AX. The AX completes the postcode.");
//        JOptionPane.showMessageDialog(null,
//        "POSTCODE INVALID.\n" +
//                "Please make sure the POSTCODE is valid :\n" +
//                "*  Out-code and In-code separated by a single space\n"+
//                "*  For example: PO1 3AX (e2 8fw) (Ox26 4tT)\n" +
//                "*  PO refers to the postcode area of Portsmouth\n" +
//                "*  PO1 refers to a postcode district within the postcode area of Portsmouth\n" +
//                "*  PO1 3 refers to the postcode sector.\n" +
//                "*  PO1 3AX. The AX completes the postcode.","Error",JOptionPane.ERROR_MESSAGE);
    }
}
