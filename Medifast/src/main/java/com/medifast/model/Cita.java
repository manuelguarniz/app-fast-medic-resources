package com.medifast.model;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Entidad que vincula a un {@link Paciente} con un {@link Horario} asignado,
 * registrando el estado administrativo del servicio médico.
 *
 * @author Equipo Medifast
 * @version 1.0
 */
public class Cita {

    /** Identificador único de la cita médica. */
    private Integer citaId;

    /** Paciente asociado a la cita médica. */
    private Paciente paciente;

    /** Bloque de horario asignado a la cita. */
    private Horario horario;

    /** Fecha y hora exacta de registro en la plataforma. */
    private LocalDateTime fechaRegistro;

    /** Estado del proceso administrativo (e.g., 'PENDIENTE', 'CONFIRMADA', 'ATENDIDA', 'CANCELADA'). */
    private String estadoAdministrativo;

    /**
     * Constructor por defecto.
     */
    public Cita() {
    }

    /**
     * Constructor completo para inicializar una cita programada.
     *
     * @param citaId               Identificador de la cita.
     * @param paciente             Instancia del paciente registrado.
     * @param horario              Horario reservado.
     * @param fechaRegistro        Fecha y hora de creación de la reserva.
     * @param estadoAdministrativo Estado administrativo inicial.
     */
    public Cita(Integer citaId, Paciente paciente, Horario horario, LocalDateTime fechaRegistro, String estadoAdministrativo) {
        this.citaId = citaId;
        this.paciente = paciente;
        this.horario = horario;
        this.fechaRegistro = fechaRegistro;
        this.estadoAdministrativo = estadoAdministrativo;
    }

    /**
     * Actualiza el estado administrativo a 'CONFIRMADA'.
     */
    public void confirmarCita() {
        this.estadoAdministrativo = "CONFIRMADA";
    }

    /**
     * Actualiza el estado administrativo a 'ATENDIDA'.
     */
    public void marcarAtendida() {
        this.estadoAdministrativo = "ATENDIDA";
    }

    public Integer getCitaId() {
        return citaId;
    }

    public void setCitaId(Integer citaId) {
        this.citaId = citaId;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getEstadoAdministrativo() {
        return estadoAdministrativo;
    }

    public void setEstadoAdministrativo(String estadoAdministrativo) {
        this.estadoAdministrativo = estadoAdministrativo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cita)) return false;
        Cita cita = (Cita) o;
        return Objects.equals(citaId, cita.citaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(citaId);
    }

    @Override
    public String toString() {
        return "Cita{" +
                "citaId=" + citaId +
                ", paciente=" + (paciente != null ? paciente.getNombre() : "null") +
                ", horario=" + horario +
                ", fechaRegistro=" + fechaRegistro +
                ", estadoAdministrativo='" + estadoAdministrativo + '\'' +
                '}';
    }
}