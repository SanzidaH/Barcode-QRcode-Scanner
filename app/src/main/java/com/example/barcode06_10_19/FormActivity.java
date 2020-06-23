package com.example.barcode06_10_19;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FormActivity extends AppCompatActivity {

    //initialize
    public TextView qrtext2;
    EditText editTextName, editTextEmail, editTextNumber, editTextNote;
    Button buttonAddUser;
    ListView listViewUsers;
    //a list to store all the User from firebase database
    List<User> Users;
    //    FirebaseDatabase database = FirebaseDatabase.getInstance();
//    DatabaseReference mDatabase = database.getReference();
    public static final String Database_Path = "customers";
    private DatabaseReference custDatabase = FirebaseDatabase.getInstance().getReference(Database_Path);
    private String qr, date;
    // Root Database Name for Firebase Database.

    //  DatabaseReference databaseReference;
    //  private DatabaseReference mDatabase;
    //  mDatabase = FirebaseDatabase.getInstance().getReference();

    private String getPostingDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String strDate = dateFormat.format(date).toString();
        return strDate;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
// method for find ids of views
        findViews();

// to maintian click listner of views
        initListner();

//get qrinfo,date from ScannedBarcodeActivity

        Intent secondIntent = getIntent();
        qr = secondIntent.getStringExtra("QRinfo");
        Log.d("QRinfo", qr);
        //date = secondIntent.getStringExtra("postingdate");
        //Log.d("postingdate", date);
        date = getPostingDate();
        //Toast.makeText(this, qr + "  " + date, Toast.LENGTH_LONG).show();
    }


    private void findViews() {
//getRefrance for user table

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextNumber = (EditText) findViewById(R.id.editTextNumber);
        editTextNote = (EditText) findViewById(R.id.editTextNote);
        listViewUsers = (ListView) findViewById(R.id.listViewUsers);
        buttonAddUser = (Button) findViewById(R.id.buttonAddUser);
        qrtext2 = (TextView) findViewById(R.id.qrtext2);
        qrtext2.setText(getIntent().getStringExtra("QRinfo"));
//list for store objects of user
        Users = new ArrayList<>();
    }

    private void initListner() {
//adding an onclicklistener to button
        buttonAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//calling the method addUser()
//the method is defined below
//this method is actually performing the write operation
                addUser();
            }
        });


// list item click listener
//        listViewUsers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                User User = Users.get(i);
//                CallUpdateAndDeleteDialog(User.getUserid(), User.getUsername(), User.getUseremail(), User.getUsermobileno());
//            }
//        });
//    }
//    @Override
//    protected void onStart() {
//        super.onStart();
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
////clearing the previous User list
//                Users.clear();
////getting all nodes
//                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
////getting User from firebase console
//                    User User = postSnapshot.getValue(User.class);
////adding User to the list
//                    Users.add(User);
//                }
////creating Userlist adapter
//                UserList UserAdapter = new UserList(FormActivity.this, Users);
////attaching adapter to the listview
//                listViewUsers.setAdapter(UserAdapter);
//            }
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//    }
//    private void CallUpdateAndDeleteDialog(final String userid, String username, final String email, String monumber) {
//
//        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
//        LayoutInflater inflater = getLayoutInflater();
//        final View dialogView = inflater.inflate(R.layout.update_dialog, null);
//        dialogBuilder.setView(dialogView);
////Access Dialog views
//        final EditText updateTextname = (EditText) dialogView.findViewById(R.id.updateTextname);
//        final EditText updateTextemail = (EditText) dialogView.findViewById(R.id.updateTextemail);
//        final EditText updateTextmobileno = (EditText) dialogView.findViewById(R.id.updateTextmobileno);
//        updateTextname.setText(username);
//        updateTextemail.setText(email);
//        updateTextmobileno.setText(monumber);
//        final Button buttonUpdate = (Button) dialogView.findViewById(R.id.buttonUpdateUser);
//        final Button buttonDelete = (Button) dialogView.findViewById(R.id.buttonDeleteUser);
////username for set dialog title
//        dialogBuilder.setTitle(username);
//        final AlertDialog b = dialogBuilder.create();
//        b.show();
//
//// Click listener for Update data
//        buttonUpdate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String name = updateTextname.getText().toString().trim();
//                String email = updateTextemail.getText().toString().trim();
//                String mobilenumber = updateTextmobileno.getText().toString().trim();
////checking if the value is provided or not Here, you can Add More Validation as you required
//
//                if (!TextUtils.isEmpty(name)) {
//                    if (!TextUtils.isEmpty(email)) {
//                        if (!TextUtils.isEmpty(mobilenumber)) {
////Method for update data
//                            updateUser(userid, name, email, mobilenumber, qrinfo, postingdate);
//                            b.dismiss();
//                        }
//                    }
//                }
//
//            }
//        });

// Click listener for Delete data
//        buttonDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////Method for delete data
//                deleteUser(userid);
//                b.dismiss();
//
//
//            }
//        });
//    }
//
//    private boolean updateUser(String id, String name, String email, String mobilenumber, String qrinfo, String postingdate) {
////getting the specified User reference
//        DatabaseReference UpdateReference = FirebaseDatabase.getInstance().getReference("Users").child(id);
//        User User = new User(id, name, email, mobilenumber, qrinfo, postingdate);
////update User to firebase
//        UpdateReference.setValue(User);
//        Toast.makeText(getApplicationContext(), "User Updated", Toast.LENGTH_LONG).show();
//        return true;
//    }
//
//    private boolean deleteUser(String id) {
////getting the specified User reference
//        DatabaseReference DeleteReference = FirebaseDatabase.getInstance().getReference("Users").child(id);
////removing User
//        DeleteReference.removeValue();
//        Toast.makeText(getApplicationContext(), "User Deleted", Toast.LENGTH_LONG).show();
//        return true;
    }


    private void addUser() {

//getting the values to save
        String name = editTextName.getText().toString().trim();
        String address = editTextEmail.getText().toString().trim();
        String mobilenumber = editTextNumber.getText().toString().trim();
        String note = editTextNote.getText().toString().trim();
        String qrinfo = getIntent().getStringExtra("QRinfo");
        String postingdate = getPostingDate();

//checking if the value is provided or not Here, you can Add More Validation as you required
        if (!TextUtils.isEmpty(name)) {
            if (!TextUtils.isEmpty(address)) {
                if (!TextUtils.isEmpty(mobilenumber)) {

                    String userId = custDatabase.push().getKey();

// creating user object
                    User user = new User(userId, name, address, mobilenumber, qrinfo, postingdate, note);
                  //  startActivity(new Intent(FormActivity.this, ScannedBarcodeActivity.class));
// pushing user to 'users' node using the userId
                    custDatabase.child(userId).setValue(user);
                    editTextName.setText("");
                    editTextNumber.setText("");
                    editTextEmail.setText("");
                    qrtext2.setText("");
                    editTextNote.setText("");
                    finish();

                    Toast.makeText(this, "Sent to support team successfully", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Please enter a contact number", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this, "Please enter a address", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Please enter a name", Toast.LENGTH_LONG).show();
        }
    }
}
