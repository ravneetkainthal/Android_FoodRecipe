package com.police.report.fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;

import com.police.report.MainActivity;
import com.police.report.R;
import com.police.report.lab.CrimeLab;
import com.police.report.model.Crime;
import com.police.report.viewholders.CrimeHolder;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class CrimeFragment extends Fragment {

    private Crime mCrime;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;

    public static final String ARG_CRIME_ID = "arg_crime_id";
    private static final String DIALOG_DATE = "DialogDate";
    public static final String REQUEST_DATE = "0";


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCrime = new Crime();
        UUID crimeId = (UUID)getArguments().getSerializable(ARG_CRIME_ID);
        mCrime = CrimeLab.get(getContext()).getCrime(crimeId);
    }

    public static CrimeFragment newInstance (UUID crimeId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_CRIME_ID, crimeId);
        CrimeFragment fragment = new CrimeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_crime, container, false);
        mTitleField = (EditText) v.findViewById(R.id.crime_title);
        mTitleField.setText(mCrime.getTitle());
        mTitleField.addTextChangedListener(textWatcher);
        mDateButton = (Button)v.findViewById(R.id.crime_date);
        mDateButton.setText(mCrime.getDate().toString());
        mDateButton.setOnClickListener(dateButtonClick);
        mSolvedCheckBox = (CheckBox)v.findViewById(R.id.crime_solved);
        mSolvedCheckBox.setChecked(mCrime.isSolved());
        mSolvedCheckBox.setOnCheckedChangeListener(compoundButtonListener);
        updateDate();
        return v;
    }

    private final View.OnClickListener dateButtonClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            FragmentManager manager = requireActivity()
                    .getSupportFragmentManager();
            DatePickerFragment dialog = DatePickerFragment
                    .newInstance(mCrime.getDate());
            manager.setFragmentResultListener(REQUEST_DATE, CrimeFragment.this, fragmentResultListener);
            dialog.show(manager, DIALOG_DATE);
        }
    };

    private final FragmentResultListener fragmentResultListener = new FragmentResultListener() {
        @Override
        public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
            if (REQUEST_DATE.equalsIgnoreCase(requestKey)){
                    Date date = (Date) result
                            .getSerializable(DatePickerFragment.EXTRA_DATE);
                    mCrime.setDate(date);
                    updateDate();
            }

        }
    };

    private final OnCheckedChangeListener compoundButtonListener = new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            mCrime.setSolved(isChecked);
        }
    };

    private final TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
             mCrime.setTitle(s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private void updateDate() {
        mDateButton.setText(mCrime.getDate().toString());
    }
}