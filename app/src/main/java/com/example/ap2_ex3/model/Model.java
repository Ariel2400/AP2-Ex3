package com.example.ap2_ex3.model;

import com.example.ap2_ex3.viewModel.ViewModel;

import java.net.Socket;

public class Model {
    ViewModel mViewModel;
    Socket socket;

    public Model(ViewModel viewModel){
        mViewModel = viewModel;
    }

    public void connect(){
        socket = new Socket()
    }
}
