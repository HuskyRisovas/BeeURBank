package com.example.mathr.burb01;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mathr.burb01.Geral.SharedPref;
import com.example.mathr.burb01.Modelos.Usuarios;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.io.FileNotFoundException;
import java.io.IOException;

public class EditarPerfilActivity extends AppCompatActivity implements View.OnClickListener{

    EditText edtNome,edtSaldo,edtPeso,edtAltura,edtSituacao,edtRenda,edtSexo;

    String UID, Nome, Saldo, Peso, Altura, Situacao,Renda,Sexo;

    Button btnSalvar;

    FirebaseDatabase database;

    FirebaseStorage storage;

    StorageReference storageReference;

    CircularImageView perfilImagem;

    DatabaseReference user,info,imagem;

    FirebaseAuth mAuth;

    SharedPref sharedPref;

    public static final String FB_STORAGE_PATH = "image/";

    public static final String FB_DATABASE_PATH = "image";

    public static final int REQUEST_CODE = 1234;

    private Uri imgUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPref = new SharedPref(this);
        if(sharedPref.loadNightModeState())
        {
            setTheme(R.style.darktheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfil);

        perfilImagem = (CircularImageView) findViewById(R.id.perfilImagem);

        perfilImagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent= new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select image"),REQUEST_CODE);

            }
        });

        btnSalvar = (Button)findViewById(R.id.salvarPerfil);

        edtNome = (EditText)findViewById(R.id.edtNome);
        edtSaldo = (EditText)findViewById(R.id.edtSaldo);
        edtPeso = (EditText)findViewById(R.id.edtPeso);
        edtSituacao = (EditText)findViewById(R.id.edtSituacao);
        edtRenda = (EditText)findViewById(R.id.edtRenda);
        edtSexo = (EditText)findViewById(R.id.edtSexo);
        edtAltura = (EditText)findViewById(R.id.edtAltura);

        database = FirebaseDatabase.getInstance();
        user = database.getReference("User");
        mAuth = FirebaseAuth.getInstance();
        UID = mAuth.getCurrentUser().getUid();
        info = user.child(UID);
        imagem = FirebaseDatabase.getInstance().getReference(FB_DATABASE_PATH);
        storageReference = FirebaseStorage.getInstance().getReference(FB_STORAGE_PATH);

        info.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final String url = dataSnapshot.child("Imagem").child("url").getValue().toString();
                //Picasso.with(getApplicationContext()).load(url).resize(1080,1920).centerInside().into(perfilImagem);
                Picasso.with(EditarPerfilActivity.this)
                        .load(url).resize(1080,1920).centerInside()
                        .networkPolicy(NetworkPolicy.OFFLINE)
                        .into(perfilImagem, new Callback() {
                            @Override
                            public void onSuccess() {

                            }

                            @Override
                            public void onError() {
                                //Try again online if cache failed
                                Picasso.with(EditarPerfilActivity.this)
                                        .load(url)
                                        .error(R.drawable.ic_add_black_24dp)
                                        .into(perfilImagem, new Callback() {
                                            @Override
                                            public void onSuccess() {

                                            }

                                            @Override
                                            public void onError() {
                                                Log.v("Picasso","Could not fetch image");
                                            }
                                        });
                            }
                        });
                Nome = dataSnapshot.child("Dados").child("nome").getValue().toString();
                edtNome.setText(Nome);
                Saldo = dataSnapshot.child("Dados").child("saldo").getValue().toString();
                edtSaldo.setText(Saldo);
                Peso = dataSnapshot.child("Dados").child("peso").getValue().toString();
                edtPeso.setText(Peso);
                Altura = dataSnapshot.child("Dados").child("altura").getValue().toString();
                edtAltura.setText(Altura);
                Situacao = dataSnapshot.child("Dados").child("situacao").getValue().toString();
                edtSituacao.setText(Situacao);
                Renda = dataSnapshot.child("Dados").child("renda").getValue().toString();
                edtRenda.setText(Renda);
                Sexo = dataSnapshot.child("Dados").child("sexo").getValue().toString();
                edtSexo.setText(Sexo);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Usuarios usuarios = new Usuarios(edtNome.getText().toString(),edtPeso.getText().toString(),edtAltura.getText().toString(),edtSituacao.getText().toString(),edtRenda.getText().toString(),edtSexo.getText().toString(),Integer.parseInt(edtSaldo.getText().toString()));
                //Usuarios usuarios = new Usuarios(edtNome.getText().toString(),Integer.parseInt(edtSaldo.getText().toString()),edtPeso.getText().toString(),edtAltura.getText().toString(),edtSituacao.getText().toString(),
                user.child(UID).child("Dados").setValue(usuarios);
                Toast.makeText(EditarPerfilActivity.this,"Dados alterados com sucesso!",Toast.LENGTH_SHORT).show();
                Intent home = new Intent(EditarPerfilActivity.this,Home.class);
                startActivity(home);
                overridePendingTransition(R.anim.slide_in_left_fast, R.anim.slide_out_right_fast);
                finish();

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null && data.getData()!= null )
        {
            imgUri = data.getData();
            try{
                Bitmap bm = MediaStore.Images.Media.getBitmap(getContentResolver(),imgUri);
                perfilImagem.setImageBitmap(bm);

                final ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setTitle("Alterando imagem...");
                progressDialog.show();

                StorageReference reference = storageReference.child(FB_STORAGE_PATH + System.currentTimeMillis()+"."+getImageExt(imgUri));

                reference.putFile(imgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(),"Imagem alterada com sucesso!",Toast.LENGTH_SHORT).show();

                            ImageUpload imageUpload = new ImageUpload(taskSnapshot.getDownloadUrl().toString());

                            //String uploadId = imagem.push().getKey();
                            info.child("Imagem").setValue(imageUpload);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        double progress = (100*taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                        progressDialog.setMessage("Uploaded "+(int)progress+"%");
                    }
                });
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getImageExt(Uri uri){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent perfil = new Intent(EditarPerfilActivity.this,Home.class);
        startActivity(perfil);
        overridePendingTransition(R.anim.slide_in_left_fast,R.anim.slide_out_right_fast);
        finish();
    }



    @Override
    public void onClick(View v) {

    }
}
