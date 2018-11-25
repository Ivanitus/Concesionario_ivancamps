/**
 * @author Ivan Camps Sanchez
 * @version 1.0
 * @since 2018-25-11
 * Class_Name=concesionarioIvancamps
 */

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class concesionarioIvancamps {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final String ADMIN="admin";
	 	final String PASSWD_ADMIN="admin123";
		String usuarioIntroducido, passwdIntroducido, opcion, cadenaIdCoche=null, marca, modelo , precio_venta, precio_compra;
		String dni, nombre, apellido_uno, apellido_dos, correo, clave, confirmarcompra, contador_cadena=null;
		final Pattern PATTERN = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		final int FILAS_COCHES=20;
		final int COLUMNAS_COCHES=7;
		final int FILAS_USUARIOS=30;
		final int COLUMNAS_USUARIOS=6;
		final int FILAS_COCHES_COMPRADOS_Y_VENDIDOS=50;
		final int COLUMNAS_COCHES_COMPRADOS_Y_VENDIDOS=7;
		final double IVA=0.21;
		int idcoche=1, idmodificar=0, numcocheusuario=0, contador=0, precioextra=12000;
		double precioconiva;
		boolean seguir=true, admin_correcto=false, seguir_admin=true, seguir_usuario=true, usuario_correcto=false, seguir_usuario_iniciado=true, dnivalido;
		boolean usuarioiniciado=false, coche_encontrado=false;
		Scanner sc=new Scanner(System.in);
		String coches[][]=new String[FILAS_COCHES][COLUMNAS_COCHES];
		String usuarios[][]=new String[FILAS_USUARIOS][COLUMNAS_USUARIOS];
		String cochescomprados_usuarios[][]=new String[FILAS_COCHES_COMPRADOS_Y_VENDIDOS][COLUMNAS_COCHES_COMPRADOS_Y_VENDIDOS];
		String cochesvendidos_usuarios[][]=new String[FILAS_COCHES_COMPRADOS_Y_VENDIDOS][COLUMNAS_COCHES_COMPRADOS_Y_VENDIDOS];
		idcoche=iniciar_coches(coches, idcoche, cadenaIdCoche);
		iniciar_usuarios(usuarios);
		iniciar_cochescomprados_usuarios(cochescomprados_usuarios, coches, usuarios);
		iniciar_cochesvendidos_usuarios(cochesvendidos_usuarios, coches, usuarios);
		
		do {
			menu_principal();
			opcion=sc.next();
			switch (opcion) {
			case "1": 
					System.out.print("Introduce el nombre de usuario: ");
					usuarioIntroducido=sc.next();
					System.out.print("Introduce la contraseña: ");
					passwdIntroducido=sc.next();
				if (datoscorrectos(usuarioIntroducido, passwdIntroducido, ADMIN, PASSWD_ADMIN, admin_correcto)) {
					do {
					menu_admin();
					opcion=sc.next();
					sc.nextLine();
						switch(opcion) {
						case "1": 
							System.out.print("Introduzca la marca del coche: ");
							marca=sc.nextLine();
							System.out.print("");
							System.out.print("Introduzca el modelo: ");
							modelo=sc.nextLine();
							System.out.print("Introduzca el precio de venta del coche: ");
							precio_venta=sc.next();
							idcoche=agregar_modificar_coches(coches, idcoche, cadenaIdCoche, marca, modelo, precio_venta, idmodificar);
							seguir_admin=true;
							break;
						case "2": 
							System.out.print("Introduce el ID del coche que quieres modificar: ");
							idmodificar=sc.nextInt();
							idmodificar=posicioncoches(coches, idmodificar, cadenaIdCoche);
							if (idmodificar!=-1){
								mostrar_coches(coches, idmodificar, usuarioiniciado);
								cadenaIdCoche=coches[idmodificar][0];
								System.out.print("Introduce la marca del coche: ");
								marca=sc.next();
								System.out.print("Introduzca el modelo: ");
								modelo=sc.next();
								System.out.print("Introduzca el precio de venta del coche: ");
								precio_venta=sc.next();
								idcoche=agregar_modificar_coches(coches, idcoche, cadenaIdCoche, marca, modelo, precio_venta, idmodificar);
								mostrar_coches(coches, idmodificar, usuarioiniciado);
								idmodificar=0;
								
							}
							seguir_admin=true;
							break;
						case "3": 
							System.out.print("Introduce el ID del coche que quieres eliminar: ");
							idmodificar=sc.nextInt();
							idmodificar=posicioncoches(coches, idmodificar, cadenaIdCoche);
							if (idmodificar!=1) {
								mostrar_coches(coches, idmodificar, usuarioiniciado);
								cadenaIdCoche=null;
								marca=null;
								modelo=null;
								precio_venta=null;
								precio_compra=null;
								idcoche=agregar_modificar_coches(coches, idcoche, cadenaIdCoche, marca, modelo, precio_venta, idmodificar);
								idmodificar=0;
							}
							seguir_admin=true;
							break;
						case "4": 
							mostrar_coches(coches, idmodificar, usuarioiniciado);
							seguir_admin=true;
							break;
						case "5":
							mostrarusuariosregistrados(usuarios);
							seguir_admin=true;
							break;
						case "6":
							mostrar_cochescomprados_admin(cochescomprados_usuarios);
							seguir_admin=true;
							break;
						case "7":
							mostrar_cochesvendidos_admin(cochesvendidos_usuarios);
							seguir_admin=true;
							break;
						case "8": 
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
								usuarioiniciado=true;
								menu_usuario_dos();
								opcion=sc.next();
								sc.nextLine();
								switch(opcion) {
								case "1":
									System.out.print("Introduce el modelo del coche que desea comprar: ");
									modelo=sc.nextLine();
									numcocheusuario=posicion_coche_usuario(coches, modelo);
									coche_encontrado=coche_existe(coches, numcocheusuario, modelo, coche_encontrado);
									if (coche_encontrado) {
										precioconiva=calcularprecio(coches, numcocheusuario, IVA);
										System.out.print("¿Confirmar compra?: ");
										confirmarcompra=sc.next();
										confirmaciondecompra(coches, confirmarcompra, numcocheusuario, contador, correo, modelo, contador_cadena, usuarios, cochescomprados_usuarios);
									}
									else if(!coche_encontrado){
										System.out.println("Coche no encontrado");
									}
									seguir_usuario_iniciado=true;
									break;
								case "2":
									System.out.print("Introduzca la marca del coche: ");
									marca=sc.nextLine();
									System.out.print("");
									System.out.print("Introduzca el modelo: ");
									modelo=sc.nextLine();
									System.out.print("Introduzca el precio de venta del coche al concesionario (en caso de ya existir el modelo, introducir 0): ");
									precio_compra=sc.next();
									idcoche=vendercoches_clientes_concesionario(coches, marca, modelo, precio_compra, cadenaIdCoche, idcoche, precioextra, contador, contador_cadena);
									registrocochesvendidosusuarios(coches, usuarios, cochesvendidos_usuarios, marca, modelo, correo);
									seguir_usuario_iniciado=true;
									break;
								case "3":
									mostrar_coches(coches, idmodificar, usuarioiniciado);
									seguir_usuario_iniciado=true;
									break;
								case "4":
									mostrar_cochescomprados_usuarios(cochescomprados_usuarios, usuarios, correo);
									seguir_usuario_iniciado=true;
									break;
								case "5":
									mostrar_cochesvendidos_usuarios(cochesvendidos_usuarios, usuarios, correo);
									seguir_usuario_iniciado=true;
									break;
								case "6":
									usuarioiniciado=false;
									seguir_usuario_iniciado=false;
									seguir_usuario=false;
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
							System.out.print("Introduce tu correo (nombredeusuario@nombrededominio.extension): ");
							correo=sc.next();
							Matcher mather=PATTERN.matcher(correo);
							System.out.print("Introduce tu contraseña: ");
							clave=sc.next();
							registro(usuarios, dni, nombre, apellido_uno, apellido_dos, correo, clave, PATTERN, mather);
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
					System.out.println("Gracias por usar el programa!");
					seguir=false;
					break;
				default:
					System.out.println("Opcion erronea");
					seguir=true;
				}
		}while(seguir);
	}
	
	public static int iniciar_coches(String coches[][], int idcoche, String cadenaIdCoche) {
		//cabecera
		coches[0][0]="NUM COCHE";
		coches[0][1]="MARCA";
		coches[0][2]="MODELO";
		coches[0][3]="PRECIO VENTA";
		coches[0][4]="PRECIO COMPRA";
		coches[0][5]="COCHES VENDIDOS";
		coches[0][6]="COCHES COMPRADOS";
		//modelo 1
		cadenaIdCoche=Integer.toString(idcoche);
		coches[1][0]=cadenaIdCoche;
		coches[1][1]="Ferrari";
		coches[1][2]="488 Spider";
		coches[1][3]="264275";
		coches[1][4]="-";
		coches[1][5]="0";
		coches[1][6]="0";
		idcoche=Integer.parseInt(cadenaIdCoche);
		idcoche++;
		//modelo 2
		cadenaIdCoche=Integer.toString(idcoche);
		coches[2][0]=cadenaIdCoche;
		coches[2][1]="Lamborghini";
		coches[2][2]="Huracan Performante";
		coches[2][3]="298087";
		coches[2][4]="-";
		coches[2][5]="0";
		coches[2][6]="0";
		idcoche=Integer.parseInt(cadenaIdCoche);
		idcoche++;
		//modelo 3
		cadenaIdCoche=Integer.toString(idcoche);
		coches[3][0]=cadenaIdCoche;
		coches[3][1]="Porsche";
		coches[3][2]="911 GTS";
		coches[3][3]="145029";
		coches[3][4]="-";
		coches[3][5]="0";
		coches[3][6]="0";
		idcoche=Integer.parseInt(cadenaIdCoche);
		idcoche++;
		//modelo 4
		cadenaIdCoche=Integer.toString(idcoche);
		coches[4][0]=cadenaIdCoche;
		coches[4][1]="Aston Martin";
		coches[4][2]="New Vantage";
		coches[4][3]="149995";
		coches[4][4]="-";
		coches[4][5]="0";
		coches[4][6]="0";
		idcoche=Integer.parseInt(cadenaIdCoche);
		idcoche++;
		//modelo 5
		cadenaIdCoche=Integer.toString(idcoche);
		coches[5][0]=cadenaIdCoche;
		coches[5][1]="Tesla";
		coches[5][2]="Roadster";
		coches[5][3]="212000";
		coches[5][4]="-";
		coches[5][5]="0";
		coches[5][6]="0";
		idcoche=Integer.parseInt(cadenaIdCoche);
		idcoche++;
		return idcoche;
	}
	
	public static void iniciar_usuarios(String usuarios[][]) {
		//cabecera
		usuarios[0][0]="DNI";
		usuarios[0][1]="NOMBRE";
		usuarios[0][2]="APELLIDO 1";
		usuarios[0][3]="APELLIDO 2";
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
	
	public static void iniciar_cochescomprados_usuarios(String cochescomprados_usuarios[][], String coches[][], String usuarios[][]) {
		//cabecera
		cochescomprados_usuarios[0][0]="DNI";
		cochescomprados_usuarios[0][1]="NOMBRE";
		cochescomprados_usuarios[0][2]="APELLIDO 1";
		cochescomprados_usuarios[0][3]="APELLIDO 2";
		cochescomprados_usuarios[0][4]="CORREO";
		cochescomprados_usuarios[0][5]="MARCA";
		cochescomprados_usuarios[0][6]="MODELO";
		//compra 1
		cochescomprados_usuarios[1][0]=usuarios[1][0];
		cochescomprados_usuarios[1][1]=usuarios[1][1];
		cochescomprados_usuarios[1][2]=usuarios[1][2];
		cochescomprados_usuarios[1][3]=usuarios[1][3];
		cochescomprados_usuarios[1][4]=usuarios[1][4];
		cochescomprados_usuarios[1][5]=coches[5][1];
		cochescomprados_usuarios[1][6]=coches[5][2];
		//compra 2
		cochescomprados_usuarios[2][0]=usuarios[4][0];
		cochescomprados_usuarios[2][1]=usuarios[4][1];
		cochescomprados_usuarios[2][2]=usuarios[4][2];
		cochescomprados_usuarios[2][3]=usuarios[4][3];
		cochescomprados_usuarios[2][4]=usuarios[4][4];
		cochescomprados_usuarios[2][5]=coches[2][1];
		cochescomprados_usuarios[2][6]=coches[2][2];
		//compra 3
		cochescomprados_usuarios[3][0]=usuarios[2][0];
		cochescomprados_usuarios[3][1]=usuarios[2][1];
		cochescomprados_usuarios[3][2]=usuarios[2][2];
		cochescomprados_usuarios[3][3]=usuarios[2][3];
		cochescomprados_usuarios[3][4]=usuarios[2][4];
		cochescomprados_usuarios[3][5]=coches[3][1];
		cochescomprados_usuarios[3][6]=coches[3][2];
	}
	
	public static void iniciar_cochesvendidos_usuarios(String cochesvendidos_usuarios[][], String coches[][], String usuarios[][]) {
		//cabecera
		cochesvendidos_usuarios[0][0]="DNI";
		cochesvendidos_usuarios[0][1]="NOMBRE";
		cochesvendidos_usuarios[0][2]="APELLIDO 1";
		cochesvendidos_usuarios[0][3]="APELLIDO 2";
		cochesvendidos_usuarios[0][4]="CORREO";
		cochesvendidos_usuarios[0][5]="MARCA";
		cochesvendidos_usuarios[0][6]="MODELO";
		//compra 1
		cochesvendidos_usuarios[1][0]=usuarios[1][0];
		cochesvendidos_usuarios[1][1]=usuarios[1][1];
		cochesvendidos_usuarios[1][2]=usuarios[1][2];
		cochesvendidos_usuarios[1][3]=usuarios[1][3];
		cochesvendidos_usuarios[1][4]=usuarios[1][4];
		cochesvendidos_usuarios[1][5]=coches[2][1];
		cochesvendidos_usuarios[1][6]=coches[2][2];
		//compra 2
		cochesvendidos_usuarios[2][0]=usuarios[4][0];
		cochesvendidos_usuarios[2][1]=usuarios[4][1];
		cochesvendidos_usuarios[2][2]=usuarios[4][2];
		cochesvendidos_usuarios[2][3]=usuarios[4][3];
		cochesvendidos_usuarios[2][4]=usuarios[4][4];
		cochesvendidos_usuarios[2][5]=coches[4][1];
		cochesvendidos_usuarios[2][6]=coches[4][2];
		//compra 3
		cochesvendidos_usuarios[3][0]=usuarios[2][0];
		cochesvendidos_usuarios[3][1]=usuarios[2][1];
		cochesvendidos_usuarios[3][2]=usuarios[2][2];
		cochesvendidos_usuarios[3][3]=usuarios[2][3];
		cochesvendidos_usuarios[3][4]=usuarios[2][4];
		cochesvendidos_usuarios[3][5]=coches[1][1];
		cochesvendidos_usuarios[3][6]=coches[1][2];
	}
	
	public static void menu_principal() {
		System.out.println("Bienvenido al concesionario de supercoches Camps' Supercars");
		System.out.println("|-------------------------------|");
		System.out.println("| 1.- Entrar como administrador |");
		System.out.println("| 2.- Entrar como usuario       |");
		System.out.println("| 3.- Salir del programa        |");
		System.out.println("|-------------------------------|");
		System.out.print("Introduce una opcion: ");
	}
	
	public static boolean datoscorrectos(String usuarioIntroducido, String passwdIntroducido, String admin, String passwd_admin, boolean admin_correcto) {
		if(usuarioIntroducido.equals(admin) && passwdIntroducido.equals(passwd_admin)) {
			admin_correcto=true;
		}
		else {
			admin_correcto=false;
		}
		return admin_correcto;	
	}
	
	public static void menu_admin() {
		System.out.println("|-----------------------------------------------|");
		System.out.println("| 1.- Agregar coches                            |");
		System.out.println("| 2.- Modificar coches                          |");
		System.out.println("| 3.- Eliminar coches                           |");
		System.out.println("| 4.- Mostrar coches                            |");
		System.out.println("| 5.- Mostrar usuarios registrados              |");
		System.out.println("| 6.- Mostrar coches comprados por los usuarios |");
		System.out.println("| 7.- Mostrar usuarios que han vendido coches   |");
		System.out.println("| 8.- Cerrar sesion                             |");
		System.out.println("|-----------------------------------------------|");
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
	
	public static void mostrarusuariosregistrados(String usuarios[][]) {
		for (int i=0; i<usuarios.length; i++) {
			if (usuarios[i][0]!=null) {
				System.out.print(usuarios[i][0] + " | ");
				System.out.print(usuarios[i][1] + " | ");
				System.out.print(usuarios[i][2] + " | ");
				System.out.print(usuarios[i][3] + " | ");
				System.out.println(usuarios[i][4] + " | ");
			}
		}
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
	
	public static void registro(String usuarios[][], String dni, String nombre, String apellido_uno, String apellido_dos, String correo, String clave, Pattern pattern, Matcher mather) {
		for (int i=1; i<usuarios.length; i++) {
			if (usuarios[i][0]==null && !usuarios[i][0].equalsIgnoreCase(dni) && !usuarios[i][4].equals(correo) && mather.find()==true) {
				usuarios[i][0]=dni;
				usuarios[i][1]=nombre;
				usuarios[i][2]=apellido_uno;
				usuarios[i][3]=apellido_dos;
				usuarios[i][4]=correo;
				usuarios[i][5]=clave;
				i=usuarios.length;
				System.out.println("Te has registrado correctamente.");
			}
			else if (mather.find()==false) {
				System.out.println("El e-mail introducido es incorrecto. Debe contener el formato nombredeusuario@nombrededominio.extension");
				i=usuarios.length;
			}
			else {
				System.out.println("El usuario ya existe");
				i=usuarios.length;
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
		System.out.println("| 4.- Mostrar los coches que has comprado      |");
		System.out.println("| 5.- Mostrar los coches que has vendido       |");
		System.out.println("| 6.- Cerrar sesion                            |");
		System.out.println("|----------------------------------------------|");
		System.out.print("Introduce una opcion: ");
	}
	
	public static int agregar_modificar_coches(String coches[][], int idcoche, String cadenaIdCoche, String marca, String modelo, String precio_venta, int idmodificar) {
		if (idmodificar!=0){	
			coches[idmodificar][0]=cadenaIdCoche;
			coches[idmodificar][1]=marca;
			coches[idmodificar][2]=modelo;
			coches[idmodificar][3]=precio_venta;
			coches[idmodificar][4]="-";
			System.out.println("Se han realizado los cambios correctamente");
		}
		else {
			for(int i=1; i<coches.length; i++) {
				if(coches[i][0]==null) {
					cadenaIdCoche=Integer.toString(idcoche);
					coches[i][0]=cadenaIdCoche;
					idcoche=Integer.parseInt(cadenaIdCoche);
					idcoche++;
					coches[i][1]=marca;
					coches[i][2]=modelo;
					coches[i][3]=precio_venta;
					coches[i][4]="-";
					coches[i][5]="0";
					coches[i][6]="0";
					i=coches.length;
				}
			}
		}
		return idcoche;
	}
	
	public static void mostrar_coches(String coches[][], int idmodificar, boolean usuarioiniciado) {
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
		else if(usuarioiniciado) {
			for (int i=0; i<coches.length; i++) {
				if (coches[i][0]!=null) {
					System.out.print(coches[i][1] + " | ");
					System.out.print(coches[i][2] + " | ");
					System.out.print(coches[i][3] + " | ");
					System.out.println(coches[i][4] + " | ");
				}
			}
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
	
	public static int posicioncoches(String coches[][], int idmodificar, String cadenaIdCoche) {
		int posicion=-1;
		cadenaIdCoche=Integer.toString(idmodificar);
		for (int i=1; i<coches.length; i++) {
			if (coches[i][0]!=null) {
				if(coches[i][0].equals(cadenaIdCoche)) {
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
				if(coches[i][2].equalsIgnoreCase(modelo)) {
					coche_usuario=i;
					System.out.println("Modelo disponible (" + coches[i][2] + ")");
					i=coches.length;
				}
			}
		}
		return coche_usuario;
	}
	
	public static boolean coche_existe(String coches[][], int numcocheusuario, String modelo, boolean coche_encontrado) {
		if (coches[numcocheusuario][2].equalsIgnoreCase(modelo)) {
			coche_encontrado=true;
		}
		else {
			coche_encontrado=false;
		}
		return coche_encontrado;
	}
	
	public static double calcularprecio(String coches[][], int numcocheusuario, double IVA) {
		double preciofinal;
		double precio=Double.valueOf(coches[numcocheusuario][3]);
		preciofinal=precio*IVA+precio;
		System.out.println("El precio del coche + IVA (21%) es: " + preciofinal + " euros");
		return preciofinal;
	}
	
	public static void confirmaciondecompra(String coches[][], String confirmarcompra, int numcocheusuario, int contador, String correo, String modelo, String contador_cadena, String usuarios[][], String cochescomprados_usuarios[][]) {
		if (confirmarcompra.equalsIgnoreCase("si")) {
			System.out.println("Compra confirmada");
			contador=Integer.parseInt(coches[numcocheusuario][5]);
			contador++;
			contador_cadena=Integer.toString(contador);
			coches[numcocheusuario][5]=contador_cadena;
			for (int i=0; i<cochescomprados_usuarios.length; i++) {
				if (cochescomprados_usuarios[i][0]==null) {
					for (int j=1; j<usuarios.length; j++) {
						if (usuarios[j][4].equals(correo)) {
							cochescomprados_usuarios[i][0]=usuarios[j][0];
							cochescomprados_usuarios[i][1]=usuarios[j][1];
							cochescomprados_usuarios[i][2]=usuarios[j][2];
							cochescomprados_usuarios[i][3]=usuarios[j][3];
							cochescomprados_usuarios[i][4]=usuarios[j][4];
							j=usuarios.length;
						}
					}
					for (int k=1; k<coches.length; k++) {
						if(coches[k][2].equalsIgnoreCase(modelo)) {
							cochescomprados_usuarios[i][5]=coches[k][1];
							cochescomprados_usuarios[i][6]=coches[k][2];
							k=coches.length;
						}
					}
					i=cochescomprados_usuarios.length;	
				}
			}
		}
		else {
			System.out.println("Compra cancelada");
		}
	}
	
	public static void mostrar_cochescomprados_admin(String cochescomprados_usuarios[][]) {
		for (int i=0; i<cochescomprados_usuarios.length; i++) {
			if (cochescomprados_usuarios[i][0]!=null) {
				System.out.print(cochescomprados_usuarios[i][0] + " | ");
				System.out.print(cochescomprados_usuarios[i][1] + " | ");
				System.out.print(cochescomprados_usuarios[i][2] + " | ");
				System.out.print(cochescomprados_usuarios[i][3] + " | ");
				System.out.print(cochescomprados_usuarios[i][4] + " | ");
				System.out.print(cochescomprados_usuarios[i][5] + " | ");
				System.out.println(cochescomprados_usuarios[i][6] + " | ");
			}
		}
	}
	
	public static void mostrar_cochescomprados_usuarios(String cochescomprados_usuarios[][], String usuarios[][], String correo) {
		for (int i=0; i<cochescomprados_usuarios.length; i++) {
			if (cochescomprados_usuarios[i][0]!=null) {
				for (int j=1; j<usuarios.length; j++) {
					if (cochescomprados_usuarios[i][4].equals(correo)) {
						System.out.print(cochescomprados_usuarios[i][0] + " | ");
						System.out.print(cochescomprados_usuarios[i][1] + " | ");
						System.out.print(cochescomprados_usuarios[i][2] + " | ");
						System.out.print(cochescomprados_usuarios[i][3] + " | ");
						System.out.print(cochescomprados_usuarios[i][4] + " | ");
						System.out.print(cochescomprados_usuarios[i][5] + " | ");
						System.out.println(cochescomprados_usuarios[i][6] + " | ");
						j=usuarios.length;
					}
				}
			}
		}
	}
	
	public static int vendercoches_clientes_concesionario (String coches[][], String marca, String modelo, String precio_compra, String cadenaIdCoche, int idcoche, int precioextra, int contador, String contador_cadena) {
		int precio;
		for (int i=1; i<coches.length; i++) {
				if (!modelo.equalsIgnoreCase(coches[i][2])) {
					if (coches[i][0]==null) {
						cadenaIdCoche=Integer.toString(idcoche);
						coches[i][0]=cadenaIdCoche;
						idcoche=Integer.parseInt(cadenaIdCoche);
						idcoche++;
						coches[i][1]=marca;
						coches[i][2]=modelo;
						coches[i][4]=precio_compra;
						precio=Integer.valueOf(coches[i][4]);
						precioextra+=precio;
						coches[i][3]=String.valueOf(precioextra);
						coches[i][5]="0";
						coches[i][6]="1";
						i=coches.length;
					}
				}
				else {
					contador=Integer.parseInt(coches[i][6]);
					contador++;
					contador_cadena=Integer.toString(contador);
					coches[i][6]=contador_cadena;
					i=coches.length;
				}
			}
		return idcoche;
	}
	
	public static void registrocochesvendidosusuarios(String coches[][], String usuarios[][], String cochesvendidos_usuarios[][], String marca, String modelo, String correo) {
		for (int i=0; i<cochesvendidos_usuarios.length; i++) {
			if (cochesvendidos_usuarios[i][0]==null) {
				for (int j=1; j<usuarios.length; j++) {
					if (usuarios[j][4].equals(correo)) {
						cochesvendidos_usuarios[i][0]=usuarios[j][0];
						cochesvendidos_usuarios[i][1]=usuarios[j][1];
						cochesvendidos_usuarios[i][2]=usuarios[j][2];
						cochesvendidos_usuarios[i][3]=usuarios[j][3];
						cochesvendidos_usuarios[i][4]=usuarios[j][4];
						j=usuarios.length;
					}
				}
				for (int k=1; k<coches.length; k++) {
					if(coches[k][2].equalsIgnoreCase(modelo)) {
						cochesvendidos_usuarios[i][5]=coches[k][1];
						cochesvendidos_usuarios[i][6]=coches[k][2];
						k=coches.length;
					}
				}
				i=cochesvendidos_usuarios.length;	
			}
		}
	}
	
	public static void mostrar_cochesvendidos_admin(String cochesvendidos_usuarios[][]) {
		for (int i=0; i<cochesvendidos_usuarios.length; i++) {
			if (cochesvendidos_usuarios[i][0]!=null) {
				System.out.print(cochesvendidos_usuarios[i][0] + " | ");
				System.out.print(cochesvendidos_usuarios[i][1] + " | ");
				System.out.print(cochesvendidos_usuarios[i][2] + " | ");
				System.out.print(cochesvendidos_usuarios[i][3] + " | ");
				System.out.print(cochesvendidos_usuarios[i][4] + " | ");
				System.out.print(cochesvendidos_usuarios[i][5] + " | ");
				System.out.println(cochesvendidos_usuarios[i][6] + " | ");
			}
		}
	}
	
	public static void mostrar_cochesvendidos_usuarios(String cochesvendidos_usuarios[][], String usuarios[][], String correo) {
		for (int i=0; i<cochesvendidos_usuarios.length; i++) {
			if (cochesvendidos_usuarios[i][0]!=null) {
				for (int j=1; j<usuarios.length; j++) {
					if (cochesvendidos_usuarios[i][4].equals(correo)) {
						System.out.print(cochesvendidos_usuarios[i][0] + " | ");
						System.out.print(cochesvendidos_usuarios[i][1] + " | ");
						System.out.print(cochesvendidos_usuarios[i][2] + " | ");
						System.out.print(cochesvendidos_usuarios[i][3] + " | ");
						System.out.print(cochesvendidos_usuarios[i][4] + " | ");
						System.out.print(cochesvendidos_usuarios[i][5] + " | ");
						System.out.println(cochesvendidos_usuarios[i][6] + " | ");
						j=usuarios.length;
					}
				}
			}
		}
	}
	
}