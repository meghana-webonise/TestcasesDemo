package com.weboniselab.meghana.android.app.testingdemoapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private int left, right, result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn=(Button) findViewById(R.id.btn);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(this,NewActivity.class);
        startActivity(intent);
    }
        public void setLeft(int val) {
            left = val;
        }

        public void setRight(int val) {
            right = val;
        }

        public int getResult() {
            return result;
        }

        public void add() {
            result = left + right;
        }

        public void multiply() {
            result = left * right;
        }

}
