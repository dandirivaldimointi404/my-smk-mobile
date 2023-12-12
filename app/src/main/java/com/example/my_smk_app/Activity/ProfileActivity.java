package com.example.my_smk_app.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.my_smk_app.R;
import com.example.my_smk_app.SessionManager;

public class ProfileActivity extends AppCompatActivity {

    TextView tv_username;
    ImageView backButton;
    Button btn_logout;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tv_username = findViewById(R.id.tv_username);
        backButton = findViewById(R.id.backButton);
        btn_logout = findViewById(R.id.btn_logout);
        sessionManager = new SessionManager(this);

        String username = sessionManager.getUsername();
        tv_username.setText(username != null ? username : "Unknown User");

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLogoutConfirmationDialog();
            }
        });

    }

    private void showLogoutConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);

        View view = getLayoutInflater().inflate(R.layout.custom_logout_dialog, null);
        builder.setView(view);

        TextView titleTextView = view.findViewById(R.id.customDialogTitle);
        TextView messageTextView = view.findViewById(R.id.customDialogMessage);
        Button positiveButton = view.findViewById(R.id.customDialogPositiveButton);
        Button negativeButton = view.findViewById(R.id.customDialogNegativeButton);

        titleTextView.setText("Konfirmasi Logout");
        messageTextView.setText("Apakah Anda yakin ingin logout?");

        AlertDialog alertDialog = builder.create();


        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performLogout();
                alertDialog.dismiss();
            }
        });

        negativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        alertDialog.show();
    }

    private void performLogout() {
        sessionManager.logout();
        Toast.makeText(ProfileActivity.this, "Anda telah logout. Sampai jumpa!", Toast.LENGTH_SHORT).show();

        tv_username.setText("");

        Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}