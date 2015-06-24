package com.d1qkens.onixtestingapp;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;


public class FlipSignActivity extends Activity {

    private boolean mIsHeads;
    private AnimatorSet mFlipper;
    private Bitmap mHeadsImage, mTailsImage;
    private ImageView mFlipImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin_flip_animation);

        mHeadsImage = BitmapFactory.decodeResource(getResources(), R.drawable.batmansign);
        mTailsImage = BitmapFactory.decodeResource(getResources(), R.drawable.jokersign);

        mFlipImage = (ImageView) findViewById(R.id.flip_image);
        mFlipImage.setImageBitmap(mHeadsImage);
        mIsHeads = true;

        mFlipper = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.flip);
        mFlipper.setTarget(mFlipImage);

        ObjectAnimator flipAnimator = (ObjectAnimator) mFlipper.getChildAnimations().get(0);

        flipAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                if (animation.getAnimatedFraction() >= 0.25f && mIsHeads) {
                    mFlipImage.setImageBitmap(mTailsImage);
                    mIsHeads = false;
                }
                if (animation.getAnimatedFraction() >= 0.75f && !mIsHeads) {
                    mFlipImage.setImageBitmap(mHeadsImage);
                    mIsHeads = true;
                }
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            mFlipper.start();
            return true;
        }
        return super.onTouchEvent(event);
    }

}