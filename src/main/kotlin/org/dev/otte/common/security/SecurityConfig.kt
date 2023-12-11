package org.dev.otte.common.security

import org.dev.otte.auth.command.application.JwtProvider
import org.dev.otte.user.command.domain.UserRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.config.http.SessionCreationPolicy.STATELESS
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
class SecurityConfig {
    @Configuration
    @Order(0)
    class ApiSecurityConfig(
//        private val customAuthenticationEntryPoint: CustomAuthenticationEntryPoint,
        private val jwtProvider: JwtProvider,
        private val userRepository: UserRepository
    ) {
        @Bean
        fun apiSecurityFilterChain(http: HttpSecurity): SecurityFilterChain {
            http {
                csrf { disable() }
                headers { frameOptions { disable() } }
                formLogin { disable() }
                httpBasic { disable() }
                sessionManagement { sessionCreationPolicy = STATELESS }
                securityMatcher("/api/**")
                authorizeRequests {
                    authorize(anyRequest, permitAll)
                }
                addFilterBefore<UsernamePasswordAuthenticationFilter>(
                    JwtAuthenticationFilter(
                        jwtProvider,
                        userRepository
                    )
                )
//                exceptionHandling {
//                    authenticationEntryPoint = customAuthenticationEntryPoint
//                }
            }
            return http.build()
        }
    }
}
