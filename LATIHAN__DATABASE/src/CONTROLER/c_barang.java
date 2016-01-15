/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLER;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import MODEL.*;
//import sun.org.mozilla.javascript.internal.ast.TryStatement;
/**
 *
 * @author Nanda
 */
public class c_barang {
    Connection koneksi = null;
    ResultSet rs=null;
    Statement st=null;
    String sql=null;
    
    public c_barang()
    {
        try {
                 koneksi = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/penjualan","root","");
       st = koneksi.createStatement();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Koneksi Gagal..."+ e.getMessage());
        }
    }
    
    public ArrayList<MODEL.m_barang> selecdata()
    {
        ArrayList<MODEL.m_barang> al= new ArrayList<>();
        String sqlbarang = "select *from tbl_barang";
        try {
            rs = st.executeQuery(sqlbarang);
            while (rs.next()){
                m_barang m01 = new m_barang();
                m01.setKodebarang(rs.getString("KodeBarang"));
                m01.setNamabarang(rs.getString("NamaBarang"));
                m01.setSatuanbarang(rs.getString("SatuanBarang"));
                m01.setHargabeli(rs.getInt("Hargabeli"));
                m01.setHargajual(rs.getInt("Hargajual"));
                m01.setStokbarang(rs.getInt("Stokbarang"));
                al.add(m01);               
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog (null, "Gagal Tampil "+e.getMessage());
        }   
        return al;   
    }
 
    public void insertdata (m_barang data)
    {
        String sqlsimpan = "insert into tbl_barang values('"
             +data.getKodebarang()+"',"
             +" '"+data.getNamabarang()+"',"
             +" '"+data.getSatuanbarang()+"',"
             +" '"+data.getHargabeli()+"',"
             +" '"+data.getHargajual()+"',"
             +" '"+data.getStokbarang()+"')";     
        try 
        {
            st.executeUpdate(sqlsimpan);
            JOptionPane.showMessageDialog(null,"Data Tersimpan...");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog (null, "Gagal Menyimpan...."+e.getMessage());
        }     
    }
    
    public void updatedata (m_barang data)
    {
        String sqlsimpan = "update tbl_barang set "
             +"namabarang = '"+data.getNamabarang()+"',"
             +"satuanbarang = '"+data.getSatuanbarang()+"',"
             +"hargabeli = '"+data.getHargabeli()+"',"
             +"hargajual = '"+data.getHargajual()+"',"
             +"stokbarang = '"+data.getStokbarang()+"'"
             +" where kodebarang = '" + data.getKodebarang() + "'";     
        try 
        {
            st.executeUpdate(sqlsimpan);
            JOptionPane.showMessageDialog(null,"Data Terupdate...");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog (null, "Gagal Menyimpan...."+e.getMessage());
        }     
    }
 
 
    public void deletedata (m_barang data)         
    {
       String sqlhapus = "delete from tbl_barang where kodebarang = '"+data.getKodebarang()+"'";
       try {
           st.executeUpdate(sqlhapus);
           JOptionPane.showMessageDialog (null, "DATA TERHAPUS....");
       }
       catch (Exception e){
           JOptionPane.showMessageDialog (null, "Gagal Dihapus...."+e);
       }
    }
    
    public MODEL.m_barang getdetail(String kodebarang)
    {
        m_barang result = new m_barang();
        String sql = "SELECT * FROM tbl_barang WHERE kodebarang ='" + kodebarang + "'";
        try
        {
            rs = st.executeQuery(sql);
            while (rs.next())
            {
                result.setKodebarang(rs.getString("kodebarang"));
                result.setNamabarang(rs.getString("namabarang"));
                result.setSatuanbarang(rs.getString("satuanbarang"));
                result.setHargabeli(rs.getInt("hargabeli"));
                result.setHargajual(rs.getInt("hargajual"));
                result.setStokbarang(rs.getInt("stokbarang"));
            }
        }
        catch (SQLException ex)
        {
            System.out.println(sql);
            JOptionPane.showMessageDialog(null, "Gagal : " + ex.getMessage());
        }
        return result;
    }
}