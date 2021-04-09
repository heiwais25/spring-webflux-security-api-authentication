package com.heiwais25.study.webflux.requestcache;

import org.springframework.security.crypto.password.PasswordEncoder;

public class EmptyPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        return rawPassword.toString();
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encodedPassword.equals(rawPassword.toString());
    }
}
