package com.basicarch.base.security;

import com.basicarch.module.user.entity.User;
import com.basicarch.module.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        User user = userRepository.findByLoginId(loginId).orElseThrow(() -> new UsernameNotFoundException(loginId));

        List<String> roles = new ArrayList<>(List.of("USR"));
        if (user.getId() == 1) {
            roles.add("ADM");
        }
        user.setRoleList(roles);

        return new AuthUserDetails(user);
    }
}
