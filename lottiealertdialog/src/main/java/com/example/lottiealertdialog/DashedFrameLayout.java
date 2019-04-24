package com.example.lottiealertdialog;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import kotlin.jvm.internal.Intrinsics;

public class DashedFrameLayout extends FrameLayout {
    private Paint paint = new Paint();
    private Path path = new Path();

    public DashedFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setWillNotDraw(false);
        this.paint.setARGB(100, 10, 10, 10);
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setStrokeWidth(2.0f);
        this.paint.setPathEffect(new DashPathEffect(new float[]{10.0f, 15.0f}, 0.0f));
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        Intrinsics.checkParameterIsNotNull(canvas, "canvas");
        this.path.reset();
        this.path.moveTo(getWidth() / 2, 0.0f);
        this.path.lineTo(getWidth() / 2, getHeight());
        this.path.close();
        canvas.drawPath(this.path, this.paint);
        super.dispatchDraw(canvas);
    }
}
