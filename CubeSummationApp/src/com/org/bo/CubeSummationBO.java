package com.org.bo;

import javax.ejb.Local;

import com.org.commons.BusinessException;
import com.org.dm.CubeSummationDTO;

@Local
public interface CubeSummationBO {

	CubeSummationDTO calcularSumaBloque(CubeSummationDTO cubeSummationDTO) throws BusinessException;

}
