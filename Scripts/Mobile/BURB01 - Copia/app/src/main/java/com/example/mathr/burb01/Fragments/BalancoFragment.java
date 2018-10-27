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
import android.widget.TextView;
import android.widget.Toast;

import com.example.mathr.burb01.Interface.ItemClickListener;
import com.example.mathr.burb01.Modelos.Transacoes;
import com.example.mathr.burb01.R;
import com.example.mathr.burb01.Geral.SharedPref;
import com.example.mathr.burb01.ViewHolder.TransacoesViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import info.hoang8f.widget.FButton;

/**
 * A simple {@link Fragment} subclass.
 */
public class BalancoFragment extends Fragment {

    FirebaseDatabase database;
    DatabaseReference transacoes, user, info;

    String UID, saldo;

    FirebaseAuth mAuth;

    RecyclerView recycler_transacoes;
    RecyclerView.LayoutManager layoutManager;

    FirebaseRecyclerAdapter<Transacoes, TransacoesViewHolder> transacoesAdapter;

    FloatingActionButton add;

    TextView txtPeriodo, txtSaldo;

    int periodo;

    SharedPref sharedPref;

    public BalancoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        sharedPref = new SharedPref(getActivity());
        if (sharedPref.loadNightModeState()) {
            getActivity().setTheme(R.style.darktheme);
        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_balanco, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        UID = mAuth.getCurrentUser().getUid();
        database = FirebaseDatabase.getInstance();
        user = database.getReference("User");
        transacoes = user.child(UID).child("Transacoes");
        info = user.child(UID);
        txtPeriodo = (TextView) view.findViewById(R.id.txtPeriodo);
        txtSaldo = (TextView) view.findViewById(R.id.txtSaldoAtual);
        info.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                saldo = dataSnapshot.child("Dados").child("saldo").getValue().toString();
                String saldo2 = String.valueOf(saldo);
                txtSaldo.setText(R.string.SaldoAtual + saldo2);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        recycler_transacoes = (RecyclerView) getView().findViewById(R.id.recycler_transacoes);
        recycler_transacoes.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recycler_transacoes.setLayoutManager(layoutManager);
        add = (FloatingActionButton) view.findViewById(R.id.addTransacao);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
                View mView = getLayoutInflater().inflate(R.layout.add_transacao_item, null);
                final EditText edtNome = (EditText) mView.findViewById(R.id.edtNomeTransacao);
                final EditText edtValor = (EditText) mView.findViewById(R.id.edtValorTransacao);
                FButton btnAdicionarTransacao = (FButton) mView.findViewById(R.id.btnAddTransacao);
                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();
                btnAdicionarTransacao.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Transacoes transacoes = new Transacoes(edtNome.getText().toString(), Integer.parseInt(edtValor.getText().toString()));
                        user.child(UID).child("Transacoes").push().setValue(transacoes);
                        final int valor = Integer.parseInt(edtValor.getText().toString());
                        info.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                int saldoAtual = Integer.parseInt(dataSnapshot.child("Dados").child("saldo").getValue().toString());
                                int novoSaldo = saldoAtual - valor;
                                info.child("Dados").child("saldo").setValue(novoSaldo);
                            }
                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                            }
                        });
                        Toast.makeText(getActivity(), "Transação adicionada com sucesso!", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
            }
        });
        loadTransacoes();
    }

    private void loadTransacoes() {
        transacoesAdapter = new FirebaseRecyclerAdapter<Transacoes, TransacoesViewHolder>(Transacoes.class, R.layout.transacoes_item, TransacoesViewHolder.class, transacoes) {
            @Override
            protected void populateViewHolder(TransacoesViewHolder viewHolder, Transacoes model, final int position) {
                viewHolder.txtNome.setText(model.getNome());
                viewHolder.txtDescricao.setText(String.valueOf(model.getValor()));
                final Transacoes clickItem = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        user.child(UID).child("Transacoes").child(transacoesAdapter.getRef(position).getKey()).removeValue();
                    }
                });
            }
        };
        recycler_transacoes.setAdapter(transacoesAdapter);
    }
}
