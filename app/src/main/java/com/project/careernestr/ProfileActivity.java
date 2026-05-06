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

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.io.IOException;

public class ProfileActivity extends AppCompatActivity {

    ImageView cameraIcon;
    de.hdodenhof.circleimageview.CircleImageView profileImage;

    EditText name,email,location,phone,skillInput;

    Button addSkill, saveProfile;

    ChipGroup skillsChipGroup;

    int PICK_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profileImage = findViewById(R.id.profileImage);
        cameraIcon = findViewById(R.id.cameraIcon);

        skillInput = findViewById(R.id.skillInput);
        addSkill = findViewById(R.id.addSkill);
        skillsChipGroup = findViewById(R.id.skillsChipGroup);

        saveProfile = findViewById(R.id.saveProfile);

        cameraIcon.setOnClickListener(v -> openGallery());
        profileImage.setOnClickListener(v -> openGallery());

        addSkill.setOnClickListener(v -> addSkillTag());

        saveProfile.setOnClickListener(v -> saveProfileData());
    }

    private void openGallery(){

        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,PICK_IMAGE);
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

    private void saveProfileData(){

        SharedPreferences prefs = getSharedPreferences("profile",MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putString("name",name.getText().toString());
        editor.putString("email",email.getText().toString());
        editor.putString("location",location.getText().toString());
        editor.putString("phone",phone.getText().toString());

        editor.apply();
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){

        super.onActivityResult(requestCode,resultCode,data);

        if(requestCode==PICK_IMAGE && resultCode==RESULT_OK){

            Uri imageUri=data.getData();

            try{

                Bitmap bitmap= MediaStore.Images.Media.getBitmap(this.getContentResolver(),imageUri);
                profileImage.setImageBitmap(bitmap);

            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}