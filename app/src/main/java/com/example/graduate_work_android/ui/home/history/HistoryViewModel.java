package com.example.graduate_work_android.ui.home.history;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.graduate_work_android.models.History;
import com.example.graduate_work_android.models.ResponseHistory;
import com.example.graduate_work_android.repository.Repository;
import com.example.graduate_work_android.utils.callback.CallbackHistory;

import java.util.List;

public class HistoryViewModel extends ViewModel implements CallbackHistory {
    private FragmentActivity activity;
    public MutableLiveData<Boolean> isLoader = new MutableLiveData<>(true);
    private MutableLiveData<List<History>> histories = new MutableLiveData<>();

    void init(FragmentActivity activity) {
        this.activity = activity;
        Repository repository = new Repository();
        SharedPreferences sharedPreferences = activity.getSharedPreferences("User", Context.MODE_PRIVATE);
        repository.history(sharedPreferences.getString("mobileNumber", "000000000"),
                this);
    }

    @Override
    public void responseHistory(ResponseHistory responseHistory) {
        isLoader.postValue(false);
        if (responseHistory.isSuccess()) {
            histories.postValue(responseHistory.getHistories());
        }
    }

    @Override
    public void error(Throwable throwable) {
        isLoader.postValue(false);
        Toast.makeText(activity, "server error", Toast.LENGTH_SHORT).show();
    }

    MutableLiveData<List<History>> getHistories() {
        return histories;
    }
}
