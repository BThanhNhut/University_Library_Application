package com.trangchu_user;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.trangchu_user.Class.DocGia;
import com.trangchu_user.Class.TaiKhoan;

import java.util.ArrayList;

public class DangKy  extends AppCompatActivity {
    TextView txt_dangnhap;
    Integer count = 0;
    EditText txt_name, txt_email, txt_password, txt_repassword;
    RadioButton rad_nam, rad_nu;
    Button btn_dangky;
    DatabaseReference database, database1;
    ArrayList<DocGia> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangky);
        getSupportActionBar().hide();

        addControls();
        database = FirebaseDatabase.getInstance().getReference().child("DocGia");
        database1 = FirebaseDatabase.getInstance().getReference().child("TaiKhoan");
        loadDocGia();

        btn_dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hoten = txt_name.getText().toString().trim();
                String email = txt_email.getText().toString().trim();
                String matkhau = txt_password.getText().toString().trim();
                String matkhaunhaplai = txt_repassword.getText().toString().trim();
                String gioitinh;
                if (rad_nam.isChecked()) {
                    gioitinh = "Nam";
                } else {
                    gioitinh = "Nữ";
                }
                if(hoten.equals("") || email.equals("") || matkhau.equals("") || matkhaunhaplai.equals("") || gioitinh.equals(""))
                {
                    Toast.makeText(DangKy.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if (checkDinhDangEmail(email))
                    {
                        if(checkEmail(email))
                        {
                            if(matkhau.equals(matkhaunhaplai))
                            {
                                count++;
                                System.out.println(count);
                                String path = String.valueOf(count);
                                DocGia d = new DocGia(count, hoten, gioitinh, email, email);
                                database.child(path).setValue(d, new DatabaseReference.CompletionListener() {
                                    @Override
                                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {

                                    }
                                });
                                TaiKhoan tk = new TaiKhoan(email, matkhau, false, count);
                                database1.child(path).setValue(tk, new DatabaseReference.CompletionListener() {
                                    @Override
                                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                        Toast.makeText(DangKy.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                                        txt_dangnhap.callOnClick();
                                    }
                                });
                            }
                            else
                            {
                                AlertDialog.Builder builder = new AlertDialog.Builder(DangKy.this);
                                builder.setTitle("Thông báo");
                                builder.setMessage("Mật khẩu chưa đúng. Vui lòng nhập lại");

                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        txt_password.setText("");
                                        txt_repassword.setText("");
                                    }
                                });
                                builder.show();
                            }
                        }
                        else
                        {
                            AlertDialog.Builder builder = new AlertDialog.Builder(DangKy.this);
                            builder.setTitle("Thông báo");
                            builder.setMessage("Email đã tồn tại");

                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    txt_email.setText("");
                                }
                            });
                            builder.show();
                        }
                    }
                    else
                    {
                        AlertDialog.Builder builder = new AlertDialog.Builder(DangKy.this);
                        builder.setTitle("Thông báo");
                        builder.setMessage("Email sai định dạng");

                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                txt_email.setText("");
                            }
                        });
                        builder.show();
                    }
                }
            }
        });

        txt_dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), DangNhap.class);
                startActivity(intent);
            }
        });
    }

    private void addControls()
    {
        txt_name = findViewById(R.id.txt_name);
        txt_email = findViewById(R.id.txt_email);
        txt_password = findViewById(R.id.txt_password);
        txt_repassword = findViewById(R.id.txt_repassword);
        txt_dangnhap = findViewById(R.id.txt_dangnhap);
        rad_nam = findViewById(R.id.rad_nam);
        rad_nu = findViewById(R.id.rad_nu);
        btn_dangky = findViewById(R.id.btn_dangky);
    }

    private void loadDocGia()
    {
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren())
                {
                    Integer id = dataSnapshot.child("id").getValue(Integer.class);
                    String email = dataSnapshot.child("email").getValue(String.class);
                    String gioitinh = dataSnapshot.child("gioiTinh").getValue(String.class);
                    String tendg = dataSnapshot.child("tenDG").getValue(String.class);
                    String username = dataSnapshot.child("username").getValue(String.class);
                    DocGia d = new DocGia(id, tendg, gioitinh, email, username);
                    list.add(d);
                    count++;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private boolean checkEmail(String email)
    {
        for(DocGia  i: list)
        {
            if(i.getEmail().equals(email)) {
                return false;
            }
        }
        return true;
    }

    private boolean checkDinhDangEmail(String email)
    {
        if(email.contains("@gmail.com"))
        {
            return true;
        }
        return false;
    }
}
