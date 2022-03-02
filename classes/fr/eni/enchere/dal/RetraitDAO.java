package fr.eni.enchere.dal;

import fr.eni.enchere.bo.Retrait;
import fr.eni.enchere.exceptions.BusinessException;

public interface RetraitDAO {
  void insert(Retrait ret )throws BusinessException;
}
