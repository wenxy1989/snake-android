package android.node.server;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.node.model.Node;

public class NodeService extends BaseDB {
	
	public static Node Now_Node;		//为单一元素的持续操作提供数据
	public static String Now_Node_User;	//界定元素的拥有用户
	public static int Now_Node_From;	//界定元素列表来源
	public static int Now_Node_Id;		//界定当前操作的唯一元素
	
	public NodeService(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 装配并返回数据集c中的node数据
	 * @param c
	 * @return node记录
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
	 * 分析node名是否存在，不存在则添加新node记录
	 * @param node
	 * @return 是否添加成功
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
	 * 删除指定id的node记录
	 * @param id
	 * @return 成功返回true，失败返回false
	 */
	public boolean deleteNodeById(int id){
		if(this.findNodeById(id)!=null){
			return this.deleteById("node", id);
		}
		return false;
	}
	
	/**
	 * 分析要修改的node，如果node没有修改name或者修改的name在同一层不存那么执行修改
	 * @param node
	 * @return 修改是否成功
	 */
	public boolean updateNode(Node node){
		StringBuffer sb = new StringBuffer();
		SQLiteDatabase dbr = this.getDBReader();
		sb.append("select * from node where node_id=");
		sb.append(node.getId());
		sb.append(" and node_name='");
		sb.append(node.getName());
		sb.append("'");
		//（两个node，id及name都相同，即要更新的node，name没有修改）不成立，进行下一种可能测试
		if(!dbr.rawQuery(sb.toString(),null).moveToFirst()){
			sb.setLength(0);
			sb.append("select * from node where node_id!=");
			sb.append(node.getId());
			sb.append(" and node_from=");
			sb.append(node.getFrom());
			sb.append(" and node_name='");
			sb.append(node.getName());
			sb.append("'");
			//（两个node，id不同，from、name相同，即修改的name在同一from下没有重复）成立，不修改
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
	 * 得到符合当前用户的指定id的node数据
	 * @param id
	 * @return node数据
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
	 * 得到当前用户的指定from的node列表
	 * @param from
	 * @return 无数据返回null
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
