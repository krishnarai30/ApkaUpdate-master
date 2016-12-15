package sd_dtu.apkaupdate;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by mohitkumar on 15/12/16.
 */

public class FIRDB extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION  = 1;
    private static final String DATABASE_NAME = "FIRINFO.DB";
    private static final String CREATE_QUERY = "CREATE TABLE " + UserContract.FirInfo.TABLENAME + "(" + UserContract.FirInfo.STATION +
            " TEXT," + UserContract.FirInfo.FIRNO + " TEXT," + UserContract.FirInfo.INOFFICER + " TEXT," + UserContract.FirInfo.MOBILE + " TEXT," + UserContract.FirInfo.QUERY + " TEXT);";

    public FIRDB(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        Log.e("DATABASE OPERATIONS","Database created/opened...");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_QUERY);
        Log.e("DATABASE OPERATIONS","Database added");
    }

    public void addinformation(String station,String firno,String officer,String mobile,String query,SQLiteDatabase db)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(UserContract.FirInfo.STATION,station);
        contentValues.put(UserContract.FirInfo.FIRNO,firno);
        contentValues.put(UserContract.FirInfo.INOFFICER,officer);
        contentValues.put(UserContract.FirInfo.MOBILE,mobile);
        contentValues.put(UserContract.FirInfo.QUERY,query);
        db.insert(UserContract.FirInfo.TABLENAME,null,contentValues);
        Log.e("DATABASE OPERATIONS","One row is inserted");
    }

    public Cursor retrievecusrsor(SQLiteDatabase db)
    {
        Cursor cursor;
        String[] projection = {UserContract.FirInfo.STATION,UserContract.FirInfo.FIRNO,UserContract.FirInfo.INOFFICER,
                UserContract.FirInfo.MOBILE,UserContract.FirInfo.QUERY};

        cursor =  db.query(UserContract.FirInfo.TABLENAME,projection,null,null,null,null,null);
        return cursor;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
