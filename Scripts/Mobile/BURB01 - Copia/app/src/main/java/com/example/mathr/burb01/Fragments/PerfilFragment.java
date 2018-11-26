package com.example.mathr.burb01.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mathr.burb01.EditarPerfilActivity;
import com.example.mathr.burb01.R;
import com.example.mathr.burb01.Geral.SharedPref;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilFragment extends Fragment implements View.OnClickListener {

    ProgressBar progressBar;

    TextView txtUserName, txtInvestidor;

    CircularImageView perfilImagem;

    TextView txtConquistas, txtObjetivos, txtEditarPerfil;

    FirebaseAuth mAuth;

    FirebaseDatabase database;
    DatabaseReference username,info;

    String UID;

    SharedPref sharedPref;

    public PerfilFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        sharedPref = new SharedPref(getActivity());
        if(sharedPref.loadNightModeState())
        {
            getActivity().setTheme(R.style.darktheme);
        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_perfil, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        ConquistasFragment conquistasFragment = new ConquistasFragment();
        FragmentTransaction ft1 = getFragmentManager().beginTransaction();
        ft1.replace(R.id.perfilFrame, conquistasFragment);
        ft1.commit();

        username = database.getReference("User");
        UID = mAuth.getCurrentUser().getUid();

        progressBar = (ProgressBar) getView().findViewById(R.id.progressBar);


        txtConquistas = (TextView) getView().findViewById(R.id.txtConquistas);
        txtObjetivos = (TextView) getView().findViewById(R.id.txtObjetivos);
        txtEditarPerfil = (TextView) getView().findViewById(R.id.txtEditarPerfil);
        txtUserName = (TextView) getView().findViewById(R.id.txtUserName);
        txtInvestidor = (TextView)getView().findViewById(R.id.txtInvestidor) ;

        perfilImagem = (CircularImageView) getView().findViewById(R.id.perfilImagem);

        txtConquistas.setOnClickListener(this);
        txtObjetivos.setOnClickListener(this);
        txtEditarPerfil.setOnClickListener(this);

        username.child(UID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String userName = dataSnapshot.child("Dados").child("nome").getValue().toString();
                txtUserName.setText(userName);
                String situacao = dataSnapshot.child("Dados").child("situacao").getValue().toString();
                txtInvestidor.setText(situacao);
           final String url = dataSnapshot.child("Imagem").child("url").getValue().toString();
                //Picasso.with(getActivity()).load(url).resize(720,1080).centerInside().into(perfilImagem);
                Picasso.with(getActivity())
                        .load(url).resize(1080,1920).centerInside()
                        .networkPolicy(NetworkPolicy.OFFLINE)
                        .into(perfilImagem, new Callback() {
                            @Override
                            public void onSuccess() {

                            }

                            @Override
                            public void onError() {
                                //Try again online if cache failed
                                Picasso.with(getActivity())
                                        .load(url)
                                        .error(R.drawable.ic_add_black_24dp)
                                        .into(perfilImagem, new Callback() {
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
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        
    }


    @Override
    public void onClick(View v) {
        int i = v.getId();
        switch (i) {
            case R.id.txtConquistas:
                ConquistasFragment conquistasFragment = new ConquistasFragment();
                FragmentTransaction ft1 = getFragmentManager().beginTransaction();
                ft1.setCustomAnimations(R.anim.slide_in_left_fast, R.anim.slide_out_right_fast);
                ft1.replace(R.id.perfilFrame, conquistasFragment);
                ft1.commit();
                return;
            case R.id.txtObjetivos:
                ObjetivosFragment objetivosFragment = new ObjetivosFragment();
                FragmentTransaction ft2 = getFragmentManager().beginTransaction();
                ft2.setCustomAnimations(R.anim.slide_in_right_fast, R.anim.slide_out_left_fast);
                ft2.replace(R.id.perfilFrame, objetivosFragment);
                ft2.commit();
                return;
            case R.id.txtEditarPerfil:
                Intent editarPerfil = new Intent(getActivity(), EditarPerfilActivity.class);
                startActivity(editarPerfil);
                getActivity().overridePendingTransition(R.anim.slide_in_right_fast, R.anim.slide_out_left_fast);
                getActivity().finish();
        }

    }


}
