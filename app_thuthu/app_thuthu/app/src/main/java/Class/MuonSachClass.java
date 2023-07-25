package Class;

public class MuonSachClass {
    Integer mamuon, id_dg;
    String tendocgia;
    Integer sosachmuon;

    public MuonSachClass(Integer mamuon, Integer id_dg, String tendocgia, Integer sosachmuon) {
        this.mamuon = mamuon;
        this.id_dg = id_dg;
        this.tendocgia = tendocgia;
        this.sosachmuon = sosachmuon;
    }

    public MuonSachClass() {
    }

    public Integer getId_dg() {
        return id_dg;
    }

    public void setId_dg(Integer id_dg) {
        this.id_dg = id_dg;
    }

    public Integer getMamuon() {
        return mamuon;
    }

    public String getTendocgia() {
        return tendocgia;
    }

    public void setMamuon(Integer mamuon) {
        this.mamuon = mamuon;
    }

    public void setTendocgia(String tendocgia) {
        this.tendocgia = tendocgia;
    }

    public Integer getSosachmuon() {
        return sosachmuon;
    }

    public void setSosachmuon(Integer sosachmuon) {
        this.sosachmuon = sosachmuon;
    }
}
