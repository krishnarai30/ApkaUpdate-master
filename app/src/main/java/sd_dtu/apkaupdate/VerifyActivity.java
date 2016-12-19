package sd_dtu.apkaupdate;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.katepratik.msg91api.MSG91;

import java.util.Random;


public class VerifyActivity extends AppCompatActivity {

    Button otpbtn;
    EditText emob;
    String smob;
    MSG91 msg91 = new MSG91("130512A0kXtdnd5820df36");

    public static boolean f = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);

        emob=(EditText)findViewById(R.id.Mobile);
        otpbtn=(Button)findViewById(R.id.rqstbtn);

        msg91.validate();


    }

    public boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    @Override
    public void onBackPressed() {
        return;
    }

    public void onClick(View view){


        if(isNetworkAvailable(getApplicationContext()))
        {
            otpbtn.setEnabled(true);

        smob=emob.getText().toString().trim();


        if(smob.length()<10)
        {
            Toast.makeText(getApplicationContext(),"Enter the proper Number",Toast.LENGTH_LONG).show();
        }
        else {
            Random r = new Random();
            int randomOTP = r.nextInt(9999 - 1000) + 1000;
            String random=Integer.toString(randomOTP);

            //Toast.makeText(getApplicationContext(),random,Toast.LENGTH_LONG).show();

            msg91.getBalance("4");
            msg91.composeMessage("DELPOL",random+" is your One Time Password(OTP) for APKA UPDATE - Delhi Police.");
            msg91.to(smob);
            msg91.setCountryCode("91");
            msg91.setRoute("4");

            Toast.makeText(getApplicationContext(),"Wait while you recieve the OTP..",Toast.LENGTH_LONG).show();

            msg91.send();



            ProgressDialog progressDialog=new ProgressDialog(VerifyActivity.this);
            progressDialog.setTitle("Apka Update");
            progressDialog.setMessage("Loading...");
            progressDialog.show();


            Intent intent = new Intent(VerifyActivity.this, OtpCheck.class);
            intent.putExtra("choice", random);
            intent.putExtra("number", smob);
            startActivity(intent);
            //progressDialog.dismiss();
        }
        }
        else {

            otpbtn.setEnabled(false);

            Toast.makeText(getApplicationContext(),"NO INTERNET SERVICE",Toast.LENGTH_LONG).show();
        }
    }


}
