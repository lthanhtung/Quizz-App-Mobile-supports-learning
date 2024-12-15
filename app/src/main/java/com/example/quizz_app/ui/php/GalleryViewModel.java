package com.example.quizz_app.ui.php;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GalleryViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public GalleryViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Các câu hỏi về ngôn ngữ php");
    }

    public LiveData<String> getText() {
        return mText;
    }
}