package com.project.careernestr;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class ResumeUploadActivity extends AppCompatActivity {

    private Button uploadBtn;
    private static final int PICK_FILE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resume_upload);

        uploadBtn = findViewById(R.id.uploadBtn);
        uploadBtn.setOnClickListener(v -> openFilePicker());
    }

    private void openFilePicker() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        String[] mimetypes = {
                "application/pdf",
                "application/msword",
                "application/vnd.openxmlformats-officedocument.wordprocessingml.document"
        };
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimetypes);
        startActivityForResult(Intent.createChooser(intent, "Select Resume"), PICK_FILE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_FILE && resultCode == RESULT_OK && data != null) {
            Uri fileUri = data.getData();
            if (fileUri != null) {
                // ফাইল আপলোড প্রসেস শুরু করা হলো
                processAndUploadFile(fileUri);
            }
        }
    }

    private void processAndUploadFile(Uri uri) {
        try {
            // ১. ফাইলের আসল নাম বের করা
            String fileName = "resume_" + System.currentTimeMillis() + ".pdf";
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    int nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
                    if (nameIndex >= 0) {
                        fileName = cursor.getString(nameIndex);
                    }
                }
                cursor.close();
            }

            // ২. ফাইলটিকে byte array তে কনভার্ট করা সুপাবেসে পাঠানোর জন্য
            InputStream inputStream = getContentResolver().openInputStream(uri);
            if (inputStream == null) return;

            ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = inputStream.read(buffer)) != -1) {
                byteBuffer.write(buffer, 0, len);
            }
            byte[] fileData = byteBuffer.toByteArray();
            inputStream.close();

            Toast.makeText(this, "Uploading to server, please wait...", Toast.LENGTH_SHORT).show();

            // ৩. সুপাবেসে ফাইল আপলোড করা এবং সাকসেস হলে স্ক্রিন চেঞ্জ করা
            final String finalFileName = fileName;
            SupabaseHelper.uploadResume(fileName, fileData, this, new SupabaseHelper.UploadCallback() {
                @Override
                public void onUploadSuccess(String uploadedName) {
                    // 🔥 এই সেই ম্যাজিক লজিক! আপলোড সম্পূর্ণ হলে তবেই এনালাইজার পেজটি ওপেন হবে
                    Intent intent = new Intent(ResumeUploadActivity.this, BrowseActivity.class);
                    intent.putExtra("RESUME_URI", uri.toString());
                    intent.putExtra("RESUME_NAME", finalFileName);
                    startActivity(intent);
                    finish(); // আপলোড স্ক্রিন বন্ধ করে দেবে
                }

                @Override
                public void onUploadFailure(String errorMessage) {
                    Toast.makeText(ResumeUploadActivity.this, "Failed to analyze resume: " + errorMessage, Toast.LENGTH_LONG).show();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "File processing error!", Toast.LENGTH_SHORT).show();
        }
    }
}