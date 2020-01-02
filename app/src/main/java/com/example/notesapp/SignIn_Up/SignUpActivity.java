package com.example.notesapp.SignIn_Up;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.example.notesapp.adapter.HomePage;
import com.example.notesapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class SignUpActivity extends AppCompatActivity {
    private TextInputLayout Eemail, Epass;
    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        mAuth = FirebaseAuth.getInstance();

        Eemail = findViewById(R.id.editU_email);
        Epass = findViewById(R.id.editU_pass);


    }

    public void btn_signUp(View view) {
        String email = Eemail.getEditText().getText().toString().trim();
        String pass = Epass.getEditText().getText().toString().trim();

        if (checkEmailPassword(email, pass)) {

            progressDialog = new ProgressDialog(SignUpActivity.this, R.style.AppTheme_Dark_Dialog);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Authenticating...");
            progressDialog.show();

            doSignUp(email, pass);

        }
        ;
    }


    private Boolean checkEmailPassword(String email, String pss) {

        if (!(email.contains("gmail.com"))) {
            if (!(email.contains("hotmail.com"))) {
                if (!(email.contains("outlook.com"))) {
                    Eemail.setError("Error email not voiled");
                    return false;
                }
            }

        } else if (!(pss.length() > 6)) {
            Epass.setError("Error should more 6");
            return false;

        } else {
            return true;
        }
        return false;
    }

    private void doSignUp(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Intent intent = new Intent(SignUpActivity.this, HomePage.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        } else {
                            Toast.makeText(SignUpActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    public void closeSignUp(View view) {
        Intent intent = new Intent(getApplicationContext(), SplashActivity.class);
        startActivity(intent);
    }

    public void SignInActivity(View view) {
        Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
        startActivity(intent);
    }


}