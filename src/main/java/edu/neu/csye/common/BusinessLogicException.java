
/**
 * Varsha Bhanushali, 001234580,
 * Shrikant Mudholkar, 001284732,
 * Rahul Chandra, 01225683,
 * Manish Patil, 001228956,
 **/
package edu.neu.csye.common;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BusinessLogicException extends Exception {

    private static final long serialVersionUID = 3287665806587414504L;


    public BusinessLogicException() {
    }

    @Override
    public String getMessage() {
        return "username already exists!";
    }
}
