package din;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DinError {

    private String type;
    private String date;
    private String origin;
    private String code;
    private String codeErrorProvider;
    private String message;
    private String detail;

    public DinError() {
        this(DinErrorEnum.SUCCESS);
    }

    public DinError(DinErrorEnum errorEnum) {
        this.type = errorEnum.getType();
        this.code = errorEnum.getCode();
        this.codeErrorProvider = errorEnum.getErrorCodeProvider();
        this.message = errorEnum.getMessage();
        this.origin = "User service's";
        this.detail = errorEnum.getMessage();
        this.calculateDate();
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCodeErrorProvider() {
        return codeErrorProvider;
    }

    public void setCodeErrorProvider(String codeErrorProvider) {
        this.codeErrorProvider = codeErrorProvider;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void calculateDate(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSz");
        this.date = dateFormat.format(new Date());
    }
}
