package com.nbaengine.covidstatistics.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.nbaengine.covidstatistics.R;
import com.nbaengine.covidstatistics.model.CovidResponse;
import com.nbaengine.covidstatistics.viewmodel.CovidViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.countryInput)
    EditText countryValue;
    @BindView(R.id.search)
    AppCompatButton search;
    @BindView(R.id.pieChart)
    BarChart barChart;

    private CovidViewModel covidViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        covidViewModel = new ViewModelProvider(this).get(CovidViewModel.class);
        covidViewModel.getMutableLiveData().observe(this, new Observer<CovidResponse>() {
            @Override
            public void onChanged(CovidResponse covidResponse) {

                String continent = covidResponse.response.get(0).continent;
                String country = covidResponse.response.get(0).country;
                long population = covidResponse.response.get(0).population;

                // cases
                String new_cases = covidResponse.response.get(0).cases.newCases;
                long active_cases = covidResponse.response.get(0).cases.active;
                long critical_cases = covidResponse.response.get(0).cases.critical;
                long recovered_cases = covidResponse.response.get(0).cases.recovered;
                String oneM_pop_cases = covidResponse.response.get(0).cases.oneM_pop;
                long total_cases = covidResponse.response.get(0).cases.total;

                // deaths
                String new_death = covidResponse.response.get(0).deaths.newCases;
                String oneM_pop_death = covidResponse.response.get(0).deaths.oneM_pop;
                long total_death = covidResponse.response.get(0).deaths.total;

                // tests
                String oneM_pop_test = covidResponse.response.get(0).tests.oneM_pop;
                long total_test = covidResponse.response.get(0).tests.total;



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
                        "total: " + covidResponse.response.get(0).tests.total + "\n"

                );

            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String country = countryValue.getText().toString();

                if (country.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Enter country name", Toast.LENGTH_SHORT).show();
                } else {
                    covidViewModel.MakeCovidStatCall(country);
                }

            }
        });


    }
}