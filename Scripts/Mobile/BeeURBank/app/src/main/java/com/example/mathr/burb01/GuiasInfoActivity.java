package com.example.mathr.burb01;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.example.mathr.burb01.Geral.SharedPref;
import com.example.mathr.burb01.Modelos.Guias;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

public class GuiasInfoActivity extends AppCompatActivity {

    CircularImageView guia_image;
    TextView guia_name,guia_autor,guia_descricao;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton btnSalvar;

    String guiasId="";

    FirebaseDatabase database;
    DatabaseReference guias,user;
    static int progresso;
    String UID;
    private FirebaseAuth mAuth;

    SharedPref sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPref = new SharedPref(this);
        if(sharedPref.loadNightModeState())
        {
            this.setTheme(R.style.darktheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guias_info);

        mAuth= FirebaseAuth.getInstance();
        UID = mAuth.getUid();

        database = FirebaseDatabase.getInstance();
        guias = database.getReference("Guias");
        user=database.getReference("Conquistas").child("01");
        guia_image = (CircularImageView)findViewById(R.id.guia_image);

        guia_name = (TextView)findViewById(R.id.guia_name);
        guia_autor=(TextView)findViewById(R.id.guia_autor);
        guia_descricao=(TextView)findViewById(R.id.guia_descricao);

        collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsing);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);

        btnSalvar = (FloatingActionButton)findViewById(R.id.btnSalvar);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        progresso = Integer.parseInt(dataSnapshot.child("Progresso").getValue().toString());

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                int progressoNovo = progresso + 20;
                user.child("Progresso").setValue(progressoNovo);
            }
        });
        if (getIntent() != null)
            guiasId = getIntent().getStringExtra("GuiaId");
        if (!guiasId.isEmpty() && guiasId != null) {
            getGuiaDetail(guiasId);
        }

    }

    private void getGuiaDetail(String guiasId)
    {
        guias.child(guiasId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Guias guias = dataSnapshot.getValue(Guias.class);

                Picasso.with(getBaseContext()).load(guias.getImagem()).into(guia_image);

                guia_name.setText(guias.getNome());

                collapsingToolbarLayout.setTitle(guias.getNome());

                guia_autor.setText(guias.getAutor());
                String html = guias.getDescricao();
                guia_descricao.setText(Html.fromHtml(html));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
