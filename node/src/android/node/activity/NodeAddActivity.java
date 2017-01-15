package android.node.activity;

import android.app.Activity;
import android.node.model.Node;
import android.node.server.NodeService;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NodeAddActivity extends Activity{
	
	private void initActivity(){
		Button btn= (Button) this.findViewById(R.id.add_sure);
		//添加操作，添加元素，关闭本界面
        btn.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				EditText tv = (EditText)findViewById(R.id.add_node_name);
				String nodeName = tv.getText().toString();
				if(nodeName==null||"".equals(nodeName)){
					NodeAddActivity.this.DisplayToast("元素名不能为空！");
				}else{
					NodeService db = new NodeService(NodeAddActivity.this);
					Node node = new Node();
					node.setName(nodeName);
					tv = (EditText)findViewById(R.id.add_node_explain);
					node.setExplain(tv.getText().toString());
					if(db.addNode(node)){
						NodeAddActivity.this.finish();
					}else{
						DisplayToast("同一目录下已存在相同的记录，添加失败");
					}
				}
			}});
        btn= (Button) this.findViewById(R.id.add_cancel);
        //返回操作，关闭本界面
        btn.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
		        NodeAddActivity.this.finish();
			}
        });
	}
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);
        this.initActivity();
    }
    
	/* 显示Toast */
	public void DisplayToast(String str){
		Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
	}
	
}
