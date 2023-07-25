package com.trangchu_user;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.trangchu_user.Adapter.LinhAdapter_timkiem;
import com.trangchu_user.Class.TaiLieu;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Activity_TimKiemSach extends AppCompatActivity {
    private SearchView searchView;
    private RecyclerView recyclerView;
    List<TaiLieu> mlistTaiLieu;

    private LinhAdapter_timkiem linhAdapter_timkiem;
    private Button btn_hienthi;
    private LinearLayout lnear;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tim_kiem_sach);
        getSupportActionBar().hide();
        addControls();

        searchView.clearFocus();
        searchView.requestFocus();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);




        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchList(newText);
                return true;
            }
        });



        recyclerView.setAdapter(linhAdapter_timkiem);



        mlistTaiLieu = new ArrayList<>();
        linhAdapter_timkiem = new LinhAdapter_timkiem(mlistTaiLieu);


        linhAdapter_timkiem = new LinhAdapter_timkiem(mlistTaiLieu);
        recyclerView.setAdapter(linhAdapter_timkiem);
        layDL();
    }


    private void layDL() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRec = database.getReference("TaiLieu");

        myRec.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (mlistTaiLieu != null)
                {
                    mlistTaiLieu.clear();
                }
                int i = 0;
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {

                    int id = dataSnapshot.child("id").getValue(Integer.class);
                    String tenSach = dataSnapshot.child("tenSach").getValue(String.class);
                    int namXB = dataSnapshot.child("namXB").getValue(Integer.class);
                    String mota = dataSnapshot.child("moTa").getValue(String.class);
                    int soLuong = dataSnapshot.child("soLuong").getValue(Integer.class);
                    String tacGia = dataSnapshot.child("tacGia").getValue(String.class);

                    String hinh = dataSnapshot.child("hinh").getValue(String.class);
                    int id_Loai = dataSnapshot.child("id_Loai").getValue(Integer.class);
                    int id_NXB = dataSnapshot.child("id_NXB").getValue(Integer.class);
                    String tenNXB = dataSnapshot.child("tenNXB").getValue(String.class);
                    Integer hinhsach = getResources().getIdentifier(hinh,"drawable", getPackageName());
                    String ha = ""+hinhsach;
                    TaiLieu tl = new TaiLieu(id, tenSach, namXB, soLuong, tacGia, ha, mota, id_Loai, id_NXB, tenNXB);
                    mlistTaiLieu.add(tl);


                }
                linhAdapter_timkiem.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Activity_TimKiemSach.this, "Lỗi lấy dữ liệu", Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void addControls()
    {
        searchView = (SearchView) findViewById(R.id.searchView_tk);
        recyclerView = (RecyclerView) findViewById(R.id.rec_timkiem);

        lnear = (LinearLayout) findViewById(R.id.linear);
    }

    private void searchList(String text) {
        List<TaiLieu> filterList = new ArrayList<>();
        for(TaiLieu taiLieu : mlistTaiLieu)
        {
            if (taiLieu.getTenSach().toLowerCase(Locale.ROOT).contains(text.toLowerCase()))
            {
                filterList.add(taiLieu);

            }
        }
        if (filterList.isEmpty())
        {
            Toast.makeText(this, "Không tìm thấy dữ liệu", Toast.LENGTH_SHORT).show();
            //int a = 0;
        }
        else {
            linhAdapter_timkiem.setSearchList(filterList);
        }
    }

}