package automation.testsuite;

import java.util.Scanner;

import org.testng.annotations.Test;

public class BTVN_Day7 {
	String hoten;

public String getHoten() {
	return hoten;
}

public void setHoten(String hoten) {
	this.hoten = hoten;
}
@Test
public void nhanvien() {
	Scanner sc = new Scanner(System.in);
	System.out.println("Nhập số lượng nhân viên");
	int n = sc.nextInt();
	sc.nextLine();
	String[] arr = new String[n];
	for (int i = 0; i<n ;i++)
	{
		System.out.println("Nhập họ tên nhân viên: " + (i+1));
		arr[i] = sc.nextLine();
	}
	  System.out.println("\nDanh sách nhân viên:");
      for (int i = 0; i < n; i++) {
    	  System.out.println("Nhân viên số " + (i+1) + ": " + arr[i]);
      }
}
}
