package com.github.domain.entity


/**
 * This is a data class that represents a notification message.
 * It has a single property called "content" of type String, which contains the text of the message.
 * Data classes in Kotlin are used to store data, and they automatically generate useful methods such as
 * equals(), hashCode(), toString(), and copy(). This makes it easy to work with data objects in Kotlin.
 * By using a data class, we can ensure that all instances of the NotificationMessage have the same properties,
 * and we can easily compare them and generate useful string representations.
 */
data class NotificationMessage(
    val content: String
)

