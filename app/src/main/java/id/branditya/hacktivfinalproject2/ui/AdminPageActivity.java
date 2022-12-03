package id.branditya.hacktivfinalproject2.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import id.branditya.hacktivfinalproject2.R;

public class AdminPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);
        Button btnAddStaff = findViewById(R.id.btn_add_staff);
        Button btnAddStock = findViewById(R.id.btn_add_stock);
        btnAddStaff.setOnClickListener(view->{
            Intent intent = new Intent(AdminPageActivity.this, AdminAddStaffActivity.class);
            startActivity(intent);
        });
        btnAddStock.setOnClickListener(view->{
            Intent intent = new Intent(AdminPageActivity.this, AdminAddStockActivity.class);
            startActivity(intent);
        });
    }

}