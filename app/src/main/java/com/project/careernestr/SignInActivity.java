package com.project.careernestr;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Patterns;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class SignInActivity extends AppCompatActivity {

    private TextInputEditText emailInput, passwordInput;
    private MaterialButton btnSignIn, btnGoogle;
    private ImageButton btnBack;
    private ImageView eyeIcon;
    private TextView tvForgotPassword, tvGotoSignup;

    private boolean isPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        // Initialize Views
        emailInput = findViewById(R.id.email_input);
        passwordInput = findViewById(R.id.password_input);

        btnSignIn = findViewById(R.id.btn_signin_submit);
        btnGoogle = findViewById(R.id.btn_google);

        btnBack = findViewById(R.id.btn_back);

        eyeIcon = findViewById(R.id.eye_icon);

        tvForgotPassword = findViewById(R.id.tv_forgot_password);
        tvGotoSignup = findViewById(R.id.tv_goto_signup);

        // =========================
        // BACK BUTTON
        // =========================
        btnBack.setOnClickListener(v -> {
            finish();
        });

        // =========================
        // PASSWORD TOGGLE
        // =========================
        eyeIcon.setOnClickListener(v -> {

            if (isPasswordVisible) {

                // Hide Password
                passwordInput.setInputType(
                        InputType.TYPE_CLASS_TEXT |
                                InputType.TYPE_TEXT_VARIATION_PASSWORD
                );

                eyeIcon.setImageResource(R.drawable.ic_eye_visible);

                isPasswordVisible = false;

            } else {

                // Show Password
                passwordInput.setInputType(
                        InputType.TYPE_CLASS_TEXT |
                                InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                );

                eyeIcon.setImageResource(R.drawable.ic_eye_close);

                isPasswordVisible = true;
            }

            // Keep cursor at end
            if (passwordInput.getText() != null) {
                passwordInput.setSelection(passwordInput.getText().length());
            }
        });

        // =========================
        // SIGN IN BUTTON
        // =========================
        btnSignIn.setOnClickListener(v -> {

            String email = emailInput.getText().toString().trim();
            String password = passwordInput.getText().toString().trim();

            // Email Validation
            if (email.isEmpty()) {
                emailInput.setError("Email is required");
                emailInput.requestFocus();
                return;
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                emailInput.setError("Enter a valid email");
                emailInput.requestFocus();
                return;
            }

            // Password Validation
            if (password.isEmpty()) {
                passwordInput.setError("Password is required");
                passwordInput.requestFocus();
                return;
            }

            if (password.length() < 4) {
                passwordInput.setError("Password must be at least 4 characters");
                passwordInput.requestFocus();
                return;
            }

            // SUCCESS
            Toast.makeText(
                    SignInActivity.this,
                    "Login Successful!",
                    Toast.LENGTH_SHORT
            ).show();

            // =========================
            // GO TO DASHBOARD
            // =========================

            Intent intent = new Intent(
                    SignInActivity.this,
                    Activity_dashboard.class
            );

            startActivity(intent);
            finish();
        });

        // =========================
        // GOOGLE BUTTON
        // =========================
        btnGoogle.setOnClickListener(v -> {

            Toast.makeText(
                    SignInActivity.this,
                    "Google Sign-In Clicked",
                    Toast.LENGTH_SHORT
            ).show();

            /*
             Later:
             Add Firebase Google Authentication here
             */
        });

        // =========================
        // FORGOT PASSWORD
        // =========================
        tvForgotPassword.setOnClickListener(v -> {

            Toast.makeText(
                    SignInActivity.this,
                    "Forgot Password Clicked",
                    Toast.LENGTH_SHORT
            ).show();

            /*
             Example:
             startActivity(new Intent(
                     SignInActivity.this,
                     ForgotPasswordActivity.class
             ));
             */
        });

        // =========================
        // GO TO SIGN UP
        // =========================
        tvGotoSignup.setOnClickListener(v -> {

            Toast.makeText(
                    SignInActivity.this,
                    "Opening Sign Up Screen",
                    Toast.LENGTH_SHORT
            ).show();

            /*
             Replace SignupActivity with your activity
             */

            Intent intent = new Intent(
                    SignInActivity.this,
                    SignUpActivity.class
            );

            startActivity(intent);
        });
    }
}