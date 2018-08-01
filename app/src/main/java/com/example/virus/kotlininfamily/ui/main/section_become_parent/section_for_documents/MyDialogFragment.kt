package com.example.virus.kotlininfamily.ui.main.section_become_parent.section_for_documents


import android.content.res.Resources
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.virus.kotlininfamily.R
import kotlinx.android.synthetic.main.fragment_dialog.view.*
import android.util.DisplayMetrics
import com.example.virus.kotlininfamily.R.layout.fragment_dialog
import android.widget.LinearLayout




/**
 * Created by admin on 7/31/18.
 */
class MyDialogFragment : DialogFragment() {
    internal var mNum: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mNum = getArguments()!!.getInt("num")

        // Pick a style based on the num.
        var style = DialogFragment.STYLE_NORMAL
        var theme = 0
        when ((mNum - 1) % 6) {
            1 -> style = DialogFragment.STYLE_NO_TITLE
            2 -> style = DialogFragment.STYLE_NO_FRAME
            3 -> style = DialogFragment.STYLE_NO_INPUT
            4 -> style = DialogFragment.STYLE_NORMAL
            5 -> style = DialogFragment.STYLE_NORMAL
            6 -> style = DialogFragment.STYLE_NO_TITLE
            7 -> style = DialogFragment.STYLE_NO_FRAME
            8 -> style = DialogFragment.STYLE_NORMAL
        }
        when ((mNum - 1) % 6) {
            4 -> theme = android.R.style.Theme_Holo
            5 -> theme = android.R.style.Theme_Holo_Light_Dialog
            6 -> theme = android.R.style.Theme_Holo_Light
            7 -> theme = android.R.style.Theme_Holo_Light_Panel
            8 -> theme = android.R.style.Theme_Holo_Light
        }
        setStyle(style, theme)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_dialog, container)
//        v.dialog_text.text = ("Dialog #" + mNum + ": using style "
//                + (mNum))


        val button = v.findViewById(R.id.show) as Button
        button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                // When button is clicked, call up to owning activity.
                dialog.dismiss()
            }
        })

        return v
    }

    fun getScreenWidth(): Int {
        return Resources.getSystem().getDisplayMetrics().widthPixels
    }

    fun getScreenHeight(): Int {
        return Resources.getSystem().getDisplayMetrics().heightPixels
    }

    companion object {

        /**
         * Create a new instance of MyDialogFragment, providing "num"
         * as an argument.
         */
        internal fun newInstance(num: Int): MyDialogFragment {
            val f = MyDialogFragment()

            // Supply num input as an argument.
            val args = Bundle()
            args.putInt("num", num)
            f.setArguments(args)

            return f
        }
    }
}