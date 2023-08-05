package Tablas;

import Logica.HibernateUtil;
import org.hibernate.Session;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.text.DecimalFormat;

@Entity
@Table(name = "pagos")
public class Pago implements ABM{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToMany
    @JoinColumn(name = "id_reserva")
    private int id_reserva;
    @Column(name = "fecha_creacion", nullable = false)
    private Date fecha_creacion;
    @Column(name = "monto", nullable = false)
    private BigDecimal monto;
    @Column(name = "tipo_pago", nullable = false)
    private String tipo_pago;
    @Column(name = "medio_pago", nullable = false)
    private String medio_pago;

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
    public Pago(int id_reserva, Date fecha_creacion, BigDecimal monto, String tipo_pago, String medio_pago)
    {
        this.id_reserva = id_reserva;
        this.fecha_creacion = fecha_creacion;
        this.monto = monto;
        this.tipo_pago = tipo_pago;
        this.medio_pago = medio_pago;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }
}
