package com.example.lastiti.recyclerViewFragment;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.lastiti.R;
import com.example.lastiti.database.note.NoteViewModel;
import com.example.lastiti.databinding.FragmentRecyclerviewBinding;

import java.io.File;


public class recyclerviewFragment extends Fragment {
    String filename = "auth";
    private FragmentRecyclerviewBinding binding;
    NoteViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRecyclerviewBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(NoteViewModel.class);
        setRecyclerViewLayoutManager();
        return binding.getRoot();
    }

    public void setRecyclerViewLayoutManager() {
        GridLayoutManager mLayoutManager = new GridLayoutManager(requireContext(), 2);
        final MyListAdapterLinear adapter = new MyListAdapterLinear(new MyListAdapterLinear.NoteDiff(), viewModel);
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setLayoutManager(mLayoutManager);
        viewModel.getmAllNotes().observe(getViewLifecycleOwner(), adapter::submitList);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.logout) {
            File file = new File(requireContext().getFilesDir(), filename);
            if (file.delete()) {
                Log.i("MainActivity", " you are Logged out");
                Navigation.findNavController(requireView()).navigateUp();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}