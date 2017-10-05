/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devimayang27.perpus.dao;

import com.devimayang27.perpus.config.KoneksiDatabase;
import com.devimayang27.perpus.model.Transaksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.sql.DataSource;

/**
 *
 * @author Devi
 */
public class TransaksiDao {
    public void pinjamBuku(Transaksi x) throws SQLException{
        KoneksiDatabase koneksiDatabase = new KoneksiDatabase();
        DataSource dataSource = new KoneksiDatabase().getDataSource();
        Connection connection = dataSource.getConnection();
        connection.setAutoCommit(false);
        
        String sql="insert into perpus.transaksi(buku_id, pengunjung_id, tanggal_pinjam) values (?,?,now())";
        
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, x.getBuku().getId());
        preparedStatement.setInt(2, x.getPengunjung().getId());
        
        preparedStatement.executeUpdate();
        preparedStatement.close();
        
        sql="update perpus.buku set jumlah_buku=? where id=?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, x.getBuku().getJumlahBuku() -1);
        preparedStatement.setInt(2, x.getBuku().getId());
        preparedStatement.executeUpdate();
        preparedStatement.close();
        
        connection.commit();
        connection.close();
    }
}
