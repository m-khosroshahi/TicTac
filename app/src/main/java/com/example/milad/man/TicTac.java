package com.example.milad.man;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class TicTac extends AppCompatActivity  {


    public static final int RED = 1;
    public static final int BLUE = 2;
    public static final int NOT_PLAYED = 3;
    private static final int NO_WINNER = 4;
    int activePlayer = RED;

    int [] gameState = {NOT_PLAYED,NOT_PLAYED,NOT_PLAYED,
                        NOT_PLAYED,NOT_PLAYED,NOT_PLAYED,
                        NOT_PLAYED,NOT_PLAYED,NOT_PLAYED};

    int [][] winnerState = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int winner = NO_WINNER;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tictac);
    }


    public void dropIn(View v){

        int tag = Integer.parseInt((String)v.getTag());

            if (  winner != NO_WINNER ||gameState[tag] != NOT_PLAYED){
                return;
            }
        ImageView iv = (ImageView) v;
           if (activePlayer == RED){
                    iv.setImageResource(R.drawable.red);
                    iv.animate().alpha(1f).setDuration(50);
                    gameState[tag]=RED;
                    activePlayer = BLUE;

           }else if(activePlayer == BLUE){
                    iv.setImageResource(R.drawable.blue);
                    iv.animate().alpha(1f).setDuration(50);
                    gameState[tag]= BLUE;
                    activePlayer = RED;
                }


        AlertDialog.Builder builder = new AlertDialog.Builder(this);

           winner = checkVinner();
            if ( winner != NO_WINNER || filled()){
                if (winner == RED){
                    builder.setMessage("بازیکن قرمز برنده شد! میخاین ادامه بدین؟");
                    builder.setCancelable(false);
                    builder.setPositiveButton("بله", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            rest();
                        }
                    });
                    builder.setNegativeButton("خیر", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                         finish();
                        }
                    }).show();}
                    if (winner == BLUE){
                    builder.setMessage("بازیکن آبی برنده شد! میخاین ادامه بدین؟");
                        builder.setCancelable(false);
                        builder.setPositiveButton("بله", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            rest();
                        }
                    });
                    builder.setNegativeButton("خیر", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    }).show();}
                if (winner == NO_WINNER){
                    builder.setMessage("بازی مساوی  شد! میخاین ادامه بدین؟");
                    builder.setCancelable(false);
                    builder.setPositiveButton("بله", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            rest();
                        }
                    });
                    builder.setNegativeButton("خیر", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    }).show();}


                }



    }

    public int checkVinner(){

        for (int [] position : winnerState){

            if (gameState [position[0]]== gameState [position[1]]&&
                gameState[position[1]]== gameState[position[2]]&& gameState[position[0]]!= NOT_PLAYED) {
                return gameState[position[0]];
            }
        }
        return NO_WINNER;
    }


    public boolean filled(){

        for (int i= 0 ; i<gameState.length ; i++){

            if (gameState[i] == NOT_PLAYED) {
                return false;
            }
        }
        return true;
    }

    public void rest(){

        activePlayer = RED;
        winner = NO_WINNER;
        for (int i = 0 ; i< gameState.length ; i++) {
            gameState[i]= NOT_PLAYED;
        }
        LinearLayout layout = (LinearLayout) findViewById(R.id.tictac);
        for (int i =0; i<layout.getChildCount();i++){
            LinearLayout row = layout.getChildAt(i) instanceof LinearLayout ? (LinearLayout) layout.getChildAt(i) :null;
            if (row == null)return;
            for (int j =0 ; j<row.getChildCount();j++){
                ImageView iv = row.getChildAt(j) instanceof  ImageView ? (ImageView) row.getChildAt(j) :null;
                if (iv == null)return;
                iv.setImageResource(0);
            }

        }



    }
}



