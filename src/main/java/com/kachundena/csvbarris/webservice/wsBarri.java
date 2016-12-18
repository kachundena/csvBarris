/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kachundena.csvbarris.webservice;

import com.kachundena.csvbarris.modelo.*;
import com.kachundena.csvbarris.util.*;
import static com.kachundena.csvbarris.controller.BarrisController.*;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.io.IOException;
import javax.ws.rs.DELETE;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;

/**
 * REST Web Service
 *
 * @author alex
 */
@Path("/ws")
@Api(value = "/ws", description = "Operaciones con CSV Barris")
@Produces(MediaType.APPLICATION_JSON)
public class wsBarri {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of wsPuntWifi
     */
    public wsBarri() {
    }

    /**
     * Retrieves representation of an instance of com.kachundena.csvbarri.webservice.wsBarri
     * @return an instance of java.lang.String
     */

    
    @GET
    @Path("/lista")
    @ApiOperation(
            value = "Todos los barrios",
            notes = "Devuelve todos los barrios"
    )
    @Produces(MediaType.APPLICATION_JSON)
    public List<Barri> getListaBarri() throws IOException {
        List<Barri> retorno = new ArrayList<Barri>();
        try {
            // Importar valores de CSV a lista
            Barris Lista = importCSV(Constantes.FILENAME_CSV_URL,Constantes.SEPARATOR_COL,Constantes.CODEPAGE);
            // Retornar todos los valores de la lista
            retorno = Lista.getAllBarris();
        }
        catch (Exception e) {
            System.console().printf(e.toString());
        }
        return retorno;
    }    
    
    @GET
    @Path("/barri/{linea}")
    @ApiOperation(
            value = "retornar barrio",
            notes = "Devuelve un barrio concreto de una linea"
    )
    @Produces(MediaType.APPLICATION_JSON)
    public Barri getBarri(
            @ApiParam(value = "Linea Bsrrio", allowableValues = "range[1," + Integer.MAX_VALUE + "]", required = true)
            @PathParam("linea") int linea) {
         Barri retorno = new Barri();
        try {
            // Importar valores de CSV a lista
            Barris Lista = importCSV(Constantes.FILENAME_CSV_URL,Constantes.SEPARATOR_COL,Constantes.CODEPAGE);
            // retornar la linea seleccionada
            retorno = Lista.getBarri(linea);           
        }
        catch (Exception e) {
            System.console().printf(e.toString());
        }
        return retorno;
  
    }
    
    
        @GET
    @Path("/districte/{distrito}")
    @ApiOperation(
            value = "retornar lista barrios de un distrito",
            notes = "Devuelve los barrios que hay en un distrito"
    )
    @Produces(MediaType.APPLICATION_JSON)
    public List<Barri> getBarrideDistricte(
            @ApiParam(value = "Código de distrito", allowableValues = "range[1," + Integer.MAX_VALUE + "]", required = true)
            @PathParam("distrito") int districte) {
        List<Barri> retorno = new ArrayList<Barri>();
        try {
            // Importar valores de CSV a lista
            Barris lista = importCSV(Constantes.FILENAME_CSV_URL,Constantes.SEPARATOR_COL,Constantes.CODEPAGE);
            // Retornar todos los valores de la lista
            retorno = lista.getBarrisdeDistricte(districte);
        }
        catch (Exception e) {
            System.console().printf(e.toString());
        }
        return retorno;
  
    }

    
    @POST
    @Path("/add")
    @ApiOperation(
            value = "añadir barrio",
            notes = "Añade un barrio al final de fichero"
    )
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean addBarri(Barri barri, 
                            @HeaderParam("authorization") String keyString) {
         try {
            if(!Utilities.isCorrectKey(keyString)) {
                return false;
            }
            // Importar valores de CSV a lista
            Barris Lista = importCSV(Constantes.FILENAME_CSV_URL,Constantes.SEPARATOR_COL,Constantes.CODEPAGE);
            // Añadir el nuevo barrio a la lista. Al final de esta.
            Lista.addBarri(barri);
            // Exportar la lista al CSV
            exportCSV(Lista,Constantes.FILENAME_CSV,Constantes.SEPARATOR_COL,Constantes.CODEPAGE);
            return true;
        }
        catch (Exception e) {
            System.console().printf(e.toString());
            return false;
        }
       
    }

    @PUT
    @Path("/update")
    @ApiOperation(
            value = "actualiza un barrio",
            notes = "Actualiza un barrio concreto"
    )
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean updateBarri(Barri barri, 
                            @HeaderParam("authorization") String keyString) {
        try {
            if(!Utilities.isCorrectKey(keyString)) {
                return false;
            }
            // Importar valores de CSV a lista
            Barris Lista = importCSV(Constantes.FILENAME_CSV_URL,Constantes.SEPARATOR_COL,Constantes.CODEPAGE);
            // Editar el barrio de la linea. Para ello cogemos la linea del punto WIFI del parámetro
            Lista.editBarri(barri.getLinea(), barri);
            // Exportar la lista al CSV
            exportCSV(Lista,Constantes.FILENAME_CSV,Constantes.SEPARATOR_COL,Constantes.CODEPAGE);
            return true;
        }
        catch (Exception e) {
            System.console().printf(e.toString());
            return false;
        }
        
    }

    @DELETE
    @Path("/delete/{linea}")
    @ApiOperation(
            value = "borrar un barrio",
            notes = "Borra un barrio de la linea especificada"
    )
    @Produces(MediaType.APPLICATION_JSON)
    public boolean deleteBarri(
            @ApiParam(value = "Linea Barrio", allowableValues = "range[1," + Integer.MAX_VALUE + "]", required = true)
            @PathParam("linea") int Linea, 
            @HeaderParam("authorization") String keyString) {
        try {
            if(!Utilities.isCorrectKey(keyString)) {
                return false;
            }
            // Importar valores de CSV a lista
            Barris Lista = importCSV(Constantes.FILENAME_CSV_URL,Constantes.SEPARATOR_COL,Constantes.CODEPAGE);
            // Borrar la entrada de la lista a partir de la linea indicada en el parámetro
            Lista.deleteBarri(Linea);
            // Exportar al CSV el resto de la lista
            exportCSV(Lista,Constantes.FILENAME_CSV,Constantes.SEPARATOR_COL,Constantes.CODEPAGE);
            return true;
        }
        catch (Exception e) {
            System.console().printf(e.toString());
            return false;
        }
    }
    
}
