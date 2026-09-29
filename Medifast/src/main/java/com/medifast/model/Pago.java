package com.medifast.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Entidad encargada de representar el registro y simulación de pagos digitales
 * asociados a las reservas de citas mediante la pasarela simulada de Yape.
 *
 * @author Equipo Medifast
 * @version 1.0
 */
public class Pago {

    /** Identificador único del comprobante/transacción simulada. */
    private Integer pagoId;

    /** Cita médica asociada al pago. */
    private Cita cita;

    /** Método utilizado (por defecto 'YAPE_SIMULADO'). */
    private String metodo;

    /** Monto en moneda local (PEN) de la consulta. */
    private BigDecimal monto;

    /** Estado del resultado de la simulación ('APROBADO', 'RECHAZADO', 'PENDIENTE'). */
    private String estadoSimulacion;

    /** Fecha y hora exacta en que se procesó la simulación del pago. */
    private LocalDateTime fechaPago;

    /**
     * Constructor por defecto.
     */
    public Pago() {
    }

    /**
     * Constructor para inicializar una operación de pago simulado.
     *
     * @param pagoId           Identificador único de pago.
     * @param cita             Instancia de la cita vinculada.
     * @param metodo           Método de pago.
     * @param monto            Importe numérico en BigDecimal.
     * @param estadoSimulacion Estado del pago simulado.
     * @param fechaPago        Marca temporal de ejecución.
     */
    public Pago(Integer pagoId, Cita cita, String metodo, BigDecimal monto, String estadoSimulacion, LocalDateTime fechaPago) {
        this.pagoId = pagoId;
        this.cita = cita;
        this.metodo = metodo;
        this.monto = monto;
        this.estadoSimulacion = estadoSimulacion;
        this.fechaPago = fechaPago;
    }

    /**
     * Ejecuta las validaciones del pago simulado y aprueba la transacción si el monto es mayor a cero.
     *
     * @return {@code true} si la transacción simulada resulta aprobada; de lo contrario {@code false}.
     */
    public boolean procesarSimulacion() {
        if (this.monto != null && this.monto.compareTo(BigDecimal.ZERO) > 0) {
            this.estadoSimulacion = "APROBADO";
            this.fechaPago = LocalDateTime.now();
            return true;
        } else {
            this.estadoSimulacion = "RECHAZADO";
            return false;
        }
    }

    public Integer getPagoId() {
        return pagoId;
    }

    public void setPagoId(Integer pagoId) {
        this.pagoId = pagoId;
    }

    public Cita getCita() {
        return cita;
    }

    public void setCita(Cita cita) {
        this.cita = cita;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public String getEstadoSimulacion() {
        return estadoSimulacion;
    }

    public void setEstadoSimulacion(String estadoSimulacion) {
        this.estadoSimulacion = estadoSimulacion;
    }

    public LocalDateTime getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDateTime fechaPago) {
        this.fechaPago = fechaPago;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pago)) return false;
        Pago pago = (Pago) o;
        return Objects.equals(pagoId, pago.pagoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pagoId);
    }

    @Override
    public String toString() {
        return "Pago{" +
                "pagoId=" + pagoId +
                ", citaId=" + (cita != null ? cita.getCitaId() : "null") +
                ", metodo='" + metodo + '\'' +
                ", monto=" + monto +
                ", estadoSimulacion='" + estadoSimulacion + '\'' +
                ", fechaPago=" + fechaPago +
                '}';
    }
}