package com.example.barcode06_10_19;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewActivity extends AppCompatActivity {

    //our database reference object
    Button addadmin, logout;
    DatabaseReference databaseUsers;
    ListView listViewUsers;
    private ValueEventListener value_event_listener;
    //firebase auth object
    private FirebaseAuth firebaseAuth;
    //a list to store all the artist from firebase database
    ProgressDialog progressDialog;

    private List<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        databaseUsers = FirebaseDatabase.getInstance().getReference(FormActivity.Database_Path);
        listViewUsers = (ListView) findViewById(R.id.listViewUsers);
        //list to store artists
        users = new ArrayList<>();
        //initializing firebase authentication object
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(ViewActivity.this);
        progressDialog.setMessage("Loading Data from Database");
        progressDialog.show();


        //if the user is not logged in
        //that means current user will return null
        if(firebaseAuth.getCurrentUser() == null){
            //closing this activity
            finish();
            //starting login activity
            startActivity(new Intent(this, LoginActivity.class));
        }

        addadmin = findViewById(R.id.addadmin);
        logout = findViewById(R.id.logout);
        initListner();
    }

    private void initListner() {

        addadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ViewActivity.this, AddAdminActivity.class));
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //logging out the user
                firebaseAuth.signOut();
                //closing activity
                finish();
                //starting login activity
                startActivity(new Intent(ViewActivity.this, LoginActivity.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        //attaching value event listener
        databaseUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //clearing the previous artist list
//                if(!users.isEmpty()){
//                    users.clear();
//                }


                //iterating through all the nodes
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting artist
                    User user = postSnapshot.getValue(User.class);
                    //adding artist to the list
                    users.add(user);
                }

                //creating adapter
                UserList userAdapter = new UserList(ViewActivity.this, users);
                //attaching adapter to the listview
                listViewUsers.setAdapter(userAdapter);
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                progressDialog.dismiss();
            }
        });
    }


}