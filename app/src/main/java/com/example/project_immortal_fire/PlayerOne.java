package com.example.project_immortal_fire;

import static com.example.project_immortal_fire.CardsSet.draw;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.processphoenix.ProcessPhoenix;

import java.util.Arrays;

import java.util.concurrent.atomic.AtomicInteger;

public class PlayerOne extends AppCompatActivity {

    public static final int CardsCount = 12;
    int AvailableBoardSlots = 6;

    static Boolean GameEnded = false;

    static boolean IsFirst = true;

    static boolean IsReplayed = false;
    private static final String TAG = "player1";
    private static final String IMAGEVIEW_TAG_CARD1 = "Card1";
    private static final String IMAGEVIEW_TAG_CARD2 = "Card2";
    private static final String IMAGEVIEW_TAG_CARD3 = "Card3";
    private static final String IMAGEVIEW_TAG_CARD4 = "Card4";
    private static final String IMAGEVIEW_TAG_CARD5 = "Card5";
    public static String[] CardsArr = new String[]{"none", "none", "none", "none", "none"};
    public static String[] BoardCards = new String[]{null, null, null, null, null, null};
    public static String[] EnemyCards = new String[]{null, null, null, null, null, null};


    @SuppressLint("ClickableViewAccessibility")
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_1);





        AtomicInteger CardsPlacedCount = new AtomicInteger();
        CardsPlacedCount.set(0);
        final String[] buffer = {null,null};
        final boolean[] CardViewerPoped = {false};
        final boolean[] card1Poped = {false};
        final boolean[] card2Poped = {false};
        final boolean[] card3Poped = {false};
        final boolean[] card4Poped = {false};
        final boolean[] card5Poped = {false};

        ConstraintLayout TurnScreen = findViewById(R.id.TurnScreen);
        CardView CardViewer = findViewById(R.id.CardViewer);
        TextView CrystalHp = findViewById(R.id.crystalHealth);
        TextView card1 = findViewById(R.id.Card1);
        TextView card2 = findViewById(R.id.Card2);
        TextView card3 = findViewById(R.id.Card3);
        TextView card4 = findViewById(R.id.Card4);
        TextView card5 = findViewById(R.id.Card5);
        ImageView GameoverImg = findViewById(R.id.GameOverScreen);
        TextView GameoverTxt = findViewById(R.id.GameOverText);
        TextView WinnerName = findViewById(R.id.WinnerName);
        ImageView Replay = findViewById(R.id.ReplayButton);
        ImageView BoardCard1 = findViewById(R.id.BoardCard1);
        ImageView BoardCard2 = findViewById(R.id.BoardCard2);
        ImageView BoardCard3 = findViewById(R.id.BoardCard3);
        ImageView BoardCard4 = findViewById(R.id.BoardCard4);
        ImageView BoardCard5 = findViewById(R.id.BoardCard5);
        ImageView BoardCard6 = findViewById(R.id.BoardCard6);
        ImageView EnemyCard1 = findViewById(R.id.EnemyCard1);
        ImageView EnemyCard2 = findViewById(R.id.EnemyCard2);
        ImageView EnemyCard3 = findViewById(R.id.EnemyCard3);
        ImageView EnemyCard4 = findViewById(R.id.EnemyCard4);
        ImageView EnemyCard5 = findViewById(R.id.EnemyCard5);
        ImageView EnemyCard6 = findViewById(R.id.EnemyCard6);
        ImageView EndTurn1 = findViewById(R.id.EndTurn);
        Crystal.renew1(CrystalHp);

        if(IsFirst){
            TurnScreen.setVisibility(View.GONE);
        }
        else {
            TurnScreen.setVisibility(View.VISIBLE);
        }
        IsFirst = false;

        if (getIntent().getStringArrayExtra("BoardCards2") != null) {
            BoardCards = Arrays.copyOf(getIntent().getExtras().getStringArray("EnemyCards2"), 6);
            EnemyCards = Arrays.copyOf(getIntent().getExtras().getStringArray("BoardCards2"), 6);

            CardsSet.boardset(BoardCard1, BoardCard2, BoardCard3, BoardCard4, BoardCard5, BoardCard6, BoardCards);
            CardsSet.enemyset(EnemyCard1, EnemyCard2, EnemyCard3, EnemyCard4, EnemyCard5, EnemyCard6, EnemyCards);
        }
        Log.i("BoardCards", "Player1 boardcards: " + Arrays.toString(BoardCards));

        card1.setTag(IMAGEVIEW_TAG_CARD1);
        card2.setTag(IMAGEVIEW_TAG_CARD2);
        card3.setTag(IMAGEVIEW_TAG_CARD3);
        card4.setTag(IMAGEVIEW_TAG_CARD4);
        card5.setTag(IMAGEVIEW_TAG_CARD5);


        EndTurn1.setOnClickListener(view -> {
            Log.i(TAG, "atomic count: " + CardsPlacedCount.get());
            Log.i(TAG, "boardcards: " + Arrays.toString(BoardCards));
            Intent i = new Intent(PlayerOne.this, PlayerTwo.class);
            Bundle extras = new Bundle();
            extras.putStringArray("BoardCards",BoardCards);
            extras.putStringArray("EnemyCards",EnemyCards);
            extras.putBoolean("Replay",IsReplayed);
            i.putExtras(extras);
            startActivity(i);
            IsReplayed = false;
        });


        TurnScreen.setOnTouchListener((view, motionEvent) -> {
            TurnScreen.setVisibility(View.GONE);
            return true;
        });



        card1.setOnLongClickListener(v -> {
            if(card1Poped[0]&& CardsPlacedCount.get()<2) {
                buffer[0] = CardsArr[0];
                buffer[1] = "0";
                CardsPlacedCount.getAndIncrement();
                CardsSet.toScale(card1,card2,card3,card4,card5,CardsArr);
                CardsArr[0] = "none";
                CardsSet.renew(card1,card2,card3,card4,card5,CardsArr);
                ClipData.Item item = new ClipData.Item((CharSequence) buffer[0]);
                ClipData dragData = new ClipData(
                        (CharSequence) v.getTag(),
                        new String[]{ClipDescription.MIMETYPE_TEXT_PLAIN},
                        item);
                View.DragShadowBuilder myShadow = new myDragShadowBuilder.MyDragShadowBuilder(card1);
                v.startDragAndDrop(dragData, myShadow, null, 0);
                return true;
            }else {
                Toast.makeText(getApplicationContext(),"cant pick that up", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        card2.setOnLongClickListener(v -> {

            if(card2Poped[0]&& CardsPlacedCount.get()<2) {
                buffer[0] = CardsArr[1];
                buffer[1] = "1";
                CardsPlacedCount.getAndIncrement();
                CardsSet.toScale(card1,card2,card3,card4,card5,CardsArr);
                CardsArr[1] = "none";
                CardsSet.renew(card1,card2,card3,card4,card5,CardsArr);
                ClipData.Item item = new ClipData.Item((CharSequence) buffer[0]);
                ClipData dragData = new ClipData(
                        (CharSequence) v.getTag(),
                        new String[]{ClipDescription.MIMETYPE_TEXT_PLAIN},
                        item);
                View.DragShadowBuilder myShadow = new myDragShadowBuilder.MyDragShadowBuilder(card2);
                v.startDragAndDrop(dragData, myShadow, null, 0);
                return true;
            }else {
                Toast.makeText(getApplicationContext(),"cant pick that up", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        card3.setOnLongClickListener(v -> {

            if(card3Poped[0]&& CardsPlacedCount.get()<2) {
                buffer[0] = CardsArr[2];
                buffer[1] = "2";
                CardsPlacedCount.getAndIncrement();
                CardsSet.toScale(card1,card2,card3,card4,card5,CardsArr);
                CardsArr[2] = "none";
                CardsSet.renew(card1,card2,card3,card4,card5,CardsArr);
                ClipData.Item item = new ClipData.Item((CharSequence) buffer[0]);
                ClipData dragData = new ClipData(
                        (CharSequence) v.getTag(),
                        new String[]{ClipDescription.MIMETYPE_TEXT_PLAIN},
                        item);
                View.DragShadowBuilder myShadow = new myDragShadowBuilder.MyDragShadowBuilder(card3);
                v.startDragAndDrop(dragData, myShadow, null, 0);
                return true;
            }else {
                Toast.makeText(getApplicationContext(),"cant pick that up", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        card4.setOnLongClickListener(v -> {

            if(card4Poped[0]&& CardsPlacedCount.get()<2) {
                buffer[0] = CardsArr[3];
                buffer[1] = "3";
                CardsPlacedCount.getAndIncrement();
                CardsSet.toScale(card1,card2,card3,card4,card5,CardsArr);
                CardsArr[3] = "none";
                CardsSet.renew(card1,card2,card3,card4,card5,CardsArr);
                ClipData.Item item = new ClipData.Item((CharSequence) buffer[0]);
                ClipData dragData = new ClipData(
                        (CharSequence) v.getTag(),
                        new String[]{ClipDescription.MIMETYPE_TEXT_PLAIN},
                        item);
                View.DragShadowBuilder myShadow = new myDragShadowBuilder.MyDragShadowBuilder(card4);
                v.startDragAndDrop(dragData, myShadow, null, 0);
                return true;
            }else {
                Toast.makeText(getApplicationContext(),"cant pick that up", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        card5.setOnLongClickListener(v -> {

            if(card5Poped[0]&& CardsPlacedCount.get()<2) {
                buffer[0] = CardsArr[4];
                buffer[1] = "4";
                CardsPlacedCount.getAndIncrement();
                CardsSet.toScale(card1,card2,card3,card4,card5,CardsArr);
                CardsArr[4] = "none";
                CardsSet.renew(card1,card2,card3,card4,card5,CardsArr);
                ClipData.Item item = new ClipData.Item((CharSequence) buffer[0]);
                ClipData dragData = new ClipData(
                        (CharSequence) v.getTag(),
                        new String[]{ClipDescription.MIMETYPE_TEXT_PLAIN},
                        item);
                View.DragShadowBuilder myShadow = new myDragShadowBuilder.MyDragShadowBuilder(card5);
                v.startDragAndDrop(dragData, myShadow, null, 0);
                return true;
            }else {
                Toast.makeText(getApplicationContext(),"cant pick that up", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        CardViewer.setOnClickListener(view -> {
            if (CardViewerPoped[0]){
                Log.i("Board cards", "All board cards:" + Arrays.toString(BoardCards));
                ValueAnimator goingAnim = ValueAnimator.ofFloat(1f, 0f);
                goingAnim.setDuration(250);
                goingAnim.setInterpolator(new LinearInterpolator());
                goingAnim.start();
                goingAnim.addUpdateListener(valueAnimator -> {
                    float goingalpha = (float) valueAnimator.getAnimatedValue();
                    CardViewer.setAlpha(goingalpha);
                    if (goingalpha == 0F){
                        CardViewer.setVisibility(View.GONE);

                    }
                });
                CardViewerPoped[0] = false;
            }
        });

/*
        Replay.setOnClickListener(v -> {

            Intent intent = getBaseContext().getPackageManager()
                    .getLaunchIntentForPackage(getBaseContext().getPackageName());
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            GameEnded = false;
            IsFirst = true;
            Crystal.setHp2(100,CrystalHp);
            GameoverImg.setVisibility(View.GONE);
            GameoverTxt.setVisibility(View.GONE);
            Replay.setVisibility(View.GONE);
            WinnerName.setVisibility(View.GONE);
            IsReplayed = true;
            startActivity(intent);
            finish();

        });
*/
        card1.setOnClickListener(view -> {
            if (!card1Poped[0]){

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
        }else if (!CardViewerPoped[0]) {
                CardViewer.setVisibility(View.VISIBLE);
                CardViewer.setAlpha(0F);
                String ViewTmp = CardsArr[0];
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
            if (!card2Poped[0]){

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
            }else if (!CardViewerPoped[0]) {
                CardViewer.setVisibility(View.VISIBLE);
                CardViewer.setAlpha(0F);
                String ViewTmp = CardsArr[1];
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
            if (!card3Poped[0]){

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
            }else if (!CardViewerPoped[0]) {
                CardViewer.setVisibility(View.VISIBLE);
                CardViewer.setAlpha(0F);
                String ViewTmp = CardsArr[2];
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
            if (!card4Poped[0]){

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
            }else if (!CardViewerPoped[0]) {
                CardViewer.setVisibility(View.VISIBLE);
                CardViewer.setAlpha(0F);
                String ViewTmp = CardsArr[3];
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
            if (!card5Poped[0]){

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
            }else if (!CardViewerPoped[0]) {
                CardViewer.setVisibility(View.VISIBLE);
                CardViewer.setAlpha(0F);
                String ViewTmp = CardsArr[4];
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
        Crystal.HpCheck();

        if(GameEnded){
            EndTurn1.setEnabled(false);
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
            WinnerName.setText("Player 2 Wins");
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

        BoardCard1.setOnDragListener( (v, e) -> {

            // Handles each of the expected events.
            switch(e.getAction()) {

                case DragEvent.ACTION_DRAG_STARTED:

                    // Determines if this View can accept the dragged data.
                    if (e.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)&&BoardCards[0]==null) {

                        // As an example of what your application might do, applies a blue color tint
                        // to the View to indicate that it can accept data.
                        ((ImageView)v).setColorFilter(Color.BLUE);

                        // Invalidate the view to force a redraw in the new tint.
                        v.invalidate();

                        // Returns true to indicate that the View can accept the dragged data.
                        return true;

                    }else {

                        // Returns false to indicate that, during the current drag and drop operation,
                        // this View will not receive events again until ACTION_DRAG_ENDED is sent.
                        return false;
                    }
                case DragEvent.ACTION_DRAG_ENTERED:

                    // Applies a green tint to the View.
                    ((ImageView)v).setColorFilter(Color.GREEN);

                    // Invalidates the view to force a redraw in the new tint.
                    v.invalidate();

                    // Returns true; the value is ignored.
                    return true;

                case DragEvent.ACTION_DRAG_LOCATION:

                    // Ignore the event.
                    return true;

                case DragEvent.ACTION_DRAG_EXITED:



                    // Resets the color tint to blue.
                    ((ImageView)v).setColorFilter(Color.BLUE);

                    // Invalidates the view to force a redraw in the new tint.
                    v.invalidate();

                    // Returns true; the value is ignored.
                    return true;

                case DragEvent.ACTION_DROP:

                    // Gets the item containing the dragged data.
                    ClipData.Item item = e.getClipData().getItemAt(0);

                    // Gets the text data from the item.
                    CharSequence dragData = item.getText();

                    Cards.boardSet((String)dragData,BoardCard1);
                    BoardCards[0] = buffer[0];




                    // Displays a message containing the dragged data.
                    Toast.makeText(this, "Dragged data is " + dragData, Toast.LENGTH_SHORT).show();

                    // Turns off any color tints.
                    ((ImageView)v).clearColorFilter();

                    // Invalidates the view to force a redraw.
                    v.invalidate();

                    // Returns true. DragEvent.getResult() will return true.
                    return true;

                case DragEvent.ACTION_DRAG_ENDED:
                    if(!e.getResult()) {

                        CardReturner.Return(card1, card2, card3, card4, card5, CardsArr, buffer, AvailableBoardSlots);
                        CardsPlacedCount.getAndDecrement();
                    }

                    // Turns off any color tinting.
                    ((ImageView)v).clearColorFilter();

                    // Invalidates the view to force a redraw.
                    v.invalidate();

                    // Does a getResult(), and displays what happened.
                    if (e.getResult()) {
                        Toast.makeText(this, "The drop was handled.", Toast.LENGTH_SHORT).show();
                    }
                    // Returns true; the value is ignored.
                    return true;

                // An unknown action type was received.
                default:
                    Log.e("DragDrop Example","Unknown action type received by View.OnDragListener.");
                    break;
            }

            return false;

        });
        BoardCard2.setOnDragListener( (v, e) -> {

            // Handles each of the expected events.
            switch(e.getAction()) {

                case DragEvent.ACTION_DRAG_STARTED:

                    // Determines if this View can accept the dragged data.
                    if (e.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)&&BoardCards[1]==null) {

                        // As an example of what your application might do, applies a blue color tint
                        // to the View to indicate that it can accept data.
                        ((ImageView)v).setColorFilter(Color.BLUE);

                        // Invalidate the view to force a redraw in the new tint.
                        v.invalidate();

                        // Returns true to indicate that the View can accept the dragged data.
                        return true;

                    }else {

                        // Returns false to indicate that, during the current drag and drop operation,
                        // this View will not receive events again until ACTION_DRAG_ENDED is sent.
                        return false;
                    }
                case DragEvent.ACTION_DRAG_ENTERED:

                    // Applies a green tint to the View.
                    ((ImageView)v).setColorFilter(Color.GREEN);

                    // Invalidates the view to force a redraw in the new tint.
                    v.invalidate();

                    // Returns true; the value is ignored.
                    return true;

                case DragEvent.ACTION_DRAG_LOCATION:

                    // Ignore the event.
                    return true;

                case DragEvent.ACTION_DRAG_EXITED:


                    // Resets the color tint to blue.
                    ((ImageView)v).setColorFilter(Color.BLUE);

                    // Invalidates the view to force a redraw in the new tint.
                    v.invalidate();

                    // Returns true; the value is ignored.
                    return true;

                case DragEvent.ACTION_DROP:

                    // Gets the item containing the dragged data.
                    ClipData.Item item = e.getClipData().getItemAt(0);

                    // Gets the text data from the item.
                    CharSequence dragData = item.getText();

                    Cards.boardSet((String)dragData,BoardCard2);
                    BoardCards[1] = buffer[0];

                    // Displays a message containing the dragged data.
                    Toast.makeText(this, "Dragged data is " + dragData, Toast.LENGTH_SHORT).show();

                    // Turns off any color tints.
                    ((ImageView)v).clearColorFilter();
                    // Invalidates the view to force a redraw.
                    v.invalidate();

                    // Returns true. DragEvent.getResult() will return true.
                    return true;

                case DragEvent.ACTION_DRAG_ENDED:
                    if(!e.getResult()) {

                        CardReturner.Return(card1, card2, card3, card4, card5, CardsArr, buffer, AvailableBoardSlots);
                        CardsPlacedCount.getAndDecrement();
                    }else AvailableBoardSlots = CardsSet.NullCount(BoardCards);
                    // Turns off any color tinting.
                    ((ImageView)v).clearColorFilter();

                    // Invalidates the view to force a redraw.
                    v.invalidate();

                    // Does a getResult(), and displays what happened.
                    if (e.getResult()) {
                        Toast.makeText(this, "The drop was handled.", Toast.LENGTH_SHORT).show();
                    }
                    // Returns true; the value is ignored.
                    return true;

                // An unknown action type was received.
                default:
                    Log.e("DragDrop Example","Unknown action type received by View.OnDragListener.");
                    break;
            }

            return false;

        });
        BoardCard3.setOnDragListener( (v, e) -> {

            // Handles each of the expected events.
            switch(e.getAction()) {

                case DragEvent.ACTION_DRAG_STARTED:

                    // Determines if this View can accept the dragged data.
                    if (e.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)&&BoardCards[2]==null) {

                        // As an example of what your application might do, applies a blue color tint
                        // to the View to indicate that it can accept data.
                        ((ImageView)v).setColorFilter(Color.BLUE);

                        // Invalidate the view to force a redraw in the new tint.
                        v.invalidate();

                        // Returns true to indicate that the View can accept the dragged data.
                        return true;

                    }else {

                        // Returns false to indicate that, during the current drag and drop operation,
                        // this View will not receive events again until ACTION_DRAG_ENDED is sent.
                        return false;
                    }
                case DragEvent.ACTION_DRAG_ENTERED:

                    // Applies a green tint to the View.
                    ((ImageView)v).setColorFilter(Color.GREEN);

                    // Invalidates the view to force a redraw in the new tint.
                    v.invalidate();

                    // Returns true; the value is ignored.
                    return true;

                case DragEvent.ACTION_DRAG_LOCATION:

                    // Ignore the event.
                    return true;

                case DragEvent.ACTION_DRAG_EXITED:


                    // Resets the color tint to blue.
                    ((ImageView)v).setColorFilter(Color.BLUE);

                    // Invalidates the view to force a redraw in the new tint.
                    v.invalidate();

                    // Returns true; the value is ignored.
                    return true;

                case DragEvent.ACTION_DROP:

                    // Gets the item containing the dragged data.
                    ClipData.Item item = e.getClipData().getItemAt(0);

                    // Gets the text data from the item.
                    CharSequence dragData = item.getText();


                    Cards.boardSet((String)dragData,BoardCard3);
                    BoardCards[2] = buffer[0];

                    // Displays a message containing the dragged data.
                    Toast.makeText(this, "Dragged data is " + dragData, Toast.LENGTH_SHORT).show();

                    // Turns off any color tints.
                    ((ImageView)v).clearColorFilter();

                    // Invalidates the view to force a redraw.
                    v.invalidate();

                    // Returns true. DragEvent.getResult() will return true.
                    return true;

                case DragEvent.ACTION_DRAG_ENDED:
                    if(!e.getResult()) {
                        CardReturner.Return(card1, card2, card3, card4, card5, CardsArr, buffer, AvailableBoardSlots);
                        CardsPlacedCount.getAndDecrement();
                    }
                    else AvailableBoardSlots = CardsSet.NullCount(BoardCards);
                    // Turns off any color tinting.
                    ((ImageView)v).clearColorFilter();
                    Log.i("DragEvent", "ended: " + e.getResult());
                    // Invalidates the view to force a redraw.
                    v.invalidate();

                    // Does a getResult(), and displays what happened.
                    if (e.getResult()) {
                        Toast.makeText(this, "The drop was handled.", Toast.LENGTH_SHORT).show();
                    }
                    // Returns true; the value is ignored.
                    return true;

                // An unknown action type was received.
                default:
                    Log.e("DragDrop Example","Unknown action type received by View.OnDragListener.");
                    break;
            }

            return false;

        });
        BoardCard4.setOnDragListener( (v, e) -> {

            // Handles each of the expected events.
            switch(e.getAction()) {

                case DragEvent.ACTION_DRAG_STARTED:

                    // Determines if this View can accept the dragged data.
                    if (e.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)&&BoardCards[3]==null) {

                        // As an example of what your application might do, applies a blue color tint
                        // to the View to indicate that it can accept data.
                        ((ImageView)v).setColorFilter(Color.BLUE);

                        // Invalidate the view to force a redraw in the new tint.
                        v.invalidate();

                        // Returns true to indicate that the View can accept the dragged data.
                        return true;

                    }else {

                        // Returns false to indicate that, during the current drag and drop operation,
                        // this View will not receive events again until ACTION_DRAG_ENDED is sent.
                        return false;
                    }
                case DragEvent.ACTION_DRAG_ENTERED:

                    // Applies a green tint to the View.
                    ((ImageView)v).setColorFilter(Color.GREEN);

                    // Invalidates the view to force a redraw in the new tint.
                    v.invalidate();

                    // Returns true; the value is ignored.
                    return true;

                case DragEvent.ACTION_DRAG_LOCATION:

                    // Ignore the event.
                    return true;

                case DragEvent.ACTION_DRAG_EXITED:


                    // Resets the color tint to blue.
                    ((ImageView)v).setColorFilter(Color.BLUE);

                    // Invalidates the view to force a redraw in the new tint.
                    v.invalidate();

                    // Returns true; the value is ignored.
                    return true;

                case DragEvent.ACTION_DROP:

                    // Gets the item containing the dragged data.
                    ClipData.Item item = e.getClipData().getItemAt(0);

                    // Gets the text data from the item.
                    CharSequence dragData = item.getText();

                    Cards.boardSet((String)dragData,BoardCard4);
                    BoardCards[3] = buffer[0];

                    // Displays a message containing the dragged data.
                    Toast.makeText(this, "Dragged data is " + dragData, Toast.LENGTH_SHORT).show();

                    // Turns off any color tints.
                    ((ImageView)v).clearColorFilter();

                    // Invalidates the view to force a redraw.
                    v.invalidate();

                    // Returns true. DragEvent.getResult() will return true.
                    return true;

                case DragEvent.ACTION_DRAG_ENDED:
                    if(!e.getResult()) {

                        CardReturner.Return(card1, card2, card3, card4, card5, CardsArr, buffer, AvailableBoardSlots);
                        CardsPlacedCount.getAndDecrement();
                    }else AvailableBoardSlots = CardsSet.NullCount(BoardCards);
                    // Turns off any color tinting.
                    ((ImageView)v).clearColorFilter();

                    // Invalidates the view to force a redraw.
                    v.invalidate();

                    // Does a getResult(), and displays what happened.
                    if (e.getResult()) {
                        Toast.makeText(this, "The drop was handled.", Toast.LENGTH_SHORT).show();
                    }
                    // Returns true; the value is ignored.
                    return true;

                // An unknown action type was received.
                default:
                    Log.e("DragDrop Example","Unknown action type received by View.OnDragListener.");
                    break;
            }

            return false;

        });
        BoardCard5.setOnDragListener( (v, e) -> {

            // Handles each of the expected events.
            switch(e.getAction()) {

                case DragEvent.ACTION_DRAG_STARTED:

                    // Determines if this View can accept the dragged data.
                    if (e.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)&&BoardCards[4]==null) {

                        // As an example of what your application might do, applies a blue color tint
                        // to the View to indicate that it can accept data.
                        ((ImageView)v).setColorFilter(Color.BLUE);

                        // Invalidate the view to force a redraw in the new tint.
                        v.invalidate();

                        // Returns true to indicate that the View can accept the dragged data.
                        return true;

                    }else {

                        // Returns false to indicate that, during the current drag and drop operation,
                        // this View will not receive events again until ACTION_DRAG_ENDED is sent.
                        return false;
                    }
                case DragEvent.ACTION_DRAG_ENTERED:

                    // Applies a green tint to the View.
                    ((ImageView)v).setColorFilter(Color.GREEN);

                    // Invalidates the view to force a redraw in the new tint.
                    v.invalidate();

                    // Returns true; the value is ignored.
                    return true;

                case DragEvent.ACTION_DRAG_LOCATION:

                    // Ignore the event.
                    return true;

                case DragEvent.ACTION_DRAG_EXITED:


                    // Resets the color tint to blue.
                    ((ImageView)v).setColorFilter(Color.BLUE);

                    // Invalidates the view to force a redraw in the new tint.
                    v.invalidate();

                    // Returns true; the value is ignored.
                    return true;

                case DragEvent.ACTION_DROP:

                    // Gets the item containing the dragged data.
                    ClipData.Item item = e.getClipData().getItemAt(0);

                    // Gets the text data from the item.
                    CharSequence dragData = item.getText();


                    Cards.boardSet((String)dragData,BoardCard5);
                    BoardCards[4] = buffer[0];

                    // Displays a message containing the dragged data.
                    Toast.makeText(this, "Dragged data is " + dragData, Toast.LENGTH_SHORT).show();

                    // Turns off any color tints.
                    ((ImageView)v).clearColorFilter();

                    // Invalidates the view to force a redraw.
                    v.invalidate();

                    // Returns true. DragEvent.getResult() will return true.
                    return true;

                case DragEvent.ACTION_DRAG_ENDED:
                    if(!e.getResult()) {

                        CardReturner.Return(card1, card2, card3, card4, card5, CardsArr, buffer, AvailableBoardSlots);
                        CardsPlacedCount.getAndDecrement();
                    }else AvailableBoardSlots = CardsSet.NullCount(BoardCards);
                    // Turns off any color tinting.
                    ((ImageView)v).clearColorFilter();

                    // Invalidates the view to force a redraw.
                    v.invalidate();

                    // Does a getResult(), and displays what happened.
                    if (e.getResult()) {
                        Toast.makeText(this, "The drop was handled.", Toast.LENGTH_SHORT).show();
                    }
                    // Returns true; the value is ignored.
                    return true;

                // An unknown action type was received.
                default:
                    Log.e("DragDrop Example","Unknown action type received by View.OnDragListener.");
                    break;
            }

            return false;

        });
        BoardCard6.setOnDragListener( (v, e) -> {

            // Handles each of the expected events.
            switch(e.getAction()) {

                case DragEvent.ACTION_DRAG_STARTED:

                    // Determines if this View can accept the dragged data.
                    if (e.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)&&BoardCards[5]==null) {

                        // As an example of what your application might do, applies a blue color tint
                        // to the View to indicate that it can accept data.
                        ((ImageView)v).setColorFilter(Color.BLUE);

                        // Invalidate the view to force a redraw in the new tint.
                        v.invalidate();

                        // Returns true to indicate that the View can accept the dragged data.
                        return true;

                    }else {

                        // Returns false to indicate that, during the current drag and drop operation,
                        // this View will not receive events again until ACTION_DRAG_ENDED is sent.
                        return false;
                    }
                case DragEvent.ACTION_DRAG_ENTERED:

                    // Applies a green tint to the View.
                    ((ImageView)v).setColorFilter(Color.GREEN);

                    // Invalidates the view to force a redraw in the new tint.
                    v.invalidate();

                    // Returns true; the value is ignored.
                    return true;

                case DragEvent.ACTION_DRAG_LOCATION:

                    // Ignore the event.
                    return true;

                case DragEvent.ACTION_DRAG_EXITED:


                    // Resets the color tint to blue.
                    ((ImageView)v).setColorFilter(Color.BLUE);

                    // Invalidates the view to force a redraw in the new tint.
                    v.invalidate();

                    // Returns true; the value is ignored.
                    return true;

                case DragEvent.ACTION_DROP:

                    // Gets the item containing the dragged data.
                    ClipData.Item item = e.getClipData().getItemAt(0);

                    // Gets the text data from the item.
                    CharSequence dragData = item.getText();


                    Cards.boardSet((String)dragData,BoardCard6);
                    BoardCards[5] = buffer[0];

                    // Displays a message containing the dragged data.
                    Toast.makeText(this, "Dragged data is " + dragData, Toast.LENGTH_SHORT).show();

                    // Turns off any color tints.
                    ((ImageView)v).clearColorFilter();

                    // Invalidates the view to force a redraw.
                    v.invalidate();

                    // Returns true. DragEvent.getResult() will return true.
                    return true;

                case DragEvent.ACTION_DRAG_ENDED:
                    if(!e.getResult()) {

                        CardReturner.Return(card1, card2, card3, card4, card5, CardsArr, buffer, AvailableBoardSlots);
                        CardsPlacedCount.getAndDecrement();
                    }else AvailableBoardSlots = CardsSet.NullCount(BoardCards);
                    // Turns off any color tinting.
                    ((ImageView)v).clearColorFilter();

                    // Invalidates the view to force a redraw.
                    v.invalidate();

                    // Does a getResult(), and displays what happened.
                    if (e.getResult()) {
                        Toast.makeText(this, "The drop was handled.", Toast.LENGTH_SHORT).show();
                    }
                    // Returns true; the value is ignored.
                    return true;

                // An unknown action type was received.
                default:
                    Log.e("DragDrop","Unknown action type received by View.OnDragListener.");
                    break;
            }

            return false;

        });
        Log.i(TAG, "Cardsarr: " + Arrays.toString(CardsArr));
        shuffler.shuffle(false);
        Log.i(TAG, "Cardsarr: " + Arrays.toString(CardsArr));
        CardsSet.set(card1,card2,card3,card4,card5,CardsArr);

    }




    // TODO: 27.01.2023 forgotten achievement system :)
/*
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        Log.i(TAG, "onKeyDown: " + keyCode + "  key event  " + event);

        String channelId = "my_channel_id";
        CharSequence channelName = "My Channel";
        int importance = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            importance = NotificationManager.IMPORTANCE_HIGH;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);

            channel.setDescription("Achievement notify channel");


            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(channel);

        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.background)
                .setContentTitle("you've got an achievement!")
                .setContentText("take a screenshot")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);


        if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
            Log.d(TAG, "Screenshot Taken " + keyCode);
            notificationManager.notify(1, builder.build());
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
*/

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    protected static void GameOver1(){
        GameEnded = true;
        Log.i(TAG, "GameOver");
    }
}