package com.example.mathr.burb01.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mathr.burb01.MainActivity;
import com.example.mathr.burb01.R;
import com.google.firebase.auth.FirebaseAuth;


/**
 * A simple {@link Fragment} subclass.
 */
public class ConfigFragment extends Fragment implements View.OnClickListener {

   FirebaseAuth mAuth;

    Button btnLogOut;

    public ConfigFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_config, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        btnLogOut = (Button)getView().findViewById(R.id.btnLogOut);
        btnLogOut.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if(i==R.id.btnLogOut)
        {
            mAuth.signOut();
            Intent inicio = new Intent(getActivity(),MainActivity.class);
            startActivity(inicio);
            getActivity().finish();
            getActivity().overridePendingTransition(R.anim.fade_in,R.anim.fade_out);

        }

    }
}
