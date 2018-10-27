package com.example.mathr.burb01.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.mathr.burb01.Interface.ItemClickListener;
import com.example.mathr.burb01.R;
import com.mikhaellopez.circularimageview.CircularImageView;

/**
 * Created by mathr on 25/02/2018.
 */

public class ConquistasViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtNomeConquista,txtDescricaoConquista,txtNivel;
    public CircularImageView imgConquista;

    private ItemClickListener itemClickListener;

    public ConquistasViewHolder(View itemView) {
        super(itemView);

        txtNomeConquista = (TextView)itemView.findViewById(R.id.conquista_name);
        txtDescricaoConquista = (TextView)itemView.findViewById(R.id.conquista_descricao);
        txtNivel = (TextView)itemView.findViewById(R.id.conquista_nivel);

        imgConquista = (CircularImageView)itemView.findViewById(R.id.conquista_image);

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
