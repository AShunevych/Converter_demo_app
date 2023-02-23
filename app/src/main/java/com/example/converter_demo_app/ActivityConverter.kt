package com.example.converter_demo_app

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.ashunevich.conversionlibrary.UnitConverter
import com.example.converter_demo_app.Utils.appendMinusPlus
import com.example.converter_demo_app.Utils.clearView
import com.example.converter_demo_app.Utils.correctValue
import com.example.converter_demo_app.Utils.textIsLessThanFifteenSymbols
import com.example.converter_demo_app.databinding.ConverterActivityBinding

open class ActivityConverter : Fragment() {
    private var binding: ConverterActivityBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = ConverterActivityBinding.inflate(inflater, container, false)
        setSpinnerOnTabPositionChange(DEFAULT_POS)
        setUnitMeasurement()
        Utils.blockInput(binding!!.resultView, binding!!.valueEdit)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initTabPositionViewModel()
        initKeyBoardViewModel()
        setSpinnerListener(binding!!.spinnerValue)
        setSpinnerListener(binding!!.spinnerResult)
        addTextWatcher()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initTabPositionViewModel() {
        val tabPositionViewModel = Utils.generateViewModel(requireActivity())
        tabPositionViewModel.getSelected().observe(
            viewLifecycleOwner
        ) { tabPos: Int -> setSpinnerOnTabPositionChange(tabPos) }
    }

    private fun initKeyBoardViewModel() {
        val keyboardViewModel = Utils.generateViewModel(requireActivity())
        keyboardViewModel.postedNumber.observe(
            viewLifecycleOwner
        ) { event: String? ->
            if (event != null) {
                setViewModelTextReceiver(event)
            }
        }
    }

    private fun setAdapter(array: Array<String>) {
        binding!!.spinnerValue.adapter = arrayAdapterFactory(array = array)
        binding!!.spinnerResult.adapter = arrayAdapterFactory(array = array)
    }

    private fun arrayAdapterFactory(array: Array<String>): ArrayAdapter<String> {
        return ArrayAdapter(
            requireContext(),
            R.layout.custom_spinner_item, array
        )
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    private fun setViewModelTextReceiver(event: String) {
        if (event == Utils.SYMBOL_CORRECT || event == Utils.SYMBOL_CLEAR || event == Utils.SYMBOL_CHECK) {
            when (event) {
                Utils.SYMBOL_CORRECT -> correctValue(
                    binding!!.valueEdit, binding!!.resultView
                )
                Utils.SYMBOL_CLEAR -> clearView(
                    binding!!.valueEdit, binding!!.resultView
                )
                Utils.SYMBOL_CHECK -> appendMinusPlus(
                    binding!!.valueEdit
                )
            }
        } else {
            binding!!.valueEdit.append(event)
        }
    }

    // Filling spinners with values
    private fun setSpinnerOnTabPositionChange(tabPos: Int) {
        when (tabPos) {
            0 -> setAdapter(resources.getStringArray(R.array.weight))
            1 -> setAdapter(resources.getStringArray(R.array.length))
            2 -> setAdapter(resources.getStringArray(R.array.volume))
            3 -> setAdapter(resources.getStringArray(R.array.area))
            4 -> setAdapter(resources.getStringArray(R.array.force))
            5 -> setAdapter(resources.getStringArray(R.array.temperature_array))
            6 -> setAdapter(resources.getStringArray(R.array.time_array))
            7 -> setAdapter(resources.getStringArray(R.array.speed))
        }
    }

    // if user changes unit - it will change measurements and will automatically recalculate result
    private fun setSpinnerListener(spinner: Spinner) {
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>?,
                selectedItemView: View,
                position: Int,
                id: Long
            ) {
                convertAndShowValues(Utils.returnLocale())
                setUnitMeasurement()
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {}
        }
    }

    // Auto conversion when user add number to value for convert
    private fun addTextWatcher() {
        binding!!.valueEdit.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                if (textIsLessThanFifteenSymbols(textview = binding!!.valueEdit)) {
                    binding!!.valueEdit.setText(s.toString().substring(0, 15))
                    binding!!.valueEdit.setSelection(s.length - 1)
                    showToast()
                }
                convertAndShowValues(Utils.returnLocale())
            }

            override fun beforeTextChanged(
                s: CharSequence,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence,
                start: Int,
                before: Int,
                count: Int
            ) {
            }
        })
    }

    protected fun showToast() {
        Toast.makeText(activity, resources.getString(R.string.maxNumberReached), Toast.LENGTH_SHORT)
            .show()
    }

    // set units of measurements for value
    private fun setUnitMeasurement() {
        Utils.measurementUnitsHandler(
            Utils.getSpinnerValueString(binding!!.spinnerValue),
            binding!!.valueUnit
        )
        Utils.measurementUnitsHandler(
            Utils.getSpinnerValueString(binding!!.spinnerResult),
            binding!!.resultUnit
        )
    }

    private fun convertAndShowValues(activeLocale: String) {
        if (TextUtils.isEmpty(binding!!.valueEdit.text.toString()) or (binding!!.valueEdit.text.toString() == "-")) {
            binding!!.resultView.setText("")
        } else {
            if (activeLocale == "українська") {
                binding!!.resultView.setText(
                    UnitConverter.ConvertValues_Ukr(
                        Utils.getSpinnerValueString(
                            binding!!.spinnerValue
                        ),
                        Utils.getSpinnerValueString(binding!!.spinnerResult),
                        stringToDouble()
                    )
                )
            } else {
                binding!!.resultView.setText(
                    UnitConverter.ConvertValues(
                        Utils.getSpinnerValueString(
                            binding!!.spinnerValue
                        ),
                        Utils.getSpinnerValueString(binding!!.spinnerResult),
                        stringToDouble()
                    )
                )
            }
        }
    }

    private fun stringToDouble(): Double {
        return binding!!.valueEdit.text.toString().toDouble()
    }

    companion object {
        private const val DEFAULT_POS = 0
    }
}
