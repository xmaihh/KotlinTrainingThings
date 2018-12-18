package ac.kotlin.screenxy

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView

/**
 * Create by Administrator on 2018-12-18
 */
class ScreenXYView : SurfaceView, SurfaceHolder.Callback {
    // 文字笔画
    private val textPaint = Paint()
    // 圆形画笔
    private val touchPaints = arrayOfNulls<Paint>(MAX_TOUCHPOINTS)
    // 对应每一个圆形画笔的颜色
    private val colors = IntArray(MAX_TOUCHPOINTS)
    // 记录屏幕的宽度和高度
    var xywidth: Int = 0
    var xyheight: Int = 0
    // 放大的倍数
    private var scale = 2.0f


    constructor(context: Context) : super(context) {
        // 得到当前的view的surfaceHolder对象
        val holder = holder
        // 设置当前holder的回调方法
        holder.addCallback(this)
        // 确保我们的View能获得输入焦点
        isFocusable = true
        // 确保能接受到触屏事件
        isFocusableInTouchMode = true
        init()
    }


    private fun init() {
        // 初始化文字笔的颜色
        textPaint.color = Color.WHITE
        // 定义十种按键的颜色值
        colors[0] = Color.BLUE//蓝色
        colors[1] = Color.RED//红色
        colors[2] = Color.GREEN//绿色
        colors[3] = Color.YELLOW//黄色
        colors[4] = Color.CYAN//蓝绿色
        colors[5] = Color.MAGENTA//洋红色
        colors[6] = Color.DKGRAY//深灰色
        colors[7] = Color.WHITE//白色
        colors[8] = Color.LTGRAY//浅灰色
        colors[9] = Color.GRAY//灰色
        // 分别初始化每个手指的颜色值的笔
        for (i in 0 until MAX_TOUCHPOINTS) {
            touchPaints[i] = Paint()
            touchPaints[i]?.color = colors[i]
        }
    }


    /**
     * 处理触屏事件
     */
    override fun onTouchEvent(event: MotionEvent): Boolean {
        // 获得屏幕触点数量
        var pointerCount = event.pointerCount
        if (pointerCount > MAX_TOUCHPOINTS) {
            pointerCount = MAX_TOUCHPOINTS
        }
        // 锁定Canvas，开始进行相应的界面处理
        val c = holder.lockCanvas()
        if (c != null) {
            // 定义canvas的背景颜色值为黑色
            c.drawColor(Color.BLACK)
            if (event.action == MotionEvent.ACTION_UP) {
                // 当手离开屏幕是，清屏
            } else {
                // 先在屏幕上画一个十字，横向贯穿屏幕，纵向贯穿屏幕
                for (i in 0 until pointerCount) {
                    // 获取一个触点的坐标，然后开始绘制
                    val id = event.getPointerId(i)
                    val x = event.getX(i).toInt()
                    val y = event.getY(i).toInt()
                    touchPaints[id]?.let { drawCrossHairsAndText(x, y, it, i, id, c) }
                }
                // 使用不同的颜色在每个手指的位置画圆
                for (i in 0 until pointerCount) {
                    val id = event.getPointerId(i)
                    val x = event.getX(i).toInt()
                    val y = event.getY(i).toInt()
                    touchPaints[id]?.let { drawCircle(x, y, it, c) }
                }
            }
            // 画完后，解锁显示
            holder.unlockCanvasAndPost(c)
        }
        return true
    }

    /**
     * 画十字交叉线及坐标信息
     * @param x:线的x坐标
     * @param y:线的y坐标
     * @param paint:线的颜色
     * @param ptr:第几个点
     * @param id:id值
     * @param c:画布
     */
    private fun drawCrossHairsAndText(
        x: Int, y: Int, paint: Paint,
        ptr: Int, id: Int, c: Canvas
    ) {
        // 在（0，y）和（xywidth ，y）这两个点上画直线
        c.drawLine(0f, y.toFloat(), xywidth.toFloat(), y.toFloat(), paint)
        // 在（x，0）和（x，xyheight）这两个点上画直线
        c.drawLine(x.toFloat(), 0f, x.toFloat(), xyheight.toFloat(), paint)
        // 定义文字的大小
        val textY = ((20 + 25 * ptr) * scale).toInt()
        // 画出x的值
        c.drawText("x$ptr=$x", 10 * scale, textY.toFloat(), textPaint)
        // 画出Y的值
        c.drawText("y$ptr=$y", 90 * scale, textY.toFloat(), textPaint)
        // 画出id的值
        c.drawText("id$ptr=$id", xywidth - 55 * scale, textY.toFloat(), textPaint)
    }


    /**
     * 画手指单击的实心圆
     * @param x:实心圆的x值
     * @param y:实心圆的y值
     * @param paint:实心圆的画笔
     * @param c:在这个画布上画
     */
    private fun drawCircle(x: Int, y: Int, paint: Paint, c: Canvas) {
        // 在canvas上画圆
        c.drawCircle(x.toFloat(), y.toFloat(), 20 * scale, paint)
    }


    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)


    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)


    //在创建时激发，一般在这里调用画图的线程。
    override fun surfaceCreated(holder: SurfaceHolder) {


    }


    //在surface的大小发生改变时激发
    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {


        // 得到屏幕的宽度
        this.xywidth = width
        // 得到屏幕的高度
        this.xyheight = height
        // 得到屏幕的放大比例
        if (width > height) {
            this.scale = width / 480f
            this.scale = height / 480f
        }
        // 通过放大比例计算出字体大小
        textPaint.textSize = 20 * scale
        // 得到当前View的holder对象
        val c = getHolder().lockCanvas()
        // 设置背景为黑色
        if (c != null) {
            // 背景黑色
            c.drawColor(Color.BLACK)
            // 在屏幕中间画上提示语
            val tWidth = textPaint.measureText(START_TEXT)
            c.drawText(START_TEXT, width / 2 - tWidth / 2, (height / 2).toFloat(), textPaint)
            // 解锁显示
            getHolder().unlockCanvasAndPost(c)
        }
    }

    //销毁时激发，一般在这里将画图的线程停止、释放。
    override fun surfaceDestroyed(holder: SurfaceHolder) {


    }

    companion object {
        // 最多的触摸点数量
        private const val MAX_TOUCHPOINTS = 10
        // 提示问题
        private const val START_TEXT = "请单击或多点触摸屏幕进行测试"
    }
}
