package com.example.tr534.wynews.splash;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.tr534.wynews.R;

/**
 * Created by tr534 on 2018/12/20.
 */

public class TimeView extends View {

    public TextPaint textPaint;
    String content="跳过";
    int padding=10;
    //内直径
    int inner;

    //外直径
    int outter;
    public Paint innerPaint;
    Paint outPaint;
    RectF outerRectF;
    int degree;


    public TimeView(Context context) {
        super(context);
    }
    OnTimeClickListener mListener;
    public void setmListener(OnTimeClickListener listener){
        this.mListener=listener;

    }

    public TimeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TimeView);
        int innerColor = typedArray.getColor(R.styleable.TimeView_innerColor, Color.BLACK);
        int outerColor = typedArray.getColor(R.styleable.TimeView_outerColor, Color.BLACK);
        //内圈
        innerPaint = new Paint();
        innerPaint.setAntiAlias(true);
        innerPaint.setColor(innerColor);
        //外圈
        outPaint=new Paint();
        outPaint.setAntiAlias(true);
        outPaint.setColor(outerColor);
        outPaint.setStyle(Paint.Style.STROKE);
        outPaint.setStrokeWidth(padding);
        textPaint = new TextPaint();
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(50);
        textPaint.setColor(Color.WHITE);
        float text_width = textPaint.measureText(content);
        //内圈直径
        inner= (int) (text_width+2*padding);

        //外圈直径
        outter=inner+2*padding;

        outerRectF=new RectF(padding/2,padding/2,outter-padding/2,outter-padding/2);
        typedArray.recycle();

    }
//    public  void  setDegree(int d)
//    {
//        this.degree=d;
//    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //文字的宽度+内圆边距*2+画笔的宽度*2

        setMeasuredDimension(outter,outter);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawCircle(outter/2,outter/2,inner/2,innerPaint);
        canvas.save();
        canvas.rotate(-90,outter/2,outter/2);
        canvas.drawArc(outerRectF,0f,degree,false,outPaint);
        canvas.restore();
        float y = canvas.getHeight() / 2;
        float descent = textPaint.descent();
        float ascent = textPaint.ascent();
        canvas.drawText(content,2*padding,y-(descent+ascent)/2,textPaint);


    }
    public  void  setProgress(int total,int now){
        int space=360/total;
        degree=space*now;
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                setAlpha(0.3f);
                break;
            case MotionEvent.ACTION_UP:
                setAlpha(1.0f);
                if (mListener!=null)
                {
                    mListener.onClickTime(this);
                }
                break;
        }
        return true;
    }
}
