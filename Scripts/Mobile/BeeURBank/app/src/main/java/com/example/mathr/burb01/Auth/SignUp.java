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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mathr.burb01.MainActivity;
import com.example.mathr.burb01.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    private EditText edtNome, edtEmail, edtSenha;

    TextView txtNome,txtSenha,txtEmail,txtJaPossuiUmaConta;

    private FirebaseAuth mAuth;

    private Button btnCadastrar;

    RelativeLayout relativeLayout;

    private FirebaseDatabase database;
    private DatabaseReference users;

    static String LoggedIn_User_Email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        relativeLayout = (RelativeLayout)findViewById(R.id.activitySignUpLayout);
        relativeLayout.setBackgroundResource(R.drawable.fundo4);

        Typeface spotify = Typeface.createFromAsset(getAssets(), "Fontes/GothamBold.ttf");
        Typeface spotifyLight = Typeface.createFromAsset(getAssets(), "Fontes/GothamLight.ttf");

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        users = database.getReference("User");

        edtNome = (EditText) findViewById(R.id.edtNome);
        edtEmail = (EditText) findViewById(R.id.edtEmail2);
        edtSenha = (EditText) findViewById(R.id.edtSenha);

        txtEmail = (TextView)findViewById(R.id.activitySignUpTxtEmail);
        txtJaPossuiUmaConta = (TextView)findViewById(R.id.activitySignUpTxtAjuda);
        txtNome = (TextView)findViewById(R.id.activitySignUpTxtNome);
        txtSenha = (TextView)findViewById(R.id.activitySignUpTxtSenha);

        btnCadastrar = (Button) findViewById(R.id.btnCadastrar);

        edtEmail.setTypeface(spotifyLight);
        edtNome.setTypeface(spotifyLight);
        edtSenha.setTypeface(spotifyLight);

        txtSenha.setTypeface(spotify);
        txtNome.setTypeface(spotify);
        txtJaPossuiUmaConta.setTypeface(spotifyLight);
        txtEmail.setTypeface(spotify);

        btnCadastrar.setTypeface(spotify);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String getnome = edtNome.getText().toString();
                String getemail = edtEmail.getText().toString().trim();
                String getpassword = edtSenha.getText().toString().trim();
                if (getemail.matches("")) {
                    Toast.makeText(SignUp.this, "Insira seu email!", Toast.LENGTH_SHORT).show();
                } else if (getpassword.matches("")) {
                    Toast.makeText(SignUp.this, "Insira sua senha!", Toast.LENGTH_SHORT).show();
                } else if (getnome.matches("")) {
                    Toast.makeText(SignUp.this, "Insira seu nome!", Toast.LENGTH_SHORT).show();
                } else {
                    signUp(getemail, getpassword);
                }
            }
        });

    }

    private void signUp(String email, String password) {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setTitle("Aguarde...");
        progressDialog.show();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("TESTING", "Sign up Successful" + task.isSuccessful());

// If sign in fails, display a message to the user. If sign in succeeds
// the auth state listener will be notified and logic to handle the
// signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            progressDialog.dismiss();
                            Toast.makeText(SignUp.this, "Email inválido", Toast.LENGTH_SHORT).show();
                        } else {
                            userProfile();
                            Toast.makeText(SignUp.this, "Conta criada com sucesso", Toast.LENGTH_SHORT).show();
                            Log.d("TESTING", "Created Account");
                            FirebaseUser user = mAuth.getCurrentUser();
                            user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(SignUp.this, "Verifique seu email", Toast.LENGTH_SHORT).show();
                                }
                            });
                            Intent login = new Intent(SignUp.this, SignIn.class);
                            startActivity(login);
                            finish();
                            progressDialog.dismiss();
                        }
                    }
                });
    }

    private void userProfile() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                    .setDisplayName(edtNome.getText().toString().trim())
//.setPhotoUri(Uri.parse("https://example.com/jane-q-user/profile.jpg")) // here you can set image link also.
                    .build();

            user.updateProfile(profileUpdates)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Log.d("TESTING", "User profile updated.");
                            }
                        }
                    });
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(SignUp.this, MainActivity.class);
        startActivity(i);
        finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

}
