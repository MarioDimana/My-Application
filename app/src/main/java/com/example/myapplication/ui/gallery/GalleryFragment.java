package com.example.myapplication.ui.gallery;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.MainActivity;
import com.example.myapplication.databinding.FragmentGalleryBinding;
import com.example.myapplication.general.Constants;

public class GalleryFragment extends Fragment {
    private Button cancelButton2;
    private FragmentGalleryBinding fgbinding2;
    private FragmentGalleryBinding binding;

    private GalleryFragment.ButtonClickListenerGallery2 buttonClickListenerGallery = new GalleryFragment.ButtonClickListenerGallery2();

    private class ButtonClickListenerGallery2 implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Log.d(Constants.TAG2, "ButtonClickListenerGallery2: button clicked");
            System.out.println("ButtonClickListenerGallery2: BUTTON CLICKED\n");
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        fgbinding2 = FragmentGalleryBinding.inflate(getLayoutInflater());
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textGallery;
        galleryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        cancelButton2 = fgbinding2.cancelButton;
        cancelButton2.setOnClickListener(buttonClickListenerGallery);

        System.out.println("FragmentGalleryBinding: onCreateView finished\n");
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}