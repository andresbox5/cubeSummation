package com.org.dao;

import com.org.commons.DAOException;
import com.org.dm.CubeSummationDTO;

public interface CubeSummationDAO {
	
	CubeSummationDTO calcularSumaBloque(CubeSummationDTO cubeSummationDTO) throws DAOException;

}
