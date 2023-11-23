package de.julius_fpl.insulin_buddy;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import de.julius_fpl.insulin_buddy.adapters.TabAdapter;
import de.julius_fpl.insulin_buddy.ui.BloodSugarCorrection.BloodSugarCorrection;
import de.julius_fpl.insulin_buddy.ui.BreadCalculator.BreadCalculator;
import de.julius_fpl.insulin_buddy.ui.CalculateCarbonHydrates.CalculateCarbonHydrates;
import de.julius_fpl.insulin_buddy.ui.CalculateCarbonHydrates.CalculateCarbonHydratesViewModel;
import de.julius_fpl.insulin_buddy.ui.KEChanger.KEChanger;
import de.julius_fpl.insulin_buddy.ui.ResultOfCalculation.ResultOfCalculation;
import de.julius_fpl.insulin_buddy.ui.logbook.LogbookFragment;

public class MealCalculator extends AppCompatActivity {

    CalculateCarbonHydratesViewModel viewModel;
    private final Fragment CalculateCarbonHydratesFragment = new CalculateCarbonHydrates();
    private final Fragment KEChangerFragment = new KEChanger();
    private final Fragment BreadCalculatorFragment = new BreadCalculator();
    private final Fragment LogbookFragmentFragment = new LogbookFragment();

    private final TabAdapter tabAdapter = new TabAdapter(this, getSupportFragmentManager());

    public MealCalculator() {
        super(R.layout.activity_meal_calculator);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    //.add(R.id.fragment_container_view, CalculateCarbonHydrates.class, null)
                    .commit();
        }

        viewModel = CalculateCarbonHydratesViewModel.getInstance();
        viewModel.clearCarbohydrates();
        viewModel.clearListOfProducts();


        //Setup TabBar with a ViewPager
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        ViewPager viewPager = findViewById(R.id.viewPager);

        tabLayout.setupWithViewPager(viewPager);

        tabAdapter.addFragment(CalculateCarbonHydratesFragment, "Kohlenhydrate");
        tabAdapter.addFragment(KEChangerFragment, "KE");
        tabAdapter.addFragment(BreadCalculatorFragment, "Brot/Kuchen");
        tabAdapter.addFragment(LogbookFragmentFragment, "Gespeicherte");

        ((ViewPager) findViewById(R.id.viewPager)).setAdapter(tabAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem((int) tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    //Loade new Fragment when clicked on >
    //Make ViewPager unvisible when i=0
    private int i = 0;
    public void next(View view) {
        if (i == 0){
            i++;
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragment_container_view, BloodSugarCorrection.class, null)
                    .addToBackStack("name")
                    .commit();

            TabLayout tabLayout = findViewById(R.id.tabLayout);
            tabLayout.setVisibility(View.GONE);

            ViewPager viewPager = findViewById(R.id.viewPager);
            viewPager.setVisibility(View.GONE);
        }
        else{
            i++;
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragment_container_view, ResultOfCalculation.class, null)
                    .addToBackStack("name")
                    .commit();
        }
    }

    //if i=0 make ViewPager visable
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        i--;
        if (i == 0){
            TabLayout tabLayout = findViewById(R.id.tabLayout);
            tabLayout.setVisibility(View.VISIBLE);

            ViewPager viewPager = findViewById(R.id.viewPager);
            viewPager.setVisibility(View.VISIBLE);
        }
    }
}