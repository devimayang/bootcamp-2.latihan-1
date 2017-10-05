<%-- 
    Document   : listBuku
    Created on : Oct 5, 2017, 9:38:02 AM
    Author     : Devi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Daftar Buku</title>
    </head>
    <body>
        <h1>Tabel Buku</h1>
        <table>
            <thead>
                <tr>
                <td>No</td>
                <td>Judul Buku</td>
                <td>Nama Pengarang</td>
                <td>Tahun Terbit</td>
                <td>Jumlah Buku</td>
                <td>Aksi</td>
                </tr>
            </thead>
            <tbody>                 
                <c:forEach items="${listBuku}" var="sebuahBuku" varStatus="index">
                <tr>
                <td>${index.count}</td>
                <td>${sebuahBuku.judulBuku}</td>
                <td>${sebuahBuku.pengarang}</td>
                <td>${sebuahBuku.tahunTerbit}</td>
                <td>${sebuahBuku.jumlahBuku}</td>
                <td>
                    <a href="${pageContext.servletContext.contextPath}/buku/delete?kodeBuku=${sebuahBuku.id}">Hapus</a>
                </td>
                </tr>
                </c:forEach>
                
            </tbody>
        </table>
        
    </body>
</html>
