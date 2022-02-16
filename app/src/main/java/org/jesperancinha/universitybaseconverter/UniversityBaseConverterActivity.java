package org.jesperancinha.universitybaseconverter;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import org.jesperancinha.universitybaseconverter.helpers.Converter;

import java.util.Locale;

public class UniversityBaseConverterActivity extends AppCompatActivity {
    private static final int SWIPE_MIN_DISTANCE = 50;
    private static final int SWIPE_MAX_OFF_PATH = 200;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
    public static final int MENU_MAIN = 0;
    public static final int MENU_DEC_TO_BASE = 1;
    public static final int MENU_BASE_TO_DEC = 2;

    static SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    static ViewPager mViewPager;


    static UniversityBaseConverterActivity thisView = null;
    private static Button btnMainMenu1;
    private static Button btnMainMenu2;
    private static Button btnGotoDecToBase;
    private static Button btnGotoBaseToDec;
    private static Button btnResultDec;
    private static Button btnResultBase;
    private static Button btnExit;

    private static EditText editDec;
    private static EditText editNumber;
    private static EditText editBaseDec;
    private static EditText editBaseNumber;

    private static TextView textViewDec;
    private static TextView textViewBase;

    private static int counter = 1;

    public UniversityBaseConverterActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        counter = 1;
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_university_base_converter);


        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        thisView = this;

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.university_base_converter, menu);
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


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
                case 2:
                    return getString(R.string.title_section3).toUpperCase(l);
            }
            return null;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public int sectionNumber = 0;

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {

            PlaceholderFragment fragment = new PlaceholderFragment(sectionNumber);
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
            this.sectionNumber = counter++;
        }


        public PlaceholderFragment(int sectionNumber) {
            this.sectionNumber = sectionNumber;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = null;
            switch (sectionNumber) {
                case 1:
                    rootView = inflater.inflate(R.layout.fragment_university_base_converter, container, false);
                    btnGotoDecToBase = (Button) rootView.findViewById(R.id.btnStartDecToBase);
                    btnGotoBaseToDec = (Button) rootView.findViewById(R.id.btnStartBaseToDec);
                    btnExit = (Button) rootView.findViewById(R.id.btnExit);
                    btnGotoDecToBase.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {


                            mViewPager.setCurrentItem(MENU_DEC_TO_BASE);


                        }
                    });
                    btnGotoBaseToDec.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {


                            mViewPager.setCurrentItem(MENU_BASE_TO_DEC);


                        }
                    });
                    btnExit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {


                            thisView.finish();


                        }
                    });
                    break;
                case 2:
                    rootView = inflater.inflate(R.layout.fragment_university_base_dec_to_base, container, false);
                    btnMainMenu1 = (Button) rootView.findViewById(R.id.btnMain1);
                    btnMainMenu1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mViewPager.setCurrentItem(MENU_MAIN);
                        }
                    });
                    editDec = (EditText) rootView.findViewById(R.id.editDec);
                    editBaseDec = (EditText) rootView.findViewById(R.id.editBaseDec);
                    btnResultBase = (Button) rootView.findViewById(R.id.btnConvertDecimalToBase);
                    btnResultBase.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            try {
                                textViewBase.setText(Converter.getDecToBase(Integer.parseInt(editDec.getText().toString()), Integer.parseInt(editBaseDec.getText().toString())));
                            } catch (Exception e) {

                            }
                        }

                    });

                    textViewBase = (TextView) rootView.findViewById(R.id.textResultBase);
                    break;
                case 3:
                    rootView = inflater.inflate(R.layout.fragment_university_base_base_to_dec, container, false);
                    btnMainMenu2 = (Button) rootView.findViewById(R.id.btnMain2);
                    btnMainMenu2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mViewPager.setCurrentItem(MENU_MAIN);
                        }
                    });

                    editNumber = (EditText) rootView.findViewById(R.id.editNumber);
                    editBaseNumber = (EditText) rootView.findViewById(R.id.editBaseNumber);

                    btnResultDec = (Button) rootView.findViewById(R.id.btnConvertBaseToDecimal);
                    btnResultDec.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            try {
                                textViewDec.setText(String.valueOf(Converter.getBaseToDeC((editNumber.getText().toString().toUpperCase()), Integer.parseInt(editBaseNumber.getText().toString()))));
                            } catch (Exception e) {

                            }
                        }
                    });

                    textViewDec = (TextView) rootView.findViewById(R.id.textResultDec);
                    break;
            }

            return rootView;
        }

    }

    private void onLeftSwipe() {
        Toast t = Toast.makeText(UniversityBaseConverterActivity.this, "Left swipe", Toast.LENGTH_LONG);
        t.show();
        Intent go = new Intent(UniversityBaseConverterActivity.this, UniversityBaseDecToBaseActivity.class);
        finish();
        startActivity(go);
    }

    private void onRightSwipe() {
        Toast t = Toast.makeText(UniversityBaseConverterActivity.this, "Right swipe", Toast.LENGTH_LONG);
        t.show();
        Intent go = new Intent(UniversityBaseConverterActivity.this, UniversityBaseDecToBaseActivity.class);
        finish();
        startActivity(go);
    }

}
