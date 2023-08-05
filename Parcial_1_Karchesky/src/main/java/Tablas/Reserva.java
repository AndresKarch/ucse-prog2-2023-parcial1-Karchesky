package Tablas;


import Logica.HibernateUtil;
import org.hibernate.Session;

import java.sql.Date;
import java.text.DecimalFormat;
import javax.persistence.*;
@Entity
@Table(name = "pagos")
public class Reserva implements ABM{
    @Id
    @ManyToOne
    @JoinColumn(name = "id_reserva")
    private int id;
    @Column(name = "fecha_creacion", nullable = false)
    private Date fecha_creacion;
    @Column(name = "fecha_actualizacion", nullable = false)
    private Date fecha_actualizacion;
    @Column(name = "nombre_cliente", nullable = false)
    private String nombre_cliente;
    @Column(name = "apellido_cliente", nullable = false)
    private String apellido_cliente;
    @Column(name = "monto_total", nullable = false)
    private DecimalFormat monto_total;

    @Override
    public void alta(ABM objeto) {
        try(Session session = HibernateUtil.getSession())
        {
            session.beginTransaction();
            session.save(objeto);
            session.getTransaction().commit();
        }
    }

    @Override
    public void baja(ABM objeto) {
        try(Session session = HibernateUtil.getSession())
        {
            session.beginTransaction();
            session.delete(objeto);
            session.getTransaction().commit();
        }
    }

    @Override
    public void modificacion(ABM objeto) {
        try(Session session = HibernateUtil.getSession())
        {
            session.beginTransaction();
            session.update(objeto);
            session.getTransaction().commit();
        }
    }

    public Reserva(int id, Date fecha_creacion, Date fecha_actualizacion, String nombre_cliente, String apellido_cliente, DecimalFormat monto_total) {
        this.id = id;
        this.fecha_creacion = fecha_creacion;
        this.fecha_actualizacion = fecha_actualizacion;
        this.nombre_cliente = nombre_cliente;
        this.apellido_cliente = apellido_cliente;
        this.monto_total = monto_total;
    }

    public DecimalFormat getMonto_total() {
        return monto_total;
    }

    public void setMonto_total(DecimalFormat monto_total) {
        this.monto_total = monto_total;
    }
}
