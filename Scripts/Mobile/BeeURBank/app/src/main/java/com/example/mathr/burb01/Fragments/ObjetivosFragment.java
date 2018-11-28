package com.example.mathr.burb01.Fragments;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mathr.burb01.Interface.ItemClickListener;
import com.example.mathr.burb01.Modelos.Objetivos;
import com.example.mathr.burb01.R;
import com.example.mathr.burb01.ViewHolder.ObjetivosViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import info.hoang8f.widget.FButton;

/**
 * A simple {@link Fragment} subclass.
 */
public class ObjetivosFragment extends Fragment {

    FirebaseDatabase database;
    DatabaseReference objetivos, user, info;

    FirebaseAuth mAuth;

    FloatingActionButton add;

    String UID;

    RecyclerView recycler_objetivos;
    RecyclerView.LayoutManager layoutManager;

    FirebaseRecyclerAdapter<Objetivos, ObjetivosViewHolder> objetivosAdapter;

    public ObjetivosFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_objetivos, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        UID = mAuth.getCurrentUser().getUid();
        database = FirebaseDatabase.getInstance();
        user = database.getReference("User");
        objetivos = user.child(UID).child("Objetivos");
        info = user.child(UID);
        recycler_objetivos = (RecyclerView) getView().findViewById(R.id.recycler_objetivos);
        recycler_objetivos.setHasFixedSize(false);
        layoutManager = new LinearLayoutManager(getContext());
        recycler_objetivos.setLayoutManager(layoutManager);
        add = (FloatingActionButton) view.findViewById(R.id.addObjetivo);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
                View mView = getLayoutInflater().inflate(R.layout.add_objetivo_item, null);
                final EditText edtNome = (EditText) mView.findViewById(R.id.edtNomeObjetivo);
                final EditText edtValor = (EditText) mView.findViewById(R.id.edtValorObjetivo);
                final EditText edtData = (EditText) mView.findViewById(R.id.edtDataObjetivo);
                FButton btnAdicionarObjetivo = (FButton) mView.findViewById(R.id.btnAddObjetivo);
                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();
                btnAdicionarObjetivo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Objetivos objetivos = new Objetivos(edtNome.getText().toString(), edtData.getText().toString(), Float.parseFloat(edtValor.getText().toString()));
                        user.child(UID).child("Objetivos").push().setValue(objetivos);
                        Toast.makeText(getActivity(), "Objetivo adicionado com sucesso!", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
            }
        });
        loadObjetivos();
    }

    private void loadObjetivos() {
        objetivosAdapter = new FirebaseRecyclerAdapter<Objetivos, ObjetivosViewHolder>(Objetivos.class, R.layout.objetivos_item, ObjetivosViewHolder.class, objetivos) {
            @Override
            protected void populateViewHolder(ObjetivosViewHolder viewHolder, Objetivos model, int position) {
                viewHolder.txtNome.setText(model.getNome());
                viewHolder.txtValor.setText(String.valueOf(model.getValor()));
                viewHolder.txtData.setText(model.getData());
                final Objetivos clickItem = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        user.child(UID).child("Objetivos").child(objetivosAdapter.getRef(position).getKey()).removeValue();
                    }
                });
            }
        };
        recycler_objetivos.setAdapter(objetivosAdapter);
    }
}
