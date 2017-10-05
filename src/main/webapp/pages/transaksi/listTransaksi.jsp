<%-- 
    Document   : listTransaksi
    Created on : Oct 5, 2017, 5:11:35 PM
    Author     : Devi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Daftar Belum Mengembalikan Buku</title>
    </head>
    <body>
        <table>
            <thead>
                <tr>
                    <td>No</td>
                    <td>Nama Pengunjung</td>
                    <td>Kode Buku</td>
                    <td>Nama Buku</td>
                    <td>Tanggal Pinjam</td>
                    <td>Aksi</td>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${listTransaksi}" var="t" varStatus="idx">
                    <tr>
                        <td>${idx.count}</td>
                        <td>${t.pengunjung.nama}</td>
                        <td>${t.buku.id}</td>
                        <td>${t.buku.judulBuku}</td>
                        <td>${t.tanggalPinjam}</td>
                        <td>
                            <c:if test ="${t.tanggalKembali!=null}">
                                <c:out value="'Sudah Dikembalikan'"/>
                            </c:if>
                            <c:if test ="${t.tanggalKembali==null}">
                                <a href ="${pageContext.servletContext.contextPath}/transaksi/kembalikan?kode=${t.id}">Kembalikan</a>
                            </c:if>
                        </td>                       
                    </tr>
                </c:forEach>

            </tbody>
        </table>
    </body>
</html>
