package com.example.banco_hex_yoder.din.request;

import com.example.banco_hex_yoder.din.generic.DinError;
import com.example.banco_hex_yoder.din.generic.DinHeader;

public class DinRequest<T> {
    private DinHeader dinHeader;
    private T dinBody;
    private DinError dinError;

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

    public DinError getDinError() {
        return dinError;
    }

    public void setDinError(DinError dinError) {
        this.dinError = dinError;
    }
}
