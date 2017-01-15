package android.node.server;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.widget.Toast;

public class CommonTools {
	
	/**
	 * 以提醒方式显示指定的字符
	 * @param context
	 * @param str
	 */
	public static void DisplayToast(Context context,String str){
		Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
	}

	/**
	 * 得到格式为“yyyy-MM-dd hh:mm:ss”当前时间的字符串
	 * @return 当前时间（字符型数据）
	 */
	public static String getNowDate(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return df.format(new Date());
	}

}
