package com.example.ejercicio13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button agregar,listar;
    @Override public void onBackPressed() { } //metodo deshabillita para ir atras

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

          agregar = (Button) findViewById(R.id.btnAgregar);
          listar = (Button) findViewById(R.id.btnlistar);

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivityAgregarPersonas.class);
                startActivity(intent);
            }
        });

        listar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivityListarPersona.class);
                startActivity(intent);
            }
        });

    }
}