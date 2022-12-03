package id.branditya.hacktivfinalproject2.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import id.branditya.hacktivfinalproject2.R;
import id.branditya.hacktivfinalproject2.SQLiteDatabaseHandler;
import id.branditya.hacktivfinalproject2.Staff;
import id.branditya.hacktivfinalproject2.User;

public class UserRegisterActivity extends AppCompatActivity {
    SQLiteDatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new SQLiteDatabaseHandler(this);
        setContentView(R.layout.activity_user_register);
        Button btnRegisterUser = findViewById(R.id.btn_user_register);
        btnRegisterUser.setOnClickListener(view -> {
            checkNull();
        });
    }

    private void checkNull() {
        EditText etUserName = findViewById(R.id.et_user_register_name);
        EditText etUserPassword = findViewById(R.id.et_user_register_password);
        EditText etUserPhoneNumber = findViewById(R.id.et_user_register_phone_number);
        String userName = etUserName.getText().toString();
        String userPassword = etUserPassword.getText().toString();
        String userPhoneNumber = etUserPhoneNumber.getText().toString();
        if (userName.isEmpty()) {
            etUserName.setError("This field cannot be blank.");
        } else {
            etUserName.setError(null);
        }

        if (userPassword.isEmpty()) {
            etUserPassword.setError("This field cannot be blank.");
        } else {
            etUserPassword.setError(null);
        }

        if (userPhoneNumber.isEmpty()) {
            etUserPhoneNumber.setError("This field cannot be blank.");
        } else {
            etUserPhoneNumber.setError(null);
        }

        if (!userName.isEmpty() && !userPassword.isEmpty() && !userPhoneNumber.isEmpty()) {
            registerStaff(userName, userPhoneNumber, userPassword);
        }


    }

    private void registerStaff(String userName, String userPhoneNumber, String userPassword) {
        User user = new User(userName, userPhoneNumber, userPassword);
        db.addUser(user);
        Toast.makeText(this, "User Registered Successfully", Toast.LENGTH_SHORT).show();

        finish();

        Intent intent = new Intent(UserRegisterActivity.this, UserLoginActivity.class);
        startActivity(intent);
    }
}