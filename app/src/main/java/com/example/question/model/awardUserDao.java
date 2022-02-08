package com.example.question.model;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface awardUserDao {
@Update
    public void updateAwardUser(awardUser awardUser);
//   برای آپدیت کردن و وارد کردن اطلاعات فقط نمونه ای از کلاس موجودیت را پاس می دهیم
@Insert
    public void insertAwardUset(awardUser awardUser);
@Query("SELECT socer FROM ScoreUser_tbl")
    public int getSocer ();
//  برای تمامی select ها باید از حاشیه نویسی کوئری استفاده کنیم و دستور سکوئل را بنویسیم
@Query("SELECT currentQuestion FROM ScoreUser_tbl")
    public int getCurrentQuestion();
}
