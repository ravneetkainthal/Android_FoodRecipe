package com.police.report;

import androidx.fragment.app.Fragment;
import com.police.report.fragment.CrimeListFragment;


public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }

}