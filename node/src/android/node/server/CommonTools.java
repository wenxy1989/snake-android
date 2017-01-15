package android.node.server;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.widget.Toast;

public class CommonTools {
	
	/**
	 * �����ѷ�ʽ��ʾָ�����ַ�
	 * @param context
	 * @param str
	 */
	public static void DisplayToast(Context context,String str){
		Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
	}

	/**
	 * �õ���ʽΪ��yyyy-MM-dd hh:mm:ss����ǰʱ����ַ���
	 * @return ��ǰʱ�䣨�ַ������ݣ�
	 */
	public static String getNowDate(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return df.format(new Date());
	}

}
