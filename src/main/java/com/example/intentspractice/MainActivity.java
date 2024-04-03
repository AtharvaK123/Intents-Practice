package com.example.intentspractice;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView number;
    Button launch;

    ActivityResultLauncher activityResultLauncher;

    static final int NUMBER_CODE = 1234;
    static String INTENT_CODE = "number";
    static String OTHER_CODE = "numero";
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        number = findViewById(R.id.something);
        launch = findViewById(R.id.button);

        launch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callSecondActivity(v);
            }
        });

        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode() == RESULT_OK){
                    //copy over the data
                    Intent data = result.getData();
                    String one = data.getStringExtra(INTENT_CODE);
                    String two = data.getStringExtra(OTHER_CODE);
                    String three = data.getStringExtra("HELLOMOTTO");
                    number.setText(one+" "+two+ " "+three);

                }
            }
        });
    }
    public void callSecondActivity(View view){
        //alternatively in place of MainActivity.this, you can put getApplicationContext()
        Intent intentToLoad = new Intent(MainActivity.this, NumberActivity.class);

        // use key value pairs to save/retrieve values
        intentToLoad.putExtra("TEST", "THIS IS A TEST");
        intentToLoad.putExtra("TEST2", "TESTTTTTT");


        //OLDER WAY
        // not going to return values, because it doesn't call onActivityResult
        //startActivity(intentToLoad);

        //startActivityForResult(intentToLoad, NUMBER_CODE);

        // NEWER WAY

        activityResultLauncher.launch(intentToLoad);

    }
/*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //check if request code is same as what is passed
        if(requestCode == NUMBER_CODE && resultCode == RESULT_OK){
            //copy over the data
            String one = data.getStringExtra(INTENT_CODE);
            String two = data.getStringExtra(OTHER_CODE);
            String three = data.getStringExtra("HELLOMOTTO");
            number.setText(one+two+three);

        }

    }

 */
}