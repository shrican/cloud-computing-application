package edu.neu.csye.useraccount.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A model of the user account domain object that is exposed to the public via an api.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAccountDto {

    private String email;
    private String password;
}
