package com.example.my_smk_app.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.my_smk_app.R;
import com.example.my_smk_app.Retrofit.BaseApiServices;
import com.example.my_smk_app.Retrofit.Koneksi;
import com.example.my_smk_app.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText, passwordEditText;
    private Button loginButton;

    ProgressDialog loading;
    Context context;
    BaseApiServices apiService;
    SessionManager sessionManager;

    public final static String TAG_USERNAME = "username";
    public final static String TAG_PASSWORD = "password";

    SharedPreferences sharedpreferences;
    Boolean session = false;
    String username, password;
    public static final String my_shared_preferences = "my_shared_preferences";
    public static final String session_status = "session_status";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = findViewById(R.id.btn_login);
        usernameEditText = findViewById(R.id.et_username);
        passwordEditText = findViewById(R.id.et_password);

        context = this;
        apiService = Koneksi.getAPIService(this);

        sessionManager = new SessionManager(this);
        cekSession();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernameInput = usernameEditText.getText().toString().trim();
                String passwordInput = passwordEditText.getText().toString().trim();

                if (TextUtils.isEmpty(usernameInput)) {
                    usernameEditText.setError("Tidak boleh kosong");
                } else if (TextUtils.isEmpty(passwordInput)) {
                    passwordEditText.setError("Tidak boleh kosong");
                } else {
                    loading = ProgressDialog.show(context, null,
                            "Harap Tunggu...", true, false);
                    requestLogin(usernameInput, passwordInput);
                }
            }
        });
    }

    private void requestLogin(String username, String password) {
        apiService.login(username, password)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        loading.dismiss();
                        try {
                            JSONObject jsonRESULTS = new JSONObject(response.body().string());

                            if (jsonRESULTS.getString("message").equals("Unauthorized")) {
                                Toast.makeText(context, "Login gagal. Periksa kredensial Anda.", Toast.LENGTH_SHORT).show();
                            } else {
                                String message = jsonRESULTS.getString("message");
                                String accessToken = jsonRESULTS.getString("access_token");
                                String tokenType = jsonRESULTS.getString("token_type");

                                sessionManager.saveSession(username, password, accessToken);


                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                intent.putExtra(TAG_USERNAME, username);
                                intent.putExtra(TAG_PASSWORD, password);
                                startActivity(intent);
                                finish();
                            }
                        } catch (JSONException | IOException e) {
                            e.printStackTrace();
                            Toast.makeText(context, "Terjadi kesalahan saat parsing data.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR > " + t.toString());
                        loading.dismiss();
                        Toast.makeText(context, "Terjadi kesalahan. Periksa koneksi internet Anda.", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void cekSession() {
        if (sessionManager.isLoggedIn()) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.putExtra(TAG_USERNAME, sessionManager.getUsername());
            intent.putExtra(TAG_PASSWORD, sessionManager.getPassword());
            startActivity(intent);
            finish();
        }
    }
}