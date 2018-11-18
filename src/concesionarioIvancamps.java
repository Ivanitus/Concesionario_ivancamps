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
		int idcoche=1;
		int idmodificar=0;
		String cadena_idcoche=null;
		String marca;
		String modelo;
		String precio_venta;
		String precio_compra;
		boolean seguir=true, admin_correcto=false, seguir_admin=true;
		int contador=0;
		final int filas_coches=20;
		final int columnas_coches=5;
		//final int filas_usuarios
		final double IVA=0.21;
		Scanner sc=new Scanner(System.in);
		String coches[][]=new String[filas_coches][columnas_coches];
		//String usuarios[][]=new String[][];
		idcoche=iniciar_coches(coches, idcoche, cadena_idcoche);
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
							idcoche=agregar_modificar_coches(coches, idcoche, cadena_idcoche, marca, modelo, precio_venta, precio_compra, idmodificar);
							seguir_admin=true;
							break;
						case "2": 
							System.out.print("Introduce el ID del coche que quieres modificar: ");
							idmodificar=sc.nextInt();
							idmodificar=posicioncoches(coches, idmodificar, cadena_idcoche);
							if (idmodificar!=-1){
								mostrar_coches(coches, idmodificar);
								cadena_idcoche=coches[idmodificar][0];
								System.out.print("Introduce la marca del coche: ");
								marca=sc.next();
								System.out.print("Introduzca el modelo: ");
								modelo=sc.next();
								System.out.print("Introduzca el precio de venta del coche: ");
								precio_venta=sc.next();
								System.out.print("Introduzca el precio por el que comprarás el coche al cliente: ");
								precio_compra=sc.next();
								idcoche=agregar_modificar_coches(coches, idcoche, cadena_idcoche, marca, modelo, precio_venta, precio_compra, idmodificar);
								mostrar_coches(coches, idmodificar);
								idmodificar=0;
								
							}
							seguir_admin=true;
							break;
						case "3": 
							System.out.print("Introduce el ID del coche que quieres eliminar: ");
							idmodificar=sc.nextInt();
							idmodificar=posicioncoches(coches, idmodificar, cadena_idcoche);
							if (idmodificar!=1) {
								mostrar_coches(coches, idmodificar);
								cadena_idcoche=null;
								marca=null;
								modelo=null;
								precio_venta=null;
								precio_compra=null;
								idcoche=agregar_modificar_coches(coches, idcoche, cadena_idcoche, marca, modelo, precio_venta, precio_compra, idmodificar);
								idmodificar=0;
							}
							seguir_admin=true;
							break;
						case "4": 
							mostrar_coches(coches, idmodificar);
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
			case "2":
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
	public static int iniciar_coches(String coches[][], int idcoche, String cadena_idcoche) {
		//cabecera
		coches[0][0]="ID COCHE";
		coches[0][1]="MARCA";
		coches[0][2]="MODELO";
		coches[0][3]="PRECIO VENTA";
		coches[0][4]="PRECIO COMPRA";
		//modelo 1
		cadena_idcoche=Integer.toString(idcoche);
		coches[1][0]=cadena_idcoche;
		coches[1][1]="Ferrari";
		coches[1][2]="488 Spider";
		coches[1][3]="264275";
		coches[1][4]="195000";
		idcoche=Integer.parseInt(cadena_idcoche);
		idcoche++;
		//modelo 2
		cadena_idcoche=Integer.toString(idcoche);
		coches[2][0]=cadena_idcoche;
		coches[2][1]="Lamborghini";
		coches[2][2]="Huracan Performante";
		coches[2][3]="298087";
		coches[2][4]="205000";
		idcoche=Integer.parseInt(cadena_idcoche);
		idcoche++;
		//modelo 3
		cadena_idcoche=Integer.toString(idcoche);
		coches[3][0]=cadena_idcoche;
		coches[3][1]="Porsche";
		coches[3][2]="911 GTS";
		coches[3][3]="145029";
		coches[3][4]="90000";
		idcoche=Integer.parseInt(cadena_idcoche);
		idcoche++;
		//modelo 4
		cadena_idcoche=Integer.toString(idcoche);
		coches[4][0]=cadena_idcoche;
		coches[4][1]="Aston Martin";
		coches[4][2]="New Vantage";
		coches[4][3]="149995";
		coches[4][4]="95000";
		idcoche=Integer.parseInt(cadena_idcoche);
		idcoche++;
		//modelo 5
		cadena_idcoche=Integer.toString(idcoche);
		coches[5][0]=cadena_idcoche;
		coches[5][1]="Tesla";
		coches[5][2]="Roadster";
		coches[5][3]="212000";
		coches[5][4]="-";
		idcoche=Integer.parseInt(cadena_idcoche);
		idcoche++;
		return idcoche;
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
	public static boolean datoscorrectos(String usuario_introducido, String passwd_introducido, String admin, String passwd_admin, boolean admin_correcto) {
		if(usuario_introducido.equals(admin) && passwd_introducido.equals(passwd_admin)) {
			admin_correcto=true;
		}
		else {
			admin_correcto=false;
		}
		return admin_correcto;	
	}
	public static void menu_admin() {
		System.out.println("1.- Agregar coches");
		System.out.println("2.- Modificar coches");
		System.out.println("3.- Eliminar coches");
		System.out.println("4.- Mostrar coches");
		System.out.println("5.- Cerrar sesion");
		System.out.print("Selecciona una opcion: ");
	}
	public static int agregar_modificar_coches(String coches[][], int idcoche, String cadena_idcoche, String marca, String modelo, String precio_venta, String precio_compra, int idmodificar) {
		if (idmodificar!=0){	
			coches[idmodificar][0]=cadena_idcoche;
			coches[idmodificar][1]=marca;
			coches[idmodificar][2]=modelo;
			coches[idmodificar][3]=precio_venta;
			coches[idmodificar][4]=precio_compra;
			System.out.println("Se han realizado los cambios correctamente");
		}
		else {
			for(int i=1; i<coches.length; i++) {
				if(coches[i][0]==null) {
					cadena_idcoche=Integer.toString(idcoche);
					coches[i][0]=cadena_idcoche;
					idcoche=Integer.parseInt(cadena_idcoche);
					idcoche++;
					coches[i][1]=marca;
					coches[i][2]=modelo;
					coches[i][3]=precio_venta;
					coches[i][4]=precio_compra;
					i=coches.length;
				}
			}
		}
		return idcoche;
	}
	public static void mostrar_coches(String coches[][], int idmodificar) {
		if (idmodificar!=0) {
			System.out.print(coches[0][0] + " | ");
			System.out.print(coches[0][1] + " | ");
			System.out.print(coches[0][2] + " | ");
			System.out.print(coches[0][3] + " | ");
			System.out.println(coches[0][4] + " | ");
			System.out.print(coches[idmodificar][0] + " | ");
			System.out.print(coches[idmodificar][1] + " | ");
			System.out.print(coches[idmodificar][2] + " | ");
			System.out.print(coches[idmodificar][3] + " | ");
			System.out.println(coches[idmodificar][4] + " | ");
		}
		else {
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
	public static int posicioncoches(String coches[][], int idmodificar, String cadena_idcoche) {
		int posicion=-1;
		cadena_idcoche=Integer.toString(idmodificar);
		for (int i=1; i<coches.length; i++) {
			if (coches[i][0]!=null) {
				if(coches[i][0].equals(cadena_idcoche)) {
					posicion=i;
					i=coches.length;
					System.out.println("Coche encontrado");
				}
			}
		}
		return posicion;
	}

}