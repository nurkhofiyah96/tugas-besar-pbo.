package CONTROLER;

import MODEL.m_suplier;
import java.sql.*;
import java.util.*;
import javax.swing.*;

public class c_suplier {
    Connection koneksi = null;
    ResultSet rs=null;
    Statement st=null;
    String sql=null;
    
    public c_suplier()
    {
        try {
                 koneksi = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/penjualan","root","");
       st = koneksi.createStatement();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Koneksi Gagal..."+ e.getMessage());
        }
    }
    
    public ArrayList<MODEL.m_suplier> selecdata()
    {
        ArrayList<MODEL.m_suplier> al= new ArrayList<>();
        String sqlsuplier = "select *from tbl_suplier";
        try {
            rs = st.executeQuery(sqlsuplier);
            while (rs.next()){
                m_suplier m01 = new m_suplier();
                m01.setKodesuplier(rs.getString("kodesuplier"));
                m01.setNamasuplier(rs.getString("namasuplier"));
                m01.setAlamatsuplier(rs.getString("alamatsuplier"));
                m01.setTeleponsuplier(rs.getString("teleponsuplier"));
                al.add(m01);               
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog (null, "Gagal Tampil "+e.getMessage());
        }   
        return al;   
    }
 
    public void insertdata (m_suplier data)
    {
        String sqlsimpan = "insert into tbl_suplier values('"
             + data.getKodesuplier() +"',"
             +" '"+data.getNamasuplier()+"',"
             +" '"+data.getAlamatsuplier()+"',"
             +" '"+data.getTeleponsuplier()+"')";     
        try 
        {
            st.executeUpdate(sqlsimpan);
            JOptionPane.showMessageDialog(null,"Data Tersimpan...");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog (null, "Gagal Menyimpan...."+e.getMessage());
        }     
    }
    
    public void updatedata (m_suplier data)
    {
        String sqlsimpan = "update tbl_suplier set "
             +"namasuplier = '"+data.getNamasuplier()+"',"
             +"alamatsuplier = '"+data.getAlamatsuplier()+"',"
             +"teleponsuplier = '"+data.getTeleponsuplier()+"'"
             +" where kodesuplier = '" + data.getKodesuplier() + "'";     
        try 
        {
            st.executeUpdate(sqlsimpan);
            JOptionPane.showMessageDialog(null,"Data Terupdate...");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog (null, "Gagal Menyimpan...."+e.getMessage());
        }     
    }
 
 
    public void deletedata (m_suplier data)         
    {
       String sqlhapus = "delete from tbl_suplier where kodesuplier = '"+data.getKodesuplier()+"'";
       try {
           st.executeUpdate(sqlhapus);
           JOptionPane.showMessageDialog (null, "DATA TERHAPUS....");
       }
       catch (SQLException e){
           JOptionPane.showMessageDialog (null, "Gagal Dihapus...."+e);
       }
    }
}
