package net.scapeemulator.game.model


class Appearance(@JvmField val gender: Gender, val style: IntArray, val colours: IntArray) {

    companion object {
        private val color_whiteTAndBlueJeans = intArrayOf(2, 5, 8, 11, 14)
        val male = Appearance(Gender.MALE, intArrayOf(0, 10, 18, 26, 33, 36, 42), color_whiteTAndBlueJeans)
        val female = Appearance(Gender.FEMALE, intArrayOf(45, 1000, 56, 64, 69, 72, 80), color_whiteTAndBlueJeans)
    }

    fun getBody(body: Body) = style[body.ordinal]
    fun getColor(colour: Colour) = colours[colour.ordinal]
    fun setBody(body: Body, value: Int) {
        style[body.ordinal] = value
        println("style[${body.name}] = $value")
    }

    constructor(gender: Gender) : this(
        if (gender == Gender.MALE) Gender.MALE else Gender.FEMALE,
        if (gender == Gender.MALE) intArrayOf(0, 10, 18, 26, 33, 36, 42) else intArrayOf(45, 1000, 56, 64, 69, 72, 80),
        if (gender == Gender.MALE) intArrayOf(2, 5, 8, 11, 14) else intArrayOf(2, 5, 8, 11, 14)
    )

//    fun setColour(colour: Colour, value: Int) {
//        colours[colour.ordinal] = value
//    }

}

enum class Gender {
    MALE, FEMALE
}

enum class Body(val male: IntArray, private val female: IntArray) {
    HEAD(
        intArrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 91, 92, 93, 94, 95, 96, 97, 261, 262, 263, 264, 265, 266, 267, 268),
        intArrayOf(
//            45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 135, 136,
//            137, 138, 139, 140, 141, 142, 143, 144, 145, 146,

            /*45, 46, 47, 48, 49, 50, 51, 52, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 269, 270, 271, 272, 273, 274, 275,
276, 277, 278, 279, 280, 281, 282, 283, 284, 285, 286, 287, 288, 289, 290, 291, 292, 293, 294, 295, 296, 297, 298, 299, 300,
301, 302, 303, 304, 353, 354, 355, 356, 357, 358, 359, 360, 361, 362*/
            45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 269, 270, 271, 272, 273, 274, 275,
            276, 277, 278, 279, 280

            /* 200, 201, 203, 204, 205, 206, 209, 210, 211, 212,
              213, 214, 215, 216, 217, 218, 219, 220, 222, 224, 225 ponytails
              231, 232, 235, 238, 239 */

            // 244 bald?
//            269, 270, 271, 272, 273, 274, 275, 276, 277, 278, 279, 280

            //484 spikes
            //todo find new hairstyles
        )
    ),
    FACIAL(
        intArrayOf(10, 11, 12, 13, 14, 15, 16, 17, 98, 99, 100, 101, 102, 103, 104, 305, 306, 307, 308),
        intArrayOf(1000)
    ),
    TORSO(
        intArrayOf(18, 19, 20, 21, 22, 23, 24, 25, 111, 112, 113, 114, 115, 116),
        intArrayOf(56, 57, 58, 59, 60, 153, 154, 155, 156, 157, 158)
    ),
    ARMS(
        intArrayOf(26, 27, 28, 29, 30, 31, 105, 106, 107, 108, 109, 110),
        intArrayOf(61, 62, 63, 64, 65, 147, 148, 149, 150, 151, 152)
    ),
    HANDS(
        intArrayOf(33, 34, 84, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126),
        intArrayOf(67, 68, 127, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168)
    ),
    LEGS(
        intArrayOf(36, 37, 38, 39, 40, 85, 86, 87, 88, 89, 90),
        intArrayOf(70, 71, 72, 73, 74, 75, 76, 77, 128, 129, 130, 131, 132, 133, 134)
    ),
    FEET(intArrayOf(42, 43), intArrayOf(79, 80));

    fun getType(gender: Gender): IntArray =
        if (gender == Gender.MALE) male else female
}

enum class Colour {
    HAIR, TORSO, LEGS, FEET, SKIN
}