package movil.camilr.playyourbooks.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import movil.camilr.playyourbooks.PagerTitle;
import movil.camilr.playyourbooks.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecientesFragment extends PagerTitle {


    public RecientesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recientes, container, false);
    }


    @Override
    public String getTitle() {
        return "Recientes";
    }
}
