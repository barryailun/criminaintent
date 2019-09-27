package com.taihao.criminaintent.ui.crime.activity;

import androidx.fragment.app.Fragment;

import com.taihao.criminaintent.base.SingleFragmentActivity;
import com.taihao.criminaintent.ui.crime.fragment.CrimeListFragment;

/**
 * @author chenwei
 * @date 2019/9/23
 * description：
 */
public class CrimeListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
