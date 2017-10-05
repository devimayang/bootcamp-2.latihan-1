/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devimayang27.perpus.controller;

import com.devimayang27.perpus.dao.BukuDao;
import com.devimayang27.perpus.model.Buku;
import javax.servlet.annotation.WebServlet;
import javax.servlet.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author Devi
 */
@WebServlet(urlPatterns={"/buku/list","/buku/"})
public class BukuListController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    //    super.doGet(req, resp); //To change body of generated methods, choose Tools | Templates.
   
        try {
            List<Buku> listBuku = new BukuDao().findAll();
            req.setAttribute("listBuku", listBuku);
        } catch (SQLException ex) {
            Logger.getLogger(BukuListController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         req.getRequestDispatcher("/pages/buku/listBuku.jsp").forward(req,resp);
    }
        
}
