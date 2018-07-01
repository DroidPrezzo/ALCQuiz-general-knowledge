package com.example.android.alcquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.alcquiz.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnNext, btnStartQuiz;
    private RadioGroup radioGroup;
    private RadioButton radioButtonOptionA, radioButtonOptionB, radioButtonOptionC, radioButtonOptionD;
    private TextView tvQuestion, tvResult;
    private Question mQuestion;
     private List<Question> mQuestioList;
    private View mView;
    private int score;
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //binding the layout components to the view in the activity
        btnNext = (Button) findViewById(R.id.btnNext);
        btnStartQuiz = (Button) findViewById(R.id.btnStartQuiz);
        radioGroup = (RadioGroup) findViewById(R.id.answerRadioGroup);
        radioButtonOptionA = (RadioButton) findViewById(R.id.radioButtonOptionA);
        radioButtonOptionB = (RadioButton) findViewById(R.id.radioButtonOptionB);
        radioButtonOptionC = (RadioButton) findViewById(R.id.radioButtonOptionC);
        radioButtonOptionD = (RadioButton) findViewById(R.id.radioButtonOptionD);
        tvQuestion = (TextView) findViewById(R.id.tvQuestion);
        tvResult = (TextView) findViewById(R.id.tvResult);
        mView = (LinearLayout) findViewById(R.id.questionView);

        mQuestioList = new ArrayList<>();

        Question mQuestion = new Question("The current executive secretary to Nigeria is ? ",
                "President Muhammadu Buhari",
                "Chief Pius Anyim",
                "Boss Mustapha",
                "Hajia Habibat Lawal",
                "Hajia Habibat Lawal");

        Question mQuestion2 = new Question("Who won 2018 CAF, African Nation Championship ?",
                "Algeria",
                "Morocco",
                "Nigeria",
                "Libya",
                "Morocco");

        Question mQuestion3 = new Question("The largest ocean in the world is: ",
                "Indian Ocean",
                "Atlantic Ocean",
                "Pacific Ocean",
                "Passive Ocean",
                "Pacific Ocean");

        Question mQuestion4 = new Question("An instrument used to measure the wind speed is: ",
                "Anemometer",
                "Wind Vane",
                "Thermometer",
                "Barometer",
                "Anemometer");

        Question mQuestion5 = new Question("Central Bank of Nigeria introduced Polymer currency in what year?",
                "2004",
                "2000",
                "2009",
                "1994",
                "2009");
        Question mQuestion6 = new Question("what is the name of the CEO of facebook",
                "Mark Zuckerberg",
                "Jeff Bezos",
                "Steve Jobs",
                "Daniel Memec",
                "Mark Zuckerberg");

        Question mQuestion7 = new Question("The first man to land in space is",
                "Yuri gangari",
                "Nixon Jeff",
                "Neil Armstrong",
                "Denver Miles",
                "Neil Armstrong");
        Question mQuestion8 = new Question("how many grams make one kilogram",
                "100",
                "1000",
                "10000",
                "10",
                "1000");
        mQuestioList.add(mQuestion);
        mQuestioList.add(mQuestion2);
        mQuestioList.add(mQuestion3);
        mQuestioList.add(mQuestion4);
        mQuestioList.add(mQuestion5);
        mQuestioList.add(mQuestion6);
        mQuestioList.add(mQuestion7);
        mQuestioList.add(mQuestion8);

        //populateQuestionView(mQuestioList.get(0));

        btnNext.setOnClickListener(this);
        btnStartQuiz.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btnStartQuiz) {
            startQuiz();
        } else {
            //radioGroup.clearCheck();
            if (count < mQuestioList.size() - 1) {
                validateCorrectScore(mQuestioList.get(count));
                count++;
                populateQuestionView(mQuestioList.get(count));
            } else {
                validateCorrectScore(mQuestioList.get(count));
                tvQuestion.setVisibility(View.INVISIBLE);
                radioGroup.setVisibility(View.INVISIBLE);
                btnNext.setVisibility(View.INVISIBLE);
                btnStartQuiz.setVisibility(View.VISIBLE);
                tvResult.setVisibility(View.VISIBLE);
                tvResult.setText("You got " + score + " out of " + mQuestioList.size() + " questions");
            }
        }

    }

    private void startQuiz() {
        radioGroup.clearCheck();
        count = 0;
        score = 0;
        btnStartQuiz.setVisibility(View.GONE);
        tvResult.setVisibility(View.GONE);
        mView.setVisibility(View.VISIBLE);
        tvQuestion.setVisibility(View.VISIBLE);
        radioGroup.setVisibility(View.VISIBLE);        Question question = mQuestioList.get(count);
        populateQuestionView(question);
        btnNext.setVisibility(View.VISIBLE);
    }

    private void populateQuestionView(Question question) {
        //radioGroup.clearCheck();
        tvQuestion.setText(question.getQuestion());
        radioButtonOptionA.setText(question.getOptionA());
        radioButtonOptionB.setText(question.getOptionB());
        radioButtonOptionC.setText(question.getOptionC());
        radioButtonOptionD.setText(question.getOptionD());
    }

    private void incrementScore() {
        score++;
    }

    private void validateCorrectScore(Question question) {
        String selectedAnswer = "";
        int id = radioGroup.getCheckedRadioButtonId();
        radioGroup.clearCheck();
        switch (id) {
            case R.id.radioButtonOptionA:
                selectedAnswer = radioButtonOptionA.getText().toString();
                break;
            case R.id.radioButtonOptionB:
                selectedAnswer = radioButtonOptionB.getText().toString();
                break;
            case R.id.radioButtonOptionC:
                selectedAnswer = radioButtonOptionC.getText().toString();
                break;
            case R.id.radioButtonOptionD:
                selectedAnswer = radioButtonOptionD.getText().toString();
                break;
        }
        if (!selectedAnswer.equalsIgnoreCase("")) {
            if (selectedAnswer == question.getCorrectAnswer()) {
                incrementScore();
            }
        } else {
            Toast.makeText(this, "Please check an option", Toast.LENGTH_SHORT).show();
        }
    }
}
