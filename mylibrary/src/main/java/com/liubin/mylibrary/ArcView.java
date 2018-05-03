package com.liubin.mylibrary;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by liubin on 2017/12/16.
 */

public class ArcView extends View {
    private Context context;
    private int width;
    private int height;
    private int dx;
    private int dy;
    private int radius;
    private int angle;
    private int backColor;

    public ArcView(Context context) {
        this(context, null);
    }

    public ArcView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ArcView);
        if (typedArray != null) {
            angle = typedArray.getInt(R.styleable.ArcView_angle, 30);
            backColor = typedArray.getColor(R.styleable.ArcView_backColor, 0x000000);
            typedArray.recycle();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getMeasure(200, widthMeasureSpec);
        height = getMeasure(200, heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    private int getMeasure(int defalut, int measure) {
        int result = defalut;
        int mode = MeasureSpec.getMode(measure);
        int size = MeasureSpec.getSize(measure);
        if (mode == MeasureSpec.EXACTLY) {
            result = size;
        } else if (mode == MeasureSpec.AT_MOST) {
            result = Math.min(defalut, size);
        }
        return result;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        dx = (left + right) / 2;
        dy = (int) (top + (right - left) / 2 / Math.sin(angle));
        radius = (int) ((right - left) / 2 / Math.sin(angle));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(backColor);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(dx, dy, radius, paint);
    }
}
