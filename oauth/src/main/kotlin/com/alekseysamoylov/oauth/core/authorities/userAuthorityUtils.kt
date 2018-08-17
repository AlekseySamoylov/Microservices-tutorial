package com.alekseysamoylov.oauth.core.authorities

import com.alekseysamoylov.oauth.domain.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils

internal val ADMIN_ROLES = AuthorityUtils.createAuthorityList("ROLE_ADMIN", "ROLE_USER")
internal val USER_ROLES = AuthorityUtils.createAuthorityList("ROLE_USER")

fun createAuthorities(user: User): Collection<GrantedAuthority> {
    val username = user.email
    return if (username.startsWith("admin")) {
        ADMIN_ROLES
    } else {
        USER_ROLES
    }
}

