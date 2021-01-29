package com.example.aplikasitu.SPP;

import java.util.List;

public class Spp  {

    /**
     * success : 1
     * status : 200
     * message : Data ada
     * DATA : [{"id_spp":"2","id_siswa":"2","tgl_bayar":"2021-01-22","bukti":"bayar.jpg","status":"0"}]
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
         * id_spp : 2
         * id_siswa : 2
         * tgl_bayar : 2021-01-22
         * bukti : bayar.jpg
         * status : 0
         */

        private String id_spp;
        private String id_siswa;
        private String tgl_bayar;
        private String bukti;
        private String status;

        public String getId_spp() {
            return id_spp;
        }

        public void setId_spp(String id_spp) {
            this.id_spp = id_spp;
        }

        public String getId_siswa() {
            return id_siswa;
        }

        public void setId_siswa(String id_siswa) {
            this.id_siswa = id_siswa;
        }

        public String getTgl_bayar() {
            return tgl_bayar;
        }

        public void setTgl_bayar(String tgl_bayar) {
            this.tgl_bayar = tgl_bayar;
        }

        public String getBukti() {
            return bukti;
        }

        public void setBukti(String bukti) {
            this.bukti = bukti;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
