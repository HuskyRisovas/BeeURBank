package com.example.mathr.burb01;

import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mathr.burb01.Auth.SignIn;
import com.example.mathr.burb01.Geral.SharedPref;
import com.example.mathr.burb01.Modelos.Bolsa;
import com.example.mathr.burb01.ViewHolder.BolsaAdapter;
import com.example.mathr.burb01.ViewHolder.BolsaLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Bolsas extends AppCompatActivity implements LoaderCallbacks<List<Bolsa>> {

    SharedPref sharedPref;

    private static final String LOG_TAG = Bolsas.class.getName();

    private static final String USGS_REQUEST_URL =
            "https://api.iextrading.com/1.0/stock/market/list/infocus";

    private static final int EARTHQUAKE_LOADER_ID = 1;

    private BolsaAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPref = new SharedPref(this);
        if(sharedPref.loadNightModeState())
        {
            setTheme(R.style.darktheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bolsas);

        TextView txtHoraDaConsulta = (TextView)findViewById(R.id.horaDaConsulta);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String hora = "Consultado em: "+sdf.format(calendar.getTime());
        txtHoraDaConsulta.setText(hora);
        ListView earthquakeListView = (ListView) findViewById(R.id.bolsaListView);

        mAdapter = new BolsaAdapter(this, new ArrayList<Bolsa>());

        earthquakeListView.setAdapter(mAdapter);
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {

            LoaderManager loaderManager = getLoaderManager();

            loaderManager.initLoader(EARTHQUAKE_LOADER_ID, null, this);
        } else {
            Toast.makeText(this,"Ah mano, não creio",Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public Loader<List<Bolsa>> onCreateLoader(int i, Bundle bundle) {

        Uri baseUri = Uri.parse(USGS_REQUEST_URL);
        Log.e("Oi Caralho",baseUri.toString());
        return new BolsaLoader(this, baseUri.toString());
    }

    @Override
    public void onLoadFinished(Loader<List<Bolsa>> loader, List<Bolsa> earthquakes) {

        if (earthquakes != null && !earthquakes.isEmpty()) {
            mAdapter.addAll(earthquakes);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Bolsa>> loader) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent bolsas = new Intent(Bolsas.this,Home.class);
        startActivity(bolsas);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        finish();
    }
}
