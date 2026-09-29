package com.medifast.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

/**
 * Representa los intervalos de disponibilidad médica configurados en el consultorio.
 *
 * @author Equipo Medifast
 * @version 1.0
 */
public class Horario {

    /** Identificador único del horario. */
    private Integer horarioId;

    /** Fecha correspondiente a la jornada de atención. */
    private LocalDate fecha;

    /** Hora de inicio del bloque de atención. */
    private LocalTime horaInicio;

    /** Hora de término del bloque de atención. */
    private LocalTime horaFin;

    /** Estado del horario (e.g., 'DISPONIBLE', 'RESERVADO', 'BLOQUEADO'). */
    private String estado;

    /**
     * Constructor por defecto.
     */
    public Horario() {
    }

    /**
     * Constructor con todos los atributos de la entidad.
     *
     * @param horarioId  Identificador del bloque de horario.
     * @param fecha      Fecha programada.
     * @param horaInicio Hora de inicio.
     * @param horaFin    Hora de finalización.
     * @param estado     Estado actual de disponibilidad.
     */
    public Horario(Integer horarioId, LocalDate fecha, LocalTime horaInicio, LocalTime horaFin, String estado) {
        this.horarioId = horarioId;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.estado = estado;
    }

    /**
     * Determina si el bloque de horario se encuentra libre para agendar una cita.
     *
     * @return {@code true} si el estado es 'DISPONIBLE' ignorando mayúsculas/minúsculas; de lo contrario {@code false}.
     */
    public boolean isDisponible() {
        return "DISPONIBLE".equalsIgnoreCase(this.estado);
    }

    public Integer getHorarioId() {
        return horarioId;
    }

    public void setHorarioId(Integer horarioId) {
        this.horarioId = horarioId;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Horario)) return false;
        Horario horario = (Horario) o;
        return Objects.equals(horarioId, horario.horarioId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(horarioId);
    }

    @Override
    public String toString() {
        return "Horario{" +
                "horarioId=" + horarioId +
                ", fecha=" + fecha +
                ", horaInicio=" + horaInicio +
                ", horaFin=" + horaFin +
                ", estado='" + estado + '\'' +
                '}';
    }
}