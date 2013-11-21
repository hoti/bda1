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
import java.util.Date;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import javax.persistence.PersistenceUnit;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.annotation.Resource;
import javax.persistence.LockModeType;
import javax.persistence.NoResultException;
import javax.transaction.UserTransaction;

/**
 *
 * @author GaspardP <gaspardp@kth.se>
 */
@WebServlet(name = "RendreExemplaireEmprunteServlet", urlPatterns = {"/RendreExemplaireEmprunte"})
public class RendreExemplaireEmprunteServlet extends HttpServlet {

    @PersistenceUnit
    //The emf corresponding to 
    private EntityManagerFactory emf;
    @Resource
    private UserTransaction utx;

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
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
            Long exemplaireTxt = Long.valueOf((String) request.getParameter("exemplaire")).longValue();

            Exemplaire exemplaire = null;
            try {
                exemplaire = (Exemplaire) em.createNamedQuery("findExemplaire", Exemplaire.class).setParameter("id", exemplaireTxt).getSingleResult();
            } catch (NoResultException e) {
            }
            em.lock(exemplaire,LockModeType.OPTIMISTIC);
            if (exemplaire.getProduit().getAdherentsDemandeurs().size() <= 0) {
                exemplaire.setStatut(Statut.DISPONIBLE);
            } else {
                exemplaire.setStatut(Statut.RESERVE);
                Adherent adherentDemandeur = exemplaire.getProduit().getAdherentsDemandeurs().get(0);
                exemplaire.setReservePourCetAdherent(adherentDemandeur);

                exemplaire.getProduit().getAdherentsDemandeurs().remove(0);
            }

            exemplaire.setEmprunteurActuel(null);

            exemplaire.setDateDebutEmprunt(null);
            exemplaire.setDateFinEmprunt(null);
            //persist the person entity
            em.persist(exemplaire);


            em.refresh(exemplaire,LockModeType.OPTIMISTIC);


            //commit transaction which will trigger the em to 
            //commit newly created entity into database
            utx.commit();

            //Forward to ListPerson servlet to list persons along with the newly
            //created person above
            request.getRequestDispatcher("ListExemplaire").forward(request, response);
        } catch (Exception ex) {
            throw new ServletException(ex);
        } finally {
            //close the em to release any resources held up by the persistebce provider
            if (em != null) {
                em.close();
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     */
    public String getServletInfo() {
        return "Short description";
    }
    // </editor-fold>
}
