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
@Table(name = "permintaan_pengiriman_barang")
public class PermintaanPengirimanBarang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long idPermintaanPengirimanBarang;

    @NotNull
    @Column(name = "kuantitas_pengiriman", nullable = false)
    private Integer kuantitasPengiriman;


    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_permintaan_pengiriman", referencedColumnName = "id", nullable = false)
    private PermintaanPengiriman permintaanPengiriman;


    @NotNull
    @ManyToOne
    @JoinColumn(name = "sku_barang", nullable = false)
    private Barang barang;
    
}
