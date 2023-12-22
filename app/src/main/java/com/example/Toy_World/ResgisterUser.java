package com.example.Toy_World;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.navbar.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class ResgisterUser extends AppCompatActivity implements View.OnClickListener {

    private TextView  textView3 ,register_user,log_in_page;
    private EditText etusername,etemail,etcontact,etpassword;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resgister_user);

        mAuth= FirebaseAuth.getInstance();

        textView3= (TextView) findViewById(R.id.banner);
        textView3.setOnClickListener(this);

        log_in_page= (TextView) findViewById(R.id.log_in_page);
        log_in_page.setOnClickListener(this);

        register_user= (Button) findViewById(R.id.register_complaint);
        register_user.setOnClickListener(this);

        etusername=(EditText) findViewById(R.id.etusername);
        etcontact=(EditText) findViewById(R.id.etlatitude);
        etemail=(EditText) findViewById(R.id.etlongitude);
        etpassword=(EditText) findViewById(R.id.etpassword);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.banner:

            case R.id.log_in_page:
                startActivity(new Intent(this,MainActivity.class));
                break;

            case R.id.register_complaint:
                register_user();
                break;
        }

    }

    private void register_user() {
        String email = etemail.getText().toString().trim();
        String password = etpassword.getText().toString().trim();
        String username = etusername.getText().toString().trim();
        String contact = etcontact.getText().toString().trim();

        if(email.isEmpty()) {
            etemail.setError("email likhdo bhiya");
            etemail.requestFocus();
            return;
        }
        if(password.isEmpty()){
            etpassword.setError("Password likhdo bhiya");
            etpassword.requestFocus();
            return;
        }
        if(username.isEmpty()){
            etusername.setError("Username likhdo bhiya");
            etusername.requestFocus();
            return;
        }
        if(contact.isEmpty()){
            etcontact.setError("Contact likhdo bhiya");
            etcontact.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            etemail.setError("Sahi se email likhdo bhiya");
            etemail.requestFocus();
            return;
        }
        if(password.length()<6){
            etpassword.setError("Pura Password likhdo bhiya");
            etpassword.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            User user = new User(username,email,contact);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(ResgisterUser.this ,"ho giyaa registration",Toast.LENGTH_LONG).show();

                                            }
                                            else{
                                                Toast.makeText(ResgisterUser.this ,"nahi hora register!!",Toast.LENGTH_LONG).show();

                                            }
                                        }
                                    });

                        }
                        else{
                            Toast.makeText(ResgisterUser.this ,"are nahi hora register",Toast.LENGTH_LONG).show();

                        }
                    }
                });
    }
}