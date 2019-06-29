package com.orbital.saveme.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.orbital.saveme.R;
import com.orbital.saveme.model.Expenditure;

import org.w3c.dom.Text;

import java.util.List;

public class ExpenditureAdapter extends RecyclerView.Adapter<ExpenditureAdapter.ViewHolder> {

    private final Context mContext;
    private final List<Expenditure> mExpenditure;

    public ExpenditureAdapter(Context mContext, List<Expenditure> mExpenditure) {
        this.mExpenditure = mExpenditure;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.expenditure_item, parent,
                false);
        return new ExpenditureAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        final Expenditure expenditure = mExpenditure.get(position);
        viewHolder.tvAmount.setText(String.valueOf(expenditure.getAmount()));
        viewHolder.tvExpenditure.setText(expenditure.getExpenditureType());
    }

    @Override
    public int getItemCount() {
        return mExpenditure.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        final TextView tvExpenditure;
        final TextView tvAmount;

        ViewHolder(View itemView) {
            super(itemView);

            tvExpenditure = itemView.findViewById(R.id.tvExpenditure);
            tvAmount = itemView.findViewById(R.id.tvAmount);
        }
    }
}
