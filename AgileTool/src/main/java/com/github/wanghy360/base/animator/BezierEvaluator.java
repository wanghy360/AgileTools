/*
 * Copyright (C) 2015, 程序亦非猿
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.wanghy360.base.animator;

import android.animation.TypeEvaluator;
import android.graphics.PointF;

public class BezierEvaluator implements TypeEvaluator<PointF> {

    private PointF pointF1;
    private PointF pointF2;

    public BezierEvaluator(PointF pointF1, PointF pointF2) {
        this.pointF1 = pointF1;
        this.pointF2 = pointF2;
    }

    @Override
    public PointF evaluate(float fraction, PointF startValue,
                           PointF endValue) {

        float fractionLeft = 1.0f - fraction;
        PointF point = new PointF();//结果

        point.x = fractionLeft * fractionLeft * fractionLeft * (startValue.x)
                + 3 * fractionLeft * fractionLeft * fraction * (pointF1.x)
                + 3 * fractionLeft * fraction * fraction * (pointF2.x)
                + fraction * fraction * fraction * (endValue.x);

        point.y = fractionLeft * fractionLeft * fractionLeft * (startValue.y)
                + 3 * fractionLeft * fractionLeft * fraction * (pointF1.y)
                + 3 * fractionLeft * fraction * fraction * (pointF2.y)
                + fraction * fraction * fraction * (endValue.y);
        return point;
    }
}
