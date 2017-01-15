package android.node.server;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.node.model.Node;

public class NodeService extends BaseDB {
	
	public static Node Now_Node;		//Ϊ��һԪ�صĳ��������ṩ����
	public static String Now_Node_User;	//�綨Ԫ�ص�ӵ���û�
	public static int Now_Node_From;	//�綨Ԫ���б���Դ
	public static int Now_Node_Id;		//�綨��ǰ������ΨһԪ��
	
	public NodeService(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * װ�䲢�������ݼ�c�е�node����
	 * @param c
	 * @return node��¼
	 */
	private Node getNodeFromCursor(Cursor c){
		try{
			Node node = new Node();
			node.setId(c.getInt(c.getColumnIndex("node_id")));
			node.setName(c.getString(c.getColumnIndex("node_name")));
			node.setExplain(c.getString(c.getColumnIndex("node_explain")));
			node.setFrom(c.getInt(c.getColumnIndex("node_from")));
			node.setUser(c.getString(c.getColumnIndex("node_user")));
			node.setCreate(c.getString(c.getColumnIndex("node_create")));
			node.setUpdate(c.getString(c.getColumnIndex("node_update")));
			return node;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * ����node���Ƿ���ڣ��������������node��¼
	 * @param node
	 * @return �Ƿ���ӳɹ�
	 */
	public boolean addNode(Node node){
		StringBuffer sb = new StringBuffer();
		sb.append("select * from node where node_from=");
		sb.append(NodeService.Now_Node_From);
		sb.append(" and node_name='");
		sb.append(node.getName());
		sb.append("'");
		SQLiteDatabase dbr = this.getDBReader();
		if(dbr.rawQuery(sb.toString(), null).moveToFirst()){
			dbr.close();
			return false;
		}
		dbr.close();
		int id = this.getMaxId("node")+1;
		ContentValues content = new ContentValues(); 
		content.put("node_id", id);
		content.put("node_name", node.getName());
		content.put("node_explain", node.getExplain());
		content.put("node_from", NodeService.Now_Node_From);
		content.put("node_user", NodeService.Now_Node_User);
		content.put("node_create", CommonTools.getNowDate());
		SQLiteDatabase dbw = this.getDBWriter();
		try{
			dbw.insert("node", "null", content);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}finally{
			dbw.close();
		}
	}
	
	/**
	 * ɾ��ָ��id��node��¼
	 * @param id
	 * @return �ɹ�����true��ʧ�ܷ���false
	 */
	public boolean deleteNodeById(int id){
		if(this.findNodeById(id)!=null){
			return this.deleteById("node", id);
		}
		return false;
	}
	
	/**
	 * ����Ҫ�޸ĵ�node�����nodeû���޸�name�����޸ĵ�name��ͬһ�㲻����ôִ���޸�
	 * @param node
	 * @return �޸��Ƿ�ɹ�
	 */
	public boolean updateNode(Node node){
		StringBuffer sb = new StringBuffer();
		SQLiteDatabase dbr = this.getDBReader();
		sb.append("select * from node where node_id=");
		sb.append(node.getId());
		sb.append(" and node_name='");
		sb.append(node.getName());
		sb.append("'");
		//������node��id��name����ͬ����Ҫ���µ�node��nameû���޸ģ���������������һ�ֿ��ܲ���
		if(!dbr.rawQuery(sb.toString(),null).moveToFirst()){
			sb.setLength(0);
			sb.append("select * from node where node_id!=");
			sb.append(node.getId());
			sb.append(" and node_from=");
			sb.append(node.getFrom());
			sb.append(" and node_name='");
			sb.append(node.getName());
			sb.append("'");
			//������node��id��ͬ��from��name��ͬ�����޸ĵ�name��ͬһfrom��û���ظ������������޸�
			if(dbr.rawQuery(sb.toString(),null).moveToFirst()){
				dbr.close();
				return false;
			}
		}
		dbr.close();
		sb.setLength(0);
		sb.append("update node set node_name='");
		sb.append(node.getName());
		sb.append("', node_explain='");
		sb.append(node.getExplain());
		sb.append("', node_update='");
		sb.append(CommonTools.getNowDate());
		sb.append("' where node_user='");
		sb.append(NodeService.Now_Node_User);
		sb.append("' and node_id=");
		sb.append(String.valueOf(node.getId()));
		SQLiteDatabase db = this.getDBWriter();
		//Object[] obj = {this.getNowDate()}; 
		try{
			db.execSQL(sb.toString());
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}finally{
			db.close();
		}
	}
	
	/**
	 * �õ����ϵ�ǰ�û���ָ��id��node����
	 * @param id
	 * @return node����
	 */
	public Node findNodeById(int id){
		String sql = "select * from node where node_user='"+NodeService.Now_Node_User+"' and node_id=" + id;
		SQLiteDatabase db = this.getDBReader();
		Cursor c = db.rawQuery(sql, null);
		try{
			c.moveToFirst();
			Node node = this.getNodeFromCursor(c);
			c.close();
			return node;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			db.close();
		}
	}
	
	/**
	 * �õ���ǰ�û���ָ��from��node�б�
	 * @param from
	 * @return �����ݷ���null
	 */
	public List<Node> findNodesByFrom(int from){
		String sql = "select * from node where node_user='"+NodeService.Now_Node_User+"' and node_from=" + from;
		SQLiteDatabase db = this.getDBReader();
		Cursor c = db.rawQuery(sql, null);
		try{
			c.moveToFirst();
			List<Node> list = new ArrayList<Node>();
			while(!c.isAfterLast()){
				list.add(this.getNodeFromCursor(c));
				c.moveToNext();
			}
			c.close();
			return list;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			db.close();
		}
	}
	
}
