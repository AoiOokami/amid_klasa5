package com.example.async_02;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class activity_add_edit_item extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_item);

        EditText editTextProductName = findViewById(R.id.editTextProductName);
        EditText editTextPrice = findViewById(R.id.editTextPrice);
        EditText editTextDescription = findViewById(R.id.editTextDescription);
        Button buttonSave = findViewById(R.id.buttonSave);

        String productName = getIntent().getStringExtra("productName");
        double price = getIntent().getIntExtra("price", 0);
        String description = getIntent().getStringExtra("description");
        int position = getIntent().getIntExtra("position", -1);

        if (productName != null) {
            editTextProductName.setText(productName);
            editTextPrice.setText(String.valueOf(price));
            editTextDescription.setText(description);
        }

        buttonSave.setOnClickListener(v -> {
            String newProductName = editTextProductName.getText().toString();
            double newPrice = Double.parseDouble(editTextPrice.getText().toString());
            String newDescription = editTextDescription.getText().toString();

            AssetItem updatedItem = new AssetItem(newProductName, newPrice, newDescription);

            List<AssetItem> items = FileHelper.readFromFile(this);
            if (position != -1) {
                items.set(position, updatedItem);
            } else {
                items.add(updatedItem);
            }

            FileHelper.saveToFile(this, items);
            finish();
        });
    }
}
