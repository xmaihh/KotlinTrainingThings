package ac.kotlin.screenxy

import ac.kotlin.R
import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.TextView

class ScreenXYActivity : Activity() {

    var tv: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        setContentView(ScreenXYView(this))
        tv = findViewById(R.id.tv)
        hideBottomUIMenu(this)
    }

//    ACTION_DOWN:用户按下屏幕的事件
//    ACTION_MOVE:用户滑动的时间
//    ACTION_UP:用户手指从按下状态抬起屏幕的时间
//
//    getAction方法：得到操作时间的类型
//    getDwonTime方法：得到用户按下的时间
//    getEventTime方法：得到用户操作的时间
//    getPressure方法：得到用户的触摸压力值

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (MotionEvent.ACTION_DOWN == event!!.action) {
            var x = event.x
            var y = event.y
            tv!!.text = "您点击的位置是:\nx: " + x + "\ny: " + y
        }
        return super.onTouchEvent(event)
    }

    /**
     * 隐藏虚拟按键，并且全屏
     *
     * @param activity
     */
    fun hideBottomUIMenu(activity: Activity) {
        // 隐藏虚拟按键,并且全屏
        if (Build.VERSION.SDK_INT in 12..18) {
            var v = activity.window.decorView
            v.systemUiVisibility = View.GONE
        } else {
            if (Build.VERSION.SDK_INT >= 19) {
                var decorView = activity.window.decorView
                var uiOptions = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or View.SYSTEM_UI_FLAG_FULLSCREEN)
                decorView.systemUiVisibility = uiOptions
            }
        }
    }
}
