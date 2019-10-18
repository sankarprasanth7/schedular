package com.incl.content.schedular.security;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import com.incl.content.schedular.bean.User;

public class ImportsAuthenticationToken extends AbstractAuthenticationToken {
    
    private static final long serialVersionUID = -1949976839306453197L;
    private User authenticatedUser;
    private Long uid;
        
    public ImportsAuthenticationToken(User user){
        super(Arrays.asList());
        this.authenticatedUser = user;        
    }
    
    public ImportsAuthenticationToken(Collection<? extends GrantedAuthority> authorities, User authenticatedUser, Long uid) {
        super(authorities);
        this.uid = uid;
        this.authenticatedUser = authenticatedUser;
    }

    @Override
    public Object getCredentials() {
        return authenticatedUser.getPassword();
    }

    @Override
    public Object getPrincipal() {
        return authenticatedUser;
    }

    public String getUid() {
        return ""+uid;
    }
    
}