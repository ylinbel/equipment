package com.mechanicaleng.user;

import com.mechanicaleng.mail.SendMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public SendMailService sendMailService;

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
    public UserDisplayDto findUserWithName(String name) {
        Optional<UserEntity> entities = userRepository.findUserEntityByName(name);
        return entities.isPresent() ? entities.get().toDto() : null;
    }

    //find user with email
    public UserDisplayDto findUserWithEmail(String email) {
        UserEntity entities = userRepository.findUserEntityByEmailEquals(email);
        return entities.toDto();
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

    public String userLogin(String email, String password) {
        UserEntity userEntity = userRepository.findUserEntityByEmailEquals(email);
        return userEntity.getPassword().equals(password) ? userEntity.getName() : null;
    }

    /*warning email 30 days before the end date to the user and manager
    delete the account after util date
     */
    @Scheduled(initialDelay = 60_000, fixedDelayString = "${task.checkDiskSpace:PT24H0M0S}")
    public void checkEndDate() {
        LocalDate date = LocalDate.now();
        List<UserEntity> users = userRepository.findAll();
        users.forEach(userEntity -> {
                    if (date.isAfter(userEntity.getUtilDate().minusDays(30))) {
                        sendMailService.sendLeavingEmailToUser(userEntity);
                        sendMailService.sendLeavingEmailToManager(userEntity);
                    }
            if (date.isAfter(userEntity.getUtilDate())) {
                deleteWithId(userEntity.getId());
            }
        });
    }


    public List<UserDisplayDto> findUserListWithName(String name) {
        List<UserEntity> entities = userRepository.findUserEntitiesByNameLike(name);
        return getUserDtos(entities);
    }
}
