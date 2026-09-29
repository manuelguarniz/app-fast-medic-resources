package com.medifast.service;

import com.medifast.model.Cita;
import com.medifast.model.Horario;
import com.medifast.model.Paciente;
import com.medifast.model.Pago;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Servicio centralizado para la orquestación de la lógica de negocio del Consultorio Medifast.
 * Implementa los casos de uso definidos: consulta de disponibilidad, registro de pacientes,
 * reservación de citas, simulación de pagos por Yape y consulta general de citas programadas.
 *
 * @author Equipo Medifast
 * @version 1.0
 */
public class CitaService {

    private final List<Paciente> listaPacientes = new ArrayList<>();
    private final List<Horario> listaHorarios = new ArrayList<>();
    private final List<Cita> listaCitas = new ArrayList<>();
    private final List<Pago> listaPagos = new ArrayList<>();

    private int secPaciente = 1;
    private int secCita = 1;
    private int secPago = 1;

    /**
     * Recupera todos los bloques de atención disponibles para una fecha específica.
     *
     * @param fecha La fecha solicitada para consultar disponibilidad.
     * @return Lista de instancias {@link Horario} con estado 'DISPONIBLE'.
     * @throws IllegalArgumentException si el parámetro fecha es nulo.
     */
    public List<Horario> listarHorariosDisponibles(LocalDate fecha) {
        if (fecha == null) {
            throw new IllegalArgumentException("La fecha de consulta no puede ser nula.");
        }
        return listaHorarios.stream()
                .filter(h -> h.getFecha().equals(fecha) && h.isDisponible())
                .collect(Collectors.toList());
    }

    /**
     * Registra los datos básicos de un nuevo paciente o retorna el existente en caso de coincidencia por DNI.
     *
     * @param p Instancia de {@link Paciente} a registrar.
     * @return La entidad {@link Paciente} persistida con su ID asignado.
     * @throws IllegalArgumentException si el paciente o sus atributos requeridos son inválidos.
     */
    public Paciente registrarPaciente(Paciente p) {
        if (p == null || p.getDni() == null || p.getNombre() == null) {
            throw new IllegalArgumentException("Los datos básicos del paciente (DNI, Nombre) son obligatorios.");
        }

        for (Paciente existente : listaPacientes) {
            if (existente.getDni().equals(p.getDni())) {
                return existente;
            }
        }

        p.setPacienteId(secPaciente++);
        listaPacientes.add(p);
        return p;
    }

    /**
     * Reserva una nueva cita vinculando un paciente y un horario previamente disponible.
     * Aplica la regla de negocio de exclusividad de horario.
     *
     * @param pacId Identificador del paciente registrado.
     * @param horId Identificador del bloque de horario seleccionado.
     * @return La entidad {@link Cita} creada con estado inicial 'PENDIENTE'.
     * @throws IllegalStateException    si el horario no existe o ya no está disponible.
     * @throws IllegalArgumentException si el paciente indicado no existe.
     */
    public Cita reservarCita(Integer pacId, Integer horId) {
        Paciente paciente = listaPacientes.stream()
                .filter(p -> p.getPacienteId().equals(pacId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Paciente no encontrado con ID: " + pacId));

        Horario horario = listaHorarios.stream()
                .filter(h -> h.getHorarioId().equals(horId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Horario no encontrado con ID: " + horId));

        if (!horario.isDisponible()) {
            throw new IllegalStateException("El horario seleccionado ya no se encuentra disponible.");
        }

        horario.setEstado("RESERVADO");

        Cita nuevaCita = new Cita(
                secCita++,
                paciente,
                horario,
                LocalDateTime.now(),
                "PENDIENTE"
        );
        listaCitas.add(nuevaCita);
        return nuevaCita;
    }

    /**
     * Procesa la simulación de pago por Yape para una cita previamente registrada.
     *
     * @param citaId Identificador de la cita médica sujeta a pago.
     * @param monto  Importe de la consulta médica en formato {@link BigDecimal}.
     * @return Objeto {@link Pago} con el detalle y estado de la simulación.
     * @throws IllegalArgumentException si la cita especificada no existe.
     */
    public Pago simularPago(Integer citaId, BigDecimal monto) {
        Cita cita = listaCitas.stream()
                .filter(c -> c.getCitaId().equals(citaId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Cita no encontrada con ID: " + citaId));

        Pago pago = new Pago();
        pago.setPagoId(secPago++);
        pago.setCita(cita);
        pago.setMetodo("YAPE_SIMULADO");
        pago.setMonto(monto);

        boolean exitoso = pago.procesarSimulacion();
        if (exitoso) {
            cita.confirmarCita();
        }

        listaPagos.add(pago);
        return pago;
    }

    /**
     * Recupera el listado completo de citas programadas para el seguimiento del médico.
     *
     * @return Lista con todas las instancias de {@link Cita} registradas.
     */
    public List<Cita> listarCitasProgramadas() {
        return new ArrayList<>(listaCitas);
    }

    /**
     * Permite agregar horarios disponibles a la colección interna (utilidad de pruebas).
     *
     * @param horario Bloque de horario a incorporar.
     */
    public void agregarHorario(Horario horario) {
        if (horario != null) {
            this.listaHorarios.add(horario);
        }
    }
}