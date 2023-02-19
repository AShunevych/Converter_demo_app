package com.example.converter_demo_app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import android.view.View
import androidx.fragment.app.Fragment
import com.example.converter_demo_app.Utils.postTextOnClick
import com.example.converter_demo_app.databinding.CalculatorKeyboardBinding

class FragmentKeyboard : Fragment() {
    private var binding: CalculatorKeyboardBinding? = null
    var model: AppViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = CalculatorKeyboardBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        model = ViewModelProvider(requireActivity()).get(AppViewModel::class.java)
        setButtonBindingsCalcKeyboard()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setButtonBindingsCalcKeyboard() {
        postTextOnClick(model, binding!!.butOne)
        postTextOnClick(model, binding!!.butTwo)
        postTextOnClick(model, binding!!.butThree)
        postTextOnClick(model, binding!!.butFour)
        postTextOnClick(model, binding!!.butFive)
        postTextOnClick(model, binding!!.butSix)
        postTextOnClick(model, binding!!.butSeven)
        postTextOnClick(model, binding!!.buttonEight)
        postTextOnClick(model, binding!!.butNine)
        postTextOnClick(model, binding!!.buttonZero)
        postTextOnClick(model, binding!!.buttonDzero)
        postTextOnClick(model, binding!!.buttonDecimal)
        postTextOnClick(model, binding!!.butDuzhky)
        postTextOnClick(model, binding!!.butPercent)
        postTextOnClick(model, binding!!.butDivide)
        postTextOnClick(model, binding!!.butMultiply)
        postTextOnClick(model, binding!!.butMinus)
        postTextOnClick(model, binding!!.butPlus)
        postTextOnClick(model, binding!!.butSolve)
        postTextOnClick(model, binding!!.butClearView)
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}