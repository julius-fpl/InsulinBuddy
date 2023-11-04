package de.julius_fpl.insulin_buddy.ui.logbook;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import de.julius_fpl.insulin_buddy.R;
import de.julius_fpl.insulin_buddy.databinding.FragmentLogbookBinding;

public class LogbookFragment extends Fragment {

    private FragmentLogbookBinding binding;

    private Button button;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        LogbookViewModel logbookViewModel =
                new ViewModelProvider(this).get(LogbookViewModel.class);

        binding = FragmentLogbookBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textBlank;
        logbookViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        button = root.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Button wurde gedrückt
                Toast.makeText(getActivity(), "Gedrückt", Toast.LENGTH_SHORT).show();
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}