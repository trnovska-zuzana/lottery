package lottery.frontend;

import java.util.ArrayList;
import java.util.List;

public class MessageBox {

    private List<String> info;

    private List<String> error;

    private List<String> warn;

    private List <String> success;

    public MessageBox() {
        info = new ArrayList<>();
        error = new ArrayList<>();
        warn = new ArrayList<>();
        success = new ArrayList<>();
    }

    public List<String> getInfo() {
        return info;
    }

    public void setInfo(List<String> info) {
        this.info = info;
    }

    public List<String> getError() {
        return error;
    }

    public void setError(List<String> error) {
        this.error = error;
    }

    public List<String> getWarn() {
        return warn;
    }

    public void setWarn(List<String> warn) {
        this.warn = warn;
    }

    public List<String> getSuccess() {
        return success;
    }

    public void setSuccess(List<String> success) {
        this.success = success;
    }

}