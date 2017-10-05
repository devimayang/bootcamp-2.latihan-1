/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devimayang27.perpus.dao;

import com.devimayang27.perpus.config.KoneksiDatabase;
import com.devimayang27.perpus.model.Buku;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author Devi
 */
public class BukuDao {
    
    public void save(Buku x) throws SQLException{
        KoneksiDatabase koneksiDB = new KoneksiDatabase();
        DataSource dataSource = koneksiDB.getDataSource();
        Connection connection = dataSource.getConnection();
        
        String sql = "insert into perpus.buku( judul_buku, tahun_terbit, pengarang, jumlah_buku) values(?,?,?,?)";
        PreparedStatement statement= connection.prepareStatement(sql);
        statement.setString(1, x.getJudulBuku());
        statement.setInt(2,x.getTahunTerbit());
        statement.setString(3, x.getPengarang());
        statement.setInt(4, x.getJumlahBuku());
        
        statement.executeUpdate();
        statement.close();
        connection.close();
    }
    
    public void update(){
        
    }
    
    public void delete(){
        
    }
    
    public List<Buku> findAll() throws SQLException{
        List<Buku> listBuku = new ArrayList<>();
        
        KoneksiDatabase koneksiDatabase = new KoneksiDatabase();
        DataSource dataSource = new KoneksiDatabase().getDataSource();
        Connection connection = dataSource.getConnection();
        
        String sql ="select id, judul_buku, tahun_terbit, pengarang, jumlah_buku from perpus.buku";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while(resultSet.next()){
            Buku buku = new Buku();
            buku.setId(resultSet.getInt("id"));
            buku.setJudulBuku(resultSet.getString("judul_buku"));
            buku.setPengarang(resultSet.getString("pengarang"));
            buku.setTahunTerbit(resultSet.getInt("tahun_terbit"));
            buku.setJumlahBuku(resultSet.getInt("jumlah_buku"));
            
            listBuku.add(buku);
        }
        resultSet.close();
        statement.close();
        connection.close();
        
        return listBuku;
    }
    
    public Buku findById (Integer idBuku){
        return null;
    }
}
