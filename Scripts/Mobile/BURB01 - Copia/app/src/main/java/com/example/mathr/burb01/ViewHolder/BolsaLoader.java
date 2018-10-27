package com.example.mathr.burb01.ViewHolder;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.example.mathr.burb01.Modelos.Bolsa;
import com.example.mathr.burb01.QueryUtils;

import java.util.List;

public class BolsaLoader extends AsyncTaskLoader<List<Bolsa>> {

    private static final String LOG_TAG = BolsaLoader.class.getName();

    private String mUrl;

    public BolsaLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Bolsa> loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        List<Bolsa> earthquakes = QueryUtils.fetchEarthquakeData(mUrl);
        return earthquakes;
    }
}
