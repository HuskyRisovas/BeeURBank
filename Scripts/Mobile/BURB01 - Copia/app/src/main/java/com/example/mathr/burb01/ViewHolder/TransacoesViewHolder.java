package com.example.mathr.burb01.ViewHolder;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.mathr.burb01.Interface.ItemClickListener;
import com.example.mathr.burb01.R;

/**
 * Created by mathr on 04/03/2018.
 */

public class TransacoesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtNome,txtDescricao;

    public FloatingActionButton remover;

    private ItemClickListener itemClickListener;

    public TransacoesViewHolder(View itemView) {
        super(itemView);

        txtNome = (TextView)itemView.findViewById(R.id.transacao_name);
        txtDescricao = (TextView)itemView.findViewById(R.id.transacao_descricao);
        remover = (FloatingActionButton) itemView.findViewById(R.id.removeTransacao);

        itemView.setOnClickListener(this);

    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {

        itemClickListener.onClick(v,getAdapterPosition(),false);

    }
}
