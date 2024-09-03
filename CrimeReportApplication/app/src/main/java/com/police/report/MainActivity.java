package com.police.report;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.police.report.fragment.CrimeFragment;
import com.police.report.model.Crime;
import com.police.report.viewholders.CrimeHolder;

import java.util.UUID;

public class MainActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        UUID crimeId = (UUID) getIntent()
                .getSerializableExtra(CrimeHolder.EXTRA_CRIME_ID);
        return CrimeFragment.newInstance(crimeId);
    }
}