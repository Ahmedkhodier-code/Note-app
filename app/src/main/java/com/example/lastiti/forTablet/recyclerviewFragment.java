package com.example.lastiti.forTablet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lastiti.database.NoteViewModel;
import com.example.lastiti.databinding.FragmentRecyclerviewBinding;

public class recyclerviewFragment extends Fragment {
    private FragmentRecyclerviewBinding binding;
    protected RecyclerView.LayoutManager mLayoutManager;
    NoteViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRecyclerviewBinding.inflate(inflater, container, false);
        setRecyclerViewLayoutManager();
//        binding.fab.setOnClickListener(view -> {
//            viewModel.setFlagDelete(true);
//            viewModel.BTNPressed();
//        });
        return binding.getRoot();
    }

    public void setRecyclerViewLayoutManager() {
        viewModel = new ViewModelProvider(requireActivity()).get(NoteViewModel.class);
        mLayoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        final MyListAdapter adapter = new MyListAdapter(new MyListAdapter.NoteDiff(), viewModel);
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setLayoutManager(mLayoutManager);
        viewModel.getmAllNotes().observe(getViewLifecycleOwner(), adapter::submitList);
    }
}