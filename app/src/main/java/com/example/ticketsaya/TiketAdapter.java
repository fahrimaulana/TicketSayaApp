package com.example.ticketsaya;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TiketAdapter extends RecyclerView.Adapter<TiketAdapter.MyViewHolder> {

    Context context;
    ArrayList<MyTicket> myTicket;

    public TiketAdapter(Context c, ArrayList<MyTicket> p){
        context = c;
        myTicket = p;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.
                from(context).inflate(R.layout.item_myticket,
                viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        myViewHolder.xnama_wisata.setText(myTicket.get(position).getNama_wisata());
        myViewHolder.xlokasi.setText(myTicket.get(position).getLokasi());
        myViewHolder.xjumlah_tiket.setText(myTicket.get(position).getJumlah_tiket() + "Tickets");

        final String getNamaWisata = myTicket.get(position).getNama_wisata();

        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToMyTiketDetails = new Intent(context,MyTicketDetailAct.class);
                goToMyTiketDetails.putExtra("nama_wisata", getNamaWisata);
                context.startActivity(goToMyTiketDetails);
            }
        });
    }

    @Override
    public int getItemCount() {
        return myTicket.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView xnama_wisata, xlokasi, xjumlah_tiket;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            xnama_wisata = itemView.findViewById(R.id.xnama_wisata);
            xlokasi = itemView.findViewById(R.id.xlokasi);
            xjumlah_tiket = itemView.findViewById(R.id.xjumlah_tiket);
        }
    }
}
