package ua.nicety.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.nicety.database.entity.User;
import ua.nicety.database.repository.UserRepository;
import ua.nicety.exception.ResourceNotFoundException;
import ua.nicety.http.dto.UserCreateEditDto;
import ua.nicety.http.mapper.UserCreateEditMapper;
import ua.nicety.security.UserPrincipal;

import java.util.Collections;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserCreateEditMapper userCreateEditMapper;


    public void create(UserCreateEditDto userDto) {
        Optional.of(userDto)
                .map(userCreateEditMapper::map)
                .map( user -> {
                    userRepository.save(user);
                    return user;
                });
    }

    @Transactional
    public boolean update(Long id, UserCreateEditDto userDto) {
        return userRepository.findById(id)
                .map(entity -> userCreateEditMapper.map(userDto))
                .map(user -> {
//                    userRepository.update(id, user);
                    User userToUpdate = userRepository.getOne(String.valueOf(id));
                    userToUpdate.setUsername(userDto.getUsername());
                    userRepository.save(userToUpdate);
                    return true;
                }).orElse(false);
    }

    @Transactional
    public boolean delete(Long id) {
        return userRepository.findById(id)
                .map(entity -> {
                    userRepository.deleteById(id);
                    return true;
                })
                .orElse(false);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        Optional<User> userFindByEmail = userRepository.findByEmail(username);
        Optional<User> userFindByUsername = userRepository.findByUsername(username);

        if(userFindByEmail.isPresent()) {
            return new org.springframework.security.core.userdetails.User(
                    userFindByEmail.get().getUsername(),
                    userFindByEmail.get().getPassword(),
                    Collections.singleton(userFindByEmail.get().getRole()));
        }

        if(userFindByUsername.isPresent()) {
            return new org.springframework.security.core.userdetails.User(
                    userFindByUsername.get().getUsername(),
                    userFindByUsername.get().getPassword(),
                    Collections.singleton(userFindByUsername.get().getRole()));
        }

        throw new UsernameNotFoundException("Failed to retrieve user");
    }

    @Override
    public UserDetails loadUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", id)
        );

        return UserPrincipal.create(user);
    }
}

