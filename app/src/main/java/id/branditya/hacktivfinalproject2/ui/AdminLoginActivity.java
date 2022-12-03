package id.branditya.hacktivfinalproject2.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import id.branditya.hacktivfinalproject2.Admin;
import id.branditya.hacktivfinalproject2.R;
import id.branditya.hacktivfinalproject2.SQLiteDatabaseHandler;

public class AdminLoginActivity extends AppCompatActivity {
    ArrayList<Admin> admins;
    SQLiteDatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new SQLiteDatabaseHandler(this);
        setContentView(R.layout.activity_login_admin);
        Button btnLogin = findViewById(R.id.btn_admin_login);
        btnLogin.setOnClickListener(view -> {
            checkNull();
        });
    }

    private void checkNull() {
        EditText etUsername = findViewById(R.id.et_admin_username);
        EditText etPassword = findViewById(R.id.et_admin_password);
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        if (username.isEmpty()) {
            etUsername.setError("This field cannot be blank.");
        } else {
            etUsername.setError(null);
        }

        if (password.isEmpty()) {
            etPassword.setError("This field cannot be blank.");
        } else {
            etPassword.setError(null);
        }

        if (!username.isEmpty() && !password.isEmpty()) {
            loginAction(username, password);
        }
    }

    private void loginAction(String username, String password) {
        admins = (ArrayList<Admin>) db.getAllAdmin();
        for (Admin admins : admins) {
            if (username.equals(admins.getName()) && password.equals(admins.getPassword())) {
                Intent intent = new Intent(AdminLoginActivity.this, AdminPageActivity.class);
                startActivity(intent);
                Toast.makeText(this, "Login As Admin", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
            }
            break;
        }

    }
}