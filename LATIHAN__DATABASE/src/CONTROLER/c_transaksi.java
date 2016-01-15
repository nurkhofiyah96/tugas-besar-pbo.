/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLER;

import MODEL.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author Nanda
 */
public class c_transaksi {
    Connection koneksi = null;
    ResultSet rs=null;
    Statement st=null;
    String sql=null;
    
    public c_transaksi()
    {
        try {
                 koneksi = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/penjualan","root","");
       st = koneksi.createStatement();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Koneksi Gagal..."+ e.getMessage());
        }
    }
    
    public ArrayList<MODEL.transaksitabel> selecdata()
    {
        ArrayList<MODEL.transaksitabel> al= new ArrayList<>();
        String sqlbarang = "SELECT " +
            "    `tbl_transaksi`.`notransaksi`" +
            "    , `tbl_transaksi`.`tgl`" +
            "    , `tbl_transaksi`.`kodepelanggan`" +
            "    , `tbl_pelanggan`.`namapelanggan`" +
            "    , `tbl_barang`.`kodebarang`" +
            "    , `tbl_barang`.`namabarang`" +
            "    , `tbl_transaksi`.`harga`" +
            "    , `tbl_transaksi`.`jumlah`" +
            "    , `tbl_transaksi`.`total`" +
            "FROM" +
            "    `tbl_transaksi`" +
            "    INNER JOIN `tbl_pelanggan` " +
            "        ON (`tbl_transaksi`.`kodepelanggan` = `tbl_pelanggan`.`kodepelanggan`)" +
            "    INNER JOIN `tbl_barang` " +
            "        ON (`tbl_transaksi`.`kodebarang` = `tbl_barang`.`kodebarang`);";
        try {
            rs = st.executeQuery(sqlbarang);
            while (rs.next()){
                transaksitabel m01 = new transaksitabel();
                m01.setNotransaksi(rs.getString("notransaksi"));
                m01.setTgl(rs.getString("tgl"));
                m01.setKodepelanggan(rs.getString("kodepelanggan"));
                m01.setNamapelanggan(rs.getString("namapelanggan"));
                m01.setKodebarang(rs.getString("kodebarang"));
                m01.setNamabarang(rs.getString("namabarang"));
                m01.setHarga(rs.getString("harga"));
                m01.setJumlah(rs.getString("jumlah"));
                m01.setTotal(rs.getString("total"));
                al.add(m01);               
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog (null, "Gagal Tampil "+e.getMessage());
        }   
        return al;   
    }
 
    public void insertdata (m_transaksi data)
    {
        String sqlsimpan = "insert into tbl_transaksi values('"
             +data.getNotransaksi()+"',"
             +" '"+data.getTgl()+"',"
             +" '"+data.getKodepelanggan()+"',"
             +" '"+data.getKodebarang()+"',"
             +" '"+data.getHarga()+"',"
             +" '"+data.getJumlah()+"',"
             +" '"+data.getTotal()+"')";     
        try 
        {
            st.executeUpdate(sqlsimpan);
            JOptionPane.showMessageDialog(null,"Data Tersimpan...");
        } catch (SQLException e) {
            System.out.println(sqlsimpan);
            JOptionPane.showMessageDialog (null, "Gagal Menyimpan...."+e.getMessage());
        }     
    }
    

    public void deletedata (m_transaksi data)         
    {
       String sqlhapus = "delete from tbl_transaksi where notransaksi = '"+data.getNotransaksi()+"'";
       try {
           st.executeUpdate(sqlhapus);
           JOptionPane.showMessageDialog (null, "DATA TERHAPUS....");
       }
       catch (SQLException e){
           JOptionPane.showMessageDialog (null, "Gagal Dihapus...."+e);
       }
    }
   
}
