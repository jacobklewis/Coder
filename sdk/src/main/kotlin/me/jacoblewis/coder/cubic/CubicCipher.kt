package me.jacoblewis.coder.cubic

import me.jacoblewis.coder.utils.Hexicode
import kotlin.math.pow

class CubicCipher(val cKey: String) {

    /**
     * ENCODE a string
     * @param data holds string data
     */
    internal fun encode(data: String) {
        var secData = data
        for (element in cKey) {
            var tempDat = ""
            for (j in data.indices) {
                var tmp =
                    (secData[j].toDouble() + (element.toInt() + j.toDouble()).pow(3.0)).toInt()
                while (tmp > 255) tmp -= 256
                tempDat += tmp.toChar() // + " ";
            }
            secData = tempDat
        }
        this.data = ""
        for (element in secData) {
            this.data += Hexicode.toHex(element.toInt())
        }
    }

    fun encodeNow(data: String): String {
        encode(data)
        return this.data
    }

    fun encodeNowByteArray(data: String): ByteArray {
        val hexData = encodeNow(data)
        return Hexicode.toByteArray(hexData)
    }

    fun decodeByteArray(arr: ByteArray): String {
        val hexData = Hexicode.fromByteArray(arr)
        return decode(hexData)
    }

    /**
     * DECODE an encoded string
     * @return decoded string
     */
    fun decode(code: String): String {
        var secData = ""
        run {
            var i = 0
            while (i < code.length) {
                secData += Hexicode.toDec(code.substring(i, i + 2)).toChar()
                i += 2
            }
        }
        for (i in cKey.length - 1 downTo 0) {
            var tempDat = ""
            for (j in secData.length - 1 downTo 0) {
                var tmp =
                    (secData[j].toDouble() - (cKey[i].toInt() + j.toDouble()).pow(3.0)).toInt()
                while (tmp < 0) tmp += 256
                tempDat += tmp.toChar()
            }
            secData = StringBuilder(tempDat).reverse().toString()
        }
        return secData
    }

    /**
     * getData()
     * -returns encrypted data
     */
    var data = ""
        private set
}