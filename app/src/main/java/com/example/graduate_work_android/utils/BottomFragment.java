package com.example.graduate_work_android.utils;


import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.graduate_work_android.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import butterknife.BindView;
import butterknife.ButterKnife;


public class BottomFragment extends BottomSheetDialogFragment {
    private ItemClickListener mListener;
    @BindView(R.id.picture)
    ImageButton picture;
    @BindView(R.id.camera)
    ImageButton camera;
    private boolean isClick = false;

    BottomFragment(ItemClickListener itemClickListener) {
        mListener = itemClickListener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.bottom_sheet_card, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        camera.setOnClickListener(view1 -> {
            mListener.onItemClick(camera.getTag().toString());
            isClick = true;
            dismiss();
        });
        picture.setOnClickListener(view1 -> {
            mListener.onItemClick(picture.getTag().toString());
            isClick = true;
            dismiss();
        });
    }

    public interface ItemClickListener {
        void onItemClick(String item);
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        if (!isClick)
            mListener.onItemClick("anything");
        super.onDismiss(dialog);

    }
}
