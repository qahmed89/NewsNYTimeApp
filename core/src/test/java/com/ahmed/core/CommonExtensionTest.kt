package com.ahmed.core

import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import com.ahmed.presentaion.asBundle
import com.ahmed.presentaion.toParcelable
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import strikt.assertions.isNotNull

@RunWith(MockitoJUnitRunner::class)

class CommonExtensionTest {


    @Mock
    lateinit var uri: Uri

    @Mock
    val bundleClass: Bundle = mock()


    @Test
    fun `asBundle_withValidObject_returnsJsonString`() {
        // Create a test Parcelable object
        val person = Person("John Doe", 30)
        val x = Mockito.mockStatic(Uri::class.java)
        whenever(Uri.encode(Gson().toJson(person))).thenReturn("%7B%22name%22%3A%22John%20Doe%22%2C%22age%22%3A30%7D")

        // Call the asBundle() method
        val bundleString = person.asBundle()

        // Verify that the returned string is not null and contains the expected JSON data
        expectThat(bundleString).isNotNull()
        expectThat(bundleString).isEqualTo("%7B%22name%22%3A%22John%20Doe%22%2C%22age%22%3A30%7D")
    }


    @Test
    fun `toParcelable_withValidJson_returnsObject`() {
        // Create a test bundle with a valid JSON string
        val bundlew: Bundle = mock()
        bundlew.putString(
            "data", "{\"name\": \"John Doe\", \"age\": 30}"
        )

        whenever(bundlew.getString("data")).thenReturn("{\"name\": \"John Doe\", \"age\": 30}")


        // Call the toParcelable() method
        val person = bundlew.toParcelable<Person>("data")

        // Verify that the returned object is not null and has the expected values
        Assert.assertNotNull(person)
        expectThat(person?.name).isEqualTo("John Doe")
        expectThat(person?.age).isEqualTo(30)
    }


}

@Parcelize
data class Person(val name: String, val age: Int) : Parcelable