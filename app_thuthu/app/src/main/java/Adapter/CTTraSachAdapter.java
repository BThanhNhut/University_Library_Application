package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.app_thuthu.R;

import java.util.List;
import java.util.zip.Inflater;

import Class.CTTraSachClass;

public class CTTraSachAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<CTTraSachClass> list;

    @Override
    public int getCount() {
        return list.size();
    }

    public CTTraSachAdapter() {
    }

    public CTTraSachAdapter(Context context, int layout, List<CTTraSachClass> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
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

        ImageView img = (ImageView) view.findViewById(R.id.ct_hinh_ts);
        TextView tensach = (TextView) view.findViewById(R.id.txt_ct_tensach_ts);
        TextView namxuatban = (TextView) view.findViewById(R.id.txt_ct_namxuatban_ts);
        TextView tacgia = (TextView) view.findViewById(R.id.txt_ct_tacgia_ts);
        CheckBox checkBox = (CheckBox)view.findViewById(R.id.ck_trangthai_ts);

        CTTraSachClass tctra = list.get(i);

        img.setImageResource(Integer.parseInt(tctra.getHinh()));
        tensach.setText(tctra.getTensach());
        namxuatban.setText(String.valueOf(tctra.getNamxuatban()));
        tacgia.setText(tctra.getTacgia());
        checkBox.setSelected(tctra.isTinhtrang());


        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(!checkBox.isChecked())
                {
                    tctra.setTinhtrang(false);
                }
                else
                {
                    tctra.setTinhtrang(true);
                }
            }
        });
        return view;
    }
}
