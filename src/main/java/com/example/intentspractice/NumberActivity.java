package com.example.intentspractice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class NumberActivity extends AppCompatActivity {
    EditText eT;
    Button close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number);

        eT = findViewById(R.id.editTextText);
        close = findViewById(R.id.button2);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callFirstAct(v);
            }
        });
    }

    public void callFirstAct(View View){
        Intent sendInfoBack = new Intent();

        sendInfoBack.putExtra(MainActivity.INTENT_CODE, eT.getText().toString());
        sendInfoBack.putExtra(MainActivity.OTHER_CODE, "127");
        sendInfoBack.putExtra("HELLOMOTTO", "DATADATA");

        setResult(RESULT_OK, sendInfoBack);

        finish();
    }
}
