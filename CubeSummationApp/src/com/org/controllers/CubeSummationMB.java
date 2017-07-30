package com.org.controllers;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import com.org.commons.BusinessException;
import com.org.commons.Util;
import com.org.dm.CubeSummationDTO;
import com.org.dm.Operacion;
import com.org.ws.CubeSummationWS;

@ManagedBean(name="cubeSummationMB")
@ViewScoped
public class CubeSummationMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// Entidades
	
	private CubeSummationDTO cubeSummationDTO;
	private Operacion operacion;
	
	// listas
	
	private List<Integer> listaCasosPrueba;
	private List<String> listaTipoOperacion;
	private ArrayList<Operacion> listaOperaciones;
		
	// Servicios Web
	
	private CubeSummationWS cubeSummationWS;
	
	// Variables
	
	private int numeroCasosPrueba;
	private boolean habilitarCasosPrueba;
	private boolean habilitarOperacion;
	private boolean habilitarQuery;
	private boolean habilitarUpdate;
	private boolean habilitarBotonGuardarOperacion;
	private boolean habilitarBotonGuardarCasoPrueba;
	private boolean habilitarObtenerResultado;
	private boolean habilitarRespuestaCasoPrueba;
	private boolean habilitarNumeroCPimpreso;
	private boolean habilitaDimensionImpreso;
	private boolean habilitaCantidadOperaciones;
	private boolean habilitaTipoOperacion;
	private int casoPrueba;
	private String tipoOperacion;
	public String informacionEntrada;
	
	private static final String QUERY = "QUERY";
	private static final String UPDATE = "UPDATE";
	
	@PostConstruct
	public void init() {
		inicializarVariables();
		Properties properties = new Properties();
		properties.setProperty("openejb.embedded.remotable", "true");
		Service calculatorService;
		try {
			calculatorService = Service.create(
					new URL("http://localhost:8080/CubeSummationApp/CubeSummationWS/CubeSummationWSImpl?wsdl"),
					new QName("http://sortedset.com/wsdl", "CubeSummationWS"));
			cubeSummationWS = calculatorService.getPort(CubeSummationWS.class);
		} catch (MalformedURLException e) {
			Logger.getLogger(CubeSummationMB.class.getName()).log(Level.SEVERE, null, e);
			Util.lanzaMensajeWarn(e.getMessage());
			Util.ejecutarUpdate(":loginForm:msgCubeSummation");
		}
	}
	
	public void inicializarVariables(){
		habilitarCasosPrueba = true;
		habilitarOperacion = true;
		listaCasosPrueba = new ArrayList<>();
		listaTipoOperacion = new ArrayList<>();
		listaTipoOperacion.add(QUERY);
		listaTipoOperacion.add(UPDATE);
		cubeSummationDTO = new CubeSummationDTO();
		cubeSummationDTO.setOperacionEjecutada(1);
		operacion = new Operacion();
		informacionEntrada = new String();
		listaOperaciones = new ArrayList<>();		
	}
	
	public void habilitarPrueba(){
		for1: for(Integer prueba : listaCasosPrueba){
			if(prueba == casoPrueba){
				habilitarOperacion = false;
				break for1;
			}else{
				habilitarOperacion = true;
			}
		}
	}
	
	public void validarNumeroCasosPrueba(){
		if( numeroCasosPrueba >= 1 && numeroCasosPrueba <= 50 ){
			if(numeroCasosPrueba != 0 ){
				listaCasosPrueba = new ArrayList<>();
				habilitarCasosPrueba = false;
			}else{
				habilitarCasosPrueba = true;
			}
			for(int i = 1; i<=numeroCasosPrueba; i++){
				listaCasosPrueba.add(i);
			}
			numeroCasosPrueba = 0;
		}else{
			numeroCasosPrueba = 0;
			Util.lanzaMensajeWarn("El número de casos de prueba debe cumplir con el siguiente criterio (1 <= T <= 50)");
			Util.ejecutarUpdate(":loginForm:idNumeroCasosPrueba");
			habilitarCasosPrueba = true;
		}
		
	}
	
	public void validarTipoOperacion() {
		if (tipoOperacion != null && tipoOperacion.equalsIgnoreCase(UPDATE)) {
			habilitarUpdate = true;
			habilitarQuery = false;
			operacion = new Operacion();
		}else if (tipoOperacion != null && tipoOperacion.equalsIgnoreCase(QUERY)) {
			habilitarQuery = true;
			habilitarUpdate = false;
			operacion = new Operacion();
		} else {
			habilitarUpdate = false;
			habilitarQuery = false;
		}
	}
	
	public void obtenerResultado(){
		habilitarRespuestaCasoPrueba = true;		
		try {
			cubeSummationDTO = cubeSummationWS.calcularSumaBloque(cubeSummationDTO);
		} catch (BusinessException e) {
			Logger.getLogger(CubeSummationMB.class.getName()).log(Level.SEVERE, null, e);
			Util.lanzaMensajeWarn(e.getMessage());
			Util.ejecutarUpdate(":loginForm:msgCubeSummation");
		}
	}
	
	public void guardarCasoPrueba(){
		Operacion[] arrayOperacion = new Operacion[listaOperaciones.size()];
		arrayOperacion = listaOperaciones.toArray(arrayOperacion);
		cubeSummationDTO.getCasosPruebaOperacion().put(casoPrueba, arrayOperacion);
		for(int i=0 ; i < listaCasosPrueba.size() ; i++){
			if( listaCasosPrueba.get(i) == casoPrueba ){
				listaCasosPrueba.remove(i);
			}
		}
		casoPrueba = 0;
		cubeSummationDTO.setDimensionMatriz(0);
		cubeSummationDTO.setNumeroOperaciones(0);
		Util.lanzaMensajeWarn("El caso de prueba ha sido guardado correctamente, por favor seleccionar una nueva prueba si existen mas");
		habilitarBotonGuardarCasoPrueba = false;
		if(listaCasosPrueba.size() == 0){
			habilitarObtenerResultado = true;
		}
		listaOperaciones = new ArrayList<>();
		habilitaDimensionImpreso = false;
		habilitaCantidadOperaciones = false;
		habilitaTipoOperacion = false;
	}
	
	public boolean validarValoresEntrada(){
		StringBuilder mensaje = new StringBuilder();
		boolean emitirMensaje = false;
		int d = cubeSummationDTO.getDimensionMatriz();
		switch (tipoOperacion) {
		case UPDATE:
			int x = Integer.valueOf(operacion.getX());
			int y = Integer.valueOf(operacion.getY());
			int z = Integer.valueOf(operacion.getZ());
			int w = Integer.valueOf(operacion.getW());
			double valor = Math.pow(10, 9);
			if( !( x >= 1 && x <= d ) ){
				mensaje.append(" X");
				emitirMensaje = true;
			}
			if( !( y >= 1 && y <= d ) ){
				mensaje.append(" Y");
				emitirMensaje = true;
			}
			if( !( z >= 1 && z <= d ) ){
				mensaje.append(" Z");
				emitirMensaje = true;
			}
			if( !( w >= -valor && w <= valor ) ){
				mensaje.append(" y W (-10^9 <= W <= 10^9)");
				emitirMensaje = true;
			}
			break;
		case QUERY:
			int x1 = Integer.valueOf(operacion.getX1());
			int x2 = Integer.valueOf(operacion.getX2());
			int y1 = Integer.valueOf(operacion.getY1());
			int y2 = Integer.valueOf(operacion.getY2());
			int z1 = Integer.valueOf(operacion.getZ1());
			int z2 = Integer.valueOf(operacion.getZ2());
			if( !( x1 >= 1 && x1 <= d ) ){
				mensaje.append(" X1");
				emitirMensaje = true;
			}
			if( !( x2 >= 1 && x2 <= d ) ){
				mensaje.append(" X2");
				emitirMensaje = true;
			}
			if( !( y1 >= 1 && y1 <= d ) ){
				mensaje.append(" Y1");
				emitirMensaje = true;
			}
			if( !( y2 >= 1 && y2 <= d ) ){
				mensaje.append(" Y2");
				emitirMensaje = true;
			}
			if( !( z1 >= 1 && z1 <= d ) ){
				mensaje.append(" Z1");
				emitirMensaje = true;
			}
			if( !( z2 >= 1 && z2 <= d ) ){
				mensaje.append(" Z2");
				emitirMensaje = true;
			}
			break;	
		default:
			break;
		}
		if(emitirMensaje){
			Util.lanzaMensajeWarn("Se debe cumplir siguiente criterio (1 <= coordenada <= N) para las coordenas"
					+mensaje);
		}			
		return !emitirMensaje;
	}
	
	public void guardarOperacion() {
		try {
			if (validarValoresEntrada()) {
				operacion.setTipoOperacion(tipoOperacion);
				operacion.setDimension(cubeSummationDTO.getDimensionMatriz());
				listaOperaciones.add(operacion);
				if (informacionEntrada == null)
					informacionEntrada = new String();
				if (!habilitarNumeroCPimpreso) {
					informacionEntrada = informacionEntrada + "" + listaCasosPrueba.size() + "\n";
					habilitarNumeroCPimpreso = true;
				}
				if (!habilitaDimensionImpreso) {
					informacionEntrada = informacionEntrada + "" + cubeSummationDTO.getDimensionMatriz() + " "
							+ cubeSummationDTO.getNumeroOperaciones() + "\n";
					habilitaDimensionImpreso = true;
				}
				switch (tipoOperacion) {
				case UPDATE:
					informacionEntrada = informacionEntrada + tipoOperacion + " " + operacion.getX() + " "
							+ operacion.getY() + " " + operacion.getZ() + " " + operacion.getW() + "\n";
					break;
				case QUERY:
					informacionEntrada = informacionEntrada + tipoOperacion + " " + operacion.getX1() + " "
							+ operacion.getY1() + " " + operacion.getZ1() + " " + operacion.getX2() + " "
							+ operacion.getY2() + " " + operacion.getZ2() + "\n";
					break;
				default:
					break;
				}
				operacion = new Operacion();
				habilitarUpdate = false;
				habilitarQuery = false;
				tipoOperacion = "";
				if (cubeSummationDTO.getOperacionEjecutada() < cubeSummationDTO.getNumeroOperaciones()) {
					Util.lanzaMensajeWarn("La operación número " + cubeSummationDTO.getOperacionEjecutada()
							+ " ha sido almacenada correctamente");
					cubeSummationDTO.setOperacionEjecutada(cubeSummationDTO.getOperacionEjecutada() + 1);
				} else {
					habilitarBotonGuardarOperacion = false;
					Util.lanzaMensajeWarn("Todas las operaciones han sido almacenado correctamente");
					habilitarBotonGuardarCasoPrueba = true;
					cubeSummationDTO.setOperacionEjecutada(1);
					cubeSummationDTO.setDimensionMatriz(0);
					cubeSummationDTO.setNumeroOperaciones(0);
					habilitarOperacion = true;
				}
			}
		} catch (NumberFormatException e) {
			Util.lanzaMensajeWarn("Los datos brindados deben ser numéricos");
		} catch (Exception e) {
			Util.lanzaMensajeWarn(e.getMessage());
		}
	}

	public void validarDimension(){
		if( !(cubeSummationDTO.getDimensionMatriz() >= 1 && cubeSummationDTO.getDimensionMatriz() <= 100) ){
			Util.lanzaMensajeWarn("la dimensión de la matriz debe cumplir con el siguiente criterio (1 <= N <= 100)");
			cubeSummationDTO.setDimensionMatriz(0);
			habilitaCantidadOperaciones = false;
		}else{
			habilitaCantidadOperaciones = true;
		}
	}
	
	public void validarCantidadOperaciones(){

		if( !(cubeSummationDTO.getNumeroOperaciones() >= 1 && cubeSummationDTO.getNumeroOperaciones() <= 1000) ){
			Util.lanzaMensajeWarn("Elnúmero de operaciones a ejecutar debe cumplir con el siguiente criterio (1 <= M <= 1000)");
			cubeSummationDTO.setNumeroOperaciones(0);
			habilitarBotonGuardarOperacion = false;
			habilitaTipoOperacion = false;
		}else{
			habilitarBotonGuardarOperacion = true;
			habilitaTipoOperacion = true;
		}
	}
	
	public void limpiar(){
		inicializarVariables();
		habilitaCantidadOperaciones = false;
		habilitaTipoOperacion = false;
		habilitarBotonGuardarOperacion = false;
		numeroCasosPrueba = 0;
		habilitarCasosPrueba = true;
		habilitarObtenerResultado = false;
	}
	
	public int getNumeroCasosPrueba() {
		return numeroCasosPrueba;
	}

	public void setNumeroCasosPrueba(int numeroCasosPrueba) {
		this.numeroCasosPrueba = numeroCasosPrueba;
	}

	public boolean isHabilitarCasosPrueba() {
		return habilitarCasosPrueba;
	}

	public void setHabilitarCasosPrueba(boolean habilitarCasosPrueba) {
		this.habilitarCasosPrueba = habilitarCasosPrueba;
	}

	public List<Integer> getListaCasosPrueba() {
		return listaCasosPrueba;
	}

	public void setListaCasosPrueba(List<Integer> listaCasosPrueba) {
		this.listaCasosPrueba = listaCasosPrueba;
	}

	public int getCasoPrueba() {
		return casoPrueba;
	}

	public void setCasoPrueba(int casoPrueba) {
		this.casoPrueba = casoPrueba;
	}

	public boolean isHabilitarQuery() {
		return habilitarQuery;
	}

	public void setHabilitarQuery(boolean habilitarQuery) {
		this.habilitarQuery = habilitarQuery;
	}

	public boolean isHabilitarUpdate() {
		return habilitarUpdate;
	}

	public void setHabilitarUpdate(boolean habilitarUpdate) {
		this.habilitarUpdate = habilitarUpdate;
	}

	public boolean isHabilitarOperacion() {
		return habilitarOperacion;
	}

	public void setHabilitarOperacion(boolean habilitarOperacion) {
		this.habilitarOperacion = habilitarOperacion;
	}

	public List<String> getListaTipoOperacion() {
		return listaTipoOperacion;
	}

	public void setListaTipoOperacion(List<String> listaTipoOperacion) {
		this.listaTipoOperacion = listaTipoOperacion;
	}

	public String getTipoOperacion() {
		return tipoOperacion;
	}

	public void setTipoOperacion(String tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}

	public CubeSummationDTO getCubeSummationDTO() {
		return cubeSummationDTO;
	}

	public void setCubeSummationDTO(CubeSummationDTO cubeSummationDTO) {
		this.cubeSummationDTO = cubeSummationDTO;
	}

	public boolean isHabilitarBotonGuardarOperacion() {
		return habilitarBotonGuardarOperacion;
	}

	public void setHabilitarBotonGuardarOperacion(boolean habilitarBotonGuardarOperacion) {
		this.habilitarBotonGuardarOperacion = habilitarBotonGuardarOperacion;
	}

	public boolean isHabilitarBotonGuardarCasoPrueba() {
		return habilitarBotonGuardarCasoPrueba;
	}

	public void setHabilitarBotonGuardarCasoPrueba(boolean habilitarBotonGuardarCasoPrueba) {
		this.habilitarBotonGuardarCasoPrueba = habilitarBotonGuardarCasoPrueba;
	}

	public boolean isHabilitarObtenerResultado() {
		return habilitarObtenerResultado;
	}

	public void setHabilitarObtenerResultado(boolean habilitarObtenerResultado) {
		this.habilitarObtenerResultado = habilitarObtenerResultado;
	}

	public boolean isHabilitarRespuestaCasoPrueba() {
		return habilitarRespuestaCasoPrueba;
	}

	public void setHabilitarRespuestaCasoPrueba(boolean habilitarRespuestaCasoPrueba) {
		this.habilitarRespuestaCasoPrueba = habilitarRespuestaCasoPrueba;
	}

	public Operacion getOperacion() {
		return operacion;
	}

	public void setOperacion(Operacion operacion) {
		this.operacion = operacion;
	}

	public String getInformacionEntrada() {
		return informacionEntrada;
	}

	public void setInformacionEntrada(String informacionEntrada) {
		this.informacionEntrada = informacionEntrada;
	}

	public boolean isHabilitaCantidadOperaciones() {
		return habilitaCantidadOperaciones;
	}

	public void setHabilitaCantidadOperaciones(boolean habilitaCantidadOperaciones) {
		this.habilitaCantidadOperaciones = habilitaCantidadOperaciones;
	}

	public boolean isHabilitaTipoOperacion() {
		return habilitaTipoOperacion;
	}

	public void setHabilitaTipoOperacion(boolean habilitaTipoOperacion) {
		this.habilitaTipoOperacion = habilitaTipoOperacion;
	}	
			
}
