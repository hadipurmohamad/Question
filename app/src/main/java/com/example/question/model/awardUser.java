package com.example.question.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;
// برای استفاده از room database  یه سه چیز نیاز داریم اول کلاس موجودیت که همان مدل است که همین کلاس است
//دوم به یک اینترفیس برای نوشتن کوئری ها که  awardUserDao  است در این پروژه
// سوم کلاس database که از  roomdatabase  ارث بری میکند و یک کلاس  abstract  است که در این پروژه  dbawardUser  است
@Entity(tableName ="ScoreUser_tbl")
public class awardUser {
    @PrimaryKey(autoGenerate = true)
    @NotNull
    private int id;

    public awardUser() {
    }

    public awardUser( int id,int socer, int currentQuestion) {
        this.id=id;
        this.socer = socer;
        this.currentQuestion = currentQuestion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ColumnInfo
    private int socer;
    @ColumnInfo
    private int currentQuestion;

    public int getSocer() {
        return socer;
    }

    public void setSocer(int socer) {
        this.socer = socer;
    }

    public int getCurrentQuestion() {
        return currentQuestion;
    }

    public void setCurrentQuestion(int currentQuestion) {
        this.currentQuestion = currentQuestion;
    }

}
