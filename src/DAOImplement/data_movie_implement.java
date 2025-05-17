/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAOImplement;
import java.util.List;
import model.*;

/**
 *
 * @author Maan
 */
public interface data_movie_implement {
    public void insert(data_movie p);
    public List<data_movie> getAll(data_movie p);
    public void update(data_movie p);
    public void delete(int id);
}
