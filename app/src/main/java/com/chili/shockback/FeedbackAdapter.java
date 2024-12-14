package com.chili.shockback;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.cardview.widget.CardView;

import java.util.ArrayList;

public class FeedbackAdapter extends RecyclerView.Adapter<FeedbackAdapter.FeedbackViewHolder> {

    private final ArrayList<String> feedbacks;
    private final Context context;

    public FeedbackAdapter(ArrayList<String> feedbacks, Context context) {
        this.feedbacks = feedbacks;
        this.context = context;
    }

    @NonNull
    @Override
    public FeedbackViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_feedback, parent, false);
        return new FeedbackViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedbackViewHolder holder, int position) {
        String feedback = feedbacks.get(position);
        holder.feedbackText.setText(feedback);
    }

    @Override
    public int getItemCount() {
        return feedbacks.size();
    }

    public static class FeedbackViewHolder extends RecyclerView.ViewHolder {
        TextView feedbackText;
        CardView cardView;

        public FeedbackViewHolder(@NonNull View itemView) {
            super(itemView);
            feedbackText = itemView.findViewById(R.id.feedbackText);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
