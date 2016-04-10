package com.nickjwpark.rockpaperscissorshomework;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    EditText editTextMe;
    Button btnPlay;
    TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextMe = (EditText) findViewById(R.id.editTextMe);
        btnPlay = (Button) findViewById(R.id.btnPlay);
        textViewResult = (TextView) findViewById(R.id.textViewResult);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String me = editTextMe.getText().toString();
                String computer = randomComputer();
                String result = whoWon(me, computer);
                showToast(result);
                showResult(result);
            }
        });
    }

    public String randomComputer(){
        String computer = "";
        Random rand = new Random();
        int random = rand.nextInt(3);
        if(random==0){
            computer = "scissors";
        } else if(random==1){
            computer = "rock";
        } else if(random==2){
            computer = "paper";
        } else {
            computer = "error";
        }
        return computer;
    }

    public String whoWon(String me, String computer){
        String result = "";

        if (me.equals("rock")) {
            if(computer.equals("rock")){
                result = "Draw!";
            } else if(computer.equals("paper")){
                result = "Lose!";
            } else if(computer.equals("scissors")){
                result = "Win!";
            } else {
                result = "Typo!";
            }
        } else if(me.equals("paper")){
            if(computer.equals("rock")){
                result = "Win!";
            } else if(computer.equals("paper")){
                result = "Draw!";
            } else if(computer.equals("scissors")){
                result = "Lose!";
            } else {
                result = "Typo!";
            }
        } else if(me.equals("scissors")){
            if(computer.equals("rock")){
                result = "Lose!";
            } else if(computer.equals("paper")){
                result = "Win!";
            } else if(computer.equals("scissors")){
                result = "Draw!";
            } else {
                result = "Typo!";
            }
        } else {
            result = "Typo!";
        }

        return result;
    }

    public void showToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    public void showResult(String result){
        textViewResult.setText(result);
    }

}
