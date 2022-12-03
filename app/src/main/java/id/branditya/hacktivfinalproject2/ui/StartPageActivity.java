package id.branditya.hacktivfinalproject2.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import id.branditya.hacktivfinalproject2.R;

public class StartPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);
        TextView btnToLoginAdmin = findViewById(R.id.btn_to_login_admin);
        TextView btnToLoginStaff = findViewById(R.id.btn_to_login_staff);
        TextView btnToAbout = findViewById(R.id.btn_to_about);
        TextView btnToLoginUser = findViewById(R.id.btn_to_user_login);
        TextView btnToRegisterUser = findViewById(R.id.btn_to_user_register);

        btnToLoginAdmin.setOnClickListener(view -> {
            Intent intent = new Intent(StartPageActivity.this, AdminLoginActivity.class);
            startActivity(intent);
        });
        btnToLoginStaff.setOnClickListener(view -> {
            Intent intent = new Intent(StartPageActivity.this, StaffLoginActivity.class);
            startActivity(intent);
        });
        btnToAbout.setOnClickListener(view -> {
            Intent intent = new Intent(StartPageActivity.this, AboutActivity.class);
            startActivity(intent);
        });
        btnToLoginUser.setOnClickListener(view -> {
            Intent intent = new Intent(StartPageActivity.this, UserLoginActivity.class);
            startActivity(intent);
        });
        btnToRegisterUser.setOnClickListener(view -> {
            Intent intent = new Intent(StartPageActivity.this, UserRegisterActivity.class);
            startActivity(intent);
        });
    }
}