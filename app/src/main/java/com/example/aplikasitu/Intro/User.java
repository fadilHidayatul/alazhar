package com.example.aplikasitu.Intro;

public class User {

    /**
     * status : 200
     * message : Success Login
     * data : {"id_user":"4","username":"burhan","id_pegawai":"5","level":"4"}
     */

    private int status;
    private String message;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id_user : 4
         * username : burhan
         * id_pegawai : 5
         * level : 4
         */

        private String id_user;
        private String username;
        private String id_pegawai;
        private String level;

        public String getId_user() {
            return id_user;
        }

        public void setId_user(String id_user) {
            this.id_user = id_user;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getId_pegawai() {
            return id_pegawai;
        }

        public void setId_pegawai(String id_pegawai) {
            this.id_pegawai = id_pegawai;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }
    }
}
