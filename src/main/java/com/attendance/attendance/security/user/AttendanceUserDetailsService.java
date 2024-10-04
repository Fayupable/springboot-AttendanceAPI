package com.attendance.attendance.security.user;

import com.attendance.attendance.entity.Person;
import com.attendance.attendance.repository.IPersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttendanceUserDetailsService implements UserDetailsService {
    private final IPersonRepository personRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Person person = Optional.ofNullable(personRepository.findByEmail(email))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return AttendanceUserDetails.buildUserDetails(person);
    }
}
