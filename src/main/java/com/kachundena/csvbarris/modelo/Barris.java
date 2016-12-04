package com.kachundena.csvbarris.modelo;


import java.util.ArrayList;
import java.util.List;




public class Barris {

    private List<Barri> barris;

    public Barris() {
        barris = new ArrayList<Barri>();
    }

    /**
     * 
     * @param barri
     */
    public void addBarri(Barri barri) {
        barris.add(barri);
    }

    /**
     * 
     * @param linea
     * @param barri
     */
    public void editBarri(int linea, Barri barri) {
        barris.set(linea, barri);
    }

    /**
     * 
     * @param linea
     */
    public void deleteBarri(int linea) {
        barris.remove(linea);
    }


    /**
     * 
     * @param linea
     */
    public Barri getBarri(int linea) {
        return barris.get(linea);
    }

    /**
     * 
     * @param districte
     */
    public List<Barri> getBarrisdeDistricte(int districte) {
        int i = 0;
        while (i< barris.size()) {
            if (barris.get(i).getDistricte() != districte) {
                barris.remove(i); 
            }  
            else {
                i += 1;
            }
        }
        return barris;
    }
    
    
    
    public int getNumItems() {
        return barris.size();
    }

    public List<Barri> getAllBarris() {
        return barris;
    }
}