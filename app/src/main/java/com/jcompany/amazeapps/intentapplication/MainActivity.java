package com.jcompany.amazeapps.intentapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText url;
    Button sendUrl, share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        url = findViewById(R.id.editText);
        sendUrl = findViewById(R.id.button_go);
        share = findViewById(R.id.button_share);

        String urlstr = url.getText().toString();
        if(!urlstr.startsWith("http")||!urlstr.startsWith("https"))
            urlstr="http://"+urlstr;
        final String str = urlstr;
        final Uri webpage = Uri.parse(urlstr);
        sendUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW,webpage);
                startActivity(intent);
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT,str);
                intent.setType("text/html");
                startActivity(intent);

            }
        });
    }


}
