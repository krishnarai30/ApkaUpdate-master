package sd_dtu.apkaupdate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {


    public static int a = 2;


    SharedPreferences pref;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        pref = getSharedPreferences("one",MODE_PRIVATE);
        a = pref.getInt("LOGGED",0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        editor = pref.edit();
        editor.putInt("isLogged",1);
        editor.commit();
    }
    public void onClickPrevQuery(View view){
        Intent intent=new Intent(MenuActivity.this,AllQueries.class);
        startActivity(intent);
    }
    public void onClickNewQuery(View view){
        Intent intent=new Intent(MenuActivity.this,QueryActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        return;
    }
}
