package com.example.ap2_ex3.model;

import com.example.ap2_ex3.viewModel.ViewModel;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Model {
    ViewModel mViewModel;
    PrintWriter out;
    Socket socket;
    BlockingQueue<Runnable> dispatchQueue = new LinkedBlockingQueue<>();


    public Model(ViewModel viewModel){
        mViewModel = viewModel;
    }

    public void connect()  {
        try{
            socket = new Socket(mViewModel.getIp(),Integer.parseInt(mViewModel.getPort()));
            out = new PrintWriter(socket.getOutputStream(),true);
            new Thread(() -> {
                while(true){
                    try{
                        dispatchQueue.take().run();
                    }catch (InterruptedException e){}
                }
            }).start();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setThrottle() throws InterruptedException {
        dispatchQueue.put(() -> out.print("set /controls/engines/current-engine/throttle" + mViewModel.getThrottle()));
    }

    public void setAileron() throws InterruptedException{
        dispatchQueue.put(() -> out.print("set /controls/flight/aileron" + mViewModel.getAileron()));
    }

    public void setElevator() throws InterruptedException{
        dispatchQueue.put(() -> out.print("set /controls/flight/elevator" + mViewModel.getElevator()));
    }

    public void setRudder() throws InterruptedException{
        dispatchQueue.put(() -> out.print("set /controls/flight/rudder" + mViewModel.getRudder()));
    }
}
