package com.ct.util;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;

import com.ct.inter.OnUpdateListener;


/**
 * 项目名称：CTFrame.
 * 创建人： CT.
 * 创建时间: 2017/6/13.
 * GitHub:https://github.com/CNHTT
 */

public class AnimationUtils {

    /**
     * 颜色渐变动画
     *
     * @param beforeColor 变化之前的颜色
     * @param afterColor  变化之后的颜色
     * @param listener    变化事件
     */
    public static void animationColorGradient(int beforeColor, int afterColor, final OnUpdateListener listener) {
        ValueAnimator valueAnimator = ValueAnimator.ofObject(new ArgbEvaluator(), beforeColor, afterColor).setDuration(3000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
//                textView.setTextColor((Integer) animation.getAnimatedValue());
                listener.onUpdate((Integer) animation.getAnimatedValue());
            }
        });
        valueAnimator.start();
    }

    /**
     * 卡片翻转动画
     *
     * @param beforeView
     * @param AfterView
     */
    public static void cardFilpAnimation(final View beforeView, final View AfterView) {
        Interpolator accelerator = new AccelerateInterpolator();
        Interpolator decelerator = new DecelerateInterpolator();
        if (beforeView.getVisibility() == View.GONE) {
            // 局部layout可达到字体翻转 背景不翻转
            ImageUtils.invisToVis = ObjectAnimator.ofFloat(beforeView,
                    "rotationY", -90f, 0f);
            ImageUtils.visToInvis = ObjectAnimator.ofFloat(AfterView,
                    "rotationY", 0f, 90f);
        } else if (AfterView.getVisibility() == View.GONE) {
            ImageUtils.invisToVis = ObjectAnimator.ofFloat(AfterView,
                    "rotationY", -90f, 0f);
            ImageUtils.visToInvis = ObjectAnimator.ofFloat(beforeView,
                    "rotationY", 0f, 90f);
        }

        ImageUtils.visToInvis.setDuration(250);// 翻转速度
        ImageUtils.visToInvis.setInterpolator(accelerator);// 在动画开始的地方速率改变比较慢，然后开始加速
        ImageUtils.invisToVis.setDuration(250);
        ImageUtils.invisToVis.setInterpolator(decelerator);
        ImageUtils.visToInvis.addListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationEnd(Animator arg0) {
                if (beforeView.getVisibility() == View.GONE) {
                    AfterView.setVisibility(View.GONE);
                    ImageUtils.invisToVis.start();
                    beforeView.setVisibility(View.VISIBLE);
                } else {
                    AfterView.setVisibility(View.GONE);
                    ImageUtils.visToInvis.start();
                    beforeView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onAnimationCancel(Animator arg0) {

            }

            @Override
            public void onAnimationRepeat(Animator arg0) {

            }

            @Override
            public void onAnimationStart(Animator arg0) {

            }
        });
        ImageUtils.visToInvis.start();
    }

    /**
     * 缩小
     *
     * @param view
     */
    public static void zoomIn(final View view, float scale, float dist) {
        view.setPivotY(view.getHeight());
        view.setPivotX(view.getWidth() / 2);
        AnimatorSet mAnimatorSet = new AnimatorSet();
        ObjectAnimator mAnimatorScaleX = ObjectAnimator.ofFloat(view, "scaleX", 1.0f, scale);
        ObjectAnimator mAnimatorScaleY = ObjectAnimator.ofFloat(view, "scaleY", 1.0f, scale);
        ObjectAnimator mAnimatorTranslateY = ObjectAnimator.ofFloat(view, "translationY", 0.0f, -dist);

        mAnimatorSet.play(mAnimatorTranslateY).with(mAnimatorScaleX);
        mAnimatorSet.play(mAnimatorScaleX).with(mAnimatorScaleY);
        mAnimatorSet.setDuration(300);
        mAnimatorSet.start();
    }

    /**
     * f放大
     *
     * @param view
     */
    public static void zoomOut(final View view, float scale) {
        view.setPivotY(view.getHeight());
        view.setPivotX(view.getWidth() / 2);
        AnimatorSet mAnimatorSet = new AnimatorSet();

        ObjectAnimator mAnimatorScaleX = ObjectAnimator.ofFloat(view, "scaleX", scale, 1.0f);
        ObjectAnimator mAnimatorScaleY = ObjectAnimator.ofFloat(view, "scaleY", scale, 1.0f);
        ObjectAnimator mAnimatorTranslateY = ObjectAnimator.ofFloat(view, "translationY", view.getTranslationY(), 0);

        mAnimatorSet.play(mAnimatorTranslateY).with(mAnimatorScaleX);
        mAnimatorSet.play(mAnimatorScaleX).with(mAnimatorScaleY);
        mAnimatorSet.setDuration(300);
        mAnimatorSet.start();
    }

}
