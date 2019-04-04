package edu.miracostacollege.cs134.touchgestures;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

// Step 1: implement GestureDetectors

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener,
GestureDetector.OnDoubleTapListener {

    private ScrollView gesturesScrollView;

    private TextView gesturesLogTextView;
    private TextView singleTapTextView;
    private TextView doubleTapTextView;
    private TextView longPressTextView;
    private TextView scrollTextView;
    private TextView flingTextView;

    private GestureDetector mGestureDetector;

    private int singleTaps = 0, doubleTaps = 0, longPresses = 0, scrolls = 0, flings = 0;

    // Step 3: Override dispatchTouchEvent
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        super.dispatchTouchEvent(ev);
        return mGestureDetector.onTouchEvent(ev);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Link views
        gesturesScrollView = findViewById(R.id.gesturesScrollView);
        gesturesLogTextView = findViewById(R.id.gesturesLogTextView);
        singleTapTextView = findViewById(R.id.singleTapTextView);
        doubleTapTextView = findViewById(R.id.doubleTapTextView);
        longPressTextView = findViewById(R.id.longPressTextView);
        scrollTextView = findViewById(R.id.scrollTextView);
        flingTextView = findViewById(R.id.flingTextView);

        mGestureDetector = new GestureDetector(gesturesScrollView.getContext(), this);

    }

    // Step 2: Dipatch the events to all children in container

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        gesturesLogTextView.append("onSingleTapConfirmed\n");
        singleTapTextView.setText(String.valueOf(++singleTaps));
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        gesturesLogTextView.append("onDoubleTap confirmed\n");
        doubleTapTextView.setText(String.valueOf(++doubleTaps));
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        gesturesLogTextView.append("onDoubleTap event\n");
        return true;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        gesturesLogTextView.append("onDown touch event\n");
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        gesturesLogTextView.append("onShowPress touch event\n");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        gesturesLogTextView.append("onSingleTapUp touch event\n");
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        gesturesLogTextView.append("onScroll: distance X is " + distanceX +
                " distanceY is " + distanceY + "\n");
        scrollTextView.setText(String.valueOf(++scrolls));
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        gesturesLogTextView.append("onLongPress touch event\n");
        longPressTextView.setText(String.valueOf(++longPresses));
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        gesturesLogTextView.append("onFling: velocity X is " + velocityX +
                " velocityY is " + velocityY + "\n");
        flingTextView.setText(String.valueOf(++flings));
        // If horizontal swipe
        if (Math.abs(velocityX) > Math.abs(velocityY)) {
            // if right swipe (like)
            if (velocityX > 0) {
                gesturesLogTextView.append("Right Swipe (Like) detected\n");
                if (velocityX > 15000) {
                    gesturesLogTextView.append("Super Like!\n");
                }
            }
            // if left swipe (dislike)
            else {
                gesturesLogTextView.append("Left Swipe (Dislike) detected\n");
            }
        }
        // If vertical swipe
        else {
            // if down swipe
            if (velocityY > 0) {
                gesturesLogTextView.append("Down Swipe detected\n");
            }
            // if up swipe
            else {
                gesturesLogTextView.append("Up Swipe detected\n");
            }
        }
        return true;
    }

    public void clearAll(View v) {
        singleTaps = 0;
        doubleTaps = 0;
        longPresses = 0;
        scrolls = 0;
        flings = 0;

        gesturesLogTextView.setText("");
        singleTapTextView.setText(getString(R.string.zero));
        doubleTapTextView.setText(getString(R.string.zero));
        longPressTextView.setText(getString(R.string.zero));
        scrollTextView.setText(getString(R.string.zero));
        flingTextView.setText(getString(R.string.zero));

    }
}
