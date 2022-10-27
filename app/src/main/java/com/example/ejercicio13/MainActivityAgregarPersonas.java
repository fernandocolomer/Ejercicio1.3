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

public class MainActivityAgregarPersonas extends AppCompatActivity {
    EditText edtNombres, edtApellidos, edtEdad, edtCorreo, edtDireccion;
    Button botonGuardar, botonRegresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_agregar_personas);

        edtNombres = (EditText) findViewById(R.id.txtnombres);
        edtApellidos = (EditText) findViewById(R.id.txtApellidos);
        edtEdad = (EditText) findViewById(R.id.txtedad);
        edtCorreo = (EditText) findViewById(R.id.txtcorreo);
        edtDireccion = (EditText) findViewById(R.id.txtdireccion);
        botonGuardar = (Button) findViewById(R.id.btnGuardar);
        botonRegresar = (Button) findViewById(R.id.btnRegresar);


        botonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarPersonas();
            }
        });

        botonRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });




    }

    private void agregarPersonas() {

        SQLiteConexion conexion = new SQLiteConexion(this, Transacciones.NameDatabase, null, 1);

        SQLiteDatabase db = conexion.getWritableDatabase();


        ContentValues valor = new ContentValues();

        valor.put(Transacciones.nombres, edtNombres.getText().toString());
        valor.put(Transacciones.apellidos, edtApellidos.getText().toString());
        valor.put(Transacciones.edad, edtEdad.getText().toString());
        valor.put(Transacciones.correo, edtCorreo.getText().toString());
        valor.put(Transacciones.direccion, edtDireccion.getText().toString());

        Long resultado = db.insert(Transacciones.tablapersonas, Transacciones.id, valor);

        Toast.makeText(getApplicationContext(), "Registro ingreso con exito, Codigo " + resultado.toString(),Toast.LENGTH_LONG).show();
        db.close();

        //Limpia el textPlain
        edtNombres.setText("");
        edtApellidos.setText("");
        edtEdad.setText("");
        edtCorreo.setText("");
        edtDireccion.setText("");

        //menu principal
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);


    }

}