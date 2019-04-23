package com.pruebita.preferencias;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class perfil extends AppCompatActivity {

    TextView nombre, telefono, correo, titulo;
    Button borrar;

    //Firebase


    DatabaseReference reference= FirebaseDatabase.getInstance().getReference();

    //DatabaseReference uniagust = database.getReference(FirebaseReferences.REFERENCE_1);

    DatabaseReference refhijo= reference.child("miTitulo");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        nombre= findViewById(R.id.Label_nombreUsuario);
        telefono= findViewById(R.id.Label_telefonoUsuario);
        correo= findViewById(R.id.Label_CorreoUsuario);
        borrar=findViewById(R.id.btn_borrar);
        titulo=findViewById(R.id.lbl_titulo);


         Log.e("refHijo", ""+refhijo);
        cargarPreferencias();



        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                borrarPreferencias();
                finish();
            }
        });
    }

    private void cargarPreferencias(){

        SharedPreferences preferences = getSharedPreferences("datos", Context.MODE_PRIVATE);
        nombre.setText(preferences.getString("nombre",""));
        telefono.setText(preferences.getString("telefono",""));
        correo.setText(preferences.getString("correo",""));

    }

    private void borrarPreferencias(){

        SharedPreferences preferences = getSharedPreferences("datos", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= preferences.edit();
        editor.putString("nombre","");
        editor.putString("telefono","");
        editor.putString("correo","");
        editor.commit();
        Toast.makeText(getApplicationContext(), "Se borraron los datos", Toast.LENGTH_SHORT).show();

    }


    @Override
    protected void onStart() {
        super.onStart();

        refhijo.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String text= dataSnapshot.getValue().toString();
                Log.e("texto_firebase",""+text);
                titulo.setText(text);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Log.w("TAG", "loadPost:onCancelled", databaseError.toException());
            }
        });
    }

}
