/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devimayang27.perpus.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Devi
 */
@Data 
@NoArgsConstructor
@AllArgsConstructor
public class Buku {
    
    private Integer id;
    private String judulBuku;
    private Integer tahunTerbit;
    private String pengarang;
    private Integer jumlahBuku;

    
}
