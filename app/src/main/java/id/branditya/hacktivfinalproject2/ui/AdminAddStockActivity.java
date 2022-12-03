package id.branditya.hacktivfinalproject2.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import id.branditya.hacktivfinalproject2.Product;
import id.branditya.hacktivfinalproject2.R;
import id.branditya.hacktivfinalproject2.SQLiteDatabaseHandler;

public class AdminAddStockActivity extends AppCompatActivity {
    ArrayList<Product> products;
    Product product;
    SQLiteDatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new SQLiteDatabaseHandler(this);
        setContentView(R.layout.activity_admin_add_stock);
        Button btnAddStock = findViewById(R.id.btn_add_stock);
        btnAddStock.setOnClickListener(view -> {
            checkNull();
        });
    }

    private void checkNull() {
        EditText etProductId = findViewById(R.id.et_id_product);
        EditText etProductQuantity = findViewById(R.id.et_quantity);
        String productId = etProductId.getText().toString();
        String productQuantity = etProductQuantity.getText().toString();
        if (productId.isEmpty()) {
            etProductId.setError("This field cannot be blank.");
        } else {
            etProductId.setError(null);
        }

        if (productQuantity.isEmpty()) {
            etProductQuantity.setError("This field cannot be blank.");
        } else {
            etProductQuantity.setError(null);
        }

        if (!productId.isEmpty() && !productQuantity.isEmpty()) {
            addStock(Integer.parseInt(productId), Integer.parseInt(productQuantity));
        }


    }

    private void addStock(int productId, int productQuantity) {
        product = db.getProduct(productId);
        int productQuantityUpdated = product.getQuantity() + productQuantity;
        db.editProductQuantity(productId, productQuantityUpdated);
        Toast.makeText(this, "Stock Added Successfully", Toast.LENGTH_SHORT).show();

        finish();

        products = (ArrayList<Product>) db.getAllProduct();
        for (Product products : products) {
            Log.e("Isi product", "Type = " + products.getType() + "Filter = " + products.getFilter() +
                    "Category = " + products.getCategory() + "Name = " + products.getName() +
                    "Quantity = " + products.getQuantity() );
        }
    }
}