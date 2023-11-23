package de.julius_fpl.insulin_buddy.ui.ResultOfCalculation;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import de.julius_fpl.insulin_buddy.R;
import de.julius_fpl.insulin_buddy.databinding.FragmentResultOfCalculationBinding;
import de.julius_fpl.insulin_buddy.ui.BloodSugarCorrection.BloodSugarCorrectionViewModel;
import de.julius_fpl.insulin_buddy.ui.CalculateCarbonHydrates.CalculateCarbonHydratesViewModel;

public class ResultOfCalculation extends Fragment {

    private FragmentResultOfCalculationBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ResultOfCalculationViewModel resultOfCalculationViewModel =
                new ViewModelProvider(this).get(ResultOfCalculationViewModel.class);

        binding = FragmentResultOfCalculationBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        //Set up and initialise the viewModels
        CalculateCarbonHydratesViewModel viewModelCalculate = CalculateCarbonHydratesViewModel.getInstance();
        BloodSugarCorrectionViewModel viewModelBlood = BloodSugarCorrectionViewModel.getInstance();

        //Save data from the viewModels
        double keOfProducts = viewModelCalculate.getCarbohydrates();
        double correctionInsulin = viewModelBlood.getCorrectionInsulin();
        double keFactor = viewModelCalculate.getKeFactor();

        //Calculate the amount of insulin to be injected
        double result = (keOfProducts * keFactor) + correctionInsulin;

        //Set up and initialise the TextView and display the result
        TextView textView = root.findViewById(R.id.textViewAmountOfInsulinToBeInjectedFragmentResultOfCalculation);
        textView.setText("Zu spritzen: " + result);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}