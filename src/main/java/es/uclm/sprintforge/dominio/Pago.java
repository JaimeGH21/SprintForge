package es.uclm.sprintforge.dominio;

public class Pago {
    private Long id;
    private Reserva reserva;
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Reserva getReserva() { return reserva; }
    public void setReserva(Reserva reserva) { this.reserva = reserva; }
}