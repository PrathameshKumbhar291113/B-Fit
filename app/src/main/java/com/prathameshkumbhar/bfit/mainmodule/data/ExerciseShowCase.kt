package com.prathameshkumbhar.bfit.mainmodule.data

data class ExerciseShowCase(
    var id: Int,
    var exerciseTitle: String? = null,
    var exerciseImageUrl : String? = null,
    var exerciseRating : String? = null,
    var exerciseStepsList: List<ExerciseDetails>
)


