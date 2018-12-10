package uts.eai.elabs.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;


import java.util.List;

import uts.eai.elabs.R;
import uts.eai.elabs.data.DataQuestion;


public class AdapterQuestion extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<DataQuestion> items;

    public AdapterQuestion(Activity activity, List<DataQuestion> items) {
        this.activity = activity;
        this.items = items;
    }


    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int location) {
        return items.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row_question, null);

        TextView id = (TextView) convertView.findViewById(R.id.id);
        TextView soal = (TextView) convertView.findViewById(R.id.soal);
        TextView jawaban = (TextView) convertView.findViewById(R.id.jawaban);

        RadioButton opsi1 = convertView.findViewById(R.id.opsi1);
        RadioButton opsi2 = convertView.findViewById(R.id.opsi2);
        RadioButton opsi3 = convertView.findViewById(R.id.opsi3);
        RadioButton opsi4 = convertView.findViewById(R.id.opsi4);


        DataQuestion data = items.get(position);

        id.setText(data.getId());
        soal.setText(data.getSoal());
        jawaban.setText(data.getJawaban());

        opsi1.setText(data.getOpsi1());
        opsi2.setText(data.getOpsi2());
        opsi3.setText(data.getOpsi3());
        opsi4.setText(data.getOpsi4());



        return convertView;
    }

}