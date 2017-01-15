package android.node.activity;

import android.app.Activity;
import android.content.Intent;
import android.node.model.Node;
import android.node.server.NodeService;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);
        Button addBT = (Button) this.findViewById(R.id.add_sure);
        addBT.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				// TODO Auto-generated method stub
				EditText tv = (EditText)findViewById(R.id.add_node_name);
				String nodeName = tv.getText().toString();
				if(nodeName==null||"".equals(nodeName)){
					MainActivity.this.DisplayToast("元素名不能为空！");
				}else{
					NodeService db = new NodeService(MainActivity.this);
					Node node = new Node();
					node.setName(nodeName);
					tv = (EditText)findViewById(R.id.add_node_explain);
					node.setExplain(tv.getText().toString());
					db.addNode(node);
			        Intent intent = new Intent();
			        intent.setClass(MainActivity.this, NodesActivity.class);
			        MainActivity.this.startActivity(intent);
			        MainActivity.this.finish();
				}
			}});
    }
    
	/* 显示Toast */
	public void DisplayToast(String str){
		Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
	}
    
}