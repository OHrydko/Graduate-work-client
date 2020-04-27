package com.example.graduate_work_android.ui.home;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.graduate_work_android.R;
import com.example.graduate_work_android.databinding.ActivityHomeBinding;
import com.example.graduate_work_android.ui.home.photo.PhotoFragment;

public class HomeActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
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
                    openFragment(new PhotoFragment());
                    return true;
                case R.id.navigation_history:
                    //openFragment(SmsFragment.newInstance("", ""));
                    return true;
                case R.id.navigation_allergic:
                    //openFragment(NotificationFragment.newInstance("", ""));
                    return true;

                case R.id.navigation_supplement:
                    //openFragment(NotificationFragment.newInstance("", ""));
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
