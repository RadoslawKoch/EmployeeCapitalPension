package com.pallas.qwork.errors;

import com.pallas.qwork.definitions.error.ErrorLoggerDefinition;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ErrorLogger 
    implements ErrorLoggerDefinition{
    
    private final String filePath;
    
    public ErrorLogger(String filePath){
        this.filePath = filePath;
    }
    
    @Override
    public void write(Exception exc){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath,true))){
            writer.append("TIME[ " + LocalDateTime.now() +" ] " + exc.getMessage());
            writer.newLine();
        } catch (IOException ex) {
            Logger.getLogger(ErrorLogger.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public String getLogContent(){
        String content = "";
        try (BufferedReader writer = new BufferedReader(new FileReader(filePath))){
            while(writer.ready()){
                content+=writer.readLine();
            }
        } catch (IOException ex) {
            Logger.getLogger(ErrorLogger.class.getName()).log(Level.SEVERE, null, ex);
        }
        return content;
    }
    
    @Override
    public void cleanUp(){
       try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))){
            writer.write("");
        } catch (IOException ex) {
            Logger.getLogger(ErrorLogger.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
