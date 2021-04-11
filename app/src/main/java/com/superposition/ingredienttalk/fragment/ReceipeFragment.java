package com.superposition.ingredienttalk.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.superposition.ingredienttalk.R;

public class ReceipeFragment extends Fragment {


    TextView txtSalt, txtOil, txtHaldi, txtGarlic, txtRed,txtPepper, txtGreen;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_receipe, container, false);

        txtSalt = view.findViewById(R.id.txtSalt);
        txtHaldi = view.findViewById(R.id.txtHaldi);
        txtGreen = view.findViewById(R.id.txtGreenChilly);
        txtOil = view.findViewById(R.id.txtOil);
        txtRed = view.findViewById(R.id.txtRedChilly);
        txtPepper = view.findViewById(R.id.txtPepper);
        txtGarlic = view.findViewById(R.id.txtGarlic);

        txtSalt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:6203520363"));
                startActivity(intent);
                getActivity().finish();
            }
        });

        txtHaldi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:6203520363"));
                startActivity(intent);
                getActivity().finish();
            }
        });

        txtOil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:6203520363"));
                startActivity(intent);
                getActivity().finish();
            }
        });

        txtGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:6203520363"));
                startActivity(intent);
                getActivity().finish();
            }
        });

        txtRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:6203520363"));
                startActivity(intent);
                getActivity().finish();
            }
        });

        txtGarlic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:6203520363"));
                startActivity(intent);
                getActivity().finish();
            }
        });

        txtPepper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:6203520363"));
                startActivity(intent);
                getActivity().finish();
            }
        });

        return view;
    }
}