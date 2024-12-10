package com.example.okno;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TaskAdapter adapter;
    private ArrayList<Task> tasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
        setupRecyclerView();
        setupFabListener();
    }

    private void initializeViews() {
        recyclerView = findViewById(R.id.recyclerViewTasks);
        tasks = new ArrayList<>();
    }

    private void setupRecyclerView() {
        adapter = new TaskAdapter(tasks, this::openTaskDetailActivity);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void setupFabListener() {
        FloatingActionButton fabAddTask = findViewById(R.id.fabAddTask);
        fabAddTask.setOnClickListener(v -> openAddTaskActivity());
    }

    private void openAddTaskActivity() {
        Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
        startActivityForResult(intent, 1);
    }

    private void openTaskDetailActivity(int position) {
        Intent intent = new Intent(MainActivity.this, TaskDetailActivity.class);
        intent.putExtra("pozycja", position);
        intent.putExtra("nazwa", tasks.get(position).getName());
        intent.putExtra("opis", tasks.get(position).getOpis());
        startActivityForResult(intent, 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data == null) return;

        if (requestCode == 1 && resultCode == RESULT_OK) {
            addNewTask(data);
        } else if (requestCode == 2 && resultCode == RESULT_OK) {
            updateExistingTask(data);
        }
    }

    private void addNewTask(Intent data) {
        String name = data.getStringExtra("nazwa");
        String opis = data.getStringExtra("opis");
        tasks.add(new Task(name, opis));
        adapter.notifyItemInserted(tasks.size() - 1);
    }

    private void updateExistingTask(Intent data) {
        int position = data.getIntExtra("pozycja", -1);
        if (position != -1) {
            String name = data.getStringExtra("nazwa");
            String opis = data.getStringExtra("opis");
            tasks.get(position).setName(name);
            tasks.get(position).setOpis(opis);
            adapter.notifyItemChanged(position);
        }
    }
}
