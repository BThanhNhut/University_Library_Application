package com.trangchu_user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.trangchu_user.Class.DocGia;

public class Activity_UpdateDocGia extends AppCompatActivity {
    EditText tendg, emaildg;
    Button capnhat;
    private Integer id_dg;
    DatabaseReference mDatabase;
    DocGia dg = new DocGia();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_doc_gia);
        getSupportActionBar().hide();
        addControls();

        Intent intent = getIntent();
        id_dg = Integer.valueOf(intent.getStringExtra("madg"));
        System.out.println("day la con quy nao day " + id_dg);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("DocGia");
        capnhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(emaildg.getText().equals("") || tendg.getText().equals("")){
                    Toast.makeText(Activity_UpdateDocGia.this, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                }
                else {
                    mDatabase.child(String.valueOf(id_dg)).child("email").setValue(emaildg.getText().toString());
                    mDatabase.child(String.valueOf(id_dg)).child("tenDG").setValue(tendg.getText().toString());
                    Toast.makeText(Activity_UpdateDocGia.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void addControls(){
        //tendn = (EditText) findViewById(R.id.userName);
        emaildg = (EditText) findViewById(R.id.userName);
        tendg = (EditText) findViewById(R.id.firstName);
        capnhat = (Button) findViewById(R.id.updateBtn);
    }
}