package com.example.question.view;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.annotation.SuppressLint;
import android.app.Application;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.question.R;
import com.example.question.data.MyviewModelquestion;
import com.example.question.data.Repository;
import com.example.question.data.VolleyReq;
import com.example.question.model.Question;
import com.example.question.model.awardUser;

import java.util.ArrayList;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class QuestionFragment extends Fragment implements View.OnClickListener,Animator.AnimatorListener {
    private static final int USER_ID = 1;
    private MyviewModelquestion myviewModelquestion;
    private Animator Fade;
    private List<Question> arrayList=new ArrayList<>();
    private Boolean Res, ResQuestion;
    private Boolean setcountQuestion=true;
    private int postion, Soccer,soundCorrect,soundIncorrect;
    public static final String Socer = "Socer : ";
    public static final String Count = "Qeustion : ";
    public static int allQuestionCount;
    public static final String Mom = " / ";
    private AudioAttributes audioAttributes = new AudioAttributes.Builder()
            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
            .setUsage(AudioAttributes.USAGE_ALARM)
            .build();
    private SoundPool soundPool = new SoundPool.Builder().setAudioAttributes(audioAttributes).build();
    private View view;
    private Button Yes, No, Next, Previous;
    private TextView showQuestion, resualtMessage, socerview, questionCount;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         final Application application= getActivity().getApplication();
        view = inflater.inflate(R.layout.question_org, container, false);
        myviewModelquestion = new ViewModelProvider.AndroidViewModelFactory(application).create(MyviewModelquestion.class);
           myviewModelquestion.getallData().observe(getViewLifecycleOwner(), questionList -> {
              myviewModelquestion.getallData().removeObservers(getViewLifecycleOwner());
arrayList.addAll(questionList);
  init(view);
           });


        return view;
    }
    @SuppressLint("SetTextI18n")
    private void setValueContent(int postion, int socer) {
        showQuestion.setText(arrayList.get(postion).getQuestion());
        socerview.setText(Socer + socer);
        questionCount.setText(Count + (postion + 1) + Mom + allQuestionCount);
        allQuestionCount = arrayList.size();
        ResQuestion = arrayList.get(postion).isResualt();
    }
    private void init(View view) {
       postion = myviewModelquestion.getCurrent();
        allQuestionCount = arrayList.size();
        Soccer = myviewModelquestion.getSocer();
        showQuestion = view.findViewById(R.id.content_question);
        questionCount = view.findViewById(R.id.count_question);
        socerview = view.findViewById(R.id.score);
        resualtMessage = view.findViewById(R.id.message_resualt);
        Yes = view.findViewById(R.id.btn_res_question_true);
        No = view.findViewById(R.id.btn_res_question_false);
        Next = view.findViewById(R.id.btn_next);
        Previous = view.findViewById(R.id.btn_previous);
        Yes.setOnClickListener(this);
        No.setOnClickListener(this);
        Next.setOnClickListener(this);
        Previous.setOnClickListener(this);
        soundCorrect=soundPool.load(getContext(),R.raw.correct,1);
        soundIncorrect=soundPool.load(getContext(),R.raw.incorrect,1);

        setValueContent(postion, Soccer);
    }
    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_next:
                getNextQuestion();
                break;
            case R.id.btn_previous:
                getPreviuosQuestion();
                break;
            case R.id.btn_res_question_true:
                trueResualt();
                break;
            case R.id.btn_res_question_false:
                falseResualt();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }
    }
    private void falseResualt() {
        fadeAnimation(false);
    }
    private void trueResualt() {
        fadeAnimation(true);
    }
    private void getPreviuosQuestion() {
        postion = (postion > 0) ? (postion - 1) % allQuestionCount : allQuestionCount - 1;
        setValueContent(postion,Soccer);
        defualtSetTextandColor();
    }
    private void getNextQuestion() {
        postion = (postion + 1) % allQuestionCount;
        setValueContent(postion,Soccer);
        defualtSetTextandColor();
    }


    @Override
    public void onPause() {
        super.onPause();
        awardUser awardUser = new awardUser(USER_ID,Soccer,postion);
        myviewModelquestion.upadateAward(awardUser);
    }

    private void fadeAnimation(Boolean res) {
        Next.setEnabled(false);
        Previous.setEnabled(false);
            Res = res == ResQuestion;
        Fade = AnimatorInflater.loadAnimator(getActivity().getApplicationContext(), R.animator.fade_animation);
        //برای نمایش انمیشن یک  resource  با نام animator در پوشه  res  میسازیم و بعد در آن آبجکت انیماتور را ایجاد می کنیم
        //و در کد بالا آبجکت انیماتور خود را مقدار دهی میکنیم
        // و برای اینکه در طول انمیشن بتوانیم کاری انجام دهیم اینترفیس animator.listener را پیاده سازی میکنیم
        Fade.setTarget(showQuestion);
        Fade.addListener(this);
        Fade.start();
    }
    @SuppressLint("SetTextI18n")
    private void setTextColorShowResualt(Boolean res) {
        if (res) {
            soundPool.play(soundCorrect,1,1,1,0,0);
            showQuestion.setTextColor(getResources().getColor(R.color.green));
            resualtMessage.setText("correct");
            resualtMessage.setTextColor(getResources().getColor(R.color.green));
            Soccer+=1;
            setValueContent(postion,Soccer);
        } else {
            soundPool.play(soundIncorrect,1,1,1,0,0);
            showQuestion.setTextColor(getResources().getColor(R.color.red));
            resualtMessage.setText("incorrect !!");
            resualtMessage.setTextColor(getResources().getColor(R.color.red));
            Soccer-=1;
            setValueContent(postion,Soccer);
        }
    }
    @Override
    public void onAnimationStart(Animator animation) {
    }
    @Override
    public void onAnimationEnd(Animator animation) {
        setTextColorShowResualt(Res);

        Next.setEnabled(true);
        Previous.setEnabled(true);
    }
    @Override
    public void onAnimationCancel(Animator animation) {
    }
    @Override
    public void onAnimationRepeat(Animator animation) {
    }
    private void defualtSetTextandColor() {
        showQuestion.setTextColor(getResources().getColor(R.color.question_color));
        resualtMessage.setText("");

    }
}
