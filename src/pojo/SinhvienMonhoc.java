package pojo;
// Generated Aug 22, 2019 11:35:44 PM by Hibernate Tools 4.3.1



/**
 * SinhvienMonhoc generated by hbm2java
 */
public class SinhvienMonhoc  implements java.io.Serializable {


     private SinhvienMonhocId id;
     private String hoTen;
     private String gioiTinh;
     private String cmnd;
     private String lop;

    public SinhvienMonhoc() {
    }

	
    public SinhvienMonhoc(SinhvienMonhocId id) {
        this.id = id;
    }
    public SinhvienMonhoc(SinhvienMonhocId id, String hoTen, String gioiTinh, String cmnd, String lop) {
       this.id = id;
       this.hoTen = hoTen;
       this.gioiTinh = gioiTinh;
       this.cmnd = cmnd;
       this.lop = lop;
    }
   
    public SinhvienMonhocId getId() {
        return this.id;
    }
    
    public void setId(SinhvienMonhocId id) {
        this.id = id;
    }
    public String getHoTen() {
        return this.hoTen;
    }
    
    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }
    public String getGioiTinh() {
        return this.gioiTinh;
    }
    
    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }
    public String getCmnd() {
        return this.cmnd;
    }
    
    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }
    public String getLop() {
        return this.lop;
    }
    
    public void setLop(String lop) {
        this.lop = lop;
    }




}


