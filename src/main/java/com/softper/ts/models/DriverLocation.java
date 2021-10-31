package com.softper.ts.models;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "driverLocation")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
public class DriverLocation {
  
  @Getter(AccessLevel.PRIVATE)
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Column(name = "locations")
  private String location;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "driverId")
  private Driver driver;
}
