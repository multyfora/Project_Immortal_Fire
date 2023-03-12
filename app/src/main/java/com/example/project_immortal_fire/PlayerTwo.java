package com.example.project_immortal_fire;

import static com.example.project_immortal_fire.CardsSet.draw;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.jakewharton.processphoenix.ProcessPhoenix;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class PlayerTwo extends AppCompatActivity {
    int AvailableBoardSlots2 = 6;

    static boolean GameEnded = false;
    public static String[] BoardCards2 = new String[]{null, null, null, null, null, null};
    public static String[] EnemyCards2 = new String[]{null, null, null, null, null, null};


    //*soundPool variables
    private SoundPool soundPool;
    private int CardPopped;
    private int CardTaken;
    private int CardPlaced;
    private int CardViewerSound;
    private int GameOver;


    private static final String IMAGEVIEW_TAG_CARD1 = "Card1";
    private static final String IMAGEVIEW_TAG_CARD2 = "Card2";
    private static final String IMAGEVIEW_TAG_CARD3 = "Card3";
    private static final String IMAGEVIEW_TAG_CARD4 = "Card4";
    private static final String IMAGEVIEW_TAG_CARD5 = "Card5";
    public static String[] CardsArr2 = new String[]{"none", "none", "none", "none", "none"};
    @SuppressLint("ClickableViewAccessibility")
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        final String[] buffer = {null, null};
        final boolean[] CardViewerPoped = {false};
        final boolean[] card1Poped = {false};
        final boolean[] card2Poped = {false};
        final boolean[] card3Poped = {false};
        final boolean[] card4Poped = {false};
        final boolean[] card5Poped = {false};
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_2);
        final boolean[] isEndTurn = {true};


        AtomicInteger CardsPlacedCount2 = new AtomicInteger();
        EnemyCards2 = Arrays.copyOf(getIntent().getExtras().getStringArray("BoardCards"), 6);
        BoardCards2 = Arrays.copyOf(getIntent().getExtras().getStringArray("EnemyCards"), 6);
        Log.i("Intent Data", "onCreate: " + Arrays.toString(BoardCards2));
        ConstraintLayout TurnScreen = findViewById(R.id.TurnScreen);
        TextView card1 = findViewById(R.id.Card1);
        TextView card2 = findViewById(R.id.Card2);
        TextView card3 = findViewById(R.id.Card3);
        TextView card4 = findViewById(R.id.Card4);
        TextView card5 = findViewById(R.id.Card5);
        ImageView GameoverImg = findViewById(R.id.GameOverScreen);
        TextView GameoverTxt = findViewById(R.id.GameOverText);
        TextView WinnerName = findViewById(R.id.WinnerName);
        ImageView Replay = findViewById(R.id.ReplayButton);
        TextView CrystalHp  = findViewById(R.id.crystalHealth);
        ImageView EnemyCard1 = findViewById(R.id.EnemyCard1);
        ImageView EnemyCard2 = findViewById(R.id.EnemyCard2);
        ImageView EnemyCard3 = findViewById(R.id.EnemyCard3);
        ImageView EnemyCard4 = findViewById(R.id.EnemyCard4);
        ImageView EnemyCard5 = findViewById(R.id.EnemyCard5);
        ImageView EnemyCard6 = findViewById(R.id.EnemyCard6);
        CardView CardViewer = findViewById(R.id.CardViewer);
        ImageView BoardCard1 = findViewById(R.id.BoardCard1);
        ImageView BoardCard2 = findViewById(R.id.BoardCard2);
        ImageView BoardCard3 = findViewById(R.id.BoardCard3);
        ImageView BoardCard4 = findViewById(R.id.BoardCard4);
        ImageView BoardCard5 = findViewById(R.id.BoardCard5);
        ImageView BoardCard6 = findViewById(R.id.BoardCard6);
        ImageView EndTurn2 = findViewById(R.id.EndTurn2);
        TextView BoardCard1Txt = findViewById(R.id.BoardCard1Txt);
        TextView BoardCard2Txt = findViewById(R.id.BoardCard2Txt);
        TextView BoardCard3Txt = findViewById(R.id.BoardCard3Txt);
        TextView BoardCard4Txt = findViewById(R.id.BoardCard4Txt);
        TextView BoardCard5Txt = findViewById(R.id.BoardCard5Txt);
        TextView BoardCard6Txt = findViewById(R.id.BoardCard6Txt);
        TextView EnemyCard1Txt = findViewById(R.id.EnemyCard1Txt);
        TextView EnemyCard2Txt = findViewById(R.id.EnemyCard2Txt);
        TextView EnemyCard3Txt = findViewById(R.id.EnemyCard3Txt);
        TextView EnemyCard4Txt = findViewById(R.id.EnemyCard4Txt);
        TextView EnemyCard5Txt = findViewById(R.id.EnemyCard5Txt);
        TextView EnemyCard6Txt = findViewById(R.id.EnemyCard6Txt);
        TextView[] BText2 = {BoardCard1Txt, BoardCard2Txt, BoardCard3Txt, BoardCard4Txt, BoardCard5Txt, BoardCard6Txt,
                EnemyCard1Txt, EnemyCard2Txt, EnemyCard3Txt, EnemyCard4Txt, EnemyCard5Txt, EnemyCard6Txt};
        Crystal.renew2(CrystalHp);





        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();

        soundPool = new SoundPool.Builder()
                .setMaxStreams(6)
                .setAudioAttributes(audioAttributes)
                .build();
        CardPopped = soundPool.load(this, R.raw.cardpopsound, 1);
        CardTaken = soundPool.load(this, R.raw.cardtaken, 1);
        CardPlaced = soundPool.load(this, R.raw.cardplaced, 1);
        CardViewerSound = soundPool.load(this, R.raw.cardviewer, 1);
        GameOver = soundPool.load(this, R.raw.gameover, 1);



        //*reload button ig
        //!update the removing variables everytime anything new appears
        /*
        if (getIntent().getExtras().getBoolean("Replay")){
            GameEnded = false;
            Crystal.setHp1(100,CrystalHp);
            Arrays.fill(BoardCards2,null);
            Arrays.fill(EnemyCards2,null);
        }
        */


        ImageView[] BCards = {BoardCard1,BoardCard2,BoardCard3,BoardCard4,BoardCard5,BoardCard6,
                EnemyCard1,EnemyCard2,EnemyCard3,EnemyCard4,EnemyCard5,EnemyCard6};

        CardsSet.boardset(BoardCard1, BoardCard2, BoardCard3, BoardCard4, BoardCard5, BoardCard6, BoardCards2);
        CardsSet.enemyset(EnemyCard1, EnemyCard2, EnemyCard3, EnemyCard4, EnemyCard5, EnemyCard6, EnemyCards2);

        Log.i("EnemyCards", "onCreate: " + Arrays.toString(EnemyCards2));

        TurnScreen.setVisibility(View.VISIBLE);

        card1.setTag(IMAGEVIEW_TAG_CARD1);
        card2.setTag(IMAGEVIEW_TAG_CARD2);
        card3.setTag(IMAGEVIEW_TAG_CARD3);
        card4.setTag(IMAGEVIEW_TAG_CARD4);
        card5.setTag(IMAGEVIEW_TAG_CARD5);
        //BoardCards.invalidate(BText2);

        EndTurn2.setOnClickListener(view -> {


                //!this could be HEAVILY optimised by making all cards follow one animation. but im too lazy now :)
                //! so ill do it only for HitAnim 1 and only for enemies

                ValueAnimator HitAnim = ValueAnimator.ofFloat(1f, 1.5f);
                HitAnim.setDuration(700);
                HitAnim.setInterpolator(new DecelerateInterpolator());
                HitAnim.start();
                HitAnim.addUpdateListener(valueAnimator -> {
                    float goingalpha = (float) valueAnimator.getAnimatedValue();
                    if(!BoardCard1Txt.getText().equals("")) {
                        BoardCard1.setScaleY(goingalpha);
                        BoardCard1.setScaleX(goingalpha);
                        BoardCard1Txt.setScaleX(goingalpha);
                        BoardCard1Txt.setScaleY(goingalpha);
                    }

                    //*enemies

                    if(!EnemyCard1Txt.getText().equals("")) {
                        EnemyCard1.setScaleY(goingalpha);
                        EnemyCard1.setScaleX(goingalpha);
                        EnemyCard1Txt.setScaleX(goingalpha);
                        EnemyCard1Txt.setScaleY(goingalpha);
                    }
                    if(!EnemyCard2Txt.getText().equals("")) {
                        EnemyCard2.setScaleY(goingalpha);
                        EnemyCard2.setScaleX(goingalpha);
                        EnemyCard2Txt.setScaleX(goingalpha);
                        EnemyCard2Txt.setScaleY(goingalpha);
                    }
                    if(!EnemyCard3Txt.getText().equals("")) {
                        EnemyCard3.setScaleY(goingalpha);
                        EnemyCard3.setScaleX(goingalpha);
                        EnemyCard3Txt.setScaleX(goingalpha);
                        EnemyCard3Txt.setScaleY(goingalpha);
                    }
                    if(!EnemyCard4Txt.getText().equals("")) {
                        EnemyCard4.setScaleY(goingalpha);
                        EnemyCard4.setScaleX(goingalpha);
                        EnemyCard4Txt.setScaleX(goingalpha);
                        EnemyCard4Txt.setScaleY(goingalpha);
                    }
                    if(!EnemyCard5Txt.getText().equals("")) {
                        EnemyCard5.setScaleY(goingalpha);
                        EnemyCard5.setScaleX(goingalpha);
                        EnemyCard5Txt.setScaleX(goingalpha);
                        EnemyCard5Txt.setScaleY(goingalpha);
                    }
                    if(!EnemyCard6Txt.getText().equals("")) {
                        EnemyCard6.setScaleY(goingalpha);
                        EnemyCard6.setScaleX(goingalpha);
                        EnemyCard6Txt.setScaleX(goingalpha);
                        EnemyCard6Txt.setScaleY(goingalpha);
                    }

                });
                HitAnim.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        ValueAnimator HitAnim2 = ValueAnimator.ofFloat(1.5f, 1f);
                        HitAnim2.setDuration(200);
                        HitAnim2.setInterpolator(new AccelerateInterpolator());
                        HitAnim2.start();
                        HitAnim2.addUpdateListener(valueAnimator -> {
                            float goingalpha = (float) valueAnimator.getAnimatedValue();
                            if(!BoardCard1Txt.getText().equals("")) {
                                BoardCard1.setScaleY(goingalpha);
                                BoardCard1.setScaleX(goingalpha);
                                BoardCard1Txt.setScaleX(goingalpha);
                                BoardCard1Txt.setScaleY(goingalpha);
                                }

                                //*enemies


                                if(!EnemyCard1Txt.getText().equals("")) {

                                    EnemyCard1.setScaleY(goingalpha);
                                    EnemyCard1.setScaleX(goingalpha);
                                    EnemyCard1Txt.setScaleX(goingalpha);
                                    EnemyCard1Txt.setScaleY(goingalpha);
                                }
                                if(!EnemyCard2Txt.getText().equals("")) {
                                    EnemyCard2.setScaleY(goingalpha);
                                    EnemyCard2.setScaleX(goingalpha);
                                    EnemyCard2Txt.setScaleX(goingalpha);
                                    EnemyCard2Txt.setScaleY(goingalpha);
                                }
                                if(!EnemyCard3Txt.getText().equals("")) {
                                    EnemyCard3.setScaleY(goingalpha);
                                    EnemyCard3.setScaleX(goingalpha);
                                    EnemyCard3Txt.setScaleX(goingalpha);
                                    EnemyCard3Txt.setScaleY(goingalpha);
                                }
                                if(!EnemyCard4Txt.getText().equals("")) {
                                    EnemyCard4.setScaleY(goingalpha);
                                    EnemyCard4.setScaleX(goingalpha);
                                    EnemyCard4Txt.setScaleX(goingalpha);
                                    EnemyCard4Txt.setScaleY(goingalpha);
                                }
                                if(!EnemyCard5Txt.getText().equals("")) {
                                    EnemyCard5.setScaleY(goingalpha);
                                    EnemyCard5.setScaleX(goingalpha);
                                    EnemyCard5Txt.setScaleX(goingalpha);
                                    EnemyCard5Txt.setScaleY(goingalpha);
                                }
                                if(!EnemyCard6Txt.getText().equals("")) {
                                    EnemyCard6.setScaleY(goingalpha);
                                    EnemyCard6.setScaleX(goingalpha);
                                    EnemyCard6Txt.setScaleX(goingalpha);
                                    EnemyCard6Txt.setScaleY(goingalpha);
                                }



                            HitAnim2.addListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    if (isEndTurn[0]) {
                                        Cards.Moved(BoardCards2, EnemyCards2, BCards, BText2);
                                        Log.i("boardCards", "array: " + Arrays.toString(BoardCards2) + "visibility: " + BoardCard1.getVisibility() + BoardCard2.getVisibility() + BoardCard3.getVisibility() + BoardCard4.getVisibility() + BoardCard5.getVisibility() + BoardCard6.getVisibility());
                                        Intent i1 = new Intent(PlayerTwo.this, PlayerOne.class);
                                        Bundle extras = new Bundle();
                                        extras.putStringArray("BoardCards2", BoardCards2);
                                        extras.putStringArray("EnemyCards2", EnemyCards2);
                                        i1.putExtras(extras);
                                        startActivity(i1);
                                        isEndTurn[0] = false;
                                    }
                                }
                            });
                        });
                    }
                });
            ValueAnimator HitAnim1 = ValueAnimator.ofFloat(1f, 1.5f);
            HitAnim.setDuration(500);
            HitAnim.setInterpolator(new DecelerateInterpolator());
            HitAnim.start();
            HitAnim.addUpdateListener(valueAnimator -> {
                float goingalpha = (float) valueAnimator.getAnimatedValue();
                if(!BoardCard2Txt.getText().equals("")) {
                    BoardCard2.setScaleY(goingalpha);
                    BoardCard2.setScaleX(goingalpha);
                    BoardCard2Txt.setScaleX(goingalpha);
                    BoardCard2Txt.setScaleY(goingalpha);
                }
            });
            HitAnim.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    ValueAnimator HitAnim2 = ValueAnimator.ofFloat(1.5f, 1f);
                    HitAnim2.setDuration(300);
                    HitAnim2.setInterpolator(new AccelerateInterpolator());
                    HitAnim2.start();
                    HitAnim2.addUpdateListener(valueAnimator -> {
                        float goingalpha = (float) valueAnimator.getAnimatedValue();
                        if(!BoardCard2Txt.getText().equals("")) {
                            BoardCard2.setScaleY(goingalpha);
                            BoardCard2.setScaleX(goingalpha);
                            BoardCard2Txt.setScaleX(goingalpha);
                            BoardCard2Txt.setScaleY(goingalpha);
                        }


                        HitAnim2.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                if (isEndTurn[0]) {
                                    Cards.Moved(BoardCards2, EnemyCards2, BCards, BText2);
                                    Log.i("boardCards", "array: " + Arrays.toString(BoardCards2) + "visibility: " + BoardCard1.getVisibility() + BoardCard2.getVisibility() + BoardCard3.getVisibility() + BoardCard4.getVisibility() + BoardCard5.getVisibility() + BoardCard6.getVisibility());
                                    Intent i1 = new Intent(PlayerTwo.this, PlayerOne.class);
                                    Bundle extras = new Bundle();
                                    extras.putStringArray("BoardCards2", BoardCards2);
                                    extras.putStringArray("EnemyCards2", EnemyCards2);
                                    i1.putExtras(extras);
                                    startActivity(i1);
                                    isEndTurn[0] = false;
                                }
                            }
                        });
                    });
                }
            });
            ValueAnimator HitAnim22 = ValueAnimator.ofFloat(1f, 1.5f);
            HitAnim.setDuration(500);
            HitAnim.setInterpolator(new DecelerateInterpolator());
            HitAnim.start();
            HitAnim.addUpdateListener(valueAnimator -> {
                float goingalpha = (float) valueAnimator.getAnimatedValue();
                if(!BoardCard3Txt.getText().equals("")) {
                    BoardCard3.setScaleY(goingalpha);
                    BoardCard3.setScaleX(goingalpha);
                    BoardCard3Txt.setScaleX(goingalpha);
                    BoardCard3Txt.setScaleY(goingalpha);
                }
            });
            HitAnim.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    ValueAnimator HitAnim2 = ValueAnimator.ofFloat(1.5f, 1f);
                    HitAnim2.setDuration(300);
                    HitAnim2.setInterpolator(new AccelerateInterpolator());
                    HitAnim2.start();
                    HitAnim2.addUpdateListener(valueAnimator -> {
                        float goingalpha = (float) valueAnimator.getAnimatedValue();
                        if(!BoardCard3Txt.getText().equals("")) {
                            BoardCard3.setScaleY(goingalpha);
                            BoardCard3.setScaleX(goingalpha);
                            BoardCard3Txt.setScaleX(goingalpha);
                            BoardCard3Txt.setScaleY(goingalpha);
                        }


                        HitAnim2.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                if (isEndTurn[0]) {
                                    Cards.Moved(BoardCards2, EnemyCards2, BCards, BText2);
                                    Log.i("boardCards", "array: " + Arrays.toString(BoardCards2) + "visibility: " + BoardCard1.getVisibility() + BoardCard2.getVisibility() + BoardCard3.getVisibility() + BoardCard4.getVisibility() + BoardCard5.getVisibility() + BoardCard6.getVisibility());
                                    Intent i1 = new Intent(PlayerTwo.this, PlayerOne.class);
                                    Bundle extras = new Bundle();
                                    extras.putStringArray("BoardCards2", BoardCards2);
                                    extras.putStringArray("EnemyCards2", EnemyCards2);
                                    i1.putExtras(extras);
                                    startActivity(i1);
                                    isEndTurn[0] = false;
                                }
                            }
                        });
                    });
                }
            });
            ValueAnimator HitAnim3 = ValueAnimator.ofFloat(1f, 1.5f);
            HitAnim.setDuration(500);
            HitAnim.setInterpolator(new DecelerateInterpolator());
            HitAnim.start();
            HitAnim.addUpdateListener(valueAnimator -> {
                float goingalpha = (float) valueAnimator.getAnimatedValue();
                if(!BoardCard4Txt.getText().equals("")) {
                    BoardCard4.setScaleY(goingalpha);
                    BoardCard4.setScaleX(goingalpha);
                    BoardCard4Txt.setScaleX(goingalpha);
                    BoardCard4Txt.setScaleY(goingalpha);
                }
            });
            HitAnim.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    ValueAnimator HitAnim2 = ValueAnimator.ofFloat(1.5f, 1f);
                    HitAnim2.setDuration(300);
                    HitAnim2.setInterpolator(new AccelerateInterpolator());
                    HitAnim2.start();
                    HitAnim2.addUpdateListener(valueAnimator -> {
                        float goingalpha = (float) valueAnimator.getAnimatedValue();
                        if(!BoardCard4Txt.getText().equals("")) {
                            BoardCard4.setScaleY(goingalpha);
                            BoardCard4.setScaleX(goingalpha);
                            BoardCard4Txt.setScaleX(goingalpha);
                            BoardCard4Txt.setScaleY(goingalpha);
                        }


                        HitAnim2.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                if (isEndTurn[0]) {
                                    Cards.Moved(BoardCards2, EnemyCards2, BCards, BText2);
                                    Log.i("boardCards", "array: " + Arrays.toString(BoardCards2) + "visibility: " + BoardCard1.getVisibility() + BoardCard2.getVisibility() + BoardCard3.getVisibility() + BoardCard4.getVisibility() + BoardCard5.getVisibility() + BoardCard6.getVisibility());
                                    Intent i1 = new Intent(PlayerTwo.this, PlayerOne.class);
                                    Bundle extras = new Bundle();
                                    extras.putStringArray("BoardCards2", BoardCards2);
                                    extras.putStringArray("EnemyCards2", EnemyCards2);
                                    i1.putExtras(extras);
                                    startActivity(i1);
                                    isEndTurn[0] = false;
                                }
                            }
                        });
                    });
                }
            });
            ValueAnimator HitAnim4 = ValueAnimator.ofFloat(1f, 1.5f);
            HitAnim.setDuration(500);
            HitAnim.setInterpolator(new DecelerateInterpolator());
            HitAnim.start();
            HitAnim.addUpdateListener(valueAnimator -> {
                float goingalpha = (float) valueAnimator.getAnimatedValue();
                if(!BoardCard5Txt.getText().equals("")) {
                    BoardCard5.setScaleY(goingalpha);
                    BoardCard5.setScaleX(goingalpha);
                    BoardCard5Txt.setScaleX(goingalpha);
                    BoardCard5Txt.setScaleY(goingalpha);
                }
            });
            HitAnim.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    ValueAnimator HitAnim2 = ValueAnimator.ofFloat(1.5f, 1f);
                    HitAnim2.setDuration(300);
                    HitAnim2.setInterpolator(new AccelerateInterpolator());
                    HitAnim2.start();
                    HitAnim2.addUpdateListener(valueAnimator -> {
                        float goingalpha = (float) valueAnimator.getAnimatedValue();
                        if(!BoardCard5Txt.getText().equals("")) {
                            BoardCard5.setScaleY(goingalpha);
                            BoardCard5.setScaleX(goingalpha);
                            BoardCard5Txt.setScaleX(goingalpha);
                            BoardCard5Txt.setScaleY(goingalpha);
                        }


                        HitAnim2.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                if (isEndTurn[0]) {
                                    Cards.Moved(BoardCards2, EnemyCards2, BCards, BText2);
                                    Log.i("boardCards", "array: " + Arrays.toString(BoardCards2) + "visibility: " + BoardCard1.getVisibility() + BoardCard2.getVisibility() + BoardCard3.getVisibility() + BoardCard4.getVisibility() + BoardCard5.getVisibility() + BoardCard6.getVisibility());
                                    Intent i1 = new Intent(PlayerTwo.this, PlayerOne.class);
                                    Bundle extras = new Bundle();
                                    extras.putStringArray("BoardCards2", BoardCards2);
                                    extras.putStringArray("EnemyCards2", EnemyCards2);
                                    i1.putExtras(extras);
                                    startActivity(i1);
                                    isEndTurn[0] = false;
                                }
                            }
                        });
                    });
                }
            });
            ValueAnimator HitAnim5 = ValueAnimator.ofFloat(1f, 1.5f);
            HitAnim.setDuration(500);
            HitAnim.setInterpolator(new DecelerateInterpolator());
            HitAnim.start();
            HitAnim.addUpdateListener(valueAnimator -> {
                float goingalpha = (float) valueAnimator.getAnimatedValue();
                if(!BoardCard6Txt.getText().equals("")) {
                    BoardCard6.setScaleY(goingalpha);
                    BoardCard6.setScaleX(goingalpha);
                    BoardCard6Txt.setScaleX(goingalpha);
                    BoardCard6Txt.setScaleY(goingalpha);
                }
            });
            HitAnim.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    ValueAnimator HitAnim2 = ValueAnimator.ofFloat(1.5f, 1f);
                    HitAnim2.setDuration(300);
                    HitAnim2.setInterpolator(new AccelerateInterpolator());
                    HitAnim2.start();
                    HitAnim2.addUpdateListener(valueAnimator -> {
                        float goingalpha = (float) valueAnimator.getAnimatedValue();
                        if(!BoardCard6Txt.getText().equals("")) {
                            BoardCard6.setScaleY(goingalpha);
                            BoardCard6.setScaleX(goingalpha);
                            BoardCard6Txt.setScaleX(goingalpha);
                            BoardCard6Txt.setScaleY(goingalpha);
                        }


                        HitAnim2.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                if (isEndTurn[0]) {
                                    Cards.Moved(BoardCards2, EnemyCards2, BCards, BText2);
                                    Log.i("boardCards", "array: " + Arrays.toString(BoardCards2) + "visibility: " + BoardCard1.getVisibility() + BoardCard2.getVisibility() + BoardCard3.getVisibility() + BoardCard4.getVisibility() + BoardCard5.getVisibility() + BoardCard6.getVisibility());
                                    Intent i1 = new Intent(PlayerTwo.this, PlayerOne.class);
                                    Bundle extras = new Bundle();
                                    extras.putStringArray("BoardCards2", BoardCards2);
                                    extras.putStringArray("EnemyCards2", EnemyCards2);
                                    i1.putExtras(extras);
                                    startActivity(i1);
                                    isEndTurn[0] = false;
                                }
                            }
                        });
                    });
                }
            });



        });

        TurnScreen.setOnTouchListener((view, motionEvent) -> {
            TurnScreen.setVisibility(View.GONE);
            return true;
        });

        card1.setOnLongClickListener(v -> {

            if (card1Poped[0]&& CardsPlacedCount2.get()<2) {
                soundPool.play(CardTaken, 0.7f, 0.7f, 0, 0, 1);
                CardsPlacedCount2.getAndIncrement();
                buffer[0] = CardsArr2[0];
                buffer[1] = "0";
                ClipData.Item item = new ClipData.Item((CharSequence) buffer[0]);
                ClipData dragData = new ClipData(
                        (CharSequence) v.getTag(),
                        new String[]{ClipDescription.MIMETYPE_TEXT_PLAIN},
                        item);
                View.DragShadowBuilder myShadow = new myDragShadowBuilder.MyDragShadowBuilder(card1);
                v.startDragAndDrop(dragData, myShadow, null, 0);

                CardsSet.toScale(card1,card2,card3,card4,card5,CardsArr2);
                CardsArr2[0] = "none";
                CardsSet.renew(card1, card2, card3, card4, card5, CardsArr2);
                return true;
            } else return false;
        });
        card2.setOnLongClickListener(v -> {

            if (card2Poped[0]&& CardsPlacedCount2.get()<2) {
                soundPool.play(CardTaken, 0.7f, 0.7f, 0, 0, 1);
                CardsPlacedCount2.getAndIncrement();
                buffer[0] = CardsArr2[1];
                buffer[1] = "1";
                CardsSet.toScale(card1,card2,card3,card4,card5,CardsArr2);
                CardsArr2[1] = "none";
                CardsSet.renew(card1, card2, card3, card4, card5, CardsArr2);
                ClipData.Item item = new ClipData.Item((CharSequence) buffer[0]);
                Log.i("IDK", "buffer: " + buffer[0]);
                ClipData dragData = new ClipData(
                        (CharSequence) v.getTag(),
                        new String[]{ClipDescription.MIMETYPE_TEXT_PLAIN},
                        item);
                View.DragShadowBuilder myShadow = new myDragShadowBuilder.MyDragShadowBuilder(card2);
                v.startDragAndDrop(dragData, myShadow, null, 0);
                return true;
            } else return false;
        });
        card3.setOnLongClickListener(v -> {

            if (card3Poped[0]&& CardsPlacedCount2.get()<2) {
                soundPool.play(CardTaken, 0.7f, 0.7f, 0, 0, 1);
                CardsPlacedCount2.getAndIncrement();
                buffer[0] = CardsArr2[2];
                buffer[1] = "2";
                CardsSet.toScale(card1,card2,card3,card4,card5,CardsArr2);
                CardsArr2[2] = "none";
                CardsSet.renew(card1, card2, card3, card4, card5, CardsArr2);
                ClipData.Item item = new ClipData.Item((CharSequence) buffer[0]);
                ClipData dragData = new ClipData(
                        (CharSequence) v.getTag(),
                        new String[]{ClipDescription.MIMETYPE_TEXT_PLAIN},
                        item);
                View.DragShadowBuilder myShadow = new myDragShadowBuilder.MyDragShadowBuilder(card3);
                v.startDragAndDrop(dragData, myShadow, null, 0);
                return true;
            } else return false;
        });
        card4.setOnLongClickListener(v -> {

            if (card4Poped[0]&& CardsPlacedCount2.get()<2) {
                soundPool.play(CardTaken, 0.7f, 0.7f, 0, 0, 1);
                CardsPlacedCount2.getAndIncrement();
                buffer[0] = CardsArr2[3];
                buffer[1] = "3";
                CardsSet.toScale(card1,card2,card3,card4,card5,CardsArr2);
                CardsArr2[3] = "none";
                CardsSet.renew(card1, card2, card3, card4, card5, CardsArr2);
                ClipData.Item item = new ClipData.Item((CharSequence) buffer[0]);
                ClipData dragData = new ClipData(
                        (CharSequence) v.getTag(),
                        new String[]{ClipDescription.MIMETYPE_TEXT_PLAIN},
                        item);
                View.DragShadowBuilder myShadow = new myDragShadowBuilder.MyDragShadowBuilder(card4);
                v.startDragAndDrop(dragData, myShadow, null, 0);
                return true;
            } else return false;
        });
        card5.setOnLongClickListener(v -> {

            if (card5Poped[0]&& CardsPlacedCount2.get()<2) {
                soundPool.play(CardTaken, 0.7f, 0.7f, 0, 0, 1);
                CardsPlacedCount2.getAndIncrement();
                buffer[0] = CardsArr2[4];
                buffer[1] = "4";
                CardsSet.toScale(card1,card2,card3,card4,card5,CardsArr2);
                CardsArr2[4] = "none";
                CardsSet.renew(card1, card2, card3, card4, card5, CardsArr2);
                ClipData.Item item = new ClipData.Item((CharSequence) buffer[0]);
                ClipData dragData = new ClipData(
                        (CharSequence) v.getTag(),
                        new String[]{ClipDescription.MIMETYPE_TEXT_PLAIN},
                        item);
                View.DragShadowBuilder myShadow = new myDragShadowBuilder.MyDragShadowBuilder(card5);
                v.startDragAndDrop(dragData, myShadow, null, 0);
                return true;
            } else return false;
        });

        CardViewer.setOnClickListener(view -> {
            if (CardViewerPoped[0]) {
                soundPool.play(CardViewerSound, 1, 1, 1,0,1);
                Log.i("Board cards", "All board cards:" + Arrays.toString(BoardCards2));
                ValueAnimator goingAnim = ValueAnimator.ofFloat(1f, 0f);
                goingAnim.setDuration(250);
                goingAnim.setInterpolator(new LinearInterpolator());
                goingAnim.start();
                goingAnim.addUpdateListener(valueAnimator -> {
                    float goingalpha = (float) valueAnimator.getAnimatedValue();
                    CardViewer.setAlpha(goingalpha);
                    if (goingalpha == 0F) {
                        CardViewer.setVisibility(View.GONE);
                    }
                });
                CardViewerPoped[0] = false;
            }
        });

        Replay.setOnClickListener(v -> {
        });

        card1.setOnClickListener(view -> {
            soundPool.play(CardPopped, 0.5f, 0.5f, 0, 0, 1);
            if (!card1Poped[0]) {

                ValueAnimator animation = ValueAnimator.ofFloat(0f, 100f);
                animation.setDuration(500);
                animation.setInterpolator(new OvershootInterpolator());
                animation.start();
                animation.addUpdateListener(updatedAnimation -> {
                    float animatedValue = (float) updatedAnimation.getAnimatedValue();
                    card1.setTranslationY(-animatedValue);
                    card2.setTranslationY(0);
                    card3.setTranslationY(0);
                    card4.setTranslationY(0);
                    card5.setTranslationY(0);
                    card1Poped[0] = true;
                    card2Poped[0] = false;
                    card3Poped[0] = false;
                    card4Poped[0] = false;
                    card5Poped[0] = false;

                });
            } else if (!CardViewerPoped[0]) {
                CardViewer.setVisibility(View.VISIBLE);
                CardViewer.setAlpha(0F);
                String ViewTmp = CardsArr2[0];
                int key = Integer.parseInt(ViewTmp.charAt(ViewTmp.length() - 2)+String.valueOf(ViewTmp.charAt(ViewTmp.length() - 1)));
                CardViewer.setBackgroundResource(draw[key]);
                ValueAnimator alphaAnim = ValueAnimator.ofFloat(0F, 1F);
                alphaAnim.setDuration(250);
                alphaAnim.setInterpolator(new LinearInterpolator());
                alphaAnim.start();
                alphaAnim.addUpdateListener(valueAnimator -> {
                    float alphaValue = (float) valueAnimator.getAnimatedValue();
                    CardViewer.setAlpha(alphaValue);
                });
                CardViewerPoped[0] = true;
            }
        });
        card2.setOnClickListener(view -> {
            soundPool.play(CardPopped, 0.5f, 0.5f, 0, 0, 1);
            if (!card2Poped[0]) {

                ValueAnimator animation = ValueAnimator.ofFloat(0f, 100f);
                animation.setDuration(500);
                animation.setInterpolator(new OvershootInterpolator());
                animation.start();
                animation.addUpdateListener(updatedAnimation -> {
                    float animatedValue = (float) updatedAnimation.getAnimatedValue();
                    card2.setTranslationY(-animatedValue);
                    card1.setTranslationY(0);
                    card3.setTranslationY(0);
                    card4.setTranslationY(0);
                    card5.setTranslationY(0);
                    card1Poped[0] = false;
                    card2Poped[0] = true;
                    card3Poped[0] = false;
                    card4Poped[0] = false;
                    card5Poped[0] = false;

                });
            } else if (!CardViewerPoped[0]) {

                CardViewer.setVisibility(View.VISIBLE);
                CardViewer.setAlpha(0F);
                String ViewTmp = CardsArr2[1];
                int key = Integer.parseInt(ViewTmp.charAt(ViewTmp.length() - 2)+String.valueOf(ViewTmp.charAt(ViewTmp.length() - 1)));
                CardViewer.setBackgroundResource(draw[key]);
                ValueAnimator alphaAnim = ValueAnimator.ofFloat(0F, 1F);
                alphaAnim.setDuration(250);
                alphaAnim.setInterpolator(new LinearInterpolator());
                alphaAnim.start();
                alphaAnim.addUpdateListener(valueAnimator -> {
                    float alphaValue = (float) valueAnimator.getAnimatedValue();
                    CardViewer.setAlpha(alphaValue);
                });
                CardViewerPoped[0] = true;
            }
        });
        card3.setOnClickListener(view -> {
            soundPool.play(CardPopped, 0.5f, 0.5f, 0, 0, 1);
            if (!card3Poped[0]) {

                ValueAnimator animation = ValueAnimator.ofFloat(0f, 100f);
                animation.setDuration(500);
                animation.setInterpolator(new OvershootInterpolator());
                animation.start();
                animation.addUpdateListener(updatedAnimation -> {
                    float animatedValue = (float) updatedAnimation.getAnimatedValue();
                    card3.setTranslationY(-animatedValue);
                    card2.setTranslationY(0);
                    card1.setTranslationY(0);
                    card4.setTranslationY(0);
                    card5.setTranslationY(0);
                    card3Poped[0] = true;
                    card2Poped[0] = false;
                    card1Poped[0] = false;
                    card4Poped[0] = false;
                    card5Poped[0] = false;

                });
            } else if (!CardViewerPoped[0]) {
                CardViewer.setVisibility(View.VISIBLE);
                CardViewer.setAlpha(0F);
                String ViewTmp = CardsArr2[2];
                int key = Integer.parseInt(ViewTmp.charAt(ViewTmp.length() - 2)+String.valueOf(ViewTmp.charAt(ViewTmp.length() - 1)));
                CardViewer.setBackgroundResource(draw[key]);
                ValueAnimator alphaAnim = ValueAnimator.ofFloat(0F, 1F);
                alphaAnim.setDuration(250);
                alphaAnim.setInterpolator(new LinearInterpolator());
                alphaAnim.start();
                alphaAnim.addUpdateListener(valueAnimator -> {
                    float alphaValue = (float) valueAnimator.getAnimatedValue();
                    CardViewer.setAlpha(alphaValue);
                });
                CardViewerPoped[0] = true;
            }
        });
        card4.setOnClickListener(view -> {
            soundPool.play(CardPopped, 0.5f, 0.5f, 0, 0, 1);
            if (!card4Poped[0]) {

                ValueAnimator animation = ValueAnimator.ofFloat(0f, 100f);
                animation.setDuration(500);
                animation.setInterpolator(new OvershootInterpolator());
                animation.start();
                animation.addUpdateListener(updatedAnimation -> {
                    float animatedValue = (float) updatedAnimation.getAnimatedValue();
                    card4.setTranslationY(-animatedValue);
                    card2.setTranslationY(0);
                    card1.setTranslationY(0);
                    card3.setTranslationY(0);
                    card5.setTranslationY(0);
                    card4Poped[0] = true;
                    card2Poped[0] = false;
                    card1Poped[0] = false;
                    card3Poped[0] = false;
                    card5Poped[0] = false;

                });
            } else if (!CardViewerPoped[0]) {
                CardViewer.setVisibility(View.VISIBLE);
                CardViewer.setAlpha(0F);
                String ViewTmp = CardsArr2[3];
                int key = Integer.parseInt(ViewTmp.charAt(ViewTmp.length() - 2)+String.valueOf(ViewTmp.charAt(ViewTmp.length() - 1)));
                CardViewer.setBackgroundResource(draw[key]);
                ValueAnimator alphaAnim = ValueAnimator.ofFloat(0F, 1F);
                alphaAnim.setDuration(250);
                alphaAnim.setInterpolator(new LinearInterpolator());
                alphaAnim.start();
                alphaAnim.addUpdateListener(valueAnimator -> {
                    float alphaValue = (float) valueAnimator.getAnimatedValue();
                    CardViewer.setAlpha(alphaValue);
                });
                CardViewerPoped[0] = true;
            }
        });
        card5.setOnClickListener(view -> {
            soundPool.play(CardPopped, 0.5f, 0.5f, 0, 0, 1);
            if (!card5Poped[0]) {

                ValueAnimator animation = ValueAnimator.ofFloat(0f, 100f);
                animation.setDuration(500);
                animation.setInterpolator(new OvershootInterpolator());
                animation.start();
                animation.addUpdateListener(updatedAnimation -> {
                    float animatedValue = (float) updatedAnimation.getAnimatedValue();
                    card5.setTranslationY(-animatedValue);
                    card2.setTranslationY(0);
                    card1.setTranslationY(0);
                    card4.setTranslationY(0);
                    card3.setTranslationY(0);
                    card5Poped[0] = true;
                    card2Poped[0] = false;
                    card1Poped[0] = false;
                    card4Poped[0] = false;
                    card3Poped[0] = false;

                });
            } else if (!CardViewerPoped[0]) {
                CardViewer.setVisibility(View.VISIBLE);
                CardViewer.setAlpha(0F);
                String ViewTmp = CardsArr2[4];
                int key = Integer.parseInt(ViewTmp.charAt(ViewTmp.length() - 2)+String.valueOf(ViewTmp.charAt(ViewTmp.length() - 1)));
                CardViewer.setBackgroundResource(draw[key]);
                ValueAnimator alphaAnim = ValueAnimator.ofFloat(0F, 1F);
                alphaAnim.setDuration(250);
                alphaAnim.setInterpolator(new LinearInterpolator());
                alphaAnim.start();
                alphaAnim.addUpdateListener(valueAnimator -> {
                    float alphaValue = (float) valueAnimator.getAnimatedValue();
                    CardViewer.setAlpha(alphaValue);
                });
                CardViewerPoped[0] = true;
            }
        });


        if(GameEnded){
            soundPool.setOnLoadCompleteListener((soundPool, sampleId, status) -> soundPool.play(GameOver, 1, 1, 1,0,1));

            EndTurn2.setEnabled(false);
            card1.setEnabled(false);
            card2.setEnabled(false);
            card3.setEnabled(false);
            card4.setEnabled(false);
            card5.setEnabled(false);
            TurnScreen.setVisibility(View.GONE);
            GameoverImg.setVisibility(View.VISIBLE);
            GameoverTxt.setVisibility(View.VISIBLE);
            WinnerName.setVisibility(View.VISIBLE);
            Replay.setVisibility(View.VISIBLE);
            WinnerName.setText("Player 1 Wins");
            WinnerName.setAlpha(0F);
            GameoverImg.setAlpha(0F);
            GameoverTxt.setAlpha(0F);
            ValueAnimator OverImgAnim = ValueAnimator.ofFloat(0F,1F);
            OverImgAnim.setDuration(1000);
            OverImgAnim.setInterpolator(new LinearInterpolator());
            OverImgAnim.start();
            OverImgAnim.addUpdateListener(animation -> {
                float alpha = (float) animation.getAnimatedValue();
                GameoverImg.setAlpha(alpha/2);
                GameoverTxt.setAlpha(alpha);
                WinnerName.setAlpha(alpha);
            });
            ValueAnimator ReplayAnim = ValueAnimator.ofFloat(0F,1.5F);
            ReplayAnim.setDuration(3000);
            ReplayAnim.setInterpolator(new LinearInterpolator());
            ReplayAnim.start();
            ReplayAnim.addUpdateListener(animation -> {
                float alpha = (float) animation.getAnimatedValue();
                Replay.setAlpha(alpha-0.5F);

            });

        }

        BoardCard1.setOnDragListener((v, e) -> {

            // Handles each of the expected events.
            switch (e.getAction()) {

                case DragEvent.ACTION_DRAG_STARTED:

                    // Determines if this View can accept the dragged data.
                    if (e.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)&&BoardCards2[0]==null) {

                        // As an example of what your application might do, applies a blue color tint
                        // to the View to indicate that it can accept data.
                        ((ImageView) v).setColorFilter(Color.BLUE);

                        // Invalidate the view to force a redraw in the new tint.
                        v.invalidate();

                        // Returns true to indicate that the View can accept the dragged data.
                        return true;

                    }

                    // Returns false to indicate that, during the current drag and drop operation,
                    // this View will not receive events again until ACTION_DRAG_ENDED is sent.
                    return false;

                case DragEvent.ACTION_DRAG_ENTERED:

                    // Applies a green tint to the View.
                    ((ImageView) v).setColorFilter(Color.GREEN);

                    // Invalidates the view to force a redraw in the new tint.
                    v.invalidate();

                    // Returns true; the value is ignored.
                    return true;

                case DragEvent.ACTION_DRAG_LOCATION:

                    // Ignore the event.
                    return true;

                case DragEvent.ACTION_DRAG_EXITED:

                    // Resets the color tint to blue.
                    ((ImageView) v).setColorFilter(Color.BLUE);

                    // Invalidates the view to force a redraw in the new tint.
                    v.invalidate();

                    // Returns true; the value is ignored.
                    return true;

                case DragEvent.ACTION_DROP:
                    soundPool.play(CardPlaced, 1, 1, 1,0,0.9f);

                    // Gets the item containing the dragged data.
                    ClipData.Item item = e.getClipData().getItemAt(0);

                    // Gets the text data from the item.
                    CharSequence dragData = item.getText();

                    Cards.boardSet((String)dragData,BoardCard1);
                    BoardCards2[0] = buffer[0];

                    // Displays a message containing the dragged data.
                    Toast.makeText(this, "Dragged data is " + dragData, Toast.LENGTH_SHORT).show();

                    // Turns off any color tints.
                    ((ImageView) v).clearColorFilter();

                    // Invalidates the view to force a redraw.
                    v.invalidate();

                    // Returns true. DragEvent.getResult() will return true.
                    return true;

                case DragEvent.ACTION_DRAG_ENDED:
                    if (!e.getResult()) {
                        CardReturner.Return(card1, card2, card3, card4, card5, CardsArr2, buffer, AvailableBoardSlots2);
                        CardsSet.toScaleBack(card1,card2,card3,card4,card5,CardsArr2);
                        CardsPlacedCount2.getAndDecrement();
                    }

                    // Turns off any color tinting.
                    ((ImageView) v).clearColorFilter();

                    // Invalidates the view to force a redraw.
                    v.invalidate();

                    // Does a getResult(), and displays what happened.
                    if (e.getResult()) {
                        Toast.makeText(this, "The drop was handled.", Toast.LENGTH_SHORT).show();
                        BoardCards.placed(BoardCard1Txt,BoardCards2[0]);
                    }

                    // Returns true; the value is ignored.
                    return true;

                // An unknown action type was received.
                default:
                    Log.e("DragDrop Example", "Unknown action type received by View.OnDragListener.");
                    break;
            }

            return false;

        });
        BoardCard2.setOnDragListener((v, e) -> {

            // Handles each of the expected events.
            switch (e.getAction()) {

                case DragEvent.ACTION_DRAG_STARTED:

                    // Determines if this View can accept the dragged data.
                    if (e.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)&&BoardCards2[1]==null) {

                        // As an example of what your application might do, applies a blue color tint
                        // to the View to indicate that it can accept data.
                        ((ImageView) v).setColorFilter(Color.BLUE);

                        // Invalidate the view to force a redraw in the new tint.
                        v.invalidate();

                        // Returns true to indicate that the View can accept the dragged data.
                        return true;

                    }

                    // Returns false to indicate that, during the current drag and drop operation,
                    // this View will not receive events again until ACTION_DRAG_ENDED is sent.
                    return false;

                case DragEvent.ACTION_DRAG_ENTERED:

                    // Applies a green tint to the View.
                    ((ImageView) v).setColorFilter(Color.GREEN);

                    // Invalidates the view to force a redraw in the new tint.
                    v.invalidate();

                    // Returns true; the value is ignored.
                    return true;

                case DragEvent.ACTION_DRAG_LOCATION:

                    // Ignore the event.
                    return true;

                case DragEvent.ACTION_DRAG_EXITED:

                    // Resets the color tint to blue.
                    ((ImageView) v).setColorFilter(Color.BLUE);

                    // Invalidates the view to force a redraw in the new tint.
                    v.invalidate();

                    // Returns true; the value is ignored.
                    return true;

                case DragEvent.ACTION_DROP:
                    soundPool.play(CardPlaced, 1, 1, 1,0,0.9f);

                    // Gets the item containing the dragged data.
                    ClipData.Item item = e.getClipData().getItemAt(0);

                    // Gets the text data from the item.
                    CharSequence dragData = item.getText();

                    Cards.boardSet((String)dragData,BoardCard2);
                    BoardCards2[1] = buffer[0];

                    // Displays a message containing the dragged data.
                    Toast.makeText(this, "Dragged data is " + dragData, Toast.LENGTH_SHORT).show();

                    // Turns off any color tints.
                    ((ImageView) v).clearColorFilter();

                    // Invalidates the view to force a redraw.
                    v.invalidate();

                    // Returns true. DragEvent.getResult() will return true.
                    return true;

                case DragEvent.ACTION_DRAG_ENDED:
                    if (!e.getResult()) {
                        CardReturner.Return(card1, card2, card3, card4, card5, CardsArr2, buffer, AvailableBoardSlots2);
                        CardsSet.toScaleBack(card1,card2,card3,card4,card5,CardsArr2);
                        CardsPlacedCount2.getAndDecrement();
                    }

                    // Turns off any color tinting.
                    ((ImageView) v).clearColorFilter();

                    // Invalidates the view to force a redraw.
                    v.invalidate();

                    // Does a getResult(), and displays what happened.
                    if (e.getResult()) {
                        Toast.makeText(this, "The drop was handled.", Toast.LENGTH_SHORT).show();
                        BoardCards.placed(BoardCard2Txt,BoardCards2[1]);
                    }

                    // Returns true; the value is ignored.
                    return true;

                // An unknown action type was received.
                default:
                    Log.e("DragDrop Example", "Unknown action type received by View.OnDragListener.");
                    break;
            }

            return false;

        });
        BoardCard3.setOnDragListener((v, e) -> {

            // Handles each of the expected events.
            switch (e.getAction()) {

                case DragEvent.ACTION_DRAG_STARTED:

                    // Determines if this View can accept the dragged data.
                    if (e.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)&&BoardCards2[2]==null) {

                        // As an example of what your application might do, applies a blue color tint
                        // to the View to indicate that it can accept data.
                        ((ImageView) v).setColorFilter(Color.BLUE);

                        // Invalidate the view to force a redraw in the new tint.
                        v.invalidate();

                        // Returns true to indicate that the View can accept the dragged data.
                        return true;

                    }

                    // Returns false to indicate that, during the current drag and drop operation,
                    // this View will not receive events again until ACTION_DRAG_ENDED is sent.
                    return false;

                case DragEvent.ACTION_DRAG_ENTERED:

                    // Applies a green tint to the View.
                    ((ImageView) v).setColorFilter(Color.GREEN);

                    // Invalidates the view to force a redraw in the new tint.
                    v.invalidate();

                    // Returns true; the value is ignored.
                    return true;

                case DragEvent.ACTION_DRAG_LOCATION:

                    // Ignore the event.
                    return true;

                case DragEvent.ACTION_DRAG_EXITED:

                    // Resets the color tint to blue.
                    ((ImageView) v).setColorFilter(Color.BLUE);

                    // Invalidates the view to force a redraw in the new tint.
                    v.invalidate();

                    // Returns true; the value is ignored.
                    return true;

                case DragEvent.ACTION_DROP:
                    soundPool.play(CardPlaced, 1, 1, 1,0,0.9f);

                    // Gets the item containing the dragged data.
                    ClipData.Item item = e.getClipData().getItemAt(0);

                    // Gets the text data from the item.
                    CharSequence dragData = item.getText();

                    Cards.boardSet((String)dragData,BoardCard3);
                    BoardCards2[2] = buffer[0];

                    // Displays a message containing the dragged data.
                    Toast.makeText(this, "Dragged data is " + dragData, Toast.LENGTH_SHORT).show();

                    // Turns off any color tints.
                    ((ImageView) v).clearColorFilter();

                    // Invalidates the view to force a redraw.
                    v.invalidate();

                    // Returns true. DragEvent.getResult() will return true.
                    return true;

                case DragEvent.ACTION_DRAG_ENDED:

                    if (!e.getResult()) {
                        CardReturner.Return(card1, card2, card3, card4, card5, CardsArr2, buffer, AvailableBoardSlots2);
                        CardsSet.toScaleBack(card1,card2,card3,card4,card5,CardsArr2);
                        CardsPlacedCount2.getAndDecrement();
                    }

                    // Turns off any color tinting.
                    ((ImageView) v).clearColorFilter();

                    // Invalidates the view to force a redraw.
                    v.invalidate();

                    // Does a getResult(), and displays what happened.
                    if (e.getResult()) {
                        Toast.makeText(this, "The drop was handled.", Toast.LENGTH_SHORT).show();
                        BoardCards.placed(BoardCard3Txt,BoardCards2[2]);
                    }

                    // Returns true; the value is ignored.
                    return true;

                // An unknown action type was received.
                default:
                    Log.e("DragDrop Example", "Unknown action type received by View.OnDragListener.");
                    break;
            }

            return false;

        });
        BoardCard4.setOnDragListener((v, e) -> {

            // Handles each of the expected events.
            switch (e.getAction()) {

                case DragEvent.ACTION_DRAG_STARTED:

                    // Determines if this View can accept the dragged data.
                    if (e.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)&&BoardCards2[3]==null) {

                        // As an example of what your application might do, applies a blue color tint
                        // to the View to indicate that it can accept data.
                        ((ImageView) v).setColorFilter(Color.BLUE);

                        // Invalidate the view to force a redraw in the new tint.
                        v.invalidate();

                        // Returns true to indicate that the View can accept the dragged data.
                        return true;

                    }

                    // Returns false to indicate that, during the current drag and drop operation,
                    // this View will not receive events again until ACTION_DRAG_ENDED is sent.
                    return false;

                case DragEvent.ACTION_DRAG_ENTERED:

                    // Applies a green tint to the View.
                    ((ImageView) v).setColorFilter(Color.GREEN);

                    // Invalidates the view to force a redraw in the new tint.
                    v.invalidate();

                    // Returns true; the value is ignored.
                    return true;

                case DragEvent.ACTION_DRAG_LOCATION:

                    // Ignore the event.
                    return true;

                case DragEvent.ACTION_DRAG_EXITED:

                    // Resets the color tint to blue.
                    ((ImageView) v).setColorFilter(Color.BLUE);

                    // Invalidates the view to force a redraw in the new tint.
                    v.invalidate();

                    // Returns true; the value is ignored.
                    return true;

                case DragEvent.ACTION_DROP:
                    soundPool.play(CardPlaced, 1, 1, 1,0,0.9f);

                    // Gets the item containing the dragged data.
                    ClipData.Item item = e.getClipData().getItemAt(0);

                    // Gets the text data from the item.
                    CharSequence dragData = item.getText();

                    Cards.boardSet((String)dragData,BoardCard4);
                    BoardCards2[3] = buffer[0];

                    // Displays a message containing the dragged data.
                    Toast.makeText(this, "Dragged data is " + dragData, Toast.LENGTH_SHORT).show();

                    // Turns off any color tints.
                    ((ImageView) v).clearColorFilter();

                    // Invalidates the view to force a redraw.
                    v.invalidate();

                    // Returns true. DragEvent.getResult() will return true.
                    return true;

                case DragEvent.ACTION_DRAG_ENDED:
                    if (!e.getResult()) {
                        CardReturner.Return(card1, card2, card3, card4, card5, CardsArr2, buffer, AvailableBoardSlots2);
                        CardsSet.toScaleBack(card1,card2,card3,card4,card5,CardsArr2);
                        CardsPlacedCount2.getAndDecrement();
                    }
                    // Turns off any color tinting.
                    ((ImageView) v).clearColorFilter();

                    // Invalidates the view to force a redraw.
                    v.invalidate();

                    // Does a getResult(), and displays what happened.
                    if (e.getResult()) {
                        Toast.makeText(this, "The drop was handled.", Toast.LENGTH_SHORT).show();
                        BoardCards.placed(BoardCard4Txt,BoardCards2[3]);
                    }

                    // Returns true; the value is ignored.
                    return true;

                // An unknown action type was received.
                default:
                    Log.e("DragDrop Example", "Unknown action type received by View.OnDragListener.");
                    break;
            }

            return false;

        });
        BoardCard5.setOnDragListener((v, e) -> {

            // Handles each of the expected events.
            switch (e.getAction()) {

                case DragEvent.ACTION_DRAG_STARTED:

                    // Determines if this View can accept the dragged data.
                    if (e.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)&&BoardCards2[4]==null) {

                        // As an example of what your application might do, applies a blue color tint
                        // to the View to indicate that it can accept data.
                        ((ImageView) v).setColorFilter(Color.BLUE);

                        // Invalidate the view to force a redraw in the new tint.
                        v.invalidate();

                        // Returns true to indicate that the View can accept the dragged data.
                        return true;

                    }

                    // Returns false to indicate that, during the current drag and drop operation,
                    // this View will not receive events again until ACTION_DRAG_ENDED is sent.
                    return false;

                case DragEvent.ACTION_DRAG_ENTERED:

                    // Applies a green tint to the View.
                    ((ImageView) v).setColorFilter(Color.GREEN);

                    // Invalidates the view to force a redraw in the new tint.
                    v.invalidate();

                    // Returns true; the value is ignored.
                    return true;

                case DragEvent.ACTION_DRAG_LOCATION:

                    // Ignore the event.
                    return true;

                case DragEvent.ACTION_DRAG_EXITED:

                    // Resets the color tint to blue.
                    ((ImageView) v).setColorFilter(Color.BLUE);

                    // Invalidates the view to force a redraw in the new tint.
                    v.invalidate();

                    // Returns true; the value is ignored.
                    return true;

                case DragEvent.ACTION_DROP:
                    soundPool.play(CardPlaced, 1, 1, 1,0,0.9f);

                    // Gets the item containing the dragged data.
                    ClipData.Item item = e.getClipData().getItemAt(0);

                    // Gets the text data from the item.
                    CharSequence dragData = item.getText();

                    Cards.boardSet((String)dragData,BoardCard5);
                    BoardCards2[4] = buffer[0];

                    // Displays a message containing the dragged data.
                    Toast.makeText(this, "Dragged data is " + dragData, Toast.LENGTH_SHORT).show();

                    // Turns off any color tints.
                    ((ImageView) v).clearColorFilter();

                    // Invalidates the view to force a redraw.
                    v.invalidate();

                    // Returns true. DragEvent.getResult() will return true.
                    return true;

                case DragEvent.ACTION_DRAG_ENDED:
                    if (!e.getResult()) {
                        CardReturner.Return(card1, card2, card3, card4, card5, CardsArr2, buffer, AvailableBoardSlots2);
                        CardsSet.toScaleBack(card1,card2,card3,card4,card5,CardsArr2);
                        CardsPlacedCount2.getAndDecrement();
                    }

                    // Turns off any color tinting.
                    ((ImageView) v).clearColorFilter();

                    // Invalidates the view to force a redraw.
                    v.invalidate();

                    // Does a getResult(), and displays what happened.
                    if (e.getResult()) {
                        Toast.makeText(this, "The drop was handled.", Toast.LENGTH_SHORT).show();
                        BoardCards.placed(BoardCard5Txt,BoardCards2[4]);
                    }

                    // Returns true; the value is ignored.
                    return true;

                // An unknown action type was received.
                default:
                    Log.e("DragDrop Example", "Unknown action type received by View.OnDragListener.");
                    break;
            }

            return false;

        });
        BoardCard6.setOnDragListener((v, e) -> {

            // Handles each of the expected events.
            switch (e.getAction()) {

                case DragEvent.ACTION_DRAG_STARTED:

                    // Determines if this View can accept the dragged data.
                    if (e.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)&&BoardCards2[5]==null) {

                        // As an example of what your application might do, applies a blue color tint
                        // to the View to indicate that it can accept data.
                        ((ImageView) v).setColorFilter(Color.BLUE);

                        // Invalidate the view to force a redraw in the new tint.
                        v.invalidate();

                        // Returns true to indicate that the View can accept the dragged data.
                        return true;

                    }

                    // Returns false to indicate that, during the current drag and drop operation,
                    // this View will not receive events again until ACTION_DRAG_ENDED is sent.
                    return false;

                case DragEvent.ACTION_DRAG_ENTERED:

                    // Applies a green tint to the View.
                    ((ImageView) v).setColorFilter(Color.GREEN);

                    // Invalidates the view to force a redraw in the new tint.
                    v.invalidate();

                    // Returns true; the value is ignored.
                    return true;

                case DragEvent.ACTION_DRAG_LOCATION:

                    // Ignore the event.
                    return true;

                case DragEvent.ACTION_DRAG_EXITED:

                    // Resets the color tint to blue.
                    ((ImageView) v).setColorFilter(Color.BLUE);

                    // Invalidates the view to force a redraw in the new tint.
                    v.invalidate();

                    // Returns true; the value is ignored.
                    return true;

                case DragEvent.ACTION_DROP:
                    soundPool.play(CardPlaced, 1, 1, 1,0,0.9f);

                    // Gets the item containing the dragged data.
                    ClipData.Item item = e.getClipData().getItemAt(0);

                    // Gets the text data from the item.
                    CharSequence dragData = item.getText();

                    Cards.boardSet((String)dragData,BoardCard6);
                    BoardCards2[5] = buffer[0];

                    // Displays a message containing the dragged data.
                    Toast.makeText(this, "Dragged data is " + dragData, Toast.LENGTH_SHORT).show();

                    // Turns off any color tints.
                    ((ImageView) v).clearColorFilter();

                    // Invalidates the view to force a redraw.
                    v.invalidate();

                    // Returns true. DragEvent.getResult() will return true.
                    return true;

                case DragEvent.ACTION_DRAG_ENDED:
                    if (!e.getResult()) {
                        CardReturner.Return(card1, card2, card3, card4, card5, CardsArr2, buffer, AvailableBoardSlots2);
                        CardsSet.toScaleBack(card1,card2,card3,card4,card5,CardsArr2);
                        CardsPlacedCount2.getAndDecrement();
                    }
                    // Turns off any color tinting.
                    ((ImageView) v).clearColorFilter();

                    // Invalidates the view to force a redraw.
                    v.invalidate();

                    // Does a getResult(), and displays what happened.
                    if (e.getResult()) {
                        Toast.makeText(this, "The drop was handled.", Toast.LENGTH_SHORT).show();
                        BoardCards.placed(BoardCard6Txt,BoardCards2[5]);
                    }
                    // Returns true; the value is ignored.
                    return true;

                // An unknown action type was received.
                default:
                    Log.e("DragDrop", "Unknown action type received by View.OnDragListener.");
                    break;
            }

            return false;

        });
        Log.i("idk", "before P2" + Arrays.toString(CardsArr2));
        shuffler.shuffle(true);
        Log.i("idk", "onCreate before setting  " + Arrays.toString(CardsArr2));
        CardsSet.set(card1, card2, card3, card4, card5, CardsArr2);
        Log.i("idk", "onCreate cards been set  " + Arrays.toString(CardsArr2));
        Cards.UpdateHp(BoardCards2,EnemyCards2,BText2,2);
    }
    protected static void GameOver2(){
        GameEnded = true;
        Log.i("player 2", "GameOver");
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}