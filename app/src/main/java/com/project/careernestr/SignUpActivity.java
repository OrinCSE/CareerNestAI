package com.project.careernestr;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {

    private boolean isPassVisible = false;
    private boolean isConfirmPassVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        EditText etPass = findViewById(R.id.et_password_signup);
        EditText etConfirmPass = findViewById(R.id.et_confirm_password);
        ImageView ivToggle1 = findViewById(R.id.iv_toggle_pass1);
        ImageView ivToggle2 = findViewById(R.id.iv_toggle_pass2);

        // Toggle Password 1
        ivToggle1.setOnClickListener(v -> {
            if (isPassVisible) {
                etPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                ivToggle1.setImageResource(R.drawable.ic_eye_close);
            } else {
                etPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                ivToggle1.setImageResource(R.drawable.ic_eye_on);
            }
            isPassVisible = !isPassVisible;
            etPass.setSelection(etPass.getText().length());
        });

        // Toggle Confirm Password
        ivToggle2.setOnClickListener(v -> {
            if (isConfirmPassVisible) {
                etConfirmPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                ivToggle2.setImageResource(R.drawable.ic_eye_close);
            } else {
                etConfirmPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                ivToggle2.setImageResource(R.drawable.ic_eye_on);
            }
            isConfirmPassVisible = !isConfirmPassVisible;
            etConfirmPass.setSelection(etConfirmPass.getText().length());
        });

        findViewById(R.id.btn_signup_submit).setOnClickListener(v -> {
            // আপাতত সরাসরি ড্যাশবোর্ডে পাঠিয়ে দেওয়ার জন্য:
            Toast.makeText(SignUpActivity.this, "Registration Successful!", Toast.LENGTH_SHORT).show();

            android.content.Intent intent = new android.content.Intent(SignUpActivity.this, Activity_dashboard.class);
            startActivity(intent);
            finish(); // সাইন আপ শেষে এই পেজ ক্লোজ করে দিবে
        });

        // Back Button
        findViewById(R.id.btn_back_signup).setOnClickListener(v -> finish());
    }
}