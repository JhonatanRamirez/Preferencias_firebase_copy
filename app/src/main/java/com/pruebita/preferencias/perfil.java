package com.pruebita.preferencias;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class perfil extends AppCompatActivity {

    TextView nombre, telefono, correo;
    Button borrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        nombre= findViewById(R.id.Label_nombreUsuario);
        telefono= findViewById(R.id.Label_telefonoUsuario);
        correo= findViewById(R.id.Label_CorreoUsuario);
        borrar=findViewById(R.id.btn_borrar);

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
}
