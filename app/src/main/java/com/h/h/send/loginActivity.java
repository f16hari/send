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

public class loginActivity extends AppCompatActivity {

    private EditText pass;
    private EditText uname;
    private Button sin;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        uname=(EditText)findViewById(R.id.username);
        pass=(EditText)findViewById(R.id.password);
        sin=(Button)findViewById(R.id.signin);

        mAuth=FirebaseAuth.getInstance();

        mAuthListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if (firebaseAuth.getCurrentUser()!=null)
                {
                    startActivity(new Intent(loginActivity.this,MainActivity.class));
                }

            }
        };

        sin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startsignin();
            }
        });
    }

    @Override
    protected void onStart() {

        super.onStart();

        mAuth.addAuthStateListener(mAuthListener);
    }

    private void startsignin()
    {
        final String e=uname.getText().toString();
        final String u=pass.getText().toString();

        if(TextUtils.isEmpty(e)||TextUtils.isEmpty(u))
        {
            Toast.makeText(loginActivity.this,"Login sucess",Toast.LENGTH_SHORT).show();
        }
        else {

            mAuth.signInWithEmailAndPassword(e, u).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(!task.isSuccessful())
                    {
                        Toast.makeText(loginActivity.this,"Login error",Toast.LENGTH_SHORT).show();
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