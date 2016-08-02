package com.itemanimation;

import android.support.v4.animation.AnimatorCompatHelper;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

/**
 * Created by tiv on 02.08.2016.
 */
public class CustomItemAnimation extends DefaultItemAnimator {
    private static final String TAG = CustomItemAnimation.class.getSimpleName();

    @Override
    public boolean animateRemove(RecyclerView.ViewHolder holder) {
        Log.d(TAG, "animateRemove() called with: " + "holder = [" + holder + "]");
        resetAnimation(holder);
       animateRemoveImpl(holder);
        return true;
    }

    private void resetAnimation(RecyclerView.ViewHolder holder) {
        AnimatorCompatHelper.clearInterpolator(holder.itemView);
        endAnimation(holder);
    }
    private void animateRemoveImpl(final RecyclerView.ViewHolder holder) {
        final View view = holder.itemView;
        Log.d(TAG, "animateRemoveImpl: Right" + view.getRight() + " Width " + view.getWidth());
        final ViewPropertyAnimatorCompat animation = ViewCompat.animate(view);
        animation.setDuration(500/*getRemoveDuration()*/)
                .translationX(view.getWidth()).setListener(new ViewPropertyAnimatorListener() {
            @Override
            public void onAnimationStart(View view) {
                dispatchRemoveStarting(holder);
            }

            @Override
            public void onAnimationEnd(View view) {
                animation.setListener(null);
                ViewCompat.setAlpha(view, 1);
                dispatchRemoveFinished(holder);
            }

            @Override
            public void onAnimationCancel(View view) {

            }
        }).start();
    }
}
