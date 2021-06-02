package com.example.weatherforecast.unitlity

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStream
import java.io.InputStreamReader

object HelperTest {

    fun <T> getDataFromJson(requestJson: String): T? {
        val type = object : TypeToken<ArrayList<T>>() {}.type
        return Gson().fromJson<T>(requestJson, type)
    }

    fun getStringFromFile(fileName:String): String {
        return getString(System.getProperty("user.dir") + "/src/DataForTest" + this)
    }

    fun getString(filePath: String): String {
        val stream = FileInputStream(filePath)
        val stData = stream.convertStreamToString()
        stream.close()
        return stData
    }

    fun InputStream.convertStreamToString(): String {
        val reader = BufferedReader(InputStreamReader(this))
        val sb = StringBuilder()
        var line: String?
        while (reader.readLine().also { line = it } != null) {
            sb.append(line).append("\n")
        }
        reader.close()
        return sb.toString()
    }
}