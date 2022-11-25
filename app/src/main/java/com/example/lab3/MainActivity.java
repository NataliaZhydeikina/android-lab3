package com.example.lab3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    TextView result;
    Button buttonDivide, buttonMultiply, buttonPlus, buttonMinus, buttonEquals;
    Button button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;
    Button buttonAC, buttonDot, buttonDelOneChar, buttonPercentage, buttonParentheses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.EditText_text);
        result = findViewById(R.id.TextView_result);

        assignId(buttonDivide, R.id.button_divide);
        assignId(buttonMultiply, R.id.button_multiply);
        assignId(buttonPlus, R.id.button_plus);
        assignId(buttonMinus, R.id.button_minus);
        assignId(buttonEquals, R.id.button_equal);

        assignId(button0, R.id.button_zero);
        assignId(button1, R.id.button_one);
        assignId(button2, R.id.button_two);
        assignId(button3, R.id.button_three);
        assignId(button4, R.id.button_four);
        assignId(button5, R.id.button_five);
        assignId(button6, R.id.button_six);
        assignId(button7, R.id.button_seven);
        assignId(button8, R.id.button_eight);
        assignId(button9, R.id.button_nine);

        assignId(buttonAC, R.id.button_AC);
        assignId(buttonDot, R.id.button_coma);
        assignId(buttonDelOneChar, R.id.button_delete);
        assignId(buttonPercentage, R.id.button_percentage);
        assignId(buttonParentheses, R.id.button_parentheses);

    }
    void assignId(Button btn, int id) {
        btn = findViewById(id);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btn = (Button) v;
                String btnText = btn.getText().toString();
                String dataToCalculate = editText.getText().toString();
                if(btnText.equals("()")) {
                    String line = dataToCalculate + btnText;
                    int countFirst = (int)(line.length() - line.replace("(", "").length());
                    int countSecond = (int)(line.length() - line.replace(")", "").length());
                    if(countFirst>countSecond) {
                        btnText = ")";
                    } else {
                        btnText = "(";
                    }
                }
                if(dataToCalculate.equals("4 + 4 =")) {
                    dataToCalculate = "";
                }
                if(btnText.equals(",")) {
                    btnText = ".";
                }
                if(btnText.equals("AC")) {
                    editText.setText("");
                    result.setText("0");
                    return;
                }
                if(btnText.equals("x")) {
                    editText.setText(dataToCalculate.substring(0, dataToCalculate.length()-1));
                    return;
                }
                if(btnText.equals("=")) {
                    result.setText(getResult(editText.getText().toString()));
                    editText.setText("");
                    return;
                }

                dataToCalculate = dataToCalculate + btnText;
                editText.setText(dataToCalculate);

            }
        });
    }

    String getResult(String data) {
        try {
            Expression expression = new ExpressionBuilder(data).build();
            String finalResult = String.valueOf(expression.evaluate());
            return finalResult;
        } catch (Exception e) {
            return "Err";
        }
    }

}