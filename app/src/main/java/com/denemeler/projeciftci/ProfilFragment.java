package com.denemeler.projeciftci;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.FirestoreRegistrar;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class ProfilFragment extends Fragment implements rvAdapter.OnItemClickListener {
    FirebaseFirestore db= FirebaseFirestore.getInstance();
    CollectionReference tarlaRef= db.collection("Tarlalar");
    ArrayList<Tarlalar> eklenenTarla;
    RecyclerView rv;
    rvAdapter adapter;
    FirebaseFirestore database;
    FirebaseAuth mAuth;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_profil, container, false);

        Button btnTarlaEkle = v.findViewById(R.id.btn_tarlaekle);
        TextView isim=v.findViewById(R.id.txtIsım);
        rv= v.findViewById(R.id.rv_tarla);


        eklenenTarla= new ArrayList<Tarlalar>();
        //adapter= new rvAdapter(getActivity(),eklenenTarla);

        setUpRecyclerView();

        btnTarlaEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(v.getContext(),YeniTarlaActivity.class));

            }
        });

        return v;
    }

    private void setUpRecyclerView() {

        Query query= tarlaRef.orderBy("tarlaKonum",Query.Direction.DESCENDING);
        FirestoreRecyclerOptions<Tarlalar> options= new FirestoreRecyclerOptions.Builder<Tarlalar>().setQuery(query,Tarlalar.class).build();

        adapter= new rvAdapter(options);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setAdapter(adapter);
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                adapter.deleteItem(viewHolder.getAdapterPosition());

            }
        }).attachToRecyclerView(rv);
        adapter.setOnClickListener(new rvAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(DocumentSnapshot documentSnapshot, int position) {
                Tarlalar tarlalar=documentSnapshot.toObject(Tarlalar.class);
                String id= documentSnapshot.getId();
                String path= documentSnapshot.getReference().getPath();
                Toast.makeText(getActivity(), "AAAAAA", Toast.LENGTH_SHORT).show();



            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public void OnItemClick(DocumentSnapshot documentSnapshot, int position) {

    }
}
