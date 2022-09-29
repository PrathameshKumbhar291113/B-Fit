package com.prathameshkumbhar.bfit.mainmodule.data

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class ExerciseDetails (
    var id: Int? = null,
    var exerciseStepName : String? = null,
    var exerciseImageGif: String? = null,
    var exerciseRepetition : String? = null,
) : Parcelable