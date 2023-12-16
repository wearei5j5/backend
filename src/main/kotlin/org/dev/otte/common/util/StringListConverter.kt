package org.dev.otte.common.util

import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter
class StringListConverter : AttributeConverter<List<String>, String> {
    override fun convertToDatabaseColumn(attribute: List<String>?): String {
        if (attribute.isNullOrEmpty()) {
            return ""
        }
        return attribute.joinToString(",")
    }

    override fun convertToEntityAttribute(dbData: String?): List<String> {
        if (dbData.isNullOrEmpty()) {
            return emptyList()
        }
        return dbData.split(",")
    }
}