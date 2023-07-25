package Adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.app_thuthu.R;

import java.util.ArrayList;
import java.util.List;

import Class.CTMuonSachClass;

public class CTMuonSachAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<CTMuonSachClass> list;

    public CTMuonSachAdapter(Context context, int layout, List<CTMuonSachClass> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(layout,null);

        ImageView img = (ImageView) view.findViewById(R.id.ct_hinh);
        TextView tensach = (TextView) view.findViewById(R.id.txt_ct_tensach);
        TextView namxuatban = (TextView) view.findViewById(R.id.txt_ct_namxuatban);
        TextView tacgia = (TextView) view.findViewById(R.id.txt_ct_tacgia);

        CTMuonSachClass ctmuon = list.get(i);

        img.setImageResource(Integer.parseInt(ctmuon.getHinh()));
        tensach.setText(ctmuon.getTensach());
        namxuatban.setText(String.valueOf(ctmuon.getNamxuatban()));
        tacgia.setText(ctmuon.getTacgia());

        return view;
    }
}
