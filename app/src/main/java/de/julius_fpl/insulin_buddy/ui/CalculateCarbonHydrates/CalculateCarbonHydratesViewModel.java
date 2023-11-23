package de.julius_fpl.insulin_buddy.ui.CalculateCarbonHydrates;

import androidx.lifecycle.ViewModel;

public class CalculateCarbonHydratesViewModel extends ViewModel {

    private static CalculateCarbonHydratesViewModel instance;
    private double carbohydrates;
    private double keFactor;
    private String ListOfProducts;

    // TODO: Implement the ViewModel
    public CalculateCarbonHydratesViewModel() {
    }

    public static CalculateCarbonHydratesViewModel getInstance() {
        if (instance == null) {
            instance = new CalculateCarbonHydratesViewModel();
        }
        return instance;
    }

    public void setCarbohydrates(double carbohydrates){
        this.carbohydrates += carbohydrates;
    }

    public double getCarbohydrates(){
        return carbohydrates;
    }

    public void clearCarbohydrates(){
        carbohydrates = 0;
        keFactor = 0;
    }

    public void setListOfProducts(String ListOfProducts){
        this.ListOfProducts = ListOfProducts;
    }

    public String getListOfProducts(){
        return ListOfProducts;
    }

    public void clearListOfProducts(){
        ListOfProducts = "";
    }

    public void setKeFactor(double keFactor){
        this.keFactor = keFactor;
    }

    public double getKeFactor(){
        return keFactor;
    }
}