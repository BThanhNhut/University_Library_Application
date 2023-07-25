package com.trangchu_user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Nhut_menu extends AppCompatActivity {
    Button tk_muontra, tk_tailieu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhut_menu);
        getSupportActionBar().hide();
        addControl();
        tk_muontra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Nhut_menu.this, Nhut_thongke1.class);
                startActivity(intent);
            }
        });
        tk_tailieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Nhut_menu.this, Nhut_thongke2.class);
                startActivity(intent);
            }
        });
    }
    private void addControl ()
    {
        tk_muontra = (Button) findViewById(R.id.tk_muontra);
        tk_tailieu = (Button) findViewById(R.id.tk_tailieu);
    }
}
