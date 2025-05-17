/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOdata_movie;
import java.sql.*;
import java.util.*;
import koneksi.connector;
import model.*;
import DAOImplement.data_movie_implement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maan
 */
public class data_movieDAO implements data_movie_implement{
    Connection connection;
    
    final String select = "SELECT * FROM data_movie";
    final String insert = "INSERT INTO data_movie (id, judul, alur, penokohan, akting) VALUES (null, ?, ?, ?, ?)";
    final String update  = "UPDATE data_movie SET judul = ?, alur = ?, penokohan = ?, akting = ? WHERE id = ?";
    final String delete = "DELETE FROM data_movie WHERE id = ?";
    
    public data_movieDAO() {
        connection = connector.connection();
    }

    @Override
    public void insert(data_movie p) {
        PreparedStatement statement = null;
        
        try {
            statement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, p.getJudul());
            statement.setDouble(2, p.getAlur());
            statement.setDouble(3, p.getPenokohan());
            statement.setDouble(4, p.getAkting());
            statement.executeUpdate();
            
            ResultSet rs = statement.getGeneratedKeys();
            while(rs.next()) {
                p.setId(rs.getInt(1));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<data_movie> getAll(data_movie p) {
        List<data_movie> dm = null;
        
        try {
            dm = new ArrayList<data_movie>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            
            while(rs.next()) {
                data_movie movie = new data_movie();
                movie.setId(rs.getInt("id"));
                movie.setJudul(rs.getString("judul"));
                movie.setAlur(rs.getFloat("alur"));
                movie.setPenokohan(rs.getFloat("penokohan"));
                movie.setAkting(rs.getFloat("akting"));
                
                dm.add(movie);
            }
        } catch (SQLException e) {
            Logger.getLogger(data_movieDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return dm;
    }

    @Override
    public void update(data_movie p) {
        PreparedStatement statement = null;
        
        try {
            statement = connection.prepareStatement(update, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, p.getJudul());
            statement.setDouble(2, p.getAlur());
            statement.setDouble(3, p.getPenokohan());
            statement.setDouble(4, p.getAkting());
            statement.setInt(5, p.getId());
            statement.executeUpdate();
            
            ResultSet rs = statement.getGeneratedKeys();
            while(rs.next()) {
                p.setId(rs.getInt(1));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(int id) {
        PreparedStatement statement = null;
        
        try {
            statement = connection.prepareStatement(delete, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, id);
            statement.executeUpdate();
            
            ResultSet rs = statement.getGeneratedKeys();
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
