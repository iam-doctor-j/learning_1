package com.jcompany.amazeapps.intentapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText url;
    Button sendUrl, share;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        url = findViewById(R.id.editText);
        sendUrl = findViewById(R.id.button_go);
        share = findViewById(R.id.button_share);
        builder = new AlertDialog.Builder(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        builder.setMessage("Do you want to share?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String urlstr = url.getText().toString();
                if(!urlstr.startsWith("http")||!urlstr.startsWith("https"))
                    urlstr="http://"+urlstr;
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT,urlstr);
                intent.setType("text/html");
                startActivity(intent);
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        sendUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String urlstr = url.getText().toString();
                if(!urlstr.startsWith("http")||!urlstr.startsWith("https"))
                    urlstr="http://"+urlstr;
                Uri webpage = Uri.parse(urlstr);
                Intent intent = new Intent(Intent.ACTION_VIEW,webpage);
                startActivity(intent);
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder.create().show();

            }
        });
    }


}
