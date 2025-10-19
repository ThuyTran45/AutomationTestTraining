package Day6_BTVN;

import java.util.Scanner;

public class Employee {
    String hoTen;
    String maNV;
    int sdt;
	public String getHoTen() {
		return hoTen;
	}
	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}
	public String getMaNV() {
		return maNV;
	}
	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}
	public int getSdt() {
		return sdt;
	}
	public void setSdt(int sdt) {
		this.sdt = sdt;
	}
    public void nhanvien(){
    	Scanner sc = new Scanner(System.in);
    	System.out.println("Nhập họ và tên: ");
    	this.hoTen = sc.nextLine();
    	System.out.println("Nhập mã nhân viên: ");
    	this.maNV  = sc.nextLine();
    	System.out.println("Nhập số điện thoại: ");
    	this.sdt   = sc.nextInt();
    	}
    public void inThongTin() {
        System.out.println("Mã NV: " + maNV + " | Họ tên: " + hoTen + " | SĐT: " + sdt);
    }
}
