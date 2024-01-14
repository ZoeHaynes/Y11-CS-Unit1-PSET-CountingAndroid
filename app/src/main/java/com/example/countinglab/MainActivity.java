package com.example.countinglab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textToChange = (TextView)findViewById(R.id.textBox);
        AssetManager assetManager = this.getApplicationContext().getAssets();

        Button btnCW = (Button) findViewById(R.id.buttonCW);
        btnCW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = (EditText) findViewById(R.id.source);
                String filename = String.valueOf(editText.getText());
                Counter counter;
                try {
                    counter = new Counter(assetManager,filename, "Common Word");
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                String toDisplay = "The most common word in the text file \"" + filename + "\" is \"" + counter.getWord() +"\" with "+ counter.getCountString() + " occurrences.";
                SpannableString ss = new SpannableString(toDisplay);
                StyleSpan boldSpan = new StyleSpan(Typeface.BOLD);
                StyleSpan boldSpan1 = new StyleSpan(Typeface.BOLD);
                StyleSpan boldSpan2 = new StyleSpan(Typeface.BOLD);
                int textColor = Color.parseColor("#758ECB");
                ss.setSpan(new ForegroundColorSpan(textColor), toDisplay.indexOf(filename), toDisplay.indexOf(filename) + filename.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                ss.setSpan(boldSpan, toDisplay.indexOf(filename), toDisplay.indexOf(filename) + filename.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                ss.setSpan(new ForegroundColorSpan(textColor), toDisplay.indexOf(counter.getWord()), toDisplay.indexOf(counter.getWord()) + counter.getWord().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                ss.setSpan(boldSpan1, toDisplay.indexOf(counter.getWord()), toDisplay.indexOf(counter.getWord()) + counter.getWord().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                ss.setSpan(new ForegroundColorSpan(textColor), toDisplay.indexOf(counter.getCountString()), toDisplay.indexOf(counter.getCountString()) + counter.getCountString().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                ss.setSpan(boldSpan2, toDisplay.indexOf(counter.getCountString()), toDisplay.indexOf(counter.getCountString()) + counter.getCountString().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                textToChange.setText(ss);

            }
        });

        Button btnT5 = findViewById(R.id.buttonT5);
        btnT5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = (EditText) findViewById(R.id.source);
                String filename = String.valueOf(editText.getText());
                Counter counter;
                try {
                    counter = new Counter(assetManager,filename, "Top 5");
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }


                SpannableString toPrint = counter.getToPrint();
                String toDisplay = "The top five most common words in the text file \"" + filename + "\" are\n\n";
                SpannableString ss = new SpannableString(toDisplay+toPrint);
                int textColor = Color.parseColor("#758ECB");
                ss.setSpan(new ForegroundColorSpan(textColor), toDisplay.indexOf(filename), toDisplay.indexOf(filename) + filename.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                textToChange.setText(ss);

            }
        });

    }


}

