package com.h.h.send;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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

public class loginActivity extends AppCompatActivity {

    private EditText pass;
    private EditText uname;
    private EditText ssname;
    private Button sin;
    private TextView sup;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        uname=(EditText)findViewById(R.id.username);
        pass=(EditText)findViewById(R.id.password);
        ssname=(EditText)findViewById(R.id.sname);
        sin=(Button)findViewById(R.id.signin);

        sup=(TextView)findViewById(R.id.signup);

        mAuth=FirebaseAuth.getInstance();

        mAuthListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if (firebaseAuth.getCurrentUser()!=null)
                {

                    String sss=uname.getText().toString();
                    String parts[]=sss.split("@");

                    String sn=ssname.getText().toString();

                    startActivity(new Intent(loginActivity.this,MainActivity.class).putExtra("sub",parts[0]).putExtra("sname",sn));
                }

            }
        };

        sin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startsignin();
            }
        });
        sup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(loginActivity.this,loginSignup.class));

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
            Toast.makeText(loginActivity.this,"Login error",Toast.LENGTH_SHORT).show();
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
