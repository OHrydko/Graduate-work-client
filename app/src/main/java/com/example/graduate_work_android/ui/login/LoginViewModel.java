package com.example.graduate_work_android.ui.login;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.graduate_work_android.App;
import com.example.graduate_work_android.R;
import com.example.graduate_work_android.models.Login;
import com.example.graduate_work_android.ui.home.HomeActivity;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class LoginViewModel extends ViewModel {
    private FragmentActivity activity;
    public MutableLiveData<String> mobileNumber = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();
    private SharedPreferences sharedPreferences;
    public MutableLiveData<Boolean> isLoader = new MutableLiveData<>(false);

    void init(FragmentActivity activity) {
        this.activity = activity;
        sharedPreferences = activity.getSharedPreferences("User", Context.MODE_PRIVATE);
    }

    @SuppressLint("CheckResult")
    private void login(String mobileNumber, String passwords) {
        RequestBody mobile = RequestBody.create(mobileNumber, MediaType.parse("text/plain"));

        RequestBody password = RequestBody.create(passwords, MediaType.parse("text/plain"));
        App.getComponent().getApi().login(mobile, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::loginResult,
                        throwable ->
                                Log.d("throwable", throwable.getMessage() + ""));
    }

    private void loginResult(Login login) {
        isLoader.postValue(false);
        if (login.isSuccess()) {
            //sharedPreferences.edit().putBoolean("login", true).apply();
            activity.startActivity(new Intent(activity, HomeActivity.class));
        } else {
            Toast.makeText(activity, login.getText(), Toast.LENGTH_SHORT).show();
        }
    }

    public void clickButton() {
        if (mobileNumber.getValue() != null && password.getValue() != null) {
            isLoader.postValue(true);
            login(mobileNumber.getValue(), password.getValue());
        }
    }

    public void registration() {
        activity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLogin, new RegistrationFragment())
                .addToBackStack(null)
                .commit();
    }
}
