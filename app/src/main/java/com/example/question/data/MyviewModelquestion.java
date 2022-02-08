package com.example.question.data;
import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.question.model.Question;
import com.example.question.model.awardUser;

import java.util.ArrayList;
import java.util.List;
public  class MyviewModelquestion extends AndroidViewModel  {
    // اگر برای ویومدل خود کانتکس نیاز داشته باشیم از  androidviewmodel  ارث بری میکنیم و در غیر اینصورت از viewmodel ارث بری می کنیم
    private final LiveData<List<Question>> LVDListQuestion;
    private MutableLiveData<List<Question>> mutableLiveData;
    // ویو مدل به live data  نیاز دارد که اگر داده ما مثلا آرایه معمولی باشد باید آنرا با  mutableLivedata  تبدیل به  live data کنیم
    Repository repository;
    private  ArrayList<Question> arrayList;
    public MyviewModelquestion(@NonNull Application application) {
        super(application);
        LVDListQuestion=Repository.getInstance(application).getListLiveData();
    if (repository==null)repository=Repository.getInstance(application);
    }
    public LiveData<List<Question>>  getallData(){
        return LVDListQuestion;
    }
    public int getSocer(){return repository.getSocer(); }
    public int getCurrent(){return  repository.getCurrentQuestion();}
    public void upadateAward(awardUser awardUser){repository.UpdateAward(awardUser);}
    public List<Question> getList(){
        return repository.getArrayList();
    }
}
