package com.medifast.model;

import java.util.Objects;

/**
 * Representa la entidad Paciente dentro del sistema web Medifast.
 * Contiene la información básica administrativa requerida para agendar y gestionar citas.
 *
 * @author Equipo Medifast
 * @version 1.0
 */
public class Paciente {

    /** Identificador único autonumérico del paciente. */
    private Integer pacienteId;

    /** Documento Nacional de Identidad del paciente (8 dígitos numéricos). */
    private String dni;

    /** Nombre completo del paciente. */
    private String nombre;

    /** Número telefónico de contacto del paciente. */
    private String telefono;

    /**
     * Constructor por defecto sin parámetros.
     */
    public Paciente() {
    }

    /**
     * Constructor parametrizado para inicializar un paciente.
     *
     * @param pacienteId Identificador único del paciente.
     * @param dni        Documento de identidad del paciente.
     * @param nombre     Nombre y apellidos del paciente.
     * @param telefono   Teléfono de contacto del paciente.
     */
    public Paciente(Integer pacienteId, String dni, String nombre, String telefono) {
        this.pacienteId = pacienteId;
        this.dni = dni;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    /**
     * Obtiene el identificador del paciente.
     * @return El ID del paciente.
     */
    public Integer getPacienteId() {
        return pacienteId;
    }

    /**
     * Asigna el identificador del paciente.
     * @param pacienteId El ID numérico a asignar.
     */
    public void setPacienteId(Integer pacienteId) {
        this.pacienteId = pacienteId;
    }

    /**
     * Obtiene el DNI del paciente.
     * @return Cadena con el número de DNI.
     */
    public String getDni() {
        return dni;
    }

    /**
     * Asigna el DNI del paciente.
     * @param dni Cadena con el DNI.
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * Obtiene el nombre del paciente.
     * @return Nombre del paciente.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Asigna el nombre del paciente.
     * @param nombre Nombre del paciente.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el número telefónico del paciente.
     * @return Teléfono de contacto.
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Asigna el número telefónico del paciente.
     * @param telefono Teléfono de contacto.
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Paciente)) return false;
        Paciente paciente = (Paciente) o;
        return Objects.equals(pacienteId, paciente.pacienteId) || Objects.equals(dni, paciente.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pacienteId, dni);
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "pacienteId=" + pacienteId +
                ", dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}