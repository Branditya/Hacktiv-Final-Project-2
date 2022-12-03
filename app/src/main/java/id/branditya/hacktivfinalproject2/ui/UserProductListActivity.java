package id.branditya.hacktivfinalproject2.ui;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import id.branditya.hacktivfinalproject2.Product;
import id.branditya.hacktivfinalproject2.R;
import id.branditya.hacktivfinalproject2.SQLiteDatabaseHandler;
import id.branditya.hacktivfinalproject2.UserProductListAdapter;

public class UserProductListActivity extends AppCompatActivity {
    SQLiteDatabaseHandler db;
    ArrayList<Product> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new SQLiteDatabaseHandler(this);
        setContentView(R.layout.activity_user_product_list);
        ListView listView = findViewById(R.id.lv_user_product_list);

        Bundle bundle = getIntent().getExtras();
        products = (ArrayList<Product>) db.getProductByType(bundle.getString("KEY_PRODUCT_TYPE"));
        UserProductListAdapter adapter = new UserProductListAdapter(this, products, db);
        listView.setAdapter(adapter);
    }
}