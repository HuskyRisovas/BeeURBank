package com.example.mathr.burb01.ViewHolder;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.mathr.burb01.Modelos.Bolsa;
import com.example.mathr.burb01.R;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class BolsaAdapter extends ArrayAdapter<Bolsa> {

    public BolsaAdapter(Context context, List<Bolsa> earthquakes) {
        super(context, 0, earthquakes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.bolsa_item, parent, false);
        }

        Bolsa currentEarthquake = getItem(position);

        TextView nomeDaEmpresa = (TextView) listItemView.findViewById(R.id.bolsaNomeDaEmpresa);
        String stringNomeDaEmpresa = currentEarthquake.getNomeDaCompanhia();
        nomeDaEmpresa.setText(stringNomeDaEmpresa);
        TextView simboloDaEmpresa = (TextView) listItemView.findViewById(R.id.bolsaSimboloDaEmpresa);
        String stringSimboloDaEmpresa = currentEarthquake.getSimboloDaCompanhia();
        simboloDaEmpresa.setText(stringSimboloDaEmpresa);
        TextView variacao = (TextView) listItemView.findViewById(R.id.bolsaVariacao);
        String stringVariacao = String.format(Locale.getDefault(),"%.2f",currentEarthquake.getVariacao())+"%";
        variacao.setText(stringVariacao);
        int variacaoColor = getVariacaoColor(currentEarthquake.getVariacao());
        variacao.setTextColor(variacaoColor);
        TextView ultimoPreco = (TextView) listItemView.findViewById(R.id.bolsaUltimoPreco);
        String stringUltimoPreco = String.valueOf(currentEarthquake.getPreco());
        ultimoPreco.setText(stringUltimoPreco);

        return listItemView;
    }

    private int getVariacaoColor(double variacaos) {
        int resourceIdCor;
        //int variacao = (int)Math.floor(variacaos);
        if (variacaos < 0.0)
        {
            resourceIdCor = R.color.variacaoNegativa;
        }
        else
        {
            resourceIdCor = R.color.variacaoPositiva;
        }
        return ContextCompat.getColor(getContext(),resourceIdCor);
    }


}