package fr.pds.isintheair.crmtab.mbalabascarin.uc.edit.crv.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import fr.pds.isintheair.crmtab.R;
import fr.pds.isintheair.crmtab.mbalabascarin.uc.edit.crv.model.Visit;

/**
 * Created by Muthu on 06/02/2016.
 */
public class VisitAdapter extends ArrayAdapter<Visit> {

    private Context context;
    private List<Visit> objects;
    TextView title, infos , infos2, lblCount;
    public VisitAdapter(Context context,  List<Visit> objects) {
        super(context, R.layout.reportlist_layout, objects);
        this.context = context;
        this.objects = objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.reportlist_layout, parent, false);

        //get views
        title = (TextView) rowView.findViewById(R.id.textTitle);
        infos = (TextView) rowView.findViewById(R.id.textInfo);
        infos2 = (TextView) rowView.findViewById(R.id.textInfo2);
        lblCount = (TextView) rowView.findViewById(R.id.txtCount);


        title.setText("Visite fait le: " + objects.get(position).getDate());
        infos.setText("Sujet: " + objects.get(position).getSubject());
        lblCount.setText(objects.get(position).getDate());

        return  rowView;
    }
}