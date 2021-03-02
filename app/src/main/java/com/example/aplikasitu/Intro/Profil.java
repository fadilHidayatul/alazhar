package com.example.aplikasitu.Intro;

import java.util.List;

public class Profil {

    /**
     * success : 1
     * status : 200
     * message : Data ada
     * DATA : [{"nip":"19231079","nama":"Burhanudin","email":"burhan@gmail.com","tlp":"0811998271","alamat":"Pauh, Padang","masuk":"2015-05-15","tempat":"Saung","tanggal":"1971-10-10","pendidikan":"D3"}]
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
         * nip : 19231079
         * nama : Burhanudin
         * email : burhan@gmail.com
         * tlp : 0811998271
         * alamat : Pauh, Padang
         * masuk : 2015-05-15
         * tempat : Saung
         * tanggal : 1971-10-10
         * pendidikan : D3
         */

        private String nip;
        private String nama;
        private String email;
        private String tlp;
        private String alamat;
        private String masuk;
        private String tempat;
        private String tanggal;
        private String pendidikan;

        public String getNip() {
            return nip;
        }

        public void setNip(String nip) {
            this.nip = nip;
        }

        public String getNama() {
            return nama;
        }

        public void setNama(String nama) {
            this.nama = nama;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getTlp() {
            return tlp;
        }

        public void setTlp(String tlp) {
            this.tlp = tlp;
        }

        public String getAlamat() {
            return alamat;
        }

        public void setAlamat(String alamat) {
            this.alamat = alamat;
        }

        public String getMasuk() {
            return masuk;
        }

        public void setMasuk(String masuk) {
            this.masuk = masuk;
        }

        public String getTempat() {
            return tempat;
        }

        public void setTempat(String tempat) {
            this.tempat = tempat;
        }

        public String getTanggal() {
            return tanggal;
        }

        public void setTanggal(String tanggal) {
            this.tanggal = tanggal;
        }

        public String getPendidikan() {
            return pendidikan;
        }

        public void setPendidikan(String pendidikan) {
            this.pendidikan = pendidikan;
        }
    }
}
