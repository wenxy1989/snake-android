package android.node.server;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class BaseDB{
	private SQLiteDatabaseHelper sdh;
	BaseDB(Context context){
		sdh = new SQLiteDatabaseHelper(context);
	}
	
	/**
	 * �õ����ݿ���޸�Ȩ��
	 * @return SQLiteDatabase DBWriter
	 */
	public SQLiteDatabase getDBWriter(){
		return sdh.getWritableDatabase();
	}
	
	/**
	 * �õ����ݿ�Ķ�ȡȨ��
	 * @return SQLiteDatabase DBReader
	 */
	public SQLiteDatabase getDBReader(){
		return sdh.getReadableDatabase();
	}

	/**
	 * ������ݿ��table��������ݣ��õ������id
	 * @return maxId
	 */
	int getMaxId(String table){
		SQLiteDatabase db = this.getDBReader();
		StringBuffer sb  = new StringBuffer();
		sb.append("select ");
		sb.append(table);
		sb.append("_id from ");
		sb.append(table);
		sb.append(" order by ");
		sb.append(table);
		sb.append("_id desc limit 0,1");
		Cursor c = db.rawQuery(sb.toString(), null);
		c.moveToFirst();
		int count = c.getInt(0);
		c.close();
		db.close();
		return count;
	}
	
	/**
	 * ������ݿ��table��������ݣ��õ�����������
	 * @return count
	 */
	int getCount(String table){
		SQLiteDatabase db = this.getDBReader();
		String sql = "select count(*) from " + table;
		Cursor c = db.rawQuery(sql, null);
		c.moveToFirst();
		int count = c.getInt(0);
		db.close();
		return count;
	}
	
	/**ɾ����table��ָ��id�ļ�¼
	 * @param table
	 * @param id
	 * @return �ɹ�����true��ʧ�ܷ���false
	 */
	boolean deleteById(String table,int id){
		SQLiteDatabase db = this.getDBWriter();
		StringBuffer sb  = new StringBuffer();
		try{
			sb.append("delete from ");
			sb.append(table);
			sb.append(" where ");
			sb.append(table);
			sb.append("_id=");
			sb.append(id);
			db.execSQL(sb.toString());
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}finally{
			db.close();
		}
	}

}
