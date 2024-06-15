package ru.gunto09.java.labaone.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.gunto09.java.labaone.exceptions.UsernameAlreadyExistsExeption;
import ru.gunto09.java.labaone.model.User;
import ru.gunto09.java.labaone.model.UserAuthority;
import ru.gunto09.java.labaone.model.UserRole;
import ru.gunto09.java.labaone.repository.UserRepository;
import ru.gunto09.java.labaone.repository.UserRolesRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final UserRolesRepository userRolesRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void registration(String username, String password) {
        if (userRepository.findByUsername(username).isEmpty()){
            User user = userRepository.save(
                    new User()
                            .setId(null)
                            .setUsername(username)
                            .setPassword(passwordEncoder.encode(password))
                            .setEnabled(true)
                            .setExpired(false)
                            .setLocked(false)
            );
            userRolesRepository.save(new UserRole(null, UserAuthority.PLACE_JOKE, user));
        }
        else{
            throw new UsernameAlreadyExistsExeption();
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
