package com.example.ap2_ex3.viewModel;

import android.util.Log;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.ap2_ex3.BR;
import com.example.ap2_ex3.model.Model;

public class ViewModel extends BaseObservable {
    public String ip;
    public String port;
    public int rudder, throttle;
    public float aileron, elevator;
    Model model;
    public ViewModel(){
        model = new Model(this);
    }
    @Bindable
    public String getIp() {
        return ip;
    }

    public void setIp(String value){
        if(ip != value){
            ip = value;
            notifyPropertyChanged(BR.ip);
        }
    }
    @Bindable
    public String getPort() {
        return port;
    }

    public void setPort(String value){
        if(port != value){
            port = value;
            notifyPropertyChanged(BR.port);
        }
    }
    @Bindable
    public float getRudder() {
        return (rudder-50)/100;
    }
    @Bindable
    public float getThrottle() {
        return throttle/100;
    }
    @Bindable
    public float getAileron() {
        return aileron;
    }
    @Bindable
    public float getElevator() {
        return elevator;
    }

    public void connect(){
        model.connect();
    }

    public void setThrottle(int progress){
        if(throttle != progress){
            throttle = progress;
            try{
                model.setThrottle();
            } catch (InterruptedException e){}
            notifyPropertyChanged(BR.throttle);
        }

    }

    public void setAileron(float value){
        if(aileron != value){
            aileron = value;
            try{
                model.setAileron();
            } catch (InterruptedException e){}
            notifyPropertyChanged(BR.aileron);
        }
    }

    public void setRudder(int value){
        if(rudder != value){
            rudder = value;
            try{
                model.setRudder();
            } catch (InterruptedException e){}
            notifyPropertyChanged(BR.rudder);
        }
    }

    public void setElevator(float value){
        if(elevator != value){
            elevator = value;
            try{
                model.setElevator();
            } catch (InterruptedException e){}
            notifyPropertyChanged(BR.elevator);
        }
    }


}
