package com.trangchu_user.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.trangchu_user.Adapter.YeuThichAdapter;
import com.trangchu_user.Class.YeuThich;
import com.trangchu_user.R;

import java.util.ArrayList;


public class YeuThichFragment extends Fragment {
    String tendg;
    Integer madg;

    public YeuThichFragment(String tendg, Integer madg) {
        this.tendg = tendg;
        this.madg = madg;
    }

    private ArrayList<YeuThich> newArrayList = new ArrayList<>();

    private RecyclerView recycleView;
    private YeuThichAdapter adapter;
    private LinearLayoutManager layout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_yeu_thich, container, false);
    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        layout = new LinearLayoutManager(getContext());
        recycleView = view.findViewById(R.id.rec_yeuthich);
        recycleView.setHasFixedSize(true);
        recycleView.setLayoutManager(layout);
        adapter = new YeuThichAdapter(newArrayList);
        adapter.notifyDataSetChanged();


        Data();
    }
    private void Data() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("DanhSachYeuThich");

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
                        YeuThich tl = new YeuThich(id,idDg, idSach,ha,tenSach,TacGia);
                        newArrayList.add(tl);
                    }
                }
                recycleView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "lỗi", Toast.LENGTH_SHORT).show();
            }
        });
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//               updateDatabase(adapter, snapshot);

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
//                updateDatabase(adapter, snapshot);

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void updateDatabase(YeuThichAdapter a, @NonNull DataSnapshot dataSnapshot)
    {
        Integer id = dataSnapshot.child("id").getValue(Integer.class);
        Integer idSach = dataSnapshot.child("id_Sach").getValue(Integer.class);
        Integer idDg = dataSnapshot.child("id_DG").getValue(Integer.class);
        String tenSach = dataSnapshot.child("tenSach").getValue(String.class);
        String tenHinh = dataSnapshot.child("hinh").getValue(String.class);
        String TacGia = dataSnapshot.child("tacGia").getValue(String.class);
        Integer hinh = getResources().getIdentifier(tenHinh,"drawable", getActivity().getPackageName());
        String ha = ""+hinh;
        YeuThich tl = new YeuThich(id,idDg, idSach,ha,tenSach,TacGia);
        for (YeuThich i : newArrayList) {
            if (i.getId_DG() == madg) {
                newArrayList.add(tl);
                a.notifyDataSetChanged();
            }

        }

    }
}