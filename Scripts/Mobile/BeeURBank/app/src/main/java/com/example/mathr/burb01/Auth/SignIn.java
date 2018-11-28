package com.example.mathr.burb01.Auth;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mathr.burb01.Home;
import com.example.mathr.burb01.MainActivity;
import com.example.mathr.burb01.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.rengwuxian.materialedittext.MaterialEditText;

public class SignIn extends AppCompatActivity {

    Button btnLogar;

    EditText edtEmail, edtSenha;

    TextView txtEmail,txtSenha,txtAjuda,txtLogin;

    MaterialEditText edtEmail2;

    RelativeLayout relativeLayout;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        relativeLayout = (RelativeLayout)findViewById(R.id.activitySignInLayout);
        relativeLayout.setBackgroundResource(R.drawable.fundo3);

        Typeface spotify = Typeface.createFromAsset(getAssets(), "Fontes/GothamBold.ttf");
        Typeface spotifyLight = Typeface.createFromAsset(getAssets(), "Fontes/GothamLight.ttf");

        btnLogar = (Button) findViewById(R.id.btn_Login);

        //edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtSenha = (EditText) findViewById(R.id.edtSenha);
        edtEmail = (MaterialEditText)findViewById(R.id.edtEmail);

        txtAjuda = (TextView)findViewById(R.id.activityMainTxtAjuda);
        txtEmail = (TextView)findViewById(R.id.activityMainTxtEmail);
        txtSenha = (TextView)findViewById(R.id.activityMainTxtSenha);
        txtLogin = (TextView)findViewById(R.id.activitySignInTxtLogin);

        txtAjuda.setTypeface(spotifyLight);

        txtLogin.setTypeface(spotify);
        txtEmail.setTypeface(spotify);
        txtSenha.setTypeface(spotify);

        btnLogar.setTypeface(spotify);

        edtEmail.setTypeface(spotifyLight);
        edtSenha.setTypeface(spotifyLight);

        mAuth = FirebaseAuth.getInstance();

        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getemail = edtEmail.getText().toString().trim();
                String getepassword = edtSenha.getText().toString().trim();
                if (getemail.matches("")) {
                    Toast.makeText(SignIn.this, "Insira seu email!", Toast.LENGTH_SHORT).show();
                } else if (getepassword.matches("")) {
                    Toast.makeText(SignIn.this, "Insira sua senha!", Toast.LENGTH_SHORT).show();
                } else {
                    signIn(getemail, getepassword);
                }
            }
        });


    }

    //Now start Sign In Process
//SignIn Process
    private void signIn(String email, String password) {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setTitle("Aguarde...");
        progressDialog.show();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("TESTING", "sign In Successful:" + task.isSuccessful());
                        FirebaseUser user = mAuth.getCurrentUser();

// If sign in fails, display a message to the user. If sign in succeeds
// the auth state listener will be notified and logic to handle the
// signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w("TESTING", "signInWithEmail:failed", task.getException());
                            progressDialog.dismiss();
                            Toast.makeText(SignIn.this, "Senha ou email incorretos!", Toast.LENGTH_SHORT).show();
                        } else {
                            if (user.isEmailVerified()) {
                                Intent i = new Intent(SignIn.this, Home.class);
                                startActivity(i);
                                finish();
                                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                                progressDialog.dismiss();
                            } else {
                                progressDialog.dismiss();
                                Toast.makeText(SignIn.this, "Email ainda não verificado.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(SignIn.this, MainActivity.class);
        startActivity(i);
        finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

}
