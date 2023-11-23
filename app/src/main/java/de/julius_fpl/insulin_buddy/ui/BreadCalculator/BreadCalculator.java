package de.julius_fpl.insulin_buddy.ui.BreadCalculator;

import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
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
import de.julius_fpl.insulin_buddy.databinding.FragmentBreadCalculatorBinding;
import de.julius_fpl.insulin_buddy.ui.BloodSugarCorrection.BloodSugarCorrectionViewModel;
import de.julius_fpl.insulin_buddy.ui.CalculateCarbonHydrates.CalculateCarbonHydratesViewModel;


public class BreadCalculator extends Fragment {

    private FragmentBreadCalculatorBinding binding;
    private CalculateCarbonHydratesViewModel viewModel;

    public static BreadCalculator newInstance() {
        return new BreadCalculator();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        BloodSugarCorrectionViewModel bloodSugarCorrectionViewModel =
                new ViewModelProvider(this).get(BloodSugarCorrectionViewModel.class);

        binding = FragmentBreadCalculatorBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //ToDO Change to a floating Button
        Button buttonAdd = root.findViewById(R.id.buttonBerechnen);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Set up and initialise the viewModel and the activity
                viewModel = CalculateCarbonHydratesViewModel.getInstance();
                Activity activity = getActivity();

                //Object initialisation
                EditText editTextInsulin = (EditText) root.findViewById(R.id.editTextTotalInsulinFragmentBreadCalculator);
                EditText editTextTotalWeight = (EditText) root.findViewById(R.id.editTextTotalWeightFragmentBreadCalculator);
                EditText editTextProductWeight = (EditText) root.findViewById(R.id.editTextProductWeightFragmentBreadCalculator);
                EditText editTextKeFactor = activity.findViewById(R.id.editTextKEFactor);
                TextView textViewListOfProducts = (TextView) activity.findViewById(R.id.textViewListOfProducts);
                TextView textViewTotalKe = (TextView) activity.findViewById(R.id.textViewTotalKe);

                //Save products that have already been added
                String ListOfProducts = (String) textViewListOfProducts.getText();

                //Store the input data
                double insulin = Double.parseDouble(String.valueOf(editTextInsulin.getText()));
                double TotalWeight = Double.parseDouble(String.valueOf(editTextTotalWeight.getText()));
                double ProductWeight = Double.parseDouble(String.valueOf(editTextProductWeight.getText()));

                //Calculate the KE
                double result = (TotalWeight / ProductWeight);
                result = insulin / result;

                //Add the new product by adding the new product at the end of the products that have already been added
                textViewListOfProducts.setText(ListOfProducts + "\n" + editTextProductWeight.getText() + "g von Brot/Kuchen sind: " + result + " KE");

                //Calculate the total KE by adding the KE result to the viewModel and display the new total
                viewModel.setCarbohydrates(result);
                textViewTotalKe.setText("Gesamt KE: " + viewModel.getCarbohydrates());

                //Save the Data in the viewModel
                ListOfProducts= String.valueOf(textViewListOfProducts.getText());
                viewModel.setListOfProducts(ListOfProducts);
                viewModel.setKeFactor(Double.parseDouble(String.valueOf(editTextKeFactor.getText())));
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