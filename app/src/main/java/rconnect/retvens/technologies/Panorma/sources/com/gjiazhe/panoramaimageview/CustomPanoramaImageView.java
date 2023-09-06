package rconnect.retvens.technologies.Panorma.sources.com.gjiazhe.panoramaimageview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;

public class CustomPanoramaImageView extends PanoramaImageView {
    private GestureDetector gestureDetector;

    public CustomPanoramaImageView(Context context) {
        super(context);
        init(context);
    }

    public CustomPanoramaImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        gestureDetector = new GestureDetector(context, new MyGestureListener());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
}
