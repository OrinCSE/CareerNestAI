package com.project.careernestr;

import android.widget.Toast;
import android.content.Context;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONObject;

public class SupabaseHelper {

    private static final String SUPABASE_URL = "https://rdubgtzxauktllyuivwh.supabase.co";
    private static final String API_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InJkdWJndHp4YXVrdGxseXVpdndoIiwicm9sZSI6ImFub24iLCJpYXQiOjE3Nzg5MTg3OTUsImV4cCI6MjA5NDQ5NDc5NX0.84iatA_TLxD1Hsi9eD1ZZ3OqAtNPue2RB0sjpmnazsc";
    private static final OkHttpClient client = new OkHttpClient();

    // 🚀 ১. এই ইন্টারফেসটি অবশ্যই থাকতে হবে, যা তোমার একটিভিটি খুঁজছে!
    public interface UploadCallback {
        void onUploadSuccess(String fileName);
        void onUploadFailure(String errorMessage);
    }

    // 🚀 প্রোফাইল পিকচার এবং ডাটাবেজ আপডেটের জন্য নতুন কলব্যাক ইন্টারফেস
    public interface DatabaseCallback {
        void onSuccess();
        void onFailure(String errorMessage);
    }

    // 🚀 [নতুন মেথড] প্রোফাইল পিকচার আপলোড করার জন্য (Storage)
    public static void uploadProfileImage(String fileName, byte[] fileData, final Context context, final UploadCallback callback) {
        new Thread(() -> {
            try {
                String bucketName = "profile_images";
                String uploadUrl = SUPABASE_URL + "/storage/v1/object/" + bucketName + "/" + fileName;

                RequestBody body = RequestBody.create(fileData, MediaType.parse("image/jpeg"));

                Request request = new Request.Builder()
                        .url(uploadUrl)
                        .post(body)
                        .addHeader("apikey", API_KEY)
                        .addHeader("Authorization", "Bearer " + API_KEY)
                        .addHeader("x-upsert", "true")
                        .build();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                        if (callback != null) callback.onUploadFailure(e.getMessage());
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response.isSuccessful()) {
                            String publicUrl = SUPABASE_URL + "/storage/v1/object/public/" + bucketName + "/" + fileName;
                            if (callback != null) callback.onUploadSuccess(publicUrl);
                        } else {
                            if (callback != null) callback.onUploadFailure("Error code: " + response.code());
                        }
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    // 🚀 [নতুন মেথড] ইউজারের প্রোফাইলের সব ডাটা একসাথে সিঙ্ক/আপডেট করার জন্য
    public static void updateProfileData(String userId, String name, String email, String phone, String imageUrl, final DatabaseCallback callback) {
        new Thread(() -> {
            try {
                JSONObject json = new JSONObject();
                json.put("id", userId);
                json.put("name", name);
                json.put("email", email);
                json.put("phone_number", phone);
                json.put("profile_image_url", imageUrl);

                RequestBody body = RequestBody.create(
                        json.toString(),
                        MediaType.parse("application/json")
                );

                Request request = new Request.Builder()
                        .url(SUPABASE_URL + "/rest/v1/users")
                        .post(body)
                        .addHeader("apikey", API_KEY)
                        .addHeader("Authorization", "Bearer " + API_KEY)
                        .addHeader("Content-Type", "application/json")
                        .addHeader("Prefer", "resolution=merge-duplicates")
                        .build();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                        if (callback != null) callback.onFailure(e.getMessage());
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response.isSuccessful()) {
                            if (callback != null) callback.onSuccess();
                        } else {
                            if (callback != null) callback.onFailure("DB Error: " + response.code());
                        }
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    // 🚀 ২. ৪টি প্যারামিটারসহ মূল আপলোড মেথড (সম্পূর্ণ অপরিবর্তিত)
    public static void uploadResume(String fileName, byte[] fileData, final Context context, final UploadCallback callback) {
        new Thread(() -> {
            try {
                String bucketName = "resumes";
                String uploadUrl = SUPABASE_URL + "/storage/v1/object/" + bucketName + "/" + fileName;

                RequestBody body = RequestBody.create(fileData, MediaType.parse("application/pdf"));

                Request request = new Request.Builder()
                        .url(uploadUrl)
                        .post(body)
                        .addHeader("apikey", API_KEY)
                        .addHeader("Authorization", "Bearer " + API_KEY)
                        .addHeader("x-upsert", "true")
                        .build();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                        new android.os.Handler(android.os.Looper.getMainLooper()).post(() -> {
                            Toast.makeText(context, "Upload Failed! Network Error", Toast.LENGTH_SHORT).show();
                            if (callback != null) callback.onUploadFailure(e.getMessage());
                        });
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final boolean isSuccess = response.isSuccessful();
                        final int responseCode = response.code();

                        new android.os.Handler(android.os.Looper.getMainLooper()).post(() -> {
                            if (isSuccess) {
                                Toast.makeText(context, "Resume Uploaded to Supabase!", Toast.LENGTH_LONG).show();
                                if (callback != null) callback.onUploadSuccess(fileName);
                            } else {
                                Toast.makeText(context, "Upload Failed Code: " + responseCode, Toast.LENGTH_SHORT).show();
                                if (callback != null) callback.onUploadFailure("Error code: " + responseCode);
                            }
                        });
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    // তোমার আগের insertUser মেথডটি (সম্পূর্ণ অপরিবর্তিত)
    public static void insertUser(String name, String email) {
        new Thread(() -> {
            try {
                JSONObject json = new JSONObject();
                json.put("name", name);
                json.put("email", email);

                RequestBody body = RequestBody.create(
                        json.toString(),
                        MediaType.parse("application/json")
                );

                Request request = new Request.Builder()
                        .url(SUPABASE_URL + "/rest/v1/users")
                        .post(body)
                        .addHeader("apikey", API_KEY)
                        .addHeader("Authorization", "Bearer " + API_KEY)
                        .addHeader("Content-Type", "application/json")
                        .addHeader("Prefer", "return=minimal")
                        .build();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response.isSuccessful()) {
                            System.out.println("Supabase Success: Data inserted successfully!");
                        } else {
                            System.out.println("Supabase Error Code: " + response.code());
                        }
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}