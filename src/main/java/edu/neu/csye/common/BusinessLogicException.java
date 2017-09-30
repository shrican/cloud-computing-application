package edu.neu.csye.common;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
public class BusinessLogicException extends Exception {

    private static final long serialVersionUID = 3287665806587414504L;


    public BusinessLogicException(){
    }

    @Override
    public String getMessage() {
        return "username already exists!";
    }
}
