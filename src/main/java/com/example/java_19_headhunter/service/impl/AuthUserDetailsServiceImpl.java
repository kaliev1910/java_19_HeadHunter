package com.example.java_19_headhunter.service.impl;

import com.example.java_19_headhunter.enums.AccountType;
import com.example.java_19_headhunter.models.Role;
import com.example.java_19_headhunter.models.User;
import com.example.java_19_headhunter.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.example.java_19_headhunter.enums.AccountType.APPLICANT;

@Service
@RequiredArgsConstructor
public class AuthUserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<User> mayBeUser = userRepository.getByEmail(username);
//        if (mayBeUser.isEmpty()) {
//            return new org.springframework.security.core.userdetails.User(
//                    " ",
//                    " ",
//                    getAuthorities(Collections.singletonList(roleRepository.findByRole("ROLE_GUEST"))));
//        }
        User user = userRepository.findUserByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.isEnabled(),
                true,
                true,
                true,
                getAuthorities(user.getRoles())
        );
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
        return getGrantedAuthorities(AccountType.getAccountTypes());
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }

//    private List<String> getPrivileges(Collection<Role> roles) {
//        List<String> privileges = new ArrayList<>();
//        List<Authority> collection = new ArrayList<>();
//
//        for (Role role : roles) {
//            privileges.add(role.getRole());
//            collection.addAll(role.getAuthorities());
//        }
//        for (Authority item : collection) {
//            privileges.add(item.getAuthority());
//        }
//        return privileges;
//    }


}
