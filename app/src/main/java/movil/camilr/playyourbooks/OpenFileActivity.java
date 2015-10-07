package movil.camilr.playyourbooks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import movil.camilr.playyourbooks.entities.FilePdf;
import movil.camilr.playyourbooks.fragments.LocalFragment;

public class OpenFileActivity extends AppCompatActivity {

    int posicionArchivo;
    List<FilePdf> data;
    FilePdf file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_file);

        Intent intent = getIntent();
        posicionArchivo = intent.getIntExtra(LocalFragment.POSICION_ARCHIVO,0);
        file = new FilePdf();
        data = file.listAll(FilePdf.class);

        file = data.get(posicionArchivo);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_open_file, menu);
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
