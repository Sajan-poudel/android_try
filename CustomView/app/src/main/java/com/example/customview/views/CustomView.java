package com.example.customview.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.Random;

public class CustomView extends View {

    Paint paint;
    private int fixColor = Color.BLACK;
    private int faceColor = Color.YELLOW;
    private float borderWidth = 4.0f;
    private int size = 550;


    public CustomView(Context context) {
        super(context);
        init(null);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    public void changeColor(){
        Random random = new Random();
        faceColor = Color.argb(255, random.nextInt(250), random.nextInt(250), random.nextInt(250));
        postInvalidate();
    }

    private void  init(@Nullable AttributeSet set){
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawFaceBG(canvas);
        drawEyes(canvas);
        drawMouth(canvas);
    }

    private void drawFaceBG(Canvas canvas){
        paint.setColor(faceColor);
        paint.setStyle(Paint.Style.FILL);

        float radius = size/2.0f;

        canvas.drawCircle(radius, radius, radius, paint);

        paint.setColor(fixColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(borderWidth);

        canvas.drawCircle(radius, radius, radius - borderWidth/2.0f, paint);
    }
    private void drawEyes(Canvas canvas){
        paint.setColor(fixColor);
        paint.setStyle(Paint.Style.FILL);

        RectF leftEye = new RectF(size*0.32f, size * 0.23f, size*0.43f, size*0.5f);
        canvas.drawOval(leftEye, paint);

        RectF rightEye = new RectF(size*0.57f, size * 0.23f, size*0.68f, size*0.5f);
        canvas.drawOval(rightEye, paint);

    }
    private void drawMouth(Canvas canvas){
        Path mouthPath = new Path();
        mouthPath.moveTo(size * .22f, size * .7f);
        mouthPath.quadTo(size * .5f, size * .8f, size * .78f, size * .7f);
        mouthPath.quadTo(size * .5f, size * .9f, size * .22f, size * .7f);

        paint.setColor(fixColor);
        paint.setStyle(Paint.Style.FILL);

        canvas.drawPath(mouthPath, paint);
    }

}
