package sd_dtu.apkaupdate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class AllQueries extends AppCompatActivity {

    ArrayAdapter<String> adapter;

    String[] firarr={"fir111","fir112","fir113"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_queries);
        ListView Querieslv=(ListView)findViewById(R.id.alquerieslv);


        adapter = new ArrayAdapter<String>(this,R.layout.listview_custom_layout,R.id.list_item,firarr);
        Querieslv.setAdapter(adapter);

        Querieslv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent,View view,int position,long id){
                switch(position){
                    case 1: Intent questioni=new Intent(AllQueries.this,DisplayDetails.class);
                        startActivity(questioni);
                        break;
                    case 0:Intent notesi=new Intent(AllQueries.this,DisplayDetails.class);
                        startActivity(notesi);
                        break;
                    case 2:Intent ebooksi=new Intent(AllQueries.this,DisplayDetails.class);
                        startActivity(ebooksi);
                        break;
                }
            }
        });
    }


}
