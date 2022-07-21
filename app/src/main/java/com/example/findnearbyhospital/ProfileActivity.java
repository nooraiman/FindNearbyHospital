package com.example.findnearbyhospital;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

    Button submit,home;
    EditText e1, e2, e3;

    String name,phone,email, keys;
    int option=0;

    DatabaseReference db = FirebaseDatabase.getInstance("https://findnearby-823cd-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("users");
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_profile);

        e1 = (EditText)findViewById(R.id.profileName);
        e2 = (EditText)findViewById(R.id.profileEmail);
        e3 = (EditText)findViewById(R.id.profilePhone);
        submit = (Button) findViewById(R.id.btnUpdate);

        email = user.getEmail();
        e2 = (EditText)findViewById(R.id.profileEmail);
        e2.setText(user.getEmail());

        home = (Button) findViewById(R.id.btnHome);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDashboard();
            }
        });


        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                for (DataSnapshot keyID : datasnapshot.getChildren() ){

                    if(keyID.child("email").getValue().toString().equals(email) ){
                        name = keyID.child("name").getValue().toString();
                        phone = keyID.child("phone").getValue().toString();
                        option=1;
                    }
                }
                e1.setText(name);
                e3.setText(phone);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(option == 0 ){
                    insertUser();
                } else if(option == 1) {
                    updateUser();
                }
            }
        });
    }

    public void openDashboard(){
        Intent i = new Intent(this, DashboardActivity.class);
        startActivity(i);
    }

    public void insertUser(){
        String name = e1.getText().toString();
        String email = e2.getText().toString();
        String phone = e3.getText().toString();

        User user = new User(name,email,phone);
        String id = db.push().getKey();
        db.child(id).setValue(user);
        Toast.makeText(this, "User profile has been updated!", Toast.LENGTH_SHORT).show();
    }

    public void updateUser() {
        String name = e1.getText().toString();
        String email = e2.getText().toString();
        String phone = e3.getText().toString();
        User user = new User(name, email, phone);

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                for (DataSnapshot keyID : datasnapshot.getChildren() ){

                    if(keyID.child("email").getValue().equals(email) ){
                        keys = keyID.getKey();
                        DatabaseReference db1 = db.child(keys);
                        db1.setValue(user);
                        Toast.makeText(getApplicationContext(), "User profile has been updated!", Toast.LENGTH_LONG).show();
                    }
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
