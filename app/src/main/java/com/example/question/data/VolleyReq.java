package com.example.question.data;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.question.model.Question;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;


public class VolleyReq {
    private static VolleyReq volleyReq;
    private static final String url = "https://raw.githubusercontent.com/curiousily/simple-quiz/master/script/statements-data.json";
    private static RequestQueue requestQueue;// request queue برای اینکه درخواست را در آخر به آن اضافه کنیم
    private ArrayList<Question> questionArrayList = new ArrayList<>();
    private static Context cont;
//برای گرفتن اطلاعات از api  سرویس با استفاده از کتابخانه  volley  یک  request لازم است و یک  request qeue
    // اگر درخواست ما آرایه ای از جیسون باشد باید از json array request  استفاده کنیم
    private VolleyReq() {
    }

    public static synchronized VolleyReq getInstance(Context context) {
        if (volleyReq == null) {
            volleyReq = new VolleyReq();
        }
        cont = context;
        return volleyReq;
    }

    private synchronized <T> void getrequest(Request<T> req) {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(cont);
        }
        requestQueue.add(req);
    }

    public List<Question> getAllQuestion( callback callback) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, response -> {

            try {

                questionArrayList.clear();
                for (int i = 0; i < response.length(); i++) {
                    Question question = new Question(response.getJSONArray(i).getString(0), response.getJSONArray(i).getBoolean(1));

                    questionArrayList.add(question);
                }

                callback.listProvider(questionArrayList);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        getrequest(jsonArrayRequest);
        return questionArrayList;
    }

    public interface callback {


        void listProvider(List<Question> questionList);
    }
}
