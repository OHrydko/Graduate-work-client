package com.example.graduate_work_android.ui.registration;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.graduate_work_android.R;
import com.example.graduate_work_android.models.ResponseModel;
import com.example.graduate_work_android.repository.Repository;
import com.example.graduate_work_android.ui.home.HomeActivity;
import com.example.graduate_work_android.ui.login.LoginFragment;
import com.example.graduate_work_android.utils.callback.CallBackRegistration;

import java.util.Calendar;
import java.util.Date;

public class RegistrationViewModel extends ViewModel implements CallBackRegistration {

    public MutableLiveData<String> mobileNumber = new MutableLiveData<>();
    public MutableLiveData<String> textErrorNumber = new MutableLiveData<>();
    public MutableLiveData<Boolean> isErrorNumber = new MutableLiveData<>(false);

    public MutableLiveData<String> password = new MutableLiveData<>();
    public MutableLiveData<Boolean> isErrorPassword = new MutableLiveData<>(false);
    public MutableLiveData<String> textErrorPassword = new MutableLiveData<>();

    public MutableLiveData<String> confirmPassword = new MutableLiveData<>();
    public MutableLiveData<Boolean> isErrorConfirmPassword = new MutableLiveData<>(false);
    public MutableLiveData<String> textErrorConfirmPassword = new MutableLiveData<>();

    public MutableLiveData<String> birthday;
    public MutableLiveData<Boolean> isErrorBirthday = new MutableLiveData<>(false);
    public MutableLiveData<String> textErrorBirthday = new MutableLiveData<>();

    public MutableLiveData<String> name = new MutableLiveData<>();
    public MutableLiveData<Boolean> isErrorName = new MutableLiveData<>(false);
    public MutableLiveData<String> textErrorName = new MutableLiveData<>();

    public MutableLiveData<String> lastName = new MutableLiveData<>();
    public MutableLiveData<Boolean> isErrorLastName = new MutableLiveData<>(false);
    public MutableLiveData<String> textErrorLastName = new MutableLiveData<>();

    public MutableLiveData<String> userName = new MutableLiveData<>();
    public MutableLiveData<Boolean> isErrorUserName = new MutableLiveData<>(false);
    public MutableLiveData<String> textErrorUserName = new MutableLiveData<>();

    public MutableLiveData<Boolean> isLoader = new MutableLiveData<>(false);
    private SharedPreferences sharedPreferences;
    private FragmentActivity activity;
    private Repository repository;
    private Calendar calendar = Calendar.getInstance();

    void init(FragmentActivity activity) {
        this.activity = activity;
        birthday = new MutableLiveData<>(activity.getResources().getString(R.string.birthday));
        sharedPreferences = activity.getSharedPreferences("User", Context.MODE_PRIVATE);
        repository = new Repository();
    }

    public void clickButton() {
        if (checkFields()) {
            isLoader.postValue(true);
            if (birthday.getValue() != null)
                repository.registration(mobileNumber.getValue(), password.getValue(), name.getValue(),
                        lastName.getValue(), birthday.getValue(), userName.getValue(), this);
        }
    }

    private boolean checkFields() {
        if (mobileNumber.getValue() == null || mobileNumber.getValue().length() != 10) {
            isErrorNumber.postValue(true);
            textErrorNumber.postValue(activity.getResources().getString(R.string.errorMobilePhone));
            return false;
        } else if (password.getValue() == null || password.getValue().isEmpty()) {
            isErrorPassword.postValue(true);
            textErrorPassword.postValue(activity.getResources().getString(R.string.required_field));
            return false;
        } else if (confirmPassword.getValue() == null || confirmPassword.getValue().isEmpty()) {
            isErrorConfirmPassword.postValue(true);
            textErrorConfirmPassword.postValue(activity.getResources().getString(R.string.required_field));
            return false;
        } else if (birthday.getValue() != null &&
                birthday.getValue().equals(activity.getResources().getString(R.string.birthday))) {
            textErrorBirthday.postValue(activity.getResources().getString(R.string.required_field));
            isErrorBirthday.postValue(true);
            return false;
        } else if (!confirmPassword.getValue().equals(password.getValue())) {
            isErrorPassword.postValue(true);
            textErrorPassword.postValue(activity.getResources().getString(R.string.did_not_macth));
            return false;
        } else if (name.getValue() == null || name.getValue().isEmpty()) {
            textErrorName.postValue(activity.getResources().getString(R.string.required_field));
            isErrorName.postValue(true);
            return false;
        } else if (lastName.getValue() == null || lastName.getValue().isEmpty()) {
            textErrorLastName.postValue(activity.getResources().getString(R.string.required_field));
            isErrorLastName.postValue(true);
            return false;
        } else if (userName.getValue() == null || userName.getValue().isEmpty()) {
            textErrorUserName.postValue(activity.getResources().getString(R.string.required_field));
            isErrorUserName.postValue(true);
            return false;
        }
        return true;

    }


    public void login() {
        activity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLogin, new LoginFragment())
                .addToBackStack(null)
                .commit();
    }

    public TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            isErrorNumber.postValue(false);
            isErrorBirthday.postValue(false);
            isErrorConfirmPassword.postValue(false);
            isErrorLastName.postValue(false);
            isErrorName.postValue(false);
            isErrorPassword.postValue(false);
            isErrorUserName.postValue(false);
            textErrorNumber.postValue(null);
            textErrorBirthday.postValue(null);
            textErrorConfirmPassword.postValue(null);
            textErrorLastName.postValue(null);
            textErrorName.postValue(null);
            textErrorPassword.postValue(null);
            textErrorUserName.postValue(null);
        }
    };


    public void clickDate() {

        int maxDay = calendar.get(Calendar.DAY_OF_MONTH);
        int maxMonth = calendar.get(Calendar.MONTH);
        int maxYear = calendar.get(Calendar.YEAR);

        @SuppressLint("DefaultLocale") DatePickerDialog datePickerDialog = new DatePickerDialog(activity,
                (view, year, monthOfYear, dayOfMonth) -> {
                    int month = monthOfYear + 1;
                    isErrorBirthday.setValue(false);
                    textErrorBirthday.setValue(null);
                    birthday.setValue(String.format("%d - %d - %d", dayOfMonth, month, year));
                },
                maxYear, maxMonth, maxDay);
        datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());
        datePickerDialog.show();

    }

    @Override
    public void response(ResponseModel responseModel) {
        isLoader.postValue(false);
        if (responseModel.isSuccess()) {
            sharedPreferences.edit().putString("mobileNumber",
                    responseModel.getMobile_number()).apply();
            activity.startActivity(new Intent(activity, HomeActivity.class));
        } else {
            Toast.makeText(activity, responseModel.getText(), Toast.LENGTH_SHORT).show();
        }
        Log.d("response", responseModel.getStatus() + "");
    }
}
