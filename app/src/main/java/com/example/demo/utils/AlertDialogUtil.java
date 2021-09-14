package com.example.demo.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.example.demo.R;


/**
 * Created by 弹窗提示
 */
public class AlertDialogUtil {

    public static AlertDialogUtil getInstance() {
        return new AlertDialogUtil();
    }

    private static OnButtonClickListener onButtonClickListener;

    public interface OnButtonClickListener {
        /**
         * 确定按钮点击操作
         *
         * @param dialog 当前 AlertDialog，传入它是为了在调用的地方对 dialog 做操作，比如 dismiss()
         *               *               也可以在该工具类中直接  dismiss() 掉，就不用将 AlertDialog 对象传出去了
         */
        void onPositiveButtonClick(AlertDialog dialog);

        /**
         * 取消按钮点击操作
         *
         * @param dialog
         */
        void onNegativeButtonClick(AlertDialog dialog);
    }

    public void setOnButtonClickListener(OnButtonClickListener onButtonClickListener) {
        this.onButtonClickListener = onButtonClickListener;
    }

    /**
     * 确认弹框
     *
     * @param context
     * @param message
     */
    public static void showConfirmDialog(Context context, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);
        final AlertDialog dialog = builder.create();
        dialog.show();
        View view = View.inflate(context, R.layout.alert_dialog_confirm, null);
        TextView tvMsg = view.findViewById(R.id.tv_dialog_message);
        TextView tvConfirm = view.findViewById(R.id.tv_dialog_confirm);

        tvMsg.setText(message);
        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onButtonClickListener != null) {
                    onButtonClickListener.onPositiveButtonClick(dialog);//点取消
                }
            }
        });
        dialog.getWindow().setContentView(view);
    }



}
