package org.dev.otte.common.domain

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import org.json.simple.parser.ParseException
import org.springframework.web.util.ContentCachingRequestWrapper
import org.springframework.web.util.ContentCachingResponseWrapper
import org.springframework.web.util.WebUtils
import java.nio.charset.StandardCharsets

object HttpMessageParser {
    private const val AUTHORIZATION: String = "authorization"

    private val jsonParser: JSONParser = JSONParser()

    fun getRequestHeaders(request: HttpServletRequest): Map<String, String> {
        val requestHeaderMap: MutableMap<String, String> = mutableMapOf()

        val headerNames = request.headerNames
        while (headerNames.hasMoreElements()) {
            val headerName = headerNames.nextElement()
            if (headerName.equals(AUTHORIZATION)) {
                requestHeaderMap[headerName] = request.getHeader(headerName)
            }
        }
        return requestHeaderMap
    }

    fun getRequestBody(request: ContentCachingRequestWrapper): JSONObject {
        val wrapper = WebUtils.getNativeRequest(request, ContentCachingRequestWrapper::class.java)
        if (wrapper != null) {
            val buf = wrapper.contentAsByteArray
            if (buf.isNotEmpty()) {
                try {
                    val body = String(buf, 0, buf.size, StandardCharsets.UTF_8)
                    return jsonParser.parse(body) as JSONObject
                } catch (e: ParseException) {
                    e.printStackTrace()
                }
            }
        }
        return JSONObject()
    }

    fun getResponseBody(response: HttpServletResponse): JSONObject {
        val wrapper = WebUtils.getNativeResponse(response, ContentCachingResponseWrapper::class.java)
        val body: String
        if (wrapper != null) {
            val buf = wrapper.contentAsByteArray
            if (buf.isNotEmpty()) {
                try {
                    body = String(buf, 0, buf.size, StandardCharsets.UTF_8)
                    wrapper.copyBodyToResponse()
                    return jsonParser.parse(body) as JSONObject
                } catch (e: ParseException) {
                    e.printStackTrace()
                }
            }
        }
        return JSONObject()
    }
}
