package rconnect.retvens.technologies.Panorma.sources.com.gjiazhe.panoramaimageview;

import android.view.GestureDetector;
import android.view.MotionEvent;

public class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
    private static final int SWIPE_THRESHOLD = 100;
    private static final int SWIPE_VELOCITY_THRESHOLD = 100;

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        // Handle horizontal scrolling here
        // "distanceX" will give you the horizontal scroll distance
        // Update the image's position or scroll view accordingly
        return true;
    }
}
