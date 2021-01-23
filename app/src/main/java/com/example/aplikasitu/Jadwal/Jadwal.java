package com.example.aplikasitu.Jadwal;

import java.util.List;

public class Jadwal {

    /**
     * success : 1
     * status : 200
     * message : Data ada
     * DATA : [{"id":"1","jam_awal":"07:30:00","jam_akhir":"08:20:00","kelas":"1","grup":"Ibnu Sina","matpel":"Matematika","hari":"Senin","nama_guru":"YULHERNIWATI S.Kom.,M.T","nip":"1969090112120803"}]
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
         * jam_awal : 07:30:00
         * jam_akhir : 08:20:00
         * kelas : 1
         * grup : Ibnu Sina
         * matpel : Matematika
         * hari : Senin
         * nama_guru : YULHERNIWATI S.Kom.,M.T
         * nip : 1969090112120803
         */

        private String id;
        private String jam_awal;
        private String jam_akhir;
        private String kelas;
        private String grup;
        private String matpel;
        private String hari;
        private String nama_guru;
        private String nip;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getJam_awal() {
            return jam_awal;
        }

        public void setJam_awal(String jam_awal) {
            this.jam_awal = jam_awal;
        }

        public String getJam_akhir() {
            return jam_akhir;
        }

        public void setJam_akhir(String jam_akhir) {
            this.jam_akhir = jam_akhir;
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

        public String getMatpel() {
            return matpel;
        }

        public void setMatpel(String matpel) {
            this.matpel = matpel;
        }

        public String getHari() {
            return hari;
        }

        public void setHari(String hari) {
            this.hari = hari;
        }

        public String getNama_guru() {
            return nama_guru;
        }

        public void setNama_guru(String nama_guru) {
            this.nama_guru = nama_guru;
        }

        public String getNip() {
            return nip;
        }

        public void setNip(String nip) {
            this.nip = nip;
        }
    }
}
