package com.example.notesapp.SignIn_Up;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.notesapp.R;
import com.example.notesapp.SignIn_Up.SignInActivity;
import com.example.notesapp.SignIn_Up.SignUpActivity;
import com.example.notesapp.adapter.HomePage;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            startActivity(new Intent(this, HomePage.class));

        }
    }


    public void signIn(View view) {
        startActivity(new Intent(this, SignInActivity.class));
    }

    public void singUp(View view) {
        startActivity(new Intent(this, SignUpActivity.class));
    }
}
