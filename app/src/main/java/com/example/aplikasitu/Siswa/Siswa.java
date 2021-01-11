package com.example.aplikasitu.Siswa;

import java.util.List;

public class Siswa {

    /**
     * success : 1
     * status : 200
     * message : Data ada
     * DATA : [{"id_siswa":"1","nama_siswa":"Siti Nur Baya","kelas":"1","grup":"A","gender":"Perempuan","tempat_lahir":"Padang","tgl_lahir":"2021-01-01"},{"id_siswa":"2","nama_siswa":"Rahmawati","kelas":"1","grup":"A","gender":"Perempuan","tempat_lahir":"Padang Pauh","tgl_lahir":"2020-06-16"},{"id_siswa":"3","nama_siswa":"nama","kelas":"1","grup":"A","gender":"laki","tempat_lahir":"adaw","tgl_lahir":"2020-01-01"},{"id_siswa":"4","nama_siswa":"aa","kelas":"2","grup":"B","gender":"txtLakiLaki","tempat_lahir":"pp","tgl_lahir":"1995-01-07"}]
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
         * id_siswa : 1
         * nama_siswa : Siti Nur Baya
         * kelas : 1
         * grup : A
         * gender : Perempuan
         * tempat_lahir : Padang
         * tgl_lahir : 2021-01-01
         */

        private String id_siswa;
        private String nama_siswa;
        private String kelas;
        private String grup;
        private String gender;
        private String tempat_lahir;
        private String tgl_lahir;

        public String getId_siswa() {
            return id_siswa;
        }

        public void setId_siswa(String id_siswa) {
            this.id_siswa = id_siswa;
        }

        public String getNama_siswa() {
            return nama_siswa;
        }

        public void setNama_siswa(String nama_siswa) {
            this.nama_siswa = nama_siswa;
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

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getTempat_lahir() {
            return tempat_lahir;
        }

        public void setTempat_lahir(String tempat_lahir) {
            this.tempat_lahir = tempat_lahir;
        }

        public String getTgl_lahir() {
            return tgl_lahir;
        }

        public void setTgl_lahir(String tgl_lahir) {
            this.tgl_lahir = tgl_lahir;
        }
    }
}
