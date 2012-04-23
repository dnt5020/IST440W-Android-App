package com.theluvexchange.android;



import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;



public class ImageUpload extends Activity {

	private User user;

	private City city;
	private Activity activity;

	private Button upload;

	private EditText caption;

	private Uri currImageURI;

	private ImageView imageView;

	public void onCreate(Bundle savedInstanceState) {
		try{

			super.onCreate(savedInstanceState);
			activity = this;
			setContentView(R.layout.imageupload);

			TheLuvExchange application = (TheLuvExchange) getApplication();
			user = application.getUser();
			city = application.getCity();

			caption = (EditText) findViewById(R.id.Caption);

			imageView = (ImageView)findViewById(R.id.ImageView);

			// To open up a gallery browser

			Intent intent = new Intent();

			intent.setType("image/*");

			intent.setAction(Intent.ACTION_GET_CONTENT);

			startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);

			Button upload = (Button)findViewById(R.id.Upload);
			upload.setOnClickListener(onUpload);
		}
		catch (Exception e)
		{
			Log.e("uploaderror", "upload error ", e);
		}

	}



	// And to convert the image URI to the direct file system path of the image file

	public String getRealPathFromURI(Uri contentUri) {



		// can post image

		String [] proj={MediaStore.Images.Media.DATA};

		Cursor cursor = managedQuery( contentUri,

				proj, // Which columns to return

				null, // WHERE clause; which rows to return (all rows)

				null, // WHERE clause selection arguments (none)

				null); // Order-by clause (ascending by name)

		int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
		cursor.moveToFirst();
		return cursor.getString(column_index);
	}




	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK && requestCode == 1) {
			currImageURI = data.getData();
			imageView.setImageDrawable(Drawable.createFromPath(getRealPathFromURI(currImageURI)));	
		} else {
			activity.finish();
		}
	}




	private OnClickListener onUpload = new OnClickListener() {
		public void onClick(View v) {

			Object result = WebService.postPhoto(user, city, getRealPathFromURI(currImageURI), caption.getText().toString());

			Toast.makeText(activity, result.toString(), Toast.LENGTH_LONG).show();
		}
	};

}