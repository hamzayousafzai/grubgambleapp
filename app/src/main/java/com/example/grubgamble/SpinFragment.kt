package com.example.grubgamble

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button


class SpinFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_spin, container, false)

        view.findViewById<Button>(R.id.buttonFilter).setOnClickListener {
            mListener!!.goToFilters() //go to Filters page
        }
        return view
    }
    var mListener: ISpinListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mListener = context as ISpinListener
    }

    interface ISpinListener {
        fun goToFilters()
    }

}