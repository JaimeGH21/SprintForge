package es.uclm.sprintforge.negocio;

import es.uclm.sprintforge.dominio.Inmueble;
import java.util.ArrayList;
import java.util.List;

public class GestorBusquedas {

    public GestorBusquedas() {}

    public List<Inmueble> buscarAlojamiento(String destino, String fechaInicio, String fechaFin) {
        System.out.println("Buscando alojamiento en: " + destino);
        
        List<Inmueble> listaSimulada = new ArrayList<>();
        
        // Simulamos un resultado si el destino coincide
        if (destino != null && "Madrid".equalsIgnoreCase(destino)) {
            // Creamos un objeto de prueba
            Inmueble i = new Inmueble();
            i.setDireccion("Calle Gran Vía, Madrid");
            i.setPrecio(100.0);
            i.setDescripcion("Apartamento céntrico y luminoso");
            listaSimulada.add(i);
        }
        
        return listaSimulada;
    }
}