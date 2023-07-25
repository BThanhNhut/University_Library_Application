package Adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.app_thuthu.R;

import java.util.ArrayList;
import Class.TaiKhoanClass;

public class TaiKhoanAdapter extends RecyclerView.Adapter<TaiKhoanAdapter.ViewHolder>{
    private ArrayList<TaiKhoanClass> list;

    public TaiKhoanAdapter(ArrayList<TaiKhoanClass> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.thongtintaikhoan, parent, false);
        return new TaiKhoanAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TaiKhoanClass d = list.get(position);
        holder.txt_tendocgia.setText(d.getTendocgia());
        holder.txt_username.setText(d.getUsername());
        holder.card_view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder b = new AlertDialog.Builder(view.getContext());
                b.setMessage("Bạn muốn duyệt tài khoản này ?");
                b.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // bà muốn xử lý gì xử lý nhe
                    }
                });
                b.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // bà muốn xử lý gì xử lý nhe
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = b.create();
                dialog.show();
                return true;
            }
        });
        holder.card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Xin Chào");
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView txt_tendocgia, txt_username;
        public RelativeLayout card_view;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_tendocgia = itemView.findViewById(R.id.txt_tendocgia_tk);
            txt_username = itemView.findViewById(R.id.txt_username_tk);
            card_view = itemView.findViewById(R.id.card_view);
        }
    }
}
