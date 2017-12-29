package com.dash.a03_custom_view_02.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.dash.a03_custom_view_02.R;

/**
 * Created by Dash on 2017/12/29.
 */
public class SwitchButton extends View {

    private Bitmap backBitmap;
    private Bitmap slideBitmap;
    private boolean state = false;
    private boolean isTouching = false;
    private float x;

    public SwitchButton(Context context) {
        super(context);
    }

    public SwitchButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SwitchButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //使用代码指定这个自定义控件的宽度和高度....背景图片的宽度和高度
        setMeasuredDimension(backBitmap.getWidth(),backBitmap.getHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //画背景图片
        canvas.drawBitmap(backBitmap,0,0,null);


        //如果现在处于触摸的状态,需要根据触摸来画这个滑块
        //否则根据状态画
        if (isTouching){
            float left = x - slideBitmap.getWidth()/2;

            //限制left的范围
            if (left < 0 ){
                left = 0;
            }else if (left > backBitmap.getWidth() - slideBitmap.getWidth()){
                left = backBitmap.getWidth() - slideBitmap.getWidth();
            }

            canvas.drawBitmap(slideBitmap,left,0,null);

        }else {
            //根据开关的状态画滑块
            if (state){
                canvas.drawBitmap(slideBitmap,backBitmap.getWidth()-slideBitmap.getWidth(),0,null);
            }else {
                canvas.drawBitmap(slideBitmap,0,0,null);
            }
        }



    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                isTouching = true;

                x = event.getX();

                break;
            case MotionEvent.ACTION_MOVE:
                isTouching = true;

                x = event.getX();

                break;

            case MotionEvent.ACTION_UP:
                isTouching = false;

                x = event.getX();

                //根据x所处的位置,确定开关的状态
                state = x > backBitmap.getWidth()/2;
                break;
        }

        //重新绘制
        postInvalidate();

        return true;
    }

    /**
     * 对外提供设置背景图片的方法
     */
    public void setBackImage(int backResource){//R.drawable.a

        //把资源文件下的图片转成bitmap
        backBitmap = BitmapFactory.decodeResource(getResources(), backResource);
    }
    /**
     * 设置滑块图片
     */
    public void setSlideImage(int slideImageResource){//R.drawable.a

        //把资源文件下的图片转成bitmap
        slideBitmap = BitmapFactory.decodeResource(getResources(), slideImageResource);
    }

    /**
     * 设置开关的状态
     */
    public void setState(boolean state){
        this.state = state;
    }
}
