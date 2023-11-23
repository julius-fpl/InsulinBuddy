package de.julius_fpl.insulin_buddy.ui.BloodSugarCorrection;

import androidx.lifecycle.ViewModel;

public class BloodSugarCorrectionViewModel extends ViewModel {
    private static BloodSugarCorrectionViewModel instance;
    private double CorrectionInsulin;
    public BloodSugarCorrectionViewModel() {
    }

    public static BloodSugarCorrectionViewModel getInstance() {
        if (instance == null) {
            instance = new BloodSugarCorrectionViewModel();
        }
        return instance;
    }

    public void setCorrectionInsulin(double CorrectionInsulin){
        this.CorrectionInsulin = CorrectionInsulin;
    }

    public double getCorrectionInsulin(){
        return CorrectionInsulin;
    }
}