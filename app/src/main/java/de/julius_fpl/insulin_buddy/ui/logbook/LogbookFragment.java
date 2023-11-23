package de.julius_fpl.insulin_buddy.ui.logbook;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.julius_fpl.insulin_buddy.databinding.FragmentLogbookBinding;

public class LogbookFragment extends Fragment {

    private FragmentLogbookBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        LogbookViewModel logbookViewModel =
                new ViewModelProvider(this).get(LogbookViewModel.class);

        binding = FragmentLogbookBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}