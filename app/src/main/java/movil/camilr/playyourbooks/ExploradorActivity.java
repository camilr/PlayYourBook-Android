package movil.camilr.playyourbooks;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import movil.camilr.playyourbooks.entities.FilePdf;

public class ExploradorActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    TextView ruta;
    String directorioRaiz;

    ListView listaArchivosView;

    List<String> listaRutaArchivos;
    List<String> listaNombreArchivos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explorador);

        ruta = (TextView) findViewById(R.id.txt_ruta);
        listaArchivosView = (ListView) findViewById(R.id.list_archivos);

       //Con esto saco la ruta "/storage/emulated/0" que es igual a la ruta "/sdcard"

        directorioRaiz = Environment.getExternalStorageDirectory().getPath();

        ruta.setText("Ruta: "+directorioRaiz);
        verArchivosDirectorio(directorioRaiz);

        listaArchivosView.setOnItemClickListener(this);

    }

    private void verArchivosDirectorio(String rutaDirectorio) {

        listaRutaArchivos = new ArrayList<>();
        listaNombreArchivos = new ArrayList<>();

        File directorioActual = new File(rutaDirectorio);
        File[] listaArchivos = directorioActual.listFiles();

        int x = 0;

        /*Si la carpeta no esta vacia*/
        if (!rutaDirectorio.equals(directorioRaiz)) {
            listaNombreArchivos.add("../");
            listaRutaArchivos.add(directorioActual.getParent());
            x = 1;
        }

        /*Para obtener todas las ubicaciones de los archivos para enlistarlas*/
        for (File archivo : listaArchivos) {
            listaRutaArchivos.add(archivo.getPath());
        }
        /*Ayuda a ordenar los archivos sin identificar mayusculas y minusculas*/
        Collections.sort(listaRutaArchivos, String.CASE_INSENSITIVE_ORDER);

        /*Para identificar si es un archivo o una carpeta*/
        for (int i = x; i < listaRutaArchivos.size(); i++) {
            File archivo = new File(listaRutaArchivos.get(i));

            if (archivo.isFile()) {
                listaNombreArchivos.add(archivo.getName());
            } else {
                listaNombreArchivos.add("/" + archivo.getName());
            }
        }

        if (listaArchivos.length < 1){
            listaNombreArchivos.add("No hay archivos");
            listaRutaArchivos.add(rutaDirectorio);
        }

        /*Convertir la lista en objetos para mostrarlos en la pantalla*/
        ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, listaNombreArchivos);
        listaArchivosView.setAdapter(adapter);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        File archivo = new File(listaRutaArchivos.get(position));

        if(archivo.isFile()){
            String ubicacion = archivo.getAbsolutePath();

            if(archivo==null){
                ruta.setText("No es posible abrir este archivo");
            }else {

                TextUtils.StringSplitter formato = new TextUtils.SimpleStringSplitter('.');
                formato.setString(archivo.getName());

                String nombre = "n";
                String direccion = "d";
                String dirNombre = "dn";

                for (String s : formato){
                    if (s.equals("pdf")){
                        nombre = archivo.getName();
                        direccion = archivo.getPath();
                        dirNombre = archivo.getAbsolutePath();
                    }
                }

                if (nombre!= "n" && direccion!="d") {
                    FilePdf pdf = new FilePdf();
                    pdf.setName(nombre);
                    pdf.setDireccion(direccion);
                    pdf.setDirName(dirNombre);

                    pdf.save();

                    Intent intent = new Intent(this,MainActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(this,"Archivo no compatible",Toast.LENGTH_SHORT).show();
                }
            }
        }
        else{
            verArchivosDirectorio(listaRutaArchivos.get(position));

        }

    }







}