/**
 * @author Ivan Camps Sanchez
 * @version 1.0.1
 * @since 2018-26-11
 * Nombre de la clase: concesionarioIvancamps
 */

import java.util.Scanner;

public class concesionarioIvancamps {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final String ADMIN="admin"; //usuario del administrador
	 	final String PASSWD_ADMIN="admin123"; //password del administrador
		String usuarioIntroducido, passwdIntroducido, opcion, cadenaIdCoche=null, marca, modelo , precioVenta, precioCompra;
		String dni, nombre, apellidoUno, apellidoDos, correo, clave, confirmarCompra, contadorCadena=null;
		final int FILAS_COCHES=20; //declaracion e inicializacion de constante que define las filas del array coches
		final int COLUMNAS_COCHES=8; //declaracion e inicializacion de constante que define las columnas del array coches
		final int FILAS_USUARIOS=30; //declaracion e inicializacion de constante que define las filas del array usuarios
		final int COLUMNAS_USUARIOS=6; //declaracion e inicializacion de constante que define las columnas del array usuarios
		final int FILAS_COCHES_COMPRADOS_Y_VENDIDOS=50; //declaracion e inicializacion de constante que define las filas de los arrays cochescompradosusuarios y cochesvendidosusuarios
		final int COLUMNAS_COCHES_COMPRADOS_Y_VENDIDOS=7; //declaracion e inicializacion de constante que define las columnas de los arrays cochescompradosusuarios y cochesvendidosusuarios
		final double IVA=0.21; //declaracion e inicializacion de la constante que define el IVA
		int idCoche=1, idModificar=0, numCocheUsuario=0, contador=0, precioExtra=6000;
		double precioConIVA;
		boolean seguir=true, adminCorrecto=false, seguirAdmin=true, seguirUsuario=true, usuarioCorrecto=false, seguirUsuarioIniciado=true, dniValido;
		boolean usuarioIniciado=false, cocheEncontrado=false, dniNoRepetido=true;
		Scanner sc=new Scanner(System.in); //creacion del objeto Scanner
		String coches[][]=new String[FILAS_COCHES][COLUMNAS_COCHES]; //array para guardar los coches
		String usuarios[][]=new String[FILAS_USUARIOS][COLUMNAS_USUARIOS]; //array para guardar la informacion de los usuarios
		String cochesCompradosUsuarios[][]=new String[FILAS_COCHES_COMPRADOS_Y_VENDIDOS][COLUMNAS_COCHES_COMPRADOS_Y_VENDIDOS]; //array para guardar los coches que compra cada usuario
		String cochesVendidosUsuarios[][]=new String[FILAS_COCHES_COMPRADOS_Y_VENDIDOS][COLUMNAS_COCHES_COMPRADOS_Y_VENDIDOS]; //array para guardar los coches que vende cada usuario
		idCoche=iniciarCoches(coches, idCoche, cadenaIdCoche); //llamada al metodo iniciarCoches
		iniciarUsuarios(usuarios); //llamada al metodo iniciarUsuarios
		iniciarCochesCompradosUsuarios(cochesCompradosUsuarios, coches, usuarios); //llamada al metodo iniciarCochesCompradosUsuarios
		iniciarCochesVendidosUsuarios(cochesVendidosUsuarios, coches, usuarios); //llamada al metodo iniciarCochesVendidosUsuarios
		
		do { 
			mostrarMenuPrincipal(); //llamada al metodo mostrarMenuPrincipal
			opcion=sc.next(); //peticion de opcion
			/*
			 * Cada sentencia tipo System.out.print("") vacia
			 * estan escritas para evitar problemas de espacios en blanco 
			 * a la hora de usar sc.nextLine() y sc.next()
			 */
			switch (opcion) { 
			case "1": //opcion entrar como administrador
					System.out.print("Introduce el nombre de usuario: ");
					usuarioIntroducido=sc.next();
					System.out.print("Introduce la contraseña: ");
					passwdIntroducido=sc.next();
				if (adminCorrecto=comprobarDatosCorrectosAdmin(usuarioIntroducido, passwdIntroducido, ADMIN, PASSWD_ADMIN, adminCorrecto)) { //condicional y metodo para comprobar creedenciales del admin introducidas sean correctas
					do {
					mostrarMenuAdmin(); //llamada al metodo mostrarMenuAdmin
					opcion=sc.next();
					sc.nextLine();
						switch(opcion) {
						case "1": //opcion agregar coches
							System.out.print("Introduzca la marca del coche: ");
							marca=sc.nextLine(); //introduccion de marca del coche
							System.out.print("");
							System.out.print("Introduzca el modelo: ");
							modelo=sc.nextLine(); //introduccion de modelo del coche
							System.out.print("");
							System.out.print("Introduzca el precio de venta del coche: ");
							precioVenta=sc.next(); //introduccion precio de venta del coche
							idCoche=agregarOmodificarCoches(coches, idCoche, cadenaIdCoche, marca, modelo, precioVenta, idModificar); //llamada al metodo que agrega datos o modifica datos del array de los coches
							seguirAdmin=true; //booleano que indica que no se va a salir del bucle que ejecuta el administrador
							break;
						case "2": //opcion modificar coches existentes
							System.out.print("Introduce el ID del coche que quieres modificar: ");
							idModificar=sc.nextInt(); //introduccion del id del cual se van a modificar los datos
							idModificar=encontrarPosicionCoches(coches, idModificar, cadenaIdCoche); //llamada al metoodo que agrega datos o modifica datos del array de los coches
							if (idModificar!=-1){
								mostrarListadoCoches(coches, idModificar, usuarioIniciado);
								cadenaIdCoche=coches[idModificar][0];
								System.out.print("Introduce la marca del coche: ");
								marca=sc.next();
								System.out.print("Introduzca el modelo: ");
								modelo=sc.next();
								System.out.print("Introduzca el precio de venta del coche: ");
								precioVenta=sc.next();
								idCoche=agregarOmodificarCoches(coches, idCoche, cadenaIdCoche, marca, modelo, precioVenta, idModificar);
								mostrarListadoCoches(coches, idModificar, usuarioIniciado);
								idModificar=0;
							}
							else {
								System.out.println("ID no encontrada");
							}
							seguirAdmin=true;
							break;
						case "3": //opcion eliminar coches existentes
							System.out.print("Introduce el ID del coche que quieres eliminar: ");
							idModificar=sc.nextInt();
							idModificar=encontrarPosicionCoches(coches, idModificar, cadenaIdCoche);
							if (idModificar!=1) {
								mostrarListadoCoches(coches, idModificar, usuarioIniciado);
								cadenaIdCoche=null;
								marca=null;
								modelo=null;
								precioVenta=null;
								precioCompra=null;
								idCoche=agregarOmodificarCoches(coches, idCoche, cadenaIdCoche, marca, modelo, precioVenta, idModificar);
								idModificar=0;
							}
							else {
								System.out.println("ID no encontrada");
							}
							seguirAdmin=true;
							break;
						case "4": //opcion mostrar listado de coches
							mostrarListadoCoches(coches, idModificar, usuarioIniciado);
							seguirAdmin=true;
							break;
						case "5": //opcion mostrar usuarios registrados
							mostrarUsuariosRegistrados(usuarios);
							seguirAdmin=true;
							break;
						case "6": //opcion mostrar coches que han sido comprados por clientes
							mostrarCochesCompradosAdmin(cochesCompradosUsuarios);
							seguirAdmin=true;
							break;
						case "7": //opcion mostrar coches que han sido vendidos por clientes
							mostrarCochesVendidosAdmin(cochesVendidosUsuarios);
							seguirAdmin=true;
							break;
						case "8": //opcion para cerrar sesion del administrador
								System.out.println("Has cerrado sesion de la cuenta de administrador");
								seguirAdmin=false;
							break;
						default: //opcion por defecto que se activara en caso de que la opcion introducida no exista
							System.out.println("Opcion erronea");
							seguirAdmin=true;
						}
					}while(seguirAdmin);
				}
				else { //en caso de que las credenciales del administrador no coincidan
					System.out.println("Nombre de usuario o contraseña incorrectos");
					seguir=true;
				}
				break;
			case "2": //opcion entrar como usuario
				do {
					mostrarMenuUsuarioUno(); //llamada al metodo mostrarMenuUsuarioUno
					opcion=sc.next();
					switch(opcion) {
					case "1": //opcion para iniciar sesion
						System.out.print("Introduce tu e-mail: ");
						correo=sc.next();
						System.out.print("Introduce tu contraseña de acceso: ");
						clave=sc.next();
						usuarioCorrecto=comprobarUsuarioExiste(usuarios, correo, clave, usuarioCorrecto);
						if (usuarioCorrecto) {
							do {
								usuarioIniciado=true;
								mostrarMenuUsuarioDos();
								opcion=sc.next();
								sc.nextLine();
								switch(opcion) {
								case "1":
									System.out.print("Introduce el modelo del coche que desea comprar: ");
									modelo=sc.nextLine();
									numCocheUsuario=encontrarPosicionCocheUsuario(coches, modelo);
									cocheEncontrado=comprobarCocheExiste(coches, numCocheUsuario, modelo, cocheEncontrado);
									if (cocheEncontrado) {
										precioConIVA=calcularPrecio(coches, numCocheUsuario, IVA);
										System.out.print("¿Confirmar compra?: ");
										confirmarCompra=sc.next();
										confirmarCompra(coches, confirmarCompra, numCocheUsuario, contador, correo, modelo, contadorCadena, usuarios, cochesCompradosUsuarios);
									}
									else{
										System.out.println("Coche no encontrado");
									}
									seguirUsuarioIniciado=true;
									break;
								case "2":
									System.out.print("Introduzca la marca del coche: ");
									marca=sc.nextLine();
									System.out.print("");
									System.out.print("Introduzca el modelo: ");
									modelo=sc.nextLine();
									System.out.print("Introduzca el precio de venta del coche al concesionario: ");
									precioCompra=sc.next();
									idCoche=venderCochesAConcesionario(coches, marca, modelo, precioCompra, cadenaIdCoche, idCoche, precioExtra, contador, contadorCadena);
									guardarInfoCochesVendidosUsuarios(coches, usuarios, cochesVendidosUsuarios, marca, modelo, correo);
									seguirUsuarioIniciado=true;
									break;
								case "3":
									mostrarListadoCoches(coches, idModificar, usuarioIniciado);
									seguirUsuarioIniciado=true;
									break;
								case "4":
									mostrarCochesCompradosUsuarios(cochesCompradosUsuarios, usuarios, correo);
									seguirUsuarioIniciado=true;
									break;
								case "5":
									mostrarCochesVendidosUsuarios(cochesVendidosUsuarios, usuarios, correo);
									seguirUsuarioIniciado=true;
									break;
								case "6":
									System.out.println("Sesion cerrada");
									usuarioIniciado=false;
									seguirUsuarioIniciado=false;
									seguirUsuario=false;
									break;
								default:
									System.out.println("Opcion erronea");
									seguirUsuarioIniciado=true;
									break;
								}
							}while(seguirUsuarioIniciado);
						}
						else {
							System.out.println("Usuario y/o contraseña incorrectos.");
							seguirUsuario=true;
						}
						break;
					case "2": //opcion para registrarse
						System.out.print("Introduce tu DNI: ");
						dni=sc.next();
						sc.nextLine();
						dniValido=comprobarDni(dni);
						dniNoRepetido=comprobarDniRepetido(usuarios, dni, dniNoRepetido);
						if (dniValido){
							System.out.println("El DNI introducido es valido");
							System.out.println("");
							if (dniNoRepetido) {
								System.out.print("Introduce tu nombre: ");
								nombre=sc.nextLine();
								System.out.print("Introduce tu primer apellido: ");
								apellidoUno=sc.next();
								System.out.print("Introduce tu segundo apellido: ");
								apellidoDos=sc.next();
								System.out.print("Introduce tu correo (nombredeusuario@nombrededominio.extension): ");
								correo=sc.next();
								System.out.print("Introduce tu contraseña: ");
								clave=sc.next();
								realizarRegistroUsuarios(usuarios, dni, nombre, apellidoUno, apellidoDos, correo, clave);
							}
							else {
								System.out.println("El DNI introducido ya esta registrado");
							}
						}
						else {
							System.out.println("El DNI introducido NO es valido");
						}
						seguirUsuario=true;
						break;
					case "3":
						seguirUsuario=false;
						break;
					default:
						System.out.println("Opcion erronea");
						seguirUsuario=true;
					}
					}while(seguirUsuario);
					break;
				case "3": //opcion para salir de la vista de usuario y volver al menu principal
					System.out.println("Gracias por usar el programa!");
					seguir=false;
					break;
				default: //en caso de que la opcion introducida sea erronea
					System.out.println("Opcion erronea");
					seguir=true;
				}
		}while(seguir);
	sc.close();
	}
	
	public static int iniciarCoches(String coches[][], int idCoche, String cadenaIdCoche) {
		//cabecera
		coches[0][0]="ID COCHE";
		coches[0][1]="MARCA";
		coches[0][2]="MODELO";
		coches[0][3]="PRECIO VENTA";
		coches[0][4]="PRECIO COMPRA";
		coches[0][5]="COCHES VENDIDOS";
		coches[0][6]="COCHES COMPRADOS";
		coches[0][7]="USADO";
		//modelo 1
		cadenaIdCoche=Integer.toString(idCoche);
		coches[1][0]=cadenaIdCoche;
		coches[1][1]="Ferrari";
		coches[1][2]="488 Spider";
		coches[1][3]="264275";
		coches[1][4]="-";
		coches[1][5]="0";
		coches[1][6]="0";
		coches[1][7]="-";
		idCoche=Integer.parseInt(cadenaIdCoche);
		idCoche++;
		//modelo 2
		cadenaIdCoche=Integer.toString(idCoche);
		coches[2][0]=cadenaIdCoche;
		coches[2][1]="Lamborghini";
		coches[2][2]="Huracan Performante";
		coches[2][3]="298087";
		coches[2][4]="-";
		coches[2][5]="0";
		coches[2][6]="0";
		coches[2][7]="-";
		idCoche=Integer.parseInt(cadenaIdCoche);
		idCoche++;
		//modelo 3
		cadenaIdCoche=Integer.toString(idCoche);
		coches[3][0]=cadenaIdCoche;
		coches[3][1]="Porsche";
		coches[3][2]="911 GTS";
		coches[3][3]="145029";
		coches[3][4]="-";
		coches[3][5]="0";
		coches[3][6]="0";
		coches[3][7]="-";
		idCoche=Integer.parseInt(cadenaIdCoche);
		idCoche++;
		//modelo 4
		cadenaIdCoche=Integer.toString(idCoche);
		coches[4][0]=cadenaIdCoche;
		coches[4][1]="Aston Martin";
		coches[4][2]="New Vantage";
		coches[4][3]="149995";
		coches[4][4]="-";
		coches[4][5]="0";
		coches[4][6]="0";
		coches[4][7]="-";
		idCoche=Integer.parseInt(cadenaIdCoche);
		idCoche++;
		//modelo 5
		cadenaIdCoche=Integer.toString(idCoche);
		coches[5][0]=cadenaIdCoche;
		coches[5][1]="Tesla";
		coches[5][2]="Roadster";
		coches[5][3]="212000";
		coches[5][4]="-";
		coches[5][5]="0";
		coches[5][6]="0";
		coches[5][7]="-";
		idCoche=Integer.parseInt(cadenaIdCoche);
		idCoche++;
		return idCoche;
	}
	
	public static void iniciarUsuarios(String usuarios[][]) {
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
		usuarios[2][1]="Maria";
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
	
	public static void iniciarCochesCompradosUsuarios(String cochesCompradosUsuarios[][], String coches[][], String usuarios[][]) {
		//cabecera
		cochesCompradosUsuarios[0][0]="DNI";
		cochesCompradosUsuarios[0][1]="NOMBRE";
		cochesCompradosUsuarios[0][2]="APELLIDO 1";
		cochesCompradosUsuarios[0][3]="APELLIDO 2";
		cochesCompradosUsuarios[0][4]="CORREO";
		cochesCompradosUsuarios[0][5]="MARCA";
		cochesCompradosUsuarios[0][6]="MODELO";
		//compra 1
		cochesCompradosUsuarios[1][0]=usuarios[1][0];
		cochesCompradosUsuarios[1][1]=usuarios[1][1];
		cochesCompradosUsuarios[1][2]=usuarios[1][2];
		cochesCompradosUsuarios[1][3]=usuarios[1][3];
		cochesCompradosUsuarios[1][4]=usuarios[1][4];
		cochesCompradosUsuarios[1][5]=coches[5][1];
		cochesCompradosUsuarios[1][6]=coches[5][2];
		//compra 2
		cochesCompradosUsuarios[2][0]=usuarios[4][0];
		cochesCompradosUsuarios[2][1]=usuarios[4][1];
		cochesCompradosUsuarios[2][2]=usuarios[4][2];
		cochesCompradosUsuarios[2][3]=usuarios[4][3];
		cochesCompradosUsuarios[2][4]=usuarios[4][4];
		cochesCompradosUsuarios[2][5]=coches[2][1];
		cochesCompradosUsuarios[2][6]=coches[2][2];
		//compra 3
		cochesCompradosUsuarios[3][0]=usuarios[2][0];
		cochesCompradosUsuarios[3][1]=usuarios[2][1];
		cochesCompradosUsuarios[3][2]=usuarios[2][2];
		cochesCompradosUsuarios[3][3]=usuarios[2][3];
		cochesCompradosUsuarios[3][4]=usuarios[2][4];
		cochesCompradosUsuarios[3][5]=coches[3][1];
		cochesCompradosUsuarios[3][6]=coches[3][2];
	}
	
	public static void iniciarCochesVendidosUsuarios(String cochesVendidosUsuarios[][], String coches[][], String usuarios[][]) {
		//cabecera
		cochesVendidosUsuarios[0][0]="DNI";
		cochesVendidosUsuarios[0][1]="NOMBRE";
		cochesVendidosUsuarios[0][2]="APELLIDO 1";
		cochesVendidosUsuarios[0][3]="APELLIDO 2";
		cochesVendidosUsuarios[0][4]="CORREO";
		cochesVendidosUsuarios[0][5]="MARCA";
		cochesVendidosUsuarios[0][6]="MODELO";
		//compra 1
		cochesVendidosUsuarios[1][0]=usuarios[1][0];
		cochesVendidosUsuarios[1][1]=usuarios[1][1];
		cochesVendidosUsuarios[1][2]=usuarios[1][2];
		cochesVendidosUsuarios[1][3]=usuarios[1][3];
		cochesVendidosUsuarios[1][4]=usuarios[1][4];
		cochesVendidosUsuarios[1][5]=coches[2][1];
		cochesVendidosUsuarios[1][6]=coches[2][2];
		//compra 2
		cochesVendidosUsuarios[2][0]=usuarios[4][0];
		cochesVendidosUsuarios[2][1]=usuarios[4][1];
		cochesVendidosUsuarios[2][2]=usuarios[4][2];
		cochesVendidosUsuarios[2][3]=usuarios[4][3];
		cochesVendidosUsuarios[2][4]=usuarios[4][4];
		cochesVendidosUsuarios[2][5]=coches[4][1];
		cochesVendidosUsuarios[2][6]=coches[4][2];
		//compra 3
		cochesVendidosUsuarios[3][0]=usuarios[2][0];
		cochesVendidosUsuarios[3][1]=usuarios[2][1];
		cochesVendidosUsuarios[3][2]=usuarios[2][2];
		cochesVendidosUsuarios[3][3]=usuarios[2][3];
		cochesVendidosUsuarios[3][4]=usuarios[2][4];
		cochesVendidosUsuarios[3][5]=coches[1][1];
		cochesVendidosUsuarios[3][6]=coches[1][2];
	}
	
	public static void mostrarMenuPrincipal() {
		System.out.println("Bienvenido al concesionario de supercoches Camps' Supercars");
		System.out.println("|-------------------------------|");
		System.out.println("| 1.- Entrar como administrador |");
		System.out.println("| 2.- Entrar como usuario       |");
		System.out.println("| 3.- Salir del programa        |");
		System.out.println("|-------------------------------|");
		System.out.print("Introduce una opcion: ");
	}
	
	public static boolean comprobarDatosCorrectosAdmin(String usuarioIntroducido, String passwdIntroducido, String admin, String passwd_admin, boolean adminCorrecto) {
		if(usuarioIntroducido.equals(admin) && passwdIntroducido.equals(passwd_admin)) {
			adminCorrecto=true;
		}
		else {
			adminCorrecto=false;
		}
		return adminCorrecto;	
	}
	
	public static void mostrarMenuAdmin() {
		System.out.println("|-----------------------------------------------|");
		System.out.println("| 1.- Agregar coches                            |");
		System.out.println("| 2.- Modificar coches                          |");
		System.out.println("| 3.- Eliminar coches                           |");
		System.out.println("| 4.- Mostrar coches                            |");
		System.out.println("| 5.- Mostrar usuarios registrados              |");
		System.out.println("| 6.- Mostrar coches comprados por los usuarios |");
		System.out.println("| 7.- Mostrar coches vendidos por los usuarios  |");
		System.out.println("| 8.- Cerrar sesion                             |");
		System.out.println("|-----------------------------------------------|");
		System.out.print("Introduce una opcion: ");
	}
	
	public static void mostrarMenuUsuarioUno() {
		System.out.println("|-----------------------|");
		System.out.println("| 1.- Iniciar sesion    |");
		System.out.println("| 2.- Registrarse       |");
		System.out.println("| 3.- Salir             |");
		System.out.println("|-----------------------|");
		System.out.print("Introduce una opcion: ");
	}
	
	public static void mostrarUsuariosRegistrados(String usuarios[][]) {
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
	
	public static boolean comprobarDni(String dniAComprobar){
		char[] letraDni = {
            'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D',  'X',  'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'
        };  
        String num= "";
        int ind = 0;  
        boolean valido=true;
		if (dniAComprobar.length()==9) {        
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
		}
		else {
			System.out.println("El DNI debe contener 8 numeros y 1 letra.");
			valido=false;
		}
        return valido;
   	} // fin comprobarDni
	
	public static boolean comprobarDniRepetido(String usuarios[][], String dni, boolean dniNoRepetido) {
		for (int i=1; i<usuarios.length; i++) {
			if (usuarios[i][0]!=null) {
				if (usuarios[i][0].equals(dni)) {
					dniNoRepetido=false;
				}
			}
			else {
				i=usuarios.length;
			}
		}
		return dniNoRepetido;
	}
	
	public static void realizarRegistroUsuarios(String usuarios[][], String dni, String nombre, String apellidoUno, String apellidoDos, String correo, String clave) {
		for (int i=1; i<usuarios.length; i++) {
			if (usuarios[i][0]==null) {
				usuarios[i][0]=dni;
				usuarios[i][1]=nombre;
				usuarios[i][2]=apellidoUno;
				usuarios[i][3]=apellidoDos;
				usuarios[i][4]=correo;
				usuarios[i][5]=clave;
				i=usuarios.length;
				System.out.println("Te has registrado correctamente.");
			}
		}
	}
	
	public static boolean comprobarUsuarioExiste(String usuarios[][], String correo, String clave, boolean usuarioCorrecto) {
		for (int i=1; i<usuarios.length; i++) {
			if (usuarios[i][4]!=null && usuarios[i][5]!=null) {
				if (usuarios[i][4].equals(correo) && usuarios[i][5].equals(clave)) {
					usuarioCorrecto=true;
					System.out.println("Has iniciado sesion correctamente.");
					i=usuarios.length;
				}
			}
		}
		return usuarioCorrecto;
	}
	
	public static void mostrarMenuUsuarioDos() {
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
	
	public static int agregarOmodificarCoches(String coches[][], int idCoche, String cadenaIdCoche, String marca, String modelo, String precioVenta, int idModificar) {
		if (idModificar!=0){	
			coches[idModificar][0]=cadenaIdCoche;
			coches[idModificar][1]=marca;
			coches[idModificar][2]=modelo;
			coches[idModificar][3]=precioVenta;
			coches[idModificar][4]="-";
			System.out.println("Se han realizado los cambios correctamente");
		}
		else {
			for(int i=1; i<coches.length; i++) {
				if(coches[i][0]==null) {
					cadenaIdCoche=Integer.toString(idCoche);
					coches[i][0]=cadenaIdCoche;
					idCoche=Integer.parseInt(cadenaIdCoche);
					idCoche++;
					coches[i][1]=marca;
					coches[i][2]=modelo;
					coches[i][3]=precioVenta;
					coches[i][4]="-";
					coches[i][5]="0";
					coches[i][6]="0";
					coches[i][7]="-";
					System.out.println("Coche agregado correctamente");
					i=coches.length;
				}
			}
		}
		return idCoche;
	}
	
	public static void mostrarListadoCoches(String coches[][], int idModificar, boolean usuarioIniciado) {
		if (idModificar!=0) {
			System.out.print(coches[0][0] + " | ");
			System.out.print(coches[0][1] + " | ");
			System.out.print(coches[0][2] + " | ");
			System.out.print(coches[0][3] + " | ");
			System.out.println(coches[0][4] + " | ");
			System.out.print(coches[idModificar][0] + " | ");
			System.out.print(coches[idModificar][1] + " | ");
			System.out.print(coches[idModificar][2] + " | ");
			System.out.print(coches[idModificar][3] + " | ");
			System.out.println(coches[idModificar][4] + " | ");
		}
		else if(usuarioIniciado) {
			for (int i=0; i<coches.length; i++) {
				if (coches[i][0]!=null) {
					System.out.print(coches[i][1] + " | ");
					System.out.print(coches[i][2] + " | ");
					System.out.print(coches[i][3] + " | ");
					System.out.println(coches[i][7] + " | ");
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
	
	public static int encontrarPosicionCoches(String coches[][], int idModificar, String cadenaIdCoche) {
		int posicion=-1;
		cadenaIdCoche=Integer.toString(idModificar);
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
	
	public static int encontrarPosicionCocheUsuario(String coches[][], String modelo) {
		int cocheUsuario=0;
		for(int i=1; i<coches.length; i++) {
			if(coches[i][2]!=null) {
				if(coches[i][2].equalsIgnoreCase(modelo)) {
					cocheUsuario=i;
					System.out.println("Modelo disponible (" + coches[i][2] + ")");
					i=coches.length;
				}
			}
		}
		return cocheUsuario;
	}
	
	public static boolean comprobarCocheExiste(String coches[][], int numCocheUsuario, String modelo, boolean cocheEncontrado) {
		if (coches[numCocheUsuario][2].equalsIgnoreCase(modelo)) {
			cocheEncontrado=true;
		}
		else {
			cocheEncontrado=false;
		}
		return cocheEncontrado;
	}
	
	public static double calcularPrecio(String coches[][], int numCocheUsuario, double IVA) {
		double precioFinal;
		double precio=Double.valueOf(coches[numCocheUsuario][3]);
		precioFinal=precio*IVA+precio;
		System.out.println("El precio del coche + IVA (21%) es: " + precioFinal + " euros");
		return precioFinal;
	}
	
	public static void confirmarCompra(String coches[][], String confirmarCompra, int numCocheUsuario, int contador, String correo, String modelo, String contadorCadena, String usuarios[][], String cochesCompradosUsuarios[][]) {
		if (confirmarCompra.equalsIgnoreCase("si")) {
			System.out.println("Compra confirmada");
			contador=Integer.parseInt(coches[numCocheUsuario][5]);
			contador++;
			contadorCadena=Integer.toString(contador);
			coches[numCocheUsuario][5]=contadorCadena;
			for (int i=0; i<cochesCompradosUsuarios.length; i++) {
				if (cochesCompradosUsuarios[i][0]==null) {
					for (int j=1; j<usuarios.length; j++) {
						if (usuarios[j][4].equals(correo)) {
							cochesCompradosUsuarios[i][0]=usuarios[j][0];
							cochesCompradosUsuarios[i][1]=usuarios[j][1];
							cochesCompradosUsuarios[i][2]=usuarios[j][2];
							cochesCompradosUsuarios[i][3]=usuarios[j][3];
							cochesCompradosUsuarios[i][4]=usuarios[j][4];
							j=usuarios.length;
						}
					}
					for (int k=1; k<coches.length; k++) {
						if(coches[k][2].equalsIgnoreCase(modelo)) {
							cochesCompradosUsuarios[i][5]=coches[k][1];
							cochesCompradosUsuarios[i][6]=coches[k][2];
							if (coches[k][7].equals("Usado")) {
								coches[k][0]=null;
								coches[k][1]=null;
								coches[k][2]=null;
								coches[k][3]=null;
								coches[k][4]=null;
								coches[k][5]=null;
								coches[k][6]=null;
								coches[k][7]=null;
							}
							k=coches.length;
						}
					}
					i=cochesCompradosUsuarios.length;	
				}
			}
		}
		else {
			System.out.println("Compra cancelada");
		}
	}
	
	public static void mostrarCochesCompradosAdmin(String cochesCompradosUsuarios[][]) {
		for (int i=0; i<cochesCompradosUsuarios.length; i++) {
			if (cochesCompradosUsuarios[i][0]!=null) {
				System.out.print(cochesCompradosUsuarios[i][0] + " | ");
				System.out.print(cochesCompradosUsuarios[i][1] + " | ");
				System.out.print(cochesCompradosUsuarios[i][2] + " | ");
				System.out.print(cochesCompradosUsuarios[i][3] + " | ");
				System.out.print(cochesCompradosUsuarios[i][4] + " | ");
				System.out.print(cochesCompradosUsuarios[i][5] + " | ");
				System.out.println(cochesCompradosUsuarios[i][6] + " | ");
			}
		}
	}
	
	public static void mostrarCochesCompradosUsuarios(String cochesCompradosUsuarios[][], String usuarios[][], String correo) {
		for (int i=0; i<cochesCompradosUsuarios.length; i++) {
			if (cochesCompradosUsuarios[i][0]!=null) {
				for (int j=1; j<usuarios.length; j++) {
					if (cochesCompradosUsuarios[i][4].equals(correo)) {
						System.out.print(cochesCompradosUsuarios[i][0] + " | ");
						System.out.print(cochesCompradosUsuarios[i][1] + " | ");
						System.out.print(cochesCompradosUsuarios[i][2] + " | ");
						System.out.print(cochesCompradosUsuarios[i][3] + " | ");
						System.out.print(cochesCompradosUsuarios[i][4] + " | ");
						System.out.print(cochesCompradosUsuarios[i][5] + " | ");
						System.out.println(cochesCompradosUsuarios[i][6] + " | ");
						j=usuarios.length;
					}
				}
			}
		}
	}
	
	public static int venderCochesAConcesionario (String coches[][], String marca, String modelo, String precioCompra, String cadenaIdCoche, int idCoche, int precioExtra, int contador, String contadorCadena) {
		int precio;
		for (int i=1; i<coches.length; i++) {
				if (!modelo.equalsIgnoreCase(coches[i][2])) {
					if (coches[i][0]==null) {
						cadenaIdCoche=Integer.toString(idCoche);
						coches[i][0]=cadenaIdCoche;
						idCoche=Integer.parseInt(cadenaIdCoche);
						idCoche++;
						coches[i][1]=marca;
						coches[i][2]=modelo;
						coches[i][4]=precioCompra;
						precio=Integer.valueOf(coches[i][4]);
						precioExtra+=precio;
						coches[i][3]=String.valueOf(precioExtra);
						coches[i][5]="0";
						coches[i][6]="1";
						coches[i][7]="Usado";
						i=coches.length;
					}
				}
				else {
					contador=Integer.parseInt(coches[i][6]);
					contador++;
					contadorCadena=Integer.toString(contador);
					coches[i][6]=contadorCadena;
					i=coches.length;
				}
			}
		return idCoche;
	}
	
	public static void guardarInfoCochesVendidosUsuarios(String coches[][], String usuarios[][], String cochesVendidosUsuarios[][], String marca, String modelo, String correo) {
		for (int i=0; i<cochesVendidosUsuarios.length; i++) {
			if (cochesVendidosUsuarios[i][0]==null) {
				for (int j=1; j<usuarios.length; j++) {
					if (usuarios[j][4].equals(correo)) {
						cochesVendidosUsuarios[i][0]=usuarios[j][0];
						cochesVendidosUsuarios[i][1]=usuarios[j][1];
						cochesVendidosUsuarios[i][2]=usuarios[j][2];
						cochesVendidosUsuarios[i][3]=usuarios[j][3];
						cochesVendidosUsuarios[i][4]=usuarios[j][4];
						j=usuarios.length;
					}
				}
				for (int k=1; k<coches.length; k++) {
					if(coches[k][2].equalsIgnoreCase(modelo)) {
						cochesVendidosUsuarios[i][5]=coches[k][1];
						cochesVendidosUsuarios[i][6]=coches[k][2];
						k=coches.length;
					}
				}
				i=cochesVendidosUsuarios.length;	
			}
		}
	}
	
	public static void mostrarCochesVendidosAdmin(String cochesVendidosUsuarios[][]) {
		for (int i=0; i<cochesVendidosUsuarios.length; i++) {
			if (cochesVendidosUsuarios[i][0]!=null) {
				System.out.print(cochesVendidosUsuarios[i][0] + " | ");
				System.out.print(cochesVendidosUsuarios[i][1] + " | ");
				System.out.print(cochesVendidosUsuarios[i][2] + " | ");
				System.out.print(cochesVendidosUsuarios[i][3] + " | ");
				System.out.print(cochesVendidosUsuarios[i][4] + " | ");
				System.out.print(cochesVendidosUsuarios[i][5] + " | ");
				System.out.println(cochesVendidosUsuarios[i][6] + " | ");
			}
		}
	}
	
	public static void mostrarCochesVendidosUsuarios(String cochesVendidosUsuarios[][], String usuarios[][], String correo) {
		for (int i=0; i<cochesVendidosUsuarios.length; i++) {
			if (cochesVendidosUsuarios[i][0]!=null) {
				for (int j=1; j<usuarios.length; j++) {
					if (cochesVendidosUsuarios[i][4].equals(correo)) {
						System.out.print(cochesVendidosUsuarios[i][0] + " | ");
						System.out.print(cochesVendidosUsuarios[i][1] + " | ");
						System.out.print(cochesVendidosUsuarios[i][2] + " | ");
						System.out.print(cochesVendidosUsuarios[i][3] + " | ");
						System.out.print(cochesVendidosUsuarios[i][4] + " | ");
						System.out.print(cochesVendidosUsuarios[i][5] + " | ");
						System.out.println(cochesVendidosUsuarios[i][6] + " | ");
						j=usuarios.length;
					}
				}
			}
		}
	}
	
}
