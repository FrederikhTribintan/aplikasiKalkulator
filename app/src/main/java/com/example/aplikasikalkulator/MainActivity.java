package com.example.aplikasikalkulator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private String firstNum = "";
    private String secondNum = "";
    private String operator = "";
    EditText editText;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_kalkulator);

        editText = findViewById(R.id.fbTempatAngka);
        textView = findViewById(R.id.fbHasil);

        // Set click listeners for number buttons
        setNumberButtonClickListeners();

        // Set click listeners for operator buttons
        setOperatorButtonClickListeners();

        // Set click listener for the equals button
        Button equalsButton = findViewById(R.id.fbHasil);
        equalsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate();
            }
        });

        // Set click listener for clear button
        Button clearButton = findViewById(R.id.fbTombolHapus);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
            }
        });
    }

    private void setNumberButtonClickListeners() {
        int[] numberButtonIds = {R.id.fbAngka0, R.id.fbAngka1, R.id.fbAngka2, R.id.fbAngka3,
                R.id.fbAngka4, R.id.fbAngka5, R.id.fbAngka6, R.id.fbAngka7, R.id.fbAngka8, R.id.fbAngka9};

        for (int id : numberButtonIds) {
            Button button = findViewById(id);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editText.append(((Button) v).getText());
                }
            });
        }
    }

    private void setOperatorButtonClickListeners() {
        int[] operatorButtonIds = {R.id.fbTambah, R.id.fbKurang, R.id.fbPerkalian, R.id.fbPembagian};

        for (int id : operatorButtonIds) {
            Button button = findViewById(id);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editText.append(" " + ((Button) v).getText() + " ");
                }
            });
        }
    }

    private void calculate() {
        String expression = editText.getText().toString();
        // Check if expression is not empty
        if (!expression.isEmpty()) {
            try {
                // Evaluate the expression
                double result = evaluateExpression(expression);
                // Display the result
                editText.setText(String.valueOf(result));
            } catch (Exception e) {
                // Handle any errors during evaluation
                editText.setText("Error");
            }
        }
    }

    // Method to evaluate the expression
    private double evaluateExpression(String expression) {
        // Split the expression by space to separate operands and operators
        String[] elements = expression.split(" ");
        // Initialize result to the first operand
        double result = Double.parseDouble(elements[0]);
        // Loop through the elements starting from index 1
        for (int i = 1; i < elements.length; i += 2) {
            // Get the operator
            String operator = elements[i];
            // Get the next operand
            double operand = Double.parseDouble(elements[i + 1]);
            // Perform the operation based on the operator
            switch (operator) {
                case "+":
                    result += operand;
                    break;
                case "-":
                    result -= operand;
                    break;
                case "*":
                    result *= operand;
                    break;
                case "/":
                    // Check for division by zero
                    if (operand != 0) {
                        result /= operand;
                    } else {
                        throw new ArithmeticException("Division by zero");
                    }
                    break;
            }
        }
        return result;
    }


    private void clear() {
        editText.setText("");
        textView.setText("");
    }
}
