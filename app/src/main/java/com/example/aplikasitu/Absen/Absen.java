package com.example.aplikasitu.Absen;

import java.util.List;

public class Absen {

    /**
     * success : 1
     * status : 200
     * message : Data Ada
     * DATA : [{"bulan":"Januari","tanggal":"2021-01-10","jam_ajar_awal":"07:30:00","jam_ajar_akhir":"08:20:00","jam_masuk":"07:50:00","jam_keluar":"08:10:00"},{"bulan":"Januari","tanggal":"2021-01-22","jam_ajar_awal":"09:10:00","jam_ajar_akhir":"10:00:00","jam_masuk":"09:00:00","jam_keluar":"11:00:00"},{"bulan":"Februari","tanggal":"2021-02-10","jam_ajar_awal":"07:30:00","jam_ajar_akhir":"08:20:00","jam_masuk":"07:40:00","jam_keluar":"08:10:00"},{"bulan":"Desember","tanggal":"2020-12-20","jam_ajar_awal":"07:30:00","jam_ajar_akhir":"08:20:00","jam_masuk":"07:50:00","jam_keluar":"08:10:00"}]
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
         * bulan : Januari
         * tanggal : 2021-01-10
         * jam_ajar_awal : 07:30:00
         * jam_ajar_akhir : 08:20:00
         * jam_masuk : 07:50:00
         * jam_keluar : 08:10:00
         */

        private String bulan;
        private String tanggal;
        private String jam_ajar_awal;
        private String jam_ajar_akhir;
        private String jam_masuk;
        private String jam_keluar;

        public String getBulan() {
            return bulan;
        }

        public void setBulan(String bulan) {
            this.bulan = bulan;
        }

        public String getTanggal() {
            return tanggal;
        }

        public void setTanggal(String tanggal) {
            this.tanggal = tanggal;
        }

        public String getJam_ajar_awal() {
            return jam_ajar_awal;
        }

        public void setJam_ajar_awal(String jam_ajar_awal) {
            this.jam_ajar_awal = jam_ajar_awal;
        }

        public String getJam_ajar_akhir() {
            return jam_ajar_akhir;
        }

        public void setJam_ajar_akhir(String jam_ajar_akhir) {
            this.jam_ajar_akhir = jam_ajar_akhir;
        }

        public String getJam_masuk() {
            return jam_masuk;
        }

        public void setJam_masuk(String jam_masuk) {
            this.jam_masuk = jam_masuk;
        }

        public String getJam_keluar() {
            return jam_keluar;
        }

        public void setJam_keluar(String jam_keluar) {
            this.jam_keluar = jam_keluar;
        }
    }
}
