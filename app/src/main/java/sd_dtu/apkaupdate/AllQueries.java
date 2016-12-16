package sd_dtu.apkaupdate;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class AllQueries extends AppCompatActivity {

    ArrayAdapter<String> adapter;
    FIRDB firdb;
    SQLiteDatabase sqLiteDatabase;
    Cursor cursor;

    ArrayList<String> firarr = new ArrayList<String>();

    String number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_queries);
        ListView Querieslv=(ListView)findViewById(R.id.alquerieslv);


        firdb = new FIRDB(getApplicationContext());
        sqLiteDatabase = firdb.getReadableDatabase();
        cursor = firdb.retrievecusrsor(sqLiteDatabase);
        if(cursor.moveToFirst())
        {
            do{
                number = cursor.getString(1);
                firarr.add("FIR : " + number);
            }while (cursor.moveToNext());
        }

        adapter = new ArrayAdapter<String>(this,R.layout.listview_custom_layout,R.id.list_item,firarr);
        Querieslv.setAdapter(adapter);

        Querieslv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(AllQueries.this,DisplayDetails.class);
                intent.putExtra("FIR",number);
                startActivity(intent);
            }
        });
    }


}
