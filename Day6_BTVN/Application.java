package Day6_BTVN;

import java.util.Scanner;

public class Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	    Scanner sc = new Scanner(System.in);
        System.out.print("Nhập kích thước cho mảng (số nhân viên): ");
        int n = sc.nextInt();
        sc.nextLine(); 

        Employee[] arr = new Employee[n];

        for (int i = 0; i < n; i++) {
            System.out.println("\nNhập thông tin nhân viên #" + (i + 1) + ":");
            arr[i] = new Employee();
            arr[i].nhanvien();
        }

        System.out.println("\nDanh sách nhân viên:");
        for (int i = 0; i < n; i++) {
            arr[i].inThongTin();
        }

	}

}
