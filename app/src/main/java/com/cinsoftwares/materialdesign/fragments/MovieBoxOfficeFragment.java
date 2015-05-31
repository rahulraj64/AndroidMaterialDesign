package com.cinsoftwares.materialdesign.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.cinsoftwares.materialdesign.R;
import com.cinsoftwares.materialdesign.application.MyApplication;
import com.cinsoftwares.materialdesign.network.VolleySingleton;

import org.json.JSONObject;


public class MovieBoxOfficeFragment extends Fragment {


    private static final String API_URL = "http://api.rottentomatoes.com/api/public/v1.0/lists/movies/box_office.json";
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    RequestQueue queue;
    JsonObjectRequest request;

    public static MovieBoxOfficeFragment newInstance(String param1, String param2) {
        MovieBoxOfficeFragment fragment = new MovieBoxOfficeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public MovieBoxOfficeFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        setUpVolley();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_box_office, container, false);
    }

    public void setUpVolley() {

         queue = VolleySingleton.getInstance().getRequestQueue();
         request = new JsonObjectRequest(Request.Method.GET, getApiUrl(10),

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Toast.makeText(getActivity(), response.toString(), Toast.LENGTH_SHORT).show();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();


                    }
                }

        );
        queue.add(request);

    }

    private String getApiUrl(int limit) {

        return API_URL + "?apikey="+ MyApplication.ROTTEN_TOMATOES_API_KEY + "&limit=" + limit;

    }


}
