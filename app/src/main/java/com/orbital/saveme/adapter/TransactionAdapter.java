package com.orbital.saveme.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.orbital.saveme.R;
import com.orbital.saveme.model.Transaction;

import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {
    private final Context mContext;
    private final List<Transaction> mTransaction;

    public TransactionAdapter(Context mContext, List<Transaction> mTransaction) {
        this.mTransaction = mTransaction;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.expenditure_item, parent,
                false);
        return new TransactionAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        final Transaction transaction = mTransaction.get(position);

        if (transaction.getTransactionType().equals("EXPENDITURE")) {
            String text = "-$" + transaction.getAmount();
            viewHolder.tvAmount.setText(text);
            viewHolder.tvAmount.setTextColor(Color.RED);
        } else {
            String text = "+$" + transaction.getAmount();
            viewHolder.tvAmount.setText(text);
            viewHolder.tvAmount.setTextColor(Color.BLUE);
        }

        viewHolder.tvTransaction.setText(transaction.getTransactionName());
        viewHolder.tvTransaction.setTextColor(Color.DKGRAY);

        viewHolder.tvDate.setText(transaction.getDate().toString().substring(0, 10)
                + transaction.getDate().toString().substring(29, 34)
                + transaction.getDate().toString().substring(10, 16));
    }

    @Override
    public int getItemCount() {
        return mTransaction.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final TextView tvTransaction;
        final TextView tvAmount;
        final TextView tvDate;

        ViewHolder(View itemView) {
            super(itemView);

            tvTransaction = itemView.findViewById(R.id.tvExpenditure);
            tvAmount = itemView.findViewById(R.id.tvAmount);
            tvDate = itemView.findViewById(R.id.tvDate);
        }
    }
}
