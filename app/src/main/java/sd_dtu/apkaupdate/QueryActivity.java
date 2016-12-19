package sd_dtu.apkaupdate;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.katepratik.msg91api.MSG91;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by sam AR on 6/4/2016.
 */

public class QueryActivity extends Activity{



    Spinner districtsp,pstationsp,statussp,querysp;
    EditText fillnodd;
    Button donedd;
    EditText Name,Query;
    MSG91 msg91 = new MSG91("130512A0kXtdnd5820df36");

    //Context context = this;
    SQLiteDatabase sqLiteDatabase;
    FIRDB firdb;

    //DetailsProvider detailsProvider;

    ArrayAdapter<CharSequence> districtadapter;
    ArrayAdapter<CharSequence> policestationadapter;
    ArrayAdapter<CharSequence> statusadapter;
    ArrayAdapter<CharSequence> queryadapter;

    //public static ArrayList<DetailsProvider> detailsProviders = new ArrayList<DetailsProvider>();


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);

        districtsp = (Spinner) findViewById(R.id.districtspinner);
        pstationsp = (Spinner) findViewById(R.id.police_station_spinner);
        statussp=(Spinner)findViewById(R.id.statusspinner);
        querysp=(Spinner)findViewById(R.id.queryspinner);
        fillnodd = (EditText) findViewById(R.id.fillno);
        Name = (EditText) findViewById(R.id.name);
        Query=(EditText)findViewById(R.id.customquery);


        fillnodd.setMovementMethod(new ScrollingMovementMethod());

        msg91.validate();

        donedd = (Button) findViewById(R.id.dropdownbtn);
        donedd.setMovementMethod(new ScrollingMovementMethod());

        districtadapter = ArrayAdapter.createFromResource(this, R.array.District, R.layout.support_simple_spinner_dropdown_item);
        districtadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        districtsp.setAdapter(districtadapter);
//        districtsp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//                switch (position) {
//
//                    case 0:
        policestationadapter = ArrayAdapter.createFromResource(getBaseContext(), R.array.Outer_District_PS, R.layout.support_simple_spinner_dropdown_item);
        policestationadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        pstationsp.setAdapter(policestationadapter);
//                        pstationsp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                            @Override
//                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                            }
//
//                            @Override
//                            public void onNothingSelected(AdapterView<?> parent) {
//                            }
//                        });
//                        break;
//                }
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//            }
//        });

        statusadapter = ArrayAdapter.createFromResource(getBaseContext(), R.array.IO_Shahbad0, R.layout.support_simple_spinner_dropdown_item);
        statusadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        statussp.setAdapter(statusadapter);
//        statussp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
        queryadapter=ArrayAdapter.createFromResource(this,R.array.QUERY,R.layout.support_simple_spinner_dropdown_item);
        queryadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        querysp.setAdapter(queryadapter);


        donedd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Date d = new Date();
                CharSequence s  = DateFormat.format("MMMM d, yyyy ", d.getTime());

                Calendar cal = Calendar.getInstance();

                int millisecond = cal.get(Calendar.MILLISECOND);
                int second = cal.get(Calendar.SECOND);
                int minute = cal.get(Calendar.MINUTE);
                int hourofday = cal.get(Calendar.HOUR_OF_DAY);

                String time = hourofday + ":" + minute + ":" + second;

                String station = pstationsp.getSelectedItem().toString();
                String firno = fillnodd.getText().toString();
                String iofficer = statussp.getSelectedItem().toString();
                String mobile = Name.getText().toString().trim();
                String query;
                if (querysp.getSelectedItem().toString().equals("Choose Query")) {
                    query = Query.getText().toString();
                } else {
                    Query.setEnabled(false);
                    query = querysp.getSelectedItem().toString();
                }

                if (firno.equals("") || mobile.equals("")) {
                    Toast.makeText(getApplicationContext(), "Enter all the fields !", Toast.LENGTH_LONG).show();
                }
                else if(mobile.length() < 10)
                {
                    Toast.makeText(getApplicationContext(),"Enter the correct mobile number !",Toast.LENGTH_LONG).show();
                }
                else {

                    msg91.getBalance("4");
                    msg91.to(mobile);
                    msg91.composeMessage("APKUDT","FIR NO : " + firno + "\n"  + query);
                    msg91.setCountryCode("91");
                    msg91.setRoute("4");
                    msg91.send();


                    ProgressDialog progressDialog=new ProgressDialog(QueryActivity.this);
                    progressDialog.setTitle("Apka Update");
                    progressDialog.setMessage("Loading...");
                    progressDialog.show();

                    firdb = new FIRDB(getApplicationContext());
                    sqLiteDatabase = firdb.getWritableDatabase();
                    firdb = new FIRDB(getApplicationContext());
                    firdb.addinformation(station, firno, iofficer, mobile, query,s.toString(),time,sqLiteDatabase);


                    // detailsProvider = new DetailsProvider(station,firno,iofficer,mobile,query);
                    Toast.makeText(QueryActivity.this, "Your Query is Submitted!!", Toast.LENGTH_LONG).show();

                    //Bundle bundle;

                    firdb.close();

                    Intent intent = new Intent(QueryActivity.this, AllQueries.class);
                    startActivity(intent);
                }
            }
        });
//        querysp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//
//
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
    }
}
