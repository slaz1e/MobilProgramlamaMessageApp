package com.example.chatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    //button edittext firebaseauth
    Button signup;
    EditText name,email,pass;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        signup=findViewById(R.id.registerButton);
        name=findViewById(R.id.registerName);
        email=findViewById(R.id.registerMail);
        pass=findViewById(R.id.registerPassword);
        firebaseAuth=FirebaseAuth.getInstance();
        //r.id referansları ve getInstance ile içerik çekme signup butonu
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signupClick();
            }
        });
    }
    public void signupClick(){
        String userName=name.getText().toString();
        String userMail=email.getText().toString();
        String userPass=pass.getText().toString();
        if(userName.isEmpty()){
            Toast.makeText(getApplicationContext(),"username can't be empty",Toast.LENGTH_SHORT).show();
        }
        if(userMail.isEmpty()){
            Toast.makeText(getApplicationContext(),"mail can't be empty",Toast.LENGTH_SHORT).show();
        }
        if(userPass.isEmpty()||userPass.length()<6){
            Toast.makeText(getApplicationContext(),"pass can't be empty",Toast.LENGTH_SHORT).show();
        }
        firebaseAuth.createUserWithEmailAndPassword(userMail,userPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Tools.showMessage("registered succesfull",getApplicationContext());
                }
                else{
                    Tools.showMessage("Register failed",getApplicationContext());
                }
            }
        });
    }
    //signup fonksiyonu
    //if stateler ile yanlışları engelleme mesaj gösterme

}