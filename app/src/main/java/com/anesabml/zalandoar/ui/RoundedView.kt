package com.anesabml.zalandoar.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.core.content.withStyledAttributes
import com.anesabml.zalandoar.R

class RoundedView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAtt: Int = 0,
    defStyleRes: Int = 0
) : View(context, attributeSet, defStyleAtt, defStyleRes) {

    private var _paint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.FILL
    }
    private val _rect = RectF(0F, 0F, 0F, 0F)
    private val _rectPath = Path()
    private var _width = 0
    private var _height = 0

    private var _cornerRadius = 0F
    var cornerRadius
        set(value) {
            _cornerRadius = value
        }
        get() = _cornerRadius

    private var _color = Color.BLACK
    var color
        set(value) {
            _color = value
        }
        get() = _color

    init {
        context.withStyledAttributes(attributeSet, R.styleable.RoundedView) {
            _cornerRadius = getDimension(R.styleable.RoundedView_cornerRadius, 0F)
            _color = getColor(R.styleable.RoundedView_color, Color.BLACK)
        }
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

        _width = right - left
        _height = bottom - top

        _rect.set(0F, 0F, _width.toFloat(), _height.toFloat())
        _rectPath.addRoundRect(_rect, cornerRadius, cornerRadius, Path.Direction.CW)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.clipPath(_rectPath)
        _paint.color = _color
        canvas.drawRoundRect(
            _rect,
            _cornerRadius,
            _cornerRadius,
            _paint
        )
    }
}