package com.example.tworzenie_bitmapy;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Bitmap bmp;
    private Canvas canvas;
    private Paint paint;
    private ImageView imageView;
    private Random random;

    boolean stworzono = false;
    boolean czy_stworzono_obiekt = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        imageView = findViewById(R.id.imageView);
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(5);
        random = new Random();
    }

    public void kliknieto_kreuj(View v){
        if (!stworzono){
            int szer = imageView.getWidth();
            int wys = imageView.getHeight();
            bmp = Bitmap.createBitmap(szer, wys, Bitmap.Config.ARGB_8888);
            canvas = new Canvas(bmp);
            canvas.drawColor(Color.WHITE);
            imageView.setImageBitmap(bmp);
            stworzono=true;
        }
    }

    public void kliknieto_czysc(View v){
        stworzono=false;
        czy_stworzono_obiekt=false;
        canvas.drawColor(Color.WHITE);
        imageView.invalidate();
    }

    public void klikniecie_lini(View v){
        if (stworzono && czy_stworzono_obiekt==false){
            for (int i = 0; i < 20; i++) {
                int startX = random.nextInt(bmp.getWidth());
                int startY = random.nextInt(bmp.getHeight());
                int stopX = random.nextInt(bmp.getWidth());
                int stopY = random.nextInt(bmp.getHeight());
                paint.setColor(Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
                canvas.drawLine(startX, startY, stopX, stopY, paint);
            }
            stworzono=true;
            czy_stworzono_obiekt=true;
            imageView.invalidate();
        }
    }

    public void kliknieto_elipsy(View v){
        if (stworzono && czy_stworzono_obiekt==false){
            stworzono=true;
            for (int i = 0; i < 20; i++) {
                int left = random.nextInt(bmp.getWidth()/2);
                int top = random.nextInt(bmp.getHeight()/2);
                int right = left + random.nextInt(bmp.getWidth() - left);
                int bottom = top + random.nextInt(bmp.getHeight() - top);
                paint.setColor(Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
                canvas.drawOval(left, top, right, bottom, paint);
            }
            czy_stworzono_obiekt=true;
            imageView.invalidate();
        }
    }

    public void kliknieto_prostakoty(View v){
        if (stworzono && czy_stworzono_obiekt==false){
            stworzono=true;
            for (int i = 0; i < 20; i++) {
                int left = random.nextInt(bmp.getWidth()/2);
                int top = random.nextInt(bmp.getHeight()/2);
                int right = left + random.nextInt(bmp.getWidth() - left);
                int bottom = top + random.nextInt(bmp.getHeight() - top);
                paint.setColor(Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
                canvas.drawRect(left, top, right, bottom, paint);
            }
            czy_stworzono_obiekt=true;
            imageView.invalidate();
        }
    }
}
