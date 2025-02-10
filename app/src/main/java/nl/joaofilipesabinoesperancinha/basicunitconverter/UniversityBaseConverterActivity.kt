package nl.joaofilipesabinoesperancinha.basicunitconverter

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import nl.joaofilipesabinoesperancinha.basicunitconverter.tools.Converter
import java.util.Locale
import java.lang.String
import kotlin.Boolean
import kotlin.CharSequence
import kotlin.Exception
import kotlin.Int

class UniversityBaseConverterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        counter = 1
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_university_base_converter)


        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)

        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById<View>(R.id.pager) as ViewPager?
        mViewPager?.setAdapter(mSectionsPagerAdapter)
        thisView = this
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.university_base_converter, menu)
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
     * A [FragmentPagerAdapter] that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    inner class SectionsPagerAdapter(fm: FragmentManager) :
        FragmentPagerAdapter(fm) {
        override fun getItem(position: Int): Fragment {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1)
        }

        override fun getCount(): Int = 3

        override fun getPageTitle(position: Int): CharSequence? {
            val l = Locale.getDefault()
            when (position) {
                0 -> return getString(R.string.title_section1).uppercase(l)
                1 -> return getString(R.string.title_section2).uppercase(l)
                2 -> return getString(R.string.title_section3).uppercase(l)
            }
            return null
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    class PlaceholderFragment : Fragment {
        var sectionNumber: Int = 0

        constructor() {
            this.sectionNumber = counter++
        }


        constructor(sectionNumber: Int) {
            this.sectionNumber = sectionNumber
        }

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            var rootView: View? = null
            when (sectionNumber) {
                1 -> {
                    rootView = inflater.inflate(
                        R.layout.fragment_university_base_converter,
                        container,
                        false
                    )
                    btnGotoDecToBase = rootView.findViewById<View>(R.id.btnStartDecToBase) as Button
                    btnGotoBaseToDec = rootView.findViewById<View>(R.id.btnStartBaseToDec) as Button
                    btnExit = rootView.findViewById<View>(R.id.btnExit) as Button
                    btnGotoDecToBase!!.setOnClickListener {
                        requireNotNull(mViewPager).setCurrentItem(
                            MENU_DEC_TO_BASE
                        )
                    }
                    btnGotoBaseToDec!!.setOnClickListener {
                        requireNotNull(mViewPager).setCurrentItem(
                            MENU_BASE_TO_DEC
                        )
                    }
                    btnExit!!.setOnClickListener { thisView?.finish() }
                }

                2 -> {
                    rootView = inflater.inflate(
                        R.layout.fragment_university_base_dec_to_base,
                        container,
                        false
                    )
                    btnMainMenu1 = rootView.findViewById<View>(R.id.btnMain1) as Button
                    btnMainMenu1!!.setOnClickListener {
                        mViewPager?.setCurrentItem(
                            MENU_MAIN
                        )
                    }
                    editDec = rootView.findViewById<View>(R.id.editDec) as EditText
                    editBaseDec = rootView.findViewById<View>(R.id.editBaseDec) as EditText
                    btnResultBase =
                        rootView.findViewById<View>(R.id.btnConvertDecimalToBase) as Button
                    btnResultBase!!.setOnClickListener {
                        try {
                            textViewBase?.text = Converter.getDecToBase(
                                editDec?.getText()
                                    .toString().toInt(),
                                editBaseDec?.getText()
                                    .toString().toInt()
                            )
                        } catch (e: Exception) {
                        }
                    }

                    textViewBase = rootView.findViewById<View>(R.id.textResultBase) as TextView
                }

                3 -> {
                    rootView = inflater.inflate(
                        R.layout.fragment_university_base_base_to_dec,
                        container,
                        false
                    )
                    btnMainMenu2 = rootView.findViewById<View>(R.id.btnMain2) as Button
                    btnMainMenu2!!.setOnClickListener {
                        mViewPager?.setCurrentItem(
                            MENU_MAIN
                        )
                    }

                    editNumber = rootView.findViewById<View>(R.id.editNumber) as EditText
                    editBaseNumber = rootView.findViewById<View>(R.id.editBaseNumber) as EditText

                    btnResultDec =
                        rootView.findViewById<View>(R.id.btnConvertBaseToDecimal) as Button
                    btnResultDec!!.setOnClickListener {
                        try {
                            textViewDec?.text = String.valueOf(
                                Converter.getBaseToDeC(
                                    (editNumber?.getText()
                                        .toString()
                                        .uppercase(Locale.getDefault())),
                                    editBaseNumber?.getText()
                                        .toString().toInt()
                                )
                            )
                        } catch (_: Exception) {
                        }
                    }

                    textViewDec = rootView.findViewById<View>(R.id.textResultDec) as TextView
                }
            }

            return rootView
        }

        companion object {
            /**
             * The fragment argument representing the section number for this
             * fragment.
             */
            private const val ARG_SECTION_NUMBER = "section_number"

            /**
             * Returns a new instance of this fragment for the given section
             * number.
             */
            fun newInstance(sectionNumber: Int): PlaceholderFragment {
                val fragment = PlaceholderFragment(sectionNumber)
                val args: Bundle = Bundle()
                args.putInt(ARG_SECTION_NUMBER, sectionNumber)
                fragment.arguments = args
                return fragment
            }
        }
    }

    private fun onLeftSwipe() {
        val t: Toast =
            Toast.makeText(this@UniversityBaseConverterActivity, "Left swipe", Toast.LENGTH_LONG)
        t.show()
        val go: Intent = Intent(
            this@UniversityBaseConverterActivity,
            UniversityBaseDecToBaseActivity::class.java
        )
        finish()
        startActivity(go)
    }

    private fun onRightSwipe() {
        val t: Toast =
            Toast.makeText(this@UniversityBaseConverterActivity, "Right swipe", Toast.LENGTH_LONG)
        t.show()
        val go: Intent = Intent(
            this@UniversityBaseConverterActivity,
            UniversityBaseDecToBaseActivity::class.java
        )
        finish()
        startActivity(go)
    }

    companion object {
        private const val SWIPE_MIN_DISTANCE = 50
        private const val SWIPE_MAX_OFF_PATH = 200
        private const val SWIPE_THRESHOLD_VELOCITY = 200
        const val MENU_MAIN: Int = 0
        const val MENU_DEC_TO_BASE: Int = 1
        const val MENU_BASE_TO_DEC: Int = 2

        var mSectionsPagerAdapter: SectionsPagerAdapter? = null

        /**
         * The [ViewPager] that will host the section contents.
         */
        var mViewPager: ViewPager? = null


        var thisView: UniversityBaseConverterActivity? = null
        private var btnMainMenu1: Button? = null
        private var btnMainMenu2: Button? = null
        private var btnGotoDecToBase: Button? = null
        private var btnGotoBaseToDec: Button? = null
        private var btnResultDec: Button? = null
        private var btnResultBase: Button? = null
        private var btnExit: Button? = null

        private var editDec: EditText? = null
        private var editNumber: EditText? = null
        private var editBaseDec: EditText? = null
        private var editBaseNumber: EditText? = null

        private var textViewDec: TextView? = null
        private var textViewBase: TextView? = null

        private var counter = 1
    }
}
