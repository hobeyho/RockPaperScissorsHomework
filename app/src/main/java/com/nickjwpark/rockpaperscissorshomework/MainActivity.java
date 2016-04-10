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

    Button btnPlay;
    Button btnScissors;
    Button btnRock;
    Button btnPaper;
    TextView textViewResult;

    String me = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPlay = (Button) findViewById(R.id.btnPlay);
        btnScissors = (Button) findViewById(R.id.btnScissors);
        btnRock = (Button) findViewById(R.id.btnRock);
        btnPaper = (Button) findViewById(R.id.btnPaper);
        textViewResult = (TextView) findViewById(R.id.textViewResult);

        btnScissors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                me = "scissors";
                btnScissors.setEnabled(false);
                btnRock.setEnabled(true);
                btnPaper.setEnabled(true);
            }
        });

        btnRock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                me = "rock";
                btnScissors.setEnabled(true);
                btnRock.setEnabled(false);
                btnPaper.setEnabled(true);
            }
        });

        btnPaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                me = "paper";
                btnScissors.setEnabled(true);
                btnRock.setEnabled(true);
                btnPaper.setEnabled(false);
            }
        });

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String computer = randomComputer();
                String result = whoWon(me, computer);
                showToast(me + " vs " + computer);
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
            result = "Select your move!";
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
