package com.project.careernestr;

import org.json.JSONObject;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SupabaseHelper {

    // YOUR SUPABASE INFO
    private static final String SUPABASE_URL =
            "https://rdubgtzxauktllyuivwh.supabase.co/rest/v1/";

    private static final String API_KEY =
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InJkdWJndHp4YXVrdGxseXVpdndoIiwicm9sZSI6ImFub24iLCJpYXQiOjE3Nzg5MTg3OTUsImV4cCI6MjA5NDQ5NDc5NX0.84iatA_TLxD1Hsi9eD1ZZ3OqAtNPue2RB0sjpmnazsc";

    private static final OkHttpClient client =
            new OkHttpClient();

    // INSERT DATA
    public static void insertUser(String name, String email) {

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
                public void onResponse(Call call, Response response)
                        throws IOException {

                    System.out.println(response.body().string());
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}