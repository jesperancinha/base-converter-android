package nl.joaofilipesabinoesperancinha.basicunitconverter.tools

import kotlin.math.pow

object Converter {
    fun getDecToBase(dec: Int, base: Int): String {
        var number = dec
        val sb = StringBuilder()
        if (number == 0) {
            sb.append(0)
        } else {
            while (number > 0) {
                val remainder = number % base
                if (number < base) {
                    sb.append(getChar(number))
                } else {
                    sb.append(getChar(remainder))
                }
                number /= base
            }
        }

        return sb.reverse().toString()
    }


    fun getBaseToDeC(number: String, base: Int): Int {
        var number = number
        number = number.trim { it <= ' ' }
        var result = 0
        if (number == "0") {
            result = 0
        } else {
            for ((j, i) in (number.length - 1 downTo 0).withIndex()) {
                val digit = getInt(number.substring(i, i + 1))

                val powNumber = base.toDouble().pow(j.toDouble()).toInt()

                result += digit * powNumber
            }
        }

        return result
    }

    private fun getInt(charString: String): Int {
        val a = charString[0]
        var converted = if (a.code <= 57) {
            a.code - 48
        } else {
            a.code + 10 - 65
        }
        return converted
    }


    fun getChar(number: Int): Char {
        val a = if (number <= 9) {
            (number + 48).toChar()
        } else {
            (number - 10 + 65).toChar()
        }
        return a
    }
}
