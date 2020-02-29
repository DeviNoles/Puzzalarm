package com.cop4020.puzzalarm.ui.timepicker;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TimePickerViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<String> mText;

    public TimePickerViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is time picker fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
