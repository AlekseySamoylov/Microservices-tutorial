package com.alekseysamoylov.oauth.authentication

import com.alekseysamoylov.oauth.core.authorities.createAuthorities
import com.alekseysamoylov.oauth.domain.User
import org.apache.commons.lang3.RandomStringUtils.randomAlphabetic
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.social.connect.Connection


fun authenticate(connection: Connection<*>) {

    val user = createCalendarUserFromProvider(connection)

    val authentication = UsernamePasswordAuthenticationToken(
            user,
            null,
            createAuthorities(user))
    SecurityContextHolder.getContext().authentication = authentication

}

fun createCalendarUserFromProvider(connection: Connection<*>): User {

    // TODO: There is a defect with Facebook:
    //        Connection<Facebook> connection = facebookConnectionFactory.createConnection(accessGrant);
    //        Facebook facebook = connection.getApi();
    //        String [] fields = { "id", "email",  "first_name", "last_name" };
    //        User userProfile = facebook.fetchObject("me", User.class, fields);

    //        Object api = connection.getApi();
    //        if(connection instanceof FacebookTemplate){
    //            System.out.println("HERE");
    //        }
    /*
            <form name='facebookSocialloginForm'
                  action="<c:url value='/auth/facebook' />" method='POST'>
                <input type="hidden" name="scope"
                    value="public_profile,email,user_about_me,user_birthday,user_likes"/>
                ...
            </form>
         */

    // FIXME: Does not work with Facebook:
    val profile = connection.fetchUserProfile()

    val user = User()

    if (profile.email != null) {
        user.email = profile.email
    } else if (profile.username != null) {
        user.email = profile.username
    } else {
        user.email = connection.displayName
    }

    user.name = profile.firstName

    user.password = randomAlphabetic(32)

    return user

}




