package pojo;
// Generated Aug 8, 2019 11:08:33 PM by Hibernate Tools 4.3.1



/**
 * Diem generated by hbm2java
 */
public class Diem  implements java.io.Serializable {


     private String mssv;
     private String hoTen;
     private Float diemGk;
     private Float diemCk;
     private Float diemKhac;
     private Float diemTong;

    public Diem() {
    }

	
    public Diem(String mssv) {
        this.mssv = mssv;
    }
    public Diem(String mssv, String hoTen, Float diemGk, Float diemCk, Float diemKhac, Float diemTong) {
       this.mssv = mssv;
       this.hoTen = hoTen;
       this.diemGk = diemGk;
       this.diemCk = diemCk;
       this.diemKhac = diemKhac;
       this.diemTong = diemTong;
    }
   
    public String getMssv() {
        return this.mssv;
    }
    
    public void setMssv(String mssv) {
        this.mssv = mssv;
    }
    public String getHoTen() {
        return this.hoTen;
    }
    
    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }
    public Float getDiemGk() {
        return this.diemGk;
    }
    
    public void setDiemGk(Float diemGk) {
        this.diemGk = diemGk;
    }
    public Float getDiemCk() {
        return this.diemCk;
    }
    
    public void setDiemCk(Float diemCk) {
        this.diemCk = diemCk;
    }
    public Float getDiemKhac() {
        return this.diemKhac;
    }
    
    public void setDiemKhac(Float diemKhac) {
        this.diemKhac = diemKhac;
    }
    public Float getDiemTong() {
        return this.diemTong;
    }
    
    public void setDiemTong(Float diemTong) {
        this.diemTong = diemTong;
    }




}


