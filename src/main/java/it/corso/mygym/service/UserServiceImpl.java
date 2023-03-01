package it.corso.mygym.service;

import it.corso.mygym.dao.UserRepository;
import it.corso.mygym.model.User;
import it.corso.mygym.model.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repo;

    @Override
    public User save(User user) {
        return repo.save(user);
    }

    @Override
    public User findById(Long id) {
        return repo.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public List<User> findAll(boolean includeInactiveFlag) {
        return null;
    }

    @Override
    public User update(Long id, UserDto userDto) {
        Optional<User> userOld = repo.findById(id);
        userDto.setId(id);

        if(userOld.isPresent()){
            copyNonNullProperties(userDto, userOld.get());
            userDto.setId(id);

            return repo.saveAndFlush(userOld.get());
        } else throw new ResourceNotFoundException();
    }

    @Override
    public User deleteById(Long id) {
        Optional<User> optionalUser = repo.findById(id);

        if(optionalUser.isPresent()){
            User user = optionalUser.get();

            // hard-delete
            //repo.deleteById(id);

            // soft-delete
            user.setActiveFlag(false);
            return repo.save(user);
        } else throw new ResourceNotFoundException();
    }

    public static void copyNonNullProperties(Object src, Object target) {
        BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
    }

    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}
