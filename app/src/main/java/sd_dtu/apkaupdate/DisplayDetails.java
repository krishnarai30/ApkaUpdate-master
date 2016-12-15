package sd_dtu.apkaupdate;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by sam AR on 6/14/2016.
 */
public class DisplayDetails extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_display_details);

        Toast.makeText(this,"Complaint Button will start working here after 10 days.",Toast.LENGTH_LONG).show();


    }
}
