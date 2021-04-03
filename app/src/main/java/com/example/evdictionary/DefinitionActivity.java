package com.example.evdictionary;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class DefinitionActivity extends AppCompatActivity {
    TextView textViewDefinition;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_definition);

        textViewDefinition = findViewById(R.id.text_definition);
        textViewDefinition.setMovementMethod(new ScrollingMovementMethod());

        Word word = (Word) getIntent().getSerializableExtra("word");

        textViewDefinition.setText(Html.fromHtml(styleHtml(word.getHtml()), Html.FROM_HTML_MODE_LEGACY));
    }

    String styleHtml(String html) {
        //TODO: Re-implement this function
        StringBuilder temp = new StringBuilder(html);

        // This is magic :)
        for (int i = 2; i < temp.length() - 2; ++i) {
            if (temp.charAt(i) == '1' && temp.charAt(i - 1) == 'h' && temp.charAt(i - 2) != '/') {
                temp.insert(i + 2, "<font color='#43a047'>");
            }  else if (temp.charAt(i) == '2' && temp.charAt(i - 1) == 'h' && temp.charAt(i - 2) != '/') {
                temp.insert(i + 2, "<font color='#1565c0'>");
            } else if (temp.charAt(i) == '3' && temp.charAt(i - 1) == 'h' && temp.charAt(i - 2) != '/') {
                temp.insert(i + 2, "<font color='red'>");
            } else if (temp.charAt(i) == '/' && temp.charAt(i + 1) == 'h' && (temp.charAt(i + 2) == '1' || temp.charAt(i + 2) == '2' || temp.charAt(i + 2) == '3')) {
                temp.insert(i - 1, "</font>");
                i += 7; // 7 is length of </font>
            }
        }

        return temp.toString();
    }
}