package com.trangchu_user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.trangchu_user.Class.DocGia;

public class Activity_ThongTinDocGia extends AppCompatActivity {
    Button btn_back;
    TextView hotendocgia, emaildocgia, gioitinhdocgia, chinhsuadocgia;
    private Integer id;
    //FirebaseDatabase database;
    DatabaseReference mDatabase;
    //private static final String USERS = "DocGia";
    DocGia dg = new DocGia();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongtin_docgia);
        getSupportActionBar().hide();
        addControls();
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), MainActivity.class);
                intent.putExtra("number","0");
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        id = Integer.valueOf(intent.getStringExtra("madg"));
        System.out.println("dat" + id);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("DocGia");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    Integer id_dg = snapshot1.child("id").getValue(Integer.class);
                    if (id_dg == id) {
                        dg.setEmail(snapshot1.child("email").getValue(String.class));
                        dg.setTenDG(snapshot1.child("tenDG").getValue(String.class));
                        dg.setGioiTinh(snapshot1.child("gioiTinh").getValue(String.class));
                    }
                }
                hotendocgia.setText(dg.getTenDG());
                emaildocgia.setText(dg.getEmail());
                gioitinhdocgia.setText(dg.getGioiTinh());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        chinhsuadocgia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), Activity_UpdateDocGia.class);
                intent.putExtra("madg",String.valueOf(id));
                startActivity(intent);
            }
        });

    }

    public void addControls()
    {
        btn_back = (Button) findViewById(R.id.back);
        hotendocgia = (TextView) findViewById(R.id.hoTenDocGia);
        emaildocgia = (TextView) findViewById(R.id.emailDocGia);
        gioitinhdocgia = (TextView) findViewById(R.id.gioiTinhDocGia);
        chinhsuadocgia = (TextView) findViewById(R.id.chinhSuaThongTinDocGia);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //setContentView(R.layout.fragment_tai_khoan);
    }
    public void replaceFrag(Fragment fragment)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_container,fragment);
        fragmentTransaction.commit();
    }
}
