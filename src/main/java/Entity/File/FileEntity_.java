/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity.File;

import Entity.File.FileEntity.FILE_STATUS;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 *
 * @author KH
 */
@StaticMetamodel(FileEntity.class)
public class FileEntity_ /*extends EnterpriseUnit_*/{
    
    public static volatile SingularAttribute<FileEntity,String> FILENAME;
    public static volatile SingularAttribute<FileEntity,Long> BYTE_SIZE;
    public static volatile SingularAttribute<FileEntity,Long> SEQUENCE_SIZE;
    public static volatile SingularAttribute<FileEntity,FILE_STATUS> STATUS;
    public static volatile ListAttribute<FileEntity,FileSequence> sequences;
}
