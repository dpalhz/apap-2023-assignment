package apap.ti.silogistik2106751543.model;
import java.math.BigDecimal;
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
@Table(name = "barang")
public class Barang {


    @Id
    @Column(name = "sku", nullable = false)
    private String sku;

    @NotNull
    @Column(name = "tipe_barang", nullable = false)
    private Integer tipeBarang;

    @NotNull
    @Column(name = "merk", nullable = false)
    private String merk;


    @NotNull
    @Column(name = "harga_barang", nullable = false)
    private BigDecimal hargaBarang;


    @OneToMany(mappedBy = "barang", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<GudangBarang> listGudangBarang;


     // Relasi dengan PermintaanPengirimanBarang jika diperlukan
    @OneToMany(mappedBy = "barang", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PermintaanPengirimanBarang> daftarPermintaanBarang;

    
}
