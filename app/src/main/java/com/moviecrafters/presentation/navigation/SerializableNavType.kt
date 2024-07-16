package com.moviecrafters.presentation.navigation

import android.os.Bundle
import androidx.navigation.NavType
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import kotlin.reflect.KClass

@OptIn(InternalSerializationApi::class)
class SerializableNavType<T : Any>(private val clazz: KClass<T>) :
    NavType<T>(isNullableAllowed = false) {

    override fun get(bundle: Bundle, key: String): T? {
        return bundle.getString(key)?.let { Json.decodeFromString(clazz.serializer(), it) }
    }

    override fun parseValue(value: String): T {
        return Json.decodeFromString(clazz.serializer(), value)
    }


    override fun put(bundle: Bundle, key: String, value: T) {
        bundle.putString(key, Json.encodeToString(clazz.serializer(), value))
    }
}