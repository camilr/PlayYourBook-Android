package movil.camilr.playyourbooks.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.orm.SugarContext;

import java.util.ArrayList;
import java.util.List;

import movil.camilr.playyourbooks.PagerTitle;
import movil.camilr.playyourbooks.R;
import movil.camilr.playyourbooks.adapters.LocalAdapter;
import movil.camilr.playyourbooks.entities.FilePdf;

/**
 * A simple {@link Fragment} subclass.
 */
public class LocalFragment extends PagerTitle {


    List<FilePdf> data;
    ListView listaLocalPdf;


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
}

