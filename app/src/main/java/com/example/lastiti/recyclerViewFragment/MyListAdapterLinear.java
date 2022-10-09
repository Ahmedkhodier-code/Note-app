package com.example.lastiti.recyclerViewFragment;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lastiti.R;
import com.example.lastiti.database.note.Note;
import com.example.lastiti.database.note.NoteViewModel;
import com.example.lastiti.databinding.ListItemFooterBinding;
import com.example.lastiti.databinding.ListItemLinearBinding;


public class MyListAdapterLinear extends ListAdapter<Note, MyListAdapterLinear.ViewHolder> {
    private final NoteViewModel viewModel;

    public MyListAdapterLinear(DiffUtil.ItemCallback<Note> diffCallback, NoteViewModel viewModel) {
        super(diffCallback);
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        if (viewType == VIEW_TYPES.Footer) {
            ListItemFooterBinding binding = ListItemFooterBinding.inflate(layoutInflater, parent, false);
            return new ViewHolder(binding);

        } else {
            ListItemLinearBinding binding = ListItemLinearBinding.inflate(layoutInflater, parent, false);
            return new ViewHolder(binding);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Note note = getItem(position);
        int viewType = getItemViewType(position);
        switch (viewType) {
            case VIEW_TYPES.Footer: // handle row footer
                holder.FooterBinding.selected.setOnClickListener(view -> {
                    Navigation.findNavController(view).navigate(R.id.action_viewPagerFragment_to_newDataFragment);
                });
                break;
            case VIEW_TYPES.Normal: // handle row item
                holder.binding.selected.setOnClickListener(view -> {
                    viewModel.selectItem(note);
                    Navigation.findNavController(view).navigate(R.id.action_viewPagerFragment_to_dataFragment);
                });
                break;
        }

        holder.bind(note, position);
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
        ListItemFooterBinding FooterBinding;

        public ViewHolder(ListItemFooterBinding FooterBinding) {
            super(FooterBinding.getRoot());
            Log.i("ViewHolder", "ListItemFooterBinding");
            this.FooterBinding = FooterBinding;
        }

        public ViewHolder(ListItemLinearBinding binding) {
            super(binding.getRoot());
            Log.i("ViewHolder", "ListItemLinearBinding");
            this.binding = binding;
        }


        void bind(Note note, int position) {
            if (note.Footer) {
                switch (position % 4) {
                    case 0:
                        FooterBinding.selected.setCardBackgroundColor(ColorStateList.valueOf(Color.parseColor("#e1f5fe")));
                        break;
                    case 1:
                        FooterBinding.selected.setCardBackgroundColor(ColorStateList.valueOf(Color.parseColor("#fff8e1")));
                        break;
                    case 2:
                        FooterBinding.selected.setCardBackgroundColor(ColorStateList.valueOf(Color.parseColor("#fce4ec")));
                        break;
                    case 3:
                        FooterBinding.selected.setCardBackgroundColor(ColorStateList.valueOf(Color.parseColor("#eceff1")));
                        break;
                }
                FooterBinding.executePendingBindings();
            } else {
                binding.setNote(note);
                switch (position % 4) {
                    case 0:
                        binding.selected.setCardBackgroundColor(ColorStateList.valueOf(Color.parseColor("#e1f5fe")));
                        break;
                    case 1:
                        binding.selected.setCardBackgroundColor(ColorStateList.valueOf(Color.parseColor("#fff8e1")));
                        break;
                    case 2:
                        binding.selected.setCardBackgroundColor(ColorStateList.valueOf(Color.parseColor("#fce4ec")));
                        break;
                    case 3:
                        binding.selected.setCardBackgroundColor(ColorStateList.valueOf(Color.parseColor("#eceff1")));
                        break;
                }
                binding.executePendingBindings();
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        Log.i("position " + position, "is footer " + getItem(position).Footer);
        if (getItem(position).Footer)
            return VIEW_TYPES.Footer;
        else
            return VIEW_TYPES.Normal;
    }

    public static class VIEW_TYPES {
        public static final int Normal = 1;
        public static final int Footer = 2;
    }
}