package apap.ti.silogistik2106751543.model;

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
@Table(name = "gudang_barang")
public class GudangBarang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long idGudangBarang;

    @NotNull
    @Column(name = "stok", nullable = false )
    private Integer stok;

    // Menambahkan relasi many-to-one ke Gudang
    @ManyToOne
    @JoinColumn(name = "id_gudang", referencedColumnName= "id", nullable = false)
    private Gudang gudang;

    // Menambahkan relasi many-to-one ke Barang
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sku_barang", referencedColumnName = "sku", nullable = false)
    private Barang barang;

    
}
