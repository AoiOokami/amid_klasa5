package com.example.okno;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddTaskActivity extends AppCompatActivity {
    private EditText editTextTaskName, editTextTaskDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        initializeViews();
    }

    private void initializeViews() {
        editTextTaskName = findViewById(R.id.editTextTaskName);
        editTextTaskDescription = findViewById(R.id.editTextTaskDescription);
        Button buttonSaveTask = findViewById(R.id.buttonSaveTask);

        buttonSaveTask.setOnClickListener(this::saveTask);
    }

    private void saveTask(View view) {
        String name = editTextTaskName.getText().toString();
        String description = editTextTaskDescription.getText().toString();

        Intent resultIntent = new Intent();
        resultIntent.putExtra("nazwa", name);
        resultIntent.putExtra("opis", description);
        setResult(RESULT_OK, resultIntent);
        finish();
    }
}
