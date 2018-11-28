package com.example.mathr.burb01;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mathr.burb01.Auth.SignIn;
import com.example.mathr.burb01.Auth.SignUp;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView txtConta;

    Button btnLogar, btnCadastrar, btnFacebook;

    LinearLayout linearLayout;

    ImageView logo;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        linearLayout = (LinearLayout) findViewById(R.id.activityMainLayout);
        linearLayout.setBackgroundResource(R.drawable.fundo2);

        txtConta = (TextView) findViewById(R.id.txtConta);
        Typeface spotify = Typeface.createFromAsset(getAssets(), "Fontes/GothamBold.ttf");
        Typeface spotifyLight = Typeface.createFromAsset(getAssets(), "Fontes/GothamLight.ttf");

        txtConta.setTypeface(spotifyLight);

        btnLogar = (Button) findViewById(R.id.btn_Login);
        btnCadastrar = (Button) findViewById(R.id.btnCadastrar);
        btnFacebook = (Button) findViewById(R.id.btnFacebook);

        btnLogar.setTypeface(spotify);
        btnCadastrar.setTypeface(spotify);
        btnFacebook.setTypeface(spotify);

        btnCadastrar.setOnClickListener(this);
        btnLogar.setOnClickListener(this);

        logo = (ImageView) findViewById(R.id.activityMainLogo);

        Picasso.with(this).load(R.drawable.logo_bee_sombra).resize(800,800).networkPolicy(NetworkPolicy.OFFLINE).into(logo);

        mAuth = FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser() != null && mAuth.getCurrentUser().isEmailVerified()) {
//User NOT logged I
            Intent home = new Intent(MainActivity.this, Home.class);
            startActivity(home);
            finish();
        }
    }

    @Override
    public void onClick(View view) {

        int i = view.getId();
        if (i == R.id.btnCadastrar) {
            Intent cadastro = new Intent(MainActivity.this, SignUp.class);
            startActivity(cadastro);
            finish();
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        } else if (i == R.id.btn_Login) {
            Intent login = new Intent(MainActivity.this, SignIn.class);
            startActivity(login);
            finish();
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }
    }
}
