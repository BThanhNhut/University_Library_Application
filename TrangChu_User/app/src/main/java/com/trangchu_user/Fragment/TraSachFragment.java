package com.trangchu_user.Fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ConcatAdapter;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.trangchu_user.Adapter.Muon_TraAdapter;
import com.trangchu_user.Adapter.SachAdapter;
import com.trangchu_user.Adapter.TraAdapter;
import com.trangchu_user.Class.CTMS;
import com.trangchu_user.Class.ChiTietMuonTra;
import com.trangchu_user.Class.DocGia;
import com.trangchu_user.Class.Item_Sach;
import com.trangchu_user.Adapter.LinhAdapter;
import com.trangchu_user.Class.MuonTra;
import com.trangchu_user.Class.MuonTra_SPTra;
import com.trangchu_user.Class.TaiLieu;
import com.trangchu_user.Class.TaiLieuTraSach;
import com.trangchu_user.R;

import java.util.ArrayList;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TraSachFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TraSachFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ArrayList<CTMS> newArrayList;
    private ArrayList<MuonTra> muonTraArrayList;
    private ArrayList<TaiLieuTraSach> newArrayList1;
    private ArrayList<MuonTra_SPTra> muonTra_spTraArrayList;
    private RecyclerView recycleView;
    private RecyclerView recycleView1;
    private TraAdapter myAdapter;
    private SachAdapter sachAdapter;
    private Muon_TraAdapter muon_traAdapter;
    private TextView tvHeading , tvNgay , title_image;
    private ArrayList<DocGia> DocGiaList = new ArrayList<>();
    DatabaseReference chitietmuontra;
    DatabaseReference sach;
    String TenSach = "";
    String id="";
    Integer madg = 0;
    Integer ma_dg = 0;
    String username;
    DatabaseReference database;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    //private com.google.android.material.imageview.ShapeableImageView title_image;
    public TraSachFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TraSachFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TraSachFragment newInstance(String param1, String param2) {
        TraSachFragment fragment = new TraSachFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tra_sach, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvHeading = (TextView) view.findViewById(R.id.tvHeading);
        tvNgay = (TextView) view.findViewById(R.id.tvNgay);

//        dataInitialize();
        recycleView = view.findViewById(R.id.recycleView);
//        recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
//        recycleView1 = view.findViewById(R.id.recycleView);
//        recycleView1.setLayoutManager(new LinearLayoutManager(getContext()));
//        recycleView.setHasFixedSize(true);
//        myAdapter = new TraAdapter(getContext(), newArrayList);
//        recycleView.setAdapter(myAdapter);
        //onClickReadData();
        //onClickReadDataMuonTra();
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("tk_mk", MODE_PRIVATE);
        username = sharedPreferences.getString("Username", "");
        myAdapter = new TraAdapter(getContext(), newArrayList);
        //recycleView.setAdapter(myAdapter);
        newArrayList = new ArrayList<CTMS>();


        layoutRecyclerView(view);
        loadSach();
        Data1();
        loadMuonTra();
        loadCTMuonSach();
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
                        System.out.println("má nó chứ"+ madg);
                    }
                    DocGiaList.add(d);
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void loadCTMuonSach()
    {

        chitietmuontra = FirebaseDatabase.getInstance().getReference().child("ChiTietMuonTra");

        chitietmuontra.addValueEventListener(new ValueEventListener() {
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
                    String NgayTra = dataSnapshot.child("ngayTra").getValue(String.class);
                    String tinhtrangSach = dataSnapshot.child("tinhTrangSach").getValue(String.class);
                    Boolean tinhtrangTra = dataSnapshot.child("tinhTrangTra").getValue(Boolean.class);

                    for(TaiLieuTraSach i: newArrayList1)
                    {
                        if(i.getID().equals(masach) && tinhtrangTra.equals(true))
                        {
                            TenSach = i.getTenSach();
                        }
                    }

                    for(MuonTra_SPTra i: muonTra_spTraArrayList)
                    {
                        if(i.getID().equals(mamuontra) && tinhtrangTra.equals(true))
                            ma_dg = i.getID_DG();
                    }

                    CTMS a = new CTMS(tinhtrangSach, tinhtrangTra, mamuontra, masach,ma_dg, "", TenSach, NgayTra);

                    if(ma_dg == madg)
                    {
                        newArrayList.add(a);
                        //break;
                    }

                    if(TenSach.equals("") || TextUtils.isEmpty(NgayTra))
                        newArrayList.remove(a);
                }
                recycleView.setAdapter(myAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void loadMuonTra()
    {
        muonTra_spTraArrayList = new ArrayList<>();

        sach = FirebaseDatabase.getInstance().getReference().child("MuonTra");
        sach.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    Integer ID = dataSnapshot.child("id").getValue(Integer.class);
                    String ngayMuon = dataSnapshot.child("ngayMuon").getValue(String.class);
                    Boolean tinhTrang = dataSnapshot.child("tinhTrang").getValue(Boolean.class);
                    Integer ID_DG = dataSnapshot.child("id_DG").getValue(Integer.class);

                    MuonTra_SPTra dg = new MuonTra_SPTra(ID, ngayMuon, tinhTrang, ID_DG);

                    muonTra_spTraArrayList.add(dg);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void loadSach()
    {
        newArrayList1 = new ArrayList<>();

        sach = FirebaseDatabase.getInstance().getReference().child("TaiLieu");
        sach.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    Integer ID = dataSnapshot.child("id").getValue(Integer.class);
                    String TenSach = dataSnapshot.child("tenSach").getValue(String.class);
                    Integer NamXB = dataSnapshot.child("namXB").getValue(Integer.class);
                    Integer SoLuong = dataSnapshot.child("soLuong").getValue(Integer.class);
                    String TacGia = dataSnapshot.child("tacGia").getValue(String.class);
                    String Hinh = dataSnapshot.child("hinh").getValue(String.class);
                    String MoTa = dataSnapshot.child("moTa").getValue(String.class);
                    Integer ID_Loai = dataSnapshot.child("id_Loai").getValue(Integer.class);
                    Integer ID_NXB = dataSnapshot.child("id_NXB").getValue(Integer.class);

                    TaiLieuTraSach dg = new TaiLieuTraSach(ID, TenSach, NamXB, SoLuong,TacGia,Hinh, MoTa, ID_Loai,ID_NXB);

                    newArrayList1.add(dg);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void layoutRecyclerView(View view)
    {
        recycleView.addItemDecoration(new DividerItemDecoration(view.getContext(), LinearLayoutManager.VERTICAL));
        myAdapter = new TraAdapter(getContext() , newArrayList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(view.getContext());
        recycleView.setLayoutManager(mLayoutManager);
        recycleView.setItemAnimator(new DefaultItemAnimator());
    }

    private void onClickReadData()
    {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference("ChiTietMuonTra");

        DatabaseReference databaseReference = firebaseDatabase.getReference("ChiTietMuonTra");
        DatabaseReference databaseReference1 = firebaseDatabase.getReference("TaiLieu");

        newArrayList = new ArrayList<>();
        newArrayList1 = new ArrayList<>();
        muonTraArrayList = new ArrayList<>();

        myAdapter = new TraAdapter(getContext() , newArrayList);
        sachAdapter = new SachAdapter(getContext() , getNewArrayList1());
        muon_traAdapter = new Muon_TraAdapter(getContext() , getMuonTraArrayList());

        //ConcatAdapter concatAdapter = new ConcatAdapter(sachAdapter , muon_traAdapter);

        //recycleView.setAdapter(myAdapter);

//        Query query = FirebaseDatabase.getInstance().getReference("ChiTietMuonTra").orderByChild("tinhTrangTra").equalTo(true);
//
//        query.addListenerForSingleValueEvent(eventListener);

        recycleView.setAdapter(myAdapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                //newArrayList.clear();
                for(DataSnapshot snap: snapshot.getChildren()) {
                    CTMS item_sach = snap.getValue(CTMS.class);

                    Query query = FirebaseDatabase.getInstance().getReference("TaiLieu").orderByChild("id").equalTo(item_sach.getID_Sach());

                    query.addListenerForSingleValueEvent(eventListener);
//
//                    Query query1 = FirebaseDatabase.getInstance().getReference("MuonTra").orderByChild("id").equalTo(item_sach.getID_MT());
//
//                    query1.addListenerForSingleValueEvent(eventListener1);

                    newArrayList.add(item_sach);

                }
                myAdapter.notifyDataSetChanged();
                Toast.makeText(getContext(), "thành công", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "lỗi", Toast.LENGTH_SHORT).show();
            }
        });

        //databaseReference1.addListenerForSingleValueEvent(eventListener);

//        databaseReference1.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                if (snapshot.exists()) {
//                    //newArrayList.clear();
//                    for (DataSnapshot snap : snapshot.getChildren()) {
//                        TaiLieu taiLieu = snap.getValue(TaiLieu.class);
//
//                        newArrayList1.add(taiLieu);
//
//                    }
//                    sachAdapter.notifyDataSetChanged();
//                    Toast.makeText(getContext(), "thành công", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(getContext(), "lỗi", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    private void onClickReadDataMuonTra()
    {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

        DatabaseReference databaseReference = firebaseDatabase.getReference("ChiTietMuonTra");

        newArrayList = new ArrayList<>();

        //muon_traAdapter = new Muon_TraAdapter(getContext() , muonTraArrayList);

        recycleView.setAdapter(myAdapter);


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                //newArrayList.clear();
                for(DataSnapshot snap: snapshot.getChildren()) {
                    CTMS item_sach = snap.getValue(CTMS.class);

                    newArrayList.add(item_sach);

                }
                myAdapter.notifyDataSetChanged();
                Toast.makeText(getContext(), "thành công", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "lỗi", Toast.LENGTH_SHORT).show();
            }
        });
    }

    ValueEventListener eventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {

            if (snapshot.exists()) {
                //newArrayList.clear();
                for (DataSnapshot snap : snapshot.getChildren()) {
                    CTMS item_sach = snap.getValue(CTMS.class);

                    newArrayList.add(item_sach);

                }
                myAdapter.notifyDataSetChanged();
                Toast.makeText(getContext(), "thành công", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {
            Toast.makeText(getContext(), "lỗi", Toast.LENGTH_SHORT).show();
        }
    };

    ValueEventListener eventListener1 = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {

            if (snapshot.exists()) {
                //newArrayList.clear();
                for(DataSnapshot snap: snapshot.getChildren()) {
                    MuonTra item_sach = snap.getValue(MuonTra.class);

                    muonTraArrayList.add(item_sach);

                }
                muon_traAdapter.notifyDataSetChanged();
                sachAdapter.notifyDataSetChanged();

                Toast.makeText(getContext(), "thành công", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {
            Toast.makeText(getContext(), "lỗi", Toast.LENGTH_SHORT).show();
        }
    };

    private ArrayList<TaiLieuTraSach> getNewArrayList1()
    {
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {
                    //newArrayList.clear();
                    for (DataSnapshot snap : snapshot.getChildren()) {
                        TaiLieuTraSach taiLieu = snap.getValue(TaiLieuTraSach.class);

                        newArrayList1.add(taiLieu);
                    }
                    muon_traAdapter.notifyDataSetChanged();
                    sachAdapter.notifyDataSetChanged();
                    Toast.makeText(getContext(), "thành công", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "lỗi", Toast.LENGTH_SHORT).show();
            }
        };
        return newArrayList1;
    }

    private ArrayList<MuonTra> getMuonTraArrayList()
    {
        ValueEventListener eventListener1 = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {
                    //newArrayList.clear();
                    for (DataSnapshot snap : snapshot.getChildren()) {
                        MuonTra item_sach = snap.getValue(MuonTra.class);

                        muonTraArrayList.add(item_sach);

                    }
                    muon_traAdapter.notifyDataSetChanged();
                    sachAdapter.notifyDataSetChanged();

                    Toast.makeText(getContext(), "thành công", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "lỗi", Toast.LENGTH_SHORT).show();
            }
        };
        return muonTraArrayList;
    }
}
