package apap.ti.silogistik2106751543.model;


import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "gudang")
public class Gudang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long idGudang;


    @NotNull
    @Column(name = "gudang", nullable = false)
    private String namaGudang;


    @NotNull
    @Column(name = "alamat_gudang", nullable = false)
    private String alamatGudang;


    @OneToMany(mappedBy = "gudang", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<GudangBarang> gudangBarang;

 

    
    
}
