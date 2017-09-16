package com.flame.oauth.security.tokenstore;

import com.gavin.model.dto.security.CurrentUser;
import com.gavin.model.dto.security.CustomUser;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

public class CustomTokenStoreDelegator implements TokenStore {

    private static final String LOGIN_USER = "login_user:";

    private TokenStore delegate;
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public OAuth2Authentication readAuthentication(OAuth2AccessToken token) {
        OAuth2Authentication authentication = delegate.readAuthentication(token);

        Object principal = authentication.getUserAuthentication().getPrincipal();
        CustomUser customUser = (CustomUser) principal;

        CurrentUser currentUser = new CurrentUser();
        BeanUtils.copyProperties(customUser, currentUser);
        currentUser.setUserId(customUser.getUsername());

//        BoundHashOperations<String, String, Object> boundHashOperations
//                = redisTemplate.boundHashOps(LOGIN_USER + customUser.getUsername());
//        boundHashOperations.put(token.getValue(), currentUser);
//        boundHashOperations.expire(1, TimeUnit.HOURS);

        BoundValueOperations<String, Object> boundValueOperations = redisTemplate.boundValueOps("user-id:" + currentUser.getUserId());
        boundValueOperations.setIfAbsent(currentUser);
        boundValueOperations.expire(1, TimeUnit.HOURS);

        return authentication;
    }

    @Override
    public OAuth2Authentication readAuthentication(String token) {
        return delegate.readAuthentication(token);
    }

    @Override
    public void storeAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
        delegate.storeAccessToken(token, authentication);

        Object principal = authentication.getUserAuthentication().getPrincipal();
        if (principal instanceof CustomUser) {
            CustomUser customUser = (CustomUser) principal;

            CurrentUser currentUser = new CurrentUser();
            BeanUtils.copyProperties(customUser, currentUser);
            currentUser.setUserId(customUser.getUsername());

//            BoundHashOperations<String, String, Object> boundHashOperations
//                    = redisTemplate.boundHashOps(LOGIN_USER + customUser.getUsername());
//            boundHashOperations.put(token.getValue(), currentUser);
//            boundHashOperations.expire(1, TimeUnit.HOURS);

            BoundValueOperations<String, Object> boundValueOperations = redisTemplate.boundValueOps("user_id:" + currentUser.getUserId());
            boundValueOperations.setIfAbsent(currentUser);
            boundValueOperations.expire(1, TimeUnit.HOURS);
        }
    }

    @Override
    public OAuth2AccessToken readAccessToken(String tokenValue) {
        return delegate.readAccessToken(tokenValue);
    }

    @Override
    public void removeAccessToken(OAuth2AccessToken token) {
        delegate.removeAccessToken(token);
    }

    @Override
    public void storeRefreshToken(OAuth2RefreshToken refreshToken, OAuth2Authentication authentication) {
        delegate.storeRefreshToken(refreshToken, authentication);
    }

    @Override
    public OAuth2RefreshToken readRefreshToken(String tokenValue) {
        return delegate.readRefreshToken(tokenValue);
    }

    @Override
    public OAuth2Authentication readAuthenticationForRefreshToken(OAuth2RefreshToken token) {
        return delegate.readAuthenticationForRefreshToken(token);
    }

    @Override
    public void removeRefreshToken(OAuth2RefreshToken token) {
        delegate.removeRefreshToken(token);
    }

    @Override
    public void removeAccessTokenUsingRefreshToken(OAuth2RefreshToken refreshToken) {
        delegate.removeAccessTokenUsingRefreshToken(refreshToken);
    }

    @Override
    public OAuth2AccessToken getAccessToken(OAuth2Authentication authentication) {
        return delegate.getAccessToken(authentication);
    }

    @Override
    public Collection<OAuth2AccessToken> findTokensByClientIdAndUserName(String clientId, String userName) {
        return delegate.findTokensByClientIdAndUserName(clientId, userName);
    }

    @Override
    public Collection<OAuth2AccessToken> findTokensByClientId(String clientId) {
        return delegate.findTokensByClientId(clientId);
    }

}
