package com.prathameshkumbhar.bfit.mainmodule.data

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class ExerciseDetails (
    var id: Int,
    var exerciseStepName : String? = null,
    var exerciseImageGif: Int? = null,
    var exerciseRepetitionBeginner : String? = null,
    var exerciseRepetitionIntermediate : String? = null,
    var exerciseRepetitionAdvance : String? = null,
    var exerciseStepsDetailTips : String? = null
) : Parcelable