package pl.edu.wszib.order;

public enum OrderResult {
    OK,

    ALREADY_EXIST,

    NOT_FOUND;

    public boolean isSuccess() {
        return this == OK;
    }

    public boolean isFailure() {
        return !isSuccess();
    }
}
