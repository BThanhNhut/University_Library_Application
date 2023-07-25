package com.trangchu_user;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import com.trangchu_user.Class.ChiTietMuonTra;
import com.trangchu_user.Class.MuonTra;


public class Nhut_thongke1 extends AppCompatActivity {

    TextView tvR1, tvR2;
    String chuoiNgayBD = "0/0/0", chuoiNgayKT = "0/0/0";
    ArrayList<ChiTietMuonTra> lst_ctmt = new ArrayList<>();
    ArrayList<MuonTra> lst_mt = new ArrayList<>();
    EditText edt_ngaybd, edt_ngaykt;
    BarChart bar_chart;
    ArrayList<BarEntry> barEntries1 = new ArrayList<>();
    ArrayList<BarEntry> barEntries2 = new ArrayList<>();

    DatabaseReference database;

    int[] a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhut_thongke1);
        getSupportActionBar().hide();
        addControl();
        loadPhieuMuon(1);
        loadChiTietMuon();
        edt_ngaybd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chonNgayBD();
            }
        });
        edt_ngaykt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chonNgayKT();
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
    }

    private  void taoDuLieu(String[] months)
    {
        System.out.println(barEntries1.size());
        System.out.println(barEntries1.size());
        System.out.println("Chay thanh cong");
        BarDataSet barDataSet1 = new BarDataSet(barEntries1,"Sách mượn");
        barDataSet1.setColor(0xFF2196F3);
        BarDataSet barDataSet2 = new BarDataSet(barEntries2,"Sách trả");
        barDataSet2.setColor(0xFF4CAF50);

        BarData data = new BarData(barDataSet1, barDataSet2);
        bar_chart.setData(data);

//        String[] months = new String[]{"1","2","3","4","5","6","7","8","9","10","11","12"};

        XAxis xAxis = bar_chart.getXAxis();

        xAxis.setValueFormatter(new IndexAxisValueFormatter(months));
        xAxis.setCenterAxisLabels(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1);
        xAxis.setGranularityEnabled(true);


        bar_chart.setDragEnabled(true);
        bar_chart.setVisibleXRangeMaximum(3);

        float barSpace = 0.10f;
        float groupSpace = 0.40f;
        data.setBarWidth(0.20f);

        bar_chart.getXAxis().setAxisMinimum(0);
        bar_chart.getXAxis().setAxisMaximum(0+bar_chart.getBarData().getGroupWidth(groupSpace,barSpace)*12);
        bar_chart.getAxisLeft().setAxisMinimum(0);

        bar_chart.groupBars(0,groupSpace, barSpace);
        bar_chart.invalidate();
        bar_chart.setDescription(null);

    }

    private void loadPhieuMuon (int iddg)
    {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference node = firebaseDatabase.getReference("MuonTra");

        node.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(lst_mt != null)
                {
                    lst_mt.clear();
                }
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
        database = FirebaseDatabase.getInstance().getReference().child("ChiTietMuonTra");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(lst_ctmt != null)
                {
                    lst_ctmt.clear();
                }
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
                    System.out.println("ID : "+ id_MT);
                    lst_ctmt.add(ctmt);
                }
                System.out.println("Dai chi tiet muon : "+ lst_ctmt.size());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void chonNgayBD()
    {
        Calendar calendar = Calendar.getInstance();
        int ngay, thang, nam;
        ngay = calendar.get(Calendar.DATE);
        thang = calendar.get(Calendar.MONTH);
        nam = calendar.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, R.style.MyDatePickerDialog, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calendar.set(i,i1,i2);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                edt_ngaybd.setText(simpleDateFormat.format(calendar.getTime()));
                chuoiNgayBD = simpleDateFormat.format(calendar.getTime()).toString();
                thongKe();
            }
        },nam,thang,ngay);
        datePickerDialog.show();
    }
    private void chonNgayKT()
    {

        Calendar calendar = Calendar.getInstance();
        int ngay, thang, nam;
        ngay = calendar.get(Calendar.DATE);
        thang = calendar.get(Calendar.MONTH);
        nam = calendar.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,R.style.MyDatePickerDialog, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calendar.set(i,i1,i2);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                edt_ngaykt.setText(simpleDateFormat.format(calendar.getTime()));
                chuoiNgayKT = simpleDateFormat.format(calendar.getTime()).toString();
                thongKe();
            }
        },nam,thang,ngay);
        datePickerDialog.show();
    }

    private void thongKe ()
    {
        String[] month = new String[15];
        barEntries1 = new ArrayList<>();
        barEntries2 = new ArrayList<>();

        String [] chuoiNgayBatDau = chuoiNgayBD.split("/");
        String [] chuoiNgayKetThuc = chuoiNgayKT.split("/");

        int tatCaSachMuon = 0;
        int tatCaSachTra = 0;

        int nbd = Integer.valueOf(chuoiNgayBatDau[2]);
        int nkt = Integer.valueOf(chuoiNgayKetThuc[2]);
        int tbd = Integer.valueOf(chuoiNgayBatDau[1]);
        int tkt = Integer.valueOf(chuoiNgayKetThuc[1]);

        System.out.println("Nam bat dau = "+ nbd);
        System.out.println("Nam ket thuc"+ nkt);



        if(nbd == nkt && tkt - tbd >= 0)
        {
            int min = 100;
            int min1 = 0, max1 = 0;
            int max = 0;
            int j = 0;
            if(month.length >= 0)
            {
                Arrays.fill(month, null);
            }
            for (int i= tbd; i <= tkt; i++)
            {
                month[j] = String.valueOf(i);
                j++;
                int sosachmuon = 0;
                int sosachtra = 0;
                for(MuonTra mt : lst_mt)
                {
                    System.out.println("Vao danh sach muon tra");
                    String[] thangmt = mt.getNgayMuon().toString().split("/");
                    System.out.println("Thang muon tra " + mt.getNgayMuon().toString());
                    int Ma_mt = mt.getID();
                    int tmt = Integer.valueOf(thangmt[1]);

                    if(tmt == i && mt.getID_DG() == 1)
                    {
                        int thang;
                        System.out.println("Thang muon tra " + tmt +"Ma muon tra " + Ma_mt + "Va i =" + i);
                        for(ChiTietMuonTra ctmt : lst_ctmt)
                        {
                            if(ctmt.getID_MT() == Ma_mt && ctmt.isTinhTrangTra() == false)
                            {
                                sosachmuon++;
                            }
                            if(ctmt.getID_MT() == Ma_mt && ctmt.isTinhTrangTra() == true)
                            {
                                String chuoiThang;
                                chuoiThang = ctmt.getNgayTra();
                                System.out.println("Ket qua laaaaaaaaaaaaaaaaaa " + chuoiThang);
                                String[] Thang = chuoiThang.split("/");
                                thang = Integer.valueOf(Thang[1]);
                                System.out.println("alooooooooooooooooooooooo = " + thang);
                                if(thang == i)
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
                System.out.println("So sach thang muon " + i + "So luong" + sosachmuon);
                System.out.println("So sach thang tra " + i + "So luong" + sosachtra);
                tvR1.setText(String.valueOf(max1));
                tvR2.setText(String.valueOf(min1));
                taoDuLieu(month);

            }
        }
    }



}