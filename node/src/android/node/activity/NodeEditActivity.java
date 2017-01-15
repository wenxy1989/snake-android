package android.node.activity;

import android.app.Activity;
import android.node.server.NodeService;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NodeEditActivity extends Activity{
	
	NodeService nodeService = null;
	EditText edit_node_name = null,edit_node_explain = null;
	
	private void initActivity(){
        edit_node_name = (EditText) this.findViewById(R.id.edit_node_name);
        edit_node_name.setText(NodeService.Now_Node.getName());
        edit_node_explain = (EditText) this.findViewById(R.id.edit_node_explain);
        edit_node_explain.setText(NodeService.Now_Node.getExplain());
        Button btn = (Button) this.findViewById(R.id.edit_save);
        //单击保存按钮，保存更改，转到相信细心界面，关掉本界面
        btn.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				NodeService.Now_Node.setName(NodeEditActivity.this.edit_node_name.getText().toString());
				NodeService.Now_Node.setExplain(NodeEditActivity.this.edit_node_explain.getText().toString());
				if(NodeEditActivity.this.nodeService.updateNode(NodeService.Now_Node)){
					NodeEditActivity.this.finish();
				}else{
					DisplayToast("同目录下已存在相同的元素名");
				}
			}});
        //返回按钮
        btn = (Button) this.findViewById(R.id.edit_back);
        btn.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				NodeEditActivity.this.finish();
			}});
	}
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.edit);
    	nodeService = new NodeService(this);
        if(NodeService.Now_Node.getId()!=NodeService.Now_Node_Id)
        	NodeService.Now_Node = nodeService.findNodeById(NodeService.Now_Node_Id);
        this.initActivity();
    }

	/* 显示Toast */
	public void DisplayToast(String str){
		Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
	}

}
