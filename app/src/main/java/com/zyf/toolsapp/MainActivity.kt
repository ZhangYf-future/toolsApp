package com.zyf.toolsapp


import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.zyf.viewlibrary.dialog.ChooseDateDialog

class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        enableEdgeToEdge()

        findViewById<Button>(R.id.btn_date_picker).setOnClickListener {
            ChooseDateDialog.Builder()
                .setTitle("请选择日期")
                .setConfirmButtonText("确定")
                .setCancelButtonText("取消")
                .setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.rect_gray_20))
                .setConfirmButtonClickCallback {

                }
                .build()
                .show(supportFragmentManager, null)

        }
    }
}