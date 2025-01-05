package com.lior.myapplication;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.graphics.PointF;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.lior.myapplication.model.Card;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity implements View.OnTouchListener {

    ImageView tile;
    float initialX, initialY;
    ArrayList<PointF> validPositions = new ArrayList<>();
    ArrayList<TileInfo> placedTiles = new ArrayList<>();
    private static final float SNAP_THRESHOLD = 50f; // Maximum distance for snapping

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tile = findViewById(R.id.tile);
        tile.setOnTouchListener(this);

        validPositions.add(new PointF(100f, 200f));
        validPositions.add(new PointF(300f, 400f));
    }

    private void snapToClosestTile(View tile) {
        PointF closestPosition = null;
        float minDistance = Float.MAX_VALUE;

        for (PointF position : validPositions) {
            // Calculate the distance between the tile and the valid position
            float distance = (float) Math.hypot(tile.getX() - position.x, tile.getY() - position.y);

            if (distance < minDistance) {
                minDistance = distance;
                closestPosition = position;
            }
        }

        if (closestPosition != null && minDistance < SNAP_THRESHOLD) {
            // Snap to the closest valid position
            animateSnap(tile, closestPosition.x, closestPosition.y);
        }
        // Else: Do nothing; the tile stays where the user dropped it.
    }

    public class TileInfo {
        public View tileView;
        public float x, y;
        public int leftValue, rightValue;

        public TileInfo(View tileView, float x, float y, int leftValue, int rightValue) {
            this.tileView = tileView;
            this.x = x;
            this.y = y;
            this.leftValue = leftValue;
            this.rightValue = rightValue;
        }
    }

    private void animateSnap(View tile, float targetX, float targetY) {
        tile.animate()
                .x(targetX)
                .y(targetY)
                .setDuration(200)
                .start();
    }
    public void createCards()
    {
        Card[] gamecards=new Card[28];
        for (int i=0;i<=28;i++)//loop on index
        {
            for(int j=0;j<=6;j++)//loop on half1
            {
                for(int k=0;k<=6;k++)//loop on half2
                {
                    Card c=new Card(j,k);
                    gamecards[i]=c;
                }
            }
        }
    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // Store initial position
                initialX = v.getX();
                initialY = v.getY();
                return true;

            case MotionEvent.ACTION_MOVE:
                v.setX(event.getRawX() - v.getWidth() / 2);
                v.setY(event.getRawY() - v.getHeight() / 2);
                return true;

            case MotionEvent.ACTION_UP:
                snapToClosestTile(v);
                return true;
            default:
                return false;

        }
    }
}
