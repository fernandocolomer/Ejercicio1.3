package com.example.ejercicio13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ejercicio13.configuraciones.SQLiteConexion;
import com.example.ejercicio13.configuraciones.Transacciones;

public class MainActivitityPersonaSeleccionada extends AppCompatActivity {
    EditText nombres,apellidos,edad,correo,direccion,codigo;
    Button btnActualizar,btnEliminar,btnRegresar;
    String obtienerId;
    @Override public void onBackPressed() { }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_persona_seleccionada);

        codigo = (EditText) findViewById(R.id.txtselecCodigo);
        nombres = (EditText) findViewById(R.id.txtSelecNombres);
        apellidos = (EditText) findViewById(R.id.txtSelecApellido);
        edad = (EditText) findViewById(R.id.txtSelecEdad);
        correo = (EditText) findViewById(R.id.txtSelecCorreo);
        direccion = (EditText) findViewById(R.id.txtSelecDireccion);

        btnActualizar = (Button) findViewById(R.id.selectbtnActualizar);
        btnEliminar = (Button) findViewById(R.id.selectbtnEliminar);
        btnRegresar = (Button) findViewById(R.id.SeletbtnRegresar);

        codigo.setText(getIntent().getStringExtra("codigo"));
        nombres.setText(getIntent().getStringExtra("nombre"));
        apellidos.setText(getIntent().getStringExtra("apellidos"));
        edad.setText(getIntent().getStringExtra("edad"));
        correo.setText(getIntent().getStringExtra("correo"));
        direccion.setText(getIntent().getStringExtra("direccion"));

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePersona();
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletePersona();
            }
        });


        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);//Regresando a Menu Principal
                startActivity(intent);
            }
        });

    }

    private void updatePersona() {
        SQLiteConexion conect = new SQLiteConexion(this,Transacciones.NameDatabase,null,1);
        SQLiteDatabase db = conect.getWritableDatabase();

        obtienerId=codigo.getText().toString();
        ContentValues valor = new ContentValues();
        valor.put(Transacciones.nombres,nombres.getText().toString());
        valor.put(Transacciones.apellidos,apellidos.getText().toString());
        valor.put(Transacciones.edad,edad.getText().toString());
        valor.put(Transacciones.correo,correo.getText().toString());
        valor.put(Transacciones.direccion,direccion.getText().toString());

        db.update(Transacciones.tablapersonas,valor,Transacciones.id +"="+obtienerId,null);
        db.close();

        Intent intent = new Intent(getApplicationContext(), MainActivityListarPersona.class);
        startActivity(intent);
    }


    private void deletePersona() {
        SQLiteConexion conect = new SQLiteConexion(this,Transacciones.NameDatabase,null,1);
        SQLiteDatabase db = conect.getWritableDatabase();
        obtienerId=codigo.getText().toString();

        db.delete(Transacciones.tablapersonas,Transacciones.id+ "=" +obtienerId, null);
        db.close();

        Toast.makeText(getApplicationContext(),"REGISTRO ELIMINADO CON EXITO, CODIGO "+ obtienerId,Toast.LENGTH_LONG).show();
        db.close();

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);//Regresando a Menu Principal
        startActivity(intent);
    }
}