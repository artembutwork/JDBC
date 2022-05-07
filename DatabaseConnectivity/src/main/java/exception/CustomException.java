/*
 * Print detailed information about runtime exceptions
 */

package exception;

import java.sql.SQLException;

public class CustomException extends RuntimeException{

    public CustomException(String message, Throwable cause) {
        Class<?> exceptionType = cause.getClass();
        System.out.println("Exception name: " + exceptionType.getSimpleName());
        System.out.println("Exception message: " + message + "\n");
        if(cause instanceof SQLException){
            SQLException sqlException = (SQLException) cause;
            System.out.println("Error code: " + sqlException.getErrorCode());
            System.out.println("SQL state : " + sqlException.getSQLState());
        }
    }

    public CustomException(String message) {
        System.out.println("Exception message: " + message + "\n");
    }
}
