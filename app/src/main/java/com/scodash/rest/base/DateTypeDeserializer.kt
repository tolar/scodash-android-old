package com.scodash.rest.base


import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import com.scodash.utility.DateUtility
import java.lang.reflect.Type
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class DateTypeDeserializer : JsonDeserializer<Date> {

	@Throws(JsonParseException::class)
	override fun deserialize(jsonElement: JsonElement, typeOF: Type, context: JsonDeserializationContext): Date {
		for (format in SUPPORTED_DATE_FORMATS) {
			try {
				return SimpleDateFormat(format, Locale.US).parse(jsonElement.asString)
			} catch (e: ParseException) {
			}

		}
		throw JsonParseException("Unparseable date: \"" + jsonElement.asString
				+ "\". Supported formats: \n" + Arrays.toString(SUPPORTED_DATE_FORMATS))
	}

	companion object {
		private val SUPPORTED_DATE_FORMATS = arrayOf(
				DateUtility.DATE_FORMAT_REST,
				DateUtility.DATE_FORMAT_TIMEZONE,
				DateUtility.DATE_FORMAT_NO_TIMEZONE)
	}
}
