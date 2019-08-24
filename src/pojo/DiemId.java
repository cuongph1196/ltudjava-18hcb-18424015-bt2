package pojo;
// Generated Aug 24, 2019 11:05:33 PM by Hibernate Tools 4.3.1



/**
 * DiemId generated by hbm2java
 */
public class DiemId  implements java.io.Serializable {


     private String mssv;
     private String lop;
     private String monHoc;

    public DiemId() {
    }

    public DiemId(String mssv, String lop, String monHoc) {
       this.mssv = mssv;
       this.lop = lop;
       this.monHoc = monHoc;
    }
   
    public String getMssv() {
        return this.mssv;
    }
    
    public void setMssv(String mssv) {
        this.mssv = mssv;
    }
    public String getLop() {
        return this.lop;
    }
    
    public void setLop(String lop) {
        this.lop = lop;
    }
    public String getMonHoc() {
        return this.monHoc;
    }
    
    public void setMonHoc(String monHoc) {
        this.monHoc = monHoc;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof DiemId) ) return false;
		 DiemId castOther = ( DiemId ) other; 
         
		 return ( (this.getMssv()==castOther.getMssv()) || ( this.getMssv()!=null && castOther.getMssv()!=null && this.getMssv().equals(castOther.getMssv()) ) )
 && ( (this.getLop()==castOther.getLop()) || ( this.getLop()!=null && castOther.getLop()!=null && this.getLop().equals(castOther.getLop()) ) )
 && ( (this.getMonHoc()==castOther.getMonHoc()) || ( this.getMonHoc()!=null && castOther.getMonHoc()!=null && this.getMonHoc().equals(castOther.getMonHoc()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getMssv() == null ? 0 : this.getMssv().hashCode() );
         result = 37 * result + ( getLop() == null ? 0 : this.getLop().hashCode() );
         result = 37 * result + ( getMonHoc() == null ? 0 : this.getMonHoc().hashCode() );
         return result;
   }   


}


