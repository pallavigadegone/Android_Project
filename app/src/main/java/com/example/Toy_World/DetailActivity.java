package com.example.Toy_World;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.navbar.R;

public class DetailActivity extends AppCompatActivity {
    TextView detailDesc, detailTitle, detailLang, detailAddress,detailloc;
    ImageView detailImage;
    String key = "";
    String imageUrl = "";
    Button call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        detailDesc = findViewById(R.id.detailDesc);
        detailAddress = findViewById(R.id.detailAddress);
        detailImage = findViewById(R.id.detailImage);
        detailTitle = findViewById(R.id.detailTitle);
        detailLang = findViewById(R.id.detailLang);
        detailloc=findViewById(R.id.detailLocality);
        call=findViewById(R.id.button);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            detailDesc.setText(bundle.getString("Description"));
            detailAddress.setText(bundle.getString("Address"));
            detailTitle.setText(bundle.getString("Title"));
            detailLang.setText(bundle.getString("Language"));
            detailloc.setText(bundle.getString("Locality"));
            key = bundle.getString("Key");
            imageUrl = bundle.getString("Image");
            Glide.with(this).load(bundle.getString("Image")).into(detailImage);
        }

        call.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                String number=detailLang.getText().toString();
                Intent intent=new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" +number));

                startActivity(intent);
            }
        });



    }

}