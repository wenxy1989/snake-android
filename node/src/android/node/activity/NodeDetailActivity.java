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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

@SuppressWarnings("unused")
public class NodeDetailActivity extends Activity{

    NodeService nodeService = null;
	
    /**
	 * 转到指定的activity，原activity关闭
	 * @param clazz
	 */
	private void openActivity(Class<? extends Activity> clazz){
		Intent intent = new Intent();
		intent.setClass(this, clazz);
		this.startActivity(intent);
	}
    
    private void initActivity(){
    	TextView tv = (TextView) this.findViewById(R.id.detail_node_name);
        tv.setText(this.getString(R.string.node_name)+":"+NodeService.Now_Node.getName());
        tv = (TextView) this.findViewById(R.id.detail_node_explain);
        tv.setText(this.getString(R.string.node_explain)+":"+NodeService.Now_Node.getExplain());
        Button btn = (Button) this.findViewById(R.id.detail_node_back);
        //返回操作，关掉本界面
        btn.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				NodeDetailActivity.this.finish();
			}});
        btn = (Button) this.findViewById(R.id.detail_node_edit);
        //编辑操作，转到编辑界面
        btn.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				openActivity(NodeEditActivity.class);
			}});
    }
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.node);
        nodeService = new NodeService(this);
        if(NodeService.Now_Node.getId()!=NodeService.Now_Node_Id)
        	NodeService.Now_Node = nodeService.findNodeById(NodeService.Now_Node_Id);
    }
	
	public void onStart(){
		super.onStart();
        this.initActivity();
	}

}
