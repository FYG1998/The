package com.example.demo.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.DrawFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

/**
 * 圆角图片
 */

public class RoundPic extends androidx.appcompat.widget.AppCompatImageView {
    private Paint mPaint;
    private int mRadius;
    private float mScale;

    public RoundPic(Context context) {
        super(context);
    }

    public RoundPic(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public RoundPic(Context context, AttributeSet attributeSet, int n2) {
        super(context, attributeSet, n2);
    }

    @SuppressLint(value="DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        this.mPaint = new Paint();
        Drawable drawable2 = this.getDrawable();
        if (drawable2 != null) {
            Bitmap bitmap = ((BitmapDrawable)drawable2).getBitmap();
            BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            this.mScale = 2.0f * (float)this.mRadius / (float)Math.min((int)bitmap.getHeight(), (int)bitmap.getWidth());
            Matrix matrix = new Matrix();
            matrix.setScale(this.mScale, this.mScale);
            bitmapShader.setLocalMatrix(matrix);
            this.mPaint.setShader((Shader)bitmapShader);
            canvas.setDrawFilter((DrawFilter)new PaintFlagsDrawFilter(0, 3));
            canvas.drawCircle((float)this.mRadius, (float)this.mRadius, (float)this.mRadius, this.mPaint);
            return;
        }
        super.onDraw(canvas);
    }

    @Override
    protected void onMeasure(int n2, int n3) {
        super.onMeasure(n2, n3);
        int n4 = Math.min((int)this.getMeasuredWidth(), (int)this.getMeasuredHeight());
        this.mRadius = n4 / 2;
        this.setMeasuredDimension(n4, n4);
    }
}

