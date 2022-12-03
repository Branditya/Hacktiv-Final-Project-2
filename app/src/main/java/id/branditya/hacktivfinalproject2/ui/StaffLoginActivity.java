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
import id.branditya.hacktivfinalproject2.Staff;

public class StaffLoginActivity extends AppCompatActivity {
    ArrayList<Staff> staffs;
    SQLiteDatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new SQLiteDatabaseHandler(this);
        setContentView(R.layout.activity_login_staff);
        Button btnLogin = findViewById(R.id.btn_staff_login);
        btnLogin.setOnClickListener(view-> {
            checkNull();
        });
    }

    private void checkNull(){
        EditText etUsername = findViewById(R.id.et_staff_username);
        EditText etPassword = findViewById(R.id.et_staff_password);
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
        staffs = (ArrayList<Staff>) db.getAllStaff();
        for (Staff staffs : staffs) {
            if (username.equals(staffs.getName())&& password.equals(staffs.getPassword())) {
                Intent intent = new Intent(StaffLoginActivity.this, StaffPageActivity.class);
                intent.putExtra("KEY_STAFF_USERNAME", staffs.getName());
                startActivity(intent);
                Toast.makeText(this, "Login As Staff", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
            }
            break;
        }

    }
}