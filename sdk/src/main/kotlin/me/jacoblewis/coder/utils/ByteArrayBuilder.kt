package me.jacoblewis.coder.utils

import java.io.ByteArrayOutputStream

class ByteArrayBuilder {
    val os = ByteArrayOutputStream()

    fun append(str: String) {
        os.write(str.toByteArray())
    }

    fun append(arr: ByteArray) {
        os.write(arr)
    }

    fun append(byte: Int) {
        os.write(byte)
    }

    fun build(): ByteArray {
        val ba = os.toByteArray()
        os.flush()
        os.close()
        return ba
    }
}