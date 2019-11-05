package com.e_monsite.nazim_app.memorytest;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;


public class ImageAdapter extends BaseAdapter{

    private Context mContext;


    public ImageAdapter(Context c) {
        mContext = c;
    }


    @Override
    public int getCount() {
        return mThumbIds.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ImageView img;

            img = new ImageView(mContext);
            img.setLayoutParams(new GridView.LayoutParams(85, 85));
            img.setScaleType(ImageView.ScaleType.CENTER_CROP);
            img.setPadding(8, 8, 8, 8);


        img.setImageResource(mThumbIds[i]);

        return img;
    }


    public Integer[] mThumbIds = {
            R.drawable.one, R.drawable.two,
            R.drawable.three, R.drawable.four,
            R.drawable.five, R.drawable.six,
            R.drawable.seven, R.drawable.eight,
            R.drawable.ten, R.drawable.nine,
            R.drawable.eleven, R.drawable.twelve,
            R.drawable.treize, R.drawable.quatorze,
            R.drawable.quinze, R.drawable.seize,
            R.drawable.seventeen, R.drawable.eighteen,
            R.drawable.nineteen,R.drawable.twenione,
            R.drawable.twenifive, R.drawable.twenifour,
            R.drawable.tweninine, R.drawable.twenisix,
            R.drawable.twenithree, R.drawable.thirty,
            R.drawable.threeone, R.drawable.threetwo,
            R.drawable.threethree, R.drawable.twenitwo,
            R.drawable.tweniseven, R.drawable.twenieight};
}


