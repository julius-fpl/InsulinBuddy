package de.julius_fpl.insulin_buddy.ui.logbook;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LogbookViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public LogbookViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is zzz fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}