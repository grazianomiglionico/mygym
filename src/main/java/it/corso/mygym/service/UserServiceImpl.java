package it.corso.mygym.service;

import it.corso.mygym.Constants;
import it.corso.mygym.repository.UserRepository;
import it.corso.mygym.mapper.UserMapper;
import it.corso.mygym.model.User;
import it.corso.mygym.dto.UserDto;
import it.corso.mygym.dto.request.UserRequest;
import it.corso.mygym.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional
    @Override
    public UserDto save(UserRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email già esistente");
        }

        User user = User.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .email(request.getEmail())
                .activeFlag(true)
                .build();

        return userMapper.toDto(
                userRepository.save(user));
    }

    @Transactional(readOnly = true)
    @Override
    public UserDto findById(Long id) {
        User user = userRepository.findById(id)
                .filter(User::isActiveFlag)
                .orElseThrow(() -> new IllegalArgumentException("Utente non trovato o inattivo"));

        return userMapper.toDto(user);
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserDto> findAll(boolean includeInactiveFlag) {
        return (includeInactiveFlag ? userRepository.findAll() : userRepository.findByActiveFlagTrue())
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @Transactional
    @Override
    public UserDto update(Long id, UserRequest request)  {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(Constants.USER_NOT_FOUND_EXCEPTION, id));

        userMapper.updateUserFromRequest(request, user);

        return userMapper.toDto(
                userRepository.saveAndFlush(user));
    }

    @Transactional
    @Override
    public UserDto deleteById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if(optionalUser.isPresent()){
            User user = optionalUser.get();

            // hard-delete
            //repo.deleteById(id);

            // soft-delete
            user.setActiveFlag(false);
            return userMapper.toDto(
                    userRepository.save(user));

        } else throw new ResourceNotFoundException();
    }
}
