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


public class AdminAddStaffActivity extends AppCompatActivity {
    SQLiteDatabaseHandler db;
    ArrayList<Staff> staffs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new SQLiteDatabaseHandler(this);
        setContentView(R.layout.activity_admin_add_staff);
        Button btnRegisterStaff = findViewById(R.id.btn_register_staff);
        btnRegisterStaff.setOnClickListener(view -> {
            checkNull();
        });
    }

    private void checkNull() {
        EditText etStaffName = findViewById(R.id.et_name);
        EditText etStaffPassword = findViewById(R.id.et_password);
        EditText etStaffConfirmPassword = findViewById(R.id.et_confirm_password);
        String staffName = etStaffName.getText().toString();
        String staffPassword = etStaffPassword.getText().toString();
        String staffConfirmPassword = etStaffConfirmPassword.getText().toString();
        if (staffName.isEmpty()) {
            etStaffName.setError("This field cannot be blank.");
        } else {
            etStaffName.setError(null);
        }

        if (staffPassword.isEmpty()) {
            etStaffPassword.setError("This field cannot be blank.");
        } else {
            etStaffPassword.setError(null);
        }

        if (!staffConfirmPassword.equals(staffPassword)) {
            etStaffConfirmPassword.setError("The password confirmation does not match.");
        } else {
            etStaffConfirmPassword.setError(null);
        }
        if (!staffName.isEmpty() && !staffPassword.isEmpty() && staffConfirmPassword.equals(staffPassword)) {
            registerStaff(staffName, staffPassword);
        }


    }

    private void registerStaff(String staffName, String staffPassword) {
        Staff staff = new Staff(staffName, staffPassword);
        db.addStaff(staff);
        Toast.makeText(this, "Staff Added Successfully", Toast.LENGTH_SHORT).show();

        finish();

        staffs = (ArrayList<Staff>) db.getAllStaff();
        for (Staff staffs : staffs) {
            Log.e("Isi Staff", staffs.getName() + staffs.getPassword());
        }
    }
}