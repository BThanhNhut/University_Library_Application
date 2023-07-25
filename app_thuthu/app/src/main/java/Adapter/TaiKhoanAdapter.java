package Adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_thuthu.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import Class.TaiKhoanClass;

public class TaiKhoanAdapter extends RecyclerView.Adapter<TaiKhoanAdapter.ViewHolder>{
    private ArrayList<TaiKhoanClass> list;
    DatabaseReference taikhoan;
    DatabaseReference docgia;

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
        loadTaiKhoan();
        TaiKhoanClass d = list.get(position);
        holder.txt_tendocgia.setText(d.getTendocgia());
        holder.txt_username.setText(d.getUsername());
        holder.card_view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder b = new AlertDialog.Builder(view.getContext());
                b.setMessage("Bạn muốn duyệt tài khoản này?");
                b.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        taikhoan.child(String.valueOf(d.getMadocgia())).child("tinhtrang").setValue(true);
                        try {
                            sendMail(d.getUsername());
                        } catch (MessagingException e) {
                            throw new RuntimeException(e);
                        }
                        Toast.makeText(view.getContext(), "Thanh cong", Toast.LENGTH_SHORT).show();
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

    private void loadTaiKhoan()
    {
        taikhoan = FirebaseDatabase.getInstance().getReference().child("TaiKhoan");
        taikhoan.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(list != null)
                {
                    list.clear();
                }
                for(DataSnapshot dataSnapshot: snapshot.getChildren())
                {
                    Boolean tinhtrang =dataSnapshot.child("tinhtrang").getValue(Boolean.class);
                    if(!tinhtrang) {
                        Integer id_dg = dataSnapshot.child("id_DG").getValue(Integer.class);
                        String password = dataSnapshot.child("password").getValue(String.class);
                        String username = dataSnapshot.child("username").getValue(String.class);
                        TaiKhoanClass a = new TaiKhoanClass("", username, password, tinhtrang, id_dg);
                        list.add(a);
                    }
                }
                loadDocGia();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void loadDocGia()
    {
        docgia = FirebaseDatabase.getInstance().getReference().child("DocGia");
        docgia.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren())
                {
                    Integer id_dg = dataSnapshot.child("id").getValue(Integer.class);
                    for(TaiKhoanClass i: list)
                    {
                        if(id_dg == i.getMadocgia())
                        {
                            i.setTendocgia(dataSnapshot.child("tenDG").getValue(String.class));
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void sendMail(String email) throws MessagingException {
        String emailSender = "otheraccofme@gmail.com";
        String password = "acgvwyufxocquddb";

        String stringHost = "smtp.gmail.com";

        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", stringHost);
        properties.put("mail.smtp.port", "465"); //587 // 465
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        javax.mail.Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailSender, password);
            }
        });

        MimeMessage mimeMessage = new MimeMessage(session);
        mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(email));

        mimeMessage.setSubject("Nhóm 07 - Android - Ứng dụng thư viện");
        mimeMessage.setText("Tài khoản của bạn đã được duyệt. Hãy đăng nhập vào app đi nàoooo");
        //mimeMessage.setFrom("Ho Phuong Thao");
        //mimeMessage.setSender("Ho Phuong Thao");


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("Thao 3");
                    Transport.send(mimeMessage);
                    System.out.println("Thao 4");
                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread.start();
    }
}
