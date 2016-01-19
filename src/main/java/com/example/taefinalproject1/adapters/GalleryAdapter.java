package com.example.taefinalproject1.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

/**
 * Created by TAE_user2 on 15/01/2016.
 */
public class GalleryAdapter extends BaseAdapter {
    private Context mContext;
    private Integer[] mThumbIds;
    public GalleryAdapter(Context c) {
        mContext = c;
        this.mThumbIds = getmThumbIds();
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
//            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
//            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }

    private Integer[] getmThumbIds(){
        // TODO: 18/01/2016 Implement thumbnailIds for Champion Selection Screen
        Integer[] ret = new Integer[128];
        int r = mContext.getResources().getIdentifier("res:drawable/aatrox", null, null);
        return ret;
    }
}
