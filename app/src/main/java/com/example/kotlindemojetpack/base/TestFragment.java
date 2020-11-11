package com.example.kotlindemojetpack.base;

import androidx.fragment.app.Fragment;

import java.util.Collection;

/**
 * create by pan yi on 2020/11/11
 * desc :
 */
public class TestFragment extends Fragment {
    private BaseAdapter adapter;

    public void setAdapter(BaseAdapter adapter) {
        this.adapter = adapter;
    }
}
