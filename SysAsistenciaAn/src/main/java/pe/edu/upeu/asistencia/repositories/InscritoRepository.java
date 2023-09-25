/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.upeu.asistencia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upeu.asistencia.models.Actividad;
import pe.edu.upeu.asistencia.models.Inscrito;

import java.util.Optional;

/**
 *
 * @author DELL
 */
@Repository
public interface InscritoRepository extends JpaRepository<Inscrito, Long>{
    Optional<Inscrito> findByNombreInscrito(String nombreinscrito);
    
}
