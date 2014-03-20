/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Program.File;

import SECA2.File.FileEntity;
import SECA2.File.FileSequence;
import java.util.List;


/**
 * Utility class that processes file and sequence objects
 * <p>
 * 
 * @author vincent.a.lee
 */
public abstract class FileManager {
    
    
    /**
     * Search for a particular sequence
     * <p>
     * Returns sequence that contains matches searchString case-sensitively.
     * 
     * @param searchString
     * @return FileSequence
     */
    public abstract FileSequence searchSequence(String searchString);
    
    /**
     * Search for a list of sequences
     * <p>
     * Returns a list of sequences that contains the substring searchString 
     * case-sensitively.
     * 
     * @param searchString
     * @return List
     */
    public abstract List<FileSequence> searchSequences(String searchString);
    
    public abstract FileEntity createFile(String filename);
    
    public abstract void addSequence(FileEntity file, FileSequence sequence);
}
