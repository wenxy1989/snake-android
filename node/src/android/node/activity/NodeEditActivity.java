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
        //�������水ť��������ģ�ת������ϸ�Ľ��棬�ص�������
        btn.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				NodeService.Now_Node.setName(NodeEditActivity.this.edit_node_name.getText().toString());
				NodeService.Now_Node.setExplain(NodeEditActivity.this.edit_node_explain.getText().toString());
				if(NodeEditActivity.this.nodeService.updateNode(NodeService.Now_Node)){
					NodeEditActivity.this.finish();
				}else{
					DisplayToast("ͬĿ¼���Ѵ�����ͬ��Ԫ����");
				}
			}});
        //���ذ�ť
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

	/* ��ʾToast */
	public void DisplayToast(String str){
		Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
	}

}
