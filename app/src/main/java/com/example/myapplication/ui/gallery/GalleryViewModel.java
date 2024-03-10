package com.example.myapplication.ui.gallery;

import android.util.Log;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.general.Constants;

public class GalleryViewModel extends ViewModel {
    private ButtonClickListenerGallery buttonClickListenerGallery = new ButtonClickListenerGallery();

    private class ButtonClickListenerGallery implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Log.d(Constants.TAG2, "ButtonClickListenerGallery: button clicked");
            System.out.println("ButtonClickListenerGallery: BUTTON CLICKED\n");
        }
    }
    private final MutableLiveData<String> mText;

    public GalleryViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}