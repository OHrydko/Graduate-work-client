package com.example.graduate_work_android.ui.home.supplement;

import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.graduate_work_android.models.ResponseSupplement;
import com.example.graduate_work_android.models.RowType;
import com.example.graduate_work_android.repository.Repository;
import com.example.graduate_work_android.utils.callback.CallbackSupplement;

import java.util.ArrayList;
import java.util.List;

public class SupplementViewModel extends ViewModel implements CallbackSupplement {

    private FragmentActivity activity;
    private MutableLiveData<List<RowType>> supplement = new MutableLiveData<>();
    public MutableLiveData<Boolean> isLoader = new MutableLiveData<>(true);

    void init(FragmentActivity activity) {
        this.activity = activity;
        Repository repository = new Repository();
        repository.getSupplement(this);
    }

    @Override
    public void responseSupplement(ResponseSupplement response) {
        isLoader.postValue(false);
        if (response.isSuccess()) {
            supplement.postValue(new ArrayList<>(response.getSupplement()));
        }
    }

    @Override
    public void error(Throwable throwable) {
        isLoader.postValue(false);
        Toast.makeText(activity, "server error", Toast.LENGTH_SHORT).show();
    }

    public MutableLiveData<List<RowType>> getSupplement() {
        return supplement;
    }
}
