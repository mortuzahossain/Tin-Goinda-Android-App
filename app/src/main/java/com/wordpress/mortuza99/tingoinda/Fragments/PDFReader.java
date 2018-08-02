package com.wordpress.mortuza99.tingoinda.Fragments;


import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.wordpress.mortuza99.tingoinda.R;

import java.io.File;

/**
 * A simple {@link Fragment} subclass.
 */
public class PDFReader  extends Fragment {


    public PDFReader() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pdfreader, container, false);

        Bundle bundle = getArguments();
        String FILENAME = String.valueOf(bundle.getString("FILENAME"));
        PDFView pdfView = view.findViewById(R.id.pdfView);
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), Environment.DIRECTORY_DOWNLOADS + "/Books/" + FILENAME);
        pdfView.fromFile(file)
                .load();

        return view;
    }

}
