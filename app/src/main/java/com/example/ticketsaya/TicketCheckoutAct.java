package com.example.ticketsaya;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Random;

public class TicketCheckoutAct extends AppCompatActivity {

    Button btn_buy_ticket, btn_mines, btn_plus;
    TextView text_jumlah_ticket, text_total_balance, text_total_harga, nama_wisata, lokasi, ketentuan;
    Integer valueJumlaHTicket = 1;
    Integer myBalance = 0;
    Integer valuetotalHarga = 0;
    Integer valuehargaTicket = 0;
    ImageView notice_uang;
    Integer sisa_balance = 0;

    DatabaseReference reference, reference2, reference3, reference4;

    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String username_key_new = "";

    String date_wisata;
    String time_wisata;

    // Generate nomor secara random(acak)
    // karena harus secara unique
    Integer nomor_transaksi = new Random().nextInt();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_checkout);

        getUsernameLocal();

        // mengambil data dari Intent
        Bundle bundle = getIntent().getExtras();
        final String jenis_tiket_baru = bundle.getString("jenis_tiket");

        btn_mines = findViewById(R.id.btn_mines);
        btn_plus = findViewById(R.id.btn_plus);
        text_jumlah_ticket = findViewById(R.id.text_jumlah_ticket);
        btn_buy_ticket = findViewById(R.id.btn_buy_ticket);
        notice_uang = findViewById(R.id.notice_uang);

        nama_wisata = findViewById(R.id.nama_wisata);
        lokasi = findViewById(R.id.lokasi);
        ketentuan = findViewById(R.id.ketentuan);

        text_total_balance = findViewById(R.id.text_total_balance);
        text_total_harga = findViewById(R.id.text_total_harga);

        // setting value baru untuk beberapa komponen
        text_jumlah_ticket.setText(valueJumlaHTicket.toString());

        // secara default hide btn_mines
        btn_mines.animate().alpha(0).setDuration(300).start();
        btn_mines.setEnabled(false);
        notice_uang.setVisibility(View.GONE);

        // mengambil data user_balance dari database
        reference2 = FirebaseDatabase.getInstance().getReference().child("Users").child(username_key_new);
        reference2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                myBalance = Integer.valueOf(dataSnapshot.child("user_balance").getValue().toString());
                text_total_balance.setText("US$ " + myBalance + "");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        // mengambil data dari firebase berdasarkan Intent
        reference = FirebaseDatabase.getInstance().getReference().child("Wisata").child(jenis_tiket_baru);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // menimpa data yang ada dengan data yang baru
                nama_wisata.setText(dataSnapshot.child("nama_wisata").getValue().toString());
                lokasi.setText(dataSnapshot.child("lokasi").getValue().toString());
                ketentuan.setText(dataSnapshot.child("ketentuan").getValue().toString());

                date_wisata = dataSnapshot.child("date_wisata").getValue().toString();
                time_wisata = dataSnapshot.child("time_wisata").getValue().toString();

                valuehargaTicket = Integer.valueOf(dataSnapshot.child("harga_tiket").getValue().toString());

                valuetotalHarga = valuehargaTicket * valueJumlaHTicket;
                text_total_harga.setText("US$" + valuetotalHarga + "");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueJumlaHTicket+=1;
                text_jumlah_ticket.setText(valueJumlaHTicket.toString());
                if (valueJumlaHTicket > 1) {
                    btn_mines.animate().alpha(1).setDuration(300).start();
                    btn_mines.setEnabled(true);
                }
                valuetotalHarga = valuehargaTicket * valueJumlaHTicket;
                text_total_harga.setText("US$" + valuetotalHarga + "");
                if (valuetotalHarga > myBalance) {
                    btn_buy_ticket.animate().translationY(250).alpha(0).setDuration(350).start();
                    btn_buy_ticket.setEnabled(false);
                    text_total_balance.setTextColor(Color.parseColor("#D1206B"));
                    notice_uang.setVisibility(View.VISIBLE);
                }
            }
        });

        btn_mines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueJumlaHTicket-=1;
                text_jumlah_ticket.setText(valueJumlaHTicket.toString());
                if (valueJumlaHTicket < 2) {
                    btn_mines.animate().alpha(0).setDuration(300).start();
                    btn_mines.setEnabled(false);
                }
                valuetotalHarga = valuehargaTicket * valueJumlaHTicket;
                text_total_harga.setText("US$" + valuetotalHarga + "");
                if (valuetotalHarga < myBalance) {
                    btn_buy_ticket.animate().translationY(0).alpha(1).setDuration(350).start();
                    btn_buy_ticket.setEnabled(true);
                    text_total_balance.setTextColor(Color.parseColor("#203DD1"));
                    notice_uang.setVisibility(View.GONE);
                }
            }
        });

        btn_buy_ticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // menyimpan data user kepada firebase database dan membuat tale baru MyTickets
                reference3 = FirebaseDatabase.getInstance()
                        .getReference().child("MyTickets").child(username_key_new)
                        .child(nama_wisata.getText().toString() + nomor_transaksi);
                reference3.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        reference3.getRef().child("id_ticket").setValue(nama_wisata.getText().toString() + nomor_transaksi);
                        reference3.getRef().child("nama_wisata").setValue(nama_wisata.getText().toString());
                        reference3.getRef().child("lokasi").setValue(lokasi.getText().toString());
                        reference3.getRef().child("ketentuan").setValue(ketentuan.getText().toString());
                        reference3.getRef().child("jumlah_tiket").setValue(valueJumlaHTicket.toString());
                        reference3.getRef().child("date_wisata").setValue(date_wisata);
                        reference3.getRef().child("time_wisata").setValue(time_wisata);

                        Intent goToSuccessTicket = new Intent(TicketCheckoutAct.this, SuccessBuyTicketAct.class);
                        startActivity(goToSuccessTicket);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                // update data balance kepada users (yang saat ini login)
                // mengambil data users dari firebase
                reference4 = FirebaseDatabase.getInstance().getReference().child("Users").child(username_key_new);
                reference4.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        sisa_balance = myBalance - valuetotalHarga;
                        reference4.getRef().child("user_balance").setValue(sisa_balance);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }

    public void getUsernameLocal(){
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key, "");
    }
}
