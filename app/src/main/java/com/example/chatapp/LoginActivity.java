package com.example.chatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

    public class LoginActivity extends AppCompatActivity {
        //Button,edittext,Firebase
        Button loginButton;
        EditText mail,pass;
        FirebaseAuth firebaseAuth;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);
            //Button ve text tanımlamaları,FirebaseAuth.getInstance!!
            loginButton=findViewById(R.id.loginLogin);
            mail=findViewById(R.id.loginMail);
            pass=findViewById(R.id.loginPass);
            firebaseAuth=FirebaseAuth.getInstance();
            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    loginUser();
                }
            });
        }
        //loginuser metodu geri dönen mesajlar
        public void loginUser(){
            String userMail=mail.getText().toString();
            String userPass=pass.getText().toString();
            if(userMail.isEmpty()){
                Toast.makeText(getApplicationContext() ,"user mail couldn't find",Toast.LENGTH_SHORT).show();
            }
            if(userPass.isEmpty()){
                Toast.makeText(getApplicationContext() ,"invalid password",Toast.LENGTH_SHORT).show();
            }
            firebaseAuth.signInWithEmailAndPassword(userMail,userPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Tools.showMessage("Welcome",getApplicationContext());
                        startActivity(new Intent(LoginActivity.this,MainActivity.class));
                    }
                    else{
                        Tools.showMessage("invalid user",getApplicationContext());
                    }
                }
            });
        }
        public void registerClick(View view){
            startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
        }
    }