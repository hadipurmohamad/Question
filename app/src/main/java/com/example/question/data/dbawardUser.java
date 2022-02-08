package com.example.question.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.question.model.awardUser;
import com.example.question.model.awardUserDao;
//در انوتیشن زیر ما سه مقدار پاس می دهیم اولی موجودیتهاس که حتما باید به صورت آرایه باشد دومی ورژن پایگاه داده وسومی احتمالا جلوگیری از نسخه گیری است
@Database(entities ={awardUser.class},version = 1,exportSchema = false)

public  abstract  class dbawardUser extends RoomDatabase {
    public abstract awardUserDao awardUserDao();
    private static  dbawardUser dbawardUser;

public static synchronized dbawardUser getInstance(Context context){
if (dbawardUser==null){
    synchronized (dbawardUser.class){

    dbawardUser= Room.databaseBuilder//برای ساخت و دسترسی به دیتابیس از کلاس Room متد databaseBuilder را فراخوانی میکنیم و مقادیر زیر را پاس میدهیم
            (context.getApplicationContext(), dbawardUser.class,"awardUser_db")//مقذار اول کانتکس است و دومی خود همین کلاس و سومی نام پایگاه داده خودمان
            .allowMainThreadQueries()//این تابع برای اجرای عملیات پایگاه داده در thread  جدید است (چون عملیات پایگاه داده سنگین است نباید در thread اصلی انجام شود)
            .build();// و در آخر فراخوانی build
    }
}
return dbawardUser;
}
}
