package com.medifast;

import com.medifast.model.Cita;
import com.medifast.model.Horario;
import com.medifast.model.Paciente;
import com.medifast.model.Pago;
import com.medifast.service.CitaService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * Clase principal de arranque y prueba para el sistema web Medifast.
 * Permite ejecutar y verificar el flujo operativo administrativo:
 * registro de pacientes, consulta de disponibilidad, reserva de citas y simulación de pago.
 *
 * @author Equipo Medifast UTP
 * @version 1.0
 */
public class Medifast {

    /**
     * Punto de entrada principal de la aplicación.
     *
     * @param args Argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("       INICIANDO SISTEMA MEDIFAST (DEMO)          ");
        System.out.println("==================================================");

        CitaService servicio = new CitaService();

        // 1. Configurar horarios de prueba
        LocalDate hoy = LocalDate.now();
        servicio.agregarHorario(new Horario(1, hoy, LocalTime.of(9, 0), LocalTime.of(9, 30), "DISPONIBLE"));
        servicio.agregarHorario(new Horario(2, hoy, LocalTime.of(10, 0), LocalTime.of(10, 30), "DISPONIBLE"));

        // 2. Registrar paciente
        Paciente nuevoPaciente = new Paciente(null, "72849102", "Juan Perez", "944123456");
        Paciente pacienteRegistrado = servicio.registrarPaciente(nuevoPaciente);
        System.out.println("[RF02] Paciente registrado: " + pacienteRegistrado.getNombre() + " (ID: " + pacienteRegistrado.getPacienteId() + ")");

        // 3. Consultar disponibilidad
        List<Horario> disponibles = servicio.listarHorariosDisponibles(hoy);
        System.out.println("[RF05] Horarios disponibles para hoy: " + disponibles.size());

        // 4. Reservar cita
        Cita citaReservada = servicio.reservarCita(pacienteRegistrado.getPacienteId(), 1);
        System.out.println("[RF01] Cita creada con éxito. ID: " + citaReservada.getCitaId() + " - Estado: " + citaReservada.getEstadoAdministrativo());

        // 5. Simular pago por Yape
        Pago pago = servicio.simularPago(citaReservada.getCitaId(), new BigDecimal("80.00"));
        System.out.println("[RF03] Simulación de Pago Yape: " + pago.getEstadoSimulacion() + " - Monto: S/ " + pago.getMonto());
        System.out.println("[RF04] Estado final de la cita: " + citaReservada.getEstadoAdministrativo());

        System.out.println("==================================================");
        System.out.println("       EJECUCIÓN COMPLETADA CORRECTAMENTE         ");
        System.out.println("==================================================");
    }
}