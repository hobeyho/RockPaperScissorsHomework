package com.nickjwpark.rockpaperscissorshomework;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button btnPlay;
    Button btnScissors;
    Button btnRock;
    Button btnPaper;
    TextView textViewResult;
    ImageView imageViewMe;
    ImageView imageViewComputer;

    TextView textViewCurScore;
    TextView textViewHighScore;

    String me = "";
    int curScore = 0;
    int highScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPlay = (Button) findViewById(R.id.btnPlay);
        btnScissors = (Button) findViewById(R.id.btnScissors);
        btnRock = (Button) findViewById(R.id.btnRock);
        btnPaper = (Button) findViewById(R.id.btnPaper);
        textViewResult = (TextView) findViewById(R.id.textViewResult);
        imageViewMe = (ImageView) findViewById(R.id.imageViewMe);
        imageViewComputer = (ImageView) findViewById(R.id.imageViewComputer);

        textViewCurScore = (TextView) findViewById(R.id.textViewCurScore);
        textViewHighScore = (TextView) findViewById(R.id.textViewHighScore);

        btnScissors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                me = "scissors";
                imageViewMe.setImageResource(R.drawable.left_scissors);
                btnScissors.setEnabled(false);
                btnRock.setEnabled(true);
                btnPaper.setEnabled(true);
            }
        });

        btnRock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                me = "rock";
                setMeImage(me);
                btnScissors.setEnabled(true);
                btnRock.setEnabled(false);
                btnPaper.setEnabled(true);
            }
        });

        btnPaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                me = "paper";
                imageViewMe.setImageResource(R.drawable.left_paper);
                btnScissors.setEnabled(true);
                btnRock.setEnabled(true);
                btnPaper.setEnabled(false);
            }
        });

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //btnPlay를 여러번 누를수 없게 딜레이가 끝날때까지 btnPlay를 비활성화 시켜준다
                btnPlay.setEnabled(false);

                //먼저 가위로 모든것이 보이게 해 준다
                setComputerImage("scissors");
                setMeImage("scissors");
                showResult("가위!");

                //Handler 를 만들어준다
                final Handler handler = new Handler();
                //1초 후 모두 바위로 보이게 해 준다
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setComputerImage("rock");
                        setMeImage("rock");
                        showResult("바위!");
                    }
                }, 1000);

                //2초 후 모두 보로 보이게 해 준다
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setComputerImage("paper");
                        setMeImage("paper");
                        showResult("보!");
                    }
                }, 2000);

                //3초 후 결과를 보여준다
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setMeImage(me);
                        playGame();
                        //게임이 끝났을때 버튼을 다시 누를 수 있게 해준다
                        btnPlay.setEnabled(true);
                    }
                }, 3000);
            }
        });
    }

    public void playGame(){
        String computer = randomComputer();
        setComputerImage(computer);
        String result = whoWon(me, computer);
        showToast(me + " vs " + computer);
        showResult(result);
        handleScore(result);
    }

    public void setMeImage(String me){
        if(me.equals("scissors")){
            imageViewMe.setImageResource(R.drawable.left_scissors);
        } else if(me.equals("rock")){
            imageViewMe.setImageResource(R.drawable.left_rock);
        } else if(me.equals("paper")){
            imageViewMe.setImageResource(R.drawable.left_paper);
        }
    }

    public void setComputerImage(String computer){
        if(computer.equals("scissors")){
            imageViewComputer.setImageResource(R.drawable.right_scissors);
        } else if(computer.equals("rock")){
            imageViewComputer.setImageResource(R.drawable.right_rock);
        } else if(computer.equals("paper")){
            imageViewComputer.setImageResource(R.drawable.right_paper);
        }
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

    public void handleScore(String result){
        if(result.equals("Win!")){
            curScore = curScore + 1;
        } else if(result.equals("Lose!")){
            curScore = 0;
        } else if(result.equals("Draw!")){
            //no change in score
        } else {
            //no change in score
        }
        setCurScore(curScore);
        checkHighScore(curScore);

    }

    public void setCurScore(int score){
        textViewCurScore.setText(""+score);
    }

    public void checkHighScore(int score){
        if(score > highScore){
            highScore = score;
            textViewHighScore.setText(""+score);
        }
    }

}
