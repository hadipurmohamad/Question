package com.example.question.data;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.question.model.Question;
import com.example.question.model.awardUser;
import com.example.question.model.awardUserDao;

import java.util.ArrayList;
import java.util.List;

public class Repository {
    private static Repository repository;
    private LiveData<List<Question>> listLiveData;
    List<Question> myquestionList = new ArrayList<>();
    private static Context con;
    private static awardUserDao awardUserDao;
    private List<Question> arrayList;
    private final MutableLiveData<List<Question>> mutableLiveData = new MutableLiveData<>();
    private Repository() {
    }

    public static synchronized Repository getInstance(final Context context) {
        if (repository == null) repository = new Repository();
        if (con == null) con = context;
        if (awardUserDao == null) awardUserDao = dbawardUser.getInstance(con).awardUserDao();
        return repository;
    }

    public int getSocer() {
        return awardUserDao.getSocer();
    }

    public int getCurrentQuestion() {
        return awardUserDao.getCurrentQuestion();
    }

    public void UpdateAward(awardUser awardUser) {
        awardUserDao.updateAwardUser(awardUser);
    }

    public LiveData<List<Question>> getListLiveData() {
     VolleyReq.getInstance(con).getAllQuestion(mutableLiveData::setValue);
        return mutableLiveData;
    }
    public List<Question> getArrayList(){
        VolleyReq.getInstance(con).getAllQuestion(questionList -> {
            myquestionList.addAll(questionList);
        });
            return myquestionList;
    }
}
