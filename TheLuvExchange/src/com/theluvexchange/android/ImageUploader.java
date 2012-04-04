package com.theluvexchange.android;
import java.io.BufferedReader;

import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
 

import org.apache.http.HttpResponse;

import org.apache.http.client.HttpClient;

import org.apache.http.client.methods.HttpPost;

/*import org.apache.http.entity.mime.HttpMultipartMode;

import org.apache.http.entity.mime.MultipartEntity;

import org.apache.http.entity.mime.content.ByteArrayBody;

import org.apache.http.entity.mime.content.StringBody;*/
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;

import org.apache.http.protocol.HttpContext;

import org.json.JSONObject;

 

import android.app.Activity;

import android.app.ProgressDialog;

import android.content.Intent;

import android.database.Cursor;

import android.graphics.Bitmap;

import android.graphics.Bitmap.CompressFormat;

import android.graphics.BitmapFactory;

import android.net.Uri;

import android.os.AsyncTask;

import android.os.Bundle;

import android.provider.MediaStore;

import android.util.Log;

import android.view.KeyEvent;

import android.view.Menu;

import android.view.MenuInflater;

import android.view.MenuItem;

import android.view.View;

import android.widget.Button;

import android.widget.EditText;

import android.widget.ImageView;

import android.widget.Toast;

 

public class ImageUploader extends Activity {

    private static final int PICK_IMAGE = 1;

    private ImageView imgView;

    private Button upload;

    private EditText caption;

    private Bitmap bitmap;

    private ProgressDialog dialog;

 

    /** Called when the activity is first created. */

    @Override

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.image_uploader);

// 
//        imgView = (ImageView) findViewById(R.id.ImageView);
//
//        upload = (Button) findViewById(R.id.Upload);
//
//        caption = (EditText) findViewById(R.id.Caption);
//
//        upload.setOnClickListener(new View.OnClickListener() {
//
// 
//
//            public void onClick(View v) {
//
//                if (bitmap == null) {
//
//                   Toast.makeText(getApplicationContext(),
//
//                            "Please select image", Toast.LENGTH_SHORT).show();
//
//                } else {
//
//                    dialog = ProgressDialog.show(ImageUploader.this, "Uploading",
//
//                            "Please wait...", true);
//
//                    new ImageUploadTask().execute();
//
//                }
//
//            }
//
//        });
//
// 
//
//    }
//
// 
//
//    @Override
//
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//        MenuInflater inflater = getMenuInflater();
//
//        inflater.inflate(R.menu.imageupload_menu, menu);
//
//        return true;
//
//    }
//
// 
//
//    @Override
//
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        // Handle item selection
//
//        switch (item.getItemId()) {
//
//        case R.id.gallery:
//
//            try {
//
//                Intent intent = new Intent();
//
//                intent.setType("image/*");
//
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//
//                startActivityForResult(
//
//                        Intent.createChooser(intent, "Select Picture"),
//
//                        PICK_IMAGE);
//
//            } catch (Exception e) {
//
//                Toast.makeText(getApplicationContext(),
//
//                        getString(R.string.exception_message),
//
//                        Toast.LENGTH_LONG).show();
//
//                Log.e(e.getClass().getName(), e.getMessage(), e);
//
//            }
//
//            return true;
//
//        default:
//
//            return super.onOptionsItemSelected(item);
//
//        }
//
//    }
//
// 
//
//    @Override
//
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//
//        switch (requestCode) {
//
//        case PICK_IMAGE:
//
//            if (resultCode == Activity.RESULT_OK) {
//
//                Uri selectedImageUri = data.getData();
//
//                String filePath = null;
//
// 
//
//                try {
//
//                    // OI FILE Manager
//
//                    String filemanagerstring = selectedImageUri.getPath();
//
// 
//
//                    // MEDIA GALLERY
//
//                    String selectedImagePath = getPath(selectedImageUri);
//
// 
//
//                    if (selectedImagePath != null) {
//
//                        filePath = selectedImagePath;
//
//                    } else if (filemanagerstring != null) {
//
//                        filePath = filemanagerstring;
//
//                    } else {
//
//                        Toast.makeText(getApplicationContext(), "Unknown path",
//
//                                Toast.LENGTH_LONG).show();
//                        Log.e("Bitmap", "Unknown path");
//
//                    }
//
// 
//
//                    if (filePath != null) {
//
//                        decodeFile(filePath);
//
//                    } else {
//
//                        bitmap = null;
//                    }
//
//                } catch (Exception e) {
//
//                    Toast.makeText(getApplicationContext(), "Internal error",
//
//                            Toast.LENGTH_LONG).show();
//
//                    Log.e(e.getClass().getName(), e.getMessage(), e);
//
//                }
//
//            }
//
//            break;
//
//        default:
//
//        }
//
//    }
//
//
//
//    class ImageUploadTask extends AsyncTask <Void, Void, String>{
//
//        @Override
//
//        protected String doInBackground(Void... unsued) {
//
//            try {
//
//                HttpClient httpClient = new DefaultHttpClient();
//
//                HttpContext localContext = new BasicHttpContext();
//
//                HttpPost httpPost = new HttpPost(
//
//                        getString(R.string.WebServiceURL)
//
//                                + "/cfc/iphonewebservice.cfc?method=uploadPhoto");
//
// 
//
//                MultipartEntity entity = new MultipartEntity(
//
//                        HttpMultipartMode.BROWSER_COMPATIBLE);
//
// 
//
//                ByteArrayOutputStream bos = new ByteArrayOutputStream();
//
//                bitmap.compress(CompressFormat.JPEG, 100, bos);
//
//                byte[] data = bos.toByteArray();
//
//                entity.addPart("photoId", new StringBody(getIntent()
//
//                        .getStringExtra("photoId")));
//
//                entity.addPart("returnformat", new StringBody("json"));
//
//                entity.addPart("uploaded", new ByteArrayBody(data,
//
//                        "myImage.jpg"));
//
//                entity.addPart("photoCaption", new StringBody(caption.getText()
//
//                        .toString()));
//
//                httpPost.setEntity(entity);
//
//               HttpResponse response = httpClient.execute(httpPost,
//
//                       localContext);
//
//               BufferedReader reader = new BufferedReader(
//
//                        new InputStreamReader(
//
//                                response.getEntity().getContent(), "UTF-8"));
//
// 
//
//                String sResponse = reader.readLine();
//
//                return sResponse;
//
//            } catch (Exception e) {
//
//                if (dialog.isShowing())
//
//                    dialog.dismiss();
//
//                Toast.makeText(getApplicationContext(),
//
//                        getString(R.string.exception_message),
//
//                        Toast.LENGTH_LONG).show();
//
//                Log.e(e.getClass().getName(), e.getMessage(), e);
//
//                return null;
//
//            }
//
// 
//
//            // (null);
//
//        }
//
// 
//
//        @Override
//
//        protected void onProgressUpdate(Void... unsued) {
//
// 
//
//        }
//
// 
//
//        @Override
//
//        protected void onPostExecute(String sResponse) {
//
//           try {
//
//               if (dialog.isShowing())
//
//                    dialog.dismiss();
//
//
//
//                if (sResponse != null) {
//
//                    JSONObject JResponse = new JSONObject(sResponse);
//
//                    int success = JResponse.getInt("SUCCESS");
//
//                    String message = JResponse.getString("MESSAGE");
//
//                    if (success == 0) {
//
//                        Toast.makeText(getApplicationContext(), message,
//
//                                Toast.LENGTH_LONG).show();
//
//                    } else {
//
//                        Toast.makeText(getApplicationContext(),
//
//                                "Photo uploaded successfully",
//
//                                Toast.LENGTH_SHORT).show();
//
//                       caption.setText("");
//
//                    }
//
//                }
//
//            } catch (Exception e) {
//
//                Toast.makeText(getApplicationContext(),
//
//                        getString(R.string.exception_message),
//
//                        Toast.LENGTH_LONG).show();
//
//                Log.e(e.getClass().getName(), e.getMessage(), e);
//
//            }
//
//        }
//
//    }
//
// 
//
//    public String getPath(Uri uri) {
//
//        String[] projection = { MediaStore.Images.Media.DATA };
//
//        Cursor cursor = managedQuery(uri, projection, null, null, null);
//
//        if (cursor != null) {
//
//            // HERE YOU WILL GET A NULLPOINTER IF CURSOR IS NULL
//
//            // THIS CAN BE, IF YOU USED OI FILE MANAGER FOR PICKING THE MEDIA
//
//            int column_index = cursor
//
//                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//
//            cursor.moveToFirst();
//
//            return cursor.getString(column_index);
//
//        } else
//
//            return null;
//
//    }
//
//
//
//    public void decodeFile(String filePath) {
//
//        // Decode image size
//
//       BitmapFactory.Options o = new BitmapFactory.Options();
//
//       o.inJustDecodeBounds = true;
//
//        BitmapFactory.decodeFile(filePath, o);
//
// 
//
//        // The new size we want to scale to
//
//        final int REQUIRED_SIZE = 1024;
//
// 
//
//        // Find the correct scale value. It should be the power of 2.
//
//       int width_tmp = o.outWidth, height_tmp = o.outHeight;
//
//        int scale = 1;
//
//        while (true) {
//
//           if (width_tmp &lt; REQUIRED_SIZE &amp;&amp; height_tmp &lt; REQUIRED_SIZE)
//
//                break;
//
//            width_tmp /= 2;
//
//            height_tmp /= 2;
//
//            scale *= 2;
//
//        }
//
// 
//
//        // Decode with inSampleSize
//
//        BitmapFactory.Options o2 = new BitmapFactory.Options();
//
//        o2.inSampleSize = scale;
//
//        bitmap = BitmapFactory.decodeFile(filePath, o2);
//
// 
//
//        imgView.setImageBitmap(bitmap);

 

    }

}


/*import java.io.File;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

public class ImageUploader extends Activity {
    private Uri mImageCaptureUri;
    private ImageView mImageView;
 
    private static final int PICK_FROM_CAMERA = 1;
    private static final int PICK_FROM_FILE = 2;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
        setContentView(R.layout.main);
 
        final String [] items           = new String [] {"From Camera", "From SD Card"};
        ArrayAdapter<String> adapter  = new ArrayAdapter<String> (this, android.R.layout.select_dialog_item,items);
        AlertDialog.Builder builder     = new AlertDialog.Builder(this);
 
        builder.setTitle("Select Image");
        builder.setAdapter( adapter, new DialogInterface.OnClickListener() {
            public void onClick( DialogInterface dialog, int item ) {
                if (item == 0) {
                    Intent intent    = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File file        = new File(Environment.getExternalStorageDirectory(),
                                        "tmp_avatar_" + String.valueOf(System.currentTimeMillis()) + ".jpg");
                    mImageCaptureUri = Uri.fromFile(file);
 
                    try {
                        intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
                        intent.putExtra("return-data", true);
 
                        startActivityForResult(intent, PICK_FROM_CAMERA);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
 
                    dialog.cancel();
                } else {
                    Intent intent = new Intent();
 
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
 
                    startActivityForResult(Intent.createChooser(intent, "Complete action using"), PICK_FROM_FILE);
                }
            }
        } );
 
        final AlertDialog dialog = builder.create();
 
        mImageView = (ImageView) findViewById(R.id.iv_pic);
 
        ((Button) findViewById(R.id.btn_choose)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });
    }
 
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) return;
 
        Bitmap bitmap   = null;
        String path     = "";
 
        if (requestCode == PICK_FROM_FILE) {
            mImageCaptureUri = data.getData();
            path = getRealPathFromURI(mImageCaptureUri); //from Gallery
 
            if (path == null)
                path = mImageCaptureUri.getPath(); //from File Manager
 
            if (path != null)
                bitmap  = BitmapFactory.decodeFile(path);
        } else {
            path    = mImageCaptureUri.getPath();
            bitmap  = BitmapFactory.decodeFile(path);
        }
 
        mImageView.setImageBitmap(bitmap);
    }
 
    public String getRealPathFromURI(Uri contentUri) {
        String [] proj      = {MediaStore.Images.Media.DATA};
        Cursor cursor       = managedQuery( contentUri, proj, null, null,null);
 
        if (cursor == null) return null;
 
        int column_index    = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
 
        cursor.moveToFirst();
 
        return cursor.getString(column_index);
    }
}*/
