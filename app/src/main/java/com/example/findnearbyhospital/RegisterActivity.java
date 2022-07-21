package com.example.findnearbyhospital;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    EditText e1, e2;
    FirebaseAuth mAuth;
    DatabaseReference db = FirebaseDatabase.getInstance("https://findnearby-823cd-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("users");
    User usr = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        e1 = (EditText)findViewById(R.id.editText);
        e2 = (EditText)findViewById(R.id.editText2);

        mAuth = FirebaseAuth.getInstance();
    }

    public void register(View v) {
        if(e1.getText().toString().equals("") && e2.getText().toString().equals("") ) {
            Toast.makeText(getApplicationContext(),"Please Fill-In All the Blanks!", Toast.LENGTH_SHORT).show();
        } else{
            String email = e1.getText().toString();
            String password = e2.getText().toString();

            mAuth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){

                                String uID = mAuth.getCurrentUser().getUid();

                                usr.setUID(uID);
                                usr.setEmail(email);

                                db.child(uID).child("info").setValue(usr);

                                Toast.makeText(getApplicationContext(),"User has been successfully registered!",Toast.LENGTH_SHORT).show();
                                finish();
                                Intent i = new Intent(getApplicationContext(), ProfileActivity.class);
                                startActivity(i);
                            }else{
                                Toast.makeText(getApplicationContext(),"Unable to register the user!",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
}
