package com.attendance.attendance.security.user;

import com.attendance.attendance.entity.Person;
import com.attendance.attendance.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceUserDetails implements UserDetails {
    private Long id;
    private String email;
    private String password;

    private Collection<GrantedAuthority> authorities;

    public static AttendanceUserDetails buildUserDetails(Person person) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        if (person.getRole() != null) {
            authorities.add(new SimpleGrantedAuthority(person.getRole().name()));
        }

        return new AttendanceUserDetails(
                person.getId(),
                person.getEmail(),
                person.getPassword(),
                authorities
        );
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }
}
