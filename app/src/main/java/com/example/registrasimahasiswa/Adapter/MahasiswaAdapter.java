package com.example.registrasimahasiswa.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.registrasimahasiswa.Model.Data.MahasiswaModel;
import com.example.registrasimahasiswa.R;

import java.util.List;

public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaAdapter.HolderData> {
    private Context ctx;
    private List<MahasiswaModel> listModel;

    //Construktor
    public MahasiswaAdapter(Context ctx, List<MahasiswaModel> listModel) {
        this.ctx = ctx;
        this.listModel = listModel;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        Melakukan inflate kedalam layout cardView dalam contoh ini card_item_mahasiswa
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_mahasiswa, parent, false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        MahasiswaModel model = listModel.get(position);
        holder.tvid.setText(String.valueOf(model.getId()));
        holder.nama.setText(model.getNama());
        holder.alamat.setText(model.getAlamat());
        holder.telp.setText(model.getTelp());
        holder.nim.setText(String.valueOf(model.getNim()));
        Glide.with(holder.itemView)
                .load(model.getAvatar())
                .into(holder.avatar);
    }

    @Override
    public int getItemCount() {
        return listModel.size();
    }

    //    Membuat Holder Data
//    Holder berguna untuk memasukkan datanya kedalam
//    Card View
        public class HolderData extends RecyclerView.ViewHolder{
        TextView tvid, nama, nim, telp,alamat;
        ImageView avatar;

        public HolderData(@NonNull View itemView) {
            super(itemView);

            nama = itemView.findViewById(R.id.tv_nama);
            nim = itemView.findViewById(R.id.tv_nim);
            alamat = itemView.findViewById(R.id.tv_alamat);
            telp = itemView.findViewById(R.id.tv_telp);
            avatar = itemView.findViewById(R.id.avatar);
            tvid = itemView.findViewById(R.id.id_mahasiswa);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    // item clicked
                    Toast.makeText(ctx, String.valueOf(nama.getText()), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


}
