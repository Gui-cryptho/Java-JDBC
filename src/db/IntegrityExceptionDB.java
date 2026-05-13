package db;

public class IntegrityExceptionDB extends RuntimeException{

    public IntegrityExceptionDB(String msg){
        super(msg);
    }
}
