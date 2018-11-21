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
		String dni;
		String nombre;
		String apellido_uno;
		String apellido_dos;
		String correo;
		String clave;
		int precioconiva;
		boolean seguir=true, admin_correcto=false, seguir_admin=true, seguir_usuario=true, usuario_correcto=false, seguir_usuario_iniciado=true;
		boolean dnivalido;
		int numcocheusuario=0;
		final int filas_coches=20;
		final int columnas_coches=5;
		final int filas_usuarios=30;
		final int columnas_usuarios=6;
		final double IVA=0.21;
		Scanner sc=new Scanner(System.in);
		String coches[][]=new String[filas_coches][columnas_coches];
		String usuarios[][]=new String[filas_usuarios][columnas_usuarios];
		idcoche=iniciar_coches(coches, idcoche, cadena_idcoche);
		iniciar_usuarios(usuarios);
		System.out.println(usuarios[1][4]);
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
					sc.nextLine();
						switch(opcion) {
						case "1": 
							System.out.print("Introduzca la marca del coche: ");
							marca=sc.nextLine();
							sc.nextLine();
							System.out.print("Introduzca el modelo: ");
							modelo=sc.nextLine();
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
						case "5": 
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
				do {
					menu_usuario_uno();
					opcion=sc.next();
					switch(opcion) {
					case "1":
						System.out.print("Introduce tu e-mail: ");
						correo=sc.next();
						System.out.print("Introduce tu contraseña de acceso: ");
						clave=sc.next();
						usuario_correcto=comprobar_usuario(usuarios, correo, clave, usuario_correcto);
						if (usuario_correcto) {
							do {
								menu_usuario_dos();
								opcion=sc.next();
								sc.nextLine();
								switch(opcion) {
								case "1":
									System.out.print("Introduce el modelo del coche que desea comprar: ");
									modelo=sc.nextLine();
									numcocheusuario=posicion_coche_usuario(coches, modelo);
									seguir_usuario_iniciado=true;
									break;
								case "2":
									seguir_usuario_iniciado=true;
									break;
								case "3":
									mostrar_coches(coches, idmodificar);
									seguir_usuario_iniciado=true;
									break;
								case "4":
									seguir_usuario_iniciado=false;
									break;
								default:
									System.out.println("Opcion erronea");
									seguir_usuario_iniciado=true;
									break;
								}
							}while(seguir_usuario_iniciado);
						}
						else {
							System.out.println("Usuario y/o contraseña incorrectos.");
						}
						break;
					case "2":
						System.out.print("Introduce tu DNI: ");
						dni=sc.next();
						sc.nextLine();
						dnivalido=comprobar(dni);
						if (dnivalido){
							System.out.println("El DNI introducido es valido");
							System.out.print("Introduce tu nombre: ");
							nombre=sc.nextLine();
							
							System.out.print("Introduce tu primer apellido: ");
							apellido_uno=sc.next();
							System.out.print("Introduce tu segundo apellido: ");
							apellido_dos=sc.next();
							System.out.print("Introduce tu correo: ");
							correo=sc.next();
							System.out.print("Introduce tu contraseña: ");
							clave=sc.next();
							registro(usuarios, dni, nombre, apellido_uno, apellido_dos, correo, clave);
						}
						else {
							System.out.println("El DNI introducido NO es valido");
						}
						seguir_usuario=true;
						break;
					case "3":
						seguir_usuario=false;
						break;
					default:	
					}
				}while(seguir_usuario);
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
		coches[0][0]="NUM COCHE";
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
	public static void iniciar_usuarios(String usuarios[][]) {
		//cabecera
		usuarios[0][0]="DNI";
		usuarios[0][1]="NOMBRE";
		usuarios[0][2]="APELLIDO_1";
		usuarios[0][3]="APELLIDO_2";
		usuarios[0][4]="CORREO";
		usuarios[0][5]="CLAVE";
		//usuario1
		usuarios[1][0]="05994241G";
		usuarios[1][1]="Ivan";
		usuarios[1][2]="Camps";
		usuarios[1][3]="Sanchez";
		usuarios[1][4]="icamps555@gmail.com";
		usuarios[1][5]="i4576_";
		//usuario2
		usuarios[2][0]="62459735S";
		usuarios[2][1]="María";
		usuarios[2][2]="Serrano";
		usuarios[2][3]="Castro";
		usuarios[2][4]="mserranocastro@gmail.com";
		usuarios[2][5]="m3649z";
		//usuario3
		usuarios[3][0]="75730953A";
		usuarios[3][1]="Lucia";
		usuarios[3][2]="Gonzalez";
		usuarios[3][3]="Navarro";
		usuarios[3][4]="lgonzaleznavarro@gmail.com";
		usuarios[3][5]="l8396:";
		//usuario4
		usuarios[4][0]="31295249T";
		usuarios[4][1]="Fernando";
		usuarios[4][2]="Frutos";
		usuarios[4][3]="Lorca";
		usuarios[4][4]="ffrutoslorca@gmail.com";
		usuarios[4][5]="f2748#";
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
		System.out.println("|-----------------------|");
		System.out.println("| 1.- Agregar coches    |");
		System.out.println("| 2.- Modificar coches  |");
		System.out.println("| 3.- Eliminar coches   |");
		System.out.println("| 4.- Mostrar coches    |");
		System.out.println("| 5.- Cerrar sesion     |");
		System.out.println("|-----------------------|");
		System.out.print("Introduce una opcion: ");
	}
	public static void menu_usuario_uno() {
		System.out.println("|-----------------------|");
		System.out.println("| 1.- Iniciar sesion    |");
		System.out.println("| 2.- Registrarse       |");
		System.out.println("| 3.- Salir             |");
		System.out.println("|-----------------------|");
		System.out.print("Introduce una opcion: ");
	}
	public static boolean comprobar(String dniAComprobar){
        char[] letraDni = {
            'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D',  'X',  'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'
        };  
        String num= "";
        int ind = 0;  
        boolean valido=true;
        if(dniAComprobar.length() == 8) {
             dniAComprobar = "0" + dniAComprobar;
        }
        if (!Character.isLetter(dniAComprobar.charAt(8))) {
             valido=false;  
        }
        if (dniAComprobar.length() != 9){   
        	valido=false;
        }  
        for (int i=0; i<8; i++) {
             if(!Character.isDigit(dniAComprobar.charAt(i))){
            	 valido=false;    
             }
             num += dniAComprobar.charAt(i);     
        }
        ind = Integer.parseInt(num);
        ind %= 23;
        if ((Character.toUpperCase(dniAComprobar.charAt(8))) != letraDni[ind]){
        	valido=false;
       }  
        return valido;
   	} // fin comprobar
	public static void registro(String usuarios[][], String dni, String nombre, String apellido_uno, String apellido_dos, String correo, String clave) {
		for (int i=1; i<usuarios.length; i++) {
			if (usuarios[i][0]==null) {
				usuarios[i][0]=dni;
				usuarios[i][1]=nombre;
				usuarios[i][2]=apellido_uno;
				usuarios[i][3]=apellido_dos;
				usuarios[i][4]=correo;
				usuarios[i][5]=clave;
				i=usuarios.length;
				System.out.println("Te has registrado correctamente.");
			}
		}
	}
	public static boolean comprobar_usuario(String usuarios[][], String correo, String clave, boolean usuario_correcto) {
		for (int i=1; i<usuarios.length; i++) {
			if (usuarios[i][4]!=null && usuarios[i][5]!=null) {
				if (usuarios[i][4].equals(correo) && usuarios[i][5].equals(clave)) {
					usuario_correcto=true;
					System.out.println("Has iniciado sesion correctamente.");
					i=usuarios.length;
				}
			}
		}
		return usuario_correcto;
	}
	public static void menu_usuario_dos() {
		System.out.println("|----------------------------------------------|");
		System.out.println("| 1.- Comprar coches                           |");
		System.out.println("| 2.- Vender un coche al concesionario         |");
		System.out.println("| 3.- Mostrar el listado de coches disponibles |");
		System.out.println("| 4.- Cerrar sesion                            |");
		System.out.println("|----------------------------------------------|");
		System.out.print("Introduce una opcion: ");
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
	public static int posicion_coche_usuario(String coches[][], String modelo) {
		int coche_usuario=0;
		for(int i=1; i<coches.length; i++) {
			if(coches[i][2]!=null) {
				if(coches[i][2].equals(modelo)) {
					coche_usuario=i;
					System.out.println("Modelo disponible (" + coches[i][2] + ")");
					i=coches.length;
				}
			}
		}
		return coche_usuario;
	}

}