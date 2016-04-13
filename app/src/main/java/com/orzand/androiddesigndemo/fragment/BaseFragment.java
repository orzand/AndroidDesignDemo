package com.orzand.androiddesigndemo.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.widget.Toast;


public abstract class BaseFragment extends Fragment {
    protected static final String TITLE = "title";
    protected Context context;

    private String title = "";

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    public final String getTitle() {
        if (getArguments() != null) {
            return getArguments().getString(TITLE);
        }

        return title;
    }

    public void onSelected() {
    }

    public void toast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }
}
