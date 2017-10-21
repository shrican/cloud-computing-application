package edu.neu.csye.useraccount.service.model;

import org.springframework.stereotype.Component;

@Component
public class UserIdMapper {

    public UserAccountDto asUserAccountDto(String id) {
        return UserAccountDto.builder()
                .id(id)
                .build();
    }
}
