package com.example.project_immortal_fire;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.DragEvent;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;

import java.util.Arrays;

import java.util.concurrent.atomic.AtomicInteger;

public class PlayerOne extends AppCompatActivity {

    public static final int CardsCount = 13;
    int AvailableBoardSlots = 6;

    static boolean IsFirst = true;
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
        ImageView CardViewer = findViewById(R.id.CardViewer);
        ImageView card1 = findViewById(R.id.Card1);
        ImageView card2 = findViewById(R.id.Card2);
        ImageView card3 = findViewById(R.id.Card3);
        ImageView card4 = findViewById(R.id.Card4);
        ImageView card5 = findViewById(R.id.Card5);
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


        if(IsFirst){
            TurnScreen.setVisibility(View.GONE);
            Log.i("player1", "IsFirst made gone " + IsFirst);
        }
        else {
            TurnScreen.setVisibility(View.VISIBLE);
            Log.i("player1", "IsFirst made visible " + IsFirst);
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
            Log.i("player1", "atomic count: " + CardsPlacedCount.get());
            Intent i = new Intent(PlayerOne.this, PlayerTwo.class);
            Bundle extras = new Bundle();
            extras.putStringArray("BoardCards",BoardCards);
            extras.putStringArray("EnemyCards",EnemyCards);
            i.putExtras(extras);
            startActivity(i);
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
                Log.i("card1", "long click listener: " + Arrays.toString(buffer));
                CardsArr[0] = "none";
                CardsSet.renew(card1,card2,card3,card4,card5,CardsArr);
                ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());
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
                CardsArr[1] = "none";
                CardsSet.renew(card1,card2,card3,card4,card5,CardsArr);
                ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());
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
                CardsArr[2] = "none";
                CardsSet.renew(card1,card2,card3,card4,card5,CardsArr);
                ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());
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
                CardsArr[3] = "none";
                CardsSet.renew(card1,card2,card3,card4,card5,CardsArr);
                ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());
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

                CardsArr[4] = "none";
                CardsSet.renew(card1,card2,card3,card4,card5,CardsArr);
                ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());
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
                CardViewer.setImageDrawable(card1.getDrawable());
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
                CardViewer.setImageDrawable(card2.getDrawable());
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
                CardViewer.setImageDrawable(card3.getDrawable());
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
                CardViewer.setImageDrawable(card4.getDrawable());
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
                CardViewer.setImageDrawable(card5.getDrawable());
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

                    if ("Card1".contentEquals(dragData)) {
                        BoardCard1.setImageDrawable(card1.getDrawable());
                        BoardCards[0] = buffer[0];
                    } else if ("Card2".contentEquals(dragData)) {
                        BoardCard1.setImageDrawable(card2.getDrawable());
                        BoardCards[0] = buffer[0];
                    } else if ("Card3".contentEquals(dragData)) {
                        BoardCard1.setImageDrawable(card3.getDrawable());
                        BoardCards[0] = buffer[0];
                    } else if ("Card4".contentEquals(dragData)) {
                        BoardCard1.setImageDrawable(card4.getDrawable());
                        BoardCards[0] = buffer[0];
                    } else if ("Card5".contentEquals(dragData)) {
                        BoardCard1.setImageDrawable(card5.getDrawable());
                        BoardCards[0] = buffer[0];
                    } else {
                        throw new IllegalStateException("Unexpected value: " + dragData);
                    }

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

                    if ("Card1".contentEquals(dragData)) {
                        BoardCard2.setImageDrawable(card1.getDrawable());
                        BoardCards[1] = buffer[0];
                    } else if ("Card2".contentEquals(dragData)) {
                        BoardCard2.setImageDrawable(card2.getDrawable());
                        BoardCards[1] = buffer[0];
                    } else if ("Card3".contentEquals(dragData)) {
                        BoardCard2.setImageDrawable(card3.getDrawable());
                        BoardCards[1] = buffer[0];
                    } else if ("Card4".contentEquals(dragData)) {
                        BoardCard2.setImageDrawable(card4.getDrawable());
                        BoardCards[1] = buffer[0];
                    } else if ("Card5".contentEquals(dragData)) {
                        BoardCard2.setImageDrawable(card5.getDrawable());
                        BoardCards[1] = buffer[0];
                    } else {
                        throw new IllegalStateException("Unexpected value: " + dragData);
                    }

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

                    if ("Card1".contentEquals(dragData)) {
                        BoardCard3.setImageDrawable(card1.getDrawable());
                        BoardCards[2] = buffer[0];
                    } else if ("Card2".contentEquals(dragData)) {
                        BoardCard3.setImageDrawable(card2.getDrawable());
                        BoardCards[2] = buffer[0];
                    } else if ("Card3".contentEquals(dragData)) {
                        BoardCard3.setImageDrawable(card3.getDrawable());
                        BoardCards[2] = buffer[0];
                    } else if ("Card4".contentEquals(dragData)) {
                        BoardCard3.setImageDrawable(card4.getDrawable());
                        BoardCards[2] = buffer[0];
                    } else if ("Card5".contentEquals(dragData)) {
                        BoardCard3.setImageDrawable(card5.getDrawable());
                        BoardCards[2] = buffer[0];
                    } else {
                        throw new IllegalStateException("Unexpected value: " + dragData);
                    }

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

                    if ("Card1".contentEquals(dragData)) {
                        BoardCard4.setImageDrawable(card1.getDrawable());
                        BoardCards[3] = buffer[0];
                    } else if ("Card2".contentEquals(dragData)) {
                        BoardCard4.setImageDrawable(card2.getDrawable());
                        BoardCards[3] = buffer[0];
                    } else if ("Card3".contentEquals(dragData)) {
                        BoardCard4.setImageDrawable(card3.getDrawable());
                        BoardCards[3] = buffer[0];
                    } else if ("Card4".contentEquals(dragData)) {
                        BoardCard4.setImageDrawable(card4.getDrawable());
                        BoardCards[3] = buffer[0];
                    } else if ("Card5".contentEquals(dragData)) {
                        BoardCard4.setImageDrawable(card5.getDrawable());
                        BoardCards[3] = buffer[0];
                    } else {
                        throw new IllegalStateException("Unexpected value: " + dragData);
                    }

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

                    if ("Card1".contentEquals(dragData)) {
                        BoardCard5.setImageDrawable(card1.getDrawable());
                        BoardCards[4] = buffer[0];
                    } else if ("Card2".contentEquals(dragData)) {
                        BoardCard5.setImageDrawable(card2.getDrawable());
                        BoardCards[4] = buffer[0];
                    } else if ("Card3".contentEquals(dragData)) {
                        BoardCard5.setImageDrawable(card3.getDrawable());
                        BoardCards[4] = buffer[0];
                    } else if ("Card4".contentEquals(dragData)) {
                        BoardCard5.setImageDrawable(card4.getDrawable());
                        BoardCards[4] = buffer[0];
                    } else if ("Card5".contentEquals(dragData)) {
                        BoardCard5.setImageDrawable(card5.getDrawable());
                        BoardCards[4] = buffer[0];
                    } else {
                        throw new IllegalStateException("Unexpected value: " + dragData);
                    }

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

                    if ("Card1".contentEquals(dragData)) {
                        BoardCard6.setImageDrawable(card1.getDrawable());
                        BoardCards[5] = buffer[0];
                    } else if ("Card2".contentEquals(dragData)) {
                        BoardCard6.setImageDrawable(card2.getDrawable());
                        BoardCards[5] = buffer[0];
                    } else if ("Card3".contentEquals(dragData)) {
                        BoardCard6.setImageDrawable(card3.getDrawable());
                        BoardCards[5] = buffer[0];
                    } else if ("Card4".contentEquals(dragData)) {
                        BoardCard6.setImageDrawable(card4.getDrawable());
                        BoardCards[5] = buffer[0];
                    } else if ("Card5".contentEquals(dragData)) {
                        BoardCard6.setImageDrawable(card5.getDrawable());
                        BoardCards[5] = buffer[0];
                    } else {
                        throw new IllegalStateException("Unexpected value: " + dragData);
                    }

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
        Log.i("Player1", "Cardsarr: " + Arrays.toString(CardsArr));
        shuffler.shuffle(false);
        Log.i("Player1", "Cardsarr: " + Arrays.toString(CardsArr));
        CardsSet.set(card1,card2,card3,card4,card5,CardsArr);

    }



    // TODO: 27.01.2023 forgotten achievement system
/*
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        Log.i("player1", "onKeyDown: " + keyCode + "  key event  " + event);

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
            Log.d("player1", "Screenshot Taken " + keyCode);
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
}