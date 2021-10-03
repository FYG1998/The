package com.example.demo.utils;

import android.app.KeyguardManager;
import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.CancellationSignal;
import android.os.Handler;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

/**
 * 指纹识别工具类
 */
public class FingerUtils {

   // 核心的两个 api:
    private final FingerprintManager fingerprintManager;
    private final KeyguardManager keyguardManager;

    @RequiresApi(api = Build.VERSION_CODES.M)
    private FingerUtils(Context context) {
        fingerprintManager = (FingerprintManager) context.getSystemService(Context.FINGERPRINT_SERVICE);
        keyguardManager = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
    }

    private static FingerUtils singleton = null;

    @RequiresApi(api = Build.VERSION_CODES.M)
    public static FingerUtils getInstance(Context context) {
        if (singleton == null) {
            synchronized (FingerUtils.class) {
                if (singleton == null) {
                    singleton = new FingerUtils(context);
                }
            }
        }
        return singleton;
    }


    /**
     * ②检查手机硬件（有没有指纹感应区）
     */

    @RequiresApi(api = Build.VERSION_CODES.M)
    public boolean isHardFinger() {
        if (fingerprintManager != null && fingerprintManager.isHardwareDetected()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * ③检查手机是否开启锁屏密码
     */

    public boolean isWindowSafe() {
        if (keyguardManager != null && keyguardManager.isKeyguardSecure()) {
            return true;
        } else {
            //Toast.makeText(Context(), "没有开启锁屏密码", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    /**
     * ④检查手机是否已录入指纹
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    public boolean isHaveHandler() {
        if (fingerprintManager != null && fingerprintManager.hasEnrolledFingerprints()) {

            return true;
        } else {
            return false;
        }
    }

    /**
     * 创建指纹验证
     * 参数中最重要的就是 cancellationSignal和 callback，其他传null 和 0 就行，
     * cancellationsignal  是用来取消指纹验证的，而callback 可以回调 指纹验证失败次数 或者指纹验证成功、
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void authenticate(FingerprintManager.CryptoObject cryptoObject, CancellationSignal cancellationSignal,
                             int flag,
                             FingerprintManager.AuthenticationCallback authenticationCallback, Handler handler) {
        if (fingerprintManager != null) {
            fingerprintManager.authenticate(cryptoObject, cancellationSignal, flag, authenticationCallback, handler);
        }
    }

    /**
     * 取消指纹验证  . 应该不会用上
     */
    public void cannelFinger(CancellationSignal cancellationSignal) {
        cancellationSignal.cancel();

    }
}

