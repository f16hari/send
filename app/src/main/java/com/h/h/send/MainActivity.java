package com.h.h.send;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button send;
    private Button receive;
    private TextView RmessageValue;
    private EditText messageValue;
    private String m;
    private DatabaseReference dbrf;
    private RecyclerView recyclerView;
    private String sn;
    private int l;
    private char[] a=new char[50];
    public String string;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent=getIntent();
        String name=intent.getStringExtra("sub");
        sn=intent.getStringExtra("sname");


        send=(Button)findViewById(R.id.sendButton);
        messageValue=(EditText)findViewById(R.id.messageV);


        recyclerView=(RecyclerView)findViewById(R.id.messageList);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
       /* recyclerView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View view, int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7) {
                if(i3<i7)
                {
                    recyclerView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            recyclerView.smoothScrollToPosition(recyclerView.getAdapter().getItemCount()-1);

                        }
                    },0);
                }
            }
        });*/


        dbrf= FirebaseDatabase.getInstance().getReference().child(name);




        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int zzz;
                zzz=sn.length();

                m=zzz+sn+messageValue.getText().toString();
                DatabaseReference newPost=dbrf.push();
                newPost.setValue(m);

            }
        });

        final List<String> mlist=new ArrayList<>();
        dbrf.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String ss=null;
                a=null;
                l=0;
                final List<String> mlist=new ArrayList<>();
                for(DataSnapshot post:dataSnapshot.getChildren())
                {
                     ss=post.getValue(String.class);
                     a=ss.toCharArray();
                     l=ss.length();
                     int x= Character.getNumericValue(a[0]);

                     ss=new String(a,x+1,l-(x+1));
                     string=new String(a,1,x);

                    if(string.equals(sn))
                        string="t";
                    else
                        string="f";

                    ss=ss+string;

                    mlist.add(ss);

                }

               adapterclass a=new adapterclass(mlist);
               recyclerView.setAdapter(a);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
      /* dbrf.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String string=dataSnapshot.getValue(String.class);
                System.out.println("Message is "+string);

                RmessageValue.setText(string);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/

    }
}
