package sd_dtu.apkaupdate;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

public class SplashScreen extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash_screen);

        Thread myThread=new Thread(){
            @Override
            public void run() {
                try {
                    sleep(3000);
//                    SharedPreferences sharedPref = getSharedPreferences("data",MODE_PRIVATE);
//                    int number = sharedPref.getInt("isLogged", 0);
//                    if(number == 0) {
//                        //Open the login activity and set this so that next it value is 1 then this conditin will be false.
                        Intent intent=new Intent(getApplicationContext(),VerifyActivity.class);
                        startActivity(intent);
                        finish();
//                        SharedPreferences.Editor prefEditor = sharedPref.edit();
//                        prefEditor.putInt("isLogged",1);
//                        prefEditor.commit();
//                    } else {
//                        Intent intent=new Intent(getApplicationContext(),MenuActivit.class);
//                        startActivity(intent);
//                        finish();
                    //}

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();

    }

}
