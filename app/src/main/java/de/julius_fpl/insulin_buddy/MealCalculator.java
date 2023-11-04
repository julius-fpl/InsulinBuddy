package de.julius_fpl.insulin_buddy;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import de.julius_fpl.insulin_buddy.databinding.ActivityMainBinding;
import de.julius_fpl.insulin_buddy.databinding.ActivityMealCalculatorBinding;
import de.julius_fpl.insulin_buddy.ui.logbook.LogbookFragment;
import de.julius_fpl.insulin_buddy.ui.settings.SettingsFragment;

public class MealCalculator extends AppCompatActivity {

    public MealCalculator() {
        super(R.layout.activity_meal_calculator);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_container_view, LogbookFragment.class, null)
                    .commit();
        }
    }

    public void Add(View view) {
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragment_container_view, SettingsFragment.class, null)
                .addToBackStack("name")
                .commit();
    }
}