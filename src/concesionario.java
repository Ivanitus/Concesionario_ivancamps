import java.util.Scanner;
public class concesionario {
	public static void main (String [] args) {
		String admin="admin";
		String passwd_admin="admin123";
		String usuario_introducido, passwd_introducido;
		String opcion;
		boolean seguir=true;
		final double IVA=0.21;
		Scanner sc=new Scanner(System.in);
		String coches[][]=new String[20][4];
		coches[0][0]="MARCA";
		coches[0][1]="MODELO";
		coches[0][2]="PRECIO VENTA";
		coches[0][3]="PRECIO COMPRA";
		//modelo 1
		coches[1][0]="Ferrari";
		coches[1][1]="488 Spider";
		coches[1][2]="264275";
		coches[1][3]="195000";
		//modelo 2
		coches[2][0]="Lamborghini";
		coches[2][1]="Huracan Performante";
		coches[2][2]="298087";
		coches[2][3]="205000";
		//modelo 3
		coches[3][0]="Porsche";
		coches[3][1]="911 GTS";
		coches[3][2]="145029";
		coches[3][3]="90000";
		//modelo 4
		coches[4][0]="Aston Martin";
		coches[4][1]="New Vantage";
		coches[4][2]="149995";
		coches[4][3]="95000";
		//modelo 5
		coches[5][0]="Tesla";
		coches[5][1]="Roadster";
		coches[5][2]="212000";
		coches[5][3]="-";
		/*for(int i=1; i < coches.length; i++) {
			if (coches[i]!=null) {
				System.out.println("");
			for(int j=0; j<coches[i].length; j++) {
				if (coches[i][j]!=null) {
				System.out.print(coches[i][j]+" ");
				}
			}
			}
		}*/
		do {
			menu_principal();
			opcion=sc.next();
			/*switch (opcion) {
			}*/
		}while(seguir);
	}
	public static void menu_principal() {
		System.out.println("1.- Entrar como administrador");
		System.out.println("2.- Entrar como usuario");
		System.out.println("3.- Salir");
		System.out.print("Introduce una opcion: ");
	}
}