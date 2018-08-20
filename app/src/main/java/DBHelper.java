import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Jiwon on 2018-08-20.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "DearDB";
    public static final String USER_TABLE_NAME = "USER";
    public static final String MESSAGE_TABLE_NAME = "MESSAGE";

    public DBHelper(Context context) {  super(context, DB_NAME, null, 1);}
    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTable = "create table " + USER_TABLE_NAME + " ( email text primary key autoincrement, nickname text );";
        db.execSQL(createTable);

        String createMessageTable = "create table" + MESSAGE_TABLE_NAME + " ( id integer primary key autoincrement, status integer, title text, delivered datetime, created datetim, updated datetime );";
        db.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}