package com.org.dao.impl;

import java.util.Map;

import com.org.commons.DAOException;
import com.org.dao.CubeSummationDAO;
import com.org.dm.CubeSummationDTO;
import com.org.dm.Operacion;

public class CubeSummationDaoImpl implements CubeSummationDAO {
	
	private static final String QUERY = "QUERY";
	private static final String UPDATE = "UPDATE";

	/**
	 * Realiza cada una de las operaciones brindadas para cada caso de prueba en particular
	 * y arma la respuesta a ser presentada al usuario
	 */
	@Override
	public CubeSummationDTO calcularSumaBloque(CubeSummationDTO cubeSummationDTO) throws DAOException {
		StringBuilder salida = new StringBuilder();
		try {
			for (Map.Entry<Integer, Operacion[]> casoPrueba : cubeSummationDTO.getCasosPruebaOperacion()
					.entrySet()) {
				int dimensionMatriz = 0;
				if(casoPrueba.getValue().length > 0){
					dimensionMatriz = casoPrueba.getValue()[0].getDimension();
				}
				int[][][] cubo = new int[dimensionMatriz + 1][dimensionMatriz + 1][dimensionMatriz + 1];
				for (Operacion operacion : casoPrueba.getValue()) {

					String tipoOperacion = operacion.getTipoOperacion();
					switch (tipoOperacion) {
					case UPDATE:
						int x = Integer.valueOf(operacion.getX());
						int y = Integer.valueOf(operacion.getY());
						int z = Integer.valueOf(operacion.getZ());
						int w = Integer.valueOf(operacion.getW());
						cubo[x][y][z] = w;
						break;
					case QUERY:
						int suma = 0;
						int x1 = Integer.valueOf(operacion.getX1());
						int x2 = Integer.valueOf(operacion.getX2());
						int y1 = Integer.valueOf(operacion.getY1());
						int y2 = Integer.valueOf(operacion.getY2());
						int z1 = Integer.valueOf(operacion.getZ1());
						int z2 = Integer.valueOf(operacion.getZ2());
						for (int i = x1; i <= x2; i++) {
							for (int j = y1; j <= y2; j++) {
								for (int k = z1; k <= z2; k++) {
									suma += cubo[i][j][k];
								}
							}
						}
						salida.append("" + suma + "\n");
						break;
					}
				}

			}
			cubeSummationDTO.setRespuestaCasosPrueba(salida.toString());
		} catch (Exception e) {
			throw new DAOException();
		}
		return cubeSummationDTO;
	}
	
}
