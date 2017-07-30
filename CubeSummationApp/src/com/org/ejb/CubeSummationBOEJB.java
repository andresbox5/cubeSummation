package com.org.ejb;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.org.bo.CubeSummationBO;
import com.org.commons.BusinessException;
import com.org.commons.DAOException;
import com.org.dao.CubeSummationDAO;
import com.org.dm.CubeSummationDTO;

@Stateless(name = "cubeSummationBO")
public class CubeSummationBOEJB implements CubeSummationBO {

	@Inject
	private CubeSummationDAO cubeSummationDAO;

	/**
	 * Consume la capacidad calcularSumaBloque de cubeSummationDAO
	 */
	@Override
	public CubeSummationDTO calcularSumaBloque(CubeSummationDTO cubeSummationDTO) throws BusinessException {
		try {
			return cubeSummationDAO.calcularSumaBloque(cubeSummationDTO);
		} catch (DAOException e) {
			throw new BusinessException(e.getMessage());
		}
	}
	
}
