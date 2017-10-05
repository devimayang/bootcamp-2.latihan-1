/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devimayang27.perpus.controller;

import com.devimayang27.perpus.dao.BukuDao;
import com.devimayang27.perpus.model.Buku;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Devi
 */
@WebServlet(urlPatterns ="/buku/edit")
public class BukuEditController extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
          //    super.doGet(req, resp); //To change body of generated methods, choose Tools | Templates.
        // Integer kodeBuku = Integer.valueOf(req.getParameter("kodeBuku"));
        try {
            Integer kodeBuku = Integer.valueOf(req.getParameter("kodeBuku"));
            Buku sebuahBuku = new BukuDao().findById(kodeBuku);
            
            req.setAttribute("buku", sebuahBuku);
            req.getRequestDispatcher("/pages/buku/editBuku.jsp").forward(req, resp);
        } catch (SQLException ex) {
            Logger.getLogger(BukuEditController.class.getName()).log(Level.SEVERE, null, ex);
        }  
  
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       // super.doPost(req, resp); //To change body of generated methods, choose Tools | Templates.
   Buku buku = new Buku();
       buku.setId(Integer.valueOf(req.getParameter("idBuku")));
       buku.setJudulBuku(req.getParameter("judulBuku"));
       buku.setTahunTerbit(Integer.valueOf(req.getParameter("tahunTerbit")));
       buku.setPengarang(req.getParameter("pengarang"));
       buku.setJumlahBuku(Integer.valueOf(req.getParameter("jumlahBuku")));
       
       BukuDao bukuDao = new BukuDao();
        try {
            bukuDao.update(buku);
        } catch (SQLException ex) {
            Logger.getLogger(BukuAddController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //cara redirect ada 2:
        //resp.sendRedirect(req.getServletContext().getContextPath()+"/buku/");
        resp.sendRedirect(new StringBuilder(req.getServletContext().getContextPath()).append("/buku/").toString());
    }
    
    
    
}
