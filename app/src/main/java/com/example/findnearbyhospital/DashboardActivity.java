package com.example.findnearbyhospital;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
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

public class DashboardActivity extends AppCompatActivity {
    TextView userName;
    String name,email;
    Button hospital,developer,out,userprofile;
    DatabaseReference db = FirebaseDatabase.getInstance("https://findnearby-823cd-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("users");
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FirebaseApp.initializeApp(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        email = user.getEmail();
        userName = (TextView)findViewById(R.id.profileName);

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                for (DataSnapshot keyID : datasnapshot.getChildren() ){

                    if(keyID.child("email").getValue().equals(email) ){
                        name = keyID.child("name").getValue().toString();
                        userName.setText(name);
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        // User Profile
        userprofile = (Button) findViewById(R.id.btnuserprofile);
        userprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openProfile();
            }
        });

        // Hospital
        hospital = (Button) findViewById(R.id.btnHospital);
        hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHospital();
            }
        });

        // Developer
        developer = (Button) findViewById(R.id.btnAboutDevelopers);
        developer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDeveloper();
            }
        });

        // Sign-Out
        out = (Button) findViewById(R.id.btnSignOut);
        out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();
            }
        });
    }

    public void openProfile(){
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    public void openHospital(){
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    public void openDeveloper(){
        Intent intent = new Intent(this, DeveloperActivity.class);
        startActivity(intent);
    }

    public void signOut(){
        try {
            FirebaseAuth.getInstance().signOut();
            Toast.makeText(getApplicationContext(),"User successfully logged out ",Toast.LENGTH_SHORT).show();
            finish();
            Intent i = new Intent(this,MainActivity.class);
            startActivity(i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
