package uts.eai.elabs.data;

public class DataTes {
    //tambahan variable dipilih untuk menampung jawaban dari radio group
    private String id, soal, jawaban, opsi1, opsi2, opsi3, opsi4, dipilih ;

    public DataTes() {
    }

    public DataTes(String id, String soal, String jawaban, String opsi1, String opsi2, String opsi3, String opsi4) {
        this.id = id;
        this.soal = soal;
        this.jawaban = jawaban;
        this.opsi1=opsi1;
        this.opsi2=opsi2;
        this.opsi3=opsi3;
        this.opsi4=opsi4;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSoal() {
        return soal;
    }

    public void setSoal(String soal) {
        this.soal = soal;
    }

    public String getJawaban() {
        return jawaban;
    }

    public void setJawaban(String jawaban) {
        this.jawaban = jawaban;
    }

    public String getOpsi1() {
        return opsi1;
    }

    public void setOpsi1(String opsi1) {
        this.opsi1 = opsi1;
    }


    public String getOpsi2() {
        return opsi2;
    }

    public void setOpsi2(String opsi2) {
        this.opsi2 = opsi2;
    }


    public String getOpsi3() {
        return opsi3;
    }

    public void setOpsi3(String opsi3) {
        this.opsi3 = opsi3;
    }

    public String getOpsi4() {
        return opsi4;
    }

    public void setOpsi4(String opsi4) {
        this.opsi4 = opsi4;
    }

    //setter dan getter dipilih
    public String getDipilih() {
        return dipilih;
    }

    public void setDipilih(String dipilih) {
        this.dipilih = dipilih;
    }
}