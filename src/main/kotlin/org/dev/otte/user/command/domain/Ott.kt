package org.dev.otte.user.command.domain

import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

enum class Ott {
    TVING,
    NETFLIX,
    COUPANG_PLAY,
    SEEZN,
    DISNEY_PLUS,
    WATCHA,
    WAVVE,
    APPLE_TV,
    NONE;

    companion object {
        fun of(name: String): Ott {
            return entries.find { it.name == name } ?: throw IllegalArgumentException()
        }
    }
}

@Converter
class OttConverter : AttributeConverter<List<Ott>, String> {
    override fun convertToDatabaseColumn(attribute: List<Ott>): String {
        return attribute.joinToString(",") { it.name }
    }

    override fun convertToEntityAttribute(dbData: String?): List<Ott> {
        if (dbData.isNullOrEmpty()) {
            return emptyList()
        }
        return dbData.split(",").map { Ott.of(it) }
    }
}
