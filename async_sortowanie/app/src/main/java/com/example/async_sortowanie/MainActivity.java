package com.example.async_sortowanie;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private EditText inputSize;
    private Button startButton;
    private ProgressBar progressBar;
    private SortingView sortingView;
    private ExecutorService executorService;
    private Handler uiHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
        initializeThreading();
        setButtonClickListener();
    }

    private void initializeViews() {
        inputSize = findViewById(R.id.inputSize);
        startButton = findViewById(R.id.startButton);
        progressBar = findViewById(R.id.progressBar);
        sortingView = findViewById(R.id.sortingView);
    }

    private void initializeThreading() {
        executorService = Executors.newSingleThreadExecutor();
        uiHandler = new Handler(Looper.getMainLooper());
    }

    private void setButtonClickListener() {
        startButton.setOnClickListener(v -> {
            String input = inputSize.getText().toString();
            if (validateInput(input)) {
                startSorting(Integer.parseInt(input));
            }
        });
    }

    private boolean validateInput(String input) {
        if (input.isEmpty()) {
            Toast.makeText(this, "Nie podałeś ilości elementów", Toast.LENGTH_SHORT).show();
            return false;
        }

        int size = Integer.parseInt(input);
        if (size <= 0) {
            Toast.makeText(this, "Liczba nie może być ujemna", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private void startSorting(int size) {
        int[] array = generateArray(size);

        progressBar.setProgress(0);
        sortingView.setArray(array);

        executorService.execute(() -> {
            performBubbleSort(array, size);
            notifySortingComplete(array);
        });
    }

    private int[] generateArray(int size) {
        int[] array = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(500) + 1;
        }
        return array;
    }

    private void performBubbleSort(int[] array, int size) {
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    swapElements(array, j, j + 1);
                }
            }

            updateProgress(array, size, i);
        }
    }

    private void swapElements(int[] array, int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    private void updateProgress(int[] array, int size, int currentIteration) {
        int progress = (int) (((currentIteration + 1) / (float) size) * 100);
        uiHandler.post(() -> {
            progressBar.setProgress(progress);
            sortingView.setArray(Arrays.copyOf(array, array.length));
        });

        sleepThread();
    }

    private void sleepThread() {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void notifySortingComplete(int[] array) {
        uiHandler.post(() -> {
            Toast.makeText(this, "Sortowanie zakończone!", Toast.LENGTH_SHORT).show();
            progressBar.setProgress(100);
            sortingView.setArray(array);
        });
    }
}
