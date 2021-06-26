package com.example.ap2_ex3.viewModel;

import com.example.ap2_ex3.model.Model;

public class ViewModel {
    public String ip;
    public String port;
    public int rudder, throttle;
    public float aileron, elevator;
    Model model;
    public ViewModel(){
        model = new Model(this);
    }
    public String getIp() {
        return ip;
    }

    public String getPort() {
        return port;
    }

    public float getRudder() {
        return rudder;
    }

    public float getThrottle() {
        return throttle;
    }

    public float getAileron() {
        return aileron;
    }

    public float getElevator() {
        return elevator;
    }

    public void connect(){
        model.connect();
    }

    public void setThrottle(){
        try{
            model.setThrottle();
        } catch (InterruptedException e){}
    }

    public void setAileron(){
        try{
            model.setAileron();
        } catch (InterruptedException e){}
    }

    public void setRudder(){
        try{
            model.setRudder();
        } catch (InterruptedException e){}
    }

    public void setElevator(){
        try{
            model.setThrottle();
        } catch (InterruptedException e){}
    }


}
