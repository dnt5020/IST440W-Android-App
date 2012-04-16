/** Image Adapter takes images and makes a grid view.
 * Joe DiZio 3/19/12
 **/

package com.theluvexchange.android;

import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter 
{
	private Context mContext;
	private Drawable[] images;


	public ImageAdapter(Context c, List<AlbumPhoto> photos) {
		mContext = c;


		images = new Drawable[photos.size()];
		int index = 0;
		for (AlbumPhoto photo : photos) {
			images[index++] = photo.getThumbnail();
		}
	}
	/*
    //full image constructor
    public ImageAdapter(Context c, AlbumPhoto photo) 
    {
        mContext = c;

    }
	 */

	public int getCount() {
		return images.length;
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
		if (convertView == null) {  // if it's not recycled, initialize some attributes
			imageView = new ImageView(mContext);
			imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
			imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
			imageView.setPadding(10, 10, 10, 10);
		} else {
			imageView = (ImageView) convertView;
		}

		imageView.setImageDrawable(images[position]);
		return imageView;
	}
}