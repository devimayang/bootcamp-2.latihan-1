/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devimayang27.perpus.model;

import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Devi
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaksi {
    private Integer id;
    private Buku buku;
    private Pengunjung pengunjung;
    private Date tanggalPinjam;
    private Date tanggalKembali;
    
}
