package co.skreel.android.models;

import com.google.gson.annotations.SerializedName;

public class Meta {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("unique_code")
    private String unique_code;

    public Meta() {
    }

    public Meta(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public String getUnique_code() {
        return unique_code;
    }

    public void setUnique_code(String unique_code) {
        this.unique_code = unique_code;
    }

    @Override
    public String toString() {
        return "Meta{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", unique_code='" + unique_code + '\'' +
                '}';
    }
}
