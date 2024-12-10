package com.example.async_sortowanie;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class SortingView extends View {

    private int[] array = new int[0];
    private Paint paint = new Paint();

    public SortingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint.setColor(Color.BLUE);
    }

    public void setArray(int[] array) {
        this.array = array;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (array == null || array.length == 0) return;

        int viewWidth = getWidth();
        int viewHeight = getHeight();
        float barWidth = (float) viewWidth / array.length;

        for (int i = 0; i < array.length; i++) {
            float barHeight = (array[i] / 500.0f) * viewHeight;
            float left = i * barWidth;
            float top = viewHeight - barHeight;
            float right = left + barWidth;
            float bottom = viewHeight;

            canvas.drawRect(left, top, right, bottom, paint);
        }
    }
}
