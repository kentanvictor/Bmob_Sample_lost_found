package com.bmob.lostfound;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Window;
import android.widget.Toast;

import com.bmob.lostfound.config.Constants;

import cn.bmob.v3.Bmob;

/**
 * ����
 * @ClassName: BaseActivity
 * @Description: TODO
 * @author smile
 * @date 2014-5-20 ����9:55:34
 */
public abstract class BaseActivity extends Activity {

	protected int mScreenWidth;
	protected int mScreenHeight;
	
	public static final String TAG = "bmob";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//第一：默认初始化
		// 注:自v3.5.2开始，数据sdk内部缝合了统计sdk，开发者无需额外集成，传渠道参数即可，不传默认没开启数据统计功能
		Bmob.initialize(this, Constants.Bmob_APPID);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//��ȡ��ǰ��Ļ���
		DisplayMetrics metric = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metric);
		mScreenWidth = metric.widthPixels;
		mScreenHeight = metric.heightPixels;
		
		setContentView();
		initViews();
		initListeners();
		initData();
	}
	public abstract void setContentView();

	public abstract void initViews();
	public abstract void initListeners();
	public abstract void initData();
	Toast mToast;

	public void ShowToast(String text) {
		if (!TextUtils.isEmpty(text)) {
			if (mToast == null) {
				mToast = Toast.makeText(getApplicationContext(), text,
						Toast.LENGTH_SHORT);
			} else {
				mToast.setText(text);
			}
			mToast.show();
		}
	}
	
	/** ��ȡ��ǰ״̬���ĸ߶�
	  * getStateBar
	  * @Title: getStateBar
	  * @throws
	  */
	public  int getStateBar(){
		Rect frame = new Rect();
		getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
		int statusBarHeight = frame.top;
		return statusBarHeight;
	}
	
	public static int dip2px(Context context,float dipValue){
		float scale=context.getResources().getDisplayMetrics().density;		
		return (int) (scale*dipValue+0.5f);		
	}
	
}
