/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.modelo;

/**
 *
 * @author alexc
 */
public class Foto {
    private String Url;
    
    public Foto(String Url){
        this.Url=Url;
    }
    
    public String getUrl(){
        return Url;
    }
    
    public void setUrl(String Url){
        this.Url=Url;
    }
}
