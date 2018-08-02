package com.wordpress.mortuza99.tingoinda.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wordpress.mortuza99.tingoinda.R;

public class Details extends Fragment {


    public Details() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_details, container, false);

        TextView name = view.findViewById(R.id.name);
        TextView image = view.findViewById(R.id.image);
        TextView download = view.findViewById(R.id.downloadlink);

        Bundle bundle=getArguments();
        name.setText(String.valueOf(bundle.getString("NAME")));
        image.setText(String.valueOf(bundle.getString("IMAGE")));
        download.setText(String.valueOf(bundle.getString("DOWNLOADURL")));
        return view;
    }

}
