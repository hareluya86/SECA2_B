/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SECA2.File;

import EDS.BusinessUnit.EnterpriseUnit;
import EDS.BusinessUnit.EnterpriseUnit_;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 *
 * @author KH
 */
@StaticMetamodel(FileEntity.class)
public class FileEntity_ extends EnterpriseUnit_{
    
    public static volatile SingularAttribute<EnterpriseUnit,String> FILENAME;
}
