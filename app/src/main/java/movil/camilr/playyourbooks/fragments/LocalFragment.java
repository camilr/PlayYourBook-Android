package movil.camilr.playyourbooks.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.orm.SugarContext;

import java.util.ArrayList;
import java.util.List;

import movil.camilr.playyourbooks.OpenFileActivity;
import movil.camilr.playyourbooks.PagerTitle;
import movil.camilr.playyourbooks.R;
import movil.camilr.playyourbooks.adapters.LocalAdapter;
import movil.camilr.playyourbooks.entities.FilePdf;

/**
 * A simple {@link Fragment} subclass.
 */
public class LocalFragment extends PagerTitle implements AdapterView.OnItemClickListener {

    public static final String POSICION_ARCHIVO = "posicion archivo";

    List<FilePdf> data;
    ListView listaLocalPdf;
    FilePdf file;


    public LocalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v;
        v = inflater.inflate(R.layout.fragment_local, container, false);

        SugarContext.init(getContext());

        listaLocalPdf = (ListView) v.findViewById(R.id.lista_local);

        registerForContextMenu(listaLocalPdf);

        listaLocalPdf.setOnItemClickListener(this);

        getAllItems();

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();

        getAllItems();
    }

    @Override
    public String getTitle() {
        return "Local";
    }

    public void getAllItems(){
        data = new ArrayList<>();
        FilePdf f = new FilePdf();

        data = f.listAll(FilePdf.class);

        LocalAdapter adapter = new LocalAdapter(data,getContext());
        listaLocalPdf.setAdapter(adapter);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(Menu.NONE,R.id.opcion_eliminar,Menu.NONE,"Eliminar");

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        file = new FilePdf();

        data = file.listAll(FilePdf.class);

        switch (item.getItemId()){
            case R.id.opcion_eliminar:
                file = data.get(info.position);
                file.delete();
                getAllItems();
                Toast.makeText(getContext(),"Archivo eliminado",Toast.LENGTH_SHORT).show();
                break;

        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

       Intent intent = new Intent(getContext(),OpenFileActivity.class);
        intent.putExtra(POSICION_ARCHIVO,position);
        startActivity(intent);
    }
}

