package com.example.karol.turek.notes.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.karol.turek.notes.R;

public class Fragments<T extends Fragment, V extends Fragment> {
    private T fragment;
    private V newFragment;
    private FragmentManager fm;
    private FragmentTransaction ft;

    public static Fragments getFragment(FragmentManager fm, Fragment fragment){
        return new Fragments(fm, fragment);
    }

    public static Fragments getNewFragment(FragmentManager fm, Fragment fragment, Fragment newFragment){
        return new Fragments(fm, fragment, newFragment);
    }

    public Fragments(FragmentManager fm, T fragment) {
        this.fm = fm;
        this.fragment = fragment;
        getFragment();
    }

    public Fragments(FragmentManager fm, T fragment, V newFragment) {
        this.fragment = fragment;
        this.newFragment = newFragment;
        this.fm = fm;
        getNewFragment();
    }

    public void getFragment(){
        ft = fm.beginTransaction();
        ft.replace(R.id.content_main, fragment);
        ft.commit();
    }

    public void getNewFragment(){
        ft = fm.beginTransaction();
        ft.remove(fragment);
        ft.replace(R.id.content_main, newFragment);
        ft.commit();
    }
}