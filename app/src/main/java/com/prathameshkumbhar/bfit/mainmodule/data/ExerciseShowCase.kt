package com.prathameshkumbhar.bfit.mainmodule.data

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class ExerciseShowCase(
    var id: Int,
    var exerciseTitle: String? = null,
    var exerciseImageUrlMale: String? = null,
    var exerciseImageUrlFemale : String? = null,
    var exerciseRatingBeginner: String? = null,
    var exerciseRatingIntermediate: String? = null,
    var exerciseRatingAdvance: String? = null,
    var exerciseStepsList: List<ExerciseDetails>
): Parcelable


