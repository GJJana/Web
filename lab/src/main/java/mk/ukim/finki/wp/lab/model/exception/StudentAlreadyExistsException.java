package mk.ukim.finki.wp.lab.model.exception;

public class StudentAlreadyExistsException extends RuntimeException{
    public StudentAlreadyExistsException(String username) {
        super(String.format("User with username: %s already exists", username));
    }

}
