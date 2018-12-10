package uts.eai.elabs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import uts.eai.elabs.adapter.AdapterRecyclerLatihan;
import uts.eai.elabs.data.DataLatihan;

public class MainActivityLatihan extends AppCompatActivity {

    RecyclerView list;
    Button btnSubmit;
    SwipeRefreshLayout swipe;
    List<DataLatihan> itemList = new ArrayList<DataLatihan>();
    AdapterRecyclerLatihan adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_latihan);

        list         = (RecyclerView) findViewById(R.id.list);
        btnSubmit    = (Button) findViewById(R.id.btSelesai);

        //inisialisasi recycler view
        list.setHasFixedSize(true);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setItemAnimator(new DefaultItemAnimator());

        //set dummy data
        setDummyData();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //fungsi untuk dapat semua jawaban di adapter
                List<DataLatihan>listAnswer = adapter.getAllAnswer();

                int nilai = 0;
                //looping untuk nilai
                for (int i = 0; i <listAnswer.size() ; i++) {
                    //cek jawaban sama atau tidak dengan kunci jawaban
                    if(listAnswer.get(i).getJawaban().equals(listAnswer.get(i).getDipilih())){
                        //jika jawaban betul maka nilai + 1
                        nilai = nilai + 1;
                    }
                }
                //tampilkan nilai di toast
                Toast.makeText(getApplicationContext(),"Nilai anda: "+nilai,Toast.LENGTH_LONG).show();
            }
        });

    }

    public void onBackPressed() {
        Intent startMain = new Intent(this, MainActivity.class);
        startActivity(startMain);
    }


    private void setDummyData() {

        //data dummy
        itemList.add(new DataLatihan("1","Hello ini soal no 1","Jawaban A",
                "Jawaban A","Jawaban B","Jawaban C","Jawaban D"));

        itemList.add(new DataLatihan("2","Hello ini soal no 2","Jawaban B",
                "Jawaban A","Jawaban B","Jawaban C","Jawaban D"));

        itemList.add(new DataLatihan("3","Hello ini soal no 3","Jawaban A",
                "Jawaban A","Jawaban B","Jawaban C","Jawaban D"));

        itemList.add(new DataLatihan("4","Hello ini soal no 4","Jawaban C",
                "Jawaban A","Jawaban B","Jawaban C","Jawaban D"));

        itemList.add(new DataLatihan("5","Hello ini soal no 5","Jawaban D",
                "Jawaban A","Jawaban B","Jawaban C","Jawaban D"));

        itemList.add(new DataLatihan("6","Hello ini soal no 6","Jawaban D",
                "Jawaban A","Jawaban B","Jawaban C","Jawaban D"));

        itemList.add(new DataLatihan("7","Hello ini soal no 7","Jawaban D",
                "Jawaban A","Jawaban B","Jawaban C","Jawaban D"));

        itemList.add(new DataLatihan("8","Hello ini soal no 8","Jawaban D",
                "Jawaban A","Jawaban B","Jawaban C","Jawaban D"));

        itemList.add(new DataLatihan("9","Hello ini soal no 9","Jawaban D",
                "Jawaban A","Jawaban B","Jawaban C","Jawaban D"));

        itemList.add(new DataLatihan("10","Hello ini soal no 10","Jawaban D",
                "Jawaban A","Jawaban B","Jawaban C","Jawaban D"));


        //set adapter untuk recycler view
        adapter = new AdapterRecyclerLatihan(itemList);
        list.setAdapter(adapter);

    }
}