package com.example.chatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

public class Tools {
    //static bir context üretip showMessage metodunu oluşturma.
    public static Context context;
    public static void showMessage(String msg,Context c){
        Toast.makeText(c,msg,Toast.LENGTH_SHORT).show();
    }
}