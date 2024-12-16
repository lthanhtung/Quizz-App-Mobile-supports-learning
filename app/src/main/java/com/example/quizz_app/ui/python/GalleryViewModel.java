package com.example.quizz_app.ui.python;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GalleryViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public GalleryViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Các dạng câu hỏi về ngôn ngữ python");
    }

    public LiveData<String> getText() {
        return mText;
    }
}