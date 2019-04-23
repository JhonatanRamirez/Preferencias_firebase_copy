package com.pruebita.preferencias;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button siguiente, guardar;
    EditText nombre, email, telefono;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        siguiente= findViewById(R.id.btn_siguiente);
        guardar=findViewById(R.id.btn_guardar);
        nombre=findViewById(R.id.edit_nombreUsuario);
        email=findViewById(R.id.edit_correo);
        telefono=findViewById(R.id.edit_telefono);

        cargarPreferencias();

        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, perfil.class);
                startActivity(intent);

            }
        });

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                guardarPreferencias();

            }
        });
    }

    private void guardarPreferencias(){

        SharedPreferences preferences= getSharedPreferences("datos", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= preferences.edit();
        editor.putString("nombre", nombre.getText().toString());
        editor.putString("correo", email.getText().toString());
        editor.putString("telefono", telefono.getText().toString());
        editor.commit();
        Toast.makeText(getApplicationContext(), "Se guardaron los datos", Toast.LENGTH_SHORT).show();
    }

    private void cargarPreferencias(){


        SharedPreferences preferences= getSharedPreferences("datos", Context.MODE_PRIVATE);

        String nombre= preferences.getString("nombre","");

        if(!nombre.equalsIgnoreCase("")){

            Toast.makeText(getApplicationContext(), "Bienvenido al app", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, perfil.class);
            startActivity(intent);
            finish();
        }

    }
}
