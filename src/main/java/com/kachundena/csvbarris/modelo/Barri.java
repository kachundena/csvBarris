package com.kachundena.csvbarris.modelo;

public class Barri {

	private int linea_;
	private int barri_;
        private int districte_;
	private String nombre_;

	/**
	 * 
	 * @param linea
	 * @param barri
         * @param districte
	 * @param nombre
	 */
	public Barri(int linea, int barri, int districte, String nombre) {
            this.linea_ = linea;
            this.barri_ = barri;
            this.districte_ = districte;
            throw new UnsupportedOperationException();
	}

        public Barri() {
	}
        
	public int getLinea() {
            return this.linea_;
	}

	/**
	 * 
	 * @param linea
	 */
	public void setLinea(int linea) {
            this.linea_ = linea;
        }

	public int getBarri() {
            return this.barri_;
	}

	/**
	 * 
	 * @param barri
	 */
	public void setBarri(int barri) {
            this.barri_ = barri;
        }

	public int getDistricte() {
            return this.districte_;
	}

	/**
	 * 
	 * @param districte
	 */
	public void setDistricte(int districte) {
            this.districte_ = districte;
        }

        
	public String getNombre() {
            return this.nombre_;
	}

	/**
	 * 
	 * @param nombre
	 */
	public void setNombre(String nombre) {
            this.nombre_ = nombre;
	}


}
