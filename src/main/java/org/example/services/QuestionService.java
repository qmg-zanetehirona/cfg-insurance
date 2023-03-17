package org.example.services;

import org.example.Enum.YesNoOption;
import org.example.InputDialog;
import org.example.OutputDialog;
import org.example.question.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestionService {

    InputDialog inputDialog;
    OutputDialog outputDialog;

    public QuestionService(InputDialog inputDialog, OutputDialog outputDialog) {
        this.inputDialog = inputDialog;
        this.outputDialog = outputDialog;
    }

    List<Question> questions = List.of(new No_BedroomsQuestion(), new PolicyTypeQuestion(), new PostcodeQuestion(), new PolicyStartDate());

    public Map<String, String> askQuestions() throws Exception {
        return collectAnswerSelectQuestions();
    }

    private Map<String, String> collectAnswerSelectQuestions() throws Exception {
        Map<String, String> answers = new HashMap<>();
        for (int i = 0; i < questions.size(); ) {
            String ans = inputDialog.inputQuestions(questions.get(i));
            try {
                questions.get(i).validate(ans.toUpperCase());
            } catch (Exception e) {
                outputDialog.outputErrorMessage(e);
                YesNoOption response = inputDialog.inputWouldYouLikeToTryAgainCD(e);
                if (response != null && response.isYes()) {
                    continue;
                } else {
                    throw e;
                }
            }
            answers.put(questions.get(i).id(), ans.toUpperCase());
            i++;
        }
        return answers;
    }
}