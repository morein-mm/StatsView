package ru.netology.statsview.ui

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.core.content.withStyledAttributes
import ru.netology.statsview.R
import ru.netology.statsview.utils.AndroidUtils
import kotlin.math.min
import kotlin.random.Random

class StatView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0,
) : View(
    context,
    attributeSet,
    defStyleAttr,
    defStyleRes,
) {
    private var textSize = AndroidUtils.dp(context, 20).toFloat()
    private var lineWigth = AndroidUtils.dp(context, 5).toFloat()
    private var colors = emptyList<Int>()

    private var progress = 0F
    private var valueAnimator: ValueAnimator? = null

    init {
        context.withStyledAttributes(attributeSet, R.styleable.StatView) {
            textSize = getDimension(R.styleable.StatView_textSize, textSize)
            lineWigth = getDimension(R.styleable.StatView_lineWidth, lineWigth)
            colors = listOf(
                getColor(R.styleable.StatView_color1, generateRandomColor()),
                getColor(R.styleable.StatView_color2, generateRandomColor()),
                getColor(R.styleable.StatView_color3, generateRandomColor()),
                getColor(R.styleable.StatView_color4, generateRandomColor()),
            )
        }
    }

    var data: List<Float> = emptyList()
        set(value) {
            field = value
//            invalidate()
            update()
        }
    private var radius = 0F
    private var center = PointF()
    private var oval = RectF()

    private val paint = Paint(
        Paint.ANTI_ALIAS_FLAG
    ).apply {
        strokeWidth = this@StatView.lineWigth
        style = Paint.Style.STROKE
        strokeJoin = Paint.Join.ROUND
        strokeCap = Paint.Cap.ROUND
    }
    private val textPaint = Paint(
        Paint.ANTI_ALIAS_FLAG
    ).apply {
        textSize = this@StatView.textSize
        style = Paint.Style.FILL
        textAlign = Paint.Align.CENTER
    }


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        radius = min(w, h) / 2F - lineWigth
        center = PointF(w / 2F, h / 2F)
        oval = RectF(
            center.x - radius,
            center.y - radius,
            center.x + radius,
            center.y + radius
        )
    }

    override fun onDraw(canvas: Canvas) {
        if (data.isEmpty()) {
            return
        }


        var startAngle = -90F
        var firstColor = 0
        data.forEachIndexed { index, datum ->
//            val el = datum / data.sum()
//            val angle = el * 360F
            val angle = datum * 360F
            paint.color = colors.getOrElse(index) { generateRandomColor() }
//            if (index == 0 ) {
//                firstColor = paint.color
//            }
            canvas.drawArc(oval, startAngle + 360F * progress, angle * progress, false, paint)
            startAngle += angle
        }

//        if (data.size > 1) {
//            paint.color = firstColor
//            canvas.drawArc(oval, -90F, 1F, false, paint)
//        }

        canvas.drawText(
//            "%.2f%%".format(data.sum() * 100),
            "%.2f%%".format(data.sum() * 100),
//            "100.00%",
            center.x,
            center.y + textPaint.textSize / 4,
            textPaint
        )
    }

    private fun generateRandomColor() = Random.nextInt(0xFF000000.toInt(), 0xFFFFFFFF.toInt())

    private fun update() {
        valueAnimator?.let {
            it.removeAllListeners()
            it.cancel()
        }
        progress = 0F

        valueAnimator = ValueAnimator.ofFloat(0F, 1F).apply {
            addUpdateListener { anim ->
                progress = anim.animatedValue as Float
                invalidate()
            }
            duration = 10000
            interpolator = LinearInterpolator()
        }.also {
            it.start()
        }


    }
}