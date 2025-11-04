package com.zyf.viewlibrary.dialog

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.DatePicker
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.zyf.viewlibrary.R
import java.util.Calendar

class ChooseDateDialog(): DialogFragment() {


    class Builder{
        private var titleStr: String? = null
        private var confirmStr: String? = null
        private var cancelStr: String? = null

        private var backgroundDrawable: Drawable? = null
        private var confirmCallback: ((Long) -> Unit)? = null

        fun setTitle(title: String?): Builder{
            this.titleStr = title
            return this
        }

        fun getTitle(): String? = titleStr

        fun setConfirmButtonText(text: String?): Builder{
            this.confirmStr = text
            return this
        }

        fun getConfirmButtonText() = confirmStr

        fun setCancelButtonText(text: String?): Builder{
            this.cancelStr = text
            return this
        }

        fun getCancelButtonText() = cancelStr

        fun setConfirmButtonClickCallback(callback: ((Long) -> Unit)?): Builder{
            this.confirmCallback = callback
            return this
        }

        fun getConfirmButtonClickCallback() = confirmCallback

        fun setBackgroundDrawable(drawable: Drawable?): Builder{
            this.backgroundDrawable = drawable
            return this
        }

        fun getBackgroundDrawable() = backgroundDrawable

        fun build(): ChooseDateDialog{
            return ChooseDateDialog(this)
        }
    }


    private var titleStr: String? = null
    private var confirmStr: String? = null
    private var cancelStr: String? = null
    private var backgroundDrawable: Drawable? = null
    private var confirmCallback: ((Long) -> Unit)? = null


    private var contentRootView: LinearLayout? = null
    private var titleView: TextView? = null
    private var datePicker: DatePicker? = null
    private var confirmBtn: Button? = null
    private var cancelBtn: Button? = null

    constructor(builder: Builder): this(){
        this.titleStr = builder.getTitle()
        this.confirmStr = builder.getConfirmButtonText()
        this.cancelStr = builder.getCancelButtonText()
        this.backgroundDrawable = builder.getBackgroundDrawable()
        this.confirmCallback = builder.getConfirmButtonClickCallback()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.DialogStyle)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.let {
            val params = it.attributes
            params.width = WindowManager.LayoutParams.MATCH_PARENT
            params.height = WindowManager.LayoutParams.WRAP_CONTENT
            params.gravity = Gravity.CENTER
            it.attributes = params
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_choose_date, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        contentRootView = view.findViewById(R.id.content_root)
        titleView = view.findViewById(R.id.tv_title)
        datePicker = view.findViewById(R.id.date_picker)
        confirmBtn = view.findViewById(R.id.btn_confirm)
        cancelBtn = view.findViewById(R.id.btn_cancel)

//        titleView?.setTextAppearance()

        contentRootView?.background = backgroundDrawable
        titleView?.text = titleStr
        confirmBtn?.text = confirmStr
        cancelBtn?.text = cancelStr

        confirmBtn?.setOnClickListener {
            datePicker?.let { picker ->
                val calendar = Calendar.getInstance()
                calendar.set(Calendar.YEAR, picker.year)
                calendar.set(Calendar.MONTH, picker.month)
                calendar.set(Calendar.DAY_OF_MONTH, picker.dayOfMonth)
                calendar.set(Calendar.HOUR, 0)
                calendar.set(Calendar.MINUTE,0)
                calendar.set(Calendar.SECOND,0)
                calendar.set(Calendar.MILLISECOND, 0)
                confirmCallback?.invoke(calendar.timeInMillis)
            }
            dismiss()
        }

        cancelBtn?.setOnClickListener {

            dismiss()
        }
    }

}