package com.example.ap2_ex3.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

import com.example.ap2_ex3.R;
import com.example.ap2_ex3.views.Joystick;

public class MainActivity extends AppCompatActivity implements Joystick.JoystickListener {

    SeekBar sbThrottle, sbAileron;
    Button bConnect;
    EditText etIp, etPort;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sbAileron = (SeekBar)findViewById(R.id.sbAileron);
        etIp = (EditText)findViewById(R.id.etIp);
        etPort = (EditText)findViewById(R.id.etPort);
        sbThrottle = (SeekBar)findViewById(R.id.sbThrottle) ;
        bConnect = (Button)findViewById(R.id.bConnect);
        bConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("bConnect", "Pressed");
            }
        });
        sbThrottle.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.d("Throttle", "Progress: "+progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        sbAileron.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.d("Aileron", "Progress: "+progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public void onJoystickMoved(float d_x, float d_y, int source) {
        Log.d("Joystick", "X pos: "+d_x+", Y pos: "+d_y);
    }



}