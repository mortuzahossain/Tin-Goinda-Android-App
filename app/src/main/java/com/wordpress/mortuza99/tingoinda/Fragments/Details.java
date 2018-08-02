package com.wordpress.mortuza99.tingoinda.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
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

        String name, image, downloadlink;

        Bundle bundle = getArguments();
        name = String.valueOf(bundle.getString("NAME"));
        image = String.valueOf(bundle.getString("IMAGE"));
        downloadlink = String.valueOf(bundle.getString("DOWNLOADURL"));

        TextView bookNameDetailPage = view.findViewById(R.id.bookNameDetailPage);
        ImageView bookImageDetailPage = view.findViewById(R.id.bookImageDetailPage);

        bookNameDetailPage.setText(name);

        Picasso.get()
                .load(image)
                .into(bookImageDetailPage);


        if (!hasDownloaded()) {
            CardView readOnline = view.findViewById(R.id.readOnline);
            readOnline.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getActivity().getApplicationContext(), "READ ONLINE", Toast.LENGTH_LONG).show();
                }
            });

            CardView openDownload = view.findViewById(R.id.download);
            openDownload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getActivity().getApplicationContext(), "READ Download", Toast.LENGTH_LONG).show();
                }
            });
        } else {
            CardView openFromFolder = view.findViewById(R.id.openFromFolder);
            openFromFolder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getActivity().getApplicationContext(), "OPen FROM FOLDER", Toast.LENGTH_LONG).show();
                }
            });
        }

        return view;
    }

    private boolean hasDownloaded() {
        return false;
    }

}
