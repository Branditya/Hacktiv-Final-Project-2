package id.branditya.hacktivfinalproject2.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import id.branditya.hacktivfinalproject2.R;

public class StaffPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_page);
        Bundle bundle = getIntent().getExtras();

        TextView tvStaffName = findViewById(R.id.tv_staff_name);
        tvStaffName.setText(bundle.getString("KEY_STAFF_USERNAME"));
    }
}