package com.d1qkens.onixtestingapp;


import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
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
        Button signUpButton = (Button) fragmentView.findViewById(R.id.sign_up_btn);
        Button loginButton = (Button) fragmentView.findViewById(R.id.login_btn);
        login = (EditText) fragmentView.findViewById(R.id.login);
        password = (EditText) fragmentView.findViewById(R.id.password);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity() != null) {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("Sign up");
                    View view = getActivity().getLayoutInflater().inflate(R.layout.sign_up, null);
                    builder.setView(view);
                    final EditText login = (EditText) view.findViewById(R.id.login_dialog);
                    final EditText password = (EditText) view.findViewById(R.id.password_dialog);
                    final EditText confirmPassword = (EditText) view.findViewById(R.id.confirm_password_dialog);
                    builder.setPositiveButton(R.string.sign_up_dialog_btn, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String loginUsername = login.getText().toString();
                            String passwordText = password.getText().toString();
                            String confirmPass = confirmPassword.getText().toString();
                            if (loginUsername.matches("") || passwordText.matches("") || confirmPass.matches("")) {
                                Toast.makeText(getActivity(), R.string.empty_field, Toast.LENGTH_SHORT).show();
                            }
                            if (!passwordText.equals(confirmPass) || passwordText.matches("") || confirmPass.matches("")) {
                                Toast.makeText(getActivity(), R.string.pass_error, Toast.LENGTH_SHORT).show();
                            } else {
                                dialog.dismiss();
                            }
                        }
                    });

                    builder.setNegativeButton(R.string.back_dialog_btn, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.show();
                }
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loginUsername = login.getText().toString();
                SaveSharedPreferences.setUserName(getActivity(),loginUsername);
                Log.d("TAG", "userName added:" + SaveSharedPreferences.getUserName(getActivity()));

                String passwordText = password.getText().toString();

                if (loginUsername.matches("") || passwordText.matches("")) {
                    Toast.makeText(getActivity(), R.string.empty_field, Toast.LENGTH_SHORT).show();
                } else {

                    AsyncHttpClient client = new AsyncHttpClient();
                    client.get(URL, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            try {
                                JSONArray imagesArr = response.getJSONArray("images");
                                carsList = new ArrayList<>();
                                for (int i = 0; i < imagesArr.length(); i++) {
                                    carsList.add(imagesArr.getString(i));
                                }
                                JSONArray titlesArr = response.getJSONArray("titles");
                                titlesList = new ArrayList<>();
                                for (int i = 0; i < titlesArr.length(); i++) {
                                    titlesList.add(titlesArr.getString(i));
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            Intent intent = new Intent(getActivity(), ListItemsActivity.class);
                            intent.putStringArrayListExtra(IMAGES, carsList);
                            intent.putStringArrayListExtra(TITLES, titlesList);
                            startActivity(intent);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                            Toast.makeText(getActivity(), "Connection Failed", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getActivity(), ListItemsActivity.class);
                            ArrayList<String> failed = new ArrayList<>();
                            for (int i = 0; i < 5; i++) {
                                failed.add("" + i + "");
                            }
                            intent.putStringArrayListExtra(IMAGES, failed);
                            intent.putStringArrayListExtra(TITLES, failed);
                            startActivity(intent);
                        }
                    });
                }
            }
        });
    }
}

