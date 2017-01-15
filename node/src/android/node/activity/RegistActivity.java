package android.node.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.node.server.SQLiteDatabaseHelper;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class RegistActivity extends Activity {
    /** Called when the activity is first created. */
	
	public static String DATABASE="node";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regist);
//        Intent intent = new Intent();
//        intent.setClass(this, LoginActivity.class);
//        this.startActivity(intent);
//        this.finish();
        Button registBT= (Button) this.findViewById(R.id.registBT);
        registBT.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				// TODO Auto-generated method stub
				TextView tv = (TextView)findViewById(R.id.textUser);
				String username = tv.getText().toString();
				tv = (TextView)findViewById(R.id.textPassword);
				String password = tv.getText().toString();
				tv = (TextView)findViewById(R.id.rePassword);
				String rePassword = tv.getText().toString();
				if(username==null||"".equals(username)){
					RegistActivity.this.DisplayToast("用户名不能为空！");
//					new AlertDialog.Builder(MainActivity.this).setTitle(BIND_ABOVE_CLIENT).setOnKeyListener(new OnKeyListener(){
//
//						public boolean onKey(DialogInterface dialog,
//								int keyCode, KeyEvent event) {
//							// TODO Auto-generated method stub
//							return true;
//						}});
				}else if(password==null||"".equals(password)||!password.equals(rePassword)){
					RegistActivity.this.DisplayToast("密码不能为空，并且两次输入必须一样！");
				}else{
					SQLiteDatabaseHelper sdh = new SQLiteDatabaseHelper(RegistActivity.this);
					SQLiteDatabase db = sdh.getWritableDatabase();
					//String sql = "INSERT INTO sys_user(username,password,actived) VALUES('"+username+"','"+password+"',1)";
					String sql ="INSERT INTO node(node_id,node_name,node_explain) VALUES(1,'wen','wenxueyong')";
					db.execSQL(sql);
					db.close();
			        Intent intent = new Intent();
			        intent.setClass(RegistActivity.this, LoginActivity.class);
			        RegistActivity.this.startActivity(intent);
			        RegistActivity.this.finish();
				}
			}});
    }
    
	/* 显示Toast */
	public void DisplayToast(String str){
		Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
	}
    
}