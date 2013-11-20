/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bda1.servlet;

import bda1.entity.*;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import javax.persistence.PersistenceUnit;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.annotation.Resource;
import javax.persistence.NoResultException;
import javax.transaction.UserTransaction;

/**
 *
 * @author GaspardP <gaspardp@kth.se>
 */
@WebServlet(name="AddProduitServlet", urlPatterns={"/AddProduit"})
public class AddProduitServlet extends HttpServlet {
    
    @PersistenceUnit
    //The emf corresponding to 
    private EntityManagerFactory emf;  
    
    @Resource
    private UserTransaction utx;

    
    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException {
        assert emf != null;  //Make sure injection went through correctly.
        EntityManager em = null;
        try {
            
            //begin a transaction
            utx.begin();
            //create an em. 
            //Since the em is created inside a transaction, it is associsated with 
            //the transaction
            
            em = emf.createEntityManager();
            
            
            //Get the data from user's form
            String titre = (String) request.getParameter("titre");
            Boolean peutEtreReemprunter   =  Boolean.valueOf(request.getParameter("peutEtreReemprunter"));
            String typeString = (String) request.getParameter("type");

            
            ProduitType type=null;
            for(ProduitType t:ProduitType.values())
            {
                if(t.toString().equals(typeString))
                {
                    type=t;
                }
            }
            
            Date datePublication = new Date();

            
            String auteur1Txt = (String) request.getParameter("auteur1");
            String auteur2Txt = (String) request.getParameter("auteur2");
            String auteur3Txt = (String) request.getParameter("auteur3");
            
            Auteur auteur1 =null;
            try {
             auteur1 = (Auteur) em.createNamedQuery("findAuteur",Auteur.class).setParameter("nom", auteur1Txt).getSingleResult();
            }
            catch (NoResultException e)
            {

            }
            Auteur auteur2 =null;
            try {
             auteur2 = (Auteur) em.createNamedQuery("findAuteur",Auteur.class).setParameter("nom", auteur2Txt).getSingleResult();
            }
            catch (NoResultException e)
            {

            }
            Auteur auteur3 =null;
            try {
             auteur3 = (Auteur) em.createNamedQuery("findAuteur",Auteur.class).setParameter("nom", auteur3Txt).getSingleResult();
            }
            catch (NoResultException e)
            {

            }
            List<Auteur> auteurs=new ArrayList<Auteur>();
            if(auteur1!=null)
                auteurs.add(auteur1);
            if(auteur2!=null)
                auteurs.add(auteur2);
            if(auteur3!=null)
                auteurs.add(auteur3);
            
            
            String genre1Txt = (String) request.getParameter("genre1");
            String genre2Txt = (String) request.getParameter("genre2");
            String genre3Txt = (String) request.getParameter("genre3");
            
            Genre genre1 =null;
            try {
             genre1 = (Genre) em.createNamedQuery("findGenre",Genre.class).setParameter("nom", genre1Txt).getSingleResult();
            }
            catch (NoResultException e)
            {

            }
            Genre genre2 =null;
            try {
             genre2 = (Genre) em.createNamedQuery("findGenre",Genre.class).setParameter("nom", genre2Txt).getSingleResult();
            }
            catch (NoResultException e)
            {

            }
            Genre genre3 =null;
            try {
             genre3 = (Genre) em.createNamedQuery("findGenre",Genre.class).setParameter("nom", genre3Txt).getSingleResult();
            }
            catch (NoResultException e)
            {

            }
            List<Genre> genres=new ArrayList<Genre>();
            if(genre1!=null)
                genres.add(genre1);
            if(genre2!=null)
                genres.add(genre2);
            if(genre3!=null)
                genres.add(genre3);
            
            //Create a person instance out of it
            Produit produit = new Produit(titre, peutEtreReemprunter,0,datePublication,auteurs,genres,type);
            
           
            
            
            //persist the person entity
            em.persist(produit);
            
            //commit transaction which will trigger the em to 
            //commit newly created entity into database
            utx.commit();
            
            //Forward to ListPerson servlet to list persons along with the newly
            //created person above
            request.getRequestDispatcher("ListProduit").forward(request, response);
        } catch (Exception ex) {
            throw new ServletException(ex);
        } finally {
            //close the em to release any resources held up by the persistebce provider
            if(em != null) {
                em.close();
            }
        }
    }
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }
    
    /** Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }
    
    /** Returns a short description of the servlet.
     */
    public String getServletInfo() {
        return "Short description";
    }
    // </editor-fold>
}
