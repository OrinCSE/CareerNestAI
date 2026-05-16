package com.project.careernestr;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Views
    private Button btnSignIn;
    private Button btnSignUp;

    private ImageView logoIcon;
    private TextView brandName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // IMPORTANT:
        // Since your XML file name is activity_home.xml
        setContentView(R.layout.activity_home);

        // =========================
        // INITIALIZE VIEWS
        // =========================

        btnSignIn = findViewById(R.id.btn_goto_signin);
        btnSignUp = findViewById(R.id.btn_goto_signup);

        logoIcon = findViewById(R.id.logo_icon);
        brandName = findViewById(R.id.brand_name);

        // =========================
        // SIGN IN BUTTON
        // =========================

        btnSignIn.setOnClickListener(v -> {

            // Optional Toast
            Toast.makeText(
                    MainActivity.this,
                    "Opening Sign In",
                    Toast.LENGTH_SHORT
            ).show();

            // Open SignInActivity
            Intent intent = new Intent(
                    MainActivity.this,
                    SignInActivity.class
            );

            startActivity(intent);
        });

        // =========================
        // SIGN UP BUTTON
        // =========================

        btnSignUp.setOnClickListener(v -> {

            // Optional Toast
            Toast.makeText(
                    MainActivity.this,
                    "Opening Sign Up",
                    Toast.LENGTH_SHORT
            ).show();

            // Open SignupActivity
            Intent intent = new Intent(
                    MainActivity.this,
                    SignUpActivity.class
            );

            startActivity(intent);
        });

        // =========================
        // LOGO CLICK
        // =========================

        logoIcon.setOnClickListener(v -> {

            Toast.makeText(
                    MainActivity.this,
                    "Career Nest",
                    Toast.LENGTH_SHORT
            ).show();
        });

        // =========================
        // BRAND NAME CLICK
        // =========================

        brandName.setOnClickListener(v -> {

            Toast.makeText(
                    MainActivity.this,
                    "Welcome to Career Nest",
                    Toast.LENGTH_SHORT
            ).show();
        });
    }

    // =========================
    // BACK BUTTON BEHAVIOR
    // =========================

    @SuppressLint("GestureBackNavigation")
    @Override
    public void onBackPressed() {

        finishAffinity();

        super.onBackPressed();
    }
}