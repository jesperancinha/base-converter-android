package nl.joaofilipesabinoesperancinha.basicunitconverter

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import nl.joaofilipesabinoesperancinha.basicunitconverter.UniversityBaseBaseToDecActivity
import kotlin.math.abs

class UniversityBaseDecToBaseActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_university_base_dec_to_base)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, PlaceholderFragment())
                .commit()
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.

        menuInflater.inflate(R.menu.university_base_dec_to_base, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId
        if (id == R.id.action_settings) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    class PlaceholderFragment : Fragment() {
        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            val rootView =
                inflater.inflate(R.layout.fragment_university_base_dec_to_base, container, false)
            return rootView
        }
    }

    override fun onFling(
        e1: MotionEvent?,
        e2: MotionEvent,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        try {
            val t = Toast.makeText(
                this@UniversityBaseDecToBaseActivity,
                "Gesture detected",
                Toast.LENGTH_SHORT
            )
            t.show()
            val diffAbs = abs((requireNotNull(e1).y - e2.y).toDouble()).toFloat()
            val diff = e1.x - e2.x

            if (diffAbs > SWIPE_MAX_OFF_PATH) return false

            // Left swipe
            if (diff > SWIPE_MIN_DISTANCE
                && abs(velocityX.toDouble()) > SWIPE_THRESHOLD_VELOCITY
            ) {
                this@UniversityBaseDecToBaseActivity.onLeftSwipe()
            } else if (-diff > SWIPE_MIN_DISTANCE
                && abs(velocityX.toDouble()) > SWIPE_THRESHOLD_VELOCITY
            ) {
                this@UniversityBaseDecToBaseActivity.onRightSwipe()
            }
        } catch (e: Exception) {
            Log.e("Home", "Error on gestures")
        }
        return false
    }

    private fun onLeftSwipe() {
        val t =
            Toast.makeText(this@UniversityBaseDecToBaseActivity, "Left swipe", Toast.LENGTH_LONG)
        t.show()
        val go = Intent(
            this@UniversityBaseDecToBaseActivity,
            UniversityBaseBaseToDecActivity::class.java
        )
        finish()
        startActivity(go)
    }

    private fun onRightSwipe() {
        val t =
            Toast.makeText(this@UniversityBaseDecToBaseActivity, "Right swipe", Toast.LENGTH_LONG)
        t.show()
        val go = Intent(
            this@UniversityBaseDecToBaseActivity,
            UniversityBaseBaseToDecActivity::class.java
        )
        finish()
        startActivity(go)
    }

    companion object {
        private const val SWIPE_MIN_DISTANCE = 50
        private const val SWIPE_MAX_OFF_PATH = 200
        private const val SWIPE_THRESHOLD_VELOCITY = 200
    }
}
