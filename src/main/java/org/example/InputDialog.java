package org.example;

import com.toedter.calendar.JDateChooser;
import org.example.Enum.BedroomsQuantityOptions;
import org.example.Enum.LogInSignUpOption;
import org.example.Enum.ViewCreatePolicyOptions;
import org.example.Enum.YesNoOption;
import org.example.question.Question;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InputDialog {

    private final InputStream resourceAsStream = getClass().getResourceAsStream("/Logo85.jpg");
    Icon icon = new ImageIcon(ImageIO.read(resourceAsStream));

    public InputDialog() throws IOException {
    }

    public YesNoOption inputWouldYouLikeToTryAgainCD(Exception e) {
        int choice = JOptionPane.showConfirmDialog(null,
                "Would you like to try again?",
                "Choose an option",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                icon);
        return YesNoOption.from(choice);
    }

    public LogInSignUpOption inputChooseLoginSignUpExitOD() {
            String[] options = {"Log in", "Sign up"};
            int choice = JOptionPane.showOptionDialog(null,
                    "What would you like to do:",
                    "Choose an option",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    icon,
                    options,
                    "Log in");
            return LogInSignUpOption.from(choice);
        }

    public void messageForUserMD(String message) {
        JOptionPane.showMessageDialog(null,
                message,
                null,
                JOptionPane.INFORMATION_MESSAGE,
                icon);
    }

    public String inputOriginalPasswordIM() {
        JPasswordField passwordField = new JPasswordField();
        JOptionPane.showConfirmDialog(null,
                passwordField,
                "Enter Password",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,icon);
        return new String(passwordField.getPassword());
    }

    public String inputUsernameIM() {
        JTextField text = new JTextField();
        JOptionPane.showConfirmDialog(null,
                text,
                "Enter Username",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,icon);
        return text.getText();
    }

    public ViewCreatePolicyOptions inputChooseViewCreateLogOutOD() {

        String[] options = {"View existing policy", "Create a new policy", "Log out"};
        int choice = JOptionPane.showOptionDialog(null,
                "What would you like to do:",
                "Choose an option",
                0,
                JOptionPane.QUESTION_MESSAGE,
                icon,
                options,
                "View existing policy");
        return ViewCreatePolicyOptions.from(choice);
    }

    public String inputQuestions(Question question) {
        String response="";
        if (question.options()==3){
            response = JOptionPane.showInputDialog(null,
                    question.text(),
                    "Questionnaire",
                    JOptionPane.PLAIN_MESSAGE,
                    icon,
                    new Object[]{"1","2","3"},
                    "1").toString();
        }
        if (question.options()==0) {
            response = JOptionPane.showInputDialog(null,
                    question.text(),
                    "Questionnaire",
                    JOptionPane.QUESTION_MESSAGE,
                    icon,
                    null,
                    null).toString();
        }
        if (question.options()==1){
            response = inputQuestionPolicyStartDate();
        }
        return response;
    }

    public String inputQuestionPolicyStartDate() {
        Date date = new Date();
        JDateChooser dateChooser = new JDateChooser();
        dateChooser.setMinSelectableDate(date);
        dateChooser.setDate(date);
        String message = "Choose start date:\n";
        Object[] params = {message, dateChooser};
        int confirmDialog = JOptionPane.showConfirmDialog(null, params, "Start date", JOptionPane.PLAIN_MESSAGE,JOptionPane.PLAIN_MESSAGE,icon);
        return  confirmDialog==-1?null:(new SimpleDateFormat("dd-MM-yyyy")).format(((JDateChooser) params[1]).getDate());
    }
}
