package com.example.lastiti.viewPager;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lastiti.R;
import com.example.lastiti.databinding.FragmentViewPagerBinding;
import com.example.lastiti.recyclerViewFragment.recyclerviewFragment;

public class ViewPagerFragment extends Fragment {
    FragmentViewPagerBinding binding;
    ViewPagerViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        viewModel = new ViewModelProvider(requireActivity()).get(ViewPagerViewModel.class);
        binding = FragmentViewPagerBinding.inflate(inflater, container, false);
        // setting up the adapter
//        (new ViewPagerAdapter(requireActivity().getSupportFragmentManager()))
        viewModel.setViewPagerAdapter(new ViewPagerAdapter(getChildFragmentManager()));
        // add the fragments
        viewModel.getViewPagerAdapter().getValue().add(new recyclerviewFragment(), "Notes");
        viewModel.getViewPagerAdapter().getValue().add(new recyclerviewFragment(), "TO DO");
        // Set the adapter
        viewModel.getViewPagerAdapter().observe(getViewLifecycleOwner(), viewPagerAdapter -> {
            binding.viewpager.setAdapter(viewPagerAdapter);
        });
        // The Page (fragment) titles will be displayed in the
        // tabLayout hence we need to  set the page viewer
        // we use the setupWithViewPager().
        binding.tabLayout.setupWithViewPager(binding.viewpager);
        binding.fab.setOnClickListener(view -> Navigation.findNavController(view).navigate(R.id.action_viewPagerFragment_to_newDataFragment));

        binding.bottomAppBar.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.profile:
//                        newGame();
                    return true;
//                case R.id.more:
//                        showHelp();
//                    return true;
                default:
                    return false;
            }
        });
        return binding.getRoot();
    }
}