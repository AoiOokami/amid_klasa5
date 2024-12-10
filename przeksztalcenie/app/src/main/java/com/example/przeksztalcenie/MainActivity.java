package com.example.przeksztalcenie;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.LruCache;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public ImageView imageView;
    public TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        setupWindowInsets();
        initializeViews();
    }

    private void setupWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void initializeViews() {
        Button button_1 = findViewById(R.id.button);
        Button button_2 = findViewById(R.id.button2);
        textView = findViewById(R.id.textView);
        imageView = findViewById(R.id.imageView);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        setupButtonListeners(button_1, button_2);
    }

    private void setupButtonListeners(Button button_1, Button button_2) {
        button_1.setOnClickListener(view -> imageView.setImageResource(R.drawable.karas));

        button_2.setOnClickListener(view -> processImageViewBitmap());
    }

    private void processImageViewBitmap() {
        Drawable drawable = imageView.getDrawable();
        if (drawable instanceof BitmapDrawable) {
            Bitmap originalBitmap = ((BitmapDrawable) drawable).getBitmap();
            Bitmap randomizedBitmap = randomizeBitmapPixels(originalBitmap);

            imageView.setImageBitmap(randomizedBitmap);
            displayBitmapInfo(randomizedBitmap);
        }
    }

    private void displayBitmapInfo(Bitmap randomizedBitmap) {
        String info = "szerokosc: " + randomizedBitmap.getWidth() +
                " wysokosc: " + randomizedBitmap.getHeight() +
                " pixele: " + (randomizedBitmap.getWidth() * randomizedBitmap.getHeight());
        textView.setText(info);
    }

    private Bitmap randomizeBitmapPixels(Bitmap originalBitmap) {
        int width = originalBitmap.getWidth();
        int height = originalBitmap.getHeight();
        Bitmap newBitmap = Bitmap.createBitmap(width, height, originalBitmap.getConfig());
        Random random = new Random();
        int max_roznica = 5;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int pixelColor = originalBitmap.getPixel(x, y);
                int newX = Math.min(Math.max(x + random.nextInt(2 * max_roznica + 1) - max_roznica, 0), width - 1);
                int newY = Math.min(Math.max(y + random.nextInt(2 * max_roznica + 1) - max_roznica, 0), height - 1);

                newBitmap.setPixel(newX, newY, pixelColor);
            }
        }
        return newBitmap;
    }
}
