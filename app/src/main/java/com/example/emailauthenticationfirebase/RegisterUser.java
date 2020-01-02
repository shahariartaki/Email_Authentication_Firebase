package com.example.emailauthenticationfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterUser extends AppCompatActivity {
    FirebaseAuth fauth;
    EditText username,email,password,phone;
    Button register;
    TextView loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        username=findViewById(R.id.ed_username);
        email=findViewById(R.id.ed_email);
        password=findViewById(R.id.ed_password);
        phone=findViewById(R.id.ed_phone);
        register=findViewById(R.id.register_btn);
        loginButton=findViewById(R.id.goto_login);
        fauth=FirebaseAuth.getInstance();

        if (fauth.getCurrentUser()!= null){
            Toast.makeText(RegisterUser.this, "User create Created", Toast.LENGTH_SHORT).show();
            finish();

        }


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // String r_name = username.getText().toString().trim();
                String r_email = email.getText().toString().trim();
                String r_pass = password.getText().toString().trim();
                //String r_phone = phone.getText().toString().trim();

                if (TextUtils.isEmpty(r_email)){
                    email.setError("Please Enter Email");
                    return;
                }

                if (TextUtils.isEmpty(r_pass)){
                    email.setError("Please Enter password");
                    return;
                }

                if (r_email.length()<6){
                    email.setError("Please Enter password 6 digit");
                    return;
                }

                fauth.createUserWithEmailAndPassword(r_email,r_pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegisterUser.this, "User create Created", Toast.LENGTH_SHORT).show();
                            startActivity( new Intent(getApplicationContext(),MainActivity.class));
                        }
                        else{
                            Toast.makeText(RegisterUser.this, "Error !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
