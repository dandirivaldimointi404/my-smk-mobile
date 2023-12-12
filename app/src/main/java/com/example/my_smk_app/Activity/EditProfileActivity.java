package com.example.my_smk_app.Activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.my_smk_app.Models.UserModel;
import com.example.my_smk_app.R;
import com.example.my_smk_app.Retrofit.BaseApiServices;
import com.example.my_smk_app.Retrofit.Koneksi;
import com.example.my_smk_app.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileActivity extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button btnUpdateProfile;

    private SessionManager sessionManager;
    private BaseApiServices apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        sessionManager = new SessionManager(this);
        apiService = Koneksi.getAPIService(this);

        editTextName = findViewById(R.id.editTextName);
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        btnUpdateProfile = findViewById(R.id.btnUpdateProfile);

        editTextName.setText(sessionManager.getUsername());
        editTextUsername.setText(sessionManager.getUsername());
        editTextPassword.setText(sessionManager.getPassword());

        btnUpdateProfile.setOnClickListener(view -> updateProfile());
    }

    private void updateProfile() {
        String newName = editTextName.getText().toString().trim();
        String newUsername = editTextUsername.getText().toString().trim();
        String newPassword = editTextPassword.getText().toString().trim();
        String accessToken = sessionManager.getAccessToken();

        if (!newName.isEmpty() && !newUsername.isEmpty() && accessToken != null) {
            UserModel updatedUser = new UserModel(
                    0,
                    newName,
                    newUsername,
                    newPassword, // Update the password
                    "",
                    "",
                    "",
                    ""
            );

            Call<UserModel> call = apiService.updateUser("Bearer " + accessToken, updatedUser);

            call.enqueue(new Callback<UserModel>() {
                @Override
                public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                    if (response.isSuccessful()) {
                        UserModel updatedUser = response.body();
                        sessionManager.saveSession(updatedUser.getUsername(), newPassword, accessToken);
                        showMessage("Profile updated successfully");
                    } else {
                        showMessage("Failed to update profile");
                    }
                }

                @Override
                public void onFailure(Call<UserModel> call, Throwable t) {
                    showMessage("Network error. Please try again.");
                }
            });
        } else {
            showMessage("Please fill in all fields");
        }
    }

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
