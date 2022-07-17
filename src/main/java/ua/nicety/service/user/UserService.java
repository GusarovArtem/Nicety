package ua.nicety.service.user;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import ua.nicety.database.entity.User;
import ua.nicety.http.dto.createEdit.UserCreateEditDto;

public interface UserService extends UserDetailsService, OAuth2UserService<OidcUserRequest, OidcUser> {
    void create(UserCreateEditDto userDto);

    User getById(Long id);
    User getByEmail(String email);
}
