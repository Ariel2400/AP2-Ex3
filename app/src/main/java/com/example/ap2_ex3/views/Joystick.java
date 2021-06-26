package com.example.ap2_ex3.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

public class Joystick extends SurfaceView implements SurfaceHolder.Callback, View.OnTouchListener {
    public static float centerX, centerY, outerRadius, innerRadius;
    public static JoystickListener joystickCallback;


    public Joystick(Context context) {
        super(context);
        init(context);
    }

    public Joystick(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public Joystick(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public Joystick(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context){
        getHolder().addCallback(this);
        setOnTouchListener(this);
        if(context instanceof JoystickListener){
            joystickCallback = (JoystickListener)context;
        }
    }

    private void drawJoystick(float x, float y){
        if(getHolder().getSurface().isValid()){
            Canvas canvas =this.getHolder().lockCanvas();
            Paint color = new Paint();
            canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
            canvas.drawColor(Color.WHITE);
            color.setColor(Color.DKGRAY);
            canvas.drawCircle(centerX, centerY, outerRadius, color);
            color.setColor(Color.LTGRAY);
            canvas.drawCircle(x,y,innerRadius,color);
            getHolder().unlockCanvasAndPost(canvas);
        }
    }

    private void setDimensions(){
        centerX = getWidth()/2;
        centerY = getHeight()/2;
        int minDim = Math.min(getWidth(), getHeight());
        outerRadius = minDim/3;
        innerRadius = minDim /7;
    }
    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        setDimensions();
        drawJoystick(centerX, centerY);
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (v.equals(this)) {
            float maxRadius = (float) Math.sqrt(
                    Math.pow(
                            event.getX() - centerX,
                            2)
                            +
                            Math.pow(
                                    event.getY() - centerY,
                                    2)
            );
            if (event.getAction() != MotionEvent.ACTION_UP) {
                if (maxRadius < outerRadius) {
                    drawJoystick(event.getX(), event.getY());
                    joystickCallback.onJoystickMoved((event.getX()-centerX)/outerRadius,(event.getY()-centerY)/outerRadius, getId());
                    return true;
                }
                float ratio = outerRadius / maxRadius;
                float updatedX = centerX + (event.getX() - centerX) * ratio;
                float updatedY = centerY + (event.getY() - centerY) * ratio;
                drawJoystick(updatedX, updatedY);
                joystickCallback.onJoystickMoved((updatedX-centerX)/outerRadius,(updatedY-centerY)/outerRadius, getId());
                return true;
            }
            drawJoystick(centerX, centerY);
            joystickCallback.onJoystickMoved(0,0, getId());
            return true;
        }
        return true;
    }

    public interface JoystickListener{
        void onJoystickMoved(float d_x, float d_y, int source);
    }
}
