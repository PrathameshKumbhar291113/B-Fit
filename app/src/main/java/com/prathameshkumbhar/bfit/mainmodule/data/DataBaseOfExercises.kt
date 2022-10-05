package com.prathameshkumbhar.bfit.mainmodule.data

val UPPER_BODY = listOf(
    ExerciseDetails(
        0,"JUMPING JACKS", "https://musclewiki.com/media/uploads/Crunch-Front-021316.gif","x10"
    ),
    ExerciseDetails(1,"INCLINE PUSH-UPS",null,"x16"),
    ExerciseDetails(2,"KNEE PUSH-UPS",null,"x10"),
    ExerciseDetails(3,"PUSH-UPS",null,"x8"),
    ExerciseDetails(4,"WIDE ARM PUSH-UPS",null,"x8"),
    ExerciseDetails(5,"INCLINE PUSH-UPS",null,"x16"),
    ExerciseDetails(6,"KNEE PUSH-UPS",null,"x10"),
    ExerciseDetails(7,"PUSH-UPS",null,"x8"),
    ExerciseDetails(8,"WIDE ARM PUSH-UPS",null,"x8"),
    ExerciseDetails(9,"COBRA STRETCH",null,"x3")

)
val LOWER_BODY = listOf(
    ExerciseDetails(0,"JUMPING JACKS", null,"x10"),
    ExerciseDetails(1,"HALF-SQUATS",null,"x12"),
    ExerciseDetails(2,"FULL-SQUATS",null,"x10"),
    ExerciseDetails(3,"SIDE-LYING LEG LIFT LEFT",null,"x10"),
    ExerciseDetails(4,"SIDE-LYING LEG LIFT RIGHT",null,"x10"),
    ExerciseDetails(5,"BACKWARD LUNGE",null,"x15"),
    ExerciseDetails(6,"DONKEY KICKS LEFT",null,"x10"),
    ExerciseDetails(7,"DONKEY KICKS RIGHT",null,"x10"),
    ExerciseDetails(8,"SIDE-LYING LEG LIFT LEFT",null,"x8"),
    ExerciseDetails(9,"SIDE-LYING LEG LIFT RIGHT",null,"x8"),
    ExerciseDetails(10,"BACKWARD LUNGE",null,"x10"),
    ExerciseDetails(11,"DONKEY KICKS LEFT",null,"x8"),
    ExerciseDetails(12,"DONKEY KICKS RIGHT",null,"x8"),
    ExerciseDetails(13,"COBRA STRETCH",null,"x3")
)
val ABS = listOf(
    ExerciseDetails(2,"Jumping Jacks", null,"10x2"),
    ExerciseDetails(2,"Touch Toe",null,"10x2")
)
val CHEST = listOf(
    ExerciseDetails(3,"Jumping Jacks", null,"10x2"),
    ExerciseDetails(3,"Touch Toe",null,"10x2")
)
val SHOULDER = listOf(
    ExerciseDetails(4,"Jumping Jacks", null,"10x2"),
    ExerciseDetails(4,"Touch Toe",null,"10x2")
)
val BACK = listOf(
    ExerciseDetails(5,"Jumping Jacks", null,"10x2"),
    ExerciseDetails(5,"Touch Toe",null,"10x2")
)
val BICEPS = listOf(
    ExerciseDetails(6,"Jumping Jacks", null,"10x2"),
    ExerciseDetails(6,"Touch Toe",null,"10x2")
)
val TRICEPS = listOf(
    ExerciseDetails(7,"Jumping Jacks", null,"10x2"),
    ExerciseDetails(7,"Touch Toe",null,"10x2")
)

// Use this as your master database. This should be added at the top level of file.
val EXERCISE_SHOWCASE_LIST = listOf(
    ExerciseShowCase(0,"Upper Body","https://i.imgur.com/fi4BK5A.jpg","Easy", UPPER_BODY),
    ExerciseShowCase(1,"Lower Body","https://i.imgur.com/fi4BK5A.jpg","Easy", LOWER_BODY),
    ExerciseShowCase(2, "Abs","https://i.imgur.com/fi4BK5A.jpg","Easy", ABS),
    ExerciseShowCase(3, "Chest","https://i.imgur.com/fi4BK5A.jpg","Easy", CHEST),
    ExerciseShowCase(4,"Shoulder","https://i.imgur.com/fi4BK5A.jpg","Easy", SHOULDER),
    ExerciseShowCase(5,"Back","https://i.imgur.com/fi4BK5A.jpg","Easy", BACK),
    ExerciseShowCase(6,"Biceps","https://i.imgur.com/fi4BK5A.jpg","Easy", BICEPS),
    ExerciseShowCase(7,"Triceps","https://i.imgur.com/fi4BK5A.jpg","Easy", TRICEPS)
)