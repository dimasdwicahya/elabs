package uts.eai.elabs.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


import java.util.List;

import uts.eai.elabs.R;
import uts.eai.elabs.data.DataLatihan;


public class AdapterRecyclerLatihan extends RecyclerView.Adapter<AdapterRecyclerLatihan.ViewHolder> {

    private List<DataLatihan> items;
    private SparseIntArray mSelections;

    public AdapterRecyclerLatihan(List<DataLatihan> items) {
        this.items = items;
        mSelections = new SparseIntArray();

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_question_latihan, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        DataLatihan item = items.get(position);
        holder.id.setText(item.getId());
        holder.soal.setText(item.getSoal());
        holder.jawaban.setText(item.getJawaban());

        holder.opsi1.setText(item.getOpsi1());
        holder.opsi2.setText(item.getOpsi2());
        holder.opsi3.setText(item.getOpsi3());
        holder.opsi4.setText(item.getOpsi4());

        holder.itemView.setTag(item);

        holder.radioGroup.setOnCheckedChangeListener(null);
        holder.radioGroup.clearCheck();

        if(mSelections.get(position) > -1) {
            holder.radioGroup.check(mSelections.get(position));
        }

        holder.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {

                switch(checkedId) {
                    case R.id.opsi1:
                        //set radio opsi1 ke jawaban
                        items.get(position).setDipilih(holder.opsi1.getText()+"");
                        mSelections.put(position, radioGroup.getCheckedRadioButtonId());
                        notifyDataSetChanged();
                        break;

                    case R.id.opsi2:
                        //set radio opsi2 ke jawaban
                        items.get(position).setDipilih(holder.opsi2.getText()+"");
                        mSelections.put(position, radioGroup.getCheckedRadioButtonId());
                        notifyDataSetChanged();
                        break;

                    case R.id.opsi3:
                        //set radio opsi3 ke jawaban
                        items.get(position).setDipilih(holder.opsi3.getText()+"");
                        mSelections.put(position, radioGroup.getCheckedRadioButtonId());
                        notifyDataSetChanged();
                        break;

                    case R.id.opsi4:
                        //set radio opsi4 ke jawaban
                        Log.i("Jawaban", holder.opsi4.getText()+" "+items.get(position).getId());
                        items.get(position).setDipilih(holder.opsi4.getText()+"");
                        mSelections.put(position, radioGroup.getCheckedRadioButtonId());
                        notifyDataSetChanged();
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    //ini untuk mengambil data list di adapter recycler
    public List<DataLatihan> getAllAnswer(){
        return items;
    }

    //view holder untuk data binding
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView id;
        TextView soal;
        TextView jawaban;
        RadioButton opsi1;
        RadioButton opsi2;
        RadioButton opsi3;
        RadioButton opsi4;
        RadioGroup radioGroup;

        public ViewHolder(View itemView) {
            super(itemView);

            //inisialisasi xml
            id       = (TextView) itemView.findViewById(R.id.id);
            soal     = (TextView) itemView.findViewById(R.id.soal);
            jawaban  = (TextView) itemView.findViewById(R.id.jawaban);

            radioGroup = itemView.findViewById(R.id.rgJawaban);
            opsi1 = itemView.findViewById(R.id.opsi1);
            opsi2 = itemView.findViewById(R.id.opsi2);
            opsi3 = itemView.findViewById(R.id.opsi3);
            opsi4 = itemView.findViewById(R.id.opsi4);
        }
    }
}