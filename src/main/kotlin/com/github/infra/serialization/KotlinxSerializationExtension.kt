package com.github.infra.serialization

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

// This is an inline function that takes a generic type parameter T and returns a String
// The function is marked as "reified" which means that the type T can be determined at runtime
inline fun <reified T> T.serializeToString(): String {
    // The function uses the Kotlinx serialization library to serialize the object of type T to a String
    return Json.encodeToString(this)
}

// This is an inline function that takes a generic type parameter T and returns an object of type T
// The function is marked as "reified" which means that the type T can be determined at runtime
inline fun <reified T> String.deserialize(): T {
    // The function uses the Kotlinx serialization library to deserialize the String to an object of type T
    return Json.decodeFromString(this)
}
