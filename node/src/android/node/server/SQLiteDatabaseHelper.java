package android.node.server;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteDatabaseHelper extends SQLiteOpenHelper {
	
	public static final String DATABASE ="node.db";
	
	public static final int VERSION = 2;
	
	public SQLiteDatabaseHelper(Context context) {
		super(context, SQLiteDatabaseHelper.DATABASE, null, SQLiteDatabaseHelper.VERSION);
	}

	@Override//创建数据库中的基本表，并将基本数据写入创建的表中
	public void onCreate(SQLiteDatabase db) {
		StringBuffer sb = new StringBuffer();
//		sb.append("CREATE  TABLE user ");
//		sb.append("(user_id INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL ,");
//		sb.append(" user_name VARCHAR, user_password VARCHAR, user_actived INTEGER);");
		sb.append("CREATE  TABLE node ");
		sb.append("(node_id INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL ,");
		sb.append(" node_name VARCHAR, node_explain VARCHAR, node_from INTEGER,");
		sb.append(" node_user INTEGER, node_create VARCHAR, node_update VARCHAR)");
		db.execSQL(sb.toString());
		String sql = "insert into node (node_id,node_name,node_explain,node_from,node_user,node_create)values(1,'root','node root',0,?,?)";
		Object[] obj = {NodeService.Now_Node_User,CommonTools.getNowDate()};
		db.execSQL(sql, obj);
	}

	@Override//db为数据操作对象，oldVersion为当前可能装的版本，newVersion为当前需要安装的版本
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS node");
		onCreate(db);
	}

}
