package android.node.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.node.model.Node;
import android.node.server.BaseDB;
import android.node.server.NodeService;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

@SuppressWarnings("unused")
public class NodesActivity extends Activity{
	
	private List<Node> nodes = null;
	private NodeService nodeService = null;
	
	private void loadNodes(){
		Button btn  = (Button) this.findViewById(R.id.list_node_up);
		if(NodeService.Now_Node_From>1){
			btn.setVisibility(View.VISIBLE);
		}else{
			btn.setVisibility(View.GONE);
		}
        nodeService = new NodeService(this);
        nodes = nodeService.findNodesByFrom(NodeService.Now_Node_From);
        NodeAdapter nodeAdapter = new NodeAdapter(this);
        ListView lv = (ListView) this.findViewById(R.id.list_view);
        lv.setAdapter(nodeAdapter);
	}
	
	private void initNode(){
		NodeService.Now_Node_From = 1;
		NodeService.Now_Node_Id = 1;
		NodeService.Now_Node_User = "wen";
		NodeService.Now_Node = new Node();
		NodeService.Now_Node.setId(1);
		NodeService.Now_Node.setFrom(0);
        this.setContentView(R.layout.list);
        Button btn = (Button) this.findViewById(R.id.list_node_up);
        //上一层动作
        btn.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				if(NodeService.Now_Node_From>1){
					NodeService.Now_Node_From = nodeService.findNodeById(NodeService.Now_Node_From).getFrom();
					loadNodes();
				}else{
					DisplayToast("已经是最顶层！");
				}
			}});
        btn = (Button) this.findViewById(R.id.list_node_add);
        //添加动作,打开添加界面
        btn.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				openActivity(NodeAddActivity.class);
			}});
	}
	
	/**
	 * 打开需要的activity，原activity隐藏
	 * @param clazz
	 */
	private void openActivity(Class<? extends Activity> clazz){
		Intent intent = new Intent();
		intent.setClass(this, clazz);
		this.startActivity(intent);
	}
	
	/**
	 * 转到指定的activity，原activity关闭
	 * @param clazz
	 */
	private void turnToActivity(Class<? extends Activity> clazz){
		Intent intent = new Intent();
		intent.setClass(this, clazz);
		this.startActivity(intent);
		this.finish();
	}
	
	//activity生命周期创建
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.initNode();
    }

	//activity生命周期起始
	public void onStart(){
		super.onStart();
		this.loadNodes();
	}

	//activity生命周期恢复
	public void onResume(){
		super.onResume();
	}
	
	public final class NodeHolder{
		TextView idTV;
		TextView nameTV;
		TextView explainTV;
		Button editBtn;
		Button deleteBtn;
	}
	
	public final class NodeAdapter extends BaseAdapter{
		
		private LayoutInflater inflater;
		
		public NodeAdapter(Context context){
			this.inflater = LayoutInflater.from(context);
		}

		public int getCount() {
			return nodes.size();
		}

		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		/**
		 * adaper需要返回给listview指定列需要的view
		 */
		public View getView(int position, View convertView, ViewGroup parent) {
			NodeHolder holder = null;
			if(convertView == null){
				holder = new NodeHolder();
				convertView = inflater.inflate(R.layout.nodes, null);
				holder.idTV = (TextView) convertView.findViewById(R.id.list_node_id);
				holder.nameTV = (TextView) convertView.findViewById(R.id.list_node_name);
				holder.explainTV = (TextView) convertView.findViewById(R.id.list_node_explain);
				holder.editBtn = (Button) convertView.findViewById(R.id.list_node_edit);
				holder.deleteBtn = (Button) convertView.findViewById(R.id.list_node_delete);
				convertView.setTag(holder);
			}else{
				holder = (NodeHolder) convertView.getTag();
			}
			holder.idTV.setText(String.valueOf(nodes.get(position).getId()));
			holder.nameTV.setText(nodes.get(position).getName());
			holder.explainTV.setText(nodes.get(position).getExplain());
			//元素名被点击事件，显示此元素的子元素列表
			holder.nameTV.setOnClickListener(new OnClickListener(){
				public void onClick(View view){
					NodeService.Now_Node_From = getIdByView(view);
					NodesActivity.this.loadNodes();
				}
			});
			//元素详解被点击事件，显示此元素详细信息
			holder.explainTV.setOnClickListener(new OnClickListener(){
				public void onClick(View view){
					NodeService.Now_Node_Id = getIdByView(view);
					openActivity(NodeDetailActivity.class);
				}
			});
			//修改按钮被点击事件，转到元素编辑视图
			holder.editBtn.setOnClickListener(new OnClickListener(){
				public void onClick(View view){
					NodeService.Now_Node_Id = getIdByView(view);
					openActivity(NodeEditActivity.class);
				}
			});
			//删除按钮被点击事件，转到元素删除视图
			holder.deleteBtn.setOnClickListener(new OnClickListener(){
				public void onClick(View view){
					NodeService.Now_Node_Id = getIdByView(view);
					openActivity(NodeDeleteActivity.class);
				}
			});
//			convertView.setOnClickListener(new OnClickListener(){
//
//				public void onClick(View v) {
//					// TODO Auto-generated method stub
//					NodeHolder holder = (NodeHolder) v.getTag();
//					final int nodeId = Integer.parseInt((String) holder.idTV.getText().toString());//元素名被点击事件
//					DisplayToast(String.valueOf(nodeId));
//					NodesActivity.this.nodeService.deleteNodeById(nodeId);
//					NodesActivity.this.loadNodes();
//					
//				}});
			return convertView;
		}
		
	}
	
	/**
	 * 在listview中的标签通过同级的标签得到当前被操作的元素id
	 * @param view
	 * @return 当前操作的元素id
	 */
	private int getIdByView(View view){
		NodeHolder group = (NodeHolder) ((View) view.getParent()).getTag();
		int id = Integer.parseInt(group.idTV.getText().toString());
		//DisplayToast("explain"+tv.getText().toString());
		return id;
	}
	

	/* 显示Toast */
	public void DisplayToast(String str){
		Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
	}

}
