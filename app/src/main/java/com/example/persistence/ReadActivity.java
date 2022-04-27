package com.example.persistence;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class ReadActivity extends AppCompatActivity {

    private TextView textView;
    private String dataText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        textView = findViewById(R.id.textView);

        Bundle extras = getIntent().getExtras();

        ArrayList<String> candyStrings = extras.getStringArrayList("data");

        if (extras == null || candyStrings.size() == 0) {
            textView.setText("No content to show");
            return;
        };

        textView.setText("");

        for (String s : extras.getStringArrayList("data")) {
            textView.setText(String.format("%s%s \n", textView.getText(), s));
        }
    }

    public void finishActivity(View view) {
        this.finish();
    }
}