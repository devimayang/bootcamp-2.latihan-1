/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devimayang27.perpus.dao;

import com.devimayang27.perpus.config.KoneksiDatabase;
import com.devimayang27.perpus.model.Buku;
import java.sql.*;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author Devi
 */
public class BukuDao {
    
    public void save() throws SQLException{
        KoneksiDatabase koneksiDB = new KoneksiDatabase();
        DataSource dataSource = koneksiDB.getDataSource();
        Connection connection = dataSource.getConnection();
        
        String sql = "insert into perpus.buku( judul_buku, tahun_terbit, pengarang, jumlah_buku) values(?,?,?,?)";
        PreparedStatement statement= connection.prepareStatement(sql);
        statement.setString(1, "Belajar Ngoding");
        statement.setInt(2,2017);
        statement.setString(3, "Asal ajaa");
        statement.setInt(4, 4);
        
        statement.executeUpdate();
        statement.close();
        connection.close();
    }
    
    public void update(){
        
    }
    
    public void delete(){
        
    }
    
    public List<Buku> findAll(){
        return null;
    }
    
    public Buku findById (Integer idBuku){
        return null;
    }
}
