package de.julius_fpl.insulin_buddy.ui.BloodSugarCorrection;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import de.julius_fpl.insulin_buddy.R;
import de.julius_fpl.insulin_buddy.databinding.FragmentBloodSugarCorrectionBinding;
import de.julius_fpl.insulin_buddy.ui.CalculateCarbonHydrates.CalculateCarbonHydratesViewModel;

public class BloodSugarCorrection extends Fragment {
    private FragmentBloodSugarCorrectionBinding binding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        BloodSugarCorrectionViewModel bloodSugarCorrectionViewModel =
                new ViewModelProvider(this).get(BloodSugarCorrectionViewModel.class);

        binding = FragmentBloodSugarCorrectionBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //Object initialisation
        Button buttonCalculate = root.findViewById(R.id.buttonCalculate);
        EditText editTextCurrentValue = root.findViewById(R.id.editTextCurrentValue);
        EditText editTextTargetValue = root.findViewById(R.id.editTextTargetValue);
        TextView textViewResult = root.findViewById(R.id.textViewResult);

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Calculate the correction insulin
                double result = Double.parseDouble(String.valueOf(editTextCurrentValue.getText())) - Double.parseDouble(String.valueOf(editTextTargetValue.getText()));
                result = result / 70;

                //Display the calculated result
                textViewResult.setText("" + result);

                //Set up and initialise the viewModel
                BloodSugarCorrectionViewModel viewModel = BloodSugarCorrectionViewModel.getInstance();

                //Save the Data in the viewModel
                viewModel.setCorrectionInsulin(result);
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