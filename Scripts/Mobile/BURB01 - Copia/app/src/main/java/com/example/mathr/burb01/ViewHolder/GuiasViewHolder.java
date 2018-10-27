package com.example.mathr.burb01.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mathr.burb01.Interface.ItemClickListener;
import com.example.mathr.burb01.R;
import com.mikhaellopez.circularimageview.CircularImageView;

/**
 * Created by mathr on 05/02/2018.
 */

public class GuiasViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtGuiaNome,txtGuiaAutor;
    public CircularImageView imgGuia;

    private ItemClickListener itemClickListener;

    public GuiasViewHolder(View itemView) {
        super(itemView);

        txtGuiaNome = (TextView)itemView.findViewById(R.id.guia_name);
        txtGuiaAutor = (TextView) itemView.findViewById(R.id.guia_autor);

        imgGuia = (CircularImageView) itemView.findViewById(R.id.guia_image);

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
