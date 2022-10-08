package com.example.lastiti.recyclerViewFragment;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lastiti.R;
import com.example.lastiti.database.note.Note;
import com.example.lastiti.database.note.NoteViewModel;
import com.example.lastiti.databinding.ListItemLinearBinding;


public class MyListAdapterLinear extends ListAdapter<Note, MyListAdapterLinear.ViewHolder> {
    private final NoteViewModel viewModel;

    // RecyclerView recyclerView;
    public MyListAdapterLinear(DiffUtil.ItemCallback<Note> diffCallback, NoteViewModel viewModel) {
        super(diffCallback);
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ListItemLinearBinding binding = ListItemLinearBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(binding);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Note note = getItem(position);
        holder.binding.selected.setOnClickListener(view -> {
            viewModel.selectItem(note);
            Navigation.findNavController(view).navigate(R.id.action_viewPagerFragment_to_dataFragment);
        });
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