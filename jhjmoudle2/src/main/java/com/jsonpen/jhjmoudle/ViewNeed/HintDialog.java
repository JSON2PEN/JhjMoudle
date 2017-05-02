package com.jsonpen.jhjmoudle.ViewNeed;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.jsonpen.jhjmoudle.R;


/**
 * 自定义dialog,带有确定和取消按钮的;
 */
public class HintDialog extends Dialog {

	public interface HintDialogListener {
		public void onConfirm(HintDialog dialog);

		public void onCancel(HintDialog dialog);
	};

	private HintDialogListener listener;

	public HintDialog setListener(HintDialogListener listener) {
		this.listener = listener;
		return this;
	}

	public String contentStr = "", titleStr = "", cancelStr = "", confirmStr = "";
	Context context;
	public Button confirmBtn;// 确定
	public Button cancelBtn;// 取消
	public TextView tv_title;// 提示
	public TextView tv_dialog_content;// 提示内容
	public boolean noShowCancelBtn = true;// 是否显示确定按钮

	public HintDialog(Context context, String contentStr) {
		super(context, R.style.SingleBtnDialog);
		this.context = context;
		this.contentStr = contentStr;
	}

	/**
	 *
	 * @param context
	 * @param contentStr
	 * @param confirmStr
     */
	public HintDialog(Context context, String contentStr, String confirmStr) {
		super(context, R.style.SingleBtnDialog);
		this.context = context;
		this.contentStr = contentStr;
		this.confirmStr = confirmStr;
	}

	/**
	 *
	 * @param context
	 * @param titleStr
	 * @param contentStr
	 * @param cancelStr
	 * @param confirmStr
     * @param noShowCancelBtn
     */
	public HintDialog(Context context, String titleStr, String contentStr, String cancelStr, String confirmStr, boolean noShowCancelBtn) {
		super(context, R.style.SingleBtnDialog);
		this.context = context;
		this.contentStr = contentStr;
		this.confirmStr = confirmStr;
		this.titleStr = titleStr;
		this.cancelStr = cancelStr;
		this.noShowCancelBtn = noShowCancelBtn;
	}

	public HintDialog(Context context, int layout) {
		super(context, R.style.SingleBtnDialog);
		this.context = context;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 指定布局
		setContentView(R.layout.dialog_hint);

		// 获取到宽度和高度后，可用于计算
		DisplayMetrics dm = new DisplayMetrics();
		((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(dm);

		WindowManager.LayoutParams params = this.getWindow().getAttributes();
		params.width = (int)(dm.widthPixels*0.7);
		params.height = LayoutParams.WRAP_CONTENT;
		this.getWindow().setAttributes(params);

		// 根据id在布局中找到控件对象
		tv_title = (TextView) findViewById(R.id.tv_title);
		confirmBtn = (Button) findViewById(R.id.confirm_btn);
		cancelBtn = (Button) findViewById(R.id.cancel_btn);
		tv_dialog_content = (TextView) findViewById(R.id.tv_dialog_content);

		tv_dialog_content.setText(contentStr);
//		if (!TextUtils.isEmpty(titleStr)) {
//			tv_title.setText(titleStr);
//		} else {
//			tv_title.setVisibility(View.GONE);
//		}
		if (!TextUtils.isEmpty(cancelStr)) {
			cancelBtn.setText(cancelStr);
		}
		if (!TextUtils.isEmpty(confirmStr)) {
			confirmBtn.setText(confirmStr);
		}
		// 设置按钮的文本颜色


		// 为按钮绑定点击事件监听器
		confirmBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				listener.onConfirm(HintDialog.this);
			}
		});
		if (!noShowCancelBtn) {
			cancelBtn.setVisibility(View.GONE);
		}
		cancelBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				listener.onCancel(HintDialog.this);
			}
		});
	}
}