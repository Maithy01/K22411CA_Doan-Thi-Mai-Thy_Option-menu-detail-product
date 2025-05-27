package com.tranduythanh.k22411casampleproject;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.tranduythanh.models.Product;

public class ProductDetailActivity extends AppCompatActivity {

    TextView tvProductId, tvProductName, tvProductPrice, tvProductDescription, tvProductQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        addViews();

        Product product = (Product) getIntent().getSerializableExtra("product");
        if (product != null) {
            displayProduct(product);
        }
    }

    private void addViews() {
        tvProductId = findViewById(R.id.tvProductId);
        tvProductName = findViewById(R.id.tvProductName);
        tvProductQuantity = findViewById(R.id.tvProductQuantity);
        tvProductPrice = findViewById(R.id.tvProductPrice);
        tvProductDescription = findViewById(R.id.tvProductDescription);
    }

    private void displayProduct(Product product) {
        tvProductId.setText("ID: " + product.getId());
        tvProductName.setText("Name: " + product.getName());
        tvProductQuantity.setText("Quantity: " + product.getQuantity());
        tvProductPrice.setText("Price: $" + product.getPrice());
        tvProductDescription.setText("Description: " + product.getDescription());
    }
}


