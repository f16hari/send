package com.h.h.send;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        send=(Button)findViewById(R.id.sendButton);
        messageValue=(EditText)findViewById(R.id.messageV);

        //receive=(Button)findViewById(R.id.receiveButton);
        /*RmessageValue=(TextView)findViewById(R.id.Rmessage);*/


        recyclerView=(RecyclerView)findViewById(R.id.messageList);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);


        dbrf= FirebaseDatabase.getInstance().getReference().child("message");




        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                m=messageValue.getText().toString();
                DatabaseReference newPost=dbrf.push();
                newPost.setValue(m);

            }
        });

        final List<String> mlist=new ArrayList<>();
        dbrf.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot post:dataSnapshot.getChildren())
                {
                    String ss=post.getValue(String.class);
                    mlist.add(ss);
                }
               /* HashMap<Integer,String> hm=(HashMap<Integer, String>)dataSnapshot.getValue();
                ArrayList<String> arrayList= new ArrayList<String>(hm.values());
                for (String s : arrayList) {
                    System.out.println("Adapter data is "+s);
                }*/
                recyclerView.setAdapter(new adapterclass(mlist));

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
