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
		String admin="admin";
		String passwd_admin="admin123";
		String usuario_introducido, passwd_introducido;
		String opcion;
		boolean seguir=true, admin_correcto=false, salir_admin=false;
		int contador=0;
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
		do {
			menu_principal();
			opcion=sc.next();
			switch (opcion) {
			case "1": 
					System.out.print("Introduce el nombre de usuario: ");
					usuario_introducido=sc.next();
					System.out.print("Introduce la contrase�a: ");
					passwd_introducido=sc.next();
				if (datoscorrectos(usuario_introducido, passwd_introducido, admin, passwd_admin, admin_correcto)) {
					//System.out.println("Correcto");
					do {
					menu_admin();
					opcion=sc.next();
						switch(opcion) {
						case "1": System.out.println(opcion);
						salir_admin=true;
								break;
						case "2": System.out.println(opcion);
						salir_admin=true;
								break;
						case "3": System.out.println(opcion);
						salir_admin=true;
								break;
						case "4": System.out.println(opcion);
						salir_admin=true;
							break;
						case "5": System.out.println(opcion);
							salir_admin=true;
							break;
						default:
							System.out.println("Opci�n err�nea");
							salir_admin=false;
						}
					}while(!salir_admin);
				}
				else {
					System.out.println("Nombre de usuario o contrase�a incorrectos");
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
	public static void menu_principal() {
		System.out.println("1.- Entrar como administrador");
		System.out.println("2.- Entrar como usuario");
		System.out.println("3.- Salir");
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

}
