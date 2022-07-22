package com.example.findnearbyhospital;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import java.util.HashMap;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity {

    Button submit,home;
    EditText e1, e2, e3;

    String email;

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

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    updateUser();
            }
        });

        db.child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String name = (String) dataSnapshot.child("info").child("name").getValue();
                String email = (String) dataSnapshot.child("info").child("email").getValue();
                String phone = (String) dataSnapshot.child("info").child("phone").getValue();

                e1.setText(name);
                e2.setText(email);
                e3.setText(phone);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void openDashboard(){
        Intent i = new Intent(this, DashboardActivity.class);
        startActivity(i);
    }

    public void updateUser() {
        String name = e1.getText().toString();
        String email = e2.getText().toString();
        String phone = e3.getText().toString();
        String userAgent = System.getProperty("http.agent");

        User usr = new User(name, email, phone);

        db.child(this.user.getUid()).child("info").setValue(usr);
        db.child(this.user.getUid()).child("ua").setValue(userAgent);

        Map<String, Object> loc = new HashMap<>();
        loc.put("latitude", "");
        loc.put("longitude", "");
        loc.put("address", "");
        loc.put("timestamp", "");

        db.child(this.user.getUid()).child("location").setValue(loc);

        Toast.makeText(this, "User profile has been updated!", Toast.LENGTH_SHORT).show();
    }
}
