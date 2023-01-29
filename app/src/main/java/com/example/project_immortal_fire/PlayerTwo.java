package com.example.project_immortal_fire;

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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.card.MaterialCardView;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class PlayerTwo extends AppCompatActivity {
    int AvailableBoardSlots2 = 6;
    public static String[] BoardCards2 = new String[]{null, null, null, null, null, null};
    public static String[] EnemyCards2 = new String[]{null, null, null, null, null, null};
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
        ImageView EnemyCard1 = findViewById(R.id.EnemyCard1);
        ImageView EnemyCard2 = findViewById(R.id.EnemyCard2);
        ImageView EnemyCard3 = findViewById(R.id.EnemyCard3);
        ImageView EnemyCard4 = findViewById(R.id.EnemyCard4);
        ImageView EnemyCard5 = findViewById(R.id.EnemyCard5);
        ImageView EnemyCard6 = findViewById(R.id.EnemyCard6);
        ImageView CardViewer = findViewById(R.id.CardViewer);
        ImageView BoardCard1 = findViewById(R.id.BoardCard1);
        ImageView BoardCard2 = findViewById(R.id.BoardCard2);
        ImageView BoardCard3 = findViewById(R.id.BoardCard3);
        ImageView BoardCard4 = findViewById(R.id.BoardCard4);
        ImageView BoardCard5 = findViewById(R.id.BoardCard5);
        ImageView BoardCard6 = findViewById(R.id.BoardCard6);
        ImageView EndTurn2 = findViewById(R.id.EndTurn2);

        CardsSet.boardset(BoardCard1, BoardCard2, BoardCard3, BoardCard4, BoardCard5, BoardCard6, BoardCards2);
        CardsSet.enemyset(EnemyCard1, EnemyCard2, EnemyCard3, EnemyCard4, EnemyCard5, EnemyCard6, EnemyCards2);

        Log.i("EnemyCards", "onCreate: " + Arrays.toString(EnemyCards2));

        TurnScreen.setVisibility(View.VISIBLE);

        card1.setTag(IMAGEVIEW_TAG_CARD1);
        card2.setTag(IMAGEVIEW_TAG_CARD2);
        card3.setTag(IMAGEVIEW_TAG_CARD3);
        card4.setTag(IMAGEVIEW_TAG_CARD4);
        card5.setTag(IMAGEVIEW_TAG_CARD5);
        EndTurn2.setOnClickListener(view -> {
            Log.i("boardCards", "array: " + Arrays.toString(BoardCards2) + "visibility: " + BoardCard1.getVisibility() + BoardCard2.getVisibility() + BoardCard3.getVisibility() + BoardCard4.getVisibility() + BoardCard5.getVisibility() + BoardCard6.getVisibility());
            Intent i1 = new Intent(PlayerTwo.this, PlayerOne.class);
            Bundle extras = new Bundle();
            extras.putStringArray("BoardCards2", BoardCards2);
            extras.putStringArray("EnemyCards2", EnemyCards2);
            i1.putExtras(extras);

            startActivity(i1);
        });

        TurnScreen.setOnTouchListener((view, motionEvent) -> {
            TurnScreen.setVisibility(View.GONE);
            return true;
        });

        card1.setOnLongClickListener(v -> {

            if (card1Poped[0]&& CardsPlacedCount2.get()<2) {
                CardsPlacedCount2.getAndIncrement();
                ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());
                ClipData dragData = new ClipData(
                        (CharSequence) v.getTag(),
                        new String[]{ClipDescription.MIMETYPE_TEXT_PLAIN},
                        item);
                View.DragShadowBuilder myShadow = new myDragShadowBuilder.MyDragShadowBuilder(card1);
                v.startDragAndDrop(dragData, myShadow, null, 0);
                buffer[0] = CardsArr2[0];
                buffer[1] = "0";
                CardsArr2[0] = "none";
                CardsSet.renew(card1, card2, card3, card4, card5, CardsArr2);
                return true;
            } else return false;
        });
        card2.setOnLongClickListener(v -> {

            if (card2Poped[0]&& CardsPlacedCount2.get()<2) {
                CardsPlacedCount2.getAndIncrement();
                buffer[0] = CardsArr2[1];
                buffer[1] = "1";
                CardsArr2[1] = "none";
                CardsSet.renew(card1, card2, card3, card4, card5, CardsArr2);
                ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());
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
                CardsPlacedCount2.getAndIncrement();
                buffer[0] = CardsArr2[2];
                buffer[1] = "2";
                CardsArr2[2] = "none";
                CardsSet.renew(card1, card2, card3, card4, card5, CardsArr2);
                ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());
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
                CardsPlacedCount2.getAndIncrement();
                buffer[0] = CardsArr2[3];
                buffer[1] = "3";
                CardsArr2[3] = "none";
                CardsSet.renew(card1, card2, card3, card4, card5, CardsArr2);
                ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());
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
                CardsPlacedCount2.getAndIncrement();
                buffer[0] = CardsArr2[4];
                buffer[1] = "4";
                CardsArr2[4] = "none";
                CardsSet.renew(card1, card2, card3, card4, card5, CardsArr2);
                ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());
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

        card1.setOnClickListener(view -> {
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
                CardViewer.setImageDrawable(card1.getBackground());
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
                CardViewer.setImageDrawable(card5.getBackground());
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
                CardViewer.setImageDrawable(card3.getBackground());
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
                CardViewer.setImageDrawable(card4.getBackground());
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
                CardViewer.setImageDrawable(card5.getBackground());
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

                    // Gets the item containing the dragged data.
                    ClipData.Item item = e.getClipData().getItemAt(0);

                    // Gets the text data from the item.
                    CharSequence dragData = item.getText();

                    if ("Card1".contentEquals(dragData)) {
                        BoardCard1.setImageDrawable(card1.getBackground());
                        BoardCards2[0] = buffer[0];
                    } else if ("Card2".contentEquals(dragData)) {
                        BoardCard1.setImageDrawable(card2.getBackground());
                        BoardCards2[0] = buffer[0];
                    } else if ("Card3".contentEquals(dragData)) {
                        BoardCard1.setImageDrawable(card3.getBackground());
                        BoardCards2[0] = buffer[0];
                    } else if ("Card4".contentEquals(dragData)) {
                        BoardCard1.setImageDrawable(card4.getBackground());
                        BoardCards2[0] = buffer[0];
                    } else if ("Card5".contentEquals(dragData)) {
                        BoardCard1.setImageDrawable(card5.getBackground());
                        BoardCards2[0] = buffer[0];
                    } else {
                        throw new IllegalStateException("Unexpected value: " + dragData);
                    }

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
                        CardsPlacedCount2.getAndDecrement();
                    }

                    // Turns off any color tinting.
                    ((ImageView) v).clearColorFilter();

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

                    // Gets the item containing the dragged data.
                    ClipData.Item item = e.getClipData().getItemAt(0);

                    // Gets the text data from the item.
                    CharSequence dragData = item.getText();

                    if ("Card1".contentEquals(dragData)) {
                        BoardCard2.setImageDrawable(card1.getBackground());
                        BoardCards2[1] = buffer[0];
                    } else if ("Card2".contentEquals(dragData)) {
                        BoardCard2.setImageDrawable(card2.getBackground());
                        BoardCards2[1] = buffer[0];
                    } else if ("Card3".contentEquals(dragData)) {
                        BoardCard2.setImageDrawable(card3.getBackground());
                        BoardCards2[1] = buffer[0];
                    } else if ("Card4".contentEquals(dragData)) {
                        BoardCard2.setImageDrawable(card4.getBackground());
                        BoardCards2[1] = buffer[0];
                    } else if ("Card5".contentEquals(dragData)) {
                        BoardCard2.setImageDrawable(card5.getBackground());
                        BoardCards2[1] = buffer[0];
                    } else {
                        throw new IllegalStateException("Unexpected value: " + dragData);
                    }

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
                        CardsPlacedCount2.getAndDecrement();
                    }

                    // Turns off any color tinting.
                    ((ImageView) v).clearColorFilter();

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

                    // Gets the item containing the dragged data.
                    ClipData.Item item = e.getClipData().getItemAt(0);

                    // Gets the text data from the item.
                    CharSequence dragData = item.getText();

                    if ("Card1".contentEquals(dragData)) {
                        BoardCard3.setImageDrawable(card1.getBackground());
                        BoardCards2[2] = buffer[0];
                    } else if ("Card2".contentEquals(dragData)) {
                        BoardCard3.setImageDrawable(card2.getBackground());
                        BoardCards2[2] = buffer[0];
                    } else if ("Card3".contentEquals(dragData)) {
                        BoardCard3.setImageDrawable(card3.getBackground());
                        BoardCards2[2] = buffer[0];
                    } else if ("Card4".contentEquals(dragData)) {
                        BoardCard3.setImageDrawable(card4.getBackground());
                        BoardCards2[2] = buffer[0];
                    } else if ("Card5".contentEquals(dragData)) {
                        BoardCard3.setImageDrawable(card5.getBackground());
                        BoardCards2[2] = buffer[0];
                    } else {
                        throw new IllegalStateException("Unexpected value: " + dragData);
                    }

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
                        CardsPlacedCount2.getAndDecrement();
                    }

                    // Turns off any color tinting.
                    ((ImageView) v).clearColorFilter();

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

                    // Gets the item containing the dragged data.
                    ClipData.Item item = e.getClipData().getItemAt(0);

                    // Gets the text data from the item.
                    CharSequence dragData = item.getText();

                    if ("Card1".contentEquals(dragData)) {
                        BoardCard4.setImageDrawable(card1.getBackground());
                        BoardCards2[3] = buffer[0];
                    } else if ("Card2".contentEquals(dragData)) {
                        BoardCard4.setImageDrawable(card2.getBackground());
                        BoardCards2[3] = buffer[0];
                    } else if ("Card3".contentEquals(dragData)) {
                        BoardCard4.setImageDrawable(card3.getBackground());
                        BoardCards2[3] = buffer[0];
                    } else if ("Card4".contentEquals(dragData)) {
                        BoardCard4.setImageDrawable(card4.getBackground());
                        BoardCards2[3] = buffer[0];
                    } else if ("Card5".contentEquals(dragData)) {
                        BoardCard4.setImageDrawable(card5.getBackground());
                        BoardCards2[3] = buffer[0];
                    } else {
                        throw new IllegalStateException("Unexpected value: " + dragData);
                    }

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
                        CardsPlacedCount2.getAndDecrement();
                    }
                    // Turns off any color tinting.
                    ((ImageView) v).clearColorFilter();

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

                    // Gets the item containing the dragged data.
                    ClipData.Item item = e.getClipData().getItemAt(0);

                    // Gets the text data from the item.
                    CharSequence dragData = item.getText();

                    if ("Card1".contentEquals(dragData)) {
                        BoardCard5.setImageDrawable(card1.getBackground());
                        BoardCards2[4] = buffer[0];
                    } else if ("Card2".contentEquals(dragData)) {
                        BoardCard5.setImageDrawable(card2.getBackground());
                        BoardCards2[4] = buffer[0];
                    } else if ("Card3".contentEquals(dragData)) {
                        BoardCard5.setImageDrawable(card3.getBackground());
                        BoardCards2[4] = buffer[0];
                    } else if ("Card4".contentEquals(dragData)) {
                        BoardCard5.setImageDrawable(card4.getBackground());
                        BoardCards2[4] = buffer[0];
                    } else if ("Card5".contentEquals(dragData)) {
                        BoardCard5.setImageDrawable(card5.getBackground());
                        BoardCards2[4] = buffer[0];
                    } else {
                        throw new IllegalStateException("Unexpected value: " + dragData);
                    }

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
                        CardsPlacedCount2.getAndDecrement();
                    }

                    // Turns off any color tinting.
                    ((ImageView) v).clearColorFilter();

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

                    // Gets the item containing the dragged data.
                    ClipData.Item item = e.getClipData().getItemAt(0);

                    // Gets the text data from the item.
                    CharSequence dragData = item.getText();

                    if ("Card1".contentEquals(dragData)) {
                        BoardCard6.setImageDrawable(card1.getBackground());
                        BoardCards2[5] = buffer[0];
                    } else if ("Card2".contentEquals(dragData)) {
                        BoardCard6.setImageDrawable(card2.getBackground());
                        BoardCards2[5] = buffer[0];
                    } else if ("Card3".contentEquals(dragData)) {
                        BoardCard6.setImageDrawable(card3.getBackground());
                        BoardCards2[5] = buffer[0];
                    } else if ("Card4".contentEquals(dragData)) {
                        BoardCard6.setImageDrawable(card4.getBackground());
                        BoardCards2[5] = buffer[0];
                    } else if ("Card5".contentEquals(dragData)) {
                        BoardCard6.setImageDrawable(card5.getBackground());
                        BoardCards2[5] = buffer[0];
                    } else {
                        throw new IllegalStateException("Unexpected value: " + dragData);
                    }

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
                        CardsPlacedCount2.getAndDecrement();
                    }
                    // Turns off any color tinting.
                    ((ImageView) v).clearColorFilter();

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

    }


}