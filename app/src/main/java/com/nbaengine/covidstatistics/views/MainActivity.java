package com.nbaengine.covidstatistics.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.blongho.country_data.World;
import com.nbaengine.covidstatistics.R;
import com.nbaengine.covidstatistics.model.CovidResponse;
import com.nbaengine.covidstatistics.viewmodel.CovidViewModel;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.country_title)
    TextView countryTitleValue;
    @BindView(R.id.country)
    TextView countryValue;
    @BindView(R.id.cases)
    TextView casesValue;
    @BindView(R.id.deaths)
    TextView deathsValue;
    @BindView(R.id.recovered)
    TextView recoveredValue;
    @BindView(R.id.lastUpdated)
    TextView lastUpdated;
    @BindView(R.id.countryName)
    EditText countryName;
    @BindView(R.id.searchCountry)
    AppCompatButton search;
    @BindView(R.id.Progressbar)
    ProgressBar progressBar;
    @BindView(R.id.countryFlag)
    ImageView countryFlag;

    @BindView(R.id.activeCases_value)
    TextView activeCases;
    @BindView(R.id.closedCases_value)
    TextView closedCases;

    @BindView(R.id.mildCondition_cases)
    TextView mildCasses;
    @BindView(R.id.criticalCondition_cases)
    TextView criticalCases;
    @BindView(R.id.recovered_value)
    TextView recoveredCases;
    @BindView(R.id.death_value)
    TextView deathCases;


    private CovidViewModel covidViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        World.init(getApplicationContext());

        covidViewModel = new ViewModelProvider(this).get(CovidViewModel.class);
        covidViewModel.getMutableLiveData().observe(this, new Observer<CovidResponse>() {
            @Override
            public void onChanged(CovidResponse covidResponse) {

                if (covidResponse != null) {

                    progressBar.setVisibility(View.GONE);

                    try {

                        String continent = covidResponse.response.get(0).continent;
                        String country = covidResponse.response.get(0).country;
                        String population = String.valueOf(covidResponse.response.get(0).population);

                        // cases
                        String new_cases = covidResponse.response.get(0).cases.newCases;
                        String active_cases = String.valueOf(covidResponse.response.get(0).cases.active);
                        String critical_cases = String.valueOf(covidResponse.response.get(0).cases.critical);
                        long recovered_cases = covidResponse.response.get(0).cases.recovered;
                        String oneM_pop_cases = covidResponse.response.get(0).cases.oneM_pop;
                        long total_cases = covidResponse.response.get(0).cases.total;

                        // deaths
                        String new_death = covidResponse.response.get(0).deaths.newCases;
                        String oneM_pop_death = covidResponse.response.get(0).deaths.oneM_pop;
                        long total_death = covidResponse.response.get(0).deaths.total;

                        // tests
                        String oneM_pop_test = covidResponse.response.get(0).tests.oneM_pop;
                        String total_test = String.valueOf(covidResponse.response.get(0).tests.total);

                        String covid_lastUpated = covidResponse.response.get(0).time;

                        DecimalFormat formatter = new DecimalFormat("#,###.####");

                        lastUpdated.setText(covid_lastUpated);
                        countryTitleValue.setText(country.toUpperCase());
                        countryValue.setText(country.toUpperCase());
                        casesValue.setText(formatter.format(total_cases));
                        deathsValue.setText(formatter.format(total_death));
                        recoveredValue.setText(formatter.format(recovered_cases));
                        activeCases.setText(formatter.format(active_cases + critical_cases));
                        closedCases.setText(formatter.format(recovered_cases + total_death));

                        mildCasses.setText(formatter.format(active_cases));
                        criticalCases.setText(formatter.format(critical_cases));

                        recoveredCases.setText(formatter.format(recovered_cases));
                        deathCases.setText(formatter.format(total_death));

                        final int flag = World.getFlagOf(country.toLowerCase());
                        countryFlag.setImageResource(flag);


                        Log.d("responseList", " \n" +

                                "continent: " + covidResponse.response.get(0).continent + " \n" +
                                "country: " + covidResponse.response.get(0).country + " \n" +
                                "population: " + covidResponse.response.get(0).population + "\n\n" +

                                "cases" + "\n" +
                                "new: " + covidResponse.response.get(0).cases.newCases + "\n" +
                                "active: " + covidResponse.response.get(0).cases.active + "\n" +
                                "critical: " + covidResponse.response.get(0).cases.critical + "\n" +
                                "recovered: " + covidResponse.response.get(0).cases.recovered + "\n" +
                                "1M_pop: " + covidResponse.response.get(0).cases.oneM_pop + "\n" +
                                "total: " + covidResponse.response.get(0).cases.total + "\n\n" +

                                "death" + "\n" +
                                "new: " + covidResponse.response.get(0).deaths.newCases + "\n" +
                                "1M_pop: " + covidResponse.response.get(0).deaths.oneM_pop + "\n" +
                                "total: " + covidResponse.response.get(0).deaths.total + "\n" +

                                "tests" + "\n" +
                                "1M_pop: " + covidResponse.response.get(0).tests.oneM_pop + "\n" +
                                "total: " + covidResponse.response.get(0).tests.total + "\n" +

                                "last updated: " + covidResponse.response.get(0).time

                        );

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

            }
        });


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String country = countryName.getText().toString();

                if (country.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Enter country name", Toast.LENGTH_SHORT).show();
                } else {
                    covidViewModel.MakeCovidStatCall(country);
                    progressBar.setVisibility(View.VISIBLE);
                }

            }
        });

    }
}