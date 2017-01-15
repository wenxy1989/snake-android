package android.node.activity;

import android.app.Activity;
import android.graphics.Color;
import android.node.server.NodeService;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class NodeDeleteActivity extends Activity {
	
	private NodeService nodeService;
	
	private void initActivity(){
		TextView tv = (TextView) this.findViewById(R.id.delete_node_name);
		tv.setText(tv.getText()+":"+NodeService.Now_Node.getName());
		tv = (TextView) this.findViewById(R.id.delete_node_explain);
		tv.setText(tv.getText()+":"+NodeService.Now_Node.getExplain());
		tv = (TextView) this.findViewById(R.id.delete_node_warn);
		tv.setTextColor(Color.RED);
		Button btn = (Button) this.findViewById(R.id.delete_node_sure);
		//删除确认操作，删除当前数据，关闭本界面
		btn.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				nodeService.deleteNodeById(NodeService.Now_Node_Id);
				NodeDeleteActivity.this.finish();
			}});
		btn = (Button) this.findViewById(R.id.delete_node_cancel);
		//删除取消界面，关闭本界面
		btn.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				NodeDeleteActivity.this.finish();
			}});
	}
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.delete);
        nodeService = new NodeService(this);
        if(NodeService.Now_Node.getId()!=NodeService.Now_Node_Id)
        	NodeService.Now_Node = nodeService.findNodeById(NodeService.Now_Node_Id);
        this.initActivity();
    }

}
