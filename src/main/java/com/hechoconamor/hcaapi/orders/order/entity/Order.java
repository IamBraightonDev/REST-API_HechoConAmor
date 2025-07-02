package com.hechoconamor.hcaapi.orders.order.entity;

import com.hechoconamor.hcaapi.orders.client.entity.Client;
import com.hechoconamor.hcaapi.orders.order.enums.OrderStatus;
import com.hechoconamor.hcaapi.orders.order_detail.entity.OrderDetail;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Fecha y hora del pedido
    @Column(nullable = false)
    private LocalDateTime fecha;

    // Relación con el cliente
    @ManyToOne(optional = false)
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus estado;

    // Relación con los detalles del pedido
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderDetail> detalles;
}