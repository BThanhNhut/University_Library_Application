package com.trangchu_user;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.trangchu_user.Class.MuonTra;
import com.trangchu_user.Class.ChiTietMuonTra;


public class Nhut_thongke2 extends AppCompatActivity {

    Button btnThongKe;
    TextView tvR1, tvR2;
    String chuoiNgayBD = "0/0/0", chuoiNgayKT = "0/0/0";
    ArrayList<ChiTietMuonTra> lst_ctmt = new ArrayList<>();
    ArrayList<MuonTra> lst_mt = new ArrayList<>();
    EditText edt_ngaybd, edt_ngaykt;
    BarChart bar_chart;
    ArrayList<BarEntry> barEntries1 = new ArrayList<>();
    ArrayList<BarEntry> barEntries2 = new ArrayList<>();
    int yearNow = Calendar.getInstance().get(Calendar.YEAR);

    int[] a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhut_thongke2);
        getSupportActionBar().hide();
        addControl();
        loadPhieuMuon(1);
        loadChiTietMuon();


        // Kiểm tra dữ liệu

        edt_ngaybd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 4) {
                    edt_ngaybd.setError("Vui lòng nhập tối đa 4 số");
                }
                else if (!charSequence.toString().isEmpty() && charSequence.length()>=4)
                {
                    int number = Integer.parseInt(charSequence.toString());
                    if (number >= yearNow - 5 && number <= yearNow + 5 )
                    {
                        edt_ngaybd.setError(null);
                    }
                    else
                    {
                        edt_ngaybd.setError("Vui lòng nhập số từ "+ (yearNow - 5) +" đến "+(yearNow+5)+"");
                    }
                }
                else
                {
                    edt_ngaybd.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        edt_ngaykt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 4) {
                    edt_ngaybd.setError("Vui lòng nhập tối đa 4 số");
                } else {
                    edt_ngaybd.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        btnThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input1 = edt_ngaybd.getText().toString().trim();
                String input2 = edt_ngaykt.getText().toString().trim();
                if (input1.isEmpty()) {
                    edt_ngaybd.setError("Không được để trống");
                }
                else if(input2.isEmpty())
                {
                    edt_ngaykt.setError("Không được để trống");
                }
                else if(input2.length() < 4)
                {
                    edt_ngaykt.setError("Năm không hợp lệ");
                }
                else if(input1.length() < 4)
                {
                    edt_ngaybd.setError("Năm không hợp lệ");
                }
                else
                {
                    thongKe();
                }
            }
        });

    }

    private void addControl()
    {
        tvR1 = (TextView)findViewById(R.id.tvR);
        tvR2 = (TextView)findViewById(R.id.tvPython);
        bar_chart = (BarChart) findViewById(R.id.bar_chart);
        edt_ngaybd = (EditText) findViewById(R.id.edt_ngaybd);
        edt_ngaykt = (EditText) findViewById(R.id.edt_ngaykt);
        btnThongKe = (Button) findViewById(R.id.btnThongKe);
    }

    private  void taoDuLieu()
    {
        System.out.println(barEntries1.size());
        System.out.println(barEntries1.size());
        System.out.println("Chay thanh conggggggggggggggggggggggggggggggggggggggggggggggggggg");
        BarDataSet barDataSet1 = new BarDataSet(barEntries1,"Sách mượn");
        barDataSet1.setColor(0xFF2196F3);
        BarDataSet barDataSet2 = new BarDataSet(barEntries2,"Sách trả");
        barDataSet2.setColor(0xFF4CAF50);

        BarData data = new BarData(barDataSet1, barDataSet2);
        bar_chart.setData(data);

        String[] months = new String[]{"2020","2021","2022","2023","2024","2025","2026"};
        XAxis xAxis = bar_chart.getXAxis();

        xAxis.setValueFormatter(new IndexAxisValueFormatter(months));
        xAxis.setCenterAxisLabels(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1);
        xAxis.setGranularityEnabled(true);


        bar_chart.setDragEnabled(true);
        bar_chart.setVisibleXRangeMaximum(3);

        float barSpace = 0.10f; // Khoảng cách giữa các cột trong cùng một nhóm
        float groupSpace = 0.40f; // Khoảng cách giữa các nhóm cột
        data.setBarWidth(0.20f); // Chiều rộng của mỗi cột

        bar_chart.getXAxis().setAxisMinimum(0);
        bar_chart.getXAxis().setAxisMaximum(0+bar_chart.getBarData().getGroupWidth(groupSpace,barSpace)*12);
        bar_chart.getAxisLeft().setAxisMinimum(0);

        bar_chart.groupBars(0,groupSpace, barSpace);
        bar_chart.invalidate();

    }

    private void loadPhieuMuon (int iddg)
    {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference node = firebaseDatabase.getReference("MuonTra");

        node.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    int id_dg = dataSnapshot.child("id_DG").getValue(Integer.class);
                    if(id_dg == iddg)
                    {
                        int id = dataSnapshot.child("id").getValue(Integer.class);
                        String ngayMuon = dataSnapshot.child("ngayMuon").getValue(String.class);
                        Boolean tinhTrang = dataSnapshot.child("tinhTrang").getValue(Boolean.class);
                        MuonTra mt = new MuonTra(id,ngayMuon,tinhTrang,id_dg);
                        lst_mt.add(mt);
                    }
                }
                System.out.println("Dai muon tra :" + lst_mt.size());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void loadChiTietMuon ()
    {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference node = firebaseDatabase.getReference("ChiTietMuonTra");

        node.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    int id_MT = dataSnapshot.child("id_MT").getValue(Integer.class);
                    int id_Sach = dataSnapshot.child("id_Sach").getValue(Integer.class);
                    String tinhTrangSach = dataSnapshot.child("tinhTrangSach").getValue(String.class);
                    Boolean tinhTrangTra = dataSnapshot.child("tinhTrangTra").getValue(Boolean.class);
                    String ngayTra = dataSnapshot.child("ngayTra").getValue(String.class);
                    if(ngayTra == null)
                    {
                        ngayTra ="0/0/0";
                    }
                    ChiTietMuonTra ctmt = new ChiTietMuonTra(ngayTra,tinhTrangSach,tinhTrangTra,id_MT,id_Sach);
                    lst_ctmt.add(ctmt);
                }
                System.out.println("Dai chi tiet muon : "+ lst_ctmt.size());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    private void thongKe ()
    {
        String year[] = new String[2500];

        barEntries1 = new ArrayList<>();
        barEntries2 = new ArrayList<>();

        String [] chuoiNgayBatDau = chuoiNgayBD.split("/");
        String [] chuoiNgayKetThuc = chuoiNgayKT.split("/");

        int tatCaSachMuon = 0;
        int tatCaSachTra = 0;

        int nbd = Integer.valueOf(edt_ngaybd.getText().toString());
        int nkt = Integer.valueOf(edt_ngaykt.getText().toString());

        int tbd = Integer.valueOf(chuoiNgayBatDau[1]);
        int tkt = Integer.valueOf(chuoiNgayKetThuc[1]);

        System.out.println("Nam bat dau = "+ nbd);
        System.out.println("Nam ket thuc"+ nkt);



        if(nbd >= yearNow - 5 && nkt <= yearNow + 5 && nkt - nbd >= 0)
        {

            System.out.println("Nam la");
            int min = 100;
            int min1 = 0, max1 = 0;
            int max = 0;
            int j = 0;
            for (int i= nbd; i <= nkt; i++)
            {
                year[j] = "202"+ i;
                j++;
                System.out.println("Nam thu " + year[i]);
                int sosachmuon = 0;
                int sosachtra = 0;
                for(MuonTra mt : lst_mt)
                {
                    String[] thangmt = mt.getNgayMuon().toString().split("/");
                    int Ma_mt = mt.getID();
                    int nmt = Integer.valueOf(thangmt[2]);
                    System.out.println("Thang muon tra " + nmt +"Ma muon tra " + Ma_mt);
                    if(nmt == i)
                    {
                        System.out.println("Thang muon tra " + nmt +"Ma muon tra " + Ma_mt + "Va i =" + i);
                        for(ChiTietMuonTra ctmt : lst_ctmt)
                        {
                            if(ctmt.getID_MT() == Ma_mt && ctmt.isTinhTrangTra() == false)
                            {
                                sosachmuon++;
                            }
                            if(ctmt.getID_MT() == Ma_mt && ctmt.isTinhTrangTra() == true)
                            {
                                String chuoiNam;
                                chuoiNam = ctmt.getNgayTra();
                                System.out.println("Ket qua laaaaaaaaaaaaaaaaaa " + chuoiNam);
                                String[] Thang = chuoiNam.split("/");
                                int Nam = Integer.valueOf(Thang[2]);
                                System.out.println("alooooooooooooooooooooooo = " + Nam);
                                if(Nam == i)
                                {
                                    sosachtra++;
                                }
                                if(min > sosachtra)
                                {
                                    min = sosachtra;
                                    min1 = i;
                                }
                            }
                        }
                        if(max < sosachmuon)
                        {
                            max = sosachmuon;
                            max1 = i;
                        }
                    }
                }
                barEntries1.add(new BarEntry(i,sosachmuon));
                barEntries2.add(new BarEntry(i,sosachtra));
                System.out.println("So sach max = " + max1);
                System.out.println("So sach max = " + min1);
                System.out.println("So sach cua nam" + i + "So luong" + sosachmuon);
                System.out.println("So sach tra cua nam " + i + "So luong" + sosachtra);
                tvR1.setText(String.valueOf(max1));
                tvR2.setText(String.valueOf(min1));
                taoDuLieu();

            }
            //Len bieu do ngay khuc nay
        }
    }



}
