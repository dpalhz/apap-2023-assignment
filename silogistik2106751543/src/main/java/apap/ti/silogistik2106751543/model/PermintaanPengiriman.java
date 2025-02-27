package apap.ti.silogistik2106751543.model;


import java.util.Date;
import java.util.List;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

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
@Table(name = "permintaan_pengiriman")
@SQLDelete(sql = "UPDATE permintaan_pengiriman SET is_cancelled = true WHERE id=?")
@Where(clause = "is_cancelled=false")
public class PermintaanPengiriman {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long idPermintaanPengiriman;

    @NotNull
    @Column(name = "nomor_pengiriman", nullable = false)
    private String nomorPengiriman;

    @NotNull
    @Column(name = "is_cancelled", nullable = false)
    private Boolean isCancelled = Boolean.FALSE;

    @NotNull
    @Column(name = "nama_penerima", nullable = false)
    private String namaPenerima;

    @NotNull
    @Column(name = "alamat_penerima", nullable = false)
    private String alamatPenerima;

    @NotNull
    @Column(name = "tanggal_pengiriman", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date tanggalPengiriman;

    @NotNull
    @Column(name = "biaya_pengiriman", nullable = false)
    private Integer biayaPengiriman;

    @NotNull
    @Column(name = "jenis_layanan", nullable = false)
    private Integer jenisLayanan;

    @NotNull
    @Column(name = "waktu_permintaan", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date waktuPermintaan;


    @PrePersist
    protected void onCreate() {
        waktuPermintaan = new Date(); // Ini akan mengisi waktu saat entitas dibuat.
    }

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_karyawan", referencedColumnName = "id", nullable = false)
    private Karyawan karyawan;


    //  Relasi dengan PermintaanPengirimanBarang jika diperlukan
    @OneToMany(mappedBy = "permintaanPengiriman", cascade = CascadeType.ALL,  fetch = FetchType.EAGER )
    private List<PermintaanPengirimanBarang> daftarPermintaanBarang;
    
}
