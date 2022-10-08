package com.example.lastiti.recyclerViewFragment;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
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
        View rowView;
        switch (viewType) {
            case VIEW_TYPES.Footer:
                rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_footer, parent, false);
                break;
            default:
                rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_linear, parent, false);
                break;
        }
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ListItemLinearBinding binding = ListItemLinearBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Note note = getItem(position);
        int viewType = getItemViewType(position);
        switch(viewType) {
            case VIEW_TYPES.Footer: // handle row footer
                break;
            case VIEW_TYPES.Normal: // handle row item
                break;
        }
        holder.binding.selected.setOnClickListener(view -> {
            viewModel.selectItem(note);
            Navigation.findNavController(view).navigate(R.id.action_viewPagerFragment_to_dataFragment);
        });
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

        public ViewHolder(ListItemLinearBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Note note, int num) {
            binding.setNote(note);
            Log.i("MyListAdapterLinear"," "+num % 3);
            switch (num % 3) {
                case 0:binding.selected.setCardBackgroundColor(ColorStateList.valueOf(Color.parseColor("#e1f5fe")));
                    break;
                case 1:binding.selected.setCardBackgroundColor(ColorStateList.valueOf(Color.parseColor("#fff8e1")));
                    break;
                case 2:binding.selected.setCardBackgroundColor(ColorStateList.valueOf(Color.parseColor("#fce4ec")));
                    break;
            }
            binding.executePendingBindings();
        }
    }

    @Override
    public int getItemViewType(int position) {
         if(getItem(position).Footer)
            return VIEW_TYPES.Footer;
        else
            return VIEW_TYPES.Normal;

    }

    public class VIEW_TYPES {
        public static final int Header = 1;
        public static final int Normal = 2;
        public static final int Footer = 3;
    }
}