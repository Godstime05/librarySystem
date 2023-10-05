package com.godstimeProjects2023.librarySystem.security;

import com.godstimeProjects2023.librarySystem.entity.Role;
import com.godstimeProjects2023.librarySystem.entity.User;
import com.godstimeProjects2023.librarySystem.repository.UserRepo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepo userRepo;

    public UserDetailsServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.getByUsername(username);
        if (user != null){
            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .authorities(mapGrantedAuthorities(user.getRoles()))
                    .build();
        }
        else {
            throw new UsernameNotFoundException("User not found");
        }

    }

    public Collection<? extends GrantedAuthority> mapGrantedAuthorities(List<Role> roles){
        List<GrantedAuthority> authorities = new ArrayList<>(roles.size());
        for (Role role : roles){
            authorities.add(new SimpleGrantedAuthority("ROLE_"+ role.getRoleName()));
        }
        return authorities;
    }
}
