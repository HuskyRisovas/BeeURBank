package com.example.mathr.burb01.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mathr.burb01.Interface.ItemClickListener;
import com.example.mathr.burb01.Modelos.Conquistas;
import com.example.mathr.burb01.Modelos.Guias;
import com.example.mathr.burb01.R;
import com.example.mathr.burb01.ViewHolder.ConquistasViewHolder;
import com.example.mathr.burb01.ViewHolder.GuiasViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class ConquistasFragment extends Fragment {

    FirebaseDatabase database;
    DatabaseReference conquistas;

    RecyclerView recycler_conquistas;
    RecyclerView.LayoutManager layoutManager;

    FirebaseRecyclerAdapter<Conquistas,ConquistasViewHolder> conquistasAdapter;


    public ConquistasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_conquistas, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        database = FirebaseDatabase.getInstance();
        conquistas = database.getReference("Conquistas");

        recycler_conquistas = (RecyclerView)getView().findViewById(R.id.recycler_conquistas);
        recycler_conquistas.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recycler_conquistas.setLayoutManager(layoutManager);

        loadConquistas();
    }

    private void loadConquistas()
    {
        conquistasAdapter = new FirebaseRecyclerAdapter<Conquistas, ConquistasViewHolder>(Conquistas.class,R.layout.conquistas_item,ConquistasViewHolder.class,conquistas) {
            @Override
            protected void populateViewHolder(final ConquistasViewHolder viewHolder, final Conquistas model, int position) {
                viewHolder.txtNivel.setText(model.getNivel());
                viewHolder.txtNomeConquista.setText(model.getNome());
                viewHolder.txtDescricaoConquista.setText(model.getDescricao());
                //Picasso.with(getActivity()).load(model.getImagem()).into(viewHolder.imgConquista);
                Picasso.with(getActivity())
                        .load(model.getImagem()).resize(480,854).centerInside()
                        .networkPolicy(NetworkPolicy.OFFLINE)
                        .into(viewHolder.imgConquista, new Callback() {
                            @Override
                            public void onSuccess() {

                            }

                            @Override
                            public void onError() {
                                //Try again online if cache failed
                                Picasso.with(getActivity())
                                        .load(model.getImagem()).resize(480,854)
                                        .error(R.drawable.ic_add_black_24dp)
                                        .into(viewHolder.imgConquista, new Callback() {
                                            @Override
                                            public void onSuccess() {

                                            }

                                            @Override
                                            public void onError() {
                                                Log.v("Picasso","Could not fetch image");
                                            }
                                        });
                            }
                        });
                final Conquistas clickItem = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                    }
                });
            }
        };
        recycler_conquistas.setAdapter(conquistasAdapter);
    }

}
