package com.example.chatapp.ui.mesajOlustur;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class mesajOlusturViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public mesajOlusturViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}