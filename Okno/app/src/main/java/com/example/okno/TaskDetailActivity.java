package com.example.okno;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class TaskDetailActivity extends AppCompatActivity {
    private EditText editTextTaskDetailName, editTextTaskDetailDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        initializeViews();
        populateTaskDetails();
    }

    private void initializeViews() {
        editTextTaskDetailName = findViewById(R.id.editTextTaskDetailName);
        editTextTaskDetailDescription = findViewById(R.id.editTextTaskDetailDescription);
        Button buttonUpdateTask = findViewById(R.id.buttonUpdateTask);
        buttonUpdateTask.setOnClickListener(this::updateTask);
    }

    private void populateTaskDetails() {
        Intent intent = getIntent();
        String taskName = intent.getStringExtra("nazwa");
        String taskDescription = intent.getStringExtra("opis");

        editTextTaskDetailName.setText(taskName);
        editTextTaskDetailDescription.setText(taskDescription);
    }

    private void updateTask(View view) {
        String updatedName = editTextTaskDetailName.getText().toString();
        String updatedDescription = editTextTaskDetailDescription.getText().toString();
        int taskPosition = getIntent().getIntExtra("pozycja", -1);

        Intent resultIntent = new Intent();
        resultIntent.putExtra("pozycja", taskPosition);
        resultIntent.putExtra("nazwa", updatedName);
        resultIntent.putExtra("opis", updatedDescription);

        setResult(RESULT_OK, resultIntent);
        finish();
    }
}
