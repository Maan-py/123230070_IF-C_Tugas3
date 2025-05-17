/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import java.util.List;
import DAOdata_movie.data_movieDAO;
import DAOImplement.data_movie_implement;
import javax.swing.JOptionPane;
import model.*;
import view.MainView;

/**
 *
 * @author Maan
 */
public class movie_data_controller {
    MainView frame;
    data_movie_implement implDataMovie;
    List<data_movie> dm;
    
    public movie_data_controller(MainView frame) {
        this.frame = frame;
        implDataMovie = new data_movieDAO();
        dm = implDataMovie.getAll(new data_movie());
    }
    
    public void isiTabel() {
        dm = implDataMovie.getAll(new data_movie());
        model_tabel_data_movie mp = new model_tabel_data_movie(dm);
        frame.getTabelDataMovie().setModel(mp);
    }
    
    public void insert() {
        data_movie dm = new data_movie();
        
        if(frame.getjTextJudul().getText().isEmpty() || frame.getjTextAlur().getText().isEmpty() || frame.getjTextPenokohan().getText().isEmpty() || frame.getjTextAkting().getText().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Semua input data harus diisi", "Kesalahan", JOptionPane.WARNING_MESSAGE);
            return;
        } else {
            dm.setJudul(frame.getjTextJudul().getText());
            dm.setAlur(Double.parseDouble(frame.getjTextAlur().getText()));
            dm.setPenokohan(Double.parseDouble(frame.getjTextPenokohan().getText()));
            dm.setAkting(Double.parseDouble(frame.getjTextAkting().getText()));
            implDataMovie.insert(dm);
        }
    }
    
//    public void insert() {
//    // Ambil nilai dari text field
//    String judul = frame.getjTextJudul().getText().trim();
//    String alurText = frame.getjTextAlur().getText().trim();
//    String penokohanText = frame.getjTextPenokohan().getText().trim();
//    String aktingText = frame.getjTextAkting().getText().trim();
//
//    // Cek apakah ada field yang kosong
//    if (judul.isEmpty() || alurText.isEmpty() || penokohanText.isEmpty() || aktingText.isEmpty()) {
//        JOptionPane.showMessageDialog(frame, "Semua field harus diisi.", "Kesalahan", JOptionPane.WARNING_MESSAGE);
//        return; // Hentikan eksekusi jika ada field kosong
//    }
//
//    // Cek apakah alur, penokohan, dan akting adalah angka
//    double alur, penokohan, akting;
//
//    try {
//        alur = Double.parseDouble(alurText);
//        penokohan = Double.parseDouble(penokohanText);
//        akting = Double.parseDouble(aktingText);
//    } catch (NumberFormatException e) {
//        JOptionPane.showMessageDialog(frame, "Input untuk Alur, Penokohan, dan Akting harus berupa angka.", "Kesalahan", JOptionPane.ERROR_MESSAGE);
//        return; // Hentikan eksekusi jika format angka tidak valid
//    }
//
//    // Jika validasi lolos, maka lanjutkan proses insert
//    data_movie dm = new data_movie();
//    dm.setJudul(judul);
//    dm.setAlur(alur);
//    dm.setPenokohan(penokohan);
//    dm.setAkting(akting);
//
//    implDataMovie.insert(dm);
//
//    JOptionPane.showMessageDialog(frame, "Data berhasil ditambahkan.", "Sukses", JOptionPane.INFORMATION_MESSAGE);
//
//    // Debugging: Menampilkan data yang diinput
//    System.out.println("Judul: " + judul);
//    System.out.println("Alur: " + alur);
//    System.out.println("Penokohan: " + penokohan);
//    System.out.println("Akting: " + akting);
//}
    
    public void update() {
        int baris = frame.getTabelDataMovie().getSelectedRow();
        if(baris != -1) {
            data_movie movie = dm.get(baris);
            
//            int konfirmasi = JOptionPane.showConfirmDialog(frame, "Apakah anda yakin ingin mengupdate movie berjudul " + frame.getjTextJudul().getText() + "?", "Konfirmasi Edit", JOptionPane.YES_NO_OPTION);
  
                movie.setJudul(frame.getjTextJudul().getText());
                movie.setAlur(Double.parseDouble(frame.getjTextAlur().getText()));
                movie.setPenokohan(Double.parseDouble(frame.getjTextPenokohan().getText()));
                movie.setAkting(Double.parseDouble(frame.getjTextAkting().getText()));
                movie.setId(movie.getId());
                implDataMovie.update(movie);
                
                frame.getjTextJudul().setText("");
                frame.getjTextAlur().setText("");
                frame.getjTextPenokohan().setText("");
                frame.getjTextAkting().setText("");
                
                JOptionPane.showMessageDialog(frame, "Movie berjudul berhasil diupdate", "Berhasil", JOptionPane.INFORMATION_MESSAGE);
        } else {
                JOptionPane.showMessageDialog(frame, "Pilih data yang ingin diupdate", "Kesalahan", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void delete() {
        int baris = frame.getTabelDataMovie().getSelectedRow();;
        
        if(baris != -1) {
            data_movie movie = dm.get(baris);
            int id = movie.getId();
            
            int konfirmasi = JOptionPane.showConfirmDialog(frame, "Apakah anda yakin ingin menghapus movie berjudul " + frame.getjTextJudul().getText() + "?", "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
            
            if(konfirmasi == JOptionPane.YES_OPTION) {
                implDataMovie.delete(id);
                
                JOptionPane.showMessageDialog(frame, "Movie berjudul " + frame.getjTextJudul().getText() + " berhasil dihapus", "Berhasil", JOptionPane.INFORMATION_MESSAGE);
            } else {
                frame.getjTextJudul().setText("");
                frame.getjTextAlur().setText("");
                frame.getjTextPenokohan().setText("");
                frame.getjTextAkting().setText("");
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Pilih data yang ingin dihapus", "Kesalahan", JOptionPane.WARNING_MESSAGE);
        }
    }
}
