package movil.camilr.playyourbooks;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.pdf.PdfRenderer;
import android.os.ParcelFileDescriptor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.util.List;

import movil.camilr.playyourbooks.entities.FilePdf;
import movil.camilr.playyourbooks.fragments.LocalFragment;

public class OpenFileActivity extends AppCompatActivity {

    int currentPage = 0;
    ImageView openFile;
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



        renderPdf();

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

    public void renderPdf(){
        try {

            openFile = (ImageView) findViewById(R.id.open_file);
            int REQ_WIDTH = 1;
            int REQ_HEIGHT = 1;
            REQ_WIDTH = openFile.getWidth();
            REQ_HEIGHT = openFile.getHeight();

            Bitmap bitmap = Bitmap.createBitmap(REQ_WIDTH, REQ_HEIGHT, Bitmap.Config.ARGB_4444);
            File file2 = new File("/storage/emulated/0/bluetooth/PMBOOK.PMI.pdf.pdf");

            PdfRenderer render = new PdfRenderer(ParcelFileDescriptor.open(file2, ParcelFileDescriptor.MODE_READ_ONLY));

            if (currentPage < 0) {
                currentPage = 0;
            } else if (currentPage > render.getPageCount()) {
                currentPage = render.getPageCount() - 1;
            }

            Matrix m = openFile.getImageMatrix();
            Rect rect = new Rect(0,0,REQ_WIDTH,REQ_HEIGHT);
            render.openPage(6).render(bitmap,rect,m,PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);
            openFile.setImageMatrix(m);
            openFile.setImageBitmap(bitmap);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
