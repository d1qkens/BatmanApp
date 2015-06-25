package com.d1qkens.onixtestingapp.Fragments;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.d1qkens.onixtestingapp.ListItemsActivity;
import com.d1qkens.onixtestingapp.R;
import com.d1qkens.onixtestingapp.Utils.ParseJson;
import com.d1qkens.onixtestingapp.Utils.SaveSharedPreferences;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONObject;

import java.util.ArrayList;


public class RegistrationFragment extends Fragment {
    private static final String URL = "https://www.dropbox.com/s/hid3nun8jbpmcxn/batman.json?dl=1";
    public static final String IMAGES = "images";
    public static final String TITLES = "titles";
    private View fragmentView;
    private EditText login;
    private EditText password;
    private ArrayList<String> carsList;
    private ArrayList<String> titlesList;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_registration, container, false);
        return fragmentView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button loginButton = (Button) fragmentView.findViewById(R.id.login_btn);
        login = (EditText) fragmentView.findViewById(R.id.login);
        password = (EditText) fragmentView.findViewById(R.id.password);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loginUsername = login.getText().toString();
                String passwordText = password.getText().toString();

                if (loginUsername.matches("") || passwordText.matches("")) {
                    Toast.makeText(getActivity(), R.string.empty_field, Toast.LENGTH_SHORT).show();
                } else {
                    SaveSharedPreferences.setUserName(getActivity(), loginUsername);
                    AsyncHttpClient client = new AsyncHttpClient();
                    client.get(URL, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            carsList = ParseJson.getCars(response);
                            titlesList = ParseJson.getTitles(response);

                            Intent intent = new Intent(getActivity(), ListItemsActivity.class);
                            intent.putStringArrayListExtra(IMAGES, carsList);
                            intent.putStringArrayListExtra(TITLES, titlesList);
                            startActivity(intent);
                        }
                    });
                }
            }
        });
    }
}

