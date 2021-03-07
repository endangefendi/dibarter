package com.dibarter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dibarter.R;
import com.dibarter.model.SugestModel;

import java.util.List;

public class SuggestAdapter extends RecyclerView.Adapter<SuggestAdapter.ViewHolder> {
    private static final String TAG = "SuggestAdapter";
    private Context context;
    private List<SugestModel> list;
    private OnItemClickListener listener;

    public SuggestAdapter(Context context, OnItemClickListener listener, List<SugestModel> list) {
        this.context = context;
        this.listener = listener;
        this.list = list;
    }

    @NonNull
    @Override
    public SuggestAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_sugest, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull SuggestAdapter.ViewHolder holder, int position) {
        final SugestModel item = list.get(position);
        holder.tv_text.setText(item.getSuggestion());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) listener.onItemClicked(position, item);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_text;

        ViewHolder(View view) {
            super(view);
            tv_text = view.findViewById(R.id.judul);
        }
    }

    public interface OnItemClickListener {
        void onItemClicked(int position, SugestModel item);
    }

    public void addList(List<SugestModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }

}
