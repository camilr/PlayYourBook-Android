package movil.camilr.playyourbooks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class InicioActivity extends AppCompatActivity {

    TextView txtText;
    TextView txtUbicacion;
    String ubicacion="";

    private void abrirArchivo(){
        try{
            txtUbicacion.setText(ubicacion);
            File f = new File(ubicacion);

            if(f==null){
                txtText.setText("Archivo no soportado");
            }

            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String texto = br.readLine();
            String aux ="";

            while(texto!=null){
                aux=aux+texto;
                texto = br.readLine();
            }
            txtText.setText(aux);
            br.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
    }


}
