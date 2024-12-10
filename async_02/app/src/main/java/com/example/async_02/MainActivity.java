package com.example.async_02;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonAddNew = findViewById(R.id.buttonAddNew);
        listView = findViewById(R.id.listViewItems);

        buttonAddNew.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, activity_add_edit_item.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        new LoadDataAsyncTask(this, listView).execute();
    }
}
