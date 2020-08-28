package me.jacoblewis.coder.utils

object Hexicode {
    fun toHex(value: Int): String {
        val first = value / 16
        val second = value % 16
        return codes[first] + "" + codes[second]
    }

    fun toDec(hex: String): Int {
        val first = charLoc(
            codes,
            hex[0].toLowerCase()
        ) * 16
        val second = charLoc(
            codes,
            hex[1].toLowerCase()
        )
        return first + second
    }

    private fun charLoc(src: CharArray, value: Char): Int {
        for (i in src.indices) {
            if (src[i] == value) return i
        }
        return -1
    }

    fun toByteArray(hex: String): ByteArray {
        val dataBuilder = ByteArrayBuilder()
        for (i in (hex.indices step 2)) {
            val ch = toDec(
                hex.substring(
                    i,
                    i + 2
                )
            )
            dataBuilder.append(ch)
        }
        return dataBuilder.build()
    }

    fun fromByteArray(arr: ByteArray): String {
        var hex = ""
        for (b in arr) {
            hex += toHex(
                b.toUByte().toInt()
            )
        }
        return hex
    }

    private val codes =
        charArrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f')
}