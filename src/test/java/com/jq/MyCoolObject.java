package com.jq;

import java.io.Serializable;
import java.util.Objects;

public class MyCoolObject implements Serializable {
    String status;
    int code;

    public MyCoolObject(String status, int code) {
        this.status = status;
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "MyCoolObject{" +
                "status='" + status + '\'' +
                ", code=" + code +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyCoolObject that = (MyCoolObject) o;
        return code == that.code && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, code);
    }
}
