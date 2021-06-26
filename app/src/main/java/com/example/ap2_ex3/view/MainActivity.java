package com.example.ap2_ex3.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

import com.example.ap2_ex3.R;
import com.example.ap2_ex3.viewModel.ViewModel;
import com.example.ap2_ex3.views.Joystick;

public class MainActivity extends AppCompatActivity implements Joystick.JoystickListener {

    SeekBar sbThrottle, sbRudder;
    Button bConnect;
    EditText etIp, etPort;
    ViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = new ViewModel();
        sbRudder = (SeekBar)findViewById(R.id.sbRudder);
        etIp = (EditText)findViewById(R.id.etIp);
        etPort = (EditText)findViewById(R.id.etPort);
        sbThrottle = (SeekBar)findViewById(R.id.sbThrottle) ;
        bConnect = (Button)findViewById(R.id.bConnect);
        bConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.connect();
            }
        });
        sbThrottle.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                viewModel.setThrottle();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        sbRudder.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
               viewModel.setRudder();
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
        viewModel.setAileron();
        viewModel.setElevator();
    }



}