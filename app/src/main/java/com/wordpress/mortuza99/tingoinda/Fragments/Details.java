package com.wordpress.mortuza99.tingoinda.Fragments;


import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.webkit.URLUtil;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.wordpress.mortuza99.tingoinda.R;

import java.io.File;

import static android.content.Context.DOWNLOAD_SERVICE;
import static android.support.v4.content.ContextCompat.getSystemService;

public class Details extends Fragment {


    public Details() {
        // Required empty public constructor
    }

    String name, image, downloadlink;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_details, container, false);


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


        CardView readOnline = view.findViewById(R.id.readOnline);
        CardView openDownload = view.findViewById(R.id.download);
        CardView openFromFolder = view.findViewById(R.id.openFromFolder);

        if (!hasDownloaded()) {
            readOnline.setVisibility(View.VISIBLE);
            openDownload.setVisibility(View.VISIBLE);
            openFromFolder.setVisibility(View.GONE);
        } else {
            readOnline.setVisibility(View.GONE);
            openDownload.setVisibility(View.GONE);
            openFromFolder.setVisibility(View.VISIBLE);
        }


        readOnline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity().getApplicationContext(), "READ ONLINE", Toast.LENGTH_LONG).show();
            }
        });

        openDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity().getApplicationContext(), "READ Download", Toast.LENGTH_LONG).show();

                int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 1;

                if (ContextCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
                } else {
                    name = name + ".pdf";
                    DownloadManager.Request request = new DownloadManager.Request(Uri.parse(downloadlink));
                    request.setTitle(name);
                    request.setDescription(name + " Downloading..");
                    request.allowScanningByMediaScanner();
                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                    request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS + "/Books/", name);
                    DownloadManager downloadManager = (DownloadManager) getActivity().getApplication().getSystemService(Context.DOWNLOAD_SERVICE);
                    downloadManager.enqueue(request);
                }

            }
        });

        openFromFolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transection = getFragmentManager().beginTransaction();
                PDFReader details = new PDFReader();
                Bundle bundle = new Bundle();
                bundle.putString("FILENAME", name+".pdf");
                details.setArguments(bundle);
                transection.replace(R.id.mainframe, details);
                transection.commit();
            }
        });

        return view;
    }

    boolean hasDownloaded() {
        File file = new File(Environment.getExternalStorageDirectory(), Environment.DIRECTORY_DOWNLOADS + "/Books/" + name + ".pdf");
        if (!file.exists()) {
            return false;
        } else {
            return true;
        }
    }

}
