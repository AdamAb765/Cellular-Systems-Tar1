package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    boolean isPlayingGame = true;

    int activePlayer = 0;
    // x = 0, o = 1, empty = 2
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}};

    public static int turnCounter = 0;

    public void playerTap(View view) {
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());

        if (turnCounter == 9 || !isPlayingGame) {
            return;
        }

        if (gameState[tappedImage] == 2) {
            turnCounter++;

            if (turnCounter == 9) {
                isPlayingGame = false;
            }

            gameState[tappedImage] = activePlayer;

            if (activePlayer == 0) {
                img.setImageResource(R.drawable.x);
                activePlayer = 1;
                ImageView status = findViewById(R.id.gameStatus);

                status.setImageResource(R.drawable.oplay);
            } else {
                img.setImageResource(R.drawable.o);
                activePlayer = 0;
                ImageView status = findViewById(R.id.gameStatus);

                status.setImageResource(R.drawable.xplay);
            }

        }

        int flag = 0;
        for (int[] winPosition : winPositions) {
            if (gameState[winPosition[0]] == gameState[winPosition[1]] &&
                    gameState[winPosition[1]] == gameState[winPosition[2]] &&
                    gameState[winPosition[0]] != 2) {
                flag = 1;

                ImageView status = findViewById(R.id.gameStatus);

                isPlayingGame = false;

                if (gameState[winPosition[0]] == 0) {
                    status.setImageResource(R.drawable.xwin);
                } else {
                    status.setImageResource(R.drawable.owin);
                }

                ImageView winMark = findViewById(R.id.winMark);

                switch(winPosition[1] - winPosition[0]) {
                    case(1):
                        if(winPosition[0] == 0)
                            winMark.setImageResource(R.drawable.mark6);
                        if(winPosition[0] == 3)
                            winMark.setImageResource(R.drawable.mark7);
                        if(winPosition[0] == 6)
                            winMark.setImageResource(R.drawable.mark8);
                        break;
                    case(3):
                        if(winPosition[0] == 0)
                            winMark.setImageResource(R.drawable.mark3);
                        if(winPosition[0] == 1)
                            winMark.setImageResource(R.drawable.mark4);
                        if(winPosition[0] == 2)
                            winMark.setImageResource(R.drawable.mark5);
                        break;
                    case(2):
                        winMark.setImageResource(R.drawable.mark2);
                        break;
                    case(4):
                        winMark.setImageResource(R.drawable.mark1);
                        break;
                    default:
                        break;
                }

                Button playAgainBtn = findViewById(R.id.playAgain);
                playAgainBtn.setVisibility(View.VISIBLE);
            }
        }

        if (turnCounter == 9 && flag == 0) {
            ImageView status = findViewById(R.id.gameStatus);
            status.setImageResource(R.drawable.nowin);

            Button playAgainBtn = findViewById(R.id.playAgain);
            playAgainBtn.setVisibility(View.VISIBLE);
        }
    }

    public void resetBoard(View view) {
        isPlayingGame = true;
        activePlayer = 0;

        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }

        ((ImageView) findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView8)).setImageResource(0);
        turnCounter = 0;

        ImageView winMark = findViewById(R.id.winMark);
        winMark.setImageResource(R.drawable.empty);

        ImageView status = findViewById(R.id.gameStatus);
        status.setImageResource(R.drawable.xplay);

        Button playAgainBtn = findViewById(R.id.playAgain);
        playAgainBtn.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView status = findViewById(R.id.gameStatus);
        status.setImageResource(R.drawable.xplay);

        Button playAgainBtn = findViewById(R.id.playAgain);
        playAgainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetBoard(view);
            }
        });
    }
}