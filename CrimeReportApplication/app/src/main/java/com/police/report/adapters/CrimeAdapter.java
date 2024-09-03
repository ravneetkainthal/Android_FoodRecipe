package com.police.report.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.police.report.R;
import com.police.report.model.Crime;
import com.police.report.viewholders.CrimeHolder;

import java.util.List;

public class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder> {

    private List<Crime> mCrimes;
    private Context context;

    public CrimeAdapter (Context context, List<Crime> crimes){
        this.mCrimes = crimes;
        this.context = context;
    }

    @NonNull
    @Override
    public CrimeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater
                .inflate(R.layout.list_tem_crime, parent, false);
        return new CrimeHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull CrimeHolder holder, int position) {
        Crime crime = mCrimes.get(position);
        holder.bindCrime(crime);
    }

    @Override
    public int getItemCount() {
        return mCrimes.size();
    }
}
