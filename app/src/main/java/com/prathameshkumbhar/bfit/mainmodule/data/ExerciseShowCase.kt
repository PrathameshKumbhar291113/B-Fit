package com.prathameshkumbhar.bfit.mainmodule.data

data class ExerciseShowCase(
    var exerciseTitle: String,
    var exerciseStepsList: List<ExerciseSteps>,
    var exerciseImageUrl : String,
    var exerciseRating : String? = null
)


