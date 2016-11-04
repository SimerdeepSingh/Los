package los.valiance.com.los.Adapters;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import los.valiance.com.los.Activity.MainActivity;
import los.valiance.com.los.Activity.MenuActivity;
import los.valiance.com.los.Activity.VerificationActivity;
import los.valiance.com.los.Fragments.VerificationFragment;
import los.valiance.com.los.Helper.Permission;
import los.valiance.com.los.Model.LeadDetails;
import los.valiance.com.los.Model.VerificationModel;
import los.valiance.com.los.R;
import los.valiance.com.los.Utils.ImageConverter;

import static los.valiance.com.los.Helper.Constants.IMAGE_SIZE;
import static los.valiance.com.los.Helper.Constants.verificationTable;
import static los.valiance.com.los.Utils.ImageConverter.base64method;

/**
 * Created by admin2 on 26-10-2016.
 */

public class VerificationAdapter  extends BaseAdapter implements Filterable {
    Context context;
    List<VerificationModel>verificationDetails;
    List<VerificationModel> mStringFilterList;
    TextView loanId;
    AlertDialog.Builder builder;
    ExpandableListView exView;
    ArrayList<String>groupList=new ArrayList<>();
    public List<VerificationModel> getVerificationDetails() {
        return verificationDetails;
    }

    public void setVerificationDetails(List<VerificationModel> verificationDetails) {
        this.verificationDetails = verificationDetails;
    }

    Button Verification;

    //ValueFilter valueFilter;
    ImageButton uploadImage;
    boolean result_camera;
    String currentPhotoPath;
    private static final int CAMERA_REQUEST = 1888;

    public VerificationAdapter(Context context, List<VerificationModel> leadDetails) {

        this.verificationDetails=leadDetails;
      //  this.mStringFilterList = leadDetails;
        this.context=context;

    }

    @Override
    public int getCount() {
        return verificationDetails.size();
    }

    @Override
    public Object getItem(int position) {
        return verificationDetails.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    @Override
    public int getViewTypeCount() {
        return getCount();
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.verifyleads, null);
        }

        loanId= (TextView) view.findViewById(R.id.loanid);
        loanId.setText(String.valueOf(verificationDetails.get(position).getLeadId()));
        Verification= (Button) view.findViewById(R.id.VerifyAddress);
       /* exView= (ExpandableListView) view.findViewById(R.id.verification);

      ArrayList<VerificationModel> verificationDetails1=new ArrayList<>();
        VerificationModel newLead=new VerificationModel();
        newLead.setLeadId(123);
      *//*  VerificationModel newLead1=new VerificationModel();
        newLead.setLeadId(345);
        VerificationModel newLead2=new VerificationModel();
        newLead.setLeadId(35);*//*
        verificationDetails1.add(newLead);
       *//* verificationDetails1.add(newLead1);
        verificationDetails1.add(newLead2);*//*
        createGroupList();
         expandadapter expListAdapter = new expandadapter(
                context, groupList,verificationDetails1);
        exView.setAdapter(expListAdapter);*/

        Verification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               View dialogview = View.inflate(context, R.layout.addressverifcation, null);


                uploadImage= (ImageButton) dialogview.findViewById(R.id.upload_image1);
                if(verificationDetails.get(position).getImage1()!=null)
                {
                   uploadImage.setImageBitmap(verificationDetails.get(position).getImage1());
                }
                uploadImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        result_camera = Permission.checkPermissioncamera(context);
                        if(result_camera)

                            selectImage(verificationDetails,position);
                    }
                });
                builder = new AlertDialog.Builder(context);
                builder.setTitle("Verification");
                //builder.set
                builder.setView(dialogview)
                        .setCancelable(false)
                        .setPositiveButton("Save", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                dialog.dismiss();

                            }
                        }).show();
                Toast.makeText(context,"clicked",Toast.LENGTH_SHORT).show();
            }
        });



        return view;
    }
    private void createGroupList() {
        groupList = new ArrayList<String>();
        groupList.add("Address Verification");
        groupList.add("KYC");
        groupList.add("Office Verifcation");

    }
    /*@Override
    public Filter getFilter() {
        if (valueFilter == null) {
            valueFilter = new ValueFilter();
        }
        return valueFilter;
    }

    private class ValueFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint != null && constraint.length() > 0) {
                ArrayList<LeadDetails> filterList = new ArrayList<>();
                for (int i = 0; i < mStringFilterList.size(); i++) {
                    if ((mStringFilterList.get(i).getFirstName().toUpperCase())
                            .contains(constraint.toString().toUpperCase()) || String.valueOf(mStringFilterList.get(i).getLeadId()).contains(constraint.toString())) {

                        filterList.add(mStringFilterList.get(i));
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
                Log.i("asasas", String.valueOf(results.values));
            } else {
                results.count = mStringFilterList.size();
                results.values = mStringFilterList;
            }
            return results;

        }

        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            leadDetails = (ArrayList<LeadDetails>) results.values;
            if (leadDetails.size() != 0)
                notifyDataSetChanged();
        }
    }*/

    private void selectImage(List<VerificationModel> verificationDetails, int leadId) {
      //  Log.i("reached123",ex.getLocalizedMessage());
       // cameraIntent();
        ((VerificationActivity) context).setImage(this);

       /* final CharSequence[] options = {"Take Photo", "Choose from Library", "Cancel"};
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("Take Photo")) {
                    userChoosenTask = "Take Photo";
                    if (result_camera)
                        cameraIntent();
                } else if (options[item].equals("Choose from Library")) {
                    userChoosenTask = "Choose from Library";
                    if (result_gallery)
                        galleryIntent();
                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();*/
    }

    private void cameraIntent() {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        ((Activity) context).startActivityForResult(cameraIntent, CAMERA_REQUEST);
        /*Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File photoFile = null;
        try {
            photoFile = createImageFile();
        } catch (IOException ex) {
            Log.i("reached123",ex.getLocalizedMessage());
            // Error occurred while creating the File
        }
        // Continue only if the File was successfully created
        if (photoFile != null) {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));

            // cameraIntent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
            intent.putExtra(MediaStore.EXTRA_SIZE_LIMIT, IMAGE_SIZE);
            Log.i("reached123","DFDFDfd");
            ((Activity) context).startActivityForResult(intent, 1);

        }*/


    }

public void updateImage()
{

}




    private File createImageFile() throws IOException {
        // Create an image file name
        Log.i("reached123","here");
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        if (!storageDir.exists()) {
            storageDir.mkdirs();
        }
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = "file:" + image.getAbsolutePath();
        return image;
    }


    private Uri setImageRes(final Uri image) {
        //final Uri image = Uri.parse(currentPhotoPath);
        //Toast.makeText(this, "****************" + image.getPath(), Toast.LENGTH_SHORT).show();
        ImageConverter.resizeImageMemory(context.getApplicationContext(), image.getPath(), image.getPath());

                    Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), image);
            uploadImage.setImageBitmap(bitmap);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        return image;
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    public void refreshLayout() {
        notifyDataSetChanged();
    }
}
