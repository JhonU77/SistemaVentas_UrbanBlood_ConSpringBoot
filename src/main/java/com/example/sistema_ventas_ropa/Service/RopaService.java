package com.example.sistema_ventas_ropa.Service;


import org.springframework.stereotype.Service;

import com.example.sistema_ventas_ropa.Dto.RopaDto;
import com.example.sistema_ventas_ropa.Entity.Ropa;
import com.example.sistema_ventas_ropa.Repository.RopaRepository;

import java.util.List;

@Service
public class RopaService {

    private final RopaRepository ropaRepository;

    public RopaService(RopaRepository ropaRepository) {
        this.ropaRepository = ropaRepository;
    }

    public List<Ropa> listarTodos() {
        return ropaRepository.findAll();
    }

    public RopaDto obtenerDtoPorId(Long id) {
        return ropaRepository.findById(id)
            .map(ropa -> {
                RopaDto dto = new RopaDto();
                dto.setId(ropa.getId());
                dto.setNombre(ropa.getNombre());
                dto.setDescripcion(ropa.getDescripcion());
                dto.setPrecio(String.valueOf(ropa.getPrecio()));
                dto.setImagenURL(ropa.getImagenURL());
                return dto;
            })
            .orElseThrow(() -> new RuntimeException("Ropa no encontrado"));
    }



    public Ropa guardar(Ropa ropa) {
        return ropaRepository.save(ropa);
    }

    public void eliminar(Long id) {
        ropaRepository.deleteById(id);
    }

    public boolean existePorNombre(String nombre) {
        return ropaRepository.existsByNombre(nombre);
    }

    public List<List<Ropa>> obtenerRopaEnGrupos(int grupo) {
        List<Ropa> ropas = listarTodos();
        List<List<Ropa>> grupos = new java.util.ArrayList<>();
        for (int i = 0; i < ropas.size(); i += grupo) {
            grupos.add(ropas.subList(i, Math.min(i + grupo, ropas.size())));
        }
        return grupos;
    }

    public void guardar(RopaDto dto) {
        Ropa ropa;

        if (dto.getId() != null) {
            ropa = ropaRepository.findById(dto.getId()).orElse(new Ropa());
        } else {
            ropa = new Ropa();
        }

        ropa.setNombre(dto.getNombre());
        ropa.setDescripcion(dto.getDescripcion());
        ropa.setPrecio(Float.parseFloat(dto.getPrecio()));
        ropa.setImagenURL(dto.getImagenURL());

        ropaRepository.save(ropa);
    }

    public List<RopaDto> listarTodosDto() {
        return listarTodos().stream().map(ropa -> {
            RopaDto dto = new RopaDto();
            dto.setId(ropa.getId());
            dto.setNombre(ropa.getNombre());
            dto.setDescripcion(ropa.getDescripcion());
            dto.setPrecio(String.valueOf(ropa.getPrecio()));
            dto.setImagenURL(ropa.getImagenURL());
            return dto;
        }).toList();
    }

}