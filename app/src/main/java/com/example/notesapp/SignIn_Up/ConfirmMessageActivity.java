package com.example.notesapp.SignIn_Up;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.notesapp.R;

public class ConfirmMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_message);


    }

    public void goEmail(View view) {

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }
}
