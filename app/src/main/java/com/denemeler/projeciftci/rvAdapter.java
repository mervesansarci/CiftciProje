package com.denemeler.projeciftci;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;


import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class rvAdapter extends FirestoreRecyclerAdapter<Tarlalar,rvAdapter.CardViewTutucu> {

    private OnItemClickListener listener;

//    Context mContext = null;
//    List<Tarlalar> tarlaListe;


    public rvAdapter(FirestoreRecyclerOptions<Tarlalar> options) {
        super(options);
//        this.mContext = mContext;
//        this.tarlaListe = tarlaListe;

    }

    @NonNull
    @Override
    public CardViewTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_tasarim,parent,false);

        return new CardViewTutucu(itemView);
    }

    public void deleteItem(int position){
        getSnapshots().getSnapshot(position).getReference().delete();
    }


    @Override
    protected void onBindViewHolder(@NonNull CardViewTutucu holder, int position, @NonNull Tarlalar model) {
        holder.tarlaekleme.setText(model.getTarlaAdi());
        holder.tarlaKonumEkleme.setText(model.getTarlaKonum());

    }

    public class CardViewTutucu extends RecyclerView.ViewHolder{

        public TextView tarlaekleme, tarlaKonumEkleme;
        public CardView cardView;
        public ImageView noktaResim;

        public CardViewTutucu(View view){
            super(view);
            tarlaekleme= view.findViewById(R.id.textTarlaIsmı);
            tarlaKonumEkleme= view.findViewById(R.id.textTarlaKonum);
            cardView=view.findViewById(R.id.cv);
            noktaResim=view.findViewById(R.id.imageViewNokta);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position= getAdapterPosition();
                    if (position!=RecyclerView.NO_POSITION && listener != null)
                        listener.OnItemClick(getSnapshots().getSnapshot(position),position);

                }
            });
        }
    }

    public interface OnItemClickListener{
        void OnItemClick(DocumentSnapshot documentSnapshot,int position);


    }
    public void setOnClickListener(OnItemClickListener listener){
        this.listener=listener;

    }







}
