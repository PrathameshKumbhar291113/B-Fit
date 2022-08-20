package com.prathameshkumbhar.bfit.coremodule

import android.text.InputFilter

object InputFilters {
    val emailFilter = InputFilter { source, _, _, _, _, _ ->
        source?.filter { char -> char.isLetterOrDigit() || char =='@' || char == '.' } }
}