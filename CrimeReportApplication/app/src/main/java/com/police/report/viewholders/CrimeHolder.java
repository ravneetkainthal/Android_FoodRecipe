package com.police.report.viewholders;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.police.report.MainActivity;
import com.police.report.R;
import com.police.report.model.Crime;

public class CrimeHolder extends RecyclerView.ViewHolder{

    private Crime mCrime;

    private TextView mTitleTextView;
    private TextView mDateTextView;
    private CheckBox mSolvedCheckBox;
    private Context context;

    public static final String EXTRA_CRIME_ID = "crime_id";

    public CrimeHolder(View itemView, Context context) {
        super(itemView);
        this.context = context;
        mTitleTextView = (TextView)
                itemView.findViewById(R.id.list_item_crime_title_text_view);
        mDateTextView = (TextView)
                itemView.findViewById(R.id.list_item_crime_date_text_view);
        mSolvedCheckBox = (CheckBox)
                itemView.findViewById(R.id.list_item_crime_solved_check_box);
        itemView.setOnClickListener(itemClickListener);
    }

    public void bindCrime(Crime crime) {
        mCrime = crime;
        mTitleTextView.setText(mCrime.getTitle());
        mDateTextView.setText(mCrime.getDate().toString());
        mSolvedCheckBox.setChecked(mCrime.isSolved());
    }

    private final View.OnClickListener itemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, MainActivity.class);
            intent.putExtra(EXTRA_CRIME_ID, mCrime.getId());
            context.startActivity(intent);
        }
    };
}
