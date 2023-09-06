package rconnect.retvens.technologies.Panorma.sources.com.gjiazhe.panoramaimageview;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import java.util.Iterator;
import java.util.LinkedList;

public class GyroscopeObserver implements SensorEventListener {
    private static final float NS2S = 1.0E-9f;
    private long mLastTimestamp;
    private double mMaxRotateRadian = 0.3490658503988659d;
    private double mRotateRadianX;
    private double mRotateRadianY;
    private SensorManager mSensorManager;
    private LinkedList<PanoramaImageView> mViews = new LinkedList<>();

    public void register(Context context) {
        if (this.mSensorManager == null) {
            this.mSensorManager = (SensorManager) context.getSystemService("sensor");
        }
        this.mSensorManager.registerListener(this, this.mSensorManager.getDefaultSensor(4), 0);
        this.mLastTimestamp = 0;
        this.mRotateRadianX = 0.0d;
        this.mRotateRadianY = 0.0d;
    }

    public void unregister() {
        if (this.mSensorManager != null) {
            this.mSensorManager.unregisterListener(this);
            this.mSensorManager = null;
        }
    }

    /* access modifiers changed from: package-private */
    public void addPanoramaImageView(PanoramaImageView view) {
        if (view != null && !this.mViews.contains(view)) {
            this.mViews.addFirst(view);
        }
    }

    public void onSensorChanged(SensorEvent event) {
        if (this.mLastTimestamp == 0) {
            this.mLastTimestamp = event.timestamp;
            return;
        }
        float rotateX = Math.abs(event.values[0]);
        float rotateY = Math.abs(event.values[1]);
        float rotateZ = Math.abs(event.values[2]);
        if (rotateY > rotateX + rotateZ) {
            this.mRotateRadianY += (double) (event.values[1] * ((float) (event.timestamp - this.mLastTimestamp)) * NS2S);
            if (this.mRotateRadianY > this.mMaxRotateRadian) {
                this.mRotateRadianY = this.mMaxRotateRadian;
            } else if (this.mRotateRadianY < (-this.mMaxRotateRadian)) {
                this.mRotateRadianY = -this.mMaxRotateRadian;
            } else {
                Iterator it = this.mViews.iterator();
                while (it.hasNext()) {
                    PanoramaImageView view = (PanoramaImageView) it.next();
                    if (view != null && view.getOrientation() == 0) {
                        view.updateProgress((float) (this.mRotateRadianY / this.mMaxRotateRadian));
                    }
                }
            }
        } else if (rotateX > rotateY + rotateZ) {
            this.mRotateRadianX += (double) (event.values[0] * ((float) (event.timestamp - this.mLastTimestamp)) * NS2S);
            if (this.mRotateRadianX > this.mMaxRotateRadian) {
                this.mRotateRadianX = this.mMaxRotateRadian;
            } else if (this.mRotateRadianX < (-this.mMaxRotateRadian)) {
                this.mRotateRadianX = -this.mMaxRotateRadian;
            } else {
                Iterator it2 = this.mViews.iterator();
                while (it2.hasNext()) {
                    PanoramaImageView view2 = (PanoramaImageView) it2.next();
                    if (view2 != null && view2.getOrientation() == 1) {
                        view2.updateProgress((float) (this.mRotateRadianX / this.mMaxRotateRadian));
                    }
                }
            }
        }
        this.mLastTimestamp = event.timestamp;
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    public void setMaxRotateRadian(double maxRotateRadian) {
        if (maxRotateRadian <= 0.0d || maxRotateRadian > 1.5707963267948966d) {
            throw new IllegalArgumentException("The maxRotateRadian must be between (0, π/2].");
        }
        this.mMaxRotateRadian = maxRotateRadian;
    }
}
