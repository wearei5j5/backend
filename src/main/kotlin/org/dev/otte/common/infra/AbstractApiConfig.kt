package org.dev.otte.common.infra

import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.support.WebClientAdapter
import org.springframework.web.service.invoker.HttpServiceProxyFactory

abstract class AbstractApiConfig {
    protected fun <T> createHttpInterface(clazz: Class<T>): T {
        val webClient = WebClient.create()
        val build = HttpServiceProxyFactory
            .builderFor(WebClientAdapter.create(webClient)).build()
        return build.createClient(clazz)
    }
}