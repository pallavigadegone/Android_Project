package com.example.Toy_World;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.navbar.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class ProfileFragment extends Fragment {
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;

    private Button log_out;
    private Button fev;
    private Button myorder;
    private Button setting;



    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =  inflater.inflate(R.layout.fragment_profile, container, false);

        log_out= (Button) view.findViewById(R.id.log_out);
        fev = (Button) view.findViewById(R.id.UserFavorite);
        myorder=(Button) view.findViewById(R.id.UserOrders);
        setting=(Button) view.findViewById(R.id.setting1);

        fev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent( getActivity(),CartFragment.class));
            }
        });

        myorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent( getActivity(),Myorder.class));
            }
        });

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent( getActivity(),Setting.class));
            }
        });
        log_out.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setMessage("Do you want to log out ?");
                builder.setTitle("Alert !");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
                    SharedPreferences pref= getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);

                    SharedPreferences.Editor editor=pref.edit();
                    editor.putBoolean("flag",false);
                    editor.apply();

                    startActivity(new Intent( getActivity(),MainActivity.class));


                    getActivity().finish();
                });
                builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
                    dialog.cancel();
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        })
        ;


                        user = FirebaseAuth.getInstance().getCurrentUser();
                        reference = FirebaseDatabase.getInstance().getReference("Users");
                        userID = user.getUid();


                        final TextView usernameTextView = (TextView) view.findViewById(R.id.username);
                        final TextView emailTextView = (TextView) view.findViewById(R.id.email);
                        final TextView contactTextView = (TextView) view.findViewById(R.id.contact);

                        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                User userProfile = snapshot.getValue(User.class);

                                if (userProfile != null) {
                                    String username = userProfile.username;
                                    String email = userProfile.email;
                                    String contact = userProfile.contact;

                                    usernameTextView.setText(username);
                                    emailTextView.setText(email);
                                    contactTextView.setText(contact);

                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_LONG).show();

                            }
                        });
                        return view;
                    }
                }