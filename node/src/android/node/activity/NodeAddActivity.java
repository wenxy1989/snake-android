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
		//��Ӳ��������Ԫ�أ��رձ�����
        btn.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				EditText tv = (EditText)findViewById(R.id.add_node_name);
				String nodeName = tv.getText().toString();
				if(nodeName==null||"".equals(nodeName)){
					NodeAddActivity.this.DisplayToast("Ԫ��������Ϊ�գ�");
				}else{
					NodeService db = new NodeService(NodeAddActivity.this);
					Node node = new Node();
					node.setName(nodeName);
					tv = (EditText)findViewById(R.id.add_node_explain);
					node.setExplain(tv.getText().toString());
					if(db.addNode(node)){
						NodeAddActivity.this.finish();
					}else{
						DisplayToast("ͬһĿ¼���Ѵ�����ͬ�ļ�¼�����ʧ��");
					}
				}
			}});
        btn= (Button) this.findViewById(R.id.add_cancel);
        //���ز������رձ�����
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
    
	/* ��ʾToast */
	public void DisplayToast(String str){
		Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
	}
	
}
