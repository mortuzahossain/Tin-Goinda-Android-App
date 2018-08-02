package com.wordpress.mortuza99.tingoinda.Fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wordpress.mortuza99.tingoinda.R;
import com.wordpress.mortuza99.tingoinda.controller.MyRecyclerAdapter;
import com.wordpress.mortuza99.tingoinda.model.Books;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment {

    public Home() {
    }

    static List<Books> booksList = new ArrayList<>();

    String TAG = "ddd";
    MyRecyclerAdapter myRecyclerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        final ProgressBar progressBar = view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        myRecyclerAdapter = new MyRecyclerAdapter(getActivity().getApplicationContext(), booksList);

        RecyclerView myrecycleView = view.findViewById(R.id.recyclerHome);
        myrecycleView.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(), 3));
        myrecycleView.setAdapter(myRecyclerAdapter);

        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        database = database.child("Books/TinGoinda");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                booksList.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Books books = ds.getValue(Books.class);
                    booksList.add(books);
                }
                myRecyclerAdapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d(TAG, "Error Loading Database");
            }
        });

        myRecyclerAdapter.setItemClickListener(new MyRecyclerAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {

                FragmentTransaction transection = getFragmentManager().beginTransaction();
                Details details = new Details();
                Bundle bundle = new Bundle();

                bundle.putString("NAME", booksList.get(position).getName());
                bundle.putString("IMAGE", booksList.get(position).getImage());
                bundle.putString("DOWNLOADURL", booksList.get(position).getDownloadlink());

                details.setArguments(bundle);
                transection.replace(R.id.mainframe, details);
                transection.commit();
            }
        });

        return view;
    }


}
