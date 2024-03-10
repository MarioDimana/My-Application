package com.example.myapplication;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.myapplication.general.Constants;
import com.example.myapplication.ui.gallery.GalleryFragment;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.databinding.ActivityMainBinding;
import com.example.myapplication.databinding.FragmentGalleryBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private FragmentGalleryBinding fgbinding;

    private EditText nameEditText;
    private EditText phoneEditText;
    private EditText emailEditText;
    private EditText addressEditText;
    private EditText jobTitleEditText;
    private EditText companyEditText;
    private EditText websiteEditText;
    private EditText imEditText;

    private Button showHideAdditionalFieldsButton;
    private Button saveButton;
    private Button cancelButton;
    private LinearLayout additionalFieldsContainer;

    private ButtonClickListener buttonClickListener = new ButtonClickListener();

    private ButtonClickListenerGallery buttonClickListenerGallery = new ButtonClickListenerGallery();

    private class ButtonClickListenerGallery implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Log.d(Constants.TAG2, "ButtonClickListenerGallery: button clicked");
            System.out.println("ButtonClickListenerGallery: BUTTON CLICKED\n");
        }
    }

    private class ButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            int viewId = view.getId();
            Log.d(Constants.TAG2, "button clicked");
            System.out.println("BUTTON CLICKED\n");
            if (viewId == R.id.show_hide_additional_fields) {
                switch (additionalFieldsContainer.getVisibility()) {
                    case View.VISIBLE:
                        showHideAdditionalFieldsButton.setText(getResources().getString(R.string.show_additional_fields));
                        additionalFieldsContainer.setVisibility(View.INVISIBLE);
                        break;
                    case View.INVISIBLE:
                        showHideAdditionalFieldsButton.setText(getResources().getString(R.string.hide_additional_fields));
                        additionalFieldsContainer.setVisibility(View.VISIBLE);
                        break;
                }
            } else if (viewId == R.id.save_button) {
                Log.d(Constants.TAG2, "save button preset");
                String name = nameEditText.getText().toString();
                String phone = phoneEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String address = addressEditText.getText().toString();
                String jobTitle = jobTitleEditText.getText().toString();
                String company = companyEditText.getText().toString();
                String website = websiteEditText.getText().toString();
                String im = imEditText.getText().toString();

                Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
                intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
                if (name != null) {
                    intent.putExtra(ContactsContract.Intents.Insert.NAME, name);
                }
                if (phone != null) {
                    intent.putExtra(ContactsContract.Intents.Insert.PHONE, phone);
                }
                if (email != null) {
                    intent.putExtra(ContactsContract.Intents.Insert.EMAIL, email);
                }
                if (address != null) {
                    intent.putExtra(ContactsContract.Intents.Insert.POSTAL, address);
                }
                if (jobTitle != null) {
                    intent.putExtra(ContactsContract.Intents.Insert.JOB_TITLE, jobTitle);
                }
                if (company != null) {
                    intent.putExtra(ContactsContract.Intents.Insert.COMPANY, company);
                }
                ArrayList<ContentValues> contactData = new ArrayList<>();
                if (website != null) {
                    ContentValues websiteRow = new ContentValues();
                    websiteRow.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Website.CONTENT_ITEM_TYPE);
                    websiteRow.put(ContactsContract.CommonDataKinds.Website.URL, website);
                    contactData.add(websiteRow);
                }
                if (im != null) {
                    ContentValues imRow = new ContentValues();
                    imRow.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Im.CONTENT_ITEM_TYPE);
                    imRow.put(ContactsContract.CommonDataKinds.Im.DATA, im);
                    contactData.add(imRow);
                }
                intent.putParcelableArrayListExtra(ContactsContract.Intents.Insert.DATA, contactData);
                startActivityForResult(intent, Constants.CONTACTS_MANAGER_REQUEST_CODE);
            } else if (viewId == R.id.cancel_button) {
                setResult(Activity.RESULT_CANCELED, new Intent());
                finish();
            }
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Log.d(Constants.TAG2, "onCreate method was invoked");

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        fgbinding = FragmentGalleryBinding.inflate(getLayoutInflater());


        setContentView(binding.getRoot());


        setSupportActionBar(binding.appBarMain.toolbar);

        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null)
                        .setAnchorView(R.id.fab).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        nameEditText = fgbinding.nameEditText;

        phoneEditText = fgbinding.phoneNumberEditText;
        emailEditText = fgbinding.emailEditText;
        addressEditText = fgbinding.addressEditText;
        jobTitleEditText = fgbinding.jobTitleEditText;
        companyEditText = fgbinding.companyEditText;
        websiteEditText = fgbinding.websiteEditText;
        imEditText = fgbinding.imEditText;

        showHideAdditionalFieldsButton = fgbinding.showHideAdditionalFields;
        showHideAdditionalFieldsButton.setOnClickListener(buttonClickListenerGallery);
        saveButton = fgbinding.saveButton;
        saveButton.setOnClickListener(buttonClickListenerGallery);
        cancelButton = fgbinding.cancelButton;
        cancelButton.setOnClickListener(buttonClickListenerGallery);

        additionalFieldsContainer = fgbinding.additionalFieldsContainer;


        Intent intent = getIntent();
        if (intent != null) {
            String phone = intent.getStringExtra("ro.pub.cs.systems.eim.lab04.contactsmanager.PHONE_NUMBER_KEY");
            if (phone != null) {
                phoneEditText.setText(phone);
            } else {
                Toast.makeText(this, getResources().getString(R.string.phone_error), Toast.LENGTH_LONG).show();
            }
        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


}