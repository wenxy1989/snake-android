package android.node.activity;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.node.server.SQLiteDatabaseHelper;
import android.os.Bundle;
import android.widget.TextView;

public class LoginActivity extends Activity {
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    	this.setContentView(R.layout.login);
    	TextView tv = (TextView) this.findViewById(R.id.sqlusername);
    	SQLiteDatabaseHelper sdh = new SQLiteDatabaseHelper(this);
    	SQLiteDatabase db = sdh.getReadableDatabase();
    	Cursor cursor = db.rawQuery("SELECT node_name FROM node", null);
    	cursor.moveToFirst();
    	StringBuffer sb = new StringBuffer();
    	while(!cursor.isAfterLast()){
    		sb.append(cursor.getString(0)+",");
    		cursor.moveToNext();
    	}
    	cursor.close();
    	db.close();
    	tv.setText(sb);
    }
}
