package id.branditya.hacktivfinalproject2.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import id.branditya.hacktivfinalproject2.R;
import id.branditya.hacktivfinalproject2.SQLiteDatabaseHandler;
import id.branditya.hacktivfinalproject2.User;

public class UserLoginActivity extends AppCompatActivity {
    SQLiteDatabaseHandler db;
    ArrayList<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new SQLiteDatabaseHandler(this);
        setContentView(R.layout.activity_user_login);
        Button btnLogin = findViewById(R.id.btn_user_login);
        btnLogin.setOnClickListener(view -> {
            checkNull();
        });
    }

    private void checkNull() {
        EditText etPhoneNumber = findViewById(R.id.et_user_login_phone_number);
        EditText etPassword = findViewById(R.id.et_user_login_password);
        String phoneNumber = etPhoneNumber.getText().toString();
        String password = etPassword.getText().toString();

        if (phoneNumber.isEmpty()) {
            etPhoneNumber.setError("This field cannot be blank.");
        } else {
            etPhoneNumber.setError(null);
        }

        if (password.isEmpty()) {
            etPassword.setError("This field cannot be blank.");
        } else {
            etPassword.setError(null);
        }

        if (!phoneNumber.isEmpty() && !password.isEmpty()) {
            loginAction(phoneNumber, password);
        }
    }

    private void loginAction(String phoneNumber, String password) {
        users = (ArrayList<User>) db.getAllUser();
        for (User users : users) {
            if (phoneNumber.equals(users.getPhoneNumber()) && password.equals(users.getPassword())) {
                Intent intent = new Intent(UserLoginActivity.this, UserHomeActivity.class);
                startActivity(intent);
                Toast.makeText(this, "Login As User", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
            }
            break;
        }

    }
}