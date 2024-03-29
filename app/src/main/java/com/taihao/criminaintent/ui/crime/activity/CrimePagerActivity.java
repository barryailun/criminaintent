package com.taihao.criminaintent.ui.crime.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.taihao.criminaintent.R;
import com.taihao.criminaintent.ui.crime.bean.Crime;
import com.taihao.criminaintent.ui.crime.bean.CrimeLab;
import com.taihao.criminaintent.ui.crime.fragment.CrimeFragment;
import com.taihao.criminaintent.ui.crime.fragment.CrimeListFragment;

import java.util.List;
import java.util.UUID;

public class CrimePagerActivity extends AppCompatActivity {

    private static final String EXTRA_CRIME_ID =
            "com.taihao.criminalintent.crime_id";

    private ViewPager mViewPager;
    private List<Crime> mCrimes;
    private Button jumpFirstBtn;
    private Button jumpLastBtn;


    public static Intent newIntent(Context packageContext, UUID crimeId) {
        Intent intent = new Intent(packageContext, CrimePagerActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_pager);
        UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);
        mViewPager = findViewById(R.id.activity_crime_pager);
        mCrimes  = CrimeLab.get(this).getCrimes();
        FragmentManager fm = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                Crime crime = mCrimes.get(position);
                return CrimeFragment.newInstance(crime.getmId());
            }

            @Override
            public int getCount() {
                return mCrimes.size();
            }
        });
        for (int i = 0; i < mCrimes.size(); i++) {
            if (mCrimes.get(i).getmId().equals(crimeId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
        jumpFirstBtn = findViewById(R.id.jump_to_first);
        jumpLastBtn = findViewById(R.id.jump_to_last);
        jumpFirstBtn.setOnClickListener(v -> {
            mViewPager.setCurrentItem(0);
        });
        jumpLastBtn.setOnClickListener(v -> {
            mViewPager.setCurrentItem(mCrimes.size()-1);
        });
    }
}
