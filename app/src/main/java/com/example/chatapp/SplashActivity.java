package com.example.chatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class SplashActivity extends AppCompatActivity {
    //firebaseauth ve içerik çekme
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        firebaseAuth=FirebaseAuth.getInstance();
        Thread wait3sec =new Thread(){
            public void run(){
                try{
                    sleep(3000);
                    startActivity(new Intent(SplashActivity.this,MainActivity.class));
                    finish();
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        },
                wait3sec2=new Thread(){
                    public void run(){
                        try{
                            sleep(3000);
                            startActivity(new Intent(SplashActivity.this,LoginActivity.class));
                            finish();
                        }
                        catch(InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                };
        if(firebaseAuth.getCurrentUser()!=null){
            wait3sec.start();
        }
        else{
            wait3sec2.start();
        }
    }
    public void loginClick(View view){
        startActivity(new Intent(SplashActivity.this,LoginActivity.class));
    }
    public void registerClick(View view){
        startActivity(new Intent(SplashActivity.this,RegisterActivity.class));
    }
}