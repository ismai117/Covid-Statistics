package com.nbaengine.covidstatistics.model;

import android.widget.ArrayAdapter;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class CovidResponse {

    @SerializedName("get")
    public String get;

    @SerializedName("parameters")
    public Parameters parameters;

    @SerializedName("errors")
    public List<Errors> errors;

    @SerializedName("results")
    public String results;

    @SerializedName("response")
    public List<Response> response;





    static class Parameters {

        @SerializedName("country")
        public String countries;

    }

    static class Errors {

    }


    public static class Response {

        @SerializedName("continent")
        public String continent;

        @SerializedName("country")
        public String country;

        @SerializedName("population")
        public Long population;

        @SerializedName("cases")
        public Cases cases;

        @SerializedName("deaths")
        public Deaths deaths;

        @SerializedName("tests")
        public Tests tests;

        @SerializedName("day")
        public String day;

        @SerializedName("time")
        public String time;

        public static class Cases {

            @SerializedName("new")
            public String newCases;

            @SerializedName("active")
            public Long active;

            @SerializedName("critical")
            public Long critical;

            @SerializedName("recovered")
            public Long recovered;

            @SerializedName("1M_pop")
            public String oneM_pop;

            @SerializedName("total")
            public Long total;

        }

        public static class Deaths {

            @SerializedName("new")
            public String newCases;

            @SerializedName("1M_pop")
            public String oneM_pop;

            @SerializedName("total")
            public Long total;

        }

        public static class Tests {

            @SerializedName("1M_pop")
            public String oneM_pop;

            @SerializedName("total")
            public Long total;

        }


    }

}







