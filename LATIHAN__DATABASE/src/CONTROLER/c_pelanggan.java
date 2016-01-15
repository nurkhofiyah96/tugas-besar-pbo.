package CONTROLER;

import java.sql.*;
import java.util.*;
import javax.swing.*;
import MODEL.m_pelanggan;

public class c_pelanggan {
    Connection koneksi = null;
    ResultSet rs=null;
    Statement st=null;
    String sql=null;
    
    public c_pelanggan()
    {
        try {
                 koneksi = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/penjualan","root","");
       st = koneksi.createStatement();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Koneksi Gagal..."+ e.getMessage());
        }
    }
    
    public ArrayList<MODEL.m_pelanggan> selecdata()
    {
        ArrayList<MODEL.m_pelanggan> al= new ArrayList<>();
        String sqlpelanggan = "select * from tbl_pelanggan";
        try {
            rs = st.executeQuery(sqlpelanggan);
            while (rs.next()){
                m_pelanggan m01 = new m_pelanggan();
                m01.setKodepelanggan(rs.getString("kodepelanggan"));
                m01.setNamapelanggan(rs.getString("namapelanggan"));
                m01.setAlamat(rs.getString("alamatpelanggan"));
                m01.setTeleponpelanggan(rs.getString("teleponpelanggan"));
                al.add(m01);               
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog (null, "Gagal Tampil "+e.getMessage());
        }   
        return al;   
    }
    
    public MODEL.m_pelanggan getdetail(String kodepelanggan)
    {
        m_pelanggan result = new m_pelanggan();
        String sql = "SELECT * FROM tbl_pelanggan WHERE kodepelanggan ='" + kodepelanggan + "'";
        try
        {
            rs = st.executeQuery(sql);
            while (rs.next())
            {
                result.setKodepelanggan(rs.getString("kodepelanggan"));
                result.setNamapelanggan(rs.getString("namapelanggan"));
                result.setAlamat(rs.getString("alamatpelanggan"));
                result.setTeleponpelanggan(rs.getString("teleponpelanggan"));
            }
        }
        catch (SQLException ex)
        {
            System.out.println(sql);
            JOptionPane.showMessageDialog(null, "Gagal : " + ex.getMessage());
        }
        return result;
    }
 
    public void insertdata (m_pelanggan data)
    {
        String sqlsimpan = "insert into tbl_pelanggan values('"
             + data.getKodepelanggan() +"',"
             +" '"+data.getNamapelanggan()+"',"
             +" '"+data.getAlamat()+"',"
             +" '"+data.getTeleponpelanggan()+"')";     
        try 
        {
            st.executeUpdate(sqlsimpan);
            JOptionPane.showMessageDialog(null,"Data Tersimpan...");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog (null, "Gagal Menyimpan...."+e.getMessage());
        }     
    }
    
    public void updatedata (m_pelanggan data)
    {
        String sqlsimpan = "update tbl_pelanggan set "
             +"namapelanggan = '"+data.getNamapelanggan()+"',"
             +"alamatpelanggan = '"+data.getAlamat()+"',"
             +"teleponpelanggan = '"+data.getTeleponpelanggan()+"'"
             +" where kodepelanggan = '" + data.getKodepelanggan() + "'";     
        try 
        {
            st.executeUpdate(sqlsimpan);
            JOptionPane.showMessageDialog(null,"Data Terupdate...");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog (null, "Gagal Menyimpan...."+e.getMessage());
        }     
    }
 
 
    public void deletedata (m_pelanggan data)         
    {
       String sqlhapus = "delete from tbl_pelanggan where kodepelanggan = '"+data.getKodepelanggan()+"'";
       try {
           st.executeUpdate(sqlhapus);
           JOptionPane.showMessageDialog (null, "DATA TERHAPUS....");
       }
       catch (SQLException e){
           JOptionPane.showMessageDialog (null, "Gagal Dihapus...."+e);
       }
    }
}
