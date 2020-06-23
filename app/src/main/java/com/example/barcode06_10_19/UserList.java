package com.example.barcode06_10_19;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class UserList extends ArrayAdapter<User> {

    private Activity context;
    //list of users
    List<User> Users;


    public UserList(Activity context, List<User> Users) {
        super(context, R.layout.layout_user_list, Users);
        this.context = context;
        this.Users = Users;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_user_list, null, true);
        //initialize
        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textviewemail = (TextView) listViewItem.findViewById(R.id.textviewemail); //address
        TextView textviewnumber = (TextView) listViewItem.findViewById(R.id.textviewnumber);
        TextView textviewqrinfo = (TextView) listViewItem.findViewById(R.id.textviewqrinfo);
        TextView textviewpostingdate = (TextView) listViewItem.findViewById(R.id.textviewpostingdate);
        TextView textviewnote = (TextView) listViewItem.findViewById(R.id.textviewnote);

        //getting user at position
        User User = Users.get(position);
        //set user name
        textViewName.setText(User.getUsername());
        //set user address
        textviewemail.setText(User.getUseremail());
        //set user mobilenumber
        textviewnumber.setText(User.getUsermobileno());
        //set user qrinfo
        textviewqrinfo.setText(User.getQrinfo());
        //set user posting date
        textviewpostingdate.setText(User.getPostingdate());
        textviewnote.setText(User.getNote());

        return listViewItem;
    }
}