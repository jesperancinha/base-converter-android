package org.jesperancinha.universitybaseconverter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import org.jesperancinha.universitybaseconverter.R;

public class UniversityBaseBaseToDecActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {
    private static final int SWIPE_MIN_DISTANCE = 50;
    private static final int SWIPE_MAX_OFF_PATH = 200;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_university_base_base_to_dec);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.university_base_base_to_dec, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float v, float v2) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float v, float velocityX) {
        try {
            Toast t = Toast.makeText(UniversityBaseBaseToDecActivity.this, "Gesture detected", Toast.LENGTH_SHORT);
            t.show();
            float diffAbs = Math.abs(e1.getY() - e2.getY());
            float diff = e1.getX() - e2.getX();

            if (diffAbs > SWIPE_MAX_OFF_PATH)
                return false;

            // Left swipe
            if (diff > SWIPE_MIN_DISTANCE
                    && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                UniversityBaseBaseToDecActivity.this.onLeftSwipe();
            }
            // Right swipe
            else if (-diff > SWIPE_MIN_DISTANCE
                    && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                UniversityBaseBaseToDecActivity.this.onRightSwipe();
            }
        } catch (Exception e) {
            Log.e("Home", "Error on gestures");
        }
        return false;
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_university_base_base_to_dec, container, false);
            return rootView;
        }
    }

    private void onLeftSwipe() {
        Toast t = Toast.makeText(UniversityBaseBaseToDecActivity.this, "Left swipe", Toast.LENGTH_LONG);
        t.show();
        Intent go = new Intent(UniversityBaseBaseToDecActivity.this, UniversityBaseConverterActivity.class);
        finish();
        startActivity(go);
    }

    private void onRightSwipe() {
        Toast t = Toast.makeText(UniversityBaseBaseToDecActivity.this, "Right swipe", Toast.LENGTH_LONG);
        t.show();
        Intent go = new Intent(UniversityBaseBaseToDecActivity.this, UniversityBaseDecToBaseActivity.class);
        finish();
        startActivity(go);
    }

}
