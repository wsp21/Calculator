package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.Bundle;


import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //一般操作
    Button btn_clean, btn_del, btn_sci;
    //数字
    Button btn_0, btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9;
    //操作符
    Button btn_add, btn_minus, btn_multiply, btn_divide, btn_point, btn_equal;
    TextView textView;
    boolean operator = true,flag_3=false;
    //符号位数
    int num = 0, no_operator_number = 0, zero_1=0, cheng_or_chu = 0,x=0,num3=0;
    int []arr =new int[30];
    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);
        //存放
        textView = findViewById(R.id.head);
        //初始化
        btn_clean = findViewById(R.id.btn_clean);
        btn_del = findViewById(R.id.btn_del);
        btn_sci = findViewById(R.id.btn_sci);
        btn_0 = findViewById(R.id.btn_0);
        btn_1 = findViewById(R.id.btn_1);
        btn_2 = findViewById(R.id.btn_2);
        btn_3 = findViewById(R.id.btn_3);
        btn_4 = findViewById(R.id.btn_4);
        btn_5 = findViewById(R.id.btn_5);
        btn_6 = findViewById(R.id.btn_6);
        btn_7 = findViewById(R.id.btn_7);
        btn_8 = findViewById(R.id.btn_8);
        btn_9 = findViewById(R.id.btn_9);
        btn_add = findViewById(R.id.btn_add);
        btn_minus = findViewById(R.id.btn_minus);
        btn_multiply = findViewById(R.id.btn_multiply);
        btn_divide = findViewById(R.id.btn_divide);
        btn_point = findViewById(R.id.btn_point);
        btn_equal = findViewById(R.id.btn_equal);

        //添加点击事件
        textView.setOnClickListener(this);
        btn_clean.setOnClickListener(this);
        btn_del.setOnClickListener(this);
        btn_0.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);
        btn_add.setOnClickListener(this);
        btn_minus.setOnClickListener(this);
        btn_multiply.setOnClickListener(this);
        btn_divide.setOnClickListener(this);
        btn_point.setOnClickListener(this);
        btn_equal.setOnClickListener(this);
        btn_sci.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //转化为字符串类型并存放
        String str = textView.getText().toString();
        switch (v.getId()) {
            case  R.id.btn_sci:
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,Main2Activity.class);
                startActivity(intent);
                break;
            case R.id.btn_0:
                zero_1 = str.length();
            case R.id.btn_1:
            case R.id.btn_2:
            case R.id.btn_3:
            case R.id.btn_4:
            case R.id.btn_5:
            case R.id.btn_6:
            case R.id.btn_7:
            case R.id.btn_8:
            case R.id.btn_9:
            case R.id.btn_point:
                //符号位数增加
                num++;
                if (num > 30) {
                    textView.setText("超出显示范围,请清空！");
                    break;
                }
                no_operator_number++;
                //可以进行运算符操作
                if(v.getId()!=R.id.btn_point)
                {
                    operator = true;
                }
//             0的验证
                if (no_operator_number == 2 && str.substring(zero_1, str.length()).equals("0") && v.getId() != R.id.btn_point || num == 2 && str.equals("0") && v.getId() != R.id.btn_point) {
                    num--;
                    str = str.substring(0, str.length() - 1);
                    textView.setText(str + ((Button) v).getText());
                }

                //末尾不出现重复的.
                if ((!str.equals("")&&str.substring(str.length()-1,str.length()).equals(".")&&v.getId() == R.id.btn_point)||(x==0&&v.getId()==R.id.btn_point)||(x==3&&v.getId()==R.id.btn_point)||(x==4&&v.getId()==R.id.btn_point)) {
                    num--;
                    break;
                }
                if(v.getId()==R.id.btn_point)
                {
                    num3=0;
                    System.out.println(num3);
                }
                if(v.getId()!=R.id.btn_point&&x!=4&&x!=3)
                {
                    x=2;
                    Log.d("X","2");
                }
                if(x==2&&v.getId()==R.id.btn_point)
                {
                    x=4;
                    Log.d("X",".");
                }
                if(x==4&&v.getId()!=R.id.btn_point)
                {
                    x=3;
                    flag_3=true;
                    Log.d("X","3");
                }
                if(x==3&&flag_3==true)
                {
                    num3++;
                    Log.d("num3","num3++");
                    System.out.println(num3);
                }
                textView.setText(str + ((Button) v).getText());
                break;
            case R.id.btn_multiply:
                cheng_or_chu = 1;
            case R.id.btn_divide:
                cheng_or_chu = 1;
            case R.id.btn_add:
            case R.id.btn_minus:
                num++;
                no_operator_number = 0;
                flag_3=false;
                arr[i]=num3;
                i++;
                num3=0;
                if(x!=4)
                {
                    x=0;
                    Log.d("X","0");
                }
                if (num > 30) {
                    textView.setText("超出显示范围");
                    break;
                }
                //符号转换
                if (operator == false&&x!=4) {
                    num--;
                    str = str.substring(0, str.length() - 1);
                    textView.setText(str + ((Button) v).getText());
                } else
                    textView.setText(str + ((Button) v).getText());
                operator = false;
                //乘法除法不能放在开头
                if (num == 1 && cheng_or_chu == 1) {
                    num--;
                    cheng_or_chu = 0;
                    operator = true;
                    textView.setText("");
                }
                if (x==4) {
                    num--;
                    textView.setText(str);
                }
                break;
            case R.id.btn_del:
                if (str != null && !str.equals("")) {
                    //str长度-1，删除末尾数字
                    num--;
                    operator = true;
                    if(x==4&&str.substring(str.length()-1,str.length()).equals("."))
                    {
                        x=2;
                        Log.d("X","2");
                    }
                    if(x==3&&num3>0)
                    {
                        num3--;
                        System.out.println(num3);
                        Log.d("X","3");
                    }
                    if(x==3&&num3==0)
                    {
                        x=4;
                        System.out.println(num3);
                        Log.d("X",".");
                    }
                    if(x==0)
                    {
                        arr[i]=0;
                        if(i>0)i--;
                        System.out.println(arr[i]);
                        num3=arr[i];
                        if(arr[i]>0)
                        {
                            x=3;
                            Log.d("X","3");
                        }
                        else
                        {
                            x=2;
                            Log.d("X","2");
                        }
                    }
                    if(num>=2){
                        if(x==2&&num!=1&&!(str.substring(str.length()-2,str.length()-1).equals("+")||str.substring(str.length()-2,str.length()-1).equals("-")||str.substring(str.length()-2,str.length()-1).equals("*")||str.substring(str.length()-2,str.length()-1).equals("/"))&&!(str.substring(str.length()-1,str.length()).equals("+")||str.substring(str.length()-1,str.length()).equals("-")||str.substring(str.length()-1,str.length()).equals("*")||str.substring(str.length()-1,str.length()).equals("/")))
                        {
                            x=2;
                            Log.d("X","2");
                        }
                        if(x==2&&(str.substring(str.length()-2,str.length()-1).equals("+")||str.substring(str.length()-2,str.length()-1).equals("-")||str.substring(str.length()-2,str.length()-1).equals("*")||str.substring(str.length()-2,str.length()-1).equals("/")))
                        {
                            x=0;
                            Log.d("X","0");
                        }
                    }

                    if (no_operator_number > 0)
                        no_operator_number--;
                    textView.setText(str.substring(0, str.length() - 1));
                }
                break;
            case R.id.btn_clean:
                if (str != null && !str.equals("")) {
                    //文本置空，符号位数置空
                    num = 0;
                    x=0;
                    num3=0;
                    no_operator_number = 0;
                    operator = true;
                    textView.setText("");
                }
                break;
            case R.id.btn_equal:
                if(!str.equals("")&&(str.substring(str.length()-1,str.length()).equals("0")||str.substring(str.length()-1,str.length()).equals("1")||str.substring(str.length()-1,str.length()).equals("2")||str.substring(str.length()-1,str.length()).equals("3")||str.substring(str.length()-1,str.length()).equals("4")||str.substring(str.length()-1,str.length()).equals("5")||str.substring(str.length()-1,str.length()).equals("6")||str.substring(str.length()-1,str.length()).equals("7")||str.substring(str.length()-1,str.length()).equals("8")||str.substring(str.length()-1,str.length()).equals("9")))
                {
                    calcu();
                }
                break;
        }
    }

    private void calcu() {
        String str = textView.getText().toString();
        str=str.replace('x', '*');
        str = cal_string(str);
        textView.setText(str);
    }
    public String cal_string(String expression){
        ScriptEngineManager sem = new ScriptEngineManager();
        ScriptEngine engine = sem.getEngineByName("js");
        String ans="";
        try {
            ans = engine.eval(expression).toString();
        }
        catch (ScriptException e) {
            e.printStackTrace();
        }
        if (ans.equals("Infinity")){
            ans = "计算错误！";
        }
        if (ans.equals("计算错误")){
            ans = "";
        }
        return ans;
    }
}

