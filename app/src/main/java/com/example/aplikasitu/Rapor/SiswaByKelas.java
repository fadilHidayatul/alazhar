package com.example.aplikasitu.Rapor;

import java.util.List;

public class SiswaByKelas {

    /**
     * success : 1
     * status : 200
     * message : Data ada
     * DATA : [{"no":1,"id_siswa":"3","siswa":"nama"},{"no":2,"id_siswa":"2","siswa":"Rahmawati"},{"no":3,"id_siswa":"1","siswa":"Siti Nur Baya"}]
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
         * no : 1
         * id_siswa : 3
         * siswa : nama
         */

        private int no;
        private String id_siswa;
        private String siswa;

        public int getNo() {
            return no;
        }

        public void setNo(int no) {
            this.no = no;
        }

        public String getId_siswa() {
            return id_siswa;
        }

        public void setId_siswa(String id_siswa) {
            this.id_siswa = id_siswa;
        }

        public String getSiswa() {
            return siswa;
        }

        public void setSiswa(String siswa) {
            this.siswa = siswa;
        }
    }
}
