/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devimayang27.perpus.controller;

import com.devimayang27.perpus.dao.BukuDao;
import com.devimayang27.perpus.dao.PengunjungDao;
import com.devimayang27.perpus.dao.TransaksiDao;
import com.devimayang27.perpus.model.Buku;
import com.devimayang27.perpus.model.Pengunjung;
import com.devimayang27.perpus.model.Transaksi;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
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
@WebServlet(urlPatterns="/transaksi/pinjam")
public class TransaksiAddController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            //    super.doGet(req, resp); //To change body of generated methods, choose Tools | Templates.
            List<Buku> listBuku = new BukuDao().findAll();
            List<Pengunjung> listPengunjung = new PengunjungDao().findAll();
            
            req.setAttribute("listBuku", listBuku);
            req.setAttribute("listPengunjung", listPengunjung);
            req.getRequestDispatcher("/pages/transaksi/addTransaksi.jsp").forward(req, resp);
        } catch (SQLException ex) {
            Logger.getLogger(TransaksiAddController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    //    super.doPost(req, resp); //To change body of generated methods, choose Tools | Templates.
    
       Transaksi transaksi = new Transaksi();
        try {
            
             Integer bukuId= Integer.valueOf(req.getParameter("bukuId"));
             transaksi.setBuku(new BukuDao().findById(bukuId));
            
             
             Integer pengunjungId= Integer.valueOf(req.getParameter("pengunjungId"));
             transaksi.setPengunjung(new PengunjungDao().findById(pengunjungId));
             
             new TransaksiDao().pinjamBuku(transaksi);
             
             resp.sendRedirect(new StringBuilder(req.getServletContext().getContextPath()).append("/transaksi/list").toString());
        } catch (SQLException ex) {
            Logger.getLogger(TransaksiAddController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       
       
    }
 
    
}
