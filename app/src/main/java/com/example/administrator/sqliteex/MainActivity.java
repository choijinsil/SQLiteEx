package com.example.administrator.sqliteex;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText et_hakbun, et_name, et_adress;
    Button btn_reset, btn_select, btn_save;
    TextView tv_hakbun, tv_name, tv_adress;

    // DB객체 선언
    MyDB myDB;
    SQLiteDatabase sql;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_hakbun = findViewById(R.id.et_hakbun);
        et_name = findViewById(R.id.et_name);
        et_adress = findViewById(R.id.et_adress);

        btn_reset = findViewById(R.id.btn_reset);
        btn_select = findViewById(R.id.btn_select);
        btn_save = findViewById(R.id.btn_save);

        tv_hakbun = findViewById(R.id.tv_hakbun);
        tv_name = findViewById(R.id.tv_name);
        tv_adress = findViewById(R.id.tv_adress);

        // DB객체 생성
        // context를 호출하는것 보통은 this
        myDB = new MyDB(this);

        // 저장
        // Et들의 내용을 가져와서 DB에 저장
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 쓰기 권한을 허용하겠다.
                sql = myDB.getWritableDatabase();

                //  et_hakbun.getText().toString()
                //  String.valueOf(et_name()) --> 자바코드
                //  위 아래 같은코드지만 자바에선 아래 코드를 사용한다.
                String query = "INSERT INTO member(hakbun, name, address)\n" +
                        "VALUES (" + et_hakbun.getText().toString() + ",'" + et_name.getText().toString() + "','" + et_adress.getText().toString() + "')";
                sql.execSQL(query);
                sql.close();    // DB와 연결 객체를 닫겠다.

                Toast.makeText(MainActivity.this, "맴버 저장 완료", Toast.LENGTH_SHORT).show();
            }
        });

        // 조회버튼
        // member테이블 내용 조회해서 화면에 보여주기
        btn_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 읽기 권한을 허용하겠다.
                sql = myDB.getReadableDatabase();
                // 조회권환은 리턴값이 있기 때문에 커서를 사용한다. rawQuery()
                Cursor cursor;
                cursor = sql.rawQuery("SELECT * FROM member", null);
                String hakbun = "학번: \r\n";
                String name = "이름: \r\n";
                String adress = "주소: \r\n";

                while (cursor.moveToNext()) {
                    hakbun += cursor.getString(1) + "\r\n";
                    name += cursor.getString(2) + "\r\n";
                    adress += cursor.getString(3) + "\r\n";
                }
                tv_hakbun.setText(hakbun);
                tv_name.setText(name);
                tv_adress.setText(adress);

                //  커서라는것은 깜빡이는것
                //  무언가 가르키는 것
                //
                cursor.close();
                sql.close();
            }
        });


    }
}
