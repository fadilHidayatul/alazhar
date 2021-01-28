package com.example.aplikasitu.SPP;

import java.util.List;

public class SppSiswa {

    /**
     * success : 1
     * status : 200
     * message : data ada
     * DATA : [{"id_siswa":"5","no":1,"nama_siswa":"Sayyid Keane Arzif"},{"id_siswa":"2","no":2,"nama_siswa":"Rahmawati"}]
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
         * id_siswa : 5
         * no : 1
         * nama_siswa : Sayyid Keane Arzif
         */

        private String id_siswa;
        private int no;
        private String nama_siswa;

        public String getId_siswa() {
            return id_siswa;
        }

        public void setId_siswa(String id_siswa) {
            this.id_siswa = id_siswa;
        }

        public int getNo() {
            return no;
        }

        public void setNo(int no) {
            this.no = no;
        }

        public String getNama_siswa() {
            return nama_siswa;
        }

        public void setNama_siswa(String nama_siswa) {
            this.nama_siswa = nama_siswa;
        }
    }
}
