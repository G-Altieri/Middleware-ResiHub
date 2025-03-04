package it.univaq.testMiddleware.adapter;

import it.univaq.testMiddleware.models.Dispositivo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExternalDataAdapterFactory {

    @Autowired
    private ZcsAzzurroAdapter zcsAzzurroAdapter;

    @Autowired
    private RandomDataAdapter randomDataAdapter;

    // Se in futuro aggiungi altri adapter, li autowiri qui
    // @Autowired private AltroAdapter altroAdapter;

    public ExternalDataAdapter getAdapter(Dispositivo dispositivo) {
        // Se il campo adapterType è null o "default",ritorna dati random
        if (dispositivo.getAdapterType() == null ||
                dispositivo.getAdapterType().trim().isEmpty() ||
                dispositivo.getAdapterType().equalsIgnoreCase("default") ) {
            System.out.println("Uso Random");
            return randomDataAdapter;
        }
        //Altrimenti torna l adapterGiusto
        else if (dispositivo.getAdapterType().equalsIgnoreCase("zscazzurro")) {
            System.out.println("Uso zscazzurro");
          return zcsAzzurroAdapter;
        }
        // Altrimenti, ritorna un adapter di default o lancia un'eccezione
        return randomDataAdapter;
    }
}
