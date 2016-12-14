package sd_dtu.apkaupdate;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



public class OtpCheck extends AppCompatActivity {
    TextView Text;
    TextView resendotp;
    boolean isCounterRunning=false;
    EditText otp;
    String otpstr,option;


    private Handler handler=new Handler();



    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_check);
        Text=(TextView)findViewById(R.id.time);
        resendotp=(TextView)findViewById(R.id.resend);
        otp=(EditText)findViewById(R.id.enterOTP);

          option=getIntent().getStringExtra("choice");





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
