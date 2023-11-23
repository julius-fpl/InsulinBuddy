package de.julius_fpl.insulin_buddy.ui.CalculateCarbonHydrates;

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
import de.julius_fpl.insulin_buddy.databinding.FragmentCalculateCarbonHydratesBinding;

public class CalculateCarbonHydrates extends Fragment {

    private FragmentCalculateCarbonHydratesBinding binding;
    private CalculateCarbonHydratesViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CalculateCarbonHydratesViewModel calculateCarbonHydratesViewModel =
                new ViewModelProvider(this).get(CalculateCarbonHydratesViewModel.class);


        binding = FragmentCalculateCarbonHydratesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //ToDo Delete if it works without
        // viewModel = new CalculateCarbonHydratesViewModel();

        //Set up and initialise the viewModel and the activity
        viewModel = CalculateCarbonHydratesViewModel.getInstance();
        Activity activity = getActivity();

        //Object initialisation
        FloatingActionButton addButton = root.findViewById(R.id.fab);
        EditText editTextProductName = root.findViewById(R.id.editTextProductNameFragmentCalculateCarbonHydrates);
        EditText editTextCarbohydratePerHundredG = root.findViewById(R.id.editTextKohlenhydrateProHundert);
        EditText editTextWeightOfProduct = root.findViewById(R.id.editTextGewicht);
        EditText editTextKeFactor = activity.findViewById(R.id.editTextKEFactor);
        TextView textViewListOfProducts = activity.findViewById(R.id.textViewListOfProducts);
        TextView textViewTotalKe = activity.findViewById(R.id.textViewTotalKe);

        //Check if information is already stored in viewModel
        //If so, restore it
        String viewModelProductString = viewModel.getListOfProducts();
        if(viewModelProductString != null){
            textViewListOfProducts.setText(viewModel.getListOfProducts());
            textViewTotalKe.setText("Gesamt KE: " + viewModel.getCarbohydrates());
        }

        //react to a click on the Add Button
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Set up and initialise the viewModel
                viewModel = CalculateCarbonHydratesViewModel.getInstance();

                //Save products that have already been added
                String ListTexOfProducts = (String) textViewListOfProducts.getText();

                //Store the input data
                double CarbohydratePerHundredG = Double.parseDouble(String.valueOf(editTextCarbohydratePerHundredG.getText()));
                double WeightOfProduct = Double.parseDouble(String.valueOf(editTextWeightOfProduct.getText()));

                //Calculate the carbohydrate for the weight and convert the unit to KE by dividing by 10
                double result = (CarbohydratePerHundredG / 100) * WeightOfProduct;
                result = (result  / 10);

                //Add the new product by adding the new product at the end of the products that have already been added
                textViewListOfProducts.setText(ListTexOfProducts + "\n" + WeightOfProduct + "g von " + editTextProductName.getText() + " sind: " + result + " KE");

                //Calculate the total KE by adding the KE result to the viewModel and display the new total
                viewModel.setCarbohydrates(result);
                textViewTotalKe.setText("Gesamt KE: " + viewModel.getCarbohydrates());

                //Save the Data in the viewModel
                viewModel.setListOfProducts(String.valueOf(textViewListOfProducts.getText()));
                viewModel.setKeFactor(Double.parseDouble(String.valueOf(editTextKeFactor.getText())));
                //ToDo Delete!! textViewListOfProducts.setText(viewModel.getProductString());

                //Clear all text fields
                editTextCarbohydratePerHundredG.setText("");
                editTextWeightOfProduct.setText("");
                editTextProductName.setText("");
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