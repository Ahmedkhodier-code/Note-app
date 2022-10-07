package com.example.lastiti.forTablet;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lastiti.database.Note;
import com.example.lastiti.database.NoteViewModel;
import com.example.lastiti.databinding.ListItemLinearBinding;


public class MyListAdapter extends ListAdapter<Note, MyListAdapter.ViewHolder> {
    private final NoteViewModel viewModel;

    // RecyclerView recyclerView;
    public MyListAdapter(DiffUtil.ItemCallback<Note> diffCallback, NoteViewModel viewModel) {
        super(diffCallback);
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public MyListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ListItemLinearBinding binding = ListItemLinearBinding.inflate(layoutInflater, parent, false);
        return new MyListAdapter.ViewHolder(binding);
    }


    @Override
    public void onBindViewHolder(MyListAdapter.ViewHolder holder, int position) {
        Note note = getItem(position);
        holder.binding.selected.setOnClickListener(view -> viewModel.selectItem(note));
        holder.bind(note);
    }


    static class NoteDiff extends DiffUtil.ItemCallback<Note> {

        @Override
        public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem == newItem;
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getNote().equals(newItem.getNote());
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ListItemLinearBinding binding;

        public ViewHolder(ListItemLinearBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Note note) {
            binding.setNote(note);
            binding.executePendingBindings();
        }
    }
}