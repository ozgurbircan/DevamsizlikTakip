package com.bircan.ozgur.devamsizliktakip;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by ozgur on 24.4.2016.
 */
class ExpandableListViewData extends BaseExpandableListAdapter {
    Context içerik;
    HashMap<String,List<String>> ulke_detay;

    public ExpandableListViewData(Context içerik, HashMap<String, List<String>> ulke_detay, List<String> ulke_listesi) {
        this.içerik = içerik;
        this.ulke_detay = ulke_detay;
        this.ulke_listesi = ulke_listesi;
    }

    List<String> ulke_listesi;
    @Override
    public int getGroupCount() {
        return ulke_listesi.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return ulke_detay.get(ulke_listesi.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return ulke_listesi.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return ulke_detay.get(ulke_listesi.get(i)).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        String text = (String) getGroup(i);

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) içerik.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.parent, null);
        }

        TextView tvText = (TextView) view.findViewById(R.id.textView1);
        tvText.setText(text);

        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        String text = (String) getChild(i,i1);

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) içerik.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.child, null);
        }

        TextView tvText = (TextView) view.findViewById(R.id.textView2);
        tvText.setText(text);

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}