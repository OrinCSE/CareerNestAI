package com.project.careernestr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ProfileActivity extends AppCompatActivity {

    ImageView cameraIcon;
    de.hdodenhof.circleimageview.CircleImageView profileImage;

    EditText name, email, location, phone, skillInput;
    Button addSkill, saveProfile;
    ChipGroup skillsChipGroup;

    int PICK_IMAGE = 1;
    Uri selectedImageUri = null;

    // 💡 এখানে তোমার রিয়েল লগইন সিস্টেম থেকে আসা ইউজারের UUID বসাবে।
    // সাময়িকভাবে টেস্ট করার জন্য আমি একটি ইউনিক আইডি স্ট্রিং দিয়ে রাখলাম।
    String currentUserId = "f1c0e1a3-7e25-4e6f-893a-f4489e2093b6";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // ১. ভিউ ইনিশিয়েলাইজেশন
        profileImage = findViewById(R.id.profileImage);
        cameraIcon = findViewById(R.id.cameraIcon);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        location = findViewById(R.id.location);
        phone = findViewById(R.id.phone);
        skillInput = findViewById(R.id.skillInput);
        addSkill = findViewById(R.id.addSkill);
        skillsChipGroup = findViewById(R.id.skillsChipGroup);
        saveProfile = findViewById(R.id.saveProfile);

        // 📥 ২. লোকাল SharedPreferences থেকে ডাটা অটো-লোড হবে (যাতে চলে না যায়)
        loadProfileFromLocal();

        // ৩. লিসেনার সেট করা
        cameraIcon.setOnClickListener(v -> openGallery());
        profileImage.setOnClickListener(v -> openGallery());
        addSkill.setOnClickListener(v -> addSkillTag());
        saveProfile.setOnClickListener(v -> saveProfileData());
    }

    private void openGallery(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE);
    }

    private void addSkillTag(){
        String skill = skillInput.getText().toString().trim();
        if(!skill.isEmpty()){
            Chip chip = new Chip(this);
            chip.setText(skill);
            chip.setCloseIconVisible(true);
            chip.setOnCloseIconClickListener(v -> skillsChipGroup.removeView(chip));
            skillsChipGroup.addView(chip);
            skillInput.setText("");
        }
    }

    // 📥 SharedPreferences থেকে ডাটা এডিট টেক্সটে সেট করা
    private void loadProfileFromLocal() {
        SharedPreferences prefs = getSharedPreferences("career_nest_profile", MODE_PRIVATE);

        name.setText(prefs.getString("name", ""));
        email.setText(prefs.getString("email", ""));
        location.setText(prefs.getString("location", ""));
        phone.setText(prefs.getString("phone_number", ""));

        String savedImageUrl = prefs.getString("profile_image_url", "");
        if (!savedImageUrl.isEmpty()) {
            Glide.with(this).load(savedImageUrl).into(profileImage);
        }
    }

    private void saveProfileData(){
        if (name == null || email == null || location == null || phone == null) {
            Toast.makeText(this, "Error: Views are not initialized properly!", Toast.LENGTH_SHORT).show();
            return;
        }

        String userFullName = name.getText().toString().trim();
        String userEmail = email.getText().toString().trim();
        String userLocation = location.getText().toString().trim();
        String userPhone = phone.getText().toString().trim();

        if (userEmail.isEmpty()) {
            Toast.makeText(this, "Email is required!", Toast.LENGTH_SHORT).show();
            return;
        }

        // ১. Local Storage-এ ব্যাকআপ রাখা
        SharedPreferences prefs = getSharedPreferences("career_nest_profile", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("name", userFullName);
        editor.putString("email", userEmail);
        editor.putString("location", userLocation);
        editor.putString("phone_number", userPhone);
        editor.apply();

        // ২. Supabase ক্লাউডে ডাটা পাঠানো
        if (selectedImageUri != null) {
            uploadImageAndSync(selectedImageUri, userFullName, userEmail, userPhone);
        } else {
            String existingUrl = prefs.getString("profile_image_url", "");
            syncTextDataToSupabase(userFullName, userEmail, userPhone, existingUrl);
        }
    }

    // ☁️ সুপাবেজ স্টোরেজে ইমেজ আপলোড
    private void uploadImageAndSync(Uri imageUri, String fullName, String userEmail, String userPhone) {
        Toast.makeText(this, "Uploading Profile Picture...", Toast.LENGTH_SHORT).show();

        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 75, baos);
            byte[] fileData = baos.toByteArray();

            String fileName = currentUserId + "_avatar.jpg";

            // এখানে SupabaseHelper এর আপলোড মেথডটি কল করা হচ্ছে (যা Public URL রিটার্ন করবে)
            SupabaseHelper.uploadProfileImage(fileName, fileData, this, new SupabaseHelper.UploadCallback() {
                @Override
                public void onUploadSuccess(String publicUrl) {
                    runOnUiThread(() -> syncTextDataToSupabase(fullName, userEmail, userPhone, publicUrl));
                }

                @Override
                public void onUploadFailure(String errorMessage) {
                    runOnUiThread(() -> Toast.makeText(ProfileActivity.this, "Image Upload Failed! " + errorMessage, Toast.LENGTH_SHORT).show());
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to process image!", Toast.LENGTH_SHORT).show();
        }
    }

    // 🗄️ সুপাবেজ ডাটাবেজের `users` টেবিলে ডাটা সিঙ্ক করা
    private void syncTextDataToSupabase(String fullName, String userEmail, String userPhone, String imageUrl) {

        SharedPreferences prefs = getSharedPreferences("career_nest_profile", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("profile_image_url", imageUrl);
        editor.apply();

        SupabaseHelper.updateProfileData(currentUserId, fullName, userEmail, userPhone, imageUrl, new SupabaseHelper.DatabaseCallback() {
            @Override
            public void onSuccess() {
                runOnUiThread(() -> Toast.makeText(ProfileActivity.this, "Profile Saved Successfully!", Toast.LENGTH_SHORT).show());
            }

            @Override
            public void onFailure(String errorMessage) {
                runOnUiThread(() -> Toast.makeText(ProfileActivity.this, "Cloud Sync Failed: " + errorMessage, Toast.LENGTH_SHORT).show());
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null){
            selectedImageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
                profileImage.setImageBitmap(bitmap);
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }
}