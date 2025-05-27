package com.tranduythanh.k22411casampleproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.tranduythanh.models.Category;
import com.tranduythanh.models.ListCategory;
import com.tranduythanh.models.Product;

public class ProductManagementActivity extends AppCompatActivity {

    Spinner spinnerCategory;
    ArrayAdapter<Category> adapterCategory;
    ListCategory listCategory;

    ListView lvProduct;
    ArrayAdapter<Product> adapterProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product_management);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        addViews();
        addEvents();
    }

    private void addViews() {
        spinnerCategory = findViewById(R.id.spinnerCategory);
        adapterCategory = new ArrayAdapter<>(ProductManagementActivity.this,
                android.R.layout.simple_spinner_item);
        adapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapterCategory);

        listCategory = new ListCategory();
        listCategory.generate_product_dataset();
        adapterCategory.addAll(listCategory.getCategories());

        lvProduct = findViewById(R.id.lvProduct);
        adapterProduct = new ArrayAdapter<>(ProductManagementActivity.this,
                android.R.layout.simple_list_item_1);
        lvProduct.setAdapter(adapterProduct);
    }

    private void addEvents() {
        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Category c = adapterCategory.getItem(i);
                displayProductByCategory(c);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });

        lvProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Product selectedProduct = adapterProduct.getItem(position);
                Intent intent = new Intent(ProductManagementActivity.this, ProductDetailActivity.class);
                intent.putExtra("product", selectedProduct);
                startActivity(intent);
            }
        });
    }

    private void displayProductByCategory(Category c) {
        adapterProduct.clear();
        adapterProduct.addAll(c.getProducts());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.product_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_add_product) {
            Intent intent = new Intent(this, ProductDetailActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.menu_search) {
            Toast.makeText(this, "Mở thanh tìm kiếm", Toast.LENGTH_SHORT).show();
            // TODO: Tạo thanh tìm kiếm
            return true;
        } else if (id == R.id.menu_show_all) {
            Toast.makeText(this, "", Toast.LENGTH_SHORT).show();

            adapterProduct.clear();
            for (Category category : listCategory.getCategories()) {
                adapterProduct.addAll(category.getProducts());
            }
            return true;
        } else if (id == R.id.menu_exit) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

