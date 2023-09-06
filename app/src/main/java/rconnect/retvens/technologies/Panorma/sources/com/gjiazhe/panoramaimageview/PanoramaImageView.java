package rconnect.retvens.technologies.Panorma.sources.com.gjiazhe.panoramaimageview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;

import rconnect.retvens.technologies.R;

@SuppressLint("AppCompatCustomView")
public class PanoramaImageView extends ImageView {
    public static final byte ORIENTATION_HORIZONTAL = 0;
    public static final byte ORIENTATION_NONE = -1;
    public static final byte ORIENTATION_VERTICAL = 1;
    private int mDrawableHeight;
    private int mDrawableWidth;
    private boolean mEnablePanoramaMode;
    private boolean mEnableScrollbar;
    private int mHeight;
    private boolean mInvertScrollDirection;
    private float mMaxOffset;
    private OnPanoramaScrollListener mOnPanoramaScrollListener;
    private byte mOrientation;
    private float mProgress;
    private Paint mScrollbarPaint;
    private int mWidth;

    public interface OnPanoramaScrollListener {
        void onScrolled(PanoramaImageView panoramaImageView, float f);
    }

    public PanoramaImageView(Context context) {
        this(context, (AttributeSet) null);
    }

    public PanoramaImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PanoramaImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mOrientation = -1;
        super.setScaleType(ImageView.ScaleType.CENTER_CROP);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.PanoramaImageView);
        this.mEnablePanoramaMode = typedArray.getBoolean(R.styleable.PanoramaImageView_piv_enablePanoramaMode, true);
        this.mInvertScrollDirection = typedArray.getBoolean(R.styleable.PanoramaImageView_piv_invertScrollDirection, false);
        this.mEnableScrollbar = typedArray.getBoolean(R.styleable.PanoramaImageView_piv_show_scrollbar, true);
        typedArray.recycle();
        if (this.mEnableScrollbar) {
            initScrollbarPaint();
        }
    }

    private void initScrollbarPaint() {
        this.mScrollbarPaint = new Paint(1);
        this.mScrollbarPaint.setColor(-1);
        this.mScrollbarPaint.setStrokeWidth(dp2px(1.5f));
    }

    public void setGyroscopeObserver(GyroscopeObserver observer) {
        if (observer != null) {
            observer.addPanoramaImageView(this);
        }
    }

    /* access modifiers changed from: package-private */
    public void updateProgress(float progress) {
        if (this.mEnablePanoramaMode) {
            if (this.mInvertScrollDirection) {
                progress = -progress;
            }
            this.mProgress = progress;
            invalidate();
            if (this.mOnPanoramaScrollListener != null) {
                this.mOnPanoramaScrollListener.onScrolled(this, -this.mProgress);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        this.mWidth = (View.MeasureSpec.getSize(widthMeasureSpec) - getPaddingLeft()) - getPaddingRight();
        this.mHeight = (View.MeasureSpec.getSize(heightMeasureSpec) - getPaddingTop()) - getPaddingBottom();
        if (getDrawable() != null) {
            this.mDrawableWidth = getDrawable().getIntrinsicWidth();
            this.mDrawableHeight = getDrawable().getIntrinsicHeight();
            if (this.mDrawableWidth * this.mHeight > this.mDrawableHeight * this.mWidth) {
                this.mOrientation = 0;
                this.mMaxOffset = Math.abs(((((float) this.mDrawableWidth) * (((float) this.mHeight) / ((float) this.mDrawableHeight))) - ((float) this.mWidth)) * 0.5f);
            } else if (this.mDrawableWidth * this.mHeight < this.mDrawableHeight * this.mWidth) {
                this.mOrientation = 1;
                this.mMaxOffset = Math.abs(((((float) this.mDrawableHeight) * (((float) this.mWidth) / ((float) this.mDrawableWidth))) - ((float) this.mHeight)) * 0.5f);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        if (!this.mEnablePanoramaMode || getDrawable() == null || isInEditMode()) {
            super.onDraw(canvas);
            return;
        }
        if (this.mOrientation == 0) {
            canvas.save();
            canvas.translate(this.mMaxOffset * this.mProgress, 0.0f);
            super.onDraw(canvas);
            canvas.restore();
        } else if (this.mOrientation == 1) {
            canvas.save();
            canvas.translate(0.0f, this.mMaxOffset * this.mProgress);
            super.onDraw(canvas);
            canvas.restore();
        }
        if (this.mEnableScrollbar) {
            switch (this.mOrientation) {
                case 0:
                    float barBgWidth = ((float) this.mWidth) * 0.9f;
                    float barWidth = (((float) this.mWidth) * barBgWidth) / ((float) this.mDrawableWidth);
                    float barBgStartX = (((float) this.mWidth) - barBgWidth) / 2.0f;
                    float barStartX = barBgStartX + (((barBgWidth - barWidth) / 2.0f) * (1.0f - this.mProgress));
                    float barY = ((float) this.mHeight) * 0.95f;
                    this.mScrollbarPaint.setAlpha(100);
                    canvas.drawLine(barBgStartX, barY, barBgStartX + barBgWidth, barY, this.mScrollbarPaint);
                    this.mScrollbarPaint.setAlpha(255);
                    canvas.drawLine(barStartX, barY, barStartX + barWidth, barY, this.mScrollbarPaint);
                    return;
                case BuildConfig.VERSION_CODE:
                    float barBgHeight = ((float) this.mHeight) * 0.9f;
                    float barHeight = (((float) this.mHeight) * barBgHeight) / ((float) this.mDrawableHeight);
                    float barBgStartY = (((float) this.mHeight) - barBgHeight) / 2.0f;
                    float barStartY = barBgStartY + (((barBgHeight - barHeight) / 2.0f) * (1.0f - this.mProgress));
                    float barX = ((float) this.mWidth) * 0.95f;
                    this.mScrollbarPaint.setAlpha(100);
                    canvas.drawLine(barX, barBgStartY, barX, barBgStartY + barBgHeight, this.mScrollbarPaint);
                    this.mScrollbarPaint.setAlpha(255);
                    canvas.drawLine(barX, barStartY, barX, barStartY + barHeight, this.mScrollbarPaint);
                    return;
                default:
                    return;
            }
        }
    }

    private float dp2px(float dp) {
        return TypedValue.applyDimension(1, dp, Resources.getSystem().getDisplayMetrics());
    }

    public void setEnablePanoramaMode(boolean enable) {
        this.mEnablePanoramaMode = enable;
    }

    public boolean isPanoramaModeEnabled() {
        return this.mEnablePanoramaMode;
    }

    public void setInvertScrollDirection(boolean invert) {
        if (this.mInvertScrollDirection != invert) {
            this.mInvertScrollDirection = invert;
        }
    }

    public boolean isInvertScrollDirection() {
        return this.mInvertScrollDirection;
    }

    public void setEnableScrollbar(boolean enable) {
        if (this.mEnableScrollbar != enable) {
            this.mEnableScrollbar = enable;
            if (this.mEnableScrollbar) {
                initScrollbarPaint();
            } else {
                this.mScrollbarPaint = null;
            }
        }
    }

    public boolean isScrollbarEnabled() {
        return this.mEnableScrollbar;
    }

    public byte getOrientation() {
        return this.mOrientation;
    }

    public void setScaleType(ImageView.ScaleType scaleType) {
    }

    public void setOnPanoramaScrollListener(OnPanoramaScrollListener listener) {
        this.mOnPanoramaScrollListener = listener;
    }
}
