/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import pojo.Usuario;

/**
 * REST Web Service
 *
 * @author anderson
 */
@Path("generic")
public class GenericResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
    }

    /**
     * Retrieves representation of an instance of ws.GenericResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuario> getXml() {
     
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection c =  DriverManager.getConnection("jdbc:mysql://127.0.0.1/batepapo","batepapo","batepapo");
            PreparedStatement  p =  c.prepareStatement("select * from usuario");
            ResultSet r =  p.executeQuery() ;
            
            ArrayList<Usuario> ArrU = new ArrayList<Usuario>();
            
            while (r.next())
            {
                Usuario u = new Usuario();
                u.setId(r.getInt("id_usuario"));
                u.setLogin(r.getString("login"));
                u.setSenha(r.getString("senha"));
                ArrU.add(u);
            }

            return ArrU;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * PUT method for updating or creating an instance of GenericResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
    
    
        @GET
	@Path("{id}")
	public Usuario getUserById(@PathParam("id") String id) {
	      try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection c =  DriverManager.getConnection("jdbc:mysql://127.0.0.1/batepapo","batepapo","batepapo");
            PreparedStatement  p =  c.prepareStatement("select * from usuario where id_usuario= ? ");
            p.setInt(1,  Integer.parseInt(id) );
            ResultSet r =  p.executeQuery() ;
                    
            Usuario u = new Usuario();
            if (r.next())
            {
               
                u.setId(r.getInt("id_usuario"));
                u.setLogin(r.getString("login"));
                u.setSenha(r.getString("senha"));
                u.setFoto("uma foto qualquer");
            }

            return u;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

	}
        
        @GET
	@Path("/l/{login}")
	public ArrayList<Usuario> getUserByLogin(@PathParam("login") String login) {
	      try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection c =  DriverManager.getConnection("jdbc:mysql://127.0.0.1/batepapo","batepapo","batepapo");
            PreparedStatement  p =  c.prepareStatement("select * from usuario where login like ? ");
            p.setString(1,  '%'+ login + '%' );
            ResultSet r =  p.executeQuery() ;

            Usuario u ;
            ArrayList<Usuario> ArrU = new ArrayList<Usuario>();
            
            while (r.next())
            {
                u = new Usuario();
                u.setId(r.getInt("id_usuario"));
                u.setLogin(r.getString("login"));
                u.setSenha(r.getString("senha"));
                ArrU.add(u);
            }

            return ArrU;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

	}

    
    
}
