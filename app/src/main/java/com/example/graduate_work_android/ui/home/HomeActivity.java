package com.example.graduate_work_android.ui.home;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.graduate_work_android.R;
import com.example.graduate_work_android.databinding.ActivityHomeBinding;
import com.example.graduate_work_android.ui.home.allergic.AllergicFragment;
import com.example.graduate_work_android.ui.home.photo.PhotoFragment;
import com.example.graduate_work_android.ui.home.supplement.SupplementFragment;

public class HomeActivity extends AppCompatActivity {
    private String state = "home";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityHomeBinding binding = DataBindingUtil.setContentView(this,
                R.layout.activity_home);
        HomeViewModel viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setViewModel(viewModel);
        binding.getRoot();
        openFragment(new PhotoFragment());

        //        viewModel.image.observe(this, s -> {
//            byte[] bytes = Base64.getDecoder().decode(s);
//            Bitmap bm = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
//            binding.photo.setImageBitmap(bm);
//        });

        binding.bottomNavigation.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    if (!state.equals("home")) {
                        openFragment(new PhotoFragment());
                        state = "home";
                    }
                    return true;
                case R.id.navigation_history:
                    if (!state.equals("history")) {
                        openFragment(new PhotoFragment());
                        state = "history";
                    }
                    return true;
                case R.id.navigation_allergic:
                    if (!state.equals("allergic")) {
                        openFragment(new AllergicFragment());
                        state = "allergic";
                    }
                    return true;

                case R.id.navigation_supplement:
                    if (!state.equals("supplement")) {
                        openFragment(new SupplementFragment());
                        state = "supplement";
                    }
                    return true;
            }
            return false;
        });
    }


    private void openFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit();
    }
}
