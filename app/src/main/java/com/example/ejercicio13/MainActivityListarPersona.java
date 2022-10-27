package com.example.ejercicio13;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ejercicio13.configuraciones.SQLiteConexion;
import com.example.ejercicio13.configuraciones.Transacciones;
import com.example.ejercicio13.tablas.Personas;
import java.util.ArrayList;

public class MainActivityListarPersona extends AppCompatActivity {

    SQLiteConexion conexion;
    ListView lista;
    ArrayList<Personas> listaPersona;
    ArrayList <String> ArregloPersona;
    Button btnregresar;

    @Override public void onBackPressed() { }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_listar_personas);

        lista = (ListView) findViewById(R.id.listar);
        conexion = new SQLiteConexion(this, Transacciones.NameDatabase,null,1);
        obtenerlistPersonas();
        ArrayAdapter arp = new ArrayAdapter(this, android.R.layout.simple_list_item_1,ArregloPersona);
        lista.setAdapter(arp);


        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ObtenerPersona(i);
            }
        });

        btnregresar = (Button) findViewById(R.id.btRegresar);
        btnregresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void ObtenerPersona(int id) {
        Personas persona = listaPersona.get(id);

        Intent intent = new Intent(getApplicationContext(), MainActivitityPersonaSeleccionada.class);

        intent.putExtra("codigo", persona.getId()+"");
        intent.putExtra("nombre", persona.getNombres());
        intent.putExtra("apellidos", persona.getApellidos());
        intent.putExtra("edad", persona.getEdad()+"");
        intent.putExtra("correo", persona.getCorreo());
        intent.putExtra("direccion", persona.getDireccion());

        startActivity(intent);
    }

    private void obtenerlistPersonas() {
        SQLiteDatabase db = conexion.getReadableDatabase();
        Personas list_personas = null;
        listaPersona = new ArrayList<Personas>();
        Cursor cursor = db.rawQuery("SELECT * FROM "+ Transacciones.tablapersonas, null);//consulta ala BD
        while (cursor.moveToNext())
        {
            list_personas = new Personas();
            list_personas.setId(cursor.getInt(0));
            list_personas.setNombres(cursor.getString(1));
            list_personas.setApellidos(cursor.getString(2));
            list_personas.setEdad(cursor.getString(3));
            list_personas.setCorreo(cursor.getString(4));
            list_personas.setDireccion(cursor.getString(5));
            listaPersona.add(list_personas);
        }
        cursor.close();
        llenarlistaPersonas();
    }
    private void llenarlistaPersonas()
    {
        ArregloPersona = new ArrayList<String>();
        for (int i=0; i<listaPersona.size();i++)
        {
            ArregloPersona.add(listaPersona.get(i).getId()+"|"+listaPersona.get(i).getNombres()+"|"+ listaPersona.get(i).getApellidos()+"|"+ listaPersona.get(i).getEdad()+"|"+ listaPersona.get(i).getCorreo()+"|"+ listaPersona.get(i).getDireccion());
        }
    }
}