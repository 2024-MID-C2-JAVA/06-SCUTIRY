package din;

public class RequestMs<T> {

    private DinHeader dinHeader;
    private T dinBody;

    public RequestMs(){}

    public RequestMs(DinHeader dinHeader, T dinBody) {
        this.dinHeader = dinHeader;
        this.dinBody = dinBody;
    }

    public DinHeader getDinHeader() {
        return dinHeader;
    }

    public void setDinHeader(DinHeader dinHeader) {
        this.dinHeader = dinHeader;
    }

    public T getDinBody() {
        return dinBody;
    }

    public void setDinBody(T dinBody) {
        this.dinBody = dinBody;
    }
}
