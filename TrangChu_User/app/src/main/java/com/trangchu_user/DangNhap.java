package com.trangchu_user;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.trangchu_user.Class.DocGia;
import com.trangchu_user.Class.TaiKhoan;

import java.util.ArrayList;

public class DangNhap extends AppCompatActivity {
    TextView txt_dangky;
    TextView txt_username, txt_password;
    Button btn_dangnhap;
    DatabaseReference database;
    ArrayList<DocGia> list = new ArrayList<>();
    int count = 0;
    ArrayList<TaiKhoan> list_tk = new ArrayList<>();
    String fileluu = "tk_mk";
    String tendg = "";
    Integer madg = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap);
        getSupportActionBar().hide();
        addControls();
        loadListDocGia();
        loadTaiKhoan();

        btn_dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = txt_username.getText().toString().trim();
                String password = txt_password.getText().toString().trim();
                if (username.equals("") || password.equals("")) {
                    Toast.makeText(DangNhap.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    if (checkUsername(username)) {
                        if (checkMatKhau(password, username)) {
                            Toast.makeText(DangNhap.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();

                            //luu dang nhap
                            SharedPreferences sharedPreferences = getSharedPreferences(fileluu, MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("Username", txt_username.getText().toString().trim());
                            editor.putString("Password", txt_password.getText().toString());
                            editor.commit();

                            //Goi trang chu
                            for (DocGia i : list) {
                                if (i.getUsername().equals(username)) {
                                    tendg = i.getTenDG();
                                    madg = i.getID();
                                }
                            }
                            Intent intent = new Intent();
                            intent.setClass(getApplicationContext(), MainActivity.class);
//                            intent.putExtra("TenDG", tendg);
//                            intent.putExtra("IdDG", String.valueOf(madg));
                            startActivity(intent);
                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(DangNhap.this);
                            builder.setTitle("Thông báo");
                            builder.setMessage("Mật khẩu chưa chính xác. Vui lòng nhập lại");

                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    txt_password.setText("");
                                }
                            });
                            builder.show();
                        }
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(DangNhap.this);
                        builder.setTitle("Thông báo");
                        builder.setMessage("Tên đăng nhập chưa chính xác. Vui lòng nhập lại");

                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                txt_username.setText("");
                            }
                        });
                        builder.show();
                    }
                }
            }
        });

        txt_dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), DangKy.class);
                startActivity(intent);
            }
        });
    }

    private void addControls() {
        txt_username = findViewById(R.id.txt_username);
        txt_password = findViewById(R.id.txt_password);
        btn_dangnhap = findViewById(R.id.btn_dangnhap);
        txt_dangky = findViewById(R.id.txt_dangky);
    }

    private void loadListDocGia() {
        database = FirebaseDatabase.getInstance().getReference().child("DocGia");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Integer id = dataSnapshot.child("id").getValue(Integer.class);
                    String email = dataSnapshot.child("email").getValue(String.class);
                    String gioitinh = dataSnapshot.child("gioiTinh").getValue(String.class);
                    String tendg = dataSnapshot.child("tenDG").getValue(String.class);
                    String username = dataSnapshot.child("username").getValue(String.class);
                    DocGia d = new DocGia(id, tendg, gioitinh, email, username);
                    list.add(d);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void loadTaiKhoan() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("TaiKhoan");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String username_dg = dataSnapshot.child("username").getValue(String.class);
                    String password_dg = dataSnapshot.child("password").getValue(String.class);
                    Boolean tt = dataSnapshot.child("tinhtrang").getValue(Boolean.class);
                    TaiKhoan tk = new TaiKhoan(username_dg, password_dg, tt, 1);
                    list_tk.add(tk);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private boolean checkUsername(String username) {
        for (TaiKhoan i : list_tk) {
            if (i.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkMatKhau(String password, String username)
    {
        for (TaiKhoan i : list_tk) {
            if (i.getUsername().equals(username)) {
                if(i.getPassword().equals(password))
                    return true;
            }
        }
        return false;
    }
}
