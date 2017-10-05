/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devimayang27.perpus.dao;

import com.devimayang27.perpus.config.KoneksiDatabase;
import com.devimayang27.perpus.model.Buku;
import com.devimayang27.perpus.model.Pengunjung;
import com.devimayang27.perpus.model.Transaksi;
import static java.nio.file.Files.list;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author Devi
 */
public class TransaksiDao {

    public void pinjamBuku(Transaksi x) throws SQLException {
        KoneksiDatabase koneksiDatabase = new KoneksiDatabase();
        DataSource dataSource = new KoneksiDatabase().getDataSource();
        Connection connection = dataSource.getConnection();
        connection.setAutoCommit(false);

        String sql = "insert into perpus.transaksi(buku_id, pengunjung_id, tanggal_pinjam) values (?,?,now())";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, x.getBuku().getId());
        preparedStatement.setInt(2, x.getPengunjung().getId());

        preparedStatement.executeUpdate();
        preparedStatement.close();

        sql = "update perpus.buku set jumlah_buku=? where id=?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, x.getBuku().getJumlahBuku() - 1);
        preparedStatement.setInt(2, x.getBuku().getId());
        preparedStatement.executeUpdate();
        preparedStatement.close();

        connection.commit();
        connection.close();
    }

    public List<Transaksi> daftarTransaksi() throws SQLException {
        KoneksiDatabase koneksiDatabase = new KoneksiDatabase();
        DataSource dataSource = new KoneksiDatabase().getDataSource();
        Connection connection = dataSource.getConnection();

        String sql = "SELECT \n"
                + "b.id as id_buku,\n"
                + "b.judul_buku as judul_buku,\n"
                + "b.jumlah_buku as jumlah_buku,\n"
                + "b.tahun_terbit as tahun_terbit,\n"
                + "b.pengarang as pengarang,\n"
                + "p.id as id_pengunjung,\n"
                + "p.nama as nama_pengunjung,\n"
                + "p.alamat as alamat,\n"
                + "t.id as id_transaksi,\n"
                + "t.tanggal_pinjam as tanggal_pinjam,\n"
                + "t.tanggal_kembali as tanggal_kembali\n"
                + "from perpus.buku b\n"
                + "join perpus.transaksi t on b.id=t.buku_id\n"
                + "join perpus.pengunjung p on t.pengunjung_id=p.id";

        List<Transaksi> listTransaksi = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            Transaksi transaksi = new Transaksi();

            transaksi.setBuku(new Buku(
                    resultSet.getInt("id_buku"),
                    resultSet.getString("judul_buku"),
                    resultSet.getInt("tahun_terbit"),
                    resultSet.getString("pengarang"),
                    resultSet.getInt("jumlah_buku")
            ));

            transaksi.setPengunjung(new Pengunjung(
                    resultSet.getInt("id_pengunjung"),
                    resultSet.getString("nama_pengunjung"),
                    resultSet.getString("alamat")
            ));

            transaksi.setId(resultSet.getInt("id_transaksi"));
            transaksi.setTanggalPinjam(resultSet.getDate("tanggal_pinjam"));
            transaksi.setTanggalKembali(resultSet.getDate("tanggal_kembali"));

            listTransaksi.add(transaksi);
        }

        resultSet.close();
        statement.close();
        connection.close();

        return listTransaksi;
    }

}
