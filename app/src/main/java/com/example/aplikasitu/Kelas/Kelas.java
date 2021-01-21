package com.example.aplikasitu.Kelas;

import java.util.List;

public class Kelas {

    /**
     * success : 1
     * status : 200
     * message : Data ada
     * DATA : [{"id_kelas":"1","kelas":"1","grup":"A","jumlah_murid":3},{"id_kelas":"2","kelas":"1","grup":"B","jumlah_murid":0},{"id_kelas":"3","kelas":"2","grup":"A","jumlah_murid":0},{"id_kelas":"4","kelas":"2","grup":"B","jumlah_murid":1},{"id_kelas":"5","kelas":"3","grup":"A","jumlah_murid":0},{"id_kelas":"6","kelas":"3","grup":"B","jumlah_murid":0},{"id_kelas":"7","kelas":"4","grup":"A","jumlah_murid":0},{"id_kelas":"8","kelas":"4","grup":"B","jumlah_murid":0},{"id_kelas":"9","kelas":"5","grup":"A","jumlah_murid":0},{"id_kelas":"10","kelas":"5","grup":"B","jumlah_murid":0},{"id_kelas":"11","kelas":"6","grup":"A","jumlah_murid":0},{"id_kelas":"12","kelas":"6","grup":"B","jumlah_murid":1}]
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
         * id_kelas : 1
         * kelas : 1
         * grup : A
         * jumlah_murid : 3
         */

        private String id_kelas;
        private String kelas;
        private String grup;
        private int jumlah_murid;

        public String getId_kelas() {
            return id_kelas;
        }

        public void setId_kelas(String id_kelas) {
            this.id_kelas = id_kelas;
        }

        public String getKelas() {
            return kelas;
        }

        public void setKelas(String kelas) {
            this.kelas = kelas;
        }

        public String getGrup() {
            return grup;
        }

        public void setGrup(String grup) {
            this.grup = grup;
        }

        public int getJumlah_murid() {
            return jumlah_murid;
        }

        public void setJumlah_murid(int jumlah_murid) {
            this.jumlah_murid = jumlah_murid;
        }


    }
}
