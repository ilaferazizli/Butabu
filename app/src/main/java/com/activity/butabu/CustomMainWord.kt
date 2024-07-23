package com.activity.butabu

import android.content.Context
import android.graphics.Typeface
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import com.activity.butabu.databinding.ActivitySoloGameBinding

class CustomMainWord (){
    private var hintIndex=0
    val editTextList = mutableListOf<EditText>()
    fun flexibleTextViews(mainWord:String,context: Context,binding:ActivitySoloGameBinding){
        for (i in mainWord.indices) {
            val editText = EditText(context)
            editText.setTextColor(context.getColor(R.color.yellow))
            editText.setBackgroundResource(R.drawable.bg_solo_word_correct)
            editText.textSize = 24f
            editText.width=80
            editText.filters = arrayOf(InputFilter.LengthFilter(1))
            editText.isSingleLine=true
            editText.setTypeface(editText.typeface, Typeface.BOLD)
            editText.textAlignment= View.TEXT_ALIGNMENT_CENTER
            editText.setPadding(8, 8, 8, 8)
            editText.setOnClickListener{
                changeEditTextsColor(R.drawable.bg_solo_word_correct)
            }

            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(8, 8, 8, 8)
            editText.layoutParams = params

            binding.textContainer.addView(editText)
            editTextList.add(editText)

            editText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (s?.length == 1 && i < editTextList.size - 1) {
                        editTextList[i + 1].requestFocus()
                    } else if (s?.length == 0 && i > 0) {
                        editTextList[i - 1].requestFocus()
                    }
                }
                override fun afterTextChanged(s: Editable?) {}
            })
        }
    }
    fun clearEditTexts(binding:ActivitySoloGameBinding) {
        binding.textContainer.removeAllViews()
        editTextList.clear()
        hintIndex=0
    }
    fun changeEditTextsColor(background:Int){
        for (editText in editTextList) {
            editText.setBackgroundResource(background)
        }
    }
    fun giveHint(word: String) {
        if (hintIndex < word.length) {
            editTextList[hintIndex].setText(word[hintIndex].toString())
            hintIndex++
        }
    }
    fun getCombinedWord(): String {
        val combinedWord = StringBuilder()
        for (editText in editTextList) {
            combinedWord.append(editText.text.toString())
        }
        return combinedWord.toString()
    }
}