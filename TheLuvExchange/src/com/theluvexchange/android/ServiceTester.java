package com.theluvexchange.android;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Toast;

public class ServiceTester extends Activity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// To open up a gallery browser
		Intent intent = new Intent();
		intent.setType("image/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);
		startActivityForResult(Intent.createChooser(intent, "Select Picture"),1);
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
		if (resultCode == RESULT_OK) {
			if (requestCode == 1) {
				Uri currImageURI = data.getData();
				
				User user = new User();
				user.setUserId("152");
				user.setCode("ec1183c9075685aeadb8ac1e3605dc2a");

				City city = new City();
				city.setId("4");
				
				Object result = WebService.postPhoto(user, city, getRealPathFromURI(currImageURI), "test");
				Toast.makeText(this, result.toString(), Toast.LENGTH_LONG).show();
			}
		}
	}
}
