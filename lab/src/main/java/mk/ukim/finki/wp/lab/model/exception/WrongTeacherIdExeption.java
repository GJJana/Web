package mk.ukim.finki.wp.lab.model.exception;

public class WrongTeacherIdExeption extends Exception {
    public WrongTeacherIdExeption() {
        super("There is no theacher with this Id");
    }
}
