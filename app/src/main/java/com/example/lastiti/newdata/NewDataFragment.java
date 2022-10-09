package com.example.lastiti.newdata;

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
import com.example.lastiti.database.note.Note;
import com.example.lastiti.database.note.NoteViewModel;
import com.example.lastiti.databinding.FragmentNewDataBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class NewDataFragment extends Fragment {
    private NoteViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentNewDataBinding binding = FragmentNewDataBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(NoteViewModel.class);
        final Integer[] Image_int = {0};
        binding.select.setOnClickListener(view -> {
            int rand = (int) Math.abs(Calendar.getInstance().getTimeInMillis() % 3);
            Toast.makeText(getContext(), "rand" + rand, Toast.LENGTH_LONG).show();
            switch (rand) {
                case 0:
                    binding.pic.setImageResource(R.drawable.dog1);
                    Image_int[0] = R.drawable.dog1;
                    break;
                case 1:
                    binding.pic.setImageResource(R.drawable.dog2);
                    Image_int[0] = R.drawable.dog2;
                    break;
                case 2:
                    binding.pic.setImageResource(R.drawable.dog3);
                    Image_int[0] = R.drawable.dog3;
                    break;
            }
        });
        binding.save.setOnClickListener(view -> {
            Toast.makeText(getContext(), "new Note added", Toast.LENGTH_LONG).show();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z", Locale.getDefault());
            Note newNote = new Note(Objects.requireNonNull(binding.title1.getText()).toString(), Objects.requireNonNull(binding.desc.getText()).toString(), sdf.format(new Date()), Image_int[0] , false);
            viewModel.insert(newNote);
            if (Boolean.TRUE.equals(viewModel.getFlag().getValue())) {
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_newDataFragment2_to_detailFragment);
                viewModel.setFlag(false);
                viewModel.setFlagDelete(true);

            } else {
                Navigation.findNavController(binding.getRoot()).navigateUp();
            }
        });
        return binding.getRoot();
    }
}