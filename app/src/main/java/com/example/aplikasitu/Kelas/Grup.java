package com.example.aplikasitu.Kelas;

import java.util.List;

public class Grup {

    /**
     * success : 1
     * status : 200
     * message : Data ada
     * DATA : [{"grup":"Ibnu Sina"},{"grup":"Al Farabi"}]
     */

    private int success;
    private int status;
    private String message;
    private List<DATABean> DATA;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DATABean> getDATA() {
        return DATA;
    }

    public void setDATA(List<DATABean> DATA) {
        this.DATA = DATA;
    }

    public static class DATABean {
        /**
         * grup : Ibnu Sina
         */

        private String grup;

        public String getGrup() {
            return grup;
        }

        public void setGrup(String grup) {
            this.grup = grup;
        }
    }
}
