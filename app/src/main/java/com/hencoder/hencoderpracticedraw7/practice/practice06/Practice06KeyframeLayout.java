package com.hencoder.hencoderpracticedraw7.practice.practice06;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.hencoder.hencoderpracticedraw7.R;

public class Practice06KeyframeLayout extends RelativeLayout {
    Practice06KeyframeView view;
    Button animateBt;

    public Practice06KeyframeLayout(Context context) {
        super(context);
    }

    public Practice06KeyframeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice06KeyframeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        view = (Practice06KeyframeView) findViewById(R.id.objectAnimatorView);
        animateBt = (Button) findViewById(R.id.animateBt);

        animateBt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 除了合并多个属性和调配多个动画，你还可以在 PropertyValuesHolder 的基础上更进一步，
                 * 通过设置  Keyframe （关键帧），把同一个动画属性拆分成多个阶段。
                 * 例如，你可以让一个进度增加到 100% 后再「反弹」回来。
                 * */

                // 使用 Keyframe.ofFloat() 来为 view 的 progress 属性创建关键帧
                // 初始帧：progress 为 0
                // 时间进行到一半：progress 为 100
                // 结束帧：progress 回落到 80
                // 使用 PropertyValuesHolder.ofKeyframe() 来把关键帧拼接成一个完整的属性动画方案
                // 使用 ObjectAnimator.ofPropertyValuesHolder() 来创建动画

                Keyframe keyframe1 = Keyframe.ofFloat(0,0);
                // 时间经过 50% 的时候，动画完成度 100%
                Keyframe keyframe2 = Keyframe.ofFloat(0.5f,100);
                //时间到100%时 动画完成度80%
                Keyframe keyframe3 = Keyframe.ofFloat(1, 80);
                PropertyValuesHolder holder = PropertyValuesHolder.ofKeyframe("progress", keyframe1, keyframe2, keyframe3);

                ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(view,holder).setDuration(2000);
                animator.start();
            }
        });
    }
}
