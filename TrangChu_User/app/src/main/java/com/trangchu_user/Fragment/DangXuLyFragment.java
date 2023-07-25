//package com.trangchu_user.Fragment;
//
//import android.os.Bundle;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//import androidx.fragment.app.FragmentManager;
//import androidx.fragment.app.FragmentTransaction;
//import androidx.recyclerview.widget.DividerItemDecoration;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.TextView;
//
//import com.google.android.material.tabs.TabLayout;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//import com.firebase.ui.database.FirebaseRecyclerOptions;
//
//import com.trangchu_user.Adapter.LinhAdapter;
//import com.trangchu_user.Adapter.MuonAdapter;
//import com.trangchu_user.Adapter.MuonTraAdapter;
//import com.trangchu_user.Class.MuonTra;
//import com.trangchu_user.Fragment.TaiKhoanFragment;
//import com.trangchu_user.Fragment.TraSachFragment;
//import com.trangchu_user.R;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//public class DSMuonFragment extends Fragment {
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    private String mParam1;
//    private String mParam2;
//    private TabLayout tab_ds_muon;
//    private TextView id , ngaymuon;
//    private List<MuonTra> muonTraList;
//    private MuonTraAdapter muonTraAdapter;
//    private RecyclerView recycleView;
//    private final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
//    public DSMuonFragment() {
//
//    }
//    public static DSMuonFragment newInstance(String param1, String param2) {
//        DSMuonFragment fragment = new DSMuonFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//
//        //return inflater.inflate(R.layout.fragment_d_s_muon, container, false);
//
//        View view=inflater.inflate(R.layout.fragment_d_s_muon, container, false);
//
//        recycleView=(RecyclerView)view.findViewById(R.id.recycleView);
//        recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
//
//        FirebaseRecyclerOptions<MuonTra> options =
//                new FirebaseRecyclerOptions.Builder<MuonTra>()
//                        .setQuery(FirebaseDatabase.getInstance().getReference().child("MuonTra"), MuonTra.class)
//                        .build();
//
//        muonTraAdapter=new MuonTraAdapter(options);
//        recycleView.setAdapter(muonTraAdapter);
//
//        return view;
//
//    }
//    @Override
//    public void onStart() {
//        super.onStart();
//        muonTraAdapter.startListening();
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        muonTraAdapter.stopListening();
//    }
////    @Override
////    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
////        super.onViewCreated(view, savedInstanceState);
//
//
//
////        addControls(view);
////        muonTraList = new ArrayList<>();
////        recycleView = view.findViewById(R.id.recycleView);
////
////        recycleView.setHasFixedSize(true);
////
////        recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
////        databaseReference.addValueEventListener(new ValueEventListener() {
////            @Override
////            public void onDataChange(@NonNull DataSnapshot snapshot) {
////                muonTraList.clear();
////                for(DataSnapshot muonTra : snapshot.child("MuonTra").getChildren())
////                {
////                    final int getId = muonTra.child("id").getValue(Integer.class);
////                    final String getNgayMuon = muonTra.child("ngayMuon").getValue(String.class);
////
////                    MuonTra muontra = new MuonTra(getId , getNgayMuon);
////
////                    muonTraList.add(muontra);
////                }
////
////                recycleView.setAdapter(new MuonAdapter(muonTraList , getContext()));
////            }
////
////            @Override
////            public void onCancelled(@NonNull DatabaseError error) {
////
////            }
////        });
////        MuonTraAdapter = new MuonAdapter(muonTraList);
////        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext() , RecyclerView.VERTICAL,false);
////        recycleView.setLayoutManager(linearLayoutManager);
////        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL);
////        recycleView.addItemDecoration(dividerItemDecoration);
////        recycleView.setAdapter(MuonTraAdapter);
//
////        onClickReadData();
//
////        tab_ds_muon.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
////            @Override
////            public void onTabSelected(TabLayout.Tab tab) {
////                switch (tab.getPosition()) {
////                    case 0:
////                        replaceFrag(new DSMuonFragment());
////                        break;
////                    case 1:
////                        replaceFrag(new TaiKhoanFragment());
////                        break;
////                    case 2:
////                        replaceFrag(new TraSachFragment());
////
////                }
////            }
////
////            @Override
////            public void onTabUnselected(TabLayout.Tab tab) {
////
////            }
////
////            @Override
////            public void onTabReselected(TabLayout.Tab tab) {
////
////            }
////        });
//
//
////    }
////    private void addControls(View view)
////    {
////        tab_ds_muon = view.findViewById(R.id.tab_ds_muon);
////        id = view.findViewById(R.id.mamuon);
////        ngaymuon = view.findViewById(R.id.ngaymuon);
////    }
////
////    public void replaceFrag(Fragment fragment)
////    {
////        FragmentManager fragmentManager = getChildFragmentManager();
////        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
////        fragmentTransaction.replace(R.id.frame,fragment);
////        fragmentTransaction.commit();
////    }
////
////    private void onClickReadData()
////    {
////        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
////        DatabaseReference myRef = firebaseDatabase.getReference("MuonTra");
////        DatabaseReference databaseReference = firebaseDatabase.getReference();
////
////        myRef.addValueEventListener(new ValueEventListener() {
////            @Override
////            public void onDataChange(@NonNull DataSnapshot snapshot) {
////                muonTraList.clear();
////                for(DataSnapshot snap: snapshot.getChildren()) {
////                    MuonTra muonTra = snap.getValue(MuonTra.class);
////                    id.setText(muonTra.getID());
////                    ngaymuon.setText(muonTra.getNgayMuon());
////                    muonTraList.add(muonTra);
////                }
////                MuonTraAdapter.notifyDataSetChanged();
////            }
////
////            @Override
////            public void onCancelled(@NonNull DatabaseError error) {
////
////            }
////        });
//
////        myRef.addValueEventListener(new ValueEventListener() {
////            @Override
////            public void onDataChange(@NonNull DataSnapshot snapshot) {
////                for(DataSnapshot snap: snapshot.getChildren()) {
////                    MuonTra muonTra = snap.getValue(MuonTra.class);
////                    id.setText(muonTra.getID());
////                    ngaymuon.setText(muonTra.getNgayMuon());
////                }
////            }
////
////            @Override
////            public void onCancelled(@NonNull DatabaseError error) {
////
////            }
////        });
//
////    }
//}


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

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.trangchu_user.Adapter.DangXuLyAdapter;
import com.trangchu_user.Adapter.MuonTraAdapter;
import com.trangchu_user.Adapter.Muon_TraAdapter;
import com.trangchu_user.Adapter.SachAdapter;
import com.trangchu_user.Adapter.TraAdapter;
import com.trangchu_user.Class.ChiTietMuonTra;
import com.trangchu_user.Class.DocGia;
import com.trangchu_user.Class.MuonTra;
import com.trangchu_user.Class.TaiLieu;
import com.trangchu_user.R;

import java.util.ArrayList;


public class DangXuLyFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private ArrayList<MuonTra> newArrayList;

    private RecyclerView recycleView;

    private DangXuLyAdapter myAdapter;
    private ArrayList<DocGia> newArrayList1 = new ArrayList<>();
    private ArrayList<MuonTra> muonTraArrayList;
    String id="";
    Integer madg = 0;
    String username;
    DatabaseReference database;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    public DangXuLyFragment() {

    }

    public static DSMuonFragment newInstance(String param1, String param2) {
        DSMuonFragment fragment = new DSMuonFragment();
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

        View view=inflater.inflate(R.layout.fragment_dang_xu_ly, container, false);

//        recview=(RecyclerView)view.findViewById(R.id.recycleView);
//        recview.setLayoutManager(new LinearLayoutManager(getContext()));
//
//        FirebaseRecyclerOptions<MuonTra> options =
//                new FirebaseRecyclerOptions.Builder<MuonTra>()
//                        .setQuery(FirebaseDatabase.getInstance().getReference().child("MuonTra"), MuonTra.class)
//                        .build();
//
//        adapter=new MuonTraAdapter(options);
//        recview.setAdapter(adapter);

        return view;
    }


    //    @Override
//    public void onStart() {
//        super.onStart();
//        adapter.startListening();
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        adapter.stopListening();
//    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        //        dataInitialize();


        recycleView = view.findViewById(R.id.recycleView);
        //recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        myAdapter = new DangXuLyAdapter(muonTraArrayList, getContext());
        muonTraArrayList = new ArrayList<MuonTra>();
        layoutRecyclerView(view);

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("tk_mk", MODE_PRIVATE);
        username = sharedPreferences.getString("Username", "");

        Data1();
        onClickReadData();

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
                    newArrayList1.add(d);
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
        myAdapter = new DangXuLyAdapter(muonTraArrayList, getContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(view.getContext());
        recycleView.setHasFixedSize(true);
        recycleView.setLayoutManager(mLayoutManager);
        recycleView.setItemAnimator(new DefaultItemAnimator());
    }

    private void onClickReadData()
    {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference("ChiTietMuonTra");

        DatabaseReference databaseReference = firebaseDatabase.getReference("ChiTietMuonTra");
        DatabaseReference databaseReference1 = firebaseDatabase.getReference("TaiLieu");

//        muonTraArrayList = new ArrayList<>();
//
//        myAdapter = new DangXuLyAdapter(muonTraArrayList, getContext());

        //ConcatAdapter concatAdapter = new ConcatAdapter(sachAdapter , muon_traAdapter);

        recycleView.setAdapter(myAdapter);



        Query query1 = FirebaseDatabase.getInstance().getReference("MuonTra").orderByChild("tinhTrang").equalTo(false);

        query1.addListenerForSingleValueEvent(eventListener1);

    }

    ValueEventListener eventListener1 = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {

            if (snapshot.exists()) {
                //newArrayList.clear();
                for(DataSnapshot snap: snapshot.getChildren()) {
                    MuonTra item_sach = snap.getValue(MuonTra.class);

                    if(item_sach.getID_DG() == madg)
                    {
                        muonTraArrayList.add(item_sach);
                    }
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


}