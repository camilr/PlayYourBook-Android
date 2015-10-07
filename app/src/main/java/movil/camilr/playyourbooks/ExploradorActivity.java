package movil.camilr.playyourbooks;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
            Intent intent = new Intent(this,MainActivity.class);
            intent.putExtra("ubicacion",ubicacion);
            startActivity(intent);
        }
        else{
            verArchivosDirectorio(listaRutaArchivos.get(position));

        }

    }





}