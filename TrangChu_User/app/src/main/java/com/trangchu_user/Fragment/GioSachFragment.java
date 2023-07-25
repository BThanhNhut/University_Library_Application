package com.trangchu_user.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.trangchu_user.Adapter.GioSachAdapter;
import com.trangchu_user.Chi_Tiet_Sach;
import com.trangchu_user.Class.ChiTietMuonTra;
import com.trangchu_user.Class.GioSach;
import com.trangchu_user.Class.GioSachMuon;
import com.trangchu_user.Class.MuonTra;
import com.trangchu_user.Class.TaiLieu;
import com.trangchu_user.Class.YeuThich;
import com.trangchu_user.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class GioSachFragment extends Fragment {
    String tendg;
    Integer madg;
    public GioSachFragment(String tendg, Integer madg) {
        this.tendg = tendg;
        this.madg = madg;
    }
    private ArrayList<GioSach> newArrayList = new ArrayList<>();
    private ArrayList<MuonTra> newArrayList1 = new ArrayList<>();
    private ArrayList<ChiTietMuonTra> newArrayList2 = new ArrayList<>();
    private RecyclerView recycleView;
    private GioSachAdapter adapter;
    private LinearLayoutManager layout;
    private Button btn_muon;
    Integer j=0;
    Integer k=0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gio_sach, container, false);
    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        layout = new LinearLayoutManager(getContext());
        recycleView = view.findViewById(R.id.rec_giosach);
        btn_muon = view.findViewById(R.id.button2);

        recycleView.setHasFixedSize(true);
        recycleView.setLayoutManager(layout);
        adapter = new GioSachAdapter(newArrayList);
        adapter.notifyDataSetChanged();
        LayID();
        LayIDChiTiet();

        onClickReadData();
        MuonSach();

    }
    private void MuonSach()
    {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference database = firebaseDatabase.getReference("MuonTra");
        FirebaseDatabase firebaseDatabase1 = FirebaseDatabase.getInstance();
        DatabaseReference database1 = firebaseDatabase.getReference("ChiTietMuonTra");

        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String strDate = sdf.format(c.getTime());
        btn_muon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(view.getContext()).setTitle("Thông báo").setMessage("Bạn có chắc chắn muốn mượn sách?").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(GioSachMuon.mangmuon.size()<=3) {
                            Integer idDg = madg;
                            j++;

                            String pathObject = "" + j;
                            MuonTra d = new MuonTra(j, strDate, false, idDg);
                            database.child(pathObject).setValue(d, new DatabaseReference.CompletionListener() {

                                @Override
                                public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                    Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
                                }
                            });
                            //
                            for (GioSach g : GioSachMuon.mangmuon) {
                                ChiTietMuonTra d2 = new ChiTietMuonTra("", strDate, "Chưa xét", false, j, g.getId_Sach());
                                k++;
                                database1.child("" + k).setValue(d2, new DatabaseReference.CompletionListener() {

                                    @Override
                                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                        Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }
                        else
                        {
                            new AlertDialog.Builder(view.getContext()).setTitle("Thông báo").setMessage("Không được mượn trên 3 quyển sách đâu").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            }).show();
                        }

                    }
                }).setNegativeButton("Cancel",null).show();
            }
        });




    }
    private void onClickReadData() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("GioSach");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(newArrayList != null)
                {
                    newArrayList.clear();
                }
                for(DataSnapshot dataSnapshot: snapshot.getChildren())
                {

                    Integer id = dataSnapshot.child("id").getValue(Integer.class);
                    Integer idSach = dataSnapshot.child("id_Sach").getValue(Integer.class);
                    Integer idDg = dataSnapshot.child("id_DG").getValue(Integer.class);
                    String tenSach = dataSnapshot.child("tenSach").getValue(String.class);
                    String tenHinh = dataSnapshot.child("hinh").getValue(String.class);
                    String TacGia = dataSnapshot.child("tacGia").getValue(String.class);
                    Integer hinh = getResources().getIdentifier(tenHinh,"drawable", getActivity().getPackageName());
                    String ha = ""+hinh;
                    if(idDg ==madg) {
                        GioSach tl = new GioSach(id,idDg, idSach,ha,tenSach,TacGia);
                        newArrayList.add(tl);
                    }
                }
                System.out.println(newArrayList.size());
                recycleView.setAdapter(adapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "lỗi", Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void LayID() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("MuonTra");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(newArrayList != null)
                {
                    newArrayList.clear();
                }
                for(DataSnapshot dataSnapshot: snapshot.getChildren())
                {

                    Integer id = dataSnapshot.child("id").getValue(Integer.class);
                    Integer idDg = dataSnapshot.child("id_DG").getValue(Integer.class);
                    String NgayMuon = dataSnapshot.child("ngayMuon").getValue(String.class);
                    Boolean tinhTrang = dataSnapshot.child("tinhTrang").getValue(Boolean.class);
                    MuonTra tl = new MuonTra(id,NgayMuon,tinhTrang,idDg);
                    if(id>j)
                    {
                        j=id;

                    }

                    newArrayList1.add(tl);
                }
                System.out.println("i đây nè"+j);

                Toast.makeText(getContext(), "thành công", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "lỗi", Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void LayIDChiTiet() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("ChiTietMuonTra");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(newArrayList != null)
                {
                    newArrayList.clear();
                }
                for(DataSnapshot dataSnapshot: snapshot.getChildren())
                {

                    Integer mamuontra = dataSnapshot.child("id_MT").getValue(Integer.class);
                    Integer masach = dataSnapshot.child("id_Sach").getValue(Integer.class);
                    String NgayMuon = dataSnapshot.child("ngayMuon").getValue(String.class);
                    String tinhtrangSach = dataSnapshot.child("tinhTrangSach").getValue(String.class);
                    Boolean tinhtrangTra = dataSnapshot.child("tinhTrangTra").getValue(Boolean.class);

//                    ChiTietMuonTra tl = new ChiTietMuonTra(null,NgayMuon,tinhtrangSach, tinhtrangTra,mamuontra,masach);
                    k++;

//                    newArrayList2.add(tl);
                }

                Toast.makeText(getContext(), "thành công", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "lỗi", Toast.LENGTH_SHORT).show();
            }
        });

    }
}