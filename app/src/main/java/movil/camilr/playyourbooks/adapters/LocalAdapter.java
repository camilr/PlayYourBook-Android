package movil.camilr.playyourbooks.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import movil.camilr.playyourbooks.R;
import movil.camilr.playyourbooks.entities.FilePdf;

/**
 * Created by Cami on 6/10/2015.
 */
public class LocalAdapter extends BaseAdapter {

    List<FilePdf> data;
    Context context;

    public LocalAdapter(List<FilePdf> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v;

        if (convertView == null)
            v = View.inflate(context, R.layout.template_adpter_filepdf,null);
        else
            v = convertView;

        TextView nombreLocal;
        TextView direccionLocal;

        nombreLocal = (TextView) v.findViewById(R.id.nombre_archivo_pdf);
        direccionLocal = (TextView) v.findViewById(R.id.direccion_archivo_pdf);

        nombreLocal.setText(data.get(position).getName());
        direccionLocal.setText(data.get(position).getDireccion());

        return v;
    }
}
