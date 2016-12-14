package sd_dtu.apkaupdate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.katepratik.msg91api.MSG91;

import java.util.Random;


public class VerifyActivity extends AppCompatActivity {

    Button otpbtn;
    EditText emob;
    String smob;
    MSG91 msg91 = new MSG91("130512A0kXtdnd5820df36");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);
        emob=(EditText)findViewById(R.id.Mobile);

        otpbtn=(Button)findViewById(R.id.rqstbtn);
        msg91.validate();


    }

    public void onClick(View view){
        Random r = new Random();
        int randomOTP = r.nextInt(9999 - 1000) + 1000;
        String random=Integer.toString(randomOTP);
         msg91.getBalance("4");


        smob=emob.getText().toString().trim();

        msg91.composeMessage("DELPOL",random+" is your One Time Password(OTP) for APKA UPDATE - Delhi Police.");
        msg91.to(smob);
        msg91.setCountryCode("91");
        msg91.setRoute("4");
        msg91.send();

        Intent intent=new Intent(VerifyActivity.this,OtpCheck.class);
        intent.putExtra("choice",random);
        startActivity(intent);
    }



}
