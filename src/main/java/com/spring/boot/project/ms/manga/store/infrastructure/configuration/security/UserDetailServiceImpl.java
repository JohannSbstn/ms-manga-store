package com.spring.boot.project.ms.manga.store.infrastructure.configuration.security;

import com.spring.boot.project.ms.manga.store.infrastructure.output.jpa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmailWithRoles(username)
                .map(UserDetailImpl::new)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Email %s not found", username)));
    }
}
