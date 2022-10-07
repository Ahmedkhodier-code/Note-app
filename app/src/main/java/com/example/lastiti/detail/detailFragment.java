package com.example.lastiti.detail;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.lastiti.R;
import com.example.lastiti.database.Note;
import com.example.lastiti.database.NoteViewModel;
import com.example.lastiti.databinding.FragmentDetailBinding;


public class detailFragment extends Fragment {
    private Note note;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentDetailBinding binding = FragmentDetailBinding.inflate(inflater, container, false);
        NoteViewModel viewModel = new ViewModelProvider(requireActivity()).get(NoteViewModel.class);
        viewModel.getSelectedItem().observe(requireActivity(), item -> {
            binding.constraintLayout.setVisibility(View.VISIBLE);
            note = item;
            binding.setNote(note);
        });
        viewModel.getFlag().observe(requireActivity(), newFlag -> {
            if (newFlag) {
                viewModel.setFlagDelete(true);
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_detailFragment_to_newDataFragment2);
            }
        });
        binding.delete.setOnClickListener(view -> {
            viewModel.delete(note);
            if (Boolean.TRUE.equals(viewModel.getFlag().getValue()) || Boolean.TRUE.equals(viewModel.getFlagDelete().getValue())) {
                binding.constraintLayout.setVisibility(View.INVISIBLE);
                System.out.println("binding.constraintLayout.setVisibility(View.VISIBLE);\n");
                viewModel.setFlagDelete(false);
            } else {
                Navigation.findNavController(view).navigateUp();
            }
        });
        binding.save.setOnClickListener(view -> {
            viewModel.update(note, binding.getNote());
            Navigation.findNavController(view).navigateUp();

        });
        binding.setNote(note);
        return binding.getRoot();
    }
}