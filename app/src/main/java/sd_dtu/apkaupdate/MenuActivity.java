package sd_dtu.apkaupdate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        button = (Button) findViewById(R.id.newquerybtn);
    }
    public void onClickPrevQuery(View view){
        Intent intent=new Intent(MenuActivity.this,AllQueries.class);
        startActivity(intent);
    }
    public void onClickNewQuery(View view){
        if(isNetworkAvailable(getApplicationContext()))
        {
            Intent intent=new Intent(MenuActivity.this,QueryActivity.class);
            startActivity(intent);
        }
        else
        {
            button.setEnabled(false);
//            new AlertDialog.Builder(getApplicationContext())
//                    .setTitle("NO INTERNET CONNECTIVITY")
//                    .setMessage("Please make sure you have an active internet connection!")
//                    .setIcon(android.R.drawable.ic_dialog_alert)
//                    .show();
            Toast.makeText(getApplicationContext(),"NO INTERNET SERVICE",Toast.LENGTH_LONG).show();
        }
    }

    public boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }


    @Override
    public void onBackPressed() {
        return;
    }
}
