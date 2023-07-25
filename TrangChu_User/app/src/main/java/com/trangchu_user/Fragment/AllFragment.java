package com.trangchu_user.Fragment;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.trangchu_user.Adapter.LoadAllAdapter;
import com.trangchu_user.Adapter.YeuThichAdapter;
import com.trangchu_user.Class.TaiLieu;
import com.trangchu_user.R;
import com.trangchu_user.TheLoai;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AllFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AllFragment extends Fragment {
    String tendg;
    Integer madg;

    public AllFragment(String tendg, Integer madg) {
        this.tendg = tendg;
        this.madg = madg;
    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private ArrayList<TaiLieu> newArrayList = new ArrayList<>();
    private RecyclerView recycleView;
    private LoadAllAdapter adapter;
    private GridLayoutManager layout;

    // TODO: Rename and change types of parameters
    public AllFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static AllFragment newInstance() {
        AllFragment fragment = new AllFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recycleView = view.findViewById(R.id.rec_allbook);

        layout = new GridLayoutManager(getContext(), 3);
        recycleView = view.findViewById(R.id.rec_allbook);
        recycleView.setHasFixedSize(true);
        recycleView.setLayoutManager(layout);
        adapter = new LoadAllAdapter(newArrayList,tendg,madg);
        adapter.notifyDataSetChanged();
        Data();



    }

    private void Data() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("TaiLieu");
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
                    Integer namxb = dataSnapshot.child("namXB").getValue(Integer.class);
                    Integer sl = dataSnapshot.child("soLuong").getValue(Integer.class);
                    String tenSach = dataSnapshot.child("tenSach").getValue(String.class);
                    String tenHinh = dataSnapshot.child("hinh").getValue(String.class);
                    String MoTa = dataSnapshot.child("moTa").getValue(String.class);
                    String TacGia = dataSnapshot.child("tacGia").getValue(String.class);
                    String TenNXB = dataSnapshot.child("tenNXB").getValue(String.class);
                    Integer hinh = getResources().getIdentifier(tenHinh,"drawable", getActivity().getPackageName());
                    String ha = ""+hinh;
                    TaiLieu tl = new TaiLieu(id,tenSach,namxb,sl,TacGia, ha,MoTa,1,1,TenNXB);
                    newArrayList.add(tl);
                }
                recycleView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "lỗi", Toast.LENGTH_SHORT).show();
            }
        });
    }
}