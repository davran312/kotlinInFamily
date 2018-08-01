package com.example.virus.kotlininfamily.ui.main.section_become_parent.section_for_documents


import android.content.res.Resources
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.virus.kotlininfamily.R
import kotlinx.android.synthetic.main.fragment_dialog.view.*


/**
 * Created by admin on 7/31/18.
 */
class MyDialogFragment : DialogFragment() {
    internal var mNum: Int = 0



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_dialog, container)
        mNum = getArguments()!!.getInt("num")


        val button = v.findViewById(R.id.show) as TextView
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