package com.example.administrator.sqliteex;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

// SQLite를 이용하기 위한 class

public class MyDB extends SQLiteOpenHelper {
    public MyDB(Context context) {
        // 내부저장소에 저장되는 곳
        // 1: 어플리케이션 자체, 2: 데이터 베이스 파일이름, 3,4: 중요하지않음
        super(context, "korea", null, 1);
    }

    // mydb가 호출될때 sql을 만들어라.
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE member(\n" +
                " _id INTEGER PRIMARY KEY AUTOINCREMENT\n" +
                " , hakbun INTEGER\n" +
                " , name VARCHAR(50)\n" +
                " , address VARCHAR(100) \n" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sql = "DROP TABLE IF EXISTS member";   //만약 member테이블이 존재하면 삭제해줘
        db.execSQL(sql);
        onCreate(db);
    }
}
