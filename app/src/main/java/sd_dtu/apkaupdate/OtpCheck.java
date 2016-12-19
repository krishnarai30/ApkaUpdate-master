package sd_dtu.apkaupdate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.CountDownTimer;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.katepratik.msg91api.MSG91;

import java.util.Random;


public class OtpCheck extends AppCompatActivity {
    TextView Text;
    TextView resendotp;
    boolean isCounterRunning=false;
    EditText otp;
    String otpstr,option;
    String snumber;

    public static boolean t = false;

    MSG91 msg91 = new MSG91("130512A0kXtdnd5820df36");


    private Handler handler=new Handler();


    @Override
    public void onBackPressed() {
        return;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_check);

        Text=(TextView)findViewById(R.id.time);
        resendotp=(TextView)findViewById(R.id.resend);
        otp=(EditText)findViewById(R.id.enterOTP);

        msg91.validate();

        option=getIntent().getStringExtra("choice");


        snumber = getIntent().getStringExtra("number");

        msg91.getBalance("4");




        final CountDownTimer countDownTimer=new CountDownTimer(180000, 1000) {

            public void onTick(long millisUntilFinished) {
                Text.setText("seconds remaining: " + millisUntilFinished / 1000);

            }

            public void onFinish() {
                isCounterRunning = false;
            }


        }.start();


        resendotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                {
                    if( !isCounterRunning ){
                        isCounterRunning = true;
                        countDownTimer.start();

                        Random r = new Random();
                        int randomOTP = r.nextInt(9999 - 1000) + 1000;
                        String random=Integer.toString(randomOTP);

                        option = random;

                        PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("OTP",option).commit();

                        msg91.getBalance("4");
                        msg91.composeMessage("DELPOL",random+" is your One Time Password(OTP) for APKA UPDATE - Delhi Police.");
                        msg91.to(snumber);
                        msg91.setCountryCode("91");
                        msg91.setRoute("4");
                        msg91.send();

                    }
                    else{
                        countDownTimer.cancel();
                        countDownTimer.start();
                    }

                }
            }
        });



    }



    public void onclick(View view){

        otpstr=otp.getText().toString();

        PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("OTP",option).commit();


        if(otpstr.equals(option)){



            Toast.makeText(OtpCheck.this,"User Verified!!!",Toast.LENGTH_LONG).show();
            Intent intent=new Intent(OtpCheck.this,MenuActivity.class);
            startActivity(intent);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                }
            },2500);
        }

        else{
            Toast.makeText(OtpCheck.this,"Error!! Wrong Otp try again!!", Toast.LENGTH_LONG).show();
        }


    }
}
