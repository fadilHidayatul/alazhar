package com.example.aplikasitu.Pegawai;

import java.util.List;

public class Pegawai {

    /**
     * success : 1
     * status : 200
     * message : Data ada
     * DATA : [{"id":"1","nama_pegawai":"sidiq","nip":"12","gender":"Male","email":"sidiq@gmail.com","nohp":"121212","foto":"1"},{"id":"2","nama_pegawai":"Siti Rahmawati","nip":"183192221923","gender":"Female","email":"INDRI_RAHMAYUNI@gmail.com","nohp":"08230129","foto":"a.jpg"},{"id":"3","nama_pegawai":"INDRI RAHMAYUNI","nip":"183192221923","gender":"Female","email":"INDRI_RAHMAYUNI@gmail.com","nohp":"08230129","foto":"a.jpg"},{"id":"4","nama_pegawai":"Agus Sudirman","nip":"183192221923","gender":"Male","email":"INDRI_RAHMAYUNI@gmail.com","nohp":"08230129","foto":"a.jpg"},{"id":"5","nama_pegawai":"Burhanudin","nip":"19231079","gender":"Male","email":"burhan@gmail.com","nohp":"0811998271","foto":"b.jpg"}]
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
         * id : 1
         * nama_pegawai : sidiq
         * nip : 12
         * gender : Male
         * email : sidiq@gmail.com
         * nohp : 121212
         * foto : 1
         */

        private String id;
        private String nama_pegawai;
        private String nip;
        private String gender;
        private String email;
        private String nohp;
        private String foto;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNama_pegawai() {
            return nama_pegawai;
        }

        public void setNama_pegawai(String nama_pegawai) {
            this.nama_pegawai = nama_pegawai;
        }

        public String getNip() {
            return nip;
        }

        public void setNip(String nip) {
            this.nip = nip;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getNohp() {
            return nohp;
        }

        public void setNohp(String nohp) {
            this.nohp = nohp;
        }

        public String getFoto() {
            return foto;
        }

        public void setFoto(String foto) {
            this.foto = foto;
        }
    }
}
