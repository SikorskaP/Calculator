package com.example.calculator;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import org.mariuszgromada.math.*;
import org.mariuszgromada.math.mxparser.Expression;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.calculator.R;


public class MainActivity extends AppCompatActivity {
    private EditText display;
    int i;
    String strToAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.input);
        display.setShowSoftInputOnFocus(false);
        display.setOnClickListener(view -> {
            if(getString(R.string.display).equals(display.getText().toString())){
                display.setText("");
            }
        });
    }
    private void updateText(String strToAdd)
    {
        String oldStr = display.getText().toString();
        int cursorPos = display.getSelectionStart();
        String leftStr = oldStr.substring(0, cursorPos);
        String rightStr = oldStr.substring(cursorPos);
        if(getString(R.string.display).equals(display.getText().toString()))
        {
            display.setText(strToAdd);
        }
        else{
            display.setText(String.format("%s%s%s", leftStr, strToAdd, rightStr));
        }
        display.setSelection(cursorPos+1);
    }
    public void zeroBTN (View view){
        updateText("0");
    }
    public void oneBTN (View view){
        updateText("1");

    }
    public void twoBTN (View view){
        updateText("2");

    }
    public void threeBTN (View view){
        updateText("3");

    }
    public void fourBTN (View view){
        updateText("4");

    }
    public void fiveBTN (View view){
        updateText("5");

    }
    public void sixBTN (View view){
        updateText("6");

    }
    public void sevenBTN (View view){
        updateText("7");

    }
    public void eightBTN (View view){
        updateText("8");

    }
    public void nineBTN (View view){
        updateText("9");

    }
    public void addBTN (View view){
        updateText("+");

    }
    public void divideBTN (View view){
        updateText("/");

    }
    public void parenthesesBTN (View view){
        int cursorPos = display.getSelectionStart();
        int openParentheses = 0, closedParentheses = 0;
        int textLength = display.getText().length();
        for(int i =0;i<cursorPos;i++){
            if(display.getText().toString().substring(i,i+1).equals("(")){
                openParentheses+=1;
            }
            if(display.getText().toString().substring(i,i+1).equals("}")){
                closedParentheses+=1;
            }
        }
        if(openParentheses==closedParentheses || display.getText().toString().substring(textLength-1,textLength).equals("("))
        {
            updateText("(");
        }
        else if(closedParentheses<closedParentheses && display.getText().toString().charAt(textLength - 1) != '(')
        {
            updateText(")");
        }
        display.setSelection(cursorPos+1);
    }
    public void exponentBTN (View view){
        updateText("^");

    }
    public void subtractBTN (View view){
        updateText("-");

    }
    public void clearBTN (View view){
        display.setText("");
    }
    public void multiplyBTN (View view){
        updateText("*");

    }
    public void plusMinusBTN (View view){
        updateText("-+");

    }
    public void pointBTN (View view){
        updateText(".");
    }
    public void equalsBTN (View view){
        String userExp = display.getText().toString();
        Expression expression = new Expression(userExp);
        String result = String.valueOf(expression.calculate());
        double value = Double.parseDouble(result);
        result = fmt(value);
        display.setText(result);
        display.setSelection(result.length());
    }
    public static String fmt(double d)
    {
        if(d == (long) d)
            return String.format("%d",(long)d);
        else
            return String.format("%s",d);
    }
    public void backspaceBTN (View view){
        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();
        if(cursorPos!=0 && textLen!=0){
            SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
            selection.replace(cursorPos-1,cursorPos,"");
            display.setText(selection);
            display.setSelection(cursorPos-1);
        }
    }
}
