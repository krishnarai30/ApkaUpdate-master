package sd_dtu.apkaupdate;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by sam AR on 6/14/2016.
 */
public class DisplayDetails extends AppCompatActivity {


    TextView pstation,firno,officer,mobile,query,date;
    Button button;
    SQLiteDatabase sqLiteDatabase;
    FIRDB firdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_display_details);

        String s = getIntent().getStringExtra("FIR");

        getSupportActionBar().setTitle(s);

        pstation = (TextView) findViewById(R.id.station);
        firno = (TextView) findViewById(R.id.fir_no);
        officer = (TextView) findViewById(R.id.name1);
        mobile = (TextView) findViewById(R.id.mobile);
        query = (TextView) findViewById(R.id.query1);
        button = (Button) findViewById(R.id.comp_button);
        date = (TextView)findViewById(R.id.date);

        firno.setText(s);

        firdb = new FIRDB(getApplicationContext());
        sqLiteDatabase = firdb.getReadableDatabase();
        Cursor cursor = firdb.search(s,sqLiteDatabase);
        if(cursor.moveToFirst())
        {
            String station = cursor.getString(0);
            String iofficer = cursor.getString(1);
            String mobileno = cursor.getString(2);
            String qquery = cursor.getString(3);
            String ddate = cursor.getString(4);
            pstation.setText(station);
            officer.setText(iofficer);
            mobile.setText(mobileno);
            query.setText(qquery);
            date.setText(ddate);
        }


        Toast.makeText(this,"Complaint Button will start working here after 10 days.",Toast.LENGTH_LONG).show();


    }
}
