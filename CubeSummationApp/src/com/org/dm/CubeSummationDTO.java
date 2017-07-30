package com.org.dm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CubeSummationDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String respuestaCasosPrueba;
	private int numeroCasosPrueba;
	private int casoPruebaEjecutado;
	private int dimensionMatriz;
	private int numeroOperaciones;
	private int operacionEjecutada;
	private List<String> ListaRespuestaCasosPrueba;
	private HashMap<Integer, Operacion[]> casosPruebaOperacion;
	
	
	public CubeSummationDTO(){
		ListaRespuestaCasosPrueba = new ArrayList<>();	
		casosPruebaOperacion = new HashMap<>();
	}
	
	public int getNumeroCasosPrueba() {
		return numeroCasosPrueba;
	}
	public void setNumeroCasosPrueba(int numeroCasosPrueba) {
		this.numeroCasosPrueba = numeroCasosPrueba;
	}
	public int getCasoPruebaEjecutado() {
		return casoPruebaEjecutado;
	}
	public void setCasoPruebaEjecutado(int casoPruebaEjecutado) {
		this.casoPruebaEjecutado = casoPruebaEjecutado;
	}
	public int getDimensionMatriz() {
		return dimensionMatriz;
	}
	public void setDimensionMatriz(int dimensionMatriz) {
		this.dimensionMatriz = dimensionMatriz;
	}
	public int getNumeroOperaciones() {
		return numeroOperaciones;
	}
	public void setNumeroOperaciones(int numeroOperaciones) {
		this.numeroOperaciones = numeroOperaciones;
	}
	public int getOperacionEjecutada() {
		return operacionEjecutada;
	}
	public void setOperacionEjecutada(int operacionEjecutada) {
		this.operacionEjecutada = operacionEjecutada;
	}

	public String getRespuestaCasosPrueba() {
		return respuestaCasosPrueba;
	}

	public void setRespuestaCasosPrueba(String respuestaCasosPrueba) {
		this.respuestaCasosPrueba = respuestaCasosPrueba;
	}

	public List<String> getListaRespuestaCasosPrueba() {
		return ListaRespuestaCasosPrueba;
	}

	public void setListaRespuestaCasosPrueba(List<String> listaRespuestaCasosPrueba) {
		ListaRespuestaCasosPrueba = listaRespuestaCasosPrueba;
	}

	public HashMap<Integer, Operacion[]> getCasosPruebaOperacion() {
		return casosPruebaOperacion;
	}

	public void setCasosPruebaOperacion(HashMap<Integer, Operacion[]> casosPruebaOperacion) {
		this.casosPruebaOperacion = casosPruebaOperacion;
	}	

}
