package com.example.notesapp.SignIn_Up;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.notesapp.adapter.HomePage;
import com.example.notesapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity {

    private TextInputLayout Eemail, Epass;
    private String email, password;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mAuth = FirebaseAuth.getInstance();

        Eemail = findViewById(R.id.editI_email);
        Epass = findViewById(R.id.editI_pass);


        findViewById(R.id.btn_signIn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = Eemail.getEditText().getText().toString().trim();
                password = Epass.getEditText().getText().toString().trim();

                if (checkEmailPassword(email, password)) {

                    progressDialog = new ProgressDialog(SignInActivity.this, R.style.AppTheme_Dark_Dialog);
                    progressDialog.setIndeterminate(true);
                    progressDialog.setMessage("Authenticating...");
                    progressDialog.show();

                    signIn(email, password);

                }
            }
        });
    }


    private Boolean checkEmailPassword(String email, String pss) {
        if (!(email.contains("gmail.com")) || !(email.contains("gmail.com")) || !(email.contains("gmail.com"))) {
            Eemail.setError("Error email not voiled");
            return false;

        } else if (!(pss.length() > 6)) {
            Epass.setError("Error should more 6");
            return false;

        } else {
            return true;
        }
    }

    private void signIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Intent intent = new Intent(SignInActivity.this, HomePage.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        } else {
                            Toast.makeText(SignInActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    public void forgotPass(View view) {
        Intent intent = new Intent(getApplicationContext(), ForgotPassActivity.class);
        startActivity(intent);
    }

    public void SignUpActivity(View view) {
        Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
        startActivity(intent);
    }

    public void closeSignIn(View view) {
        Intent intent = new Intent(getApplicationContext(), SplashActivity.class);
        startActivity(intent);
    }
}
