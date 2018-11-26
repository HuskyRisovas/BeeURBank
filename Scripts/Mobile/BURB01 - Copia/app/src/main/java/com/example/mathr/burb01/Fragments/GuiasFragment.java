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
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mathr.burb01.GuiasInfoActivity;
import com.example.mathr.burb01.Interface.ItemClickListener;
import com.example.mathr.burb01.Modelos.Guias;
import com.example.mathr.burb01.R;
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
public class GuiasFragment extends Fragment {

    FirebaseDatabase database;
    DatabaseReference guias;
    FirebaseStorage storage;
    StorageReference guiaImagem;

    RecyclerView recycler_guias;
    RecyclerView.LayoutManager layoutManager;

    FirebaseRecyclerAdapter<Guias,GuiasViewHolder> guiasAdapter;

    public GuiasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_guias, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        database = FirebaseDatabase.getInstance();
        guias = database.getReference("Guias");
        storage = FirebaseStorage.getInstance();
        guiaImagem = storage.getReference("profilepics");

        recycler_guias = (RecyclerView)getView().findViewById(R.id.recycler_guias);
        recycler_guias.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recycler_guias.setLayoutManager(layoutManager);

        loadGuias();
    }

    private void loadGuias()
    {
        guiasAdapter = new FirebaseRecyclerAdapter<Guias, GuiasViewHolder>(Guias.class,R.layout.guias_item,GuiasViewHolder.class,guias) {
            @Override
            protected void populateViewHolder(final GuiasViewHolder viewHolder, final Guias model, int position) {
                viewHolder.txtGuiaAutor.setText(model.getAutor());
                viewHolder.txtGuiaNome.setText(model.getNome());
                viewHolder.ratingBar.setEnabled(false);
                viewHolder.ratingBar.setRating(model.getAvaliacao());
                //Picasso.with(getActivity()).load(model.getImagem()).into(viewHolder.imgGuia);
                Picasso.with(getActivity())
                        .load(model.getImagem()).resize(800,800).centerInside()
                        .networkPolicy(NetworkPolicy.OFFLINE)
                        .into(viewHolder.imgGuia, new Callback() {
                            @Override
                            public void onSuccess() {

                            }

                            @Override
                            public void onError() {
                                //Try again online if cache failed
                                Picasso.with(getActivity())
                                        .load(model.getImagem()).resize(480,854)
                                        .error(R.drawable.ic_add_black_24dp)
                                        .into(viewHolder.imgGuia, new Callback() {
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
                final Guias clickItem = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Intent guiaInfo = new Intent(getActivity(),GuiasInfoActivity.class);
                        guiaInfo.putExtra("GuiaId",guiasAdapter.getRef(position).getKey());
                        startActivity(guiaInfo);
                    }
                });
            }
        };
        recycler_guias.setAdapter(guiasAdapter);
    }
}
