import java.util.Scanner;

/**
 * 
 */

/**
 * @author Ivan Camps Sanchez
 *
 */
public class concesionarioIvancamps {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final String admin="admin";
	 	final String passwd_admin="admin123";
		String usuario_introducido, passwd_introducido;
		String opcion;
		String marca;
		String modelo=null;
		String precio_venta=null;
		String precio_compra=null;
		boolean seguir=true, admin_correcto=false, seguir_admin=true;
		int contador=0;
		final int filas_coches=20;
		final int columnas_coches=4;
		final double IVA=0.21;
		Scanner sc=new Scanner(System.in);
		String coches[][]=new String[filas_coches][columnas_coches];
		iniciar_coches(coches);
		do {
			menu_principal();
			opcion=sc.next();
			switch (opcion) {
			case "1": 
					System.out.print("Introduce el nombre de usuario: ");
					usuario_introducido=sc.next();
					System.out.print("Introduce la contraseña: ");
					passwd_introducido=sc.next();
				if (datoscorrectos(usuario_introducido, passwd_introducido, admin, passwd_admin, admin_correcto)) {
					do {
					menu_admin();
					opcion=sc.next();
						switch(opcion) {
						case "1": 
							System.out.print("Introduzca la marca del coche: ");
							marca=sc.next();
							System.out.print("Introduzca el modelo: ");
							modelo=sc.next();
							System.out.print("Introduzca el precio de venta del coche: ");
							precio_venta=sc.next();
							System.out.print("Introduzca el precio por el que comprarás el coche al cliente: ");
							precio_compra=sc.next();
							agregar_coches(coches, marca, modelo, precio_venta, precio_compra);
							
						seguir_admin=true;
								break;
						case "2": System.out.println(opcion);
						seguir_admin=true;
								break;
						case "3": System.out.println(opcion);
						seguir_admin=true;
								break;
						case "4": 
							mostrar_coches(coches);
						seguir_admin=true;
							break;
						case "5": System.out.println(opcion);
								seguir_admin=false;
							break;
						default:
							System.out.println("Opción errónea");
							seguir_admin=true;
						}
					}while(seguir_admin);
				}
				else {
					System.out.println("Nombre de usuario o contraseña incorrectos");
					seguir=true;
				}
				break;
			case "3":
				seguir=false;
				break;
			default:
				System.out.println("Opcion erronea");
				seguir=true;
			}
		}while(seguir);
	}
	public static void iniciar_coches(String coches[][]) {
		//cabecera
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
	}
	public static void menu_principal() {
		System.out.println("Bienvenido al concesionario de supercoches Camps' Supercars");
		System.out.println("|-------------------------------|");
		System.out.println("| 1.- Entrar como administrador |");
		System.out.println("| 2.- Entrar como usuario       |");
		System.out.println("| 3.- Salir                     |");
		System.out.println("|-------------------------------|");
		System.out.print("Introduce una opcion: ");
	}
	public static void menu_admin() {
		System.out.println("1.- Agregar coches");
		System.out.println("2.- Modificar coches");
		System.out.println("3.- Eliminar coches");
		System.out.println("4.- Mostrar coches");
		System.out.println("5.- Salir");
		System.out.print("Selecciona una opcion: ");
	}
	public static boolean datoscorrectos(String usuario_introducido, String passwd_introducido, String admin, String passwd_admin, boolean admin_correcto) {
		if(usuario_introducido.equals(admin) && passwd_introducido.equals(passwd_admin)) {
			admin_correcto=true;
		}
		else {
			admin_correcto=false;
		}
		return admin_correcto;	
	}
	public static void agregar_coches(String coches[][], String marca, String modelo, String precio_venta, String precio_compra) {
		
		for(int i=1; i<coches.length; i++) {
			if(coches[i][0]==null) {
				coches[i][0]=marca;
				//System.out.println(coches[i][j]);
				//System.out.print(i + j);
				coches[i][1]=modelo;
				coches[i][2]=precio_venta;
				coches[i][3]=precio_compra;
				i=coches.length;
			}
		}
	}
	public static void mostrar_coches(String coches[][]) {
		for (int i=0; i<coches.length; i++){
			if (coches[i][0]!=null) {
				for (int j=0; j<coches[i].length; j++) {
					System.out.print(coches[i][j] + " | ");
				}
			System.out.println("");
			}
		}
	}

}