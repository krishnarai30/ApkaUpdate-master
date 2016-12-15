package sd_dtu.apkaupdate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }
    public void onClickPrevQuery(View view){
        Intent intent=new Intent(MenuActivity.this,AllQueries.class);
        startActivity(intent);
    }
    public void onClickNewQuery(View view){
        Intent intent=new Intent(MenuActivity.this,QueryActivity.class);
        startActivity(intent);
    }

}
