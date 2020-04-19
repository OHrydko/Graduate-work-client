package com.example.graduate_work_android.ui.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.graduate_work_android.R;
import com.example.graduate_work_android.models.ResponseModel;
import com.example.graduate_work_android.repository.Repository;
import com.example.graduate_work_android.ui.home.HomeActivity;
import com.example.graduate_work_android.ui.registration.RegistrationFragment;
import com.example.graduate_work_android.utils.callback.CallBackLogin;

public class LoginViewModel extends ViewModel implements CallBackLogin {
    private FragmentActivity activity;
    public MutableLiveData<String> mobileNumber = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();
    private SharedPreferences sharedPreferences;
    public MutableLiveData<Boolean> isLoader = new MutableLiveData<>(false);
    private Repository repository;

    void init(FragmentActivity activity) {
        this.activity = activity;
        sharedPreferences = activity.getSharedPreferences("User", Context.MODE_PRIVATE);
        repository = new Repository();
    }


    public void clickButton() {
        if (mobileNumber.getValue() != null && password.getValue() != null) {
            isLoader.postValue(true);
            repository.login(mobileNumber.getValue(), password.getValue(), this);
        }
    }

    public void registration() {
        activity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLogin, new RegistrationFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void response(ResponseModel responseModel) {
        isLoader.postValue(false);
        if (responseModel.isSuccess()) {
            sharedPreferences.edit().putBoolean("login", true).apply();
            sharedPreferences.edit().putString("mobileNumber", responseModel.getMobile_number()).apply();
            activity.startActivity(new Intent(activity, HomeActivity.class));
        } else {
            Toast.makeText(activity, responseModel.getText(), Toast.LENGTH_SHORT).show();
        }
    }
}
