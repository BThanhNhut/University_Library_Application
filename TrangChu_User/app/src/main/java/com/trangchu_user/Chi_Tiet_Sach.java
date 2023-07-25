package com.trangchu_user;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
import com.trangchu_user.Adapter.AdapterViewPager;
import com.trangchu_user.Class.DocGia;
import com.trangchu_user.Class.GioSach;
import com.trangchu_user.Class.ListYeuThich;
import com.trangchu_user.Class.TaiLieu;
import com.trangchu_user.Class.YeuThich;
import com.trangchu_user.Fragment.GioSachFragment;
import com.trangchu_user.Fragment.TaiKhoanFragment;
import com.trangchu_user.Fragment.TrangChuFragment;
import com.trangchu_user.Fragment.YeuThichFragment;

import java.util.ArrayList;
import java.util.List;

public class Chi_Tiet_Sach extends AppCompatActivity {
    ImageView img,ha, img_yt;
    TextView name, tv_tg , theloai1, theloai2, sl, mota, tv_nxb, tv_namxb;
    Button btn_muon;
    String id="";
    Integer madg = 0;
    Integer hinh;
    Integer i=0;
    Integer j=0;
    Integer tt1=0;
    String username;
    List<TaiLieu> taiLieuList;
    private ArrayList<DocGia> newArrayList1 = new ArrayList<>();
    private ArrayList<GioSach> newArrayList2 = new ArrayList<>();

    DatabaseReference database;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference myRef = firebaseDatabase.getReference("TaiLieu");
    DatabaseReference databaseReference = firebaseDatabase.getReference();
    FirebaseDatabase firebaseDatabase1 = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference1 = firebaseDatabase1.getReference("DanhSachYeuThich");
    FirebaseDatabase firebaseDatabase2 = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference2 = firebaseDatabase2.getReference("GioSach");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_sach);
        getSupportActionBar().hide();
        addControls();
        taiLieuList = new ArrayList<>();
        img = (ImageView) findViewById(R.id.imageView);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String back = getIntent().getStringExtra("back_theloai");
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), MainActivity.class);
                if (back != null)
                {
                    intent.setClass(getApplicationContext(), TheLoai.class);
                }
                intent.putExtra("number","10");
                startActivity(intent);
            }
        });
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        SharedPreferences sharedPreferences = getSharedPreferences("tk_mk", MODE_PRIVATE);
        username = sharedPreferences.getString("Username", "");
        System.out.println("tên t nè"+username);
        Data();
        trangThaiYeuThich();
        Data1();
        DataGioSach();
        onClickReadData();
        themvaoDSYeuThich();
        themvaoGioSach();

    }
    public void themvaoDSYeuThich()
    {
        img_yt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer idSach = Integer.parseInt(id);
                if(checkSach(idSach)) {
                    img_yt.setImageResource(R.drawable.mai_yeuthich1);
                    tt1 = 1;
                    String tenSach = name.getText().toString();
                    String tenTacGia = tv_tg.getText().toString();

                    String ha = String.valueOf(hinh);
                    Integer idDg = madg;
                    i++;

                    String pathObject = "" + i;
                    YeuThich d = new YeuThich(i, idDg, idSach, ha, tenSach, tenTacGia);
                    databaseReference1.child(pathObject).setValue(d, new DatabaseReference.CompletionListener() {

                        @Override
                        public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                            Toast.makeText(Chi_Tiet_Sach.this, "Success", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else {
                    System.out.println("Sách này có rồi");
                }


            }
        });
    }
    public void themvaoGioSach()
    {
        btn_muon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer idSach = Integer.parseInt(id);
                if(checkGioSach(idSach)) {
                    String tenSach = name.getText().toString();
                    String tenTacGia = tv_tg.getText().toString();

                    String ha = String.valueOf(hinh);
                    Integer idDg = madg;
                    j++;

                    String pathObject = "" + j;
                    GioSach d = new GioSach(j, idDg, idSach, ha, tenSach, tenTacGia);
                    databaseReference2.child(pathObject).setValue(d, new DatabaseReference.CompletionListener() {

                        @Override
                        public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                            Toast.makeText(Chi_Tiet_Sach.this, "Success", Toast.LENGTH_SHORT).show();
                        }
                    });
                }


            }
        });
    }

    public void addControls()
    {
        img_yt = (ImageView) findViewById(R.id.img_yeuthich);
        name = (TextView) findViewById(R.id.textView11);
        tv_tg = (TextView) findViewById(R.id.tv_tg);
        theloai1 = (TextView) findViewById(R.id.textView7);
        theloai2 = (TextView) findViewById(R.id.textView10);
        sl = (TextView) findViewById(R.id.textView12);
        mota = (TextView) findViewById(R.id.textView13);
        tv_nxb = (TextView) findViewById(R.id.tv_nxb);
        tv_namxb = (TextView) findViewById(R.id.tv_namxb);
        btn_muon = (Button) findViewById(R.id.button2);
        ha = (ImageView) findViewById(R.id.imageView7);
    }

    private void onClickReadData() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snap : snapshot.getChildren()) {
                    TaiLieu taiLieu = snap.getValue(TaiLieu.class);
                    if (taiLieu.getID() == Integer.parseInt(id)) {
                        name.setText(taiLieu.getTenSach());
                        tv_tg.setText("Tác giả: "+taiLieu.getTacGia());
//                        theloai1.setText(String.valueOf(taiLieu.getID_Loai()));
                        sl.setText("Số lượng: "+String.valueOf(taiLieu.getSoLuong()));
                        mota.setText("Mô tả: "+taiLieu.getMoTa());
                        tv_namxb.setText("Năm xuất bản :"+ String.valueOf(taiLieu.getNamXB()));
                        tv_nxb.setText(String.valueOf(taiLieu.getTenNXB()));
                        hinh = getResources().getIdentifier(taiLieu.getHinh(),"drawable", getPackageName());
                        ha.setImageResource(hinh);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });
    }
    private void Data() {

        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (ListYeuThich.mangyeuthich != null) {
                    ListYeuThich.mangyeuthich.clear();
                }
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Integer id = dataSnapshot.child("id").getValue(Integer.class);
                    Integer idSach = dataSnapshot.child("id_Sach").getValue(Integer.class);
                    Integer idDg = dataSnapshot.child("id_DG").getValue(Integer.class);
                    String tenSach = dataSnapshot.child("tenSach").getValue(String.class);
                    String tenHinh = dataSnapshot.child("hinh").getValue(String.class);
                    String TacGia = dataSnapshot.child("tacGia").getValue(String.class);
                    Integer hinh = getResources().getIdentifier(tenHinh, "drawable", getPackageName());
                    String ha = "" + hinh;
                    YeuThich tl = new YeuThich(id,idDg, idSach, ha, tenSach, TacGia);
                    if(id>i)
                    {
                        i=id;
                    }
                    ListYeuThich.mangyeuthich.add(tl);


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
    private void Data1() {

        database = FirebaseDatabase.getInstance().getReference().child("DocGia");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Integer id = dataSnapshot.child("id").getValue(Integer.class);
                    String email = dataSnapshot.child("email").getValue(String.class);
                    String gioitinh = dataSnapshot.child("gioiTinh").getValue(String.class);
                    String tendg = dataSnapshot.child("tenDG").getValue(String.class);
                    String userName = dataSnapshot.child("username").getValue(String.class);
                    DocGia d = new DocGia(id, tendg, gioitinh, email, userName);
                    if(userName.equals(username))
                    {
                        madg =id;
                    }
                    newArrayList1.add(d);
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void DataGioSach() {

        databaseReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (ListYeuThich.mangsach != null) {
                    ListYeuThich.mangsach.clear();
                }
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Integer id = dataSnapshot.child("id").getValue(Integer.class);
                    Integer idSach = dataSnapshot.child("id_Sach").getValue(Integer.class);
                    Integer idDg = dataSnapshot.child("id_DG").getValue(Integer.class);
                    String tenSach = dataSnapshot.child("tenSach").getValue(String.class);
                    String tenHinh = dataSnapshot.child("hinh").getValue(String.class);
                    String TacGia = dataSnapshot.child("tacGia").getValue(String.class);
                    Integer hinh = getResources().getIdentifier(tenHinh, "drawable", getPackageName());
                    String ha = "" + hinh;
                    GioSach tl = new GioSach(id,idDg, idSach, ha, tenSach, TacGia);
                    if(id>j)
                    {
                        j=id;
                    }
                    ListYeuThich.mangsach.add(tl);


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
    private boolean checkSach(Integer maS)
    {
        System.out.println("cái list nó dài từng này nề"+ListYeuThich.mangyeuthich.size());
        for(YeuThich i: ListYeuThich.mangyeuthich)
        {
            if(i.getId_Sach()==maS && i.getId_DG()==madg) {
                return false;
            }
        }
        return true;
    }
    private boolean checkGioSach(Integer maS)
    {
        for(GioSach i: ListYeuThich.mangsach)
        {
            if(i.getId_Sach()==maS && i.getId_DG()==madg) {
                return false;
            }
        }
        return true;
    }
    private void trangThaiYeuThich()
    {
        for(YeuThich a : ListYeuThich.mangyeuthich)
        {
            if(a.getId_Sach() ==Integer.parseInt(id))
            {
                img_yt.setImageResource(R.drawable.mai_yeuthich1);
            }
            else
            {
                img_yt.setImageResource(R.drawable.mai_yeuthich);
            }
        }
    }





}
