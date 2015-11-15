package com.example.andrea.storage3a;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Activity1 extends Activity {

    EditText mensaje;
    Button fichero;
    Button mostrar;
    TextView contenido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);

        mensaje = (EditText) findViewById(R.id.et_mensaje);
        fichero = (Button) findViewById(R.id.bt_añadir);
        mostrar = (Button) findViewById(R.id.bt_mostrar);
        contenido = (TextView) findViewById(R.id.tv_contenido);

        fichero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //abriremos un fichero de escritura
                    FileOutputStream fos = openFileOutput("fichero.txt", Context.MODE_APPEND);
                    //envoltura de datos
                    DataOutputStream dos = new DataOutputStream(fos);
                    dos.writeBytes(mensaje.getText().toString()+"\n");
                    fos.close();
                }catch(FileNotFoundException e){
                    e.printStackTrace();
                }catch(IOException e){
                    e.printStackTrace();
                }

                try{
                    FileInputStream fin = openFileInput("fichero.txt");
                    DataInputStream dis = new DataInputStream(fin);
                    byte[] buff = new byte[1000];
                    dis.read(buff);
                    //Toast.makeText(getApplicationContext(), "He escrito: "+ new String(buff), Toast.LENGTH_LONG).show();
                    fin.close();
                    
                }catch(FileNotFoundException e){
                    e.printStackTrace();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        });

        mostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileInputStream fin = openFileInput("fichero.txt");
                    DataInputStream dis = new DataInputStream(fin);
                    byte[] buff = new byte[1000];
                    dis.read(buff);
                    contenido.setText(" "+new String(buff));
                    //Toast.makeText(getApplicationContext(), "Contenido: "+new String(buff)+" ",Toast.LENGTH_LONG).show();
                }catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
