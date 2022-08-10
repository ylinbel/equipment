package com.mechanicaleng.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    public UserRepository userRepository;

    //create user
    public void addUser(UserDto userDto) {
        UserEntity userEntity = UserEntity.fromDto(userDto);
        userRepository.save(userEntity);
    }

    //delete user
    public void deleteWithId(Long id) {
        userRepository.deleteById(id);
    }

    //update user information
    public Boolean updateUser(UserDto userDto) {
        Optional<UserEntity> opUserEntity = userRepository.findById(userDto.getId());
        if (opUserEntity.isEmpty()) return false;
        UserEntity userEntity = opUserEntity.get();
        userEntity.updateFromDto(userDto);
        userRepository.save(userEntity);
        return true;
    }

    //find user with name
    public List<UserDisplayDto> findUserWithName(String name) {
        List<UserEntity> entities = userRepository.findUserEntitiesByNameLike(name);
        return getUserDtos(entities);
    }

    public List<UserDisplayDto> findUsersWithUserType(UserTypeEnum userTypeEnum) {
        List<UserEntity> entities = userRepository.findUserEntitiesByUserTypeEnumEquals(userTypeEnum);
        return getUserDtos(entities);
    }

    private List<UserDisplayDto> getUserDtos(List<UserEntity> entities) {
        List<UserDisplayDto> userDtoList = new ArrayList<>();
        entities.forEach(userEntity -> {
            userDtoList.add(userEntity.toDto());
        });
        return userDtoList;
    }

}
