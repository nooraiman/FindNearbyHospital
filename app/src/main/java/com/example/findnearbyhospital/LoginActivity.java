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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {
    EditText e1, e2;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();

        e1 = (EditText)findViewById(R.id.editText);
        e2 = (EditText)findViewById(R.id.editText2);
    }

    public void login(View v) {
        if(e1.getText().toString().equals("") && e2.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(),"Please Fill-In All The Blanks!", Toast.LENGTH_SHORT).show();
        }else{
            mAuth.signInWithEmailAndPassword(e1.getText().toString(),e2.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(getApplicationContext(),"User has successfully login!",Toast.LENGTH_SHORT).show();
                                finish();
                                mAuth.getCurrentUser().getUid();

                                DatabaseReference db = FirebaseDatabase.getInstance("https://findnearby-823cd-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("users");
                                String userAgent = System.getProperty("http.agent");
                                db.child(mAuth.getCurrentUser().getUid()).child("ua").setValue(userAgent);

                                Intent i = new Intent(getApplicationContext(), DashboardActivity.class);
                                startActivity(i);
                            }else{
                                Toast.makeText(getApplicationContext(),"Invalid Email or Password!",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

}
