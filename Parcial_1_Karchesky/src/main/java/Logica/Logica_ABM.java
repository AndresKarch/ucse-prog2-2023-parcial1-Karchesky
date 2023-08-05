package Logica;

import Tablas.Pago;
import Tablas.Reserva;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Logica_ABM {
    public void alta_pago(int id_reserva,Date fecha_creacion, String tipo, String medio, BigDecimal monto)
    {
        BigDecimal acumulador = null;
        List<Pago> pagos_filtro = encontrar_pagos(id_reserva);
        for (Pago pagos: pagos_filtro)
        {
            BigDecimal monto_obtenido = pagos.getMonto();
            acumulador = monto_obtenido; //+ acumulador;
        }

        if (monto == acumulador)
        {
            Pago pago = new Pago(id_reserva,fecha_creacion,monto,tipo,medio);
            pago.alta(pago);
        }


    }
    public List<Pago> encontrar_pagos(int id_reserva)
    {
        try (Session session = HibernateUtil.getSession())
        {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Pago> query = builder.createQuery(Pago.class);
            Root<Pago> root = query.from(Pago.class);

            Predicate likePredicate = builder.like(root.get("id_reserva"),"%" + id_reserva + "%");
            query.where(likePredicate);

            query.select(root);
            return session.createQuery(query).getResultList();
        }
    }
    public List<Reserva> Reservas(String nombre, String apellido, Date fecha, DecimalFormat monto, DecimalFormat Saldo)
    {
        List<Reserva> reservas = new ArrayList<>();

        return reservas;
    }



}
