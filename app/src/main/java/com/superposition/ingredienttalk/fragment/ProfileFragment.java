package com.superposition.ingredienttalk.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.superposition.ingredienttalk.R;
import com.superposition.ingredienttalk.util.Constants;

public class ProfileFragment extends Fragment {


    TextView txtName, txtEmail, txtMobile;
    Button btnLogout;
    SharedPreferences sharedPreferences;
    ImageView imgProfile;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        txtName = view.findViewById(R.id.txtProfileName);
        txtEmail = view.findViewById(R.id.txtProfileEmail);
        txtMobile = view.findViewById(R.id.txtProfileMobile);
        btnLogout = view.findViewById(R.id.btnLogout);
        imgProfile = view.findViewById(R.id.imgProfile);

        Picasso.get().load(sharedPreferences.getString(Constants.IMAGE_LINK,"no_image"))
                .error(R.drawable.ic_user_150)
                .into(imgProfile);

        txtName.setText(sharedPreferences.getString(Constants.NAME,"--"));
        txtEmail.setText(sharedPreferences.getString(Constants.EMAIL,"--"));
        txtMobile.setText(sharedPreferences.getString(Constants.MOBILE,"--"));

        return view;
    }
}