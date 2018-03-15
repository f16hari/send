package com.h.h.send;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginSignup extends AppCompatActivity {

    private EditText uname;
    private EditText pass;
    private Button sup;

    private FirebaseAuth mAuth;

    String u;
    String p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_signup);

        uname=(EditText)findViewById(R.id.upusername);
        pass=(EditText)findViewById(R.id.uppassword);

        sup=(Button)findViewById(R.id.upsignup);

        mAuth=FirebaseAuth.getInstance();

        sup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setNewUser();
            }
        });


    }

    public void setNewUser()
    {
        u=uname.getText().toString();
        p=pass.getText().toString();

        if(TextUtils.isEmpty(u)||TextUtils.isEmpty(p))
        {
            Toast.makeText(loginSignup.this,"Login error",Toast.LENGTH_SHORT).show();
        }
        else {

                mAuth.createUserWithEmailAndPassword(u,p).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(!task.isSuccessful())
                        {
                            Toast.makeText(loginSignup.this,"Registration error",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(loginSignup.this,"Registration sucess",Toast.LENGTH_SHORT).show();
                            FirebaseAuth.getInstance().signOut();
                            startActivity(new Intent(loginSignup.this,loginActivity.class));
                        }
                    }
                });
        }
    }
    @Override
    public void onStop() {

        super.onStop();
        FirebaseAuth.getInstance().signOut();
    }
}
