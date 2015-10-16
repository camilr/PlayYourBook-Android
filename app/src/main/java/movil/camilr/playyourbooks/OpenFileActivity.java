package movil.camilr.playyourbooks;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.pdf.PdfRenderer;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.util.List;

import movil.camilr.playyourbooks.entities.FilePdf;

public class OpenFileActivity extends AppCompatActivity {


    public static final String EXTRA_POSICION_ARCHIVO = "posicion archivo";
    public static final String EXTRA_DIR = "dir";

    int posicionArchivo;
    List<FilePdf> data;
    FilePdf file;
    String dir;

    // LECTURA DE PDF
    private ImageView openFile;
    private int currentPage=0;
    private Button previous,next;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_file);

        Intent intent = getIntent();
        posicionArchivo = intent.getIntExtra(EXTRA_POSICION_ARCHIVO,0);
        dir =  intent.getStringExtra(EXTRA_DIR);

        file = new FilePdf();
        data = file.listAll(FilePdf.class);

        file = data.get(posicionArchivo);

        //Lectura del pdf
        next = (Button) findViewById(R.id.next);
        previous = (Button) findViewById(R.id.previous);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPage++;
                render();
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPage--;
                render();
            }
        });
        render();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_open_file, menu);
        return true;
    }

    @Override

    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void render (){

        String directorioRaiz;

        try {
            openFile = (ImageView) findViewById(R.id.open_file);
            int REQ_WIDTH = openFile.getWidth();
            int REQ_HEIGHT = openFile.getHeight();

            //sacando la ruta raiz del dispositivo
            directorioRaiz=Environment.getExternalStorageDirectory().getPath();

            Bitmap bitmap = Bitmap.createBitmap(REQ_WIDTH, REQ_HEIGHT, Bitmap.Config.ARGB_4444);


            File archivo =new File(""+dir);


            PdfRenderer renderer = new PdfRenderer(ParcelFileDescriptor.open(archivo, ParcelFileDescriptor.MODE_READ_ONLY));



            if (currentPage < 0){
                currentPage=0;
            } else if (currentPage > renderer.getPageCount()){
                currentPage = renderer.getPageCount() -1;
            }
            Matrix m = openFile.getImageMatrix();
            Rect rect = new Rect(0, 0, REQ_WIDTH, REQ_HEIGHT);
            renderer.openPage(currentPage).render(bitmap,rect,m,PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);
            openFile.setImageMatrix(m);
            openFile.setImageBitmap(bitmap);
            openFile.invalidate();

        }catch (Exception e){
            e.printStackTrace();
        }
    }







}
