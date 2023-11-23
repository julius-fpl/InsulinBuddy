package de.julius_fpl.insulin_buddy.ui.KEChanger;

import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import de.julius_fpl.insulin_buddy.R;
import de.julius_fpl.insulin_buddy.databinding.FragmentKeChangerBinding;
import de.julius_fpl.insulin_buddy.ui.BloodSugarCorrection.BloodSugarCorrectionViewModel;
import de.julius_fpl.insulin_buddy.ui.CalculateCarbonHydrates.CalculateCarbonHydratesViewModel;

public class KEChanger extends Fragment {
    private FragmentKeChangerBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        BloodSugarCorrectionViewModel bloodSugarCorrectionViewModel =
                new ViewModelProvider(this).get(BloodSugarCorrectionViewModel.class);

        binding = FragmentKeChangerBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        FloatingActionButton addButton = root.findViewById(R.id.fab1);
        Activity activity = getActivity();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Object initialisation
                EditText editTextProductName = root.findViewById(R.id.editTextProductNameFragmentKEChanger);
                EditText editTextOneKE = root.findViewById(R.id.editTextOneKE);
                EditText editTextTotalWeight = root.findViewById(R.id.editTextTotalWeightFragmentKEChanger);
                EditText editTextKeFactor = activity.findViewById(R.id.editTextKEFactor);
                TextView textViewListOfProducts = (TextView) activity.findViewById(R.id.textViewListOfProducts);
                TextView textViewTotalKe = (TextView) activity.findViewById(R.id.textViewTotalKe);

                //Save products that have already been added
                String ListOfProducts = (String) textViewListOfProducts.getText();

                //Store the input data
                double OneKE = Double.parseDouble(String.valueOf(editTextOneKE.getText()));
                double TotalWeight = Double.parseDouble(String.valueOf(editTextTotalWeight.getText()));

                //Calculate the KE
                double result = TotalWeight / OneKE;

                //Set up and initialise the viewModel and the activity
                CalculateCarbonHydratesViewModel viewModel;
                viewModel = CalculateCarbonHydratesViewModel.getInstance();

                //Add the new product by adding the new product at the end of the products that have already been added
                textViewListOfProducts.setText(ListOfProducts + "\n" + TotalWeight + "g von " + editTextProductName.getText() +" sind: " + result + " KE");

                //Calculate the total KE by adding the KE result to the viewModel and display the new total
                viewModel.setCarbohydrates(result);
                textViewTotalKe.setText("Gesamt KE: " + viewModel.getCarbohydrates());

                //Save the Data in the viewModel
                ListOfProducts = String.valueOf(textViewListOfProducts.getText());
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