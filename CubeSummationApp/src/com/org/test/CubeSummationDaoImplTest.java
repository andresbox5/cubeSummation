package com.org.test;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;

import com.org.commons.DAOException;
import com.org.dao.impl.CubeSummationDaoImpl;
import com.org.dm.CubeSummationDTO;
import com.org.dm.Operacion;

public class CubeSummationDaoImplTest {
	
	private static final String QUERY = "QUERY";
	private static final String UPDATE = "UPDATE";
	
	@Test
	public void calcularSumaBloque(){
		CubeSummationDaoImpl cubeSummationDaoImpl = new CubeSummationDaoImpl();
		CubeSummationDTO cubeSummationDTO = new CubeSummationDTO();
		HashMap<Integer, Operacion[]> casosPruebaOperacion;
		casosPruebaOperacion = new HashMap<>();
		Operacion[] arrayOpercion1 = new Operacion[5];
		Operacion operacion = new Operacion();
		operacion.setTipoOperacion(UPDATE);
		operacion.setX("2");
		operacion.setY("2");
		operacion.setZ("2");
		operacion.setW("4");
		operacion.setDimension(4);
		arrayOpercion1[0] = operacion;
		
		operacion = new Operacion();
		operacion.setTipoOperacion(QUERY);
		operacion.setX1("1");
		operacion.setX2("3");
		operacion.setY1("1");
		operacion.setY2("3");
		operacion.setZ1("1");
		operacion.setZ2("3");
		arrayOpercion1[1] = operacion;
		
		operacion = new Operacion();
		operacion.setTipoOperacion(UPDATE);
		operacion.setX("1");
		operacion.setY("1");
		operacion.setZ("1");
		operacion.setW("23");
		arrayOpercion1[2] = operacion;
		
		operacion = new Operacion();
		operacion.setTipoOperacion(QUERY);
		operacion.setX1("2");
		operacion.setX2("4");
		operacion.setY1("2");
		operacion.setY2("4");
		operacion.setZ1("2");
		operacion.setZ2("4");
		arrayOpercion1[3] = operacion;
		
		operacion = new Operacion();
		operacion.setTipoOperacion(QUERY);
		operacion.setX1("1");
		operacion.setX2("3");
		operacion.setY1("1");
		operacion.setY2("3");
		operacion.setZ1("1");
		operacion.setZ2("3");
		arrayOpercion1[4] = operacion;
		
		// Caso prueba 1
		casosPruebaOperacion.put(1, arrayOpercion1);
		
		Operacion[] arrayOpercion2 = new Operacion[4];
		
		operacion = new Operacion();
		operacion.setTipoOperacion(UPDATE);
		operacion.setX("2");
		operacion.setY("2");
		operacion.setZ("2");
		operacion.setW("1");
		operacion.setDimension(2);
		arrayOpercion2[0] = operacion;
		
		operacion = new Operacion();
		operacion.setTipoOperacion(QUERY);
		operacion.setX1("1");
		operacion.setX2("1");
		operacion.setY1("1");
		operacion.setY2("1");
		operacion.setZ1("1");
		operacion.setZ2("1");
		arrayOpercion2[1] = operacion;
		
		operacion = new Operacion();
		operacion.setTipoOperacion(QUERY);
		operacion.setX1("1");
		operacion.setX2("2");
		operacion.setY1("1");
		operacion.setY2("2");
		operacion.setZ1("1");
		operacion.setZ2("2");
		arrayOpercion2[2] = operacion;
		
		operacion = new Operacion();
		operacion.setTipoOperacion(QUERY);
		operacion.setX1("2");
		operacion.setX2("2");
		operacion.setY1("2");
		operacion.setY2("2");
		operacion.setZ1("2");
		operacion.setZ2("2");
		arrayOpercion2[3] = operacion;
		
		// Caso prueba 2
		casosPruebaOperacion.put(2, arrayOpercion2);
		
		cubeSummationDTO.setCasosPruebaOperacion(casosPruebaOperacion);
		String resultadoEsperado = "4\n4\n27\n0\n1\n1\n";
		System.out.println(" resultadoEsperado \n"+resultadoEsperado);
		try {
			String resultadoObtenido = cubeSummationDaoImpl.calcularSumaBloque(cubeSummationDTO).getRespuestaCasosPrueba();
			System.out.println(" resultadoObtenido \n"+resultadoObtenido);
			Assert.assertEquals(resultadoEsperado, resultadoObtenido);
		} catch (DAOException e) {
			
			e.printStackTrace();
		}
		
	}

}
